package com.itheima.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductDao {
    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    @SelectKey(keyProperty = "id", resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "id", useGeneratedKeys = true,keyColumn = "id")
    void save(Product product);

    //根据id查询产品
    @Select("select * from product where id =#{id}")
    Product findById(String id);
}
