package com.vtb.zolotarev.homeWork2;

import com.vtb.zolotarev.homeWork2.services.ConsumerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductsClientsApp {
    private boolean appStatus = true;

    public static void main(String[] args) {
        new ProductsClientsApp();
    }

    public ProductsClientsApp() {
        System.out.println("Вы вошли в систему учета. Если вы не знакомы с командами, напишите команду /help");
        startApplication();
    }

    private void startApplication() {
        try(Scanner scanner = new Scanner(System.in)) {
            while (appStatus) {
                System.out.println("Ожидание ввода:");
                List<String> splitMessage = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
                commandController(splitMessage);
            }
        }
    }

    private void commandController(List<String> splitMessage) {
        String command = splitMessage.get(0);
        if (command.equals("/end")) {
            appStatus = false;
        } else if (command.equals("/help")) {
            printCommandsInfo();
        } else if (command.equals("/showProductsByConsumer")) {
            ConsumerService.showProductsByConsumer(splitMessage);
        } else if (command.equals("/showConsumersByProductTitle")) {

        } else if (command.equals("/deleteConsumer")) {

        } else if (command.equals("/deleteProduct")) {

        } else if (command.equals("/buy")) {

        } else {
            System.err.println("Данная команда отсутствует! Проверьте правильность ввода.");
        }
    }

    private void printCommandsInfo() {
        System.out.println("/showProductsByConsumer имя_покупателя - распечатать в консоль товары, которые приобрел покупатель, по имени покупателя;\n" +
                "/showConsumersByProductTitle название_товара - распечатать имена покупателей, купивших указанный товар, по названию товара;\n" +
                "/deleteConsumer (/deleteProduct) имя_элемента - предоставить возможность удалять из базы товары/покупателей по названию/имени; \n" +
                "/buy id_покупателя id_товара - реализовать возможность “покупки товара” по id покупателя и товара.\n"+
                "/end - выйти из приложения.\n");
    }
}
