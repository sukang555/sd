package com.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * @author sukang
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class AbstractJob{

	/**
	 *
	 */
	public abstract void doExecute(JobExecutionContext context);
	
}
