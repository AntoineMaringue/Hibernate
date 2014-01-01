/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.test;

import fr.sciencesu.sns.hibernate.jpa.Association;
import fr.sciencesu.sns.hibernate.jpa.Produit;
import fr.sciencesu.sns.hibernate.jpa.Stock;
import fr.sciencesu.sns.hibernate.jpa.TypeAssociation;
import static fr.sciencesu.sns.hibernate.test.BDD.print;
import fr.sciencesu.sns.hibernate.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
        connection();
        
        CreateProduit("name", 12.0, null);
        
        testCreateAssociation();
        
        
        UpdateProduit("produits" ,"name" ,"produits_stock_stocks_id", ReadAssociationWithStock("associations", "Raison sociale"));
        deconnection();
    }
    
    public static String ReadAssociationWithStock(String table, String nameAssociation) {
        // Récupération de l'Event d'après son titre
        Query q = session.createSQLQuery("SELECT stock_stocks_id FROM " + table + " WHERE associations_rs = '"+nameAssociation +"'" );
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
   
   public static void UpdateProduit(String table, String nameProduct , String field, String value) {
        // Récupération de l'Event d'après son titre
       
       String query = "FROM Produit WHERE nom = '" + nameProduct +"'" ;
        Query q = session.createQuery(query);//"FROM " + table + " WHERE " + field + " = " + value + " AND " + "produits_nom = "+"'"+nameProduct+"'"
        Produit e = (Produit) q.uniqueResult();
        Stock s = getStock("nom",value);
        e.setProduits_stock(s);
        
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(s);
        session.saveOrUpdate(e);
        //s.save(a);
        tx.commit();

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
      TypeAssociation ta = new TypeAssociation("Type association");
        Stock s = new Stock("stock association 1", new Long(2000));
        
      
      Set<TypeAssociation> sta = new HashSet<>();
      
      sta.add(ta);
      
        // Association entre Event et Address
      a.setTypes(sta);
 
      a.setStock(s);
      // Enregistrements
      Transaction tx = session.beginTransaction();
      
      session.save(s);
      session.save(ta);
      session.save(a);
      
      session.flush();
      
      tx.commit();
      
      //Affichage de la table
      printAssociation();
      printTypeAssociation();
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
    
    public static void printTypeAssociation()
    {
        System.out.println("[Type Association]");
        
        String hql = "from TypeAssociation";
        Query q = session.createQuery(hql);
        
        ArrayList<TypeAssociation> dataTable = (ArrayList) q.list();
        
        for (TypeAssociation tassoc : dataTable) 
        {
            System.out.println(tassoc.toString());
        }
    }

    public static Stock getStock(String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("FROM Stock WHERE id = '"+value+"'");
        Stock e = (Stock) q.uniqueResult();
        return e;
    }
    
}
