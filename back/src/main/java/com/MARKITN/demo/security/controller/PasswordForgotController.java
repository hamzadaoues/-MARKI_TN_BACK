package com.MARKITN.demo.security.controller;


import com.MARKITN.demo.model.User;
import com.MARKITN.demo.repository.UserRepository;
import com.MARKITN.demo.security.dto.NewPasswordDto;
import com.MARKITN.demo.security.dto.PasswordForgotDto;
import com.MARKITN.demo.security.dto.TokenDto;
import com.MARKITN.demo.security.model.PasswordResetToken;
import com.MARKITN.demo.security.repository.PasswordResetTokenRepository;
import com.MARKITN.demo.security.response.ApiResponse;
import com.MARKITN.demo.security.service.EmailService;
import com.MARKITN.demo.security.service.TokenVerificationService;
import com.MARKITN.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private TokenVerificationService tokenVerificationService;


    //Sending Email to ResetPassword
    @PostMapping(value = "")
    public ResponseEntity<?> processForgotPasswordForm(@Valid @RequestBody PasswordForgotDto form,
                                                       BindingResult result,
                                                       HttpServletRequest httpServletRequest) throws IOException, MessagingException {

        if (result.hasErrors()){
            return new ResponseEntity(new ApiResponse(false, "Result error."),
                    HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(form.getEmail());
        //User not found
        if (user == null){

            return new ResponseEntity(new ApiResponse(false, "We could not find an account for that e-mail address."),
                    HttpStatus.BAD_REQUEST);
        }

        //sending mail
        emailService.createResetMail(user);

        return new ResponseEntity(new ApiResponse(true, "Email successfully sent. "),
                HttpStatus.ACCEPTED);

    }


    //Updating password
    @PostMapping(value = "/new-password")
    public ResponseEntity<?> newPassword(@Valid @RequestBody NewPasswordDto newPasswordDto){
        if (tokenVerificationService.verifyEmail(newPasswordDto.getToken()).getStatusCode()==HttpStatus.BAD_REQUEST){
            return tokenVerificationService.verifyEmail(newPasswordDto.getToken());
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(newPasswordDto.getToken());
        User user=passwordResetToken.getUser();
        user.setPassword(newPasswordDto.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);
        passwordResetToken.setUsed(true);
        passwordResetTokenRepository.save(passwordResetToken);

        return new ResponseEntity(new ApiResponse(true, "Password changed successfully"),
                HttpStatus.ACCEPTED);    }

}