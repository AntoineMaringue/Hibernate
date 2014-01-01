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
import javax.persistence.Table;

/**
 *
 * @author antoi_000
 */
@javax.persistence.Entity
@Table(name="types_association")
public class TypeAssociation implements Serializable 
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "types_association_id",nullable = false)
    private Integer id;
    
    @Column(name = "types_association_name")
    private String name;
    
    /*@ManyToMany(
             cascade={CascadeType.PERSIST, CascadeType.MERGE}
   )*/
   //@JoinTable(
     //  joinColumns = {
       // @JoinColumn(name = "typeAssociationId")
       //}
   //)
    /*@JoinTable(
       name="Type_Assoc",
       joinColumns=@JoinColumn(name="types_association_id"),
       inverseJoinColumns=@JoinColumn(name="associations_id")
   )*/
    
    
    
    public TypeAssociation()
    {
           }

    public TypeAssociation(String name) 
    {
        this.name = name;
           }
    
    //Getter / Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    
    @Override
    public String toString()
    {
         String id = ("".equals(this.getId()) || this.getId()==null)?("\n"):"[ID = ]"+(this.getId()+"\n");
         String n = ("".equals(this.getName()) || this.getName()==null)?("\n"):"[TYPE = ]"+(this.getName()+"\n");
        return id + n;
    }
    
    
    
    

    
}
