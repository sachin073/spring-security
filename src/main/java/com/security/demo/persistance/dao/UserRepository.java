package com.security.demo.persistance.dao;

import com.security.demo.persistance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByLoginId(String loginId);

    User findByLoginIdAndPassword(String loginId,String password);

    @Override
    void delete(User user);

}