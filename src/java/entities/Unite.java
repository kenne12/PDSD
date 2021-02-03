/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Unite.findAll", query = "SELECT u FROM Unite u"),
    @NamedQuery(name = "Unite.findByIdunite", query = "SELECT u FROM Unite u WHERE u.idunite = :idunite"),
    @NamedQuery(name = "Unite.findByNom", query = "SELECT u FROM Unite u WHERE u.nom = :nom"),
    @NamedQuery(name = "Unite.findByCode", query = "SELECT u FROM Unite u WHERE u.code = :code")})
public class Unite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idunite;
    @Size(max = 254)
    private String nom;
    @Size(max = 2147483647)
    private String code;
    @OneToMany(mappedBy = "idunite", fetch = FetchType.LAZY)
    private List<Indicateur> indicateurList;

    public Unite() {
    }

    public Unite(Integer idunite) {
        this.idunite = idunite;
    }

    public Integer getIdunite() {
        return idunite;
    }

    public void setIdunite(Integer idunite) {
        this.idunite = idunite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public List<Indicateur> getIndicateurList() {
        return indicateurList;
    }

    public void setIndicateurList(List<Indicateur> indicateurList) {
        this.indicateurList = indicateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunite != null ? idunite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unite)) {
            return false;
        }
        Unite other = (Unite) object;
        if ((this.idunite == null && other.idunite != null) || (this.idunite != null && !this.idunite.equals(other.idunite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Unite[ idunite=" + idunite + " ]";
    }
    
}
