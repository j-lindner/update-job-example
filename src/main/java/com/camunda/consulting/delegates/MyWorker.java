package com.camunda.consulting.delegates;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class MyWorker {


    @JobWorker(type="my-type-1", autoComplete = false, timeout = 20000)
    public void handleType1(ActivatedJob activatedJob, JobClient jobClient) {
        log.info("starting my-type-1 with key: {} and retries: {}", activatedJob.getKey(), activatedJob.getRetries());

    }
}

