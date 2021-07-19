package com.itheima.ssm.controller;

import com.itheima.service.PermissionService;
import com.itheima.ssm.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    public PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Permission> permissions = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
