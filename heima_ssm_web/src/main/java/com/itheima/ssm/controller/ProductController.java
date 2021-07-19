package com.itheima.ssm.controller;

import com.itheima.service.ProductService;
import com.itheima.ssm.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    static {
        System.out.println("dioahdafs");
    }

    @Autowired
    public ProductService productService;

    @RolesAllowed("ADMIN")
    //查询全部产品
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list");
        System.out.println("nihao");
        return mv;

    }

    @RequestMapping("/save.do")
    public String save(Product product){
        System.out.println(product);
        productService.save(product);

        return "redirect:findAll.do";
    }

    @RequestMapping("/test.do")
    public void test(Product product){
        System.out.println(product);
        return;
    }
}
