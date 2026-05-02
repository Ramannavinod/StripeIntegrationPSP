package com.Stripe.HTTPService;import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import com.Stripe.pojo.CreatePayment;
import com.Stripe.pojo.Lineitems;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HTTPServiceengine {
	public RestClient restclient;
	public HTTPServiceengine(RestClient.Builder builder) {
		 this.restclient = builder.build();
  }
	public ResponseEntity<String> createpaymenet(CreatePayment payment) {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	headers.setBearerAuth("sk_test_tR3PYbcVNZZ796tH88S4VQ2u");
	MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

    body.add("mode", payment.getMode());
    body.add("success_url", payment.getSuccess_url());
    body.add("cancel_url", payment.getCancel_url());

    for (int i = 0; i < payment.getLine_items().size(); i++) {

        Lineitems item = payment.getLine_items().get(i);

        body.add("line_items[" + i + "][quantity]", String.valueOf(item.getQuantity()));
        body.add("line_items[" + i + "][price_data][currency]", item.getCurrency());
        body.add("line_items[" + i + "][price_data][unit_amount]", item.getUnitAmount());
        body.add("line_items[" + i + "][price_data][product_data][name]", item.getProductName());
    }

 
	 ResponseEntity<String> response=restclient.post()
	.uri("https://api.stripe.com/v1/checkout/sessions")
	.headers(h->h.addAll(headers))
	.body(body)
	.retrieve()
	.toEntity(String.class);
	 log.info("Stripe API response: {}", response.getBody());
	
	return response;
	}
	

}
