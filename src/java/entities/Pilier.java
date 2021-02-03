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
    @NamedQuery(name = "Pilier.findAll", query = "SELECT p FROM Pilier p"),
    @NamedQuery(name = "Pilier.findByIdpilier", query = "SELECT p FROM Pilier p WHERE p.idpilier = :idpilier"),
    @NamedQuery(name = "Pilier.findByNomFr", query = "SELECT p FROM Pilier p WHERE p.nomFr = :nomFr"),
    @NamedQuery(name = "Pilier.findByNomEn", query = "SELECT p FROM Pilier p WHERE p.nomEn = :nomEn")})
public class Pilier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idpilier;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idpilier", fetch = FetchType.LAZY)
    private List<Ffom> ffomList;

    public Pilier() {
    }

    public Pilier(Integer idpilier) {
        this.idpilier = idpilier;
    }

    public Integer getIdpilier() {
        return idpilier;
    }

    public void setIdpilier(Integer idpilier) {
        this.idpilier = idpilier;
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
    public List<Ffom> getFfomList() {
        return ffomList;
    }

    public void setFfomList(List<Ffom> ffomList) {
        this.ffomList = ffomList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpilier != null ? idpilier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pilier)) {
            return false;
        }
        Pilier other = (Pilier) object;
        if ((this.idpilier == null && other.idpilier != null) || (this.idpilier != null && !this.idpilier.equals(other.idpilier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pilier[ idpilier=" + idpilier + " ]";
    }
    
}
