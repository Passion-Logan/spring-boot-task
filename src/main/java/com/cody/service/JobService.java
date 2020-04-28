package com.cody.service;

import com.cody.entity.QuartzEntity;
import com.cody.entity.Result;
import org.quartz.SchedulerException;

/**
 * ClassName: IJobService
 *
 * @author WQL
 * @Description:
 * @date: 2020/4/28 23:46
 * @since JDK 1.8
 */
public interface JobService {

    Result listQuartzEntity(QuartzEntity quartz, Integer pageNo, Integer pageSize) throws SchedulerException;

    Long listQuartzEntity(QuartzEntity quartz);

    void save(QuartzEntity quartz) throws Exception;

}
