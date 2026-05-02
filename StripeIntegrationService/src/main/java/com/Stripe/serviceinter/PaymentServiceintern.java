package com.Stripe.serviceinter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Stripe.pojo.CreatePayment;

@Component
public interface PaymentServiceintern {
	ResponseEntity<String> CreatePaymentIntent(CreatePayment createPayment);
}
