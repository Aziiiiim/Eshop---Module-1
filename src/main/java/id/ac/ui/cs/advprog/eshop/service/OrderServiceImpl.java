package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;

public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateStatus(String orderId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAllByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
