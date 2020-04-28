package com.cody.job;

import org.quartz.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: ChickenJob
 *
 * @author WQL
 * @Description: 实现序列化接口 防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 * Job的实例要到该执行它们的时候才去实例化，每次job被执行，一个新的job实例会被创建
 * 期中暗含的意思是你的job不必担心线程安全性，因为同一时刻仅有一个线程去执行给定job类的实例，甚至是并发执行同一job也一样
 * @DisallowConcurrentExecution 保证上一个任务执行完后，再去执行下一个任务，这里的任务是同一任务
 * @date: 2020/4/28 23:33
 * @since JDK 1.8
 */
@DisallowConcurrentExecution
public class ChickenJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();

        /**
         * 获取任务中保存的名字方法，动态调用方法
         */
        String methodName = dataMap.getString("jobMethodName");
        try {
            ChickenJob job = new ChickenJob();
            Method method = job.getClass().getMethod(methodName);
            method.invoke(job);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void test1() {
        System.out.println("测试方法1");
    }

    public void test2() {
        System.out.println("测试方法2");
    }

    public void test3() {
        System.out.println("测试方法3");
    }
}
