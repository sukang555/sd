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
	public static final Integer NORMAL = 1;

	/** 冻结状态码 */
	public static final Integer FREEZED = 2;

	/** 删除状态码 */
	public static final Integer DELETE = 3;
}
