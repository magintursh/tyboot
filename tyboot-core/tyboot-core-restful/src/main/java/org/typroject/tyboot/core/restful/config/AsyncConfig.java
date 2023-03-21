package org.typroject.tyboot.core.restful.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * description: AsyncConfig <br>
 * date: 2023/3/14 17:25 <br>
 * author: wangjinyi <br>
 * version: 1.0 <br>
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setThreadNamePrefix("async-pool-");
        // 这一步是关键，异步Task装饰器
        executor.setTaskDecorator(new MyContextDecorator());
        executor.initialize();
        return executor;
    }
}
