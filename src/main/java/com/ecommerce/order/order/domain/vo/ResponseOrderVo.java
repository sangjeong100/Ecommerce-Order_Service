package com.ecommerce.order.order.domain.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResponseOrderVo {

	private String productId;
	private Integer qty;
	private Integer unitPrice;
	private Integer totalPrice;
	private LocalDate createDate;
	
	private String orderId;
}
