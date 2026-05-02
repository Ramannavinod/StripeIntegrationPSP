package com.Stripe.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.Stripe.HTTPService.HTTPReq;
import com.Stripe.HTTPService.HTTPServiceengine;
import com.Stripe.exception.StripeExceptionHandling;
import com.Stripe.pojo.CreatePayment;
import com.Stripe.pojo.Lineitems;
import com.Stripe.serviceinter.PaymentServiceintern;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceintern {
	
	private final HTTPServiceengine httpServiceengine;
	
	@Value("${stripe.secret.key}")
	private String secret;
	
	@Value("${stripe.url}")
    private String url;	

	@Override
	public ResponseEntity<String> CreatePaymentIntent(CreatePayment payment) {
		log.info("Creating Payment Intent");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBearerAuth(secret);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	    body.add("mode", payment.getMode());
	    body.add("success_url", payment.getSuccess_url());
	    body.add("cancel_url", payment.getCancel_url());
        if(payment.getCancel_url()==null) {
        	throw new StripeExceptionHandling("10001","Cancel URL is required",HttpStatus.BAD_REQUEST.value());
		}
	    for (int i = 0; i < payment.getLine_items().size(); i++) {

	        Lineitems item = payment.getLine_items().get(i);

	        body.add("line_items[" + i + "][quantity]", String.valueOf(item.getQuantity()));
	        body.add("line_items[" + i + "][price_data][currency]", item.getCurrency());
	        body.add("line_items[" + i + "][price_data][unit_amount]", item.getUnitAmount());
	        body.add("line_items[" + i + "][price_data][product_data][name]", item.getProductName());
	    }
        
	    HTTPReq httpreq = HTTPReq.builder()
		        .url(url)
		        .method(HttpMethod.POST)
		        .headers(headers)
		        .body(body)
		        .build();
		ResponseEntity<String> response=httpServiceengine.createpaymenet(httpreq);
		 log.info("Stripe API response: {}", response.getBody());
		return response;
	}
	

}
