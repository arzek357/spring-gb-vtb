package com.vtb.zolotarev.homeWork2.services;

import java.util.List;

public class ConsumerService {

    public static void showProductsByConsumer(List<String> splitMessage){
        if (splitMessage.size()!=2){
            System.err.println("Параметра для команды showProductsByConsumer заданы не верно! Используйте");
            return;
        }
    }
    public static void deleteConsumer(){

    }
    public static void buy(){

    }
}
