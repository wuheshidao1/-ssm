package com.itheima.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRolesByUserName")) })
    public UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @SelectKey(keyProperty = "id", resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "id", useGeneratedKeys = true,keyColumn = "id")
    @Insert("insert into users(id,username,email,password,phoneNum,status) values(#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id",property = "roles",
                    javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRoleById"))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findAllRoleById(String userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}