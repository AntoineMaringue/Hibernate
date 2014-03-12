/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
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
 * @author antoi_000
 */
@Entity
@Table(name = "membres")
public class Membre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_membre")
    private Integer id_membre;
    
    @Column(name="nom_membre")
    private String nom_membre;
    
    @Column(name="prenom_membre")
    private String prenom_membre;
    
      @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
       name="adhérer",
       joinColumns=@JoinColumn(name="id_membre"),
       inverseJoinColumns=@JoinColumn(name="id_assoc")
   )    
     private Set<Association> associations;
      @Column(name="ville_membre")
      private String ville_membre;
      @Column(name="adresse_membre")
      private String adresse_membre;
      @Column(name="cp_membre")
      private String cp_membre;
      @Column(name="email_membre")
      private String email_membre;
      @Column(name="mdp_membre")
      private String mdp_membre;

    public Membre() {
    }

    public Membre(String nom, String prenom) {
        this.nom_membre = nom;
        this.prenom_membre = prenom;
    }
      
    

    public Integer getId() {
        return id_membre;
    }

    public void setId(Integer id) {
        this.id_membre = id;
    }

    public String getNom() {
        return nom_membre;
    }

    public void setNom(String nom) {
        this.nom_membre = nom;
    }

    public String getPrenom() {
        return prenom_membre;
    }

    public void setPrenom(String prenom) {
        this.prenom_membre = prenom;
    }

    public Set<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(Set<Association> associations) {
        this.associations = associations;
    }

    public String getVille() {
        return ville_membre;
    }

    public void setVille(String ville) {
        this.ville_membre = ville;
    }

    public String getAdresse() {
        return adresse_membre;
    }

    public void setAdresse(String adresse) {
        this.adresse_membre = adresse;
    }

    public String getCode_postal() {
        return cp_membre;
    }

    public void setCode_postal(String code_postal) {
        this.cp_membre = code_postal;
    }

    public String getEmail() {
        return email_membre;
    }

    public void setEmail(String email) {
        this.email_membre = email;
    }

    public String getPassword() {
        return mdp_membre;
    }

    public void setPassword(String password) {
        this.mdp_membre = password;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_membre != null ? id_membre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.id_membre == null && other.id_membre != null) || (this.id_membre != null && !this.id_membre.equals(other.id_membre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.sciencesu.sns.hibernate.jpa.Membre[ id=" + id_membre + " ]";
    }
    
}
