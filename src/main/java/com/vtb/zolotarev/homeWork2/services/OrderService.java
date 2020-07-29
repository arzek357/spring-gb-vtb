package com.vtb.zolotarev.homeWork2.services;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import com.vtb.zolotarev.homeWork2.entity.Order;
import com.vtb.zolotarev.homeWork2.repositories.OrderRepository;
import com.vtb.zolotarev.homeWork2.utils.FeaturesForServices;

import javax.persistence.NoResultException;
import java.util.List;

public class OrderService {
    private static OrderRepository orderRepository = new OrderRepository();

    public static void buy(List<String> splitMessage) {
        if (!FeaturesForServices.isArgumentNumberCorrect(splitMessage, 4)) {
            return;
        }
        try {
            orderRepository.save(
                    new Order(Long.parseLong(splitMessage.get(1)),
                            Long.parseLong(splitMessage.get(2)),
                            ProductService.findProductById(Long.parseLong(splitMessage.get(2))).getPrice(),
                            Integer.parseInt(splitMessage.get(3))));
        } catch (NoResultException exception){
            System.err.println("Заказ не сформирован! Проверьте правильность id продукта и клиента!");
        }
    }
    public static void getDetails(List<String> splitMessage){
        if (!FeaturesForServices.isArgumentNumberCorrect(splitMessage, 2)) {
            return;
        }
        long orderId = Long.parseLong(splitMessage.get(1));
        try {
            System.out.println(orderRepository.findOrderById(orderId));
        } catch (NoResultException exception){
            System.err.println(String.format("Заказ с идентификатором %d отсутствует в базе!",orderId));
        }
    }
}
