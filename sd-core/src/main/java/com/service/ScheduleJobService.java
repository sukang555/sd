package com.service;

import com.common.entity.ScheduleJobEntity;

/**
 * @author sukang on 2018/12/7.
 */
public interface ScheduleJobService {

    ScheduleJobEntity getDataSourcePrimary(Long id);

    ScheduleJobEntity getFromDataSource(Long id);

    Integer updateEntity(ScheduleJobEntity scheduleJobEntity);
}
