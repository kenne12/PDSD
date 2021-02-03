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
    @NamedQuery(name = "Mortalitedistrict.findAll", query = "SELECT m FROM Mortalitedistrict m"),
    @NamedQuery(name = "Mortalitedistrict.findByIdmortalitedistrict", query = "SELECT m FROM Mortalitedistrict m WHERE m.idmortalitedistrict = :idmortalitedistrict"),
    @NamedQuery(name = "Mortalitedistrict.findByValeur", query = "SELECT m FROM Mortalitedistrict m WHERE m.valeur = :valeur")})
public class Mortalitedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idmortalitedistrict;
    private BigInteger valeur;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idmortalite", referencedColumnName = "idmortalite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mortalite idmortalite;
    @JoinColumn(name = "idrubriquemortalite", referencedColumnName = "idrubriquemortalite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquemortalite idrubriquemortalite;

    public Mortalitedistrict() {
    }

    public Mortalitedistrict(Long idmortalitedistrict) {
        this.idmortalitedistrict = idmortalitedistrict;
    }

    public Long getIdmortalitedistrict() {
        return idmortalitedistrict;
    }

    public void setIdmortalitedistrict(Long idmortalitedistrict) {
        this.idmortalitedistrict = idmortalitedistrict;
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

    public Mortalite getIdmortalite() {
        return idmortalite;
    }

    public void setIdmortalite(Mortalite idmortalite) {
        this.idmortalite = idmortalite;
    }

    public Rubriquemortalite getIdrubriquemortalite() {
        return idrubriquemortalite;
    }

    public void setIdrubriquemortalite(Rubriquemortalite idrubriquemortalite) {
        this.idrubriquemortalite = idrubriquemortalite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmortalitedistrict != null ? idmortalitedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mortalitedistrict)) {
            return false;
        }
        Mortalitedistrict other = (Mortalitedistrict) object;
        if ((this.idmortalitedistrict == null && other.idmortalitedistrict != null) || (this.idmortalitedistrict != null && !this.idmortalitedistrict.equals(other.idmortalitedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mortalitedistrict[ idmortalitedistrict=" + idmortalitedistrict + " ]";
    }
    
}
