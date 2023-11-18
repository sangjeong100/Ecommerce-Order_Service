package com.ecommerce.order.order.service;

import com.ecommerce.order.order.domain.dto.OrderDto;
import com.ecommerce.order.order.domain.jpa.entity.OrderEntity;

public interface OrderService {

	OrderDto createOrder(OrderDto orderDetails);
	OrderDto getOrderByOrderId(String orderId);
	Iterable<OrderEntity> getOrderListByUserId(String userId);
	
}
