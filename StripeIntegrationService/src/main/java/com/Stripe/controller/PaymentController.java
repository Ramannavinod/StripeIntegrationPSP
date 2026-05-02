package com.Stripe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stripe.pojo.CreatePayment;
import com.Stripe.serviceinter.PaymentServiceintern;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentServiceintern paymentServiceintern;
	
	@PostMapping
	public ResponseEntity<String> getPayment(@RequestBody CreatePayment createPayment) {
		 log.info("Received request to get payment");
		 ResponseEntity<String> response = paymentServiceintern.CreatePaymentIntent(createPayment);
//		 log.info("Payment Intent created with response: {}", response);
		return response;
	}

}
