package com.component;



import java.util.*;
import java.util.concurrent.TimeUnit;


import com.common.entity.ScheduleJobEntity;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.common.constant.CommonConstant;

import javax.annotation.ManagedBean;
import javax.inject.Named;

/**
 * @author sukang
 */
@ManagedBean
public class JobTask {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    
    @Autowired
	@Named(value = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
    
    
    
    public void initAndUpdateJob(){
    	
    	new Thread(() ->{
    		while (true) {
    			
    			try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			List<ScheduleJobEntity> jobs = getJobsFromDb(false,null);
    			//删除job
    			jobs.stream().filter(t -> t.getIsDelete() == true)
    				.forEach(t2 -> {deleteJob(String.valueOf(t2.getJobId()));});
    			
    			
    			//暂停job
    			jobs.stream().filter(t -> Objects.equals(CommonConstant.JOB_PAUSE,t.getStatus())
    					&& t.getIsDelete() == false)
    				.forEach(t2 -> {pauseJob(String.valueOf(t2.getJobId()));});
    			
    			//更新和添加
    			
    			jobs.stream().filter(t -> Objects.equals(CommonConstant.JOB_RESUME,t.getStatus())
    					&& t.getIsDelete() == false)
    				.forEach( t2 -> {
    					String name = String.valueOf(t2.getJobId());
    					
    					if (isExitJob(name)) {
    						logger.info("更新id为{}的job任务",name);
							updateJob(t2);
						}else {
							logger.info("添加id为{}的job任务",name);
							addJob(t2);
						}
    				});
    			
    			
			}
    	},"initAndUpdateJob").start();
    }
    
  
    

	public void start() {
		Scheduler scheduler = getScheduler();
		try {
			startDbJobs(scheduler);
			
			TimeUnit.SECONDS.sleep(10);
			initAndUpdateJob();
		} catch (Exception e) {
			logger.error("启动任务异常，异常信息为",e);
		}
	}

	private void startDbJobs(Scheduler scheduler) throws Exception{
		List<ScheduleJobEntity> jobs = getJobsFromDb(true,false);
		logger.info("初始化的job列表为{}",String.valueOf(jobs));
		
		if (jobs != null && !jobs.isEmpty()) {
			
			for (ScheduleJobEntity scheduleJobEntity : jobs) {
				
				if (CommonConstant.JOB_PAUSE.equals(scheduleJobEntity.getStatus())){
					continue;
				}
				
				addJob(scheduleJobEntity);
			}
		}
		scheduler.start();
	}
	
	
	  
    private Boolean isExitJob(String name){
    	Scheduler scheduler = getScheduler();
    	JobKey jobKey = getJobkey(name);
    	try {
			return scheduler.getJobDetail(jobKey) != null;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    	return false;
    }

	/**
	 * 恢复job
	 */
	public void resumerJob(String jobName){
		Scheduler scheduler = getScheduler();
		
		try {
			if (isExitJob(jobName)) {
				scheduler.resumeJob(getJobkey(jobName));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 	暂停job
	 */

	public void pauseJob(String jobName){
		Scheduler scheduler = getScheduler();
		
		try {
			if (isExitJob(jobName)) {
				scheduler.pauseJob(getJobkey(jobName));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	


	/**
	 * 删除job
	 */
	
	public void deleteJob(String jobName){
		Scheduler scheduler = getScheduler();
		
		try {
			if (isExitJob(jobName)) {
				scheduler.deleteJob(getJobkey(jobName));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 	添加job
	 */

	public void addJob(ScheduleJobEntity scheduleJobEntity){
		
		Scheduler scheduler = getScheduler();
		
		try {
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(scheduleJobEntity.getBeanName());
			
			JobDetail jobDetail = JobBuilder.newJob(clazz)
					.withIdentity(String.valueOf(scheduleJobEntity.getJobId()))
					.setJobData(new JobDataMap(getParams(scheduleJobEntity)))
					.build();
			
			CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression());
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(String.valueOf(scheduleJobEntity.getJobId()))
					.withSchedule(cronSchedule).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 	更新job
	 */

	public void updateJob(ScheduleJobEntity scheduleJobEntity){

		Scheduler scheduler = getScheduler();
		String name = String.valueOf(scheduleJobEntity.getJobId());
		
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(
				scheduleJobEntity.getCronExpression());
		
		CronTrigger oldTrigger = getCronTriggerKey(name);
		oldTrigger =  oldTrigger.getTriggerBuilder()
				.withIdentity(name)
				.withSchedule(cronSchedule)
				.build();
		oldTrigger.getJobDataMap().put(CommonConstant.JOB_PARAMS_KEY,
				scheduleJobEntity.getParams());
		
		try {
			scheduler.rescheduleJob(getTriggerKey(name), oldTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 *从scheduler 中获取cron触发器
	 */
	private CronTrigger getCronTriggerKey(String name){
		Scheduler scheduler = getScheduler();
		try {
			return (CronTrigger)scheduler.getTrigger(getTriggerKey(name));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private TriggerKey getTriggerKey(String name){
		return TriggerKey.triggerKey(name);
	}
	private JobKey getJobkey(String name) {
		return JobKey.jobKey(name);
	}

	private Map<?, ?> getParams(ScheduleJobEntity scheduleJobEntity) {
		
		return new HashMap<String,Object>(1){{
			put(CommonConstant.JOB_PARAMS_KEY, scheduleJobEntity.getParams());
		}};
	}
	
	private List<ScheduleJobEntity> getJobsFromDb(Boolean isFilter,Boolean isDelete) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM schedule_job ");
		BeanPropertyRowMapper<ScheduleJobEntity> rowMapper = 
				new BeanPropertyRowMapper<>(ScheduleJobEntity.class);
		
		if (isFilter) {
			sql.append(" WHERE is_delete = ? ");
			List<Object> params = new ArrayList<>();
			params.add(isDelete);
			return primaryJdbcTemplate.query(sql.toString(),params.toArray(), rowMapper);
		}
		return primaryJdbcTemplate.query(sql.toString(), rowMapper);
	}

	private Scheduler getScheduler() {
		return schedulerFactoryBean.getScheduler();
	}
	

}
