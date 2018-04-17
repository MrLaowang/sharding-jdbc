package com.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Order;
import com.test.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(path="/countTotal/{userId}", method={RequestMethod.POST})
	public Long countTotal(@PathVariable("userId") Integer userId) {
		return orderService.countTotal();
	}
	@RequestMapping(path="/selectGtUserId/{userId}", method={RequestMethod.POST})
	public List<Order> selectGtUserId(@PathVariable("userId") Integer userId) {
		return orderService.selectGtUserId(userId);
	}
	
	@RequestMapping(path="/{userId}", method={RequestMethod.GET})
	public List<Order> getOrderListByUserId(@PathVariable("userId") Integer userId) {
		return orderService.getOrderListByUserId(userId);
	}
	
	@RequestMapping(path="/{userId}/{orderId}", method={RequestMethod.POST})
	public String createOrderRest(@PathVariable("userId") Integer userId, @PathVariable("orderId") Integer orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(userId);
		orderService.createOrder(order);
		return "success";
	}
	
	@RequestMapping(path="/createOrder", method={RequestMethod.POST})
	public String createOrder(Integer userId, Integer orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(userId);
		orderService.createOrder(order);
		return "success";
	}
	/**
	 * 不支持batchInsert
	 * @return
	 */
	@RequestMapping(path="/batchInsert", method={RequestMethod.POST})
	public String batchInsert() {
		List<Order> orders = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			
			Order order = new Order();
			order.setOrderId(i+1);
			order.setUserId(i+2);
			orders.add(order);
		}
		orderService.batchInsert(orders);
		return "success";
	}
	
	/**
	 * 不支持batchInsert
	 * @return
	 */
	@RequestMapping(path="/foreachInsert", method={RequestMethod.POST})
	public String foreachInsert() {
		List<Order> orders = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			
			Order order = new Order();
			order.setOrderId(i+1);
			order.setUserId(i+2);
			orders.add(order);
		}
		orderService.foreachInsert(orders);
		return "success";
	}

}
