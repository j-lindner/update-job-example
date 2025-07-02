# update-job-example

1. Start C8Run
```log
./start.sh

System Version Information
--------------------------
Camunda Details:
Version: 8.7.6
Java Details:
Version: 22.0.2
--------------------------

Starting Elasticsearch 8.13.4...
(Hint: you can find the log output in the 'elasticsearch.log' file in the 'log' folder of your distribution.)
Waiting for Elasticsearch to start. 12 retries left
Elasticsearch has successfully been started.
Waiting for Camunda to start. 24 retries left
Camunda has successfully been started.
-------------------------------------------
Access each component at the following urls with these default credentials:
- username: demo
- password: demo

Operate:                http://localhost:8080/operate
Tasklist:               http://localhost:8080/tasklist

Camunda 8 API:          http://localhost:8080/v2/
Inbound Connectors API: http://localhost:8085/
Zeebe API (gRPC):       http://localhost:26500/

Camunda metrics endpoint:    http://localhost:9600/actuator/prometheus

When using the Desktop Modeler, Authentication may be set to None.

Refer to https://docs.camunda.io/docs/guides/getting-started-java-spring/ for help getting started with Camunda
```

2. Start Application

3. Create Process Instance
```bash
curl -X POST http://localhost:8081/process/start \
  -H "Content-Type: application/json" \
  -d '{"myId":"1"}'
```

4. Check Logs
```log
2025-07-02T16:10:47.039+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:11:08.049+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:11:29.215+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
```
   
5. (Try to) update Retries
```bash
curl -L -X PATCH 'http://localhost:8080/v2/jobs/2251799813697951' \
-H 'Content-Type: application/json' \
-d '{
  "changeset": {
    "retries": 99
  }
}'
```

6. Check Logs (still 3 Retries)
```log
2025-07-02T16:10:47.039+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:11:08.049+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:11:29.215+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:11:49.462+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:12:09.736+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:12:30.659+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:12:51.173+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:13:11.496+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:13:31.586+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
2025-07-02T16:13:52.236+02:00  INFO 5261 --- [pool-2-thread-1] c.camunda.consulting.delegates.MyWorker  : starting my-type-1 with key: 2251799813697951 and retries: 3
```