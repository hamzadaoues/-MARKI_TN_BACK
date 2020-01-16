package com.MARKITN.demo.service;

import com.MARKITN.demo.model.Role;
import com.MARKITN.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {


    void updatePassword(String password, Long userId);

    User findUserById(Long userId);

    List<User> findAllByRoles(Set<Role> roles);

    boolean existsById(Long id);

    User findUserByUsername(String username);

    User updateUser(User user);
}