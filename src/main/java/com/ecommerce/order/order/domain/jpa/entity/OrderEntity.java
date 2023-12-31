package com.ecommerce.order.order.domain.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class OrderEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 120)
	private String productId;
	
	@Column(nullable = false)
	private Integer qty;
	
	@Column(nullable = false)
	private Integer unitPrice;
	
	@Column(nullable = false)
	private Integer totalPrice;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false, unique = true)
	private String orderId;
	
	@Column(nullable = false, updatable = false, insertable = false)
	@ColumnDefault( value = "CURRENT_TIMESTAMP")
	private LocalDate createDate;
	
	
}
