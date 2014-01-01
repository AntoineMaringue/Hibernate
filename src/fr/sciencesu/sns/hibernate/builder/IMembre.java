/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.builder;

/**
 *
 * @author antoi_000
 */
public interface IMembre {
    
    public void CreateMembre(String nom, String prenom);
    public void ReadMembre(String table, String field, String value);
    public String ReadMembre(String table);
    public void UpdateMembre(String table, String field, String value);
    public void DeleteMembre(String table, String field, String value);
    
}
