package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.service.OrdersService;
import com.itheima.ssm.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //未分页
/*
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll();
        mv.addObject("ordersList",orders);
        mv.setViewName("orders-list");
        return mv;
    }
*/

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name="page",required = true,defaultValue = "1")
                    int page,
            @RequestParam(name="size",required = true,defaultValue = "2")
                    int size){
        ModelAndView mv = new ModelAndView();

        System.out.println("nihao"+page+" "+size);
        //不能直接将orders装入mv中,因为orders中有其他的东西，
        //例如
        List<Orders> orders = ordersService.findAll(page,size);

        System.out.println(orders);
        //PageInfo 就是一个分页的bean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true,defaultValue = "1")String id){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
