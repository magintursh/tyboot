package org.typroject.tyboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by magintursh on 2017-05-03.
 */
//@actuator

@SpringBootApplication
public class TybootWebApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TybootWebApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
