package com.MARKITN.demo.security.service;

import com.MARKITN.demo.repository.UserRepository;
import com.MARKITN.demo.security.model.PasswordResetToken;
import com.MARKITN.demo.security.repository.PasswordResetTokenRepository;
import com.MARKITN.demo.security.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TokenVerificationService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> verifyEmail(String token) {
        System.out.println("here2");
        System.out.println(token);
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        System.out.println(passwordResetToken);
        if (passwordResetToken == null) {
            return new ResponseEntity(new ApiResponse(false, "Invalid token. "),
                    HttpStatus.BAD_REQUEST);
        }

        if(passwordResetToken.isUsed()){
            return new ResponseEntity(new ApiResponse(false, "Used Token. "),
                    HttpStatus.BAD_REQUEST);
        }


        if (passwordResetToken.getExpiryDate().before(Calendar.getInstance().getTime())) {
            return new ResponseEntity(new ApiResponse(false, "Expired token "),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new ApiResponse(true, "Valid token "),
                HttpStatus.ACCEPTED);

    }


}
