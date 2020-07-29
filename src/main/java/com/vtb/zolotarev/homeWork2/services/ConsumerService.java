package com.vtb.zolotarev.homeWork2.services;

import com.vtb.zolotarev.homeWork2.entity.Consumer;
import com.vtb.zolotarev.homeWork2.entity.Product;
import com.vtb.zolotarev.homeWork2.repositories.ConsumerRepository;
import com.vtb.zolotarev.homeWork2.utils.FeaturesForServices;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsumerService {
    private static ConsumerRepository consumerRepository = new ConsumerRepository();

    public static Set<Product> showProductsByConsumer(List<String> splitMessage) {
        if (!FeaturesForServices.isArgumentNumberCorrect(splitMessage, 2)) {
            return null;
        }
        String consumerName = splitMessage.get(1);
        Set<Product> products = new HashSet<>();
        try {
            products.addAll(consumerRepository.findConsumerByName(consumerName).getProductList());
            System.out.println(String.format("Список продуктов, когда-то купленных клиентом c именем %s:", consumerName));
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (NoResultException ex) {
            System.err.println(String.format("Клиент с именем %s не найден в базе!", consumerName));
        }
        return products;
    }

    public static void deleteConsumer(List<String> splitMessage) {
        if (!FeaturesForServices.isArgumentNumberCorrect(splitMessage, 2)) {
            return;
        }
        String consumerName = splitMessage.get(1);
        try {
            consumerRepository.deleteConsumer(consumerName);
            System.out.println(String.format("Клиент с именем %s удален из базы.", consumerName));
        } catch (NoResultException ex) {
            System.err.println(String.format("Клиент с именем %s не найден в базе!", consumerName));
        }
    }

    public Consumer findConsumerById(long id){
        return consumerRepository.findConsumerById(id);
    }

}
