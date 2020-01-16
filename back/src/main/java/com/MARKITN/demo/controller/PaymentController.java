package com.MARKITN.demo.controller;

import com.MARKITN.demo.payment.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        double amount = Double.parseDouble(request.getHeader("amount"));
        System.out.println(this.stripeClient.chargeCreditCard(token, amount));
        return this.stripeClient.chargeCreditCard(token, amount);
    }
}