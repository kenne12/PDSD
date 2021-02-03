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
    @NamedQuery(name = "Faiblesse.findAll", query = "SELECT f FROM Faiblesse f"),
    @NamedQuery(name = "Faiblesse.findByIdfaiblesse", query = "SELECT f FROM Faiblesse f WHERE f.idfaiblesse = :idfaiblesse"),
    @NamedQuery(name = "Faiblesse.findByNom", query = "SELECT f FROM Faiblesse f WHERE f.nom = :nom"),
    @NamedQuery(name = "Faiblesse.findByFacteur", query = "SELECT f FROM Faiblesse f WHERE f.facteur = :facteur")})
public class Faiblesse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idfaiblesse;
    @Size(max = 2147483647)
    private String nom;
    private Boolean facteur;
    @JoinColumn(name = "idacteur", referencedColumnName = "idacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Acteur idacteur;
    @JoinColumn(name = "idfacteur", referencedColumnName = "idfacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facteur idfacteur;
    @JoinColumn(name = "idffom", referencedColumnName = "idffom")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ffom idffom;

    public Faiblesse() {
    }

    public Faiblesse(Long idfaiblesse) {
        this.idfaiblesse = idfaiblesse;
    }

    public Long getIdfaiblesse() {
        return idfaiblesse;
    }

    public void setIdfaiblesse(Long idfaiblesse) {
        this.idfaiblesse = idfaiblesse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getFacteur() {
        return facteur;
    }

    public void setFacteur(Boolean facteur) {
        this.facteur = facteur;
    }

    public Acteur getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(Acteur idacteur) {
        this.idacteur = idacteur;
    }

    public Facteur getIdfacteur() {
        return idfacteur;
    }

    public void setIdfacteur(Facteur idfacteur) {
        this.idfacteur = idfacteur;
    }

    public Ffom getIdffom() {
        return idffom;
    }

    public void setIdffom(Ffom idffom) {
        this.idffom = idffom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfaiblesse != null ? idfaiblesse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faiblesse)) {
            return false;
        }
        Faiblesse other = (Faiblesse) object;
        if ((this.idfaiblesse == null && other.idfaiblesse != null) || (this.idfaiblesse != null && !this.idfaiblesse.equals(other.idfaiblesse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Faiblesse[ idfaiblesse=" + idfaiblesse + " ]";
    }
    
}
