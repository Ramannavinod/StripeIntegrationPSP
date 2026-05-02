package com.Stripe.HTTPService;import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import com.Stripe.exception.StripeExceptionHandling;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HTTPServiceengine {
	public RestClient restclient;
	public HTTPServiceengine(RestClient.Builder builder) {
		 this.restclient = builder.build();
  }
	public ResponseEntity<String> createpaymenet(HTTPReq httpreq) {
	
     try {
	 ResponseEntity<String> response=restclient.method(httpreq.getMethod())
	.uri(httpreq.getUrl())
	.headers(h->h.addAll(httpreq.getHeaders()))
	.body(httpreq.getBody())
	.retrieve()
	.toEntity(String.class);
	 log.info("Stripe API response: {}", response.getBody());
	
	return response;
     }
     catch(HttpServerErrorException | HttpClientErrorException e) {
        log.error("Stripe API error: {}", e.getResponseBodyAsString());
        throw new StripeExceptionHandling("10002", "unable to connect with stripe", HttpStatus.BAD_GATEWAY.value());
     }
     catch(Exception e) {
		 log.error("Error while calling Stripe API: {}", e.getMessage());
		 throw new StripeExceptionHandling("10003", "An unexpected error occurred while processing the payment", HttpStatus.INTERNAL_SERVER_ERROR.value());
	 }
	}
	

}
