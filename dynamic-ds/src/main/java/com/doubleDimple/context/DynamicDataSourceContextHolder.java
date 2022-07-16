package com.doubleDimple.context;

import com.doubleDimple.constants.DataSourceConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * @author doubleDimple
 * @date 2022:07:16日 20:30
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    /**
     * 动态数据源名称上下文
     */
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_KEY_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param key
     */
    public static void setContextKey(String key){
        log.info("change dataSource:[{}] success",key);
        DATASOURCE_CONTEXT_KEY_HOLDER.set(key);
    }

    /**
     * 获取数据源名称
     * @return
     */
    public static String getContextKey(){
        String key = DATASOURCE_CONTEXT_KEY_HOLDER.get();
        return key == null? DataSourceConstants.DS_KEY_MASTER:key;
    }

    /**
     * 删除当前数据源名称
     */
    public static void removeContextKey(){
        DATASOURCE_CONTEXT_KEY_HOLDER.remove();
    }
}
