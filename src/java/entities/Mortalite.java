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
    @NamedQuery(name = "Mortalite.findAll", query = "SELECT m FROM Mortalite m"),
    @NamedQuery(name = "Mortalite.findByIdmortalite", query = "SELECT m FROM Mortalite m WHERE m.idmortalite = :idmortalite"),
    @NamedQuery(name = "Mortalite.findByNomFr", query = "SELECT m FROM Mortalite m WHERE m.nomFr = :nomFr"),
    @NamedQuery(name = "Mortalite.findByCode", query = "SELECT m FROM Mortalite m WHERE m.code = :code"),
    @NamedQuery(name = "Mortalite.findByNomEn", query = "SELECT m FROM Mortalite m WHERE m.nomEn = :nomEn")})
public class Mortalite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmortalite;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idmortalite", fetch = FetchType.LAZY)
    private List<Mortalitedistrict> mortalitedistrictList;

    public Mortalite() {
    }

    public Mortalite(Integer idmortalite) {
        this.idmortalite = idmortalite;
    }

    public Integer getIdmortalite() {
        return idmortalite;
    }

    public void setIdmortalite(Integer idmortalite) {
        this.idmortalite = idmortalite;
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
    public List<Mortalitedistrict> getMortalitedistrictList() {
        return mortalitedistrictList;
    }

    public void setMortalitedistrictList(List<Mortalitedistrict> mortalitedistrictList) {
        this.mortalitedistrictList = mortalitedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmortalite != null ? idmortalite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mortalite)) {
            return false;
        }
        Mortalite other = (Mortalite) object;
        if ((this.idmortalite == null && other.idmortalite != null) || (this.idmortalite != null && !this.idmortalite.equals(other.idmortalite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mortalite[ idmortalite=" + idmortalite + " ]";
    }
    
}
