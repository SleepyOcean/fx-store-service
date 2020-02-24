package com.sleepy.goods.aop;

import com.sleepy.goods.dto.CommonDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

/**
 * controller方法拦截器
 *
 * @author Captain
 * @create 2019-05-21 14:46
 **/
@Aspect
@Component
@Slf4j
public class ControllerInterceptor {

    @Pointcut("execution(public com.sleepy.goods.dto.CommonDTO* com.sleepy.goods.controller.*.*(..))")
    public void controllerAnnotationPointCut() {
    }

    @Before("controllerAnnotationPointCut()")
    public void doBefore(JoinPoint joinPoint) {
    }

    /**
     * 拦截Controller中带BindingResult的方法
     *
     * @param point
     * @param bindingResult
     * @return
     */
    @Around("controllerAnnotationPointCut() &&args(..,bindingResult)")
    public CommonDTO<Object> around(ProceedingJoinPoint point, BindingResult bindingResult) {
        CommonDTO<Object> result = new CommonDTO<>();
        if (bindingResult.hasErrors()) {
            result.setStatus(503);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            log.error("http请求参数不规范：" + bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return around(point);
    }

    /**
     * 拦截Controller中常规的方法
     *
     * @param point
     * @return
     */
    @Around("controllerAnnotationPointCut() &&args(..)")
    public CommonDTO<Object> around(ProceedingJoinPoint point) {
        CommonDTO<Object> result = new CommonDTO<>();
        try {
            Long startTime = System.currentTimeMillis();
            result = (CommonDTO<Object>) point.proceed();
            result.setStatus(200);
            result.setTimeout((double) (System.currentTimeMillis() - startTime) / 1000);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            result.setStatus(200);
            result.setMessage("查询结果为空：" + e.getMessage());
            log.warn("数据库请求结果为空：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(503);
            result.setMessage(e.getMessage());
            log.error("serviceImpl异常：" + e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            result.setStatus(503);
            result.setMessage(throwable.getMessage());
            log.error("Controller拦截器异常：" + throwable.getMessage());
        }
        return result;
    }

    @After("controllerAnnotationPointCut()")
    public void doAfter() {
    }

}