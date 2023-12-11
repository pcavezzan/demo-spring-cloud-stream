# Demo of spring cloud stream

This repo shows how we can use spring cloud stream to process (consume, process and produce) messages from a messaging system (ex: kafka).

## Prerequisites

* [JAVA 21](https://adoptium.net/temurin/releases/)
* Docker

## How to run

With spring boot docker support, you can simply run the java application with the following command:

```bash
git clone https://github.com/pcavezzan/demo-spring-cloud-stream.git
cd demo-spring-cloud-stream
./mvnw spring-boot:run
```


## Resources

* [Spring Cloud Stream Project](https://cloud.spring.io/spring-cloud-stream/)
* [Spring Cloud Stream Reference Guide](https://docs.spring.io/spring-cloud-stream/reference/)