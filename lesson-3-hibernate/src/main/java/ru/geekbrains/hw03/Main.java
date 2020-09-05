package ru.geekbrains.hw03;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.hw03.entities.Buyer;
import ru.geekbrains.hw03.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityManagerFactory.createEntityManager();
// ================== init DB =====================
//        em.getTransaction().begin();
//
//        Buyer b1 = new Buyer("Oleg");
//        Buyer b2 = new Buyer("Vlad");
//        Buyer b3 = new Buyer("Tom");
//
//        Product p1 = new Product("Mac", 42000.0);
//        Product p2 = new Product("Phone", 14400.0);
//        Product p3 = new Product("Book", 50.0);
//        Product p4 = new Product("Cheese", 14.0);
//        Product p5 = new Product("Shirt", 51.0);
//        Product p6 = new Product("Pants", 12.12);
//        Product p7 = new Product("Bread", 12.44);
//
//        b1.setProducts(Arrays.asList(p1,p2,p3,p4));
//        b2.setProducts(Arrays.asList(p5,p6));
//        b3.setProducts(Arrays.asList(p7));
//
//        em.persist(b1);
//        em.persist(b2);
//        em.persist(b3);
//
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);
//        em.persist(p4);
//        em.persist(p5);
//        em.persist(p6);
//        em.persist(p7);
//
//
//        em.getTransaction().commit();
//        em.close();
// ============== init DB end ==============

    }
}
