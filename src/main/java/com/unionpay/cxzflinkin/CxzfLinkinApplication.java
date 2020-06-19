package com.unionpay.cxzflinkin;

import com.unionpay.cxzflinkin.bootstrap.SpringContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 13563
 */
@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan("com.unionpay.cxzflinkin.filter")
public class CxzfLinkinApplication {

    public static void main(String[] args) {

            ConfigurableApplicationContext context = SpringApplication.run(CxzfLinkinApplication.class, args);
            SpringContext.init(context);

    }

}
