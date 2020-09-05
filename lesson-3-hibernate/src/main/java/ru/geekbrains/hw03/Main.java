package ru.geekbrains.hw03;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.hw03.entities.Buyer;
import ru.geekbrains.hw03.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager em;
    private static Scanner scanner;

    public static void main(String[] args) {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        em = entityManagerFactory.createEntityManager();
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
        scanner = new Scanner(System.in);

        while (true) {
            showMenu();

            String reader = scanner.nextLine();

            if (reader.equals("1")) {
                buyersAndTheirProducts();
            }

            if (reader.equals("2")) {
                productAndTheirBuyers();
            }

            if (reader.equals("3")) {
                deleteUser();
            }

            if (reader.equals("4")) {
                deleteProduct();
            }

            if (reader.equals("/end")) {
                break;
            }
        }


        scanner.close();
        em.close();
    }

    private static void deleteProduct() {
        showAllProducts();
        while (true) {
            String reader = scanner.nextLine();
            if (reader.equals("0")) {
                break;
            }

            try {
                Integer.parseInt(reader);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            em.getTransaction().begin();
            em.createQuery("delete from Product p where p.id = :id").setParameter("id", Integer.parseInt(reader)).executeUpdate();
            em.getTransaction().commit();
            showAllProducts();
            break;
        }
    }

    private static void deleteUser() {
        showAllUsers();
        while (true) {
            String reader = scanner.nextLine();
            if (reader.equals("0")) {
                break;
            }

            try {
                Integer.parseInt(reader);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            em.getTransaction().begin();
            em.createQuery("delete from Buyer b where b.id = :id").setParameter("id", Integer.parseInt(reader)).executeUpdate();
            em.getTransaction().commit();
            showAllUsers();
            break;
        }
    }

    private static void productAndTheirBuyers() {
        showAllProducts();
        System.out.println();
        System.out.println("CHOOSE PRODUCT TO SHOW BUYERS:");
        while (true) {
            String reader = scanner.nextLine();
            if (reader.equals("0")) {
                break;
            }

            try {
                Integer.parseInt(reader);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            Product p = em.createQuery("Select p from Product p where p.id = :id", Product.class).setParameter("id", Integer.parseInt(reader)).getSingleResult();
            List<Buyer> buyerList = p.getBuyers();
            System.out.println();
            System.out.println("PRODUCT: " + p + " --> BUYERS:");
            for (Buyer b : buyerList) {
                System.out.println(b);
            }
            System.out.println();
            break;
        }

    }

    private static void showAllProducts() {
        List<Product> productList = em.createQuery("from Product", Product.class).getResultList();
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    private static void buyersAndTheirProducts() {
        showAllUsers();
        System.out.println();
        System.out.println("CHOOSE BUYERS TO SHOW PRODUCTS:");
        while (true) {
            String reader = scanner.nextLine();
            if (reader.equals("0")) {
                break;
            }

            try {
                Integer.parseInt(reader);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            Buyer b = em.createQuery("Select b from Buyer b where b.id = :id", Buyer.class).setParameter("id", Integer.parseInt(reader)).getSingleResult();
            List<Product> productList = b.getProducts();
            System.out.println();
            System.out.println("BUYER: " + b + " --> PRODUCTS:");
            for (Product p : productList) {
                System.out.println(p);
            }
            System.out.println();
            break;
        }
    }

    private static void showAllUsers() {
        List<Buyer> buyers = em.createQuery("from Buyer", Buyer.class).getResultList();
        for (Buyer b : buyers) {
            System.out.println(b);
        }
    }

    private static void showMenu() {
        System.out.println("====================");
        System.out.println("1. Show users\n2. Show products\n3. Delete user\n4. Delete product");
        System.out.println("====================");
    }
}
