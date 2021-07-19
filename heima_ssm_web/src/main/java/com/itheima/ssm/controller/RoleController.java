package com.itheima.ssm.controller;

import com.itheima.service.RoleService;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {


    @Autowired
    public RoleService roleService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id") String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findRoleByRoleId(roleId);
        List<Permission> permissions = roleService.findOtherPermissionById(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam("roleId") String roleId,@RequestParam("ids")String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
