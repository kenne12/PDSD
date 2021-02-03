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
    @NamedQuery(name = "Sourcefinancement.findAll", query = "SELECT s FROM Sourcefinancement s"),
    @NamedQuery(name = "Sourcefinancement.findByIdsourcefi", query = "SELECT s FROM Sourcefinancement s WHERE s.idsourcefi = :idsourcefi"),
    @NamedQuery(name = "Sourcefinancement.findByNomFr", query = "SELECT s FROM Sourcefinancement s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Sourcefinancement.findByNomEn", query = "SELECT s FROM Sourcefinancement s WHERE s.nomEn = :nomEn")})
public class Sourcefinancement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsourcefi;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idsourcefi", fetch = FetchType.LAZY)
    private List<ActiviteDefault> activiteDefaultList;
    @OneToMany(mappedBy = "idsourcefi", fetch = FetchType.LAZY)
    private List<Recette> recetteList;
    @OneToMany(mappedBy = "idsourcefinancement", fetch = FetchType.LAZY)
    private List<Projectionrecette> projectionrecetteList;
    @OneToMany(mappedBy = "idsourcefi", fetch = FetchType.LAZY)
    private List<Activite> activiteList;
    @OneToMany(mappedBy = "idsourcefi", fetch = FetchType.LAZY)
    private List<ActiviteStructure> activiteStructureList;

    public Sourcefinancement() {
    }

    public Sourcefinancement(Integer idsourcefi) {
        this.idsourcefi = idsourcefi;
    }

    public Integer getIdsourcefi() {
        return idsourcefi;
    }

    public void setIdsourcefi(Integer idsourcefi) {
        this.idsourcefi = idsourcefi;
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

    @XmlTransient
    public List<ActiviteDefault> getActiviteDefaultList() {
        return activiteDefaultList;
    }

    public void setActiviteDefaultList(List<ActiviteDefault> activiteDefaultList) {
        this.activiteDefaultList = activiteDefaultList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @XmlTransient
    public List<Projectionrecette> getProjectionrecetteList() {
        return projectionrecetteList;
    }

    public void setProjectionrecetteList(List<Projectionrecette> projectionrecetteList) {
        this.projectionrecetteList = projectionrecetteList;
    }

    @XmlTransient
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    @XmlTransient
    public List<ActiviteStructure> getActiviteStructureList() {
        return activiteStructureList;
    }

    public void setActiviteStructureList(List<ActiviteStructure> activiteStructureList) {
        this.activiteStructureList = activiteStructureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsourcefi != null ? idsourcefi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sourcefinancement)) {
            return false;
        }
        Sourcefinancement other = (Sourcefinancement) object;
        if ((this.idsourcefi == null && other.idsourcefi != null) || (this.idsourcefi != null && !this.idsourcefi.equals(other.idsourcefi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sourcefinancement[ idsourcefi=" + idsourcefi + " ]";
    }
    
}
