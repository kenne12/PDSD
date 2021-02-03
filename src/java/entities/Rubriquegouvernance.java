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
    @NamedQuery(name = "Rubriquegouvernance.findAll", query = "SELECT r FROM Rubriquegouvernance r"),
    @NamedQuery(name = "Rubriquegouvernance.findByIdrubriquegouvernance", query = "SELECT r FROM Rubriquegouvernance r WHERE r.idrubriquegouvernance = :idrubriquegouvernance"),
    @NamedQuery(name = "Rubriquegouvernance.findByNomFr", query = "SELECT r FROM Rubriquegouvernance r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Rubriquegouvernance.findByCode", query = "SELECT r FROM Rubriquegouvernance r WHERE r.code = :code"),
    @NamedQuery(name = "Rubriquegouvernance.findByNomEn", query = "SELECT r FROM Rubriquegouvernance r WHERE r.nomEn = :nomEn")})
public class Rubriquegouvernance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquegouvernance;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idrubriquegouvernance", fetch = FetchType.LAZY)
    private List<Gouvernancedistrict> gouvernancedistrictList;

    public Rubriquegouvernance() {
    }

    public Rubriquegouvernance(Integer idrubriquegouvernance) {
        this.idrubriquegouvernance = idrubriquegouvernance;
    }

    public Integer getIdrubriquegouvernance() {
        return idrubriquegouvernance;
    }

    public void setIdrubriquegouvernance(Integer idrubriquegouvernance) {
        this.idrubriquegouvernance = idrubriquegouvernance;
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
    public List<Gouvernancedistrict> getGouvernancedistrictList() {
        return gouvernancedistrictList;
    }

    public void setGouvernancedistrictList(List<Gouvernancedistrict> gouvernancedistrictList) {
        this.gouvernancedistrictList = gouvernancedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquegouvernance != null ? idrubriquegouvernance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquegouvernance)) {
            return false;
        }
        Rubriquegouvernance other = (Rubriquegouvernance) object;
        if ((this.idrubriquegouvernance == null && other.idrubriquegouvernance != null) || (this.idrubriquegouvernance != null && !this.idrubriquegouvernance.equals(other.idrubriquegouvernance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquegouvernance[ idrubriquegouvernance=" + idrubriquegouvernance + " ]";
    }
    
}
