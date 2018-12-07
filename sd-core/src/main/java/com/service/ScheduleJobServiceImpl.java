package com.service;

import com.common.entity.ScheduleJobEntity;
import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import com.mapper.ScheduleJobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sukang  on 2018/12/7.
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService{

    @Resource
    ScheduleJobMapper scheduleJobMapper;



    @Override
    @DynamicRouteDataSource(DataSourceNames.FIRST)
    public ScheduleJobEntity getById(Long id) {
        return scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Override
    @DynamicRouteDataSource(DataSourceNames.SECOND)
    public Integer updateEntity(ScheduleJobEntity scheduleJobEntity) {
        return scheduleJobMapper.updateByPrimaryKey(scheduleJobEntity);
    }
}