package com.vtb.zolotarev.homeWork5.repositories;

import com.vtb.zolotarev.homeWork5.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public Product findProductById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAllProducts() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> allProducts = new ArrayList<>(session.createQuery("SELECT p FROM Product p", Product.class).getResultList());
            session.getTransaction().commit();
            return allProducts;
        }
    }
}
