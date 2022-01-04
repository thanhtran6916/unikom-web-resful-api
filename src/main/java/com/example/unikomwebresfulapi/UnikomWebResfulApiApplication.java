package com.example.unikomwebresfulapi;

import com.example.unikomwebresfulapi.helper.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class UnikomWebResfulApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnikomWebResfulApiApplication.class, args);
    }

}
