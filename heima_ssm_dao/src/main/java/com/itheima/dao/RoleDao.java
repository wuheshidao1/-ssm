package com.itheima.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",
                    column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionById"))
    })
    List<Role> findRoleById(String id);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    //设置主键自增
    @SelectKey(keyProperty = "id", resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "id", useGeneratedKeys = true,keyColumn = "id")
    void save(Role role);

    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    List<Role> findRolesByUserName(String id);

    @Select("select * from role where id=#{roleId}")
    Role findRoleByRoleId(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissionById(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
