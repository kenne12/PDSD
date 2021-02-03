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
    @NamedQuery(name = "Analysefinanciere.findAll", query = "SELECT a FROM Analysefinanciere a"),
    @NamedQuery(name = "Analysefinanciere.findByIdanalysefinanciere", query = "SELECT a FROM Analysefinanciere a WHERE a.idanalysefinanciere = :idanalysefinanciere"),
    @NamedQuery(name = "Analysefinanciere.findByRecettes", query = "SELECT a FROM Analysefinanciere a WHERE a.recettes = :recettes"),
    @NamedQuery(name = "Analysefinanciere.findByDepenses", query = "SELECT a FROM Analysefinanciere a WHERE a.depenses = :depenses")})
public class Analysefinanciere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idanalysefinanciere;
    @Size(max = 254)
    private String recettes;
    @Size(max = 254)
    private String depenses;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;

    public Analysefinanciere() {
    }

    public Analysefinanciere(Integer idanalysefinanciere) {
        this.idanalysefinanciere = idanalysefinanciere;
    }

    public Integer getIdanalysefinanciere() {
        return idanalysefinanciere;
    }

    public void setIdanalysefinanciere(Integer idanalysefinanciere) {
        this.idanalysefinanciere = idanalysefinanciere;
    }

    public String getRecettes() {
        return recettes;
    }

    public void setRecettes(String recettes) {
        this.recettes = recettes;
    }

    public String getDepenses() {
        return depenses;
    }

    public void setDepenses(String depenses) {
        this.depenses = depenses;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanalysefinanciere != null ? idanalysefinanciere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analysefinanciere)) {
            return false;
        }
        Analysefinanciere other = (Analysefinanciere) object;
        if ((this.idanalysefinanciere == null && other.idanalysefinanciere != null) || (this.idanalysefinanciere != null && !this.idanalysefinanciere.equals(other.idanalysefinanciere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Analysefinanciere[ idanalysefinanciere=" + idanalysefinanciere + " ]";
    }
    
}
