package com.service.impl;

import com.common.entity.ScheduleJobEntity;
import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import com.mapper.ScheduleJobMapper;
import com.service.ScheduleJobService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author sukang  on 2018/12/7.
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    ScheduleJobMapper scheduleJobMapper;

    @Inject
    @Named("mySqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate;


    @Override
    @DynamicRouteDataSource(DataSourceNames.FIRST)
    public ScheduleJobEntity getDataSourcePrimary(Long id) {

        scheduleJobMapper = sqlSessionTemplate.getMapper(ScheduleJobMapper.class);

        return scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Override
    @DynamicRouteDataSource(DataSourceNames.FIRST)
    public ScheduleJobEntity getFromDataSource(Long id) {
        return scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Override
    @DynamicRouteDataSource(DataSourceNames.SECOND)
    public Integer updateEntity(ScheduleJobEntity scheduleJobEntity) {
        return scheduleJobMapper.updateByPrimaryKey(scheduleJobEntity);
    }
}
