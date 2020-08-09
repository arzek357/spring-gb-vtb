package com.vtb.zolotarev.homeWork5.configs;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.vtb.zolotarev.homeWork5")
public class AppConfig {
    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure("homeWork5/config/hibernate.cfg.xml").buildSessionFactory();
    }
}
