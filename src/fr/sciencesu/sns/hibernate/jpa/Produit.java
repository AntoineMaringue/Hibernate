/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="produit")
public class Produit implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pdt",nullable = false)
    private Integer id;
    
    @Column(name = "nom_pdt")
    private String nom;
    
    @Column(name = "prix_pdt")
    private Double prix;
    
    @Column(name = "unite_contenance_pdt")
    private String unite;
    
    @Column(name = "contenance_pdt")
    private Double contenance;
    
    @Column(name = "provenance_pdt")
    private String provenance;
    
    @Column(name = "decription_pdt")
    private String description;
    
    
    @OneToOne
    private Stock produits_stock;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
       name="rassembler",
       joinColumns=@JoinColumn(name="id_pdt"),
       inverseJoinColumns=@JoinColumn(name="id_panier")
   )    
    private Set<Panier> paniers;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
       name="appartenir",
       joinColumns=@JoinColumn(name="id_pdt"),
       inverseJoinColumns=@JoinColumn(name="id_cat")
   )    
    private Set<CategorieProduit> categoriesProduits;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "date_lim_pdt")
    private Calendar  dluo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "date_perem_pdt")
    private Calendar  ddp;
    
    @Column(name = "img_pdt")
    private String img;

    public Produit() 
    {
        categoriesProduits = new HashSet<>();
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

    public Set<CategorieProduit> getTypesProduits() {
        return categoriesProduits;
    }

    public void setTypesProduits(Set<CategorieProduit> typesProduits) {
        this.categoriesProduits = typesProduits;
    }

    public Calendar  getDluo() {
        return dluo;
    }

    public void setDluo(Calendar  dluo) {
        this.dluo = dluo;
    }

    public Calendar  getDdp() {
        return ddp;
    }

    public void setDdp(Calendar  ddp) {
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

    public Set<Panier> getQrcodes() {
        return paniers;
    }

    public void setQrcodes(Set<Panier> qrcodes) {
        this.paniers = qrcodes;
    }
    
    

    @Override
    public String toString() {
        return id!=null?id + "," :""+
                nom!=null?nom + "," :""
                + prix!=null?prix + ",":"" 
                + unite!=null?unite + "," :""
                + contenance!=null?contenance + "," :""
                + provenance!=null?provenance + "," :""
                + description!=null?description + "," :""
                + produits_stock!=null?produits_stock +  ",":""
                + img!=null?img + ",":"" 
                + dluo!=null?dluo.toString()+"," :""+
                ddp!=null?ddp.toString():"" 
                ;
                
    }
    
    

    
    
}
