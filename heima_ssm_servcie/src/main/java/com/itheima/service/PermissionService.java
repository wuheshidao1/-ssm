package com.itheima.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
