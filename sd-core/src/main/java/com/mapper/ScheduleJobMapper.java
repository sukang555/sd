package com.mapper;

import com.common.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author sukang
 */
public interface ScheduleJobMapper {

    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleJobEntity record);

    int insertSelective(ScheduleJobEntity record);

    ScheduleJobEntity selectByPrimaryKey(@Param("jobId") Long jobId);

    int updateByPrimaryKeySelective(ScheduleJobEntity record);

    int updateByPrimaryKey(ScheduleJobEntity record);
}
