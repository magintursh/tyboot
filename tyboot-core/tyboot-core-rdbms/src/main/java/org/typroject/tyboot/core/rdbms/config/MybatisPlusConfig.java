package org.typroject.tyboot.core.rdbms.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 子杨 on 2017/4/19.
 */
@Configuration
public class MybatisPlusConfig {


    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        paginationInterceptor.setOverflow(false);
        paginationInterceptor.setMaxLimit(20L);
        return  paginationInterceptor;
    }
}
