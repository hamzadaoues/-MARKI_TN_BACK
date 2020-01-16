package com.MARKITN.demo.service.implementation;

import com.MARKITN.demo.model.Role;
import com.MARKITN.demo.model.User;
import com.MARKITN.demo.repository.UserRepository;
import com.MARKITN.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> findAllByRoles(Set<Role> roles) {
        return userRepository.getAllByRoles(roles);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

}
