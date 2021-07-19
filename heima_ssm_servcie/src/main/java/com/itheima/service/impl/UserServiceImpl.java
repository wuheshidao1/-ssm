package com.itheima.service.impl;

import com.itheia.ssm.utils.MD5Utils;
import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import com.itheima.ssm.domain.Role;

import com.itheima.ssm.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo =null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //处理自己的用户对象封装为UserDetails
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),
                userInfo.getStatus()==0?false:true,true,true,true,
                getAuthority(userInfo.getRoles()));
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(MD5Utils.getMD5(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }

    @Override
    public List<Role> findAllRoleById(String userId) {
        return userDao.findAllRoleById(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

}
