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
    @NamedQuery(name = "Interventionpnds.findAll", query = "SELECT i FROM Interventionpnds i"),
    @NamedQuery(name = "Interventionpnds.findByIdinterventionpnds", query = "SELECT i FROM Interventionpnds i WHERE i.idinterventionpnds = :idinterventionpnds"),
    @NamedQuery(name = "Interventionpnds.findByNomFr", query = "SELECT i FROM Interventionpnds i WHERE i.nomFr = :nomFr"),
    @NamedQuery(name = "Interventionpnds.findByDescription", query = "SELECT i FROM Interventionpnds i WHERE i.description = :description"),
    @NamedQuery(name = "Interventionpnds.findByActivite", query = "SELECT i FROM Interventionpnds i WHERE i.activite = :activite"),
    @NamedQuery(name = "Interventionpnds.findByCode", query = "SELECT i FROM Interventionpnds i WHERE i.code = :code"),
    @NamedQuery(name = "Interventionpnds.findByNomEn", query = "SELECT i FROM Interventionpnds i WHERE i.nomEn = :nomEn"),
    @NamedQuery(name = "Interventionpnds.findByObjectifOpFr", query = "SELECT i FROM Interventionpnds i WHERE i.objectifOpFr = :objectifOpFr"),
    @NamedQuery(name = "Interventionpnds.findByObjectifOpEn", query = "SELECT i FROM Interventionpnds i WHERE i.objectifOpEn = :objectifOpEn"),
    @NamedQuery(name = "Interventionpnds.findByDistrict", query = "SELECT i FROM Interventionpnds i WHERE i.district = :district"),
    @NamedQuery(name = "Interventionpnds.findByRegion", query = "SELECT i FROM Interventionpnds i WHERE i.region = :region")})
public class Interventionpnds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idinterventionpnds;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String description;
    @Size(max = 254)
    private String activite;
    @Size(max = 10)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @Size(max = 2147483647)
    @Column(name = "objectif_op_fr")
    private String objectifOpFr;
    @Size(max = 2147483647)
    @Column(name = "objectif_op_en")
    private String objectifOpEn;
    private Boolean district;
    private Boolean region;
    @OneToMany(mappedBy = "idintervention", fetch = FetchType.LAZY)
    private List<ObjectifOppDistrict> objectifOppDistrictList;
    @OneToMany(mappedBy = "idinterventionpnds", fetch = FetchType.LAZY)
    private List<ActiviteTraceur> activiteTraceurList;
    @JoinColumn(name = "idcategorieintervention", referencedColumnName = "idcategorieintervention")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorieintervention idcategorieintervention;
    @OneToMany(mappedBy = "idinterventionpnds", fetch = FetchType.LAZY)
    private List<Indicateur> indicateurList;

    public Interventionpnds() {
    }

    public Interventionpnds(Integer idinterventionpnds) {
        this.idinterventionpnds = idinterventionpnds;
    }

    public Integer getIdinterventionpnds() {
        return idinterventionpnds;
    }

    public void setIdinterventionpnds(Integer idinterventionpnds) {
        this.idinterventionpnds = idinterventionpnds;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public String getObjectifOpFr() {
        return objectifOpFr;
    }

    public void setObjectifOpFr(String objectifOpFr) {
        this.objectifOpFr = objectifOpFr;
    }

    public String getObjectifOpEn() {
        return objectifOpEn;
    }

    public void setObjectifOpEn(String objectifOpEn) {
        this.objectifOpEn = objectifOpEn;
    }

    public Boolean getDistrict() {
        return district;
    }

    public void setDistrict(Boolean district) {
        this.district = district;
    }

    public Boolean getRegion() {
        return region;
    }

    public void setRegion(Boolean region) {
        this.region = region;
    }

    @XmlTransient
    public List<ObjectifOppDistrict> getObjectifOppDistrictList() {
        return objectifOppDistrictList;
    }

    public void setObjectifOppDistrictList(List<ObjectifOppDistrict> objectifOppDistrictList) {
        this.objectifOppDistrictList = objectifOppDistrictList;
    }

    @XmlTransient
    public List<ActiviteTraceur> getActiviteTraceurList() {
        return activiteTraceurList;
    }

    public void setActiviteTraceurList(List<ActiviteTraceur> activiteTraceurList) {
        this.activiteTraceurList = activiteTraceurList;
    }

    public Categorieintervention getIdcategorieintervention() {
        return idcategorieintervention;
    }

    public void setIdcategorieintervention(Categorieintervention idcategorieintervention) {
        this.idcategorieintervention = idcategorieintervention;
    }

    @XmlTransient
    public List<Indicateur> getIndicateurList() {
        return indicateurList;
    }

    public void setIndicateurList(List<Indicateur> indicateurList) {
        this.indicateurList = indicateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinterventionpnds != null ? idinterventionpnds.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interventionpnds)) {
            return false;
        }
        Interventionpnds other = (Interventionpnds) object;
        if ((this.idinterventionpnds == null && other.idinterventionpnds != null) || (this.idinterventionpnds != null && !this.idinterventionpnds.equals(other.idinterventionpnds))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Interventionpnds[ idinterventionpnds=" + idinterventionpnds + " ]";
    }
    
}
