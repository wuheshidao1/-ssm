package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.service.SysLogService;
import com.itheima.ssm.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
