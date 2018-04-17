package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.OrderMapper;
import com.test.model.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	public List<Order> getOrderListByUserId(Integer userId) {
		return orderMapper.getOrderListByUserId(userId);
	}
	
	public void createOrder(Order order) {
		orderMapper.createOrder(order);
	}

	public Long countTotal() {
		
		return orderMapper.countTotal();
	}

	public List<Order> selectGtUserId(Integer userId) {
		
		return orderMapper.selectGtUserId(userId);
	}

	public void batchInsert(List<Order> orders) {
		orderMapper.batchInsert(orders);
	}

	public void foreachInsert(List<Order> orders) {
		orders.forEach((order) ->orderMapper.createOrder(order));
	}
}
