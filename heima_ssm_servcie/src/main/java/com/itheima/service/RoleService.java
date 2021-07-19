package com.itheima.service;


import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findRoleByRoleId(String roleId);

    List<Permission> findOtherPermissionById(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
