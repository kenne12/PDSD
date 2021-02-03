/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Population.findAll", query = "SELECT p FROM Population p"),
    @NamedQuery(name = "Population.findByIdpopulation", query = "SELECT p FROM Population p WHERE p.idpopulation = :idpopulation"),
    @NamedQuery(name = "Population.findByValeurpopulationdistrict", query = "SELECT p FROM Population p WHERE p.valeurpopulationdistrict = :valeurpopulationdistrict"),
    @NamedQuery(name = "Population.findByValeurpopulationrubrique", query = "SELECT p FROM Population p WHERE p.valeurpopulationrubrique = :valeurpopulationrubrique")})
public class Population implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idpopulation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeurpopulationdistrict;
    private Double valeurpopulationrubrique;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idrubriquepopulation", referencedColumnName = "idrubriquepopulation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquepopulation idrubriquepopulation;

    public Population() {
    }

    public Population(Long idpopulation) {
        this.idpopulation = idpopulation;
    }

    public Long getIdpopulation() {
        return idpopulation;
    }

    public void setIdpopulation(Long idpopulation) {
        this.idpopulation = idpopulation;
    }

    public Double getValeurpopulationdistrict() {
        return valeurpopulationdistrict;
    }

    public void setValeurpopulationdistrict(Double valeurpopulationdistrict) {
        this.valeurpopulationdistrict = valeurpopulationdistrict;
    }

    public Double getValeurpopulationrubrique() {
        return valeurpopulationrubrique;
    }

    public void setValeurpopulationrubrique(Double valeurpopulationrubrique) {
        this.valeurpopulationrubrique = valeurpopulationrubrique;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Rubriquepopulation getIdrubriquepopulation() {
        return idrubriquepopulation;
    }

    public void setIdrubriquepopulation(Rubriquepopulation idrubriquepopulation) {
        this.idrubriquepopulation = idrubriquepopulation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpopulation != null ? idpopulation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Population)) {
            return false;
        }
        Population other = (Population) object;
        if ((this.idpopulation == null && other.idpopulation != null) || (this.idpopulation != null && !this.idpopulation.equals(other.idpopulation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Population[ idpopulation=" + idpopulation + " ]";
    }
    
}
