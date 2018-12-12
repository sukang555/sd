package com.service.impl;

import com.common.entity.ScheduleJobEntity;
import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import com.mapper.ScheduleJobMapper;
import com.service.ScheduleJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sukang  on 2018/12/7.
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Resource
    ScheduleJobMapper scheduleJobMapper;


    @Override
    @DynamicRouteDataSource(DataSourceNames.FIRST)
    public ScheduleJobEntity getDataSourcePrimary(Long id) {
        return scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Override
    @DynamicRouteDataSource(DataSourceNames.SECOND)
    public ScheduleJobEntity getFromDataSource(Long id) {
        return scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Override
    @DynamicRouteDataSource(DataSourceNames.SECOND)
    public Integer updateEntity(ScheduleJobEntity scheduleJobEntity) {
        return scheduleJobMapper.updateByPrimaryKey(scheduleJobEntity);
    }
}
