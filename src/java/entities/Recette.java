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
    @NamedQuery(name = "Recette.findAll", query = "SELECT r FROM Recette r"),
    @NamedQuery(name = "Recette.findByIdrecette", query = "SELECT r FROM Recette r WHERE r.idrecette = :idrecette"),
    @NamedQuery(name = "Recette.findByValeur", query = "SELECT r FROM Recette r WHERE r.valeur = :valeur"),
    @NamedQuery(name = "Recette.findByPrcds", query = "SELECT r FROM Recette r WHERE r.prcds = :prcds")})
public class Recette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrecette;
    private BigInteger valeur;
    private Boolean prcds;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idsourcefi", referencedColumnName = "idsourcefi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefi;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @JoinColumn(name = "idtyperecette", referencedColumnName = "idtyperecette")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typerecette idtyperecette;

    public Recette() {
    }

    public Recette(Integer idrecette) {
        this.idrecette = idrecette;
    }

    public Integer getIdrecette() {
        return idrecette;
    }

    public void setIdrecette(Integer idrecette) {
        this.idrecette = idrecette;
    }

    public BigInteger getValeur() {
        return valeur;
    }

    public void setValeur(BigInteger valeur) {
        this.valeur = valeur;
    }

    public Boolean getPrcds() {
        return prcds;
    }

    public void setPrcds(Boolean prcds) {
        this.prcds = prcds;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Sourcefinancement getIdsourcefi() {
        return idsourcefi;
    }

    public void setIdsourcefi(Sourcefinancement idsourcefi) {
        this.idsourcefi = idsourcefi;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    public Typerecette getIdtyperecette() {
        return idtyperecette;
    }

    public void setIdtyperecette(Typerecette idtyperecette) {
        this.idtyperecette = idtyperecette;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecette != null ? idrecette.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recette)) {
            return false;
        }
        Recette other = (Recette) object;
        if ((this.idrecette == null && other.idrecette != null) || (this.idrecette != null && !this.idrecette.equals(other.idrecette))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Recette[ idrecette=" + idrecette + " ]";
    }
    
}
