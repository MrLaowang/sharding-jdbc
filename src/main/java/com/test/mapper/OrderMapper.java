package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.model.Order;

public interface OrderMapper {
	
	List<Order> getOrderListByUserId(Integer userId);
	
	List<Order> queryAll();
	
	List<Order> selectGtUserId(int userId);
	
	void createOrder(Order order);

	Long countTotal();

	//sharding-jdbc目前不支持批量插入的sql
	void batchInsert(@Param("orders")List<Order> orders);


}
