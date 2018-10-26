package com.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.constant.CommonConstant;
import com.common.util.DateTimeUtil;



public class DemoJob2 extends AbstratJob implements Job{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		long start = System.currentTimeMillis();
		doExecute(context);
		logger.info("定时任务{}执行结束,花费时间为{}ms",getClass().getSimpleName(),
				String.valueOf(System.currentTimeMillis() - start));
	}

	@Override
	public void doExecute(JobExecutionContext context) {
		logger.info("{}定时任务开始执行:{}",getClass().getName()
				,DateTimeUtil.currentDate(null));
		
		try {
			logger.info("获取到的参数为{}",context.getMergedJobDataMap()
					.get(CommonConstant.JOB_PARAMS_KEY));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
