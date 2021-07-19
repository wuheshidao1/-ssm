package com.itheima.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

public interface SysLogDao {

    @Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method) values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    @SelectKey(keyProperty = "id", resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "id", useGeneratedKeys = true,keyColumn = "id")
    void save(SysLog sysLog);
}
