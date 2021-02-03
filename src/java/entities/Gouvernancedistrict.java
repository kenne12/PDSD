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
    @NamedQuery(name = "Gouvernancedistrict.findAll", query = "SELECT g FROM Gouvernancedistrict g"),
    @NamedQuery(name = "Gouvernancedistrict.findByIdgouvernancedistrict", query = "SELECT g FROM Gouvernancedistrict g WHERE g.idgouvernancedistrict = :idgouvernancedistrict"),
    @NamedQuery(name = "Gouvernancedistrict.findByValeur", query = "SELECT g FROM Gouvernancedistrict g WHERE g.valeur = :valeur")})
public class Gouvernancedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idgouvernancedistrict;
    @Size(max = 2147483647)
    private String valeur;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idrubriquegouvernance", referencedColumnName = "idrubriquegouvernance")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquegouvernance idrubriquegouvernance;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Gouvernancedistrict() {
    }

    public Gouvernancedistrict(Integer idgouvernancedistrict) {
        this.idgouvernancedistrict = idgouvernancedistrict;
    }

    public Integer getIdgouvernancedistrict() {
        return idgouvernancedistrict;
    }

    public void setIdgouvernancedistrict(Integer idgouvernancedistrict) {
        this.idgouvernancedistrict = idgouvernancedistrict;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Rubriquegouvernance getIdrubriquegouvernance() {
        return idrubriquegouvernance;
    }

    public void setIdrubriquegouvernance(Rubriquegouvernance idrubriquegouvernance) {
        this.idrubriquegouvernance = idrubriquegouvernance;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgouvernancedistrict != null ? idgouvernancedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gouvernancedistrict)) {
            return false;
        }
        Gouvernancedistrict other = (Gouvernancedistrict) object;
        if ((this.idgouvernancedistrict == null && other.idgouvernancedistrict != null) || (this.idgouvernancedistrict != null && !this.idgouvernancedistrict.equals(other.idgouvernancedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Gouvernancedistrict[ idgouvernancedistrict=" + idgouvernancedistrict + " ]";
    }
    
}
