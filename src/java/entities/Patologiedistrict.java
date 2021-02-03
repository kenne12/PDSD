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
    @NamedQuery(name = "Patologiedistrict.findAll", query = "SELECT p FROM Patologiedistrict p"),
    @NamedQuery(name = "Patologiedistrict.findByIdpatologiedistrict", query = "SELECT p FROM Patologiedistrict p WHERE p.idpatologiedistrict = :idpatologiedistrict"),
    @NamedQuery(name = "Patologiedistrict.findByValeur", query = "SELECT p FROM Patologiedistrict p WHERE p.valeur = :valeur")})
public class Patologiedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idpatologiedistrict;
    private BigInteger valeur;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idpathologie", referencedColumnName = "idpathologie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pathologie idpathologie;
    @JoinColumn(name = "idrubriquemorbidite", referencedColumnName = "idrubriquemorbidite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquemorbidite idrubriquemorbidite;

    public Patologiedistrict() {
    }

    public Patologiedistrict(Integer idpatologiedistrict) {
        this.idpatologiedistrict = idpatologiedistrict;
    }

    public Integer getIdpatologiedistrict() {
        return idpatologiedistrict;
    }

    public void setIdpatologiedistrict(Integer idpatologiedistrict) {
        this.idpatologiedistrict = idpatologiedistrict;
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

    public Pathologie getIdpathologie() {
        return idpathologie;
    }

    public void setIdpathologie(Pathologie idpathologie) {
        this.idpathologie = idpathologie;
    }

    public Rubriquemorbidite getIdrubriquemorbidite() {
        return idrubriquemorbidite;
    }

    public void setIdrubriquemorbidite(Rubriquemorbidite idrubriquemorbidite) {
        this.idrubriquemorbidite = idrubriquemorbidite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpatologiedistrict != null ? idpatologiedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patologiedistrict)) {
            return false;
        }
        Patologiedistrict other = (Patologiedistrict) object;
        if ((this.idpatologiedistrict == null && other.idpatologiedistrict != null) || (this.idpatologiedistrict != null && !this.idpatologiedistrict.equals(other.idpatologiedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Patologiedistrict[ idpatologiedistrict=" + idpatologiedistrict + " ]";
    }
    
}
