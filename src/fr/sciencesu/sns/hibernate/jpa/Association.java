/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author antoi_000
 */
@javax.persistence.Entity
@Table(name = "association")
public class Association implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_assoc")
    private Integer id_assoc;
    
    @Column(name = "raison_sociale_assoc")
    private String raison_sociale_assoc;
    
    @Column(name = "adresse_assoc")
    private String adresse_assoc;
    
    @Column(name = "cp_assoc")
    private String codePostal;
    
    @Column(name = "ville_assoc")
    private String ville;
    
    @Column(name = "tel_assoc")
    private String telephone;
    
    @Column(name = "email_assoc")
    private String email;
    
    @OneToOne
    private Stock stock;
    
    public Association() {
    }

    public Association(String raisonSociale, String adresse, String codePostal, String ville, String telephone, String email) {
        this.raison_sociale_assoc = raisonSociale;
        this.adresse_assoc = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
    }
        
    //Getter / Setter
    public Integer getId() {
        return id_assoc;
    }

    public void setId(Integer id) {
        this.id_assoc = id;
    }

    public String getRaisonSociale() {
        return raison_sociale_assoc;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raison_sociale_assoc = raisonSociale;
    }

    public String getAdresse() {
        return adresse_assoc;
    }

    public void setAdresse(String adresse) {
        this.adresse_assoc = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    
    
    
    @Override
    public String toString()
    {
        String rs = ("".equals(this.getRaisonSociale()) || this.getRaisonSociale()==null)?(""):"[RAISON SOCIALE = ]"+(this.getRaisonSociale()+"\n");
        String mail = ("".equals(this.getEmail()) || this.getEmail()==null)?(""):"[EMAIL = ]"+(this.getEmail()+"\n");
        String ad = ("".equals(this.getAdresse()) || this.getAdresse()==null)?(""):"[ADRESSE = ]"+(this.getAdresse()+"\n");
        String v = ("".equals(this.getVille()) || this.getVille()==null)?(""):"[VILLE = ]"+(this.getVille()+"\n");
        String cp = ("".equals(this.getCodePostal()) || this.getCodePostal()==null)?(""):"[CODE POSTAL= ]"+(this.getCodePostal()+"\n");
        
        return  rs + mail + ad + v + cp;
    }

    
    
}
