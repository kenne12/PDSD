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
    @NamedQuery(name = "Airesante.findAll", query = "SELECT a FROM Airesante a"),
    @NamedQuery(name = "Airesante.findByIdairesante", query = "SELECT a FROM Airesante a WHERE a.idairesante = :idairesante"),
    @NamedQuery(name = "Airesante.findByNomFr", query = "SELECT a FROM Airesante a WHERE a.nomFr = :nomFr"),
    @NamedQuery(name = "Airesante.findByNomEn", query = "SELECT a FROM Airesante a WHERE a.nomEn = :nomEn")})
public class Airesante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idairesante;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 255)
    @Column(name = "nom_en")
    private String nomEn;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @OneToMany(mappedBy = "idairesante", fetch = FetchType.LAZY)
    private List<Structure> structureList;

    public Airesante() {
    }

    public Airesante(Integer idairesante) {
        this.idairesante = idairesante;
    }

    public Integer getIdairesante() {
        return idairesante;
    }

    public void setIdairesante(Integer idairesante) {
        this.idairesante = idairesante;
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

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
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
        hash += (idairesante != null ? idairesante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airesante)) {
            return false;
        }
        Airesante other = (Airesante) object;
        if ((this.idairesante == null && other.idairesante != null) || (this.idairesante != null && !this.idairesante.equals(other.idairesante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Airesante[ idairesante=" + idairesante + " ]";
    }
    
}
