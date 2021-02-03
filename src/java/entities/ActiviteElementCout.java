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
@Table(name = "activite_element_cout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteElementCout.findAll", query = "SELECT a FROM ActiviteElementCout a"),
    @NamedQuery(name = "ActiviteElementCout.findByIdactiviteElementCout", query = "SELECT a FROM ActiviteElementCout a WHERE a.idactiviteElementCout = :idactiviteElementCout"),
    @NamedQuery(name = "ActiviteElementCout.findByCoutunitaire", query = "SELECT a FROM ActiviteElementCout a WHERE a.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "ActiviteElementCout.findByQte", query = "SELECT a FROM ActiviteElementCout a WHERE a.qte = :qte"),
    @NamedQuery(name = "ActiviteElementCout.findByNbreJr", query = "SELECT a FROM ActiviteElementCout a WHERE a.nbreJr = :nbreJr")})
public class ActiviteElementCout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_element_cout")
    private Long idactiviteElementCout;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    private Double qte;
    @Column(name = "nbre_jr")
    private Double nbreJr;
    @JoinColumn(name = "idactivite", referencedColumnName = "idactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activite idactivite;
    @JoinColumn(name = "idelementcout", referencedColumnName = "idelement_cout")
    @ManyToOne(fetch = FetchType.LAZY)
    private ElementCout idelementcout;

    public ActiviteElementCout() {
    }

    public ActiviteElementCout(Long idactiviteElementCout) {
        this.idactiviteElementCout = idactiviteElementCout;
    }

    public Long getIdactiviteElementCout() {
        return idactiviteElementCout;
    }

    public void setIdactiviteElementCout(Long idactiviteElementCout) {
        this.idactiviteElementCout = idactiviteElementCout;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getNbreJr() {
        return nbreJr;
    }

    public void setNbreJr(Double nbreJr) {
        this.nbreJr = nbreJr;
    }

    public Activite getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Activite idactivite) {
        this.idactivite = idactivite;
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
        hash += (idactiviteElementCout != null ? idactiviteElementCout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteElementCout)) {
            return false;
        }
        ActiviteElementCout other = (ActiviteElementCout) object;
        if ((this.idactiviteElementCout == null && other.idactiviteElementCout != null) || (this.idactiviteElementCout != null && !this.idactiviteElementCout.equals(other.idactiviteElementCout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteElementCout[ idactiviteElementCout=" + idactiviteElementCout + " ]";
    }
    
}
