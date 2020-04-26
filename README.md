# secure your spring app with SSO [![CI](https://github.com/daggerok/spring-cloud-security-oauth2-sso-github/workflows/CI/badge.svg)](https://github.com/daggerok/spring-cloud-security-oauth2-sso-github/actions?query=workflow%3ACI)
Secure you spring-boot apps by using OAuth2 (GitHub) SSO quickly (Servlet API / legacy)

## step: 0

1. build and run:
   ```bash
   ./gradlew -p step-0-no-security clean build bootRun
   ```
1. test:
   ```bash
   curl 0:8080
   http :8080
   ```

## step: 1

1. build and run:
   ```bash
   ./gradlew -p step-1-basic-security clean build bootRun
   ```
1. test:
   ```bash
   curl -u ololo:trololo 0:8080
   http -a ololo:trololo :8080
   ```

## step: 2

1. create `./step-2-oauth2-sso-security/src/main/resources/application-github.properties` file with content like:
   ```properties
   security.oauth2.client.clientId=bd1c0a783ccdd1c9b9e4
   security.oauth2.client.clientSecret=1a9030fbca47a5b2c28e92f19050bb77824b5ad1
   ```
   this information can be fetched from you personal github account. you must create application for GitHub SSO.
1. build and run:
   ```bash
   ./gradlew -p step-2-oauth2-sso-security clean build bootRun
   ```
1. open http://127.0.0.1:8080
1. first time, you will be redirected on GitHub page for login
1. after success auth you will see result
1. next time you will be authenticated automatically

## resources
<!--
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.0.M4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.M4/gradle-plugin/reference/html/#build-image)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.5.RELEASE/spring-framework-reference/languages.html#coroutines)
* [Cloud LoadBalancer](https://cloud.spring.io/spring-cloud-static/spring-cloud-commons/current/reference/html/#spring-cloud-loadbalancer)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Client-side load-balancing with Spring Cloud LoadBalancer](https://spring.io/guides/gs/spring-cloud-loadbalancer/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
-->
* https://cloud.spring.io/spring-cloud-static/spring-cloud-security/2.2.1.RELEASE/reference/html/
* https://www.baeldung.com/spring-security-oauth2-enable-resource-server-vs-enable-oauth2-sso
* https://www.baeldung.com/spring-security-oauth
