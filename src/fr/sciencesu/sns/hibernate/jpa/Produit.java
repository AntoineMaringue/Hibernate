/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author antoi_000
 */

@javax.persistence.Entity
@Table(name="produits")
public class Produit implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produits_id",nullable = false)
    private Integer id;
    
    @Column(name = "produits_nom")
    private String nom;
    
    @Column(name = "produits_prix")
    private Double prix;
    
    @Column(name = "produits_unite")
    private String unite;
    
    @Column(name = "produits_contenance")
    private Double contenance;
    
    @Column(name = "produits_provenance")
    private String provenance;
    
    @Column(name = "produits_decription")
    private String description;
    
    
    @OneToOne
    private Stock produits_stock;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
       name="Type_Produit",
       joinColumns=@JoinColumn(name="produits_id"),
       inverseJoinColumns=@JoinColumn(name="types_produit_id")
   )    
    private Set<TypeProduit> typesProduits;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "produits_date_lim")
    private Calendar dluo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "produits_date_perem")
    private Calendar ddp;
    
    @Column(name = "produits_img")
    private String img;

    public Produit() 
    {
        typesProduits = new HashSet<>();
    }

    public Produit(String nom, Double prix, String unite, Double contenance, String provenance) {
        this.nom = nom;
        this.prix = prix;
        this.unite = unite;
        this.contenance = contenance;
        this.provenance = provenance;
    }

    public Produit(String nom, Double prix, Calendar ddp) {
        this.nom = nom;
        this.prix = prix;
        this.ddp = ddp;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Double getContenance() {
        return contenance;
    }

    public void setContenance(Double contenance) {
        this.contenance = contenance;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public Set<TypeProduit> getTypesProduits() {
        return typesProduits;
    }

    public void setTypesProduits(Set<TypeProduit> typesProduits) {
        this.typesProduits = typesProduits;
    }

    public Calendar getDluo() {
        return dluo;
    }

    public void setDluo(Calendar dluo) {
        this.dluo = dluo;
    }

    public Calendar getDdp() {
        return ddp;
    }

    public void setDdp(Calendar ddp) {
        this.ddp = ddp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Stock getProduits_stock() {
        return produits_stock;
    }

    public void setProduits_stock(Stock produits_stock) {
        this.produits_stock = produits_stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return id + "," + nom + "," + prix + "," + unite + "," + contenance + "," + provenance + "," + description + "," + produits_stock +  "," + img;//"," + dluo!=null?dluo.toString():"" + "," + ddp!=null?ddp.toString():"" +
    }
    
    

    
    
}
