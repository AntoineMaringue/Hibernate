/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.test;

import fr.sciencesu.sns.hibernate.jpa.Association;

import static fr.sciencesu.sns.hibernate.test.Main.connection;
import static fr.sciencesu.sns.hibernate.test.Main.deconnection;
import static fr.sciencesu.sns.hibernate.test.Main.printAssociation;
import static fr.sciencesu.sns.hibernate.test.Main.testCreateAssociation;
import fr.sciencesu.sns.hibernate.utils.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author antoi_000
 */
public class TestAssociation 
{
    
    private static Session session = null;
    
    public static void main(String[] args)
    {
        connection();
        testCreateAssociation();
        //testReadAssociation("1");
        deconnection();
    }
    
    /**
     * CREATE
     */
    public static void testCreateAssociation()
    {
         //Création des objets à rendre persistants
      Association a = new Association("Raison sociale", 
              "adresse", 
              "71160", 
              "Digoin", 
              "00 00 00 00 00", 
              "");
 
      // Enregistrements
      Transaction tx = session.beginTransaction();
      session.save(a);
      
      session.flush();
      
      tx.commit();
      
      //Affichage de la table
      printAssociation();
    }
    
    /**
     * READ
     */
    public static void testReadAssociation(String id)
    {
     // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from Association where association_id= :id");
        q.setString("id", id);
        Association e = (Association) q.uniqueResult();

        // Affichage de l'objet récupéré
        System.out.println(e.toString());   
    }
    
    /**
     * UPDATE
     */
    
    /**
     * DELETE
     */
    
    public static void connection()
    {
        //Ouverture d'une session
        session = HibernateUtil.getSession();        
        
    }
    
    public static void deconnection()
    {
        //fermeture de la session
        session.close();
    }
    
    public static void printAssociation()
    {
        System.out.println("[Association]");
        
        String hql = "from Association";
        Query q = session.createQuery(hql);
        
        ArrayList<Association> dataTable = (ArrayList) q.list();
        
        for (Association assoc : dataTable) 
        {
            System.out.println(assoc.toString());
        }
    }

    
}
