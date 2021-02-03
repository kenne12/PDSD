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
    @NamedQuery(name = "Force.findAll", query = "SELECT f FROM Force f"),
    @NamedQuery(name = "Force.findByIdforce", query = "SELECT f FROM Force f WHERE f.idforce = :idforce"),
    @NamedQuery(name = "Force.findByNom", query = "SELECT f FROM Force f WHERE f.nom = :nom"),
    @NamedQuery(name = "Force.findByFacteur", query = "SELECT f FROM Force f WHERE f.facteur = :facteur")})
public class Force implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idforce;
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

    public Force() {
    }

    public Force(Long idforce) {
        this.idforce = idforce;
    }

    public Long getIdforce() {
        return idforce;
    }

    public void setIdforce(Long idforce) {
        this.idforce = idforce;
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
        hash += (idforce != null ? idforce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Force)) {
            return false;
        }
        Force other = (Force) object;
        if ((this.idforce == null && other.idforce != null) || (this.idforce != null && !this.idforce.equals(other.idforce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Force[ idforce=" + idforce + " ]";
    }
    
}
