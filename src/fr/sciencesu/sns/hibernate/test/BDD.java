/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.test;

import fr.sciencesu.sns.hibernate.jpa.Association;
import fr.sciencesu.sns.hibernate.jpa.Membre;
import fr.sciencesu.sns.hibernate.jpa.Produit;
import fr.sciencesu.sns.hibernate.jpa.Stock;
import static fr.sciencesu.sns.hibernate.test.Main.getStock;
import fr.sciencesu.sns.hibernate.utils.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author antoi_000
 */
public class BDD {
    //Déclaration d'une session hibernate

    private static Session session = null;

    public static void connection() {
        //Ouverture d'une session
        session = HibernateUtil.getSession();

    }

    public static void deconnection() {
        //fermeture de la session
        session.close();
    }

    public static void print(String nameTable) {
        System.out.println("[" + nameTable + "]");

        String hql = "from " + nameTable;
        Query q = session.createQuery(hql);

        Set dataTable = (Set) q.list();

        for (Object object : dataTable) {
            System.out.println(object.toString());
        }
    }

    public Produit Create(String table, String... params) {
        return new Produit();//initEntity(table, params);
    }


    public static void CreateProduit(String nom, Double prix, Calendar date) {
        Produit e = new Produit(nom, prix, date);

        // Enregistrements
        Transaction tx = session.beginTransaction();

        session.save(e);
        //s.save(a);
        tx.commit();

    }

    public static boolean Create(String table, Produit e) {

        //Création des objets à rendre persistants
        //e = initEntity(table);

        //Event e = new Event("Titre de l'évènement", "description", true);
        //Address a = new Address("Nom de l'adresse", "24 rue des cerisiers", "75001", "Paris");

        // Association entre Event et Address
        //e.setAddress(a);

        // Enregistrements
        Transaction tx = session.beginTransaction();

        session.save(e);
        //s.save(a);
        tx.commit();

        //Affichage de la table
        //print(table);
        return false;

    }

   
    public static void ReadProduit(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Produit e = (Produit) q.uniqueResult();

        // Affichage de l'objet récupéré
        System.out.println(e.toString());
    }

    
    public static String ReadProduit(String table) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("select nom from " + table);
        String s = "";
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            s += (String) it.next() + "\n";

        }
        return s;
    }

    
    /*public static void UpdateProduit(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Produit e = (Produit) q.uniqueResult();

        // Modifications des attributs de l'objet
        // e.setDescription("Description modifiée");
        //e.setAllDay(false);

        // Prise en compte de la modification
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();

        print(table);
    }*/
    public static void UpdateProduit(String table, String nameProduct , String field, String value) {
        // Récupération de l'Event d'après son titre
       
       String query = "FROM Produit WHERE nom = '" + nameProduct +"'" ;
        Query q = session.createQuery(query);//"FROM " + table + " WHERE " + field + " = " + value + " AND " + "produits_nom = "+"'"+nameProduct+"'"
        Produit e = null;
        for (Iterator it = q.list().iterator(); it.hasNext();) 
        {
            e = (Produit) it.next();
            
        }         
        Stock s = getStock("nom",value);
        e.setProduits_stock(s);
        
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(s);
        session.saveOrUpdate(e);
        //s.save(a);
        tx.commit();
    }

   
    public static void DeleteProduit(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Produit e = (Produit) q.uniqueResult();

        // supression object
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();

        print(table);
    }

    private static Calendar toCalendar(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
            Date date = format.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    
    public static void CreateAssociation(String raisonSociale, String adresse, String codePostal, String ville, String telephone, String email) {
        Association e = new Association(raisonSociale, adresse, codePostal, ville, telephone, email);

        // Enregistrements
        Transaction tx = session.beginTransaction();

        session.save(e);
        //s.save(a);
        tx.commit();
    }

    
    public static void ReadAssociation(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Association e = (Association) q.uniqueResult();

        // Affichage de l'objet récupéré
        System.out.println(e.toString());
    }
    
    public static Association getAssociation(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Association e = (Association) q.uniqueResult();
        return e;
    }

    
    public static String ReadAssociation(String table) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("select raisonSociale from " + table);
        String s = "";
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            s += (String) it.next() + "\n";

        }
        return s;
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

    
    public static void UpdateAssociation(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Association e = (Association) q.uniqueResult();

        // Modifications des attributs de l'objet
        // e.setDescription("Description modifiée");
        //e.setAllDay(false);

        // Prise en compte de la modification
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();

        print(table);
    }

    
    public static void DeleteAssociation(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Association e = (Association) q.uniqueResult();

        // supression object
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();

        print(table);
    }

     
    public static void CreateMembre(String nom,String prenom) {
        Membre e = new Membre(nom,prenom);

        // Enregistrements
        Transaction tx = session.beginTransaction();

        session.save(e);
        //s.save(a);
        tx.commit();
    }

    
    public static void ReadMembre(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Membre e = (Membre) q.uniqueResult();

        // Affichage de l'objet récupéré
        System.out.println(e.toString());
    }
    
    public static Membre getMembre(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Membre e = (Membre) q.uniqueResult();
        return e;
    }

    
    public static String ReadMembre(String table) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("select nom from " + table);
        String s = "";
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            s += (String) it.next() + "\n";

        }
        return s;
    }

    
    public static void UpdateMembre(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Membre e = (Membre) q.uniqueResult();

        // Modifications des attributs de l'objet
        // e.setDescription("Description modifiée");
        //e.setAllDay(false);

        // Prise en compte de la modification
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();

        print(table);
    }

    
    public static void DeleteMembre(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Membre e = (Membre) q.uniqueResult();

        // supression object
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();

        print(table);
    }
    
    public static void CreateStock(String nom,Long superficie) {
        Stock e = new Stock(nom,superficie);

        // Enregistrements
        Transaction tx = session.beginTransaction();

        session.save(e);
        //s.save(a);
        tx.commit();
    }

    
    public static void ReadStock(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Stock e = (Stock) q.uniqueResult();

        // Affichage de l'objet récupéré
        System.out.println(e.toString());
    }
    
     public static Stock getStock(String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("FROM Stock WHERE id = '"+value+"'");
        Stock e = (Stock) q.uniqueResult();
        return e;
    }

    
    public static String ReadStock(String table) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("select nom from " + table);
        String s = "";
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            s += (String) it.next() + "\n";

        }
        return s;
    }
    
    

    
    public static void UpdateStock(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Stock e = (Stock) q.uniqueResult();

        // Modifications des attributs de l'objet
        // e.setDescription("Description modifiée");
        //e.setAllDay(false);

        // Prise en compte de la modification
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();

        print(table);
    }

    
    public static void DeleteStock(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where " + field + "= :myTitle");
        q.setString("myTitle", value);
        Stock e = (Stock) q.uniqueResult();

        // supression object
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();

        print(table);
    }
}
