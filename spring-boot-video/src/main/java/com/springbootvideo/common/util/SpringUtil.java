package com.springbootvideo.common.util;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 15:56
 * @Description:
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取注入对象
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        try {
            return (T) applicationContext.getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
        }
        return null;
    }
}
