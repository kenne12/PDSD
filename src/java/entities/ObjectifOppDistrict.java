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
@Table(name = "objectif_opp_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjectifOppDistrict.findAll", query = "SELECT o FROM ObjectifOppDistrict o"),
    @NamedQuery(name = "ObjectifOppDistrict.findByIdobjectifOppDistrict", query = "SELECT o FROM ObjectifOppDistrict o WHERE o.idobjectifOppDistrict = :idobjectifOppDistrict"),
    @NamedQuery(name = "ObjectifOppDistrict.findByObjectif", query = "SELECT o FROM ObjectifOppDistrict o WHERE o.objectif = :objectif")})
public class ObjectifOppDistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idobjectif_opp_district")
    private Long idobjectifOppDistrict;
    @Size(max = 2000)
    private String objectif;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idintervention", referencedColumnName = "idinterventionpnds")
    @ManyToOne(fetch = FetchType.LAZY)
    private Interventionpnds idintervention;
    @OneToMany(mappedBy = "ididobjectifOpp", fetch = FetchType.LAZY)
    private List<Activite> activiteList;

    public ObjectifOppDistrict() {
    }

    public ObjectifOppDistrict(Long idobjectifOppDistrict) {
        this.idobjectifOppDistrict = idobjectifOppDistrict;
    }

    public Long getIdobjectifOppDistrict() {
        return idobjectifOppDistrict;
    }

    public void setIdobjectifOppDistrict(Long idobjectifOppDistrict) {
        this.idobjectifOppDistrict = idobjectifOppDistrict;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Interventionpnds getIdintervention() {
        return idintervention;
    }

    public void setIdintervention(Interventionpnds idintervention) {
        this.idintervention = idintervention;
    }

    @XmlTransient
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobjectifOppDistrict != null ? idobjectifOppDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjectifOppDistrict)) {
            return false;
        }
        ObjectifOppDistrict other = (ObjectifOppDistrict) object;
        if ((this.idobjectifOppDistrict == null && other.idobjectifOppDistrict != null) || (this.idobjectifOppDistrict != null && !this.idobjectifOppDistrict.equals(other.idobjectifOppDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ObjectifOppDistrict[ idobjectifOppDistrict=" + idobjectifOppDistrict + " ]";
    }
    
}
