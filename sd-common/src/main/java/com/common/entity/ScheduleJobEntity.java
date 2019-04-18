package com.common.entity;

import com.common.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 * 
 * @author sukang
 * 
 * @date 2018-07-16
 */
@Setter
@Getter
public class ScheduleJobEntity implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1332642967474443090L;

	/**
     * 任务id
     */
    private Long jobId;

    /**
     * spring bean全类名
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 任务状态  0：正常  1：暂停
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;
    
    
    private Boolean isDelete;


    @Override
    public String toString() {
        return BeanUtil.toJsonStr(this);
    }
}