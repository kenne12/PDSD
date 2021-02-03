/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mois.findAll", query = "SELECT m FROM Mois m"),
    @NamedQuery(name = "Mois.findByIdMois", query = "SELECT m FROM Mois m WHERE m.idMois = :idMois"),
    @NamedQuery(name = "Mois.findByNom", query = "SELECT m FROM Mois m WHERE m.nom = :nom")})
public class Mois implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mois")
    private Integer idMois;
    @Size(max = 25)
    private String nom;

    public Mois() {
    }

    public Mois(Integer idMois) {
        this.idMois = idMois;
    }

    public Integer getIdMois() {
        return idMois;
    }

    public void setIdMois(Integer idMois) {
        this.idMois = idMois;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMois != null ? idMois.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mois)) {
            return false;
        }
        Mois other = (Mois) object;
        if ((this.idMois == null && other.idMois != null) || (this.idMois != null && !this.idMois.equals(other.idMois))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mois[ idMois=" + idMois + " ]";
    }
    
}
