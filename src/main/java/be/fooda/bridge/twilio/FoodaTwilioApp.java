package be.fooda.bridge.twilio;

import be.fooda.bridge.twilio.config.SwaggerConfig;
import be.fooda.bridge.twilio.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@Import({
        SwaggerConfig.class,
        WebSecurityConfig.class
})
public class FoodaTwilioApp {

    public static void main(String[] args) {
        SpringApplication.run(FoodaTwilioApp.class, args);
    }

}
