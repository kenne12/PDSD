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
    @NamedQuery(name = "Listetableau.findAll", query = "SELECT l FROM Listetableau l"),
    @NamedQuery(name = "Listetableau.findByIdlisttableau", query = "SELECT l FROM Listetableau l WHERE l.idlisttableau = :idlisttableau"),
    @NamedQuery(name = "Listetableau.findByNomFr", query = "SELECT l FROM Listetableau l WHERE l.nomFr = :nomFr"),
    @NamedQuery(name = "Listetableau.findByNomEn", query = "SELECT l FROM Listetableau l WHERE l.nomEn = :nomEn")})
public class Listetableau implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idlisttableau;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idlisttableau", fetch = FetchType.LAZY)
    private List<ListetableauDistrict> listetableauDistrictList;

    public Listetableau() {
    }

    public Listetableau(Integer idlisttableau) {
        this.idlisttableau = idlisttableau;
    }

    public Integer getIdlisttableau() {
        return idlisttableau;
    }

    public void setIdlisttableau(Integer idlisttableau) {
        this.idlisttableau = idlisttableau;
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
    public List<ListetableauDistrict> getListetableauDistrictList() {
        return listetableauDistrictList;
    }

    public void setListetableauDistrictList(List<ListetableauDistrict> listetableauDistrictList) {
        this.listetableauDistrictList = listetableauDistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlisttableau != null ? idlisttableau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listetableau)) {
            return false;
        }
        Listetableau other = (Listetableau) object;
        if ((this.idlisttableau == null && other.idlisttableau != null) || (this.idlisttableau != null && !this.idlisttableau.equals(other.idlisttableau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Listetableau[ idlisttableau=" + idlisttableau + " ]";
    }
    
}
