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
    @NamedQuery(name = "Profilpersonnel.findAll", query = "SELECT p FROM Profilpersonnel p"),
    @NamedQuery(name = "Profilpersonnel.findByIdprofilpersonnel", query = "SELECT p FROM Profilpersonnel p WHERE p.idprofilpersonnel = :idprofilpersonnel"),
    @NamedQuery(name = "Profilpersonnel.findByNomFr", query = "SELECT p FROM Profilpersonnel p WHERE p.nomFr = :nomFr"),
    @NamedQuery(name = "Profilpersonnel.findByNomEn", query = "SELECT p FROM Profilpersonnel p WHERE p.nomEn = :nomEn"),
    @NamedQuery(name = "Profilpersonnel.findByDistrict", query = "SELECT p FROM Profilpersonnel p WHERE p.district = :district"),
    @NamedQuery(name = "Profilpersonnel.findByRegion", query = "SELECT p FROM Profilpersonnel p WHERE p.region = :region")})
public class Profilpersonnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idprofilpersonnel;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean district;
    private Boolean region;
    @OneToMany(mappedBy = "idprofilpersonnel", fetch = FetchType.LAZY)
    private List<Rhs> rhsList;

    public Profilpersonnel() {
    }

    public Profilpersonnel(Integer idprofilpersonnel) {
        this.idprofilpersonnel = idprofilpersonnel;
    }

    public Integer getIdprofilpersonnel() {
        return idprofilpersonnel;
    }

    public void setIdprofilpersonnel(Integer idprofilpersonnel) {
        this.idprofilpersonnel = idprofilpersonnel;
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
    public List<Rhs> getRhsList() {
        return rhsList;
    }

    public void setRhsList(List<Rhs> rhsList) {
        this.rhsList = rhsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofilpersonnel != null ? idprofilpersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profilpersonnel)) {
            return false;
        }
        Profilpersonnel other = (Profilpersonnel) object;
        if ((this.idprofilpersonnel == null && other.idprofilpersonnel != null) || (this.idprofilpersonnel != null && !this.idprofilpersonnel.equals(other.idprofilpersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profilpersonnel[ idprofilpersonnel=" + idprofilpersonnel + " ]";
    }
    
}
