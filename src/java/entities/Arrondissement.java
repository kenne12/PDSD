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
    @NamedQuery(name = "Arrondissement.findAll", query = "SELECT a FROM Arrondissement a"),
    @NamedQuery(name = "Arrondissement.findByIdarrondi", query = "SELECT a FROM Arrondissement a WHERE a.idarrondi = :idarrondi"),
    @NamedQuery(name = "Arrondissement.findByNom", query = "SELECT a FROM Arrondissement a WHERE a.nom = :nom"),
    @NamedQuery(name = "Arrondissement.findByCoordonneegps", query = "SELECT a FROM Arrondissement a WHERE a.coordonneegps = :coordonneegps")})
public class Arrondissement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idarrondi;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String coordonneegps;
    @JoinColumn(name = "iddepart", referencedColumnName = "iddepart")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departement iddepart;
    @OneToMany(mappedBy = "idarrondissement", fetch = FetchType.LAZY)
    private List<Quartier> quartierList;

    public Arrondissement() {
    }

    public Arrondissement(Integer idarrondi) {
        this.idarrondi = idarrondi;
    }

    public Integer getIdarrondi() {
        return idarrondi;
    }

    public void setIdarrondi(Integer idarrondi) {
        this.idarrondi = idarrondi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoordonneegps() {
        return coordonneegps;
    }

    public void setCoordonneegps(String coordonneegps) {
        this.coordonneegps = coordonneegps;
    }

    public Departement getIddepart() {
        return iddepart;
    }

    public void setIddepart(Departement iddepart) {
        this.iddepart = iddepart;
    }

    @XmlTransient
    public List<Quartier> getQuartierList() {
        return quartierList;
    }

    public void setQuartierList(List<Quartier> quartierList) {
        this.quartierList = quartierList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarrondi != null ? idarrondi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arrondissement)) {
            return false;
        }
        Arrondissement other = (Arrondissement) object;
        if ((this.idarrondi == null && other.idarrondi != null) || (this.idarrondi != null && !this.idarrondi.equals(other.idarrondi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Arrondissement[ idarrondi=" + idarrondi + " ]";
    }
    
}
