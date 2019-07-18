package com.security.demo.services;

import com.security.demo.exception.UserAlreadyExistException;
import com.security.demo.persistance.dao.RoleRepository;
import com.security.demo.persistance.dao.UserRepository;
import com.security.demo.persistance.models.Role;
import com.security.demo.persistance.models.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User registerNewUserAccount(User accountDto) throws UserAlreadyExistException {
        if (userRepository.findByEmail(accountDto.getEmail())!=null){
            throw new UserAlreadyExistException("user already present with same email");
        }
        accountDto.setPassword(encoder.encode(accountDto.getPassword()));

        accountDto.setRoles(Arrays.asList(roleRepository.findByName("ROLES_USER")) );
      return   userRepository.save(accountDto);
    }

    @Override
    public User getUser(String verificationToken) {
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
            userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public List<User> getAll(){
        List<User> users =userRepository.findAll();
        for (User user :
                users) {
            Hibernate.initialize(user.getRoles());
            Collection<Role> roles = user.getRoles();
            for (Role role :
                    roles) {
                Hibernate.initialize(role.getPrivileges());
            }

        }
        return users;
    }
}
