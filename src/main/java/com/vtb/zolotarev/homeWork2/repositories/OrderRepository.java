package com.vtb.zolotarev.homeWork2.repositories;

import com.vtb.zolotarev.homeWork2.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrderRepository {

    private static SessionFactory factory;

    public static void setFactory(SessionFactory factory) {
        OrderRepository.factory = factory;
    }

    public void save(Order order) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        }
    }

    public Order findOrderById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.getTransaction().commit();
            return order;
        }
    }
}
