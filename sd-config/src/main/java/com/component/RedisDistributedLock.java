package com.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * redis分布式🔒
 * @author sukang
 * @date 2018-07-04 19:00:13
 */
@Component
public class RedisDistributedLock implements DistributedLock{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	@Named("localRedisTemplate")
	private RedisTemplate redisTemplate;

	private  final String LOCK_PREFIX = "REDIS_LOCK_";
	/**
	 *
	 *  if (setnx(key) == 1) {
	 *      EX second ：设置键的过期时间为 second 秒
	 *      PX millisecond ：设置键的过期时间为 millisecond 毫秒
	 *  } else{
	 *    return 0;
	 *  }
	 */
	private final String LUA_SCRIPT_LOCK = "return redis.call('SET', KEYS[1], ARGV[1]," +
			" 'NX', 'EX', ARGV[2])";



	private final  String LUA_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] \n" +
			"    then \n" +
			"        return redis.call('del', KEYS[1]) \n" +
			"    else \n" +
			"        return 0 \n" +
			"end";

	private final String LOCK_OK = "OK";

	private static final Long LOCK_RELEASE_OK = 1L;

	@Override
	public Boolean lock(String lockKey, TimeUnit timeUnit, long timeout,String token) {

		try {
			DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
			redisScript.setScriptText(LUA_SCRIPT_LOCK);
			redisScript.setResultType(String.class);

			String key = LOCK_PREFIX.concat(lockKey);

			Object execute = redisTemplate.execute(redisScript,
					redisTemplate.getStringSerializer(),
					redisTemplate.getStringSerializer(),
					Collections.singletonList(key),
					token,String.valueOf(timeUnit.toSeconds(timeout)));
			logger.info("key:{},value:{}添加锁结束{}",key,token,execute);

			return execute != null && Objects.equals(LOCK_OK,execute.toString());
		} catch (Exception e){
			logger.error("获取redis锁异常",e);
		}
		return false;
	}

	@Override
	public Boolean unLock(String lockKey,String token) {
		try {
			DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
			redisScript.setScriptText(LUA_UNLOCK);
			redisScript.setResultType(Long.class);

			String key = LOCK_PREFIX.concat(lockKey);

			Object execute = redisTemplate.execute(redisScript,
					redisTemplate.getStringSerializer(),
					redisTemplate.getStringSerializer(),
					Collections.singletonList(key), token);
			logger.info("key:{},value:{}释放锁结束{}",key,token,execute);

			return execute != null && Objects.equals(LOCK_RELEASE_OK,execute);
		} catch (Exception e) {
			logger.error("key:{},value:{}释放锁异常",lockKey,token,e);
		}
		return false;
	}
}
