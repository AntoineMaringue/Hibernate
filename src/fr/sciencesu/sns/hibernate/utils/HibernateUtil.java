/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.utils;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author antoi_000
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession() throws HibernateException
    {
        return sessionFactory.openSession();
    }
}
