package com.ecommerce.order.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.order.domain.dto.OrderDto;
import com.ecommerce.order.order.domain.jpa.entity.OrderEntity;
import com.ecommerce.order.order.domain.vo.RequestOrderVo;
import com.ecommerce.order.order.domain.vo.ResponseOrderVo;
import com.ecommerce.order.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderRestController {

	private final Environment env;
	
	private final OrderService orderService;
	
	/**
	 * 서버 상태 체크
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/health_check")
	public String status(HttpServletRequest request) {
		return String.format("It's Working in User Service on Port %s", request.getServerPort());
	}
	
	/**
	 * 유저 신규 주문
	 * 
	 * @param userId
	 * @param orderDetails
	 * @return
	 */
	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrderVo> createOrder(@PathVariable("userId") String userId, 
																@RequestBody RequestOrderVo orderDetails) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
		orderDto.setUserId(userId);
		
		ResponseOrderVo responseOrderVo = modelMapper.map(orderService.createOrder(orderDto), ResponseOrderVo.class);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(responseOrderVo);
		
	}
	
	/**
	 * 유저 주문 조회 
	 * 
	 * @return
	 */
	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrderVo>> geOrderList(@PathVariable("userId") String userId){
		
		Iterable<OrderEntity> orderList = orderService.getOrderListByUserId(userId);
		
		List<ResponseOrderVo> resultList = new ArrayList<>();
		orderList.forEach(order -> {
			resultList.add(new ModelMapper().map(order, ResponseOrderVo.class));
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
}
