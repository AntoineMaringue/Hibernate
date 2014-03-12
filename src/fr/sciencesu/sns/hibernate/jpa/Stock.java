/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author antoi_000
 */
@javax.persistence.Entity
@Table(name="stock")
public class Stock implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_stock")
    private Integer id;
    
    @Column(name = "nom_stock")
    private String nom;
    
    @OneToMany(mappedBy="produits_stock")
    Set<Produit> produits;
    
    @OneToOne(mappedBy = "stock")
    Association association;
    
    public Stock() {
    }

    public Stock(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", nom=" + nom +  ", produits=" + produits  + '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }
    
    
    
}
