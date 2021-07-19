package com.itheima.ssm.controller;

import com.itheima.service.UserService;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        mv.addObject("userList",userInfos);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        System.out.println();
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam("id") String userId){
        ModelAndView mv = new ModelAndView();
        //根据id查用户
        UserInfo userInfo = userService.findById(userId);
        //根据id查可添加的角色
        List<Role> roles = userService.findAllRoleById(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,
                              @RequestParam(name = "ids",required = true)String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
