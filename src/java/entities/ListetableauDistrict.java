/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "listetableau_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListetableauDistrict.findAll", query = "SELECT l FROM ListetableauDistrict l"),
    @NamedQuery(name = "ListetableauDistrict.findByIdlistetableauDistrict", query = "SELECT l FROM ListetableauDistrict l WHERE l.idlistetableauDistrict = :idlistetableauDistrict"),
    @NamedQuery(name = "ListetableauDistrict.findByNumpage", query = "SELECT l FROM ListetableauDistrict l WHERE l.numpage = :numpage")})
public class ListetableauDistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlistetableau_district")
    private Long idlistetableauDistrict;
    private Integer numpage;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idlisttableau", referencedColumnName = "idlisttableau")
    @ManyToOne(fetch = FetchType.LAZY)
    private Listetableau idlisttableau;

    public ListetableauDistrict() {
    }

    public ListetableauDistrict(Long idlistetableauDistrict) {
        this.idlistetableauDistrict = idlistetableauDistrict;
    }

    public Long getIdlistetableauDistrict() {
        return idlistetableauDistrict;
    }

    public void setIdlistetableauDistrict(Long idlistetableauDistrict) {
        this.idlistetableauDistrict = idlistetableauDistrict;
    }

    public Integer getNumpage() {
        return numpage;
    }

    public void setNumpage(Integer numpage) {
        this.numpage = numpage;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Listetableau getIdlisttableau() {
        return idlisttableau;
    }

    public void setIdlisttableau(Listetableau idlisttableau) {
        this.idlisttableau = idlisttableau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistetableauDistrict != null ? idlistetableauDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListetableauDistrict)) {
            return false;
        }
        ListetableauDistrict other = (ListetableauDistrict) object;
        if ((this.idlistetableauDistrict == null && other.idlistetableauDistrict != null) || (this.idlistetableauDistrict != null && !this.idlistetableauDistrict.equals(other.idlistetableauDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ListetableauDistrict[ idlistetableauDistrict=" + idlistetableauDistrict + " ]";
    }
    
}
