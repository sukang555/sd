package com.mapper;

import com.common.entity.ScheduleJobEntity;
import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import org.apache.ibatis.annotations.Param;

/**
 * @author sukang
 */
public interface ScheduleJobMapper {

    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleJobEntity record);

    int insertSelective(ScheduleJobEntity record);
    @DynamicRouteDataSource(DataSourceNames.FIRST)
    ScheduleJobEntity selectByPrimaryKey(@Param("jobId") Long jobId);

    int updateByPrimaryKeySelective(ScheduleJobEntity record);

    int updateByPrimaryKey(ScheduleJobEntity record);
}
