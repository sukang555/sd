package com.common.constant;

/**
 * @author sukang
 */
public interface CommonConstant {
	
	String JOB_PARAMS_KEY = "params";
	
	Integer JOB_PAUSE = 1;
	
	Integer JOB_RESUME = 0;


	String ENV_DEV = "dev";

	String ENV_PRD = "prd";

    String OK = "ok";

	/** 正常状态码 */
	Integer NORMAL = 1;

	/** 冻结状态码 */
	Integer FREEZED = 2;

	/** 删除状态码 */
	Integer DELETE = 3;
	
	
	int DEFAULT_PAGE_SIZE = 10;
	int DEFAULT_PAGE_NUM = 1;
	
	String PAGE_NUM = "pageNum";
	String PAGE_SIZE = "pageSize";
}
