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
    @NamedQuery(name = "Rue.findAll", query = "SELECT r FROM Rue r"),
    @NamedQuery(name = "Rue.findByIdrue", query = "SELECT r FROM Rue r WHERE r.idrue = :idrue"),
    @NamedQuery(name = "Rue.findByNom", query = "SELECT r FROM Rue r WHERE r.nom = :nom")})
public class Rue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrue;
    @Size(max = 254)
    private String nom;
    @JoinColumn(name = "idquartier", referencedColumnName = "idquartier")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quartier idquartier;
    @OneToMany(mappedBy = "idrue", fetch = FetchType.LAZY)
    private List<Adresse> adresseList;

    public Rue() {
    }

    public Rue(Integer idrue) {
        this.idrue = idrue;
    }

    public Integer getIdrue() {
        return idrue;
    }

    public void setIdrue(Integer idrue) {
        this.idrue = idrue;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Quartier getIdquartier() {
        return idquartier;
    }

    public void setIdquartier(Quartier idquartier) {
        this.idquartier = idquartier;
    }

    @XmlTransient
    public List<Adresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<Adresse> adresseList) {
        this.adresseList = adresseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrue != null ? idrue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rue)) {
            return false;
        }
        Rue other = (Rue) object;
        if ((this.idrue == null && other.idrue != null) || (this.idrue != null && !this.idrue.equals(other.idrue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rue[ idrue=" + idrue + " ]";
    }
    
}
