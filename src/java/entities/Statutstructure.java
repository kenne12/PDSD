/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Statutstructure.findAll", query = "SELECT s FROM Statutstructure s"),
    @NamedQuery(name = "Statutstructure.findByIdstatutstructure", query = "SELECT s FROM Statutstructure s WHERE s.idstatutstructure = :idstatutstructure"),
    @NamedQuery(name = "Statutstructure.findByNomFr", query = "SELECT s FROM Statutstructure s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Statutstructure.findByDesignation", query = "SELECT s FROM Statutstructure s WHERE s.designation = :designation"),
    @NamedQuery(name = "Statutstructure.findByEtat", query = "SELECT s FROM Statutstructure s WHERE s.etat = :etat"),
    @NamedQuery(name = "Statutstructure.findByDateEnregistre", query = "SELECT s FROM Statutstructure s WHERE s.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Statutstructure.findByDerniereModif", query = "SELECT s FROM Statutstructure s WHERE s.derniereModif = :derniereModif"),
    @NamedQuery(name = "Statutstructure.findByNomEn", query = "SELECT s FROM Statutstructure s WHERE s.nomEn = :nomEn")})
public class Statutstructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idstatutstructure;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String designation;
    @Size(max = 50)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idstatutstructure", fetch = FetchType.LAZY)
    private List<Structure> structureList;

    public Statutstructure() {
    }

    public Statutstructure(Integer idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
    }

    public Integer getIdstatutstructure() {
        return idstatutstructure;
    }

    public void setIdstatutstructure(Integer idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateEnregistre() {
        return dateEnregistre;
    }

    public void setDateEnregistre(Date dateEnregistre) {
        this.dateEnregistre = dateEnregistre;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatutstructure != null ? idstatutstructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statutstructure)) {
            return false;
        }
        Statutstructure other = (Statutstructure) object;
        if ((this.idstatutstructure == null && other.idstatutstructure != null) || (this.idstatutstructure != null && !this.idstatutstructure.equals(other.idstatutstructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Statutstructure[ idstatutstructure=" + idstatutstructure + " ]";
    }
    
}
