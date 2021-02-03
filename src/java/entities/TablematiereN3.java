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
@Table(name = "tablematiere_n3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablematiereN3.findAll", query = "SELECT t FROM TablematiereN3 t"),
    @NamedQuery(name = "TablematiereN3.findByIdtablematiereN3", query = "SELECT t FROM TablematiereN3 t WHERE t.idtablematiereN3 = :idtablematiereN3"),
    @NamedQuery(name = "TablematiereN3.findByNiveauFr", query = "SELECT t FROM TablematiereN3 t WHERE t.niveauFr = :niveauFr"),
    @NamedQuery(name = "TablematiereN3.findByDefaultnumpage", query = "SELECT t FROM TablematiereN3 t WHERE t.defaultnumpage = :defaultnumpage"),
    @NamedQuery(name = "TablematiereN3.findByNiveauEn", query = "SELECT t FROM TablematiereN3 t WHERE t.niveauEn = :niveauEn")})
public class TablematiereN3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematiere_n3")
    private Long idtablematiereN3;
    @Size(max = 2147483647)
    @Column(name = "niveau_fr")
    private String niveauFr;
    private Integer defaultnumpage;
    @Size(max = 233)
    @Column(name = "niveau_en")
    private String niveauEn;
    @JoinColumn(name = "idtablematiere_n2", referencedColumnName = "idtablematiere_n2")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN2 idtablematiereN2;
    @OneToMany(mappedBy = "idtablematiereN3", fetch = FetchType.LAZY)
    private List<Tablematieren3District> tablematieren3DistrictList;

    public TablematiereN3() {
    }

    public TablematiereN3(Long idtablematiereN3) {
        this.idtablematiereN3 = idtablematiereN3;
    }

    public Long getIdtablematiereN3() {
        return idtablematiereN3;
    }

    public void setIdtablematiereN3(Long idtablematiereN3) {
        this.idtablematiereN3 = idtablematiereN3;
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

    public TablematiereN2 getIdtablematiereN2() {
        return idtablematiereN2;
    }

    public void setIdtablematiereN2(TablematiereN2 idtablematiereN2) {
        this.idtablematiereN2 = idtablematiereN2;
    }

    @XmlTransient
    public List<Tablematieren3District> getTablematieren3DistrictList() {
        return tablematieren3DistrictList;
    }

    public void setTablematieren3DistrictList(List<Tablematieren3District> tablematieren3DistrictList) {
        this.tablematieren3DistrictList = tablematieren3DistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematiereN3 != null ? idtablematiereN3.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablematiereN3)) {
            return false;
        }
        TablematiereN3 other = (TablematiereN3) object;
        if ((this.idtablematiereN3 == null && other.idtablematiereN3 != null) || (this.idtablematiereN3 != null && !this.idtablematiereN3.equals(other.idtablematiereN3))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TablematiereN3[ idtablematiereN3=" + idtablematiereN3 + " ]";
    }
    
}
