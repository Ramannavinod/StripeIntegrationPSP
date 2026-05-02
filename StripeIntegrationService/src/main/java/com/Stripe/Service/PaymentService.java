package com.Stripe.Service;

import org.springframework.stereotype.Service;

import com.Stripe.serviceinter.PaymentServiceintern;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService implements PaymentServiceintern {

	@Override
	public String CreatePaymentIntent() {
		log.info("Creating Payment Intent");
		return "Create Payment Intent";
	}
	

}
