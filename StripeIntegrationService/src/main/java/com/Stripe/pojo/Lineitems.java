package com.Stripe.pojo;

import lombok.Data;

@Data
public class Lineitems {
		private int quantity;
		private String productName;
		private String currency;
		private String unitAmount;
}
