package com.itheima.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface memberDao {

    @Select("select * from member where id = #{id}")
    Member findById(String id);
}
