package com.security.demo.controller;

import com.security.demo.exception.UserAlreadyExistException;
import com.security.demo.persistance.dao.UserRepository;
import com.security.demo.persistance.models.Privilege;
import com.security.demo.persistance.models.Role;
import com.security.demo.persistance.models.User;
import com.security.demo.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserRegistrationController {

    static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    IUserService userService;

    @Autowired
    UserRepository userRepository;



    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String registerUserAccount() throws UserAlreadyExistException {
        LOGGER.debug("Registering form : " );

        return "users/createUser";
    }


    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public String registerUserAccount(@Valid final User accountDto,
                                      Model model) throws UserAlreadyExistException {
        LOGGER.debug("Registering user account with information: {}", accountDto);

        final User registered = userService.registerNewUserAccount(accountDto);
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));

        User user = userRepository.findByEmail(accountDto.getEmail());
        model.addAttribute("user",user);
        return "redirect:/user/details";
    }

    @RequestMapping(value = "/user/details", method = RequestMethod.GET)
    public String registerUserAccount(Model model) throws UserAlreadyExistException {
        LOGGER.debug("user account with information:");

        List<User> user = userService.getAll();
        model.addAttribute("users",user);
        for (User us :
                user) {
            System.out.println(us.getFirstName());
            for (Role role :
                    us.getRoles()) {
                System.out.println(role.getName());

                for (Privilege privilege :
                        role.getPrivileges()) {
                    System.out.println(privilege.getName()+"\n");
                }
            }
        }
        return "users/details";
    }


    @RequestMapping(value = "/user/{email}", method = RequestMethod.POST)
    public String registerUserAccount(@PathVariable("email") final String email, Model model) {


        User user= userRepository.findByEmail(email);
        model.addAttribute("user", Arrays.asList(user));

        return "users/user";

    }

}
