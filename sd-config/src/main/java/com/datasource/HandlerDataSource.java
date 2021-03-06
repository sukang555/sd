package com.datasource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author sukang
 */
public class HandlerDataSource extends AbstractRoutingDataSource{

    private final static ThreadLocal<String> DATASOURCE_THREAD_LOCAL = new ThreadLocal<>();

    public HandlerDataSource(DataSource defaultTargetDataSource,
                             Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }


    public static Object getDataSource() {
        String dataSourceKey = DATASOURCE_THREAD_LOCAL.get();
        Assert.isTrue(StringUtils.isNotBlank(dataSourceKey),"未找到数据源");
        return dataSourceKey;
    }

    public static void setDataSource(String dataSourceKey){
        DATASOURCE_THREAD_LOCAL.set(dataSourceKey);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void clearDataSource() {
        DATASOURCE_THREAD_LOCAL.remove();
    }
}
