package com.doubleDimple.aop;

import com.doubleDimple.context.DynamicDataSourceContextHolder;
import com.doubleDimple.annoation.DynamicDS;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author doubleDimple
 * @date 2022:07:16日 20:31
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {
    @Pointcut("@within(com.doubleDimple.annoation.DynamicDS)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();
        DynamicDataSourceContextHolder.setContextKey(dsKey);
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

    /**
     * 根据类或方法获取数据源注解
     *
     * @param joinPoint
     * @return
     */
    private DynamicDS getDSAnnotation(ProceedingJoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        DynamicDS dynamicDsAnnotation = targetClass.getAnnotation(DynamicDS.class);
        // 先判断类的注解，再判断方法注解
        if (Objects.nonNull(dynamicDsAnnotation)) {
            return dynamicDsAnnotation;
        } else {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(DynamicDS.class);
        }
    }
}