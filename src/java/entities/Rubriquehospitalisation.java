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
    @NamedQuery(name = "Rubriquehospitalisation.findAll", query = "SELECT r FROM Rubriquehospitalisation r"),
    @NamedQuery(name = "Rubriquehospitalisation.findByIdrubriquehospitalisation", query = "SELECT r FROM Rubriquehospitalisation r WHERE r.idrubriquehospitalisation = :idrubriquehospitalisation"),
    @NamedQuery(name = "Rubriquehospitalisation.findByCode", query = "SELECT r FROM Rubriquehospitalisation r WHERE r.code = :code"),
    @NamedQuery(name = "Rubriquehospitalisation.findByNomFr", query = "SELECT r FROM Rubriquehospitalisation r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Rubriquehospitalisation.findByNomEn", query = "SELECT r FROM Rubriquehospitalisation r WHERE r.nomEn = :nomEn")})
public class Rubriquehospitalisation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquehospitalisation;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idrubriquehospitalisation", fetch = FetchType.LAZY)
    private List<Hospitalisationdistrict> hospitalisationdistrictList;

    public Rubriquehospitalisation() {
    }

    public Rubriquehospitalisation(Integer idrubriquehospitalisation) {
        this.idrubriquehospitalisation = idrubriquehospitalisation;
    }

    public Integer getIdrubriquehospitalisation() {
        return idrubriquehospitalisation;
    }

    public void setIdrubriquehospitalisation(Integer idrubriquehospitalisation) {
        this.idrubriquehospitalisation = idrubriquehospitalisation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public List<Hospitalisationdistrict> getHospitalisationdistrictList() {
        return hospitalisationdistrictList;
    }

    public void setHospitalisationdistrictList(List<Hospitalisationdistrict> hospitalisationdistrictList) {
        this.hospitalisationdistrictList = hospitalisationdistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquehospitalisation != null ? idrubriquehospitalisation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquehospitalisation)) {
            return false;
        }
        Rubriquehospitalisation other = (Rubriquehospitalisation) object;
        if ((this.idrubriquehospitalisation == null && other.idrubriquehospitalisation != null) || (this.idrubriquehospitalisation != null && !this.idrubriquehospitalisation.equals(other.idrubriquehospitalisation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquehospitalisation[ idrubriquehospitalisation=" + idrubriquehospitalisation + " ]";
    }
    
}
