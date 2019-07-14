package com.security.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder encoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
         //csrf disable
                .csrf().disable()

                //start with auth request
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                // allow pages without logins
                .antMatchers("/anonymous*").anonymous()
                // allow url to let users login
                .antMatchers("/login*").permitAll()


         //other need login
                .anyRequest().authenticated()

            // >> set custom login form
                    .and()
                    .formLogin()
                    .loginPage("/login.jsp")
                    // need to be form action for login form , spring security listen to it
                    .loginProcessingUrl("/perform_login")
                    // landing page for logged-in user
                    .defaultSuccessUrl("/home", true)
                    // if invalid login performed
                    .failureHandler(authenticationFailureHandler())

            // customize error page handler for unauthorized request
                .and()
                    //custom access denied page handler
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())

            // logout processing
                .and()
                .logout()
                    //logout processing url
                .logoutUrl("/perform_logout")
                    //steps need to perform
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                    // custom handler to do extra task like auditing or other
                .logoutSuccessHandler(logoutSuccessHandler());


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("sachin").password(encoder.encode("password")).roles("user").
                and().
                withUser("admin").password(encoder.encode("password")).roles("ADMIN");

    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

}
