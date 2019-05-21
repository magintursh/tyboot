package org.typroject.tyboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.typroject.tyboot.component.activemq.JmsReceiver;
import org.typroject.tyboot.component.opendata.storage.Storage;

@Configuration
public class Config {



    //七牛配置
    private String qiniuConfigAccessKey;
    private String qiniuConfigSecretKey;


    @Bean
    public JmsReceiver jmsReceiver ()
    {
        return new JmsReceiver();
    }

    @Bean
    public Storage storage()
    {
        return new Storage(qiniuConfigAccessKey,qiniuConfigSecretKey);
    }




}
