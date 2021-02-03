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
    @NamedQuery(name = "Rubriquemortalite.findAll", query = "SELECT r FROM Rubriquemortalite r"),
    @NamedQuery(name = "Rubriquemortalite.findByIdrubriquemortalite", query = "SELECT r FROM Rubriquemortalite r WHERE r.idrubriquemortalite = :idrubriquemortalite"),
    @NamedQuery(name = "Rubriquemortalite.findByCode", query = "SELECT r FROM Rubriquemortalite r WHERE r.code = :code"),
    @NamedQuery(name = "Rubriquemortalite.findByNomFr", query = "SELECT r FROM Rubriquemortalite r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Rubriquemortalite.findByNomEn", query = "SELECT r FROM Rubriquemortalite r WHERE r.nomEn = :nomEn")})
public class Rubriquemortalite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquemortalite;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idrubriquemortalite", fetch = FetchType.LAZY)
    private List<Mortalitedistrict> mortalitedistrictList;

    public Rubriquemortalite() {
    }

    public Rubriquemortalite(Integer idrubriquemortalite) {
        this.idrubriquemortalite = idrubriquemortalite;
    }

    public Integer getIdrubriquemortalite() {
        return idrubriquemortalite;
    }

    public void setIdrubriquemortalite(Integer idrubriquemortalite) {
        this.idrubriquemortalite = idrubriquemortalite;
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
    public List<Mortalitedistrict> getMortalitedistrictList() {
        return mortalitedistrictList;
    }

    public void setMortalitedistrictList(List<Mortalitedistrict> mortalitedistrictList) {
        this.mortalitedistrictList = mortalitedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquemortalite != null ? idrubriquemortalite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquemortalite)) {
            return false;
        }
        Rubriquemortalite other = (Rubriquemortalite) object;
        if ((this.idrubriquemortalite == null && other.idrubriquemortalite != null) || (this.idrubriquemortalite != null && !this.idrubriquemortalite.equals(other.idrubriquemortalite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquemortalite[ idrubriquemortalite=" + idrubriquemortalite + " ]";
    }
    
}
