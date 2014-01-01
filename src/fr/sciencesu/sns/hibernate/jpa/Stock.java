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
@Table(name="stocks")
public class Stock implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stocks_id")
    private Integer id;
    
    @Column(name = "stocks_nom")
    private String nom;
    
    @Column(name = "stocks_superficie")
    private Long superficie;
    
    @OneToMany(mappedBy="produits_stock")
    Set<Produit> produits;
    
    @OneToOne(mappedBy = "stock")
    Association association;
    
    public Stock() {
    }

    public Stock(String nom, Long superficie) {
        this.nom = nom;
        this.superficie = superficie;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", nom=" + nom + ", superficie=" + superficie + ", produits=" + produits  + '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Long superficie) {
        this.superficie = superficie;
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
