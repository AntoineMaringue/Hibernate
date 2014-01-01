/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.builder;

import fr.sciencesu.sns.hibernate.jpa.Produit;
import static fr.sciencesu.sns.hibernate.test.BDD.print;
import java.util.Calendar;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author antoi_000
 */
public interface IProduit 
{
    
    public void CreateProduit(String nom,Double prix,Calendar date);
    public void ReadProduit(String table, String field, String value);
    public String ReadProduit(String table);
    public void UpdateProduit(String table, String field, String value);
    public void DeleteProduit(String table, String field, String value);
    
}
