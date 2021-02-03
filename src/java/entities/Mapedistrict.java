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
    @NamedQuery(name = "Mapedistrict.findAll", query = "SELECT m FROM Mapedistrict m"),
    @NamedQuery(name = "Mapedistrict.findByIdmapedistrict", query = "SELECT m FROM Mapedistrict m WHERE m.idmapedistrict = :idmapedistrict"),
    @NamedQuery(name = "Mapedistrict.findByValeur", query = "SELECT m FROM Mapedistrict m WHERE m.valeur = :valeur")})
public class Mapedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idmapedistrict;
    private BigInteger valeur;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idmape", referencedColumnName = "idmape")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mape idmape;

    public Mapedistrict() {
    }

    public Mapedistrict(Long idmapedistrict) {
        this.idmapedistrict = idmapedistrict;
    }

    public Long getIdmapedistrict() {
        return idmapedistrict;
    }

    public void setIdmapedistrict(Long idmapedistrict) {
        this.idmapedistrict = idmapedistrict;
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

    public Mape getIdmape() {
        return idmape;
    }

    public void setIdmape(Mape idmape) {
        this.idmape = idmape;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmapedistrict != null ? idmapedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mapedistrict)) {
            return false;
        }
        Mapedistrict other = (Mapedistrict) object;
        if ((this.idmapedistrict == null && other.idmapedistrict != null) || (this.idmapedistrict != null && !this.idmapedistrict.equals(other.idmapedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mapedistrict[ idmapedistrict=" + idmapedistrict + " ]";
    }
    
}
