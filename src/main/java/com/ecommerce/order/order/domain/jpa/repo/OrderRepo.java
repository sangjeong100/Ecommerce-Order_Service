package com.ecommerce.order.order.domain.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.order.domain.jpa.entity.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Long>{
	
	OrderEntity findByOrderId(String orderId);
	Iterable<OrderEntity> findByUserId(String userId);
}
