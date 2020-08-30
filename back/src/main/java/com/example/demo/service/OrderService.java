package com.example.demo.service;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderAndOrderDetail;
import com.example.demo.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  service
 *
 * @author duanww
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public void updateOrder(String order_id) {
        orderDao.updateOrder(order_id);
    }

    public Map getOrderDetailById(String order_id) {
        Order order = orderDao.getOrderById(order_id);
        List<OrderDetail> orderDetails = orderDao.getOrderDetailByOrderId(order_id);
        Map result = new HashMap();
        result.put("order", order);
        result.put("orderDetails", orderDetails);
        return result;
    }

    public Map listPageOrder(Order order) {
        List<Order> orders = orderDao.listPageOrder(order);
        int count = orderDao.getOrderCount(order);
        Map result = new HashMap();
        result.put("orders", orders);
        result.put("count", count);
        return result;
    }

    public void addOrderAndOrderDetail(OrderAndOrderDetail addOrderAndOrderDetail) {
        Order order = addOrderAndOrderDetail.getOrder();
        order.setOrder_time(paseString(order.getOrder_time()));//时间格式转换
        orderDao.addOrder(order);
        int order_id = orderDao.selectMaxId();
        List<OrderDetail> orderDetails = addOrderAndOrderDetail.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder_id(order_id);
        }
        orderDao.addOrderDetailList(orderDetails);
    }

    public String paseString(String UTCString) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = null;
        try {
            Date date = sdf1.parse(UTCString);//拿到Date对象
            str = sdf2.format(date);//输出格式：2017-01-22 09:28:33
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
