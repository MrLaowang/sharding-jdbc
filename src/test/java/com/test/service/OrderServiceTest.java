package com.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.test.OrderApplication;
import com.test.mapper.OrderMapper;
import com.test.model.Order;
import com.test.service.OrderService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(OrderApplication.class)
@Transactional
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;
    @Test
    public void getOrderListByUserId() throws Exception {
    	List<Order>  orderList=orderService.getOrderListByUserId(1);
    	orderList.forEach(e ->System.out.println(e.getUserId()));
    	
    }
    
    
    @Test
    @Rollback(false)
    public void createOrder() throws Exception {
    	Order o1=new Order();
    	o1.setOrderId(1);
    	o1.setUserId(1);
    	orderService.createOrder(o1);
    	Order o2=new Order();
    	o2.setOrderId(2);
    	o2.setUserId(2);
    	orderService.createOrder(o2);
    }

    @Test
    public void countTotal(){
    	Long i = orderMapper.countTotal();
    	System.out.println(i);
    }
    
    @Test
    @Rollback(false)
    public void queryAll(){
    	List<Order> list = orderMapper.queryAll();
    	list.forEach(e ->System.out.println(e));
    }
    
    @Test
    public void selectGtUserId(){
    	List<Order> list = orderMapper.selectGtUserId(1);
    	list.forEach(e ->System.out.println(e));
    }
    
    
}
