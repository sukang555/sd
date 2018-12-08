package com.service;

import com.common.entity.ScheduleJobEntity;

/**
 * @author sukang on 2018/12/7.
 */
public interface ScheduleJobService {

    public ScheduleJobEntity getDataSourcePrimary(Long id);

    public ScheduleJobEntity getFromDataSource(Long id);

    public Integer updateEntity(ScheduleJobEntity scheduleJobEntity);
}
