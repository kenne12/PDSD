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
    @NamedQuery(name = "Depense.findAll", query = "SELECT d FROM Depense d"),
    @NamedQuery(name = "Depense.findByIddepense", query = "SELECT d FROM Depense d WHERE d.iddepense = :iddepense"),
    @NamedQuery(name = "Depense.findByValeur", query = "SELECT d FROM Depense d WHERE d.valeur = :valeur"),
    @NamedQuery(name = "Depense.findByPrcds", query = "SELECT d FROM Depense d WHERE d.prcds = :prcds")})
public class Depense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer iddepense;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Boolean prcds;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idnaturedepense", referencedColumnName = "idnaturedepense")
    @ManyToOne(fetch = FetchType.LAZY)
    private Naturedepense idnaturedepense;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Depense() {
    }

    public Depense(Integer iddepense) {
        this.iddepense = iddepense;
    }

    public Integer getIddepense() {
        return iddepense;
    }

    public void setIddepense(Integer iddepense) {
        this.iddepense = iddepense;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
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

    public Naturedepense getIdnaturedepense() {
        return idnaturedepense;
    }

    public void setIdnaturedepense(Naturedepense idnaturedepense) {
        this.idnaturedepense = idnaturedepense;
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
        hash += (iddepense != null ? iddepense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depense)) {
            return false;
        }
        Depense other = (Depense) object;
        if ((this.iddepense == null && other.iddepense != null) || (this.iddepense != null && !this.iddepense.equals(other.iddepense))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Depense[ iddepense=" + iddepense + " ]";
    }
    
}
