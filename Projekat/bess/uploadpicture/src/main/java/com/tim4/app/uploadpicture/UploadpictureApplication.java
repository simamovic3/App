package com.tim4.app.uploadpicture;

import com.tim4.app.uploadpicture.controllers.ServiceInstanceRestController;
import com.tim4.app.uploadpicture.models.UploadPicture;
import com.tim4.app.uploadpicture.repositories.UploadPictureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestClientException;

import java.io.IOException;


@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class UploadpictureApplication {


    public static void main(String[] args) throws RestClientException, IOException {

        ApplicationContext ctx = SpringApplication.run(UploadpictureApplication.class, args);
        ServiceInstanceRestController serviceInstanceRestController=ctx.getBean(ServiceInstanceRestController.class);
        System.out.println(serviceInstanceRestController);
        serviceInstanceRestController.getPictures();
    }

    @Bean
    public  ServiceInstanceRestController serviceInstanceRestController()
    {
        return  new ServiceInstanceRestController();
    }
}
