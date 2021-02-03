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
    @NamedQuery(name = "Observation.findAll", query = "SELECT o FROM Observation o"),
    @NamedQuery(name = "Observation.findByIdobservation", query = "SELECT o FROM Observation o WHERE o.idobservation = :idobservation"),
    @NamedQuery(name = "Observation.findByValeurFr", query = "SELECT o FROM Observation o WHERE o.valeurFr = :valeurFr"),
    @NamedQuery(name = "Observation.findByValeurEn", query = "SELECT o FROM Observation o WHERE o.valeurEn = :valeurEn")})
public class Observation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idobservation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "valeur_fr")
    private String valeurFr;
    @Size(max = 255)
    @Column(name = "valeur_en")
    private String valeurEn;
    @OneToMany(mappedBy = "idobservation", fetch = FetchType.LAZY)
    private List<IndicateurDistrict> indicateurDistrictList;

    public Observation() {
    }

    public Observation(Integer idobservation) {
        this.idobservation = idobservation;
    }

    public Observation(Integer idobservation, String valeurFr) {
        this.idobservation = idobservation;
        this.valeurFr = valeurFr;
    }

    public Integer getIdobservation() {
        return idobservation;
    }

    public void setIdobservation(Integer idobservation) {
        this.idobservation = idobservation;
    }

    public String getValeurFr() {
        return valeurFr;
    }

    public void setValeurFr(String valeurFr) {
        this.valeurFr = valeurFr;
    }

    public String getValeurEn() {
        return valeurEn;
    }

    public void setValeurEn(String valeurEn) {
        this.valeurEn = valeurEn;
    }

    @XmlTransient
    public List<IndicateurDistrict> getIndicateurDistrictList() {
        return indicateurDistrictList;
    }

    public void setIndicateurDistrictList(List<IndicateurDistrict> indicateurDistrictList) {
        this.indicateurDistrictList = indicateurDistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobservation != null ? idobservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Observation)) {
            return false;
        }
        Observation other = (Observation) object;
        if ((this.idobservation == null && other.idobservation != null) || (this.idobservation != null && !this.idobservation.equals(other.idobservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Observation[ idobservation=" + idobservation + " ]";
    }
    
}
