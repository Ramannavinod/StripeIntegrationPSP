package com.Stripe.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Stripe.HTTPService.HTTPServiceengine;
import com.Stripe.pojo.CreatePayment;
import com.Stripe.serviceinter.PaymentServiceintern;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceintern {
	
	private final HTTPServiceengine httpServiceengine;

	@Override
	public ResponseEntity<String> CreatePaymentIntent(CreatePayment createPayment) {
		log.info("Creating Payment Intent");
		ResponseEntity<String> response=httpServiceengine.createpaymenet(createPayment);
		return response;
	}
	

}
