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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "typeinfra_typestruc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeinfraTypestruc.findAll", query = "SELECT t FROM TypeinfraTypestruc t"),
    @NamedQuery(name = "TypeinfraTypestruc.findByIdtypeinfraTypestruc", query = "SELECT t FROM TypeinfraTypestruc t WHERE t.idtypeinfraTypestruc = :idtypeinfraTypestruc"),
    @NamedQuery(name = "TypeinfraTypestruc.findByCoutunitaire", query = "SELECT t FROM TypeinfraTypestruc t WHERE t.coutunitaire = :coutunitaire")})
public class TypeinfraTypestruc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtypeinfra_typestruc")
    private Integer idtypeinfraTypestruc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    @JoinColumn(name = "idtypeinfrastructure", referencedColumnName = "idtypeinfrastructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeinfrastructure idtypeinfrastructure;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;
    @OneToMany(mappedBy = "idtypeinfraTypestruc", fetch = FetchType.LAZY)
    private List<Infrastructure> infrastructureList;

    public TypeinfraTypestruc() {
    }

    public TypeinfraTypestruc(Integer idtypeinfraTypestruc) {
        this.idtypeinfraTypestruc = idtypeinfraTypestruc;
    }

    public Integer getIdtypeinfraTypestruc() {
        return idtypeinfraTypestruc;
    }

    public void setIdtypeinfraTypestruc(Integer idtypeinfraTypestruc) {
        this.idtypeinfraTypestruc = idtypeinfraTypestruc;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public Typeinfrastructure getIdtypeinfrastructure() {
        return idtypeinfrastructure;
    }

    public void setIdtypeinfrastructure(Typeinfrastructure idtypeinfrastructure) {
        this.idtypeinfrastructure = idtypeinfrastructure;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
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
        hash += (idtypeinfraTypestruc != null ? idtypeinfraTypestruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeinfraTypestruc)) {
            return false;
        }
        TypeinfraTypestruc other = (TypeinfraTypestruc) object;
        if ((this.idtypeinfraTypestruc == null && other.idtypeinfraTypestruc != null) || (this.idtypeinfraTypestruc != null && !this.idtypeinfraTypestruc.equals(other.idtypeinfraTypestruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeinfraTypestruc[ idtypeinfraTypestruc=" + idtypeinfraTypestruc + " ]";
    }
    
}
