/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "coasting_default")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoastingDefault.findAll", query = "SELECT c FROM CoastingDefault c"),
    @NamedQuery(name = "CoastingDefault.findByIdcoastingDefault", query = "SELECT c FROM CoastingDefault c WHERE c.idcoastingDefault = :idcoastingDefault"),
    @NamedQuery(name = "CoastingDefault.findByCoutunitaire", query = "SELECT c FROM CoastingDefault c WHERE c.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "CoastingDefault.findByQte", query = "SELECT c FROM CoastingDefault c WHERE c.qte = :qte"),
    @NamedQuery(name = "CoastingDefault.findByNbreJr", query = "SELECT c FROM CoastingDefault c WHERE c.nbreJr = :nbreJr")})
public class CoastingDefault implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcoasting_default")
    private Long idcoastingDefault;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    private Integer qte;
    @Column(name = "nbre_jr")
    private Integer nbreJr;
    @JoinColumn(name = "idactivite_default", referencedColumnName = "idactivite_default")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActiviteDefault idactiviteDefault;
    @JoinColumn(name = "idelementcout", referencedColumnName = "idelement_cout")
    @ManyToOne(fetch = FetchType.LAZY)
    private ElementCout idelementcout;

    public CoastingDefault() {
    }

    public CoastingDefault(Long idcoastingDefault) {
        this.idcoastingDefault = idcoastingDefault;
    }

    public Long getIdcoastingDefault() {
        return idcoastingDefault;
    }

    public void setIdcoastingDefault(Long idcoastingDefault) {
        this.idcoastingDefault = idcoastingDefault;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getNbreJr() {
        return nbreJr;
    }

    public void setNbreJr(Integer nbreJr) {
        this.nbreJr = nbreJr;
    }

    public ActiviteDefault getIdactiviteDefault() {
        return idactiviteDefault;
    }

    public void setIdactiviteDefault(ActiviteDefault idactiviteDefault) {
        this.idactiviteDefault = idactiviteDefault;
    }

    public ElementCout getIdelementcout() {
        return idelementcout;
    }

    public void setIdelementcout(ElementCout idelementcout) {
        this.idelementcout = idelementcout;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcoastingDefault != null ? idcoastingDefault.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoastingDefault)) {
            return false;
        }
        CoastingDefault other = (CoastingDefault) object;
        if ((this.idcoastingDefault == null && other.idcoastingDefault != null) || (this.idcoastingDefault != null && !this.idcoastingDefault.equals(other.idcoastingDefault))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CoastingDefault[ idcoastingDefault=" + idcoastingDefault + " ]";
    }
    
}
