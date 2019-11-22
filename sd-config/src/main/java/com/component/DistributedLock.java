package com.component;

import java.util.concurrent.TimeUnit;

/**
 * @author sukang
 */
public interface DistributedLock {

	/**
	 * 带超时时间的锁
	 *
	 * @param lockKey 锁的键值
	 * @param timeUnit 锁的时间单位
	 * @param timeout 锁的时间长度
	 * @return  是否添加锁成功
	 */
	Boolean lock(String lockKey, TimeUnit timeUnit, long timeout,String token);


	/**
	 * 释放锁
	 * @param lockKey 锁的键值
	 * @return 是否释放锁成功
	 */
	Boolean unLock(String lockKey,String token);

}
