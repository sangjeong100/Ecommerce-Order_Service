package com.ecommerce.order.order.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestOrderVo {

	private String productId;
	private Integer qty;
	private Integer unitPrice;
	
	
	
}
