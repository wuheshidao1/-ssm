package com.itheima.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.aspectj.weaver.ast.Or;

import javax.annotation.Resources;
import java.util.List;


public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "payTypeStr",column = "payTypeStr"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId"
                    ,javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findById"))
    })
    List<Orders> findAll();

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "payTypeStr",column = "payTypeStr"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId"
                    ,javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",
                        javaType = Member.class,one = @One(select = "com.itheima.dao.memberDao.findById")),
            @Result(property = "travellers",column = "id"
                    ,javaType = java.util.List.class
                    ,many = @Many(select = "com.itheima.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(String id);
}
