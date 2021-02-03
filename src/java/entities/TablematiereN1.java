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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "tablematiere_n1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablematiereN1.findAll", query = "SELECT t FROM TablematiereN1 t"),
    @NamedQuery(name = "TablematiereN1.findByIdtablematiereN1", query = "SELECT t FROM TablematiereN1 t WHERE t.idtablematiereN1 = :idtablematiereN1"),
    @NamedQuery(name = "TablematiereN1.findByNiveau1Fr", query = "SELECT t FROM TablematiereN1 t WHERE t.niveau1Fr = :niveau1Fr"),
    @NamedQuery(name = "TablematiereN1.findByDefaultnumpage", query = "SELECT t FROM TablematiereN1 t WHERE t.defaultnumpage = :defaultnumpage"),
    @NamedQuery(name = "TablematiereN1.findByNiveau1En", query = "SELECT t FROM TablematiereN1 t WHERE t.niveau1En = :niveau1En"),
    @NamedQuery(name = "TablematiereN1.findByDistrict", query = "SELECT t FROM TablematiereN1 t WHERE t.district = :district"),
    @NamedQuery(name = "TablematiereN1.findByRegion", query = "SELECT t FROM TablematiereN1 t WHERE t.region = :region")})
public class TablematiereN1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematiere_n1")
    private Integer idtablematiereN1;
    @Size(max = 2147483647)
    @Column(name = "niveau1_fr")
    private String niveau1Fr;
    private Integer defaultnumpage;
    @Size(max = 233)
    @Column(name = "niveau1_en")
    private String niveau1En;
    private Boolean district;
    private Boolean region;
    @OneToMany(mappedBy = "idtablematiereN1", fetch = FetchType.LAZY)
    private List<TablematiereN2> tablematiereN2List;
    @OneToMany(mappedBy = "idtablematiereN1", fetch = FetchType.LAZY)
    private List<Tablematieren1District> tablematieren1DistrictList;

    public TablematiereN1() {
    }

    public TablematiereN1(Integer idtablematiereN1) {
        this.idtablematiereN1 = idtablematiereN1;
    }

    public Integer getIdtablematiereN1() {
        return idtablematiereN1;
    }

    public void setIdtablematiereN1(Integer idtablematiereN1) {
        this.idtablematiereN1 = idtablematiereN1;
    }

    public String getNiveau1Fr() {
        return niveau1Fr;
    }

    public void setNiveau1Fr(String niveau1Fr) {
        this.niveau1Fr = niveau1Fr;
    }

    public Integer getDefaultnumpage() {
        return defaultnumpage;
    }

    public void setDefaultnumpage(Integer defaultnumpage) {
        this.defaultnumpage = defaultnumpage;
    }

    public String getNiveau1En() {
        return niveau1En;
    }

    public void setNiveau1En(String niveau1En) {
        this.niveau1En = niveau1En;
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
    public List<TablematiereN2> getTablematiereN2List() {
        return tablematiereN2List;
    }

    public void setTablematiereN2List(List<TablematiereN2> tablematiereN2List) {
        this.tablematiereN2List = tablematiereN2List;
    }

    @XmlTransient
    public List<Tablematieren1District> getTablematieren1DistrictList() {
        return tablematieren1DistrictList;
    }

    public void setTablematieren1DistrictList(List<Tablematieren1District> tablematieren1DistrictList) {
        this.tablematieren1DistrictList = tablematieren1DistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematiereN1 != null ? idtablematiereN1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablematiereN1)) {
            return false;
        }
        TablematiereN1 other = (TablematiereN1) object;
        if ((this.idtablematiereN1 == null && other.idtablematiereN1 != null) || (this.idtablematiereN1 != null && !this.idtablematiereN1.equals(other.idtablematiereN1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TablematiereN1[ idtablematiereN1=" + idtablematiereN1 + " ]";
    }
    
}
