/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Antoine
 */
@Entity
@Table(name = "panier")
public class Panier implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_panier")
    private int id;
    
    @Column(name="quantite_panier")
    private int qte;
    
    /*@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
       name="Paniers",
       joinColumns=@JoinColumn(name="id_panier"),
       inverseJoinColumns=@JoinColumn(name="produits_id")
   )
    private Set<Produit> produits;
*/
    public Panier() {
    }

    public Panier(int id, int qte) {
        this.id = id;
        this.qte = qte;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

   /* public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", qte=" + qte + ", produits=" + produits + '}';
    }*/
    
    

}
