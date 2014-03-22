/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.test;

import fr.sciencesu.sns.hibernate.builder.AbstractBDD;
import fr.sciencesu.sns.hibernate.jpa.Association;
import fr.sciencesu.sns.hibernate.jpa.Produit;
import fr.sciencesu.sns.hibernate.jpa.Stock;
import fr.sciencesu.sns.hibernate.utils.HibernateUtil;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author antoi_000
 */
public class Main 
{
    
    private static Session session = null;
    
    public static void main(String[] args)
    {
        try {
            
            connection();
            //testCreateAssociation();
            
            UpdateProduit("TEST" ,
                    "TEST" ,
                    "produits_stock_id_stock", 
                    ReadAssociationWithStock("association", "Raison sociale"));
            deconnection();
            //create(2);
            
            /*connection();
            
            //Création d'un produit
            CreateProduit("name", 12.0, null);
            
            //Création d'une association avec un stock
            
            
            //Mise en place du produit dans le stock adéquate
            UpdateProduit("produits" ,"name" ,"produits_stock_stocks_id", ReadAssociationWithStock("associations", "Raison sociale"));
            deconnection();*/
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static String ReadAssociationWithStock(String table, String nameAssociation) {
        // Récupération de l'Event d'après son titre
        Query q = session.createSQLQuery("SELECT stock_id_stock FROM " + table + " WHERE raison_sociale_assoc = '"+nameAssociation +"'" );
        String s = "";
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            s += it.next() + "\n";

        }
        return s;
    }
    
   public static void CreateProduit(String nom, Double prix, Calendar date) {
        Produit e = new Produit(nom, prix, date);

        // Enregistrements
        Transaction tx = session.beginTransaction();

        session.save(e);
        //s.save(a);
        tx.commit();

    }
   
   public static void UpdateProduit(String table, 
           String nameProduct , 
           String field, 
           String value) {
        // Récupération de l'Event d'après son titre
       
       String query = "FROM Produit WHERE nom_pdt = '" + nameProduct +"'" ;
        Query q = session.createQuery(query);//"FROM " + table + " WHERE " + field + " = " + value + " AND " + "produits_nom = "+"'"+nameProduct+"'"
        Stack<Produit> produits = new Stack<>();
         for (Iterator it = q.list().iterator(); it.hasNext();) {
            Object p = it.next();
             produits.add((Produit)p);
             System.out.println(p.toString());
         }
        //Produit e = (Produit) q.uniqueResult();
        
        for (Produit produit : produits) {
            Transaction tx = session.beginTransaction();
            Stock s = getStock("nom",value);
            produit.setProduits_stock(s);
             session.saveOrUpdate(s);
            session.saveOrUpdate(produit);
            //s.save(a);
            tx.commit();
       }
        

       

        // Modifications des attributs de l'objet
        // e.setDescription("Description modifiée");
        //e.setAllDay(false);

        // Prise en compte de la modification
        

        //print(table);
    }
    
    /**
     * CREATE
     */
    public static void testCreateAssociation()
    {
         //Création des objets à rendre persistants
      Association a = new Association("Raison sociale", "adresse", "71160", "Digoin", "00 00 00 00 00", "");
      Stock s = new Stock("stock association 1");
 
      a.setStock(s);
      // Enregistrements
      Transaction tx = session.beginTransaction();
      
      session.save(s);
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
        Query q = session.createQuery("from Association where associations_id= :id");
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
        session = HibernateUtil.getSessionFactory().openSession();        
        
    }
    
    public static void deconnection()
    {
        //fermeture de la session
        session.close();
    }
    
    public static void printAssociation()
    {
        System.out.println("[Association]");
        
        String hql = "from association";
        Query q = session.createQuery(hql);
        
        ArrayList<Association> dataTable = (ArrayList) q.list();
        
        for (Association assoc : dataTable) 
        {
            System.out.println(assoc.toString());
        }
    }

    public static Stock getStock(String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("FROM Stock WHERE id = '"+value+"'");
        Stock e = (Stock) q.uniqueResult();
        return e;
    }

    private static void create(int qte) throws Exception {
        if(qte > 0)
        {
            for (int i = 0; i < qte; i++) {
                create();
            }
        }
        else
        {
            throw new Exception("La quantité doit être positive ! ");
        }
    }

    private static void create() {    
        InitDB database = new InitDB();
        database.Create(new Produit("TEST", 12.50, AbstractBDD.toCalendar(12, 12, 2010)));
    }
    
}
