package com.vtb.zolotarev.homeWork2.repositories;

import com.vtb.zolotarev.homeWork2.entity.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsumerRepository {
    private static SessionFactory factory;

    public void deleteConsumer(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.createQuery("SELECT c FROM Consumer c WHERE c.name LIKE :name").setParameter("name", name).getSingleResult());
            session.getTransaction().commit();
        }
    }

    public Consumer findConsumerByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Consumer consumer = (Consumer) session.createQuery("SELECT c FROM Consumer c WHERE c.name LIKE :name").setParameter("name", name).getSingleResult();
            session.getTransaction().commit();
            return consumer;
        }
    }

    public Consumer findConsumerById(long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Consumer consumer = session.get(Consumer.class,id);
            session.getTransaction().commit();
            return consumer;
        }
    }

    public static void setFactory(SessionFactory factory) {
        ConsumerRepository.factory = factory;
    }
}
