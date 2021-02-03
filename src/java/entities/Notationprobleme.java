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
    @NamedQuery(name = "Notationprobleme.findAll", query = "SELECT n FROM Notationprobleme n"),
    @NamedQuery(name = "Notationprobleme.findByIdnotationprobleme", query = "SELECT n FROM Notationprobleme n WHERE n.idnotationprobleme = :idnotationprobleme"),
    @NamedQuery(name = "Notationprobleme.findByValeur", query = "SELECT n FROM Notationprobleme n WHERE n.valeur = :valeur")})
public class Notationprobleme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idnotationprobleme;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    @JoinColumn(name = "idprobleme", referencedColumnName = "idprobleme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Probleme idprobleme;
    @JoinColumn(name = "idsousrubriquenotationprobleme", referencedColumnName = "idsousrubriquenotationprobleme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousrubriquenotationprobleme idsousrubriquenotationprobleme;

    public Notationprobleme() {
    }

    public Notationprobleme(Long idnotationprobleme) {
        this.idnotationprobleme = idnotationprobleme;
    }

    public Long getIdnotationprobleme() {
        return idnotationprobleme;
    }

    public void setIdnotationprobleme(Long idnotationprobleme) {
        this.idnotationprobleme = idnotationprobleme;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Probleme getIdprobleme() {
        return idprobleme;
    }

    public void setIdprobleme(Probleme idprobleme) {
        this.idprobleme = idprobleme;
    }

    public Sousrubriquenotationprobleme getIdsousrubriquenotationprobleme() {
        return idsousrubriquenotationprobleme;
    }

    public void setIdsousrubriquenotationprobleme(Sousrubriquenotationprobleme idsousrubriquenotationprobleme) {
        this.idsousrubriquenotationprobleme = idsousrubriquenotationprobleme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotationprobleme != null ? idnotationprobleme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notationprobleme)) {
            return false;
        }
        Notationprobleme other = (Notationprobleme) object;
        if ((this.idnotationprobleme == null && other.idnotationprobleme != null) || (this.idnotationprobleme != null && !this.idnotationprobleme.equals(other.idnotationprobleme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Notationprobleme[ idnotationprobleme=" + idnotationprobleme + " ]";
    }
    
}
