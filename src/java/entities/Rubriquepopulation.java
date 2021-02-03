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
    @NamedQuery(name = "Rubriquepopulation.findAll", query = "SELECT r FROM Rubriquepopulation r"),
    @NamedQuery(name = "Rubriquepopulation.findByIdrubriquepopulation", query = "SELECT r FROM Rubriquepopulation r WHERE r.idrubriquepopulation = :idrubriquepopulation"),
    @NamedQuery(name = "Rubriquepopulation.findByCode", query = "SELECT r FROM Rubriquepopulation r WHERE r.code = :code"),
    @NamedQuery(name = "Rubriquepopulation.findByNomFr", query = "SELECT r FROM Rubriquepopulation r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Rubriquepopulation.findByPourcentage", query = "SELECT r FROM Rubriquepopulation r WHERE r.pourcentage = :pourcentage"),
    @NamedQuery(name = "Rubriquepopulation.findByNomEn", query = "SELECT r FROM Rubriquepopulation r WHERE r.nomEn = :nomEn")})
public class Rubriquepopulation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquepopulation;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double pourcentage;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idrubriquepopulation", fetch = FetchType.LAZY)
    private List<Population> populationList;

    public Rubriquepopulation() {
    }

    public Rubriquepopulation(Integer idrubriquepopulation) {
        this.idrubriquepopulation = idrubriquepopulation;
    }

    public Integer getIdrubriquepopulation() {
        return idrubriquepopulation;
    }

    public void setIdrubriquepopulation(Integer idrubriquepopulation) {
        this.idrubriquepopulation = idrubriquepopulation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Population> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(List<Population> populationList) {
        this.populationList = populationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquepopulation != null ? idrubriquepopulation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquepopulation)) {
            return false;
        }
        Rubriquepopulation other = (Rubriquepopulation) object;
        if ((this.idrubriquepopulation == null && other.idrubriquepopulation != null) || (this.idrubriquepopulation != null && !this.idrubriquepopulation.equals(other.idrubriquepopulation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquepopulation[ idrubriquepopulation=" + idrubriquepopulation + " ]";
    }
    
}
