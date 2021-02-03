/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Departement.findAll", query = "SELECT d FROM Departement d"),
    @NamedQuery(name = "Departement.findByIddepart", query = "SELECT d FROM Departement d WHERE d.iddepart = :iddepart"),
    @NamedQuery(name = "Departement.findByNom", query = "SELECT d FROM Departement d WHERE d.nom = :nom"),
    @NamedQuery(name = "Departement.findByCoordonnegps", query = "SELECT d FROM Departement d WHERE d.coordonnegps = :coordonnegps")})
public class Departement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer iddepart;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String coordonnegps;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddepart", fetch = FetchType.LAZY)
    private List<Arrondissement> arrondissementList;

    public Departement() {
    }

    public Departement(Integer iddepart) {
        this.iddepart = iddepart;
    }

    public Integer getIddepart() {
        return iddepart;
    }

    public void setIddepart(Integer iddepart) {
        this.iddepart = iddepart;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoordonnegps() {
        return coordonnegps;
    }

    public void setCoordonnegps(String coordonnegps) {
        this.coordonnegps = coordonnegps;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @XmlTransient
    public List<Arrondissement> getArrondissementList() {
        return arrondissementList;
    }

    public void setArrondissementList(List<Arrondissement> arrondissementList) {
        this.arrondissementList = arrondissementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepart != null ? iddepart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.iddepart == null && other.iddepart != null) || (this.iddepart != null && !this.iddepart.equals(other.iddepart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Departement[ iddepart=" + iddepart + " ]";
    }
    
}
