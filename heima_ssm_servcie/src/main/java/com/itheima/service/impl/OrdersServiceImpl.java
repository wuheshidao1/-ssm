package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.service.OrdersService;
import com.itheima.ssm.domain.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer page,Integer size) {
        //分页
        PageHelper.startPage(page,size);
        List<Orders> orders = ordersDao.findAll();
        return orders;
    }

    @Override
    public Orders findById(String id) {
        Orders order = ordersDao.findById(id);
        return order;
    }
}
