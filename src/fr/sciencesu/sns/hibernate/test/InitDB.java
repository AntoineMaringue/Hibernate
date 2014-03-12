/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.test;

import fr.sciencesu.sns.hibernate.builder.AbstractBDD;
import fr.sciencesu.sns.hibernate.builder.IProduit;
import fr.sciencesu.sns.hibernate.jpa.Association;
import fr.sciencesu.sns.hibernate.jpa.Produit;
import fr.sciencesu.sns.hibernate.jpa.Stock;
import static fr.sciencesu.sns.hibernate.test.BDD.getStock;
import static fr.sciencesu.sns.hibernate.test.BDD.print;
import java.net.ConnectException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author antoi_000
 */
public class InitDB extends AbstractBDD implements IProduit<Produit> {

    @Override
    public void Create(Produit element) {
        try {
            connection();
            Transaction tx = null;
            try {
                // Enregistrements
                tx = session.beginTransaction();
                session.save(element);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.err.println(e.getMessage());
                System.exit(-1);
            } finally {
                deconnection();
            }
        } catch (ConnectException | org.hibernate.exception.JDBCConnectionException ex) {
            System.err.println("Impossible de se connecter à la base de données\n"
                    + "Merci de vérifier que vous avez lancé votre WAMP ! ");
            System.exit(-1);
        }
    }

    @Override
    public void Read(String table, String field, String value) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from " + table + " where "
                + field + "= :myTitle");
        q.setString("myTitle", value);
        Produit e = (Produit) q.uniqueResult();

        // Affichage de l'objet récupéré
        System.out.println(e.toString());
    }

    @Override
    public String Read(String table) {

        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("select nom from " + table);
        String s = "";
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            s += (String) it.next() + "\n";

        }
        return s;
    }

    @Override
    public void Update(Produit element, int id) {
        // Récupération de l'Event d'après son titre
        String query = "FROM Produit WHERE nom = '" + element.getNom() + "'";
        Query q = session.createQuery(query);//"FROM " + table + " WHERE " + field + " = " + value + " AND " + "produits_nom = "+"'"+nameProduct+"'"
        Produit e = null;
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            e = (Produit) it.next();
        }
        Stock s = (Stock) session.createQuery("FROM Stock WHERE id = '" + id + "'").uniqueResult();
        e.setProduits_stock(s);

        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(s);
        session.saveOrUpdate(e);
        //s.save(a);
        tx.commit();
    }

    @Override
    public void Delete(Produit element) {
        // Récupération de l'Event d'après son titre
        Query q = session.createQuery("from Produit where nom = :myTitle");
        q.setString("myTitle", element.getNom());
        Stock e = (Stock) q.uniqueResult();

        // supression object
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
    }
}
