package com.likhith;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/*
 * This class demonstrates standard CRUD (Create, Read, Update, Delete)
 * operations
 * using Hibernate ORM and standard JPA interfaces natively provided by
 * Hibernate 7.
 */
public class Main {
    public static void main(String[] args) {

        /*
         * 1. CONFIGURATION & SESSION FACTORY
         * Loads configuration from hibernate.cfg.xml, maps the annotated Entity
         * classes,
         * and builds a SessionFactory. The SessionFactory is thread-safe and usually
         * created only once during application startup.
         */
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(com.likhith.Alien.class)
                .configure()
                .buildSessionFactory();

        /*
         * 2. OPENING A SESSION
         * A Session provides the main interface for performing database operations.
         * It is designed to be lightweight, short-lived, and non-thread-safe.
         */
        Session session = factory.openSession();

        Transaction transaction = null;

        try {
            /*
             * 3. BEGINNING A TRANSACTION
             * All read/write database operations should occur within the scope of a
             * transaction
             * to ensure ACiD properties are maintained.
             */
            transaction = session.beginTransaction();

            /*
             * ==========================
             * PERFORMING CREATE (Insert)
             * ==========================
             */
            System.out.println("--- PERFORMING CREATE ---");
            Alien a1 = new Alien();
            a1.setAid(106);
            a1.setAname("manthan");
            a1.setTech("cyber sec");

            Alien a2 = new Alien();
            a2.setAid(107);
            a2.setAname("suman");
            a2.setTech("fullstack");

            // Persist newly created transient entities into the Session cache.
            // This translates to INSERT SQL queries upon committing the transaction.
            session.persist(a1);
            session.persist(a2);
            System.out.println("Inserted: " + a1.getAname() + " and " + a2.getAname());

            /*
             * ==========================
             * PERFORMING READ (Fetch)
             * ==========================
             */
            System.out.println("\n--- PERFORMING READ ---");
            // The find() method eagerly fetches the entity from the database immediately.
            Alien fetchedAlien = session.find(Alien.class, 103);
            if (fetchedAlien != null) {
                System.out.println("Fetched Alien by ID 103: " + fetchedAlien);
            }

            // Using Hibernate Query Language (HQL) to fetch multiple records.
            // HQL uses Entity Name (Alien) and Property Names instead of DB tables and
            // columns.
            List<Alien> aliens = session.createQuery("from Alien", Alien.class).getResultList();
            System.out.println("All Aliens in database:");
            for (Alien a : aliens) {
                System.out.println(" -> " + a);
            }

            /*
             * ==========================
             * PERFORMING UPDATE
             * ==========================
             */
            System.out.println("\n--- PERFORMING UPDATE ---");
            Alien alienToUpdate = session.find(Alien.class, 106);
            if (alienToUpdate != null) {
                alienToUpdate.setTech("Advanced Cloud Computing");
                // Hibernate automatically tracks state variations (Dirty Checking)
                // for objects present inside the current active Session.
                // explicitly using merge() makes this robust for detached instances.
                session.merge(alienToUpdate);
                System.out.println("Updated Alien ID 103 to: " + alienToUpdate);
            }

            /*
             * ==========================
             * PERFORMING DELETE
             * ==========================
             */
            System.out.println("\n--- PERFORMING DELETE ---");
            Alien alienToDelete = session.find(Alien.class, 106);
            if (alienToDelete != null) {
                // Marks the entity for deletion from the database.
                // This issues a DELETE SQL query upon transaction commit.
                session.remove(alienToDelete);
                System.out.println("Deleted Alien ID 106");
            }

            /*
             * 4. COMMITTING THE TRANSACTION
             * Ensures all cached SQL statements constructed by Hibernate are actually
             * flushed out and permanently saved in the database.
             */
            transaction.commit();
            System.out.println("\n--- ALL TRANSACTIONS COMMITTED SUCCESSFULLY ---");

        } catch (Exception e) {
            // Rolling back prevents partial database updates if any error occurs above
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            /*
             * 5. RESOURCE CLEANUP
             * Releases JDBC connection pools allocated for the Session and Factory.
             */
            session.close();
            factory.close();
        }
    }
}
