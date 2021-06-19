package service;

import dao.OrderDao;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;

@Service
public class OrderServerImpl {
    @Autowired
    public OrderDao orderDao;
    public int deleteOrder(Order order){
        return orderDao.DeleteByOrderId(order);
    }
    public Order findOrderById(int id){
        return orderDao.findByOrderId(id);
    }
}
