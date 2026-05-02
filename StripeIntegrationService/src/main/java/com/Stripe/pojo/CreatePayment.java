package com.Stripe.pojo;

import java.util.List;

import lombok.Data;

@Data
public class CreatePayment {
	private String mode;
	private String success_url;
	private String cancel_url;
	private List<Lineitems> line_items;

}
