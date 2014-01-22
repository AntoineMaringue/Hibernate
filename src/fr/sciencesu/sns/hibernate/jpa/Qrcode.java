/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.sciencesu.sns.hibernate.jpa;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "Qrcode")
public class Qrcode implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="qrcode_id")
    private int qrcode_id;
    
    @Column(name="qrcode")
    private String qrcode;

    public Qrcode() {
    }

    public Qrcode(int qrcode_id, String qrcode) {
        this.qrcode_id = qrcode_id;
        this.qrcode = qrcode;
    }

    public int getQrcode_id() {
        return qrcode_id;
    }

    public void setQrcode_id(int qrcode_id) {
        this.qrcode_id = qrcode_id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    
    
}
