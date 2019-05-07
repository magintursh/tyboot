package org.typroject.tyboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by magintursh on 2017-05-03.
 */
//@actuator

@MapperScan("org.typroject.*.*.face.orm.dao")
@SpringBootApplication
public class TybootApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TybootApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}