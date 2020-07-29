package com.vtb.zolotarev.homeWork2;

import com.vtb.zolotarev.homeWork2.repositories.ConsumerRepository;
import com.vtb.zolotarev.homeWork2.repositories.OrderRepository;
import com.vtb.zolotarev.homeWork2.repositories.ProductRepository;
import com.vtb.zolotarev.homeWork2.services.ConsumerService;
import com.vtb.zolotarev.homeWork2.services.OrderService;
import com.vtb.zolotarev.homeWork2.services.ProductService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductsClientsApp {
    private SessionFactory factory;
    private boolean appStatus = true;

    public static void main(String[] args) {
        new ProductsClientsApp();
    }

    public ProductsClientsApp() {
        initRepositories();
        System.out.println("Вы вошли в систему учета. Если вы не знакомы с командами, напишите команду /help");
        startApplication();
    }

    private void startApplication() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (appStatus) {
                System.out.println("Ожидание ввода:");
                List<String> splitMessage = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
                commandController(splitMessage);
            }
        }
    }

    private void commandController(List<String> splitMessage) {
        String command = splitMessage.get(0);
        switch (command) {
            case "/end":
                appStatus = false;
                factory.close();
                break;
            case "/help":
                printCommandsInfo();
                break;
            case "/showProductsByConsumer":
                ConsumerService.showProductsByConsumer(splitMessage);
                break;
            case "/showConsumersByProductTitle":
                ProductService.showConsumersByProduct(splitMessage);
                break;
            case "/deleteConsumer":
                ConsumerService.deleteConsumer(splitMessage);
                break;
            case "/deleteProduct":
                ProductService.deleteProduct(splitMessage);
                break;
            case "/buy":
                OrderService.buy(splitMessage);
                break;
            case "/getDetails":
                OrderService.getDetails(splitMessage);
                break;
            default:
                System.err.println("Данная команда отсутствует! Проверьте правильность ввода.");
                break;
        }
    }

    private void printCommandsInfo() {
        System.out.println(String.format("1) %s\n2) %s\n3) %s\n4) %s\n5) %s\n6) %s",
                "/showProductsByConsumer имя_покупателя - распечатать в консоль товары, которые приобрел покупатель, по имени покупателя",
                "/showConsumersByProductTitle название_товара - распечатать имена покупателей, купивших указанный товар, по названию товара",
                "/deleteConsumer (/deleteProduct) имя_элемента - удалить из базы товары/покупателей по названию/имени",
                "/buy id_покупателя id_товара количество_товара - зафиксировать покупку по id покупателя и товара",
                "/getDetails id_заказа - получить детализацию по заказу c учетом стоимости цены товара на момент покупки",
                "/end - выйти из приложения."));
    }

    private void initRepositories() {
        factory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
        ConsumerRepository.setFactory(factory);
        OrderRepository.setFactory(factory);
        ProductRepository.setFactory(factory);
    }
}
