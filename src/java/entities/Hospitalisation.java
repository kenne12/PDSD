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
    @NamedQuery(name = "Hospitalisation.findAll", query = "SELECT h FROM Hospitalisation h"),
    @NamedQuery(name = "Hospitalisation.findByIdhospitalisation", query = "SELECT h FROM Hospitalisation h WHERE h.idhospitalisation = :idhospitalisation"),
    @NamedQuery(name = "Hospitalisation.findByNomFr", query = "SELECT h FROM Hospitalisation h WHERE h.nomFr = :nomFr"),
    @NamedQuery(name = "Hospitalisation.findByCode", query = "SELECT h FROM Hospitalisation h WHERE h.code = :code"),
    @NamedQuery(name = "Hospitalisation.findByNomEn", query = "SELECT h FROM Hospitalisation h WHERE h.nomEn = :nomEn")})
public class Hospitalisation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idhospitalisation;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idhospitalisation", fetch = FetchType.LAZY)
    private List<Hospitalisationdistrict> hospitalisationdistrictList;

    public Hospitalisation() {
    }

    public Hospitalisation(Integer idhospitalisation) {
        this.idhospitalisation = idhospitalisation;
    }

    public Integer getIdhospitalisation() {
        return idhospitalisation;
    }

    public void setIdhospitalisation(Integer idhospitalisation) {
        this.idhospitalisation = idhospitalisation;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
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

    @XmlTransient
    public List<Hospitalisationdistrict> getHospitalisationdistrictList() {
        return hospitalisationdistrictList;
    }

    public void setHospitalisationdistrictList(List<Hospitalisationdistrict> hospitalisationdistrictList) {
        this.hospitalisationdistrictList = hospitalisationdistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhospitalisation != null ? idhospitalisation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospitalisation)) {
            return false;
        }
        Hospitalisation other = (Hospitalisation) object;
        if ((this.idhospitalisation == null && other.idhospitalisation != null) || (this.idhospitalisation != null && !this.idhospitalisation.equals(other.idhospitalisation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hospitalisation[ idhospitalisation=" + idhospitalisation + " ]";
    }
    
}
