package com.listener;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.component.JobTask;

/**
 * @author sukang
 */
@Configuration
public class SchedulerStartListener implements ApplicationListener<ContextRefreshedEvent> {
	
	
	@Resource
	private JobTask jobTask;
	
	
	@Override
	public void onApplicationEvent(@Nullable ContextRefreshedEvent event) {
		jobTask.start();
	}
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean();
    }

}
