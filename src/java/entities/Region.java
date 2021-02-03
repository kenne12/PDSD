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
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r"),
    @NamedQuery(name = "Region.findByIdregion", query = "SELECT r FROM Region r WHERE r.idregion = :idregion"),
    @NamedQuery(name = "Region.findByNomFr", query = "SELECT r FROM Region r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Region.findByCoordonnegps", query = "SELECT r FROM Region r WHERE r.coordonnegps = :coordonnegps"),
    @NamedQuery(name = "Region.findByCoderegion", query = "SELECT r FROM Region r WHERE r.coderegion = :coderegion"),
    @NamedQuery(name = "Region.findByIdpays", query = "SELECT r FROM Region r WHERE r.idpays = :idpays"),
    @NamedQuery(name = "Region.findByNomEn", query = "SELECT r FROM Region r WHERE r.nomEn = :nomEn")})
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idregion;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String coordonnegps;
    @Size(max = 254)
    private String coderegion;
    private Integer idpays;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Departement> departementList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Structurecommunautaire> structurecommunautaireList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<PrevalenceMaladie> prevalenceMaladieList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<District> districtList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Actualite> actualiteList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Structure> structureList;

    public Region() {
    }

    public Region(Integer idregion) {
        this.idregion = idregion;
    }

    public Integer getIdregion() {
        return idregion;
    }

    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getCoordonnegps() {
        return coordonnegps;
    }

    public void setCoordonnegps(String coordonnegps) {
        this.coordonnegps = coordonnegps;
    }

    public String getCoderegion() {
        return coderegion;
    }

    public void setCoderegion(String coderegion) {
        this.coderegion = coderegion;
    }

    public Integer getIdpays() {
        return idpays;
    }

    public void setIdpays(Integer idpays) {
        this.idpays = idpays;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Departement> getDepartementList() {
        return departementList;
    }

    public void setDepartementList(List<Departement> departementList) {
        this.departementList = departementList;
    }

    @XmlTransient
    public List<Structurecommunautaire> getStructurecommunautaireList() {
        return structurecommunautaireList;
    }

    public void setStructurecommunautaireList(List<Structurecommunautaire> structurecommunautaireList) {
        this.structurecommunautaireList = structurecommunautaireList;
    }

    @XmlTransient
    public List<PrevalenceMaladie> getPrevalenceMaladieList() {
        return prevalenceMaladieList;
    }

    public void setPrevalenceMaladieList(List<PrevalenceMaladie> prevalenceMaladieList) {
        this.prevalenceMaladieList = prevalenceMaladieList;
    }

    @XmlTransient
    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    @XmlTransient
    public List<Actualite> getActualiteList() {
        return actualiteList;
    }

    public void setActualiteList(List<Actualite> actualiteList) {
        this.actualiteList = actualiteList;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregion != null ? idregion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.idregion == null && other.idregion != null) || (this.idregion != null && !this.idregion.equals(other.idregion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Region[ idregion=" + idregion + " ]";
    }
    
}
