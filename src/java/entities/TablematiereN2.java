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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "tablematiere_n2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablematiereN2.findAll", query = "SELECT t FROM TablematiereN2 t"),
    @NamedQuery(name = "TablematiereN2.findByIdtablematiereN2", query = "SELECT t FROM TablematiereN2 t WHERE t.idtablematiereN2 = :idtablematiereN2"),
    @NamedQuery(name = "TablematiereN2.findByNiveauFr", query = "SELECT t FROM TablematiereN2 t WHERE t.niveauFr = :niveauFr"),
    @NamedQuery(name = "TablematiereN2.findByDefaultnumpage", query = "SELECT t FROM TablematiereN2 t WHERE t.defaultnumpage = :defaultnumpage"),
    @NamedQuery(name = "TablematiereN2.findByNiveauEn", query = "SELECT t FROM TablematiereN2 t WHERE t.niveauEn = :niveauEn")})
public class TablematiereN2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematiere_n2")
    private Long idtablematiereN2;
    @Size(max = 2147483647)
    @Column(name = "niveau_fr")
    private String niveauFr;
    private Integer defaultnumpage;
    @Size(max = 233)
    @Column(name = "niveau_en")
    private String niveauEn;
    @OneToMany(mappedBy = "idtablematiereN2", fetch = FetchType.LAZY)
    private List<TablematiereN3> tablematiereN3List;
    @JoinColumn(name = "idtablematiere_n1", referencedColumnName = "idtablematiere_n1")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN1 idtablematiereN1;
    @OneToMany(mappedBy = "idtablematiereN2", fetch = FetchType.LAZY)
    private List<Tablematieren2District> tablematieren2DistrictList;

    public TablematiereN2() {
    }

    public TablematiereN2(Long idtablematiereN2) {
        this.idtablematiereN2 = idtablematiereN2;
    }

    public Long getIdtablematiereN2() {
        return idtablematiereN2;
    }

    public void setIdtablematiereN2(Long idtablematiereN2) {
        this.idtablematiereN2 = idtablematiereN2;
    }

    public String getNiveauFr() {
        return niveauFr;
    }

    public void setNiveauFr(String niveauFr) {
        this.niveauFr = niveauFr;
    }

    public Integer getDefaultnumpage() {
        return defaultnumpage;
    }

    public void setDefaultnumpage(Integer defaultnumpage) {
        this.defaultnumpage = defaultnumpage;
    }

    public String getNiveauEn() {
        return niveauEn;
    }

    public void setNiveauEn(String niveauEn) {
        this.niveauEn = niveauEn;
    }

    @XmlTransient
    public List<TablematiereN3> getTablematiereN3List() {
        return tablematiereN3List;
    }

    public void setTablematiereN3List(List<TablematiereN3> tablematiereN3List) {
        this.tablematiereN3List = tablematiereN3List;
    }

    public TablematiereN1 getIdtablematiereN1() {
        return idtablematiereN1;
    }

    public void setIdtablematiereN1(TablematiereN1 idtablematiereN1) {
        this.idtablematiereN1 = idtablematiereN1;
    }

    @XmlTransient
    public List<Tablematieren2District> getTablematieren2DistrictList() {
        return tablematieren2DistrictList;
    }

    public void setTablematieren2DistrictList(List<Tablematieren2District> tablematieren2DistrictList) {
        this.tablematieren2DistrictList = tablematieren2DistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematiereN2 != null ? idtablematiereN2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablematiereN2)) {
            return false;
        }
        TablematiereN2 other = (TablematiereN2) object;
        if ((this.idtablematiereN2 == null && other.idtablematiereN2 != null) || (this.idtablematiereN2 != null && !this.idtablematiereN2.equals(other.idtablematiereN2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TablematiereN2[ idtablematiereN2=" + idtablematiereN2 + " ]";
    }
    
}
