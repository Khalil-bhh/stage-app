package com.tekupstage.stageapp;

import com.tekupstage.stageapp.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class StageAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StageAppApplication.class, args);
    }

}
