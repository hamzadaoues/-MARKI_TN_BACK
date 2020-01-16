package com.MARKITN.demo.security.service;

import com.MARKITN.demo.model.User;
import com.MARKITN.demo.security.model.PasswordResetToken;
import com.MARKITN.demo.security.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetToken createRestToken(User user) {

        //Token Creation-Save
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(60);
        passwordResetTokenRepository.save(token);
        return token;
    }
}
