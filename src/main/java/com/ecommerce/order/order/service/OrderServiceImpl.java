package com.ecommerce.order.order.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ecommerce.order.order.domain.dto.OrderDto;
import com.ecommerce.order.order.domain.jpa.entity.OrderEntity;
import com.ecommerce.order.order.domain.jpa.repo.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final Environment env;
	private final OrderRepo orderRepo;

	@Override
	public OrderDto createOrder(OrderDto orderDetails) {
		
		orderDetails.setOrderId(UUID.randomUUID().toString());
		orderDetails.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		OrderEntity orderEntity = modelMapper.map(orderDetails, OrderEntity.class);
		
		orderRepo.save(orderEntity);
		return modelMapper.map(orderEntity, OrderDto.class);
	}

	@Override
	public OrderDto getOrderByOrderId(String orderId) {
		return new ModelMapper().map(orderRepo.findByOrderId(orderId),OrderDto.class);
	}

	@Override
	public Iterable<OrderEntity> getOrderListByUserId(String userId) {
		return orderRepo.findByUserId(userId);
	}

	
}
