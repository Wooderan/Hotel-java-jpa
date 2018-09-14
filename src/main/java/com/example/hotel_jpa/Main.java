package com.example.hotel_jpa;

import com.example.hotel_jpa.controllers.SandBoxClientController;
import com.example.hotel_jpa.view.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main{


    public static void main(String[] args) {

        SpringApplication.run(Main.class);

        App app = new App(args);
        SandBoxClientController controller = ApplicationContextHolder.getContext().getBean(SandBoxClientController.class);
        controller.setApp(app);
        controller.makeSomeMagic();


    }
}
