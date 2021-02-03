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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projectionrecette.findAll", query = "SELECT p FROM Projectionrecette p"),
    @NamedQuery(name = "Projectionrecette.findByIdprojectionrecette", query = "SELECT p FROM Projectionrecette p WHERE p.idprojectionrecette = :idprojectionrecette"),
    @NamedQuery(name = "Projectionrecette.findByMontant", query = "SELECT p FROM Projectionrecette p WHERE p.montant = :montant")})
public class Projectionrecette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idprojectionrecette;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double montant;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idsourcefinancement", referencedColumnName = "idsourcefi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefinancement;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Projectionrecette() {
    }

    public Projectionrecette(Integer idprojectionrecette) {
        this.idprojectionrecette = idprojectionrecette;
    }

    public Integer getIdprojectionrecette() {
        return idprojectionrecette;
    }

    public void setIdprojectionrecette(Integer idprojectionrecette) {
        this.idprojectionrecette = idprojectionrecette;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Sourcefinancement getIdsourcefinancement() {
        return idsourcefinancement;
    }

    public void setIdsourcefinancement(Sourcefinancement idsourcefinancement) {
        this.idsourcefinancement = idsourcefinancement;
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
        hash += (idprojectionrecette != null ? idprojectionrecette.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projectionrecette)) {
            return false;
        }
        Projectionrecette other = (Projectionrecette) object;
        if ((this.idprojectionrecette == null && other.idprojectionrecette != null) || (this.idprojectionrecette != null && !this.idprojectionrecette.equals(other.idprojectionrecette))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Projectionrecette[ idprojectionrecette=" + idprojectionrecette + " ]";
    }
    
}
