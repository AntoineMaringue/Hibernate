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
@Table(name = "associations")
public class Association implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "associations_id")
    private Integer id;
    
    @Column(name = "associations_rs")
    private String raisonSociale;
    
    @Column(name = "associations_adresse")
    private String adresse;
    
    @Column(name = "associations_cp")
    private String codePostal;
    
    @Column(name = "associations_ville")
    private String ville;
    
    @Column(name = "associations_tel")
    private String telephone;
    
    @Column(name = "associations_mail")
    private String email;
    
    @OneToOne
    private Stock stock;
    
    public Association() {
    }

    public Association(String raisonSociale, String adresse, String codePostal, String ville, String telephone, String email) {
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
    }
        
    //Getter / Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
