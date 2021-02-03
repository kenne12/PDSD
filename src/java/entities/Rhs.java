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
    @NamedQuery(name = "Rhs.findAll", query = "SELECT r FROM Rhs r"),
    @NamedQuery(name = "Rhs.findByIdrhs", query = "SELECT r FROM Rhs r WHERE r.idrhs = :idrhs"),
    @NamedQuery(name = "Rhs.findByValeur", query = "SELECT r FROM Rhs r WHERE r.valeur = :valeur"),
    @NamedQuery(name = "Rhs.findByPrcds", query = "SELECT r FROM Rhs r WHERE r.prcds = :prcds")})
public class Rhs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrhs;
    private Integer valeur;
    private Boolean prcds;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idprofilpersonnel", referencedColumnName = "idprofilpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profilpersonnel idprofilpersonnel;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Rhs() {
    }

    public Rhs(Integer idrhs) {
        this.idrhs = idrhs;
    }

    public Integer getIdrhs() {
        return idrhs;
    }

    public void setIdrhs(Integer idrhs) {
        this.idrhs = idrhs;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
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

    public Profilpersonnel getIdprofilpersonnel() {
        return idprofilpersonnel;
    }

    public void setIdprofilpersonnel(Profilpersonnel idprofilpersonnel) {
        this.idprofilpersonnel = idprofilpersonnel;
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
        hash += (idrhs != null ? idrhs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhs)) {
            return false;
        }
        Rhs other = (Rhs) object;
        if ((this.idrhs == null && other.idrhs != null) || (this.idrhs != null && !this.idrhs.equals(other.idrhs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rhs[ idrhs=" + idrhs + " ]";
    }
    
}
