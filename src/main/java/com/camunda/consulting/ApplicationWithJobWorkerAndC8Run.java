package com.camunda.consulting;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Deployment(resources = "classpath*:*.*mn")
public class ApplicationWithJobWorkerAndC8Run {

    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationWithJobWorkerAndC8Run.class, args);
    }

}
