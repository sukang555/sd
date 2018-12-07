package com.component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

/**
 * @author sukang
 */
@ManagedBean
public class SdBeforeRunner implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	

    private JdbcTemplate jdbcTemplate;

	@Inject
	public SdBeforeRunner(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("开始加载初始数据");
		
		initTables();

		printBeans();
		
		logger.info("获取到的spring上下文信息为:"+ApplicationUtils.getApplicationContext().toString());
		logger.info("数据加载完毕");
	}

	private void printBeans() {
		ApplicationContext applicationContext = ApplicationUtils.getApplicationContext();

		String[] names = applicationContext.getBeanDefinitionNames();
		Arrays.stream(names).forEach(System.out::println);
	}


	private void initTables() {
		String sql =  "show tables";
		List<Object> list = new ArrayList<>();
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		queryForList.forEach((t) -> {
			list.addAll(t.entrySet()
					.stream()
					.map(Entry::getValue)
					.collect(Collectors.toList())
					);
		});

		logger.info("获取到的表为：{}",list.toString());
	}
	
	
	

}
