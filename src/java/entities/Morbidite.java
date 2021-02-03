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
    @NamedQuery(name = "Morbidite.findAll", query = "SELECT m FROM Morbidite m"),
    @NamedQuery(name = "Morbidite.findByIdmorbidite", query = "SELECT m FROM Morbidite m WHERE m.idmorbidite = :idmorbidite"),
    @NamedQuery(name = "Morbidite.findByNomFr", query = "SELECT m FROM Morbidite m WHERE m.nomFr = :nomFr"),
    @NamedQuery(name = "Morbidite.findByCode", query = "SELECT m FROM Morbidite m WHERE m.code = :code"),
    @NamedQuery(name = "Morbidite.findByNomEn", query = "SELECT m FROM Morbidite m WHERE m.nomEn = :nomEn")})
public class Morbidite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmorbidite;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idmorbidite", fetch = FetchType.LAZY)
    private List<Morbiditedistrict> morbiditedistrictList;
    @JoinColumn(name = "idtypepathologie", referencedColumnName = "idtypepathologie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typepathologie idtypepathologie;

    public Morbidite() {
    }

    public Morbidite(Integer idmorbidite) {
        this.idmorbidite = idmorbidite;
    }

    public Integer getIdmorbidite() {
        return idmorbidite;
    }

    public void setIdmorbidite(Integer idmorbidite) {
        this.idmorbidite = idmorbidite;
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
    public List<Morbiditedistrict> getMorbiditedistrictList() {
        return morbiditedistrictList;
    }

    public void setMorbiditedistrictList(List<Morbiditedistrict> morbiditedistrictList) {
        this.morbiditedistrictList = morbiditedistrictList;
    }

    public Typepathologie getIdtypepathologie() {
        return idtypepathologie;
    }

    public void setIdtypepathologie(Typepathologie idtypepathologie) {
        this.idtypepathologie = idtypepathologie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmorbidite != null ? idmorbidite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Morbidite)) {
            return false;
        }
        Morbidite other = (Morbidite) object;
        if ((this.idmorbidite == null && other.idmorbidite != null) || (this.idmorbidite != null && !this.idmorbidite.equals(other.idmorbidite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Morbidite[ idmorbidite=" + idmorbidite + " ]";
    }
    
}
