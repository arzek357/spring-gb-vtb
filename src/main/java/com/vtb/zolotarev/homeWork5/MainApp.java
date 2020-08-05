package com.vtb.zolotarev.homeWork5;

import com.vtb.zolotarev.homeWork5.configs.AppConfig;
import com.vtb.zolotarev.homeWork5.services.ProductService;
import com.vtb.zolotarev.homeWork5.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService",ProductService.class);
        productService.findAllProducts().forEach(System.out::println);
        UserService userService = context.getBean("userService",UserService.class);
        userService.findAllUsers().forEach(System.out::println);
    }
}
