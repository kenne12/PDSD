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
    @NamedQuery(name = "Morbiditedistrict.findAll", query = "SELECT m FROM Morbiditedistrict m"),
    @NamedQuery(name = "Morbiditedistrict.findByIdmorbiditedistrict", query = "SELECT m FROM Morbiditedistrict m WHERE m.idmorbiditedistrict = :idmorbiditedistrict"),
    @NamedQuery(name = "Morbiditedistrict.findByValeur", query = "SELECT m FROM Morbiditedistrict m WHERE m.valeur = :valeur")})
public class Morbiditedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idmorbiditedistrict;
    private BigInteger valeur;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idmorbidite", referencedColumnName = "idmorbidite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Morbidite idmorbidite;
    @JoinColumn(name = "idpathologie", referencedColumnName = "idpathologie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pathologie idpathologie;
    @JoinColumn(name = "idrubriquemorbidite", referencedColumnName = "idrubriquemorbidite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquemorbidite idrubriquemorbidite;

    public Morbiditedistrict() {
    }

    public Morbiditedistrict(Long idmorbiditedistrict) {
        this.idmorbiditedistrict = idmorbiditedistrict;
    }

    public Long getIdmorbiditedistrict() {
        return idmorbiditedistrict;
    }

    public void setIdmorbiditedistrict(Long idmorbiditedistrict) {
        this.idmorbiditedistrict = idmorbiditedistrict;
    }

    public BigInteger getValeur() {
        return valeur;
    }

    public void setValeur(BigInteger valeur) {
        this.valeur = valeur;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Morbidite getIdmorbidite() {
        return idmorbidite;
    }

    public void setIdmorbidite(Morbidite idmorbidite) {
        this.idmorbidite = idmorbidite;
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
        hash += (idmorbiditedistrict != null ? idmorbiditedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Morbiditedistrict)) {
            return false;
        }
        Morbiditedistrict other = (Morbiditedistrict) object;
        if ((this.idmorbiditedistrict == null && other.idmorbiditedistrict != null) || (this.idmorbiditedistrict != null && !this.idmorbiditedistrict.equals(other.idmorbiditedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Morbiditedistrict[ idmorbiditedistrict=" + idmorbiditedistrict + " ]";
    }
    
}
