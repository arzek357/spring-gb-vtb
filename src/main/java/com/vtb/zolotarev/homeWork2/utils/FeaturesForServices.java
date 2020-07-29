package com.vtb.zolotarev.homeWork2.utils;

import java.util.List;

public class FeaturesForServices {

    public static boolean isArgumentNumberCorrect(List<String> splitMessage, int size){
        if (splitMessage.size()!=size){
            System.err.println(String.format("Параметры для команды %s заданы не верно! Используйте команду /help, чтобы посмотреть список передаваемых параметров",splitMessage.get(0)));
            return false;
        }
        return true;
    }
}
