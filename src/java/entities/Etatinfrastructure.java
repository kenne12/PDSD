/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etatinfrastructure.findAll", query = "SELECT e FROM Etatinfrastructure e"),
    @NamedQuery(name = "Etatinfrastructure.findByIdetatinfrastructure", query = "SELECT e FROM Etatinfrastructure e WHERE e.idetatinfrastructure = :idetatinfrastructure"),
    @NamedQuery(name = "Etatinfrastructure.findByCode", query = "SELECT e FROM Etatinfrastructure e WHERE e.code = :code"),
    @NamedQuery(name = "Etatinfrastructure.findByNomFr", query = "SELECT e FROM Etatinfrastructure e WHERE e.nomFr = :nomFr"),
    @NamedQuery(name = "Etatinfrastructure.findByNomEn", query = "SELECT e FROM Etatinfrastructure e WHERE e.nomEn = :nomEn"),
    @NamedQuery(name = "Etatinfrastructure.findByPriorite", query = "SELECT e FROM Etatinfrastructure e WHERE e.priorite = :priorite")})
public class Etatinfrastructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idetatinfrastructure;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean priorite;
    @OneToMany(mappedBy = "idetatinfrastructure", fetch = FetchType.LAZY)
    private List<Paramcoutinfrastructure> paramcoutinfrastructureList;
    @OneToMany(mappedBy = "idetatinfrastructure", fetch = FetchType.LAZY)
    private List<Infrastructure> infrastructureList;

    public Etatinfrastructure() {
    }

    public Etatinfrastructure(Integer idetatinfrastructure) {
        this.idetatinfrastructure = idetatinfrastructure;
    }

    public Integer getIdetatinfrastructure() {
        return idetatinfrastructure;
    }

    public void setIdetatinfrastructure(Integer idetatinfrastructure) {
        this.idetatinfrastructure = idetatinfrastructure;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public Boolean getPriorite() {
        return priorite;
    }

    public void setPriorite(Boolean priorite) {
        this.priorite = priorite;
    }

    @XmlTransient
    public List<Paramcoutinfrastructure> getParamcoutinfrastructureList() {
        return paramcoutinfrastructureList;
    }

    public void setParamcoutinfrastructureList(List<Paramcoutinfrastructure> paramcoutinfrastructureList) {
        this.paramcoutinfrastructureList = paramcoutinfrastructureList;
    }

    @XmlTransient
    public List<Infrastructure> getInfrastructureList() {
        return infrastructureList;
    }

    public void setInfrastructureList(List<Infrastructure> infrastructureList) {
        this.infrastructureList = infrastructureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetatinfrastructure != null ? idetatinfrastructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etatinfrastructure)) {
            return false;
        }
        Etatinfrastructure other = (Etatinfrastructure) object;
        if ((this.idetatinfrastructure == null && other.idetatinfrastructure != null) || (this.idetatinfrastructure != null && !this.idetatinfrastructure.equals(other.idetatinfrastructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etatinfrastructure[ idetatinfrastructure=" + idetatinfrastructure + " ]";
    }
    
}
