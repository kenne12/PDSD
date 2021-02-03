/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Facteurdistrict.findAll", query = "SELECT f FROM Facteurdistrict f"),
    @NamedQuery(name = "Facteurdistrict.findByIdfacteurdistrict", query = "SELECT f FROM Facteurdistrict f WHERE f.idfacteurdistrict = :idfacteurdistrict"),
    @NamedQuery(name = "Facteurdistrict.findByObservation", query = "SELECT f FROM Facteurdistrict f WHERE f.observation = :observation")})
public class Facteurdistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idfacteurdistrict;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idfacteur", referencedColumnName = "idfacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facteur idfacteur;

    public Facteurdistrict() {
    }

    public Facteurdistrict(Long idfacteurdistrict) {
        this.idfacteurdistrict = idfacteurdistrict;
    }

    public Long getIdfacteurdistrict() {
        return idfacteurdistrict;
    }

    public void setIdfacteurdistrict(Long idfacteurdistrict) {
        this.idfacteurdistrict = idfacteurdistrict;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Facteur getIdfacteur() {
        return idfacteur;
    }

    public void setIdfacteur(Facteur idfacteur) {
        this.idfacteur = idfacteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacteurdistrict != null ? idfacteurdistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facteurdistrict)) {
            return false;
        }
        Facteurdistrict other = (Facteurdistrict) object;
        if ((this.idfacteurdistrict == null && other.idfacteurdistrict != null) || (this.idfacteurdistrict != null && !this.idfacteurdistrict.equals(other.idfacteurdistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Facteurdistrict[ idfacteurdistrict=" + idfacteurdistrict + " ]";
    }
    
}
