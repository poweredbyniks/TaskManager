package org.niks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(AppContextConfiguration.class);
        SpringApplication.run(App.class, args);

    }
}


