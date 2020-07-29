package com.vtb.zolotarev.homeWork2.repositories;

import com.vtb.zolotarev.homeWork2.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductRepository {
    private static SessionFactory factory;

    public void deleteProduct(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name").setParameter("name", name).getSingleResult());
            session.getTransaction().commit();
        }
    }

    public Product findProductByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = (Product) session.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name").setParameter("name", name).getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    public Product findProductById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public static void setFactory(SessionFactory factory) {
        ProductRepository.factory = factory;
    }
}
