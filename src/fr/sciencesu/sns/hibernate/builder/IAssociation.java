/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.builder;

/**
 *
 * @author antoi_000
 */
public interface IAssociation 
{
    
    public void CreateAssociation(String raisonSociale, String adresse, String codePostal, String ville, String telephone, String email);
    public void ReadAssociation(String table, String field, String value);
    public String ReadAssociation(String table);
    public void UpdateAssociation(String table, String field, String value);
    public void DeleteAssociation(String table, String field, String value);
    
}
