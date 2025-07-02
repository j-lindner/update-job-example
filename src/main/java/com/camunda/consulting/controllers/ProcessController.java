package com.camunda.consulting.controllers;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ZeebeClient zeebe;

    @PostMapping("/start")
    public void startProcessInstance(
            @RequestBody Map<String, Object> processVariables) throws Exception {

        zeebe
                .newCreateInstanceCommand()
                .bpmnProcessId("MyProcess")
                .latestVersion()
                .variables(processVariables)
                .send();
    }
}
