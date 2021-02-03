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
@Table(name = "resultat_attendu_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultatAttenduDistrict.findAll", query = "SELECT r FROM ResultatAttenduDistrict r"),
    @NamedQuery(name = "ResultatAttenduDistrict.findByIdresultatAttenduDistrict", query = "SELECT r FROM ResultatAttenduDistrict r WHERE r.idresultatAttenduDistrict = :idresultatAttenduDistrict"),
    @NamedQuery(name = "ResultatAttenduDistrict.findByResultat", query = "SELECT r FROM ResultatAttenduDistrict r WHERE r.resultat = :resultat")})
public class ResultatAttenduDistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idresultat_attendu_district")
    private Long idresultatAttenduDistrict;
    @Size(max = 2000)
    private String resultat;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @OneToMany(mappedBy = "idresultatattendu", fetch = FetchType.LAZY)
    private List<Activite> activiteList;

    public ResultatAttenduDistrict() {
    }

    public ResultatAttenduDistrict(Long idresultatAttenduDistrict) {
        this.idresultatAttenduDistrict = idresultatAttenduDistrict;
    }

    public Long getIdresultatAttenduDistrict() {
        return idresultatAttenduDistrict;
    }

    public void setIdresultatAttenduDistrict(Long idresultatAttenduDistrict) {
        this.idresultatAttenduDistrict = idresultatAttenduDistrict;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
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
        hash += (idresultatAttenduDistrict != null ? idresultatAttenduDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultatAttenduDistrict)) {
            return false;
        }
        ResultatAttenduDistrict other = (ResultatAttenduDistrict) object;
        if ((this.idresultatAttenduDistrict == null && other.idresultatAttenduDistrict != null) || (this.idresultatAttenduDistrict != null && !this.idresultatAttenduDistrict.equals(other.idresultatAttenduDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ResultatAttenduDistrict[ idresultatAttenduDistrict=" + idresultatAttenduDistrict + " ]";
    }
    
}
