/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Populationfosa.findAll", query = "SELECT p FROM Populationfosa p"),
    @NamedQuery(name = "Populationfosa.findByIdpopulationfosa", query = "SELECT p FROM Populationfosa p WHERE p.idpopulationfosa = :idpopulationfosa"),
    @NamedQuery(name = "Populationfosa.findByPopulationcouverte", query = "SELECT p FROM Populationfosa p WHERE p.populationcouverte = :populationcouverte"),
    @NamedQuery(name = "Populationfosa.findByPrcds", query = "SELECT p FROM Populationfosa p WHERE p.prcds = :prcds")})
public class Populationfosa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idpopulationfosa;
    private BigInteger populationcouverte;
    private Boolean prcds;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Populationfosa() {
    }

    public Populationfosa(Long idpopulationfosa) {
        this.idpopulationfosa = idpopulationfosa;
    }

    public Long getIdpopulationfosa() {
        return idpopulationfosa;
    }

    public void setIdpopulationfosa(Long idpopulationfosa) {
        this.idpopulationfosa = idpopulationfosa;
    }

    public BigInteger getPopulationcouverte() {
        return populationcouverte;
    }

    public void setPopulationcouverte(BigInteger populationcouverte) {
        this.populationcouverte = populationcouverte;
    }

    public Boolean getPrcds() {
        return prcds;
    }

    public void setPrcds(Boolean prcds) {
        this.prcds = prcds;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpopulationfosa != null ? idpopulationfosa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Populationfosa)) {
            return false;
        }
        Populationfosa other = (Populationfosa) object;
        if ((this.idpopulationfosa == null && other.idpopulationfosa != null) || (this.idpopulationfosa != null && !this.idpopulationfosa.equals(other.idpopulationfosa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Populationfosa[ idpopulationfosa=" + idpopulationfosa + " ]";
    }
    
}
