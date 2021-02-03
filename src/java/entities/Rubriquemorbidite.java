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
    @NamedQuery(name = "Rubriquemorbidite.findAll", query = "SELECT r FROM Rubriquemorbidite r"),
    @NamedQuery(name = "Rubriquemorbidite.findByIdrubriquemorbidite", query = "SELECT r FROM Rubriquemorbidite r WHERE r.idrubriquemorbidite = :idrubriquemorbidite"),
    @NamedQuery(name = "Rubriquemorbidite.findByCode", query = "SELECT r FROM Rubriquemorbidite r WHERE r.code = :code"),
    @NamedQuery(name = "Rubriquemorbidite.findByNomFr", query = "SELECT r FROM Rubriquemorbidite r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Rubriquemorbidite.findByNomEn", query = "SELECT r FROM Rubriquemorbidite r WHERE r.nomEn = :nomEn")})
public class Rubriquemorbidite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquemorbidite;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idrubriquemorbidite", fetch = FetchType.LAZY)
    private List<Morbiditedistrict> morbiditedistrictList;
    @OneToMany(mappedBy = "idrubriquemorbidite", fetch = FetchType.LAZY)
    private List<Patologiedistrict> patologiedistrictList;

    public Rubriquemorbidite() {
    }

    public Rubriquemorbidite(Integer idrubriquemorbidite) {
        this.idrubriquemorbidite = idrubriquemorbidite;
    }

    public Integer getIdrubriquemorbidite() {
        return idrubriquemorbidite;
    }

    public void setIdrubriquemorbidite(Integer idrubriquemorbidite) {
        this.idrubriquemorbidite = idrubriquemorbidite;
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
    public List<Morbiditedistrict> getMorbiditedistrictList() {
        return morbiditedistrictList;
    }

    public void setMorbiditedistrictList(List<Morbiditedistrict> morbiditedistrictList) {
        this.morbiditedistrictList = morbiditedistrictList;
    }

    @XmlTransient
    public List<Patologiedistrict> getPatologiedistrictList() {
        return patologiedistrictList;
    }

    public void setPatologiedistrictList(List<Patologiedistrict> patologiedistrictList) {
        this.patologiedistrictList = patologiedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquemorbidite != null ? idrubriquemorbidite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquemorbidite)) {
            return false;
        }
        Rubriquemorbidite other = (Rubriquemorbidite) object;
        if ((this.idrubriquemorbidite == null && other.idrubriquemorbidite != null) || (this.idrubriquemorbidite != null && !this.idrubriquemorbidite.equals(other.idrubriquemorbidite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquemorbidite[ idrubriquemorbidite=" + idrubriquemorbidite + " ]";
    }
    
}
