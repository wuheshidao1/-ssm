package com.itheima.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface   PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId =#{id})")
    List<Permission> findPermissionById(String id);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(id,permissionName,url) values(#{id},#{permissionName},#{url})")
    @SelectKey(keyProperty = "id", resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "id", useGeneratedKeys = true,keyColumn = "id")
    void save(Permission permission);
}
