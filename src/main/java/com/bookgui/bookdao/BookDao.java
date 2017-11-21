/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookgui.bookdao;

import com.bookgui.entity.Book;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author davtsin
 */
public class BookDao {

    private static EntityManagerFactory emf
        = Persistence.createEntityManagerFactory("bookGuiPU");
    private EntityManager em;
    private EntityTransaction tx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================
    @PostConstruct
    public void initEntityManager() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @PreDestroy
    public void closeEntityManager() {
        if (em != null) {
            em.close();
        }
    }

    public void createBook(Book book) {
        // Persists the book to the database
//        try {
            tx.begin();
            em.persist(book);
            tx.commit();
//        } catch (ConstraintViolationException cve) {
//            System.out.println(cve.getConstraintViolations());
//            tx.rollback();
//            throw cve;
//        }
    }

    public List<Book> showAllBooks() {
        List<Book> books
            = em.createNamedQuery("findAllBooks", Book.class).getResultList();
        return books;
    }

    public void deleteById(Long id) {
        Book book = em.find(Book.class, id);
        System.out.println("Delete by Id:" + book);
        if (book != null) {
            tx.begin();
            em.remove(book);
            tx.commit();
        }
    }

    public void update(Book book) {
        createBook(book);
    }

    public Book searchById(Long id) {
        return em.find(Book.class, id);
    }
}
