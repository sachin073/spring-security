package com.security.demo.config.security.models;

import com.security.demo.persistance.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.user.getRoles().stream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

            role.getPrivileges().stream().forEach(privilege -> {
                authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            });

        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isTokenExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
