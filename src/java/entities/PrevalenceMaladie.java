/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "prevalence_maladie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrevalenceMaladie.findAll", query = "SELECT p FROM PrevalenceMaladie p"),
    @NamedQuery(name = "PrevalenceMaladie.findByIdprevalence", query = "SELECT p FROM PrevalenceMaladie p WHERE p.idprevalence = :idprevalence"),
    @NamedQuery(name = "PrevalenceMaladie.findByNombrecas", query = "SELECT p FROM PrevalenceMaladie p WHERE p.nombrecas = :nombrecas"),
    @NamedQuery(name = "PrevalenceMaladie.findByDate", query = "SELECT p FROM PrevalenceMaladie p WHERE p.date = :date")})
public class PrevalenceMaladie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idprevalence;
    private Integer nombrecas;
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idaxe", referencedColumnName = "idaxe")
    @ManyToOne(fetch = FetchType.LAZY)
    private Axe idaxe;
    @JoinColumn(name = "idpathologie", referencedColumnName = "idpathologie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pathologie idpathologie;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public PrevalenceMaladie() {
    }

    public PrevalenceMaladie(Integer idprevalence) {
        this.idprevalence = idprevalence;
    }

    public Integer getIdprevalence() {
        return idprevalence;
    }

    public void setIdprevalence(Integer idprevalence) {
        this.idprevalence = idprevalence;
    }

    public Integer getNombrecas() {
        return nombrecas;
    }

    public void setNombrecas(Integer nombrecas) {
        this.nombrecas = nombrecas;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Axe getIdaxe() {
        return idaxe;
    }

    public void setIdaxe(Axe idaxe) {
        this.idaxe = idaxe;
    }

    public Pathologie getIdpathologie() {
        return idpathologie;
    }

    public void setIdpathologie(Pathologie idpathologie) {
        this.idpathologie = idpathologie;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprevalence != null ? idprevalence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrevalenceMaladie)) {
            return false;
        }
        PrevalenceMaladie other = (PrevalenceMaladie) object;
        if ((this.idprevalence == null && other.idprevalence != null) || (this.idprevalence != null && !this.idprevalence.equals(other.idprevalence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrevalenceMaladie[ idprevalence=" + idprevalence + " ]";
    }
    
}
