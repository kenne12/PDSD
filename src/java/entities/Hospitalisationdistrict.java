/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospitalisationdistrict.findAll", query = "SELECT h FROM Hospitalisationdistrict h"),
    @NamedQuery(name = "Hospitalisationdistrict.findByIdhospitalisationdistrict", query = "SELECT h FROM Hospitalisationdistrict h WHERE h.idhospitalisationdistrict = :idhospitalisationdistrict"),
    @NamedQuery(name = "Hospitalisationdistrict.findByValeur", query = "SELECT h FROM Hospitalisationdistrict h WHERE h.valeur = :valeur")})
public class Hospitalisationdistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idhospitalisationdistrict;
    private BigInteger valeur;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idhospitalisation", referencedColumnName = "idhospitalisation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hospitalisation idhospitalisation;
    @JoinColumn(name = "idrubriquehospitalisation", referencedColumnName = "idrubriquehospitalisation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquehospitalisation idrubriquehospitalisation;

    public Hospitalisationdistrict() {
    }

    public Hospitalisationdistrict(Long idhospitalisationdistrict) {
        this.idhospitalisationdistrict = idhospitalisationdistrict;
    }

    public Long getIdhospitalisationdistrict() {
        return idhospitalisationdistrict;
    }

    public void setIdhospitalisationdistrict(Long idhospitalisationdistrict) {
        this.idhospitalisationdistrict = idhospitalisationdistrict;
    }

    public BigInteger getValeur() {
        return valeur;
    }

    public void setValeur(BigInteger valeur) {
        this.valeur = valeur;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Hospitalisation getIdhospitalisation() {
        return idhospitalisation;
    }

    public void setIdhospitalisation(Hospitalisation idhospitalisation) {
        this.idhospitalisation = idhospitalisation;
    }

    public Rubriquehospitalisation getIdrubriquehospitalisation() {
        return idrubriquehospitalisation;
    }

    public void setIdrubriquehospitalisation(Rubriquehospitalisation idrubriquehospitalisation) {
        this.idrubriquehospitalisation = idrubriquehospitalisation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhospitalisationdistrict != null ? idhospitalisationdistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospitalisationdistrict)) {
            return false;
        }
        Hospitalisationdistrict other = (Hospitalisationdistrict) object;
        if ((this.idhospitalisationdistrict == null && other.idhospitalisationdistrict != null) || (this.idhospitalisationdistrict != null && !this.idhospitalisationdistrict.equals(other.idhospitalisationdistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hospitalisationdistrict[ idhospitalisationdistrict=" + idhospitalisationdistrict + " ]";
    }
    
}
