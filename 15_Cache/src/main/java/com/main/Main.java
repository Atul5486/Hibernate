package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Cricketer;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ Create Configuration and configure Hibernate
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");      // Load hibernate.cfg.xml
        cfg.addAnnotatedClass(Cricketer.class); // Register entity

        // 2️⃣ Build SessionFactory
        SessionFactory sf = cfg.buildSessionFactory();

        // 3️⃣ Open first session and load Cricketer with id = 1
        Session s1 = sf.openSession();
        Cricketer c1 = s1.get(Cricketer.class, 1); // Load by primary key
        if (c1 != null) {
            System.out.println("Cricketer 1: " + c1.getName() + ", Runs: " + c1.getRuns());
        } else {
            System.out.println("Cricketer with ID 1 not found.");
        }
        s1.close();

        // 4️⃣ Open second session and load the same Cricketer
        Session s2 = sf.openSession();
        Cricketer c2 = s2.get(Cricketer.class, 1);
        if (c2 != null) {
            System.out.println("Cricketer 2: " + c2.getName() + ", Runs: " + c2.getRuns());
        } else {
            System.out.println("Cricketer with ID 1 not found.");
        }
        s2.close();

        // 5️⃣ Close SessionFactory
        sf.close();
    }
}
