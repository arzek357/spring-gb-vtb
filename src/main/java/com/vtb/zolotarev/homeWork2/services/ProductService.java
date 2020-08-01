package com.vtb.zolotarev.homeWork2.services;

import com.vtb.zolotarev.homeWork2.entity.Consumer;
import com.vtb.zolotarev.homeWork2.entity.Product;
import com.vtb.zolotarev.homeWork2.repositories.ProductRepository;
import com.vtb.zolotarev.homeWork2.utils.FeaturesForServices;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductService {
    private static ProductRepository productRepository = new ProductRepository();

    public static Set<Consumer> showConsumersByProduct(List<String> splitMessage) {
        if (!FeaturesForServices.isArgumentNumberCorrect(splitMessage, 2)) {
            return null;
        }
        String productName = splitMessage.get(1);
        Set<Consumer> products = new HashSet<>();
        try {
            products.addAll(productRepository.findProductByName(productName).getConsumerList());
            System.out.println(String.format("Список клиентов, когда-то купивших продукт c именем %s:", productName));
            for (Consumer consumer : products) {
                System.out.println(consumer);
            }
        } catch (NoResultException ex) {
            System.err.println(String.format("Продукт с именем %s не найден в базе!", productName));
        }
        return products;
    }

    public static void deleteProduct(List<String> splitMessage) {
        if (!FeaturesForServices.isArgumentNumberCorrect(splitMessage, 2)) {
            return;
        }
        String productName = splitMessage.get(1);
        try {
            productRepository.deleteProduct(productName);
            System.out.println(String.format("Продукт с именем %s удален из базы.", productName));
        } catch (NoResultException ex) {
            System.err.println(String.format("Продукт с именем %s не найден в базе!", productName));
        }
    }

    public static Product findProductById(long id) {
        return productRepository.findProductById(id);
    }
}
