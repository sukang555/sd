package com.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sukang on 2019/11/26 13:59
 */
@Component
public class ZKCustor {

    private CuratorFramework client = null;

    final static Logger log = LoggerFactory.getLogger(ZKCustor.class);

    public static final String ZOOKEEPER_SERVER = "192.168.1.123:2181";

    public void init() {
        if (client != null) {
            return;
        }

        //创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

        //创建zookeeper客户端
        client = CuratorFrameworkFactory.builder().connectString(ZOOKEEPER_SERVER)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace("admin")
                .build();

        client.start();

    }
}