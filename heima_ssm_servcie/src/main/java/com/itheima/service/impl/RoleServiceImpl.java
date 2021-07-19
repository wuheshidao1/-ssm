package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.service.RoleService;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    public RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findRoleByRoleId(String roleId) {
        return roleDao.findRoleByRoleId(roleId);
    }

    @Override
    public List<Permission> findOtherPermissionById(String roleId) {
        return roleDao.findOtherPermissionById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }


}
