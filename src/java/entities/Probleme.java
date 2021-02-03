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
    @NamedQuery(name = "Probleme.findAll", query = "SELECT p FROM Probleme p"),
    @NamedQuery(name = "Probleme.findByIdprobleme", query = "SELECT p FROM Probleme p WHERE p.idprobleme = :idprobleme"),
    @NamedQuery(name = "Probleme.findByNom", query = "SELECT p FROM Probleme p WHERE p.nom = :nom"),
    @NamedQuery(name = "Probleme.findByCause", query = "SELECT p FROM Probleme p WHERE p.cause = :cause"),
    @NamedQuery(name = "Probleme.findByObjectif", query = "SELECT p FROM Probleme p WHERE p.objectif = :objectif"),
    @NamedQuery(name = "Probleme.findByFaible", query = "SELECT p FROM Probleme p WHERE p.faible = :faible"),
    @NamedQuery(name = "Probleme.findByTotalpoint", query = "SELECT p FROM Probleme p WHERE p.totalpoint = :totalpoint")})
public class Probleme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idprobleme;
    @Size(max = 2147483647)
    private String nom;
    @Size(max = 2147483647)
    private String cause;
    @Size(max = 2147483647)
    private String objectif;
    private Boolean faible;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double totalpoint;
    @OneToMany(mappedBy = "idprobleme", fetch = FetchType.LAZY)
    private List<Notationprobleme> notationproblemeList;
    @OneToMany(mappedBy = "idprobleme", fetch = FetchType.LAZY)
    private List<Cible> cibleList;
    @JoinColumn(name = "idindicateur_district", referencedColumnName = "idindicateur_district")
    @ManyToOne(fetch = FetchType.LAZY)
    private IndicateurDistrict idindicateurDistrict;
    @OneToMany(mappedBy = "idprobleme", fetch = FetchType.LAZY)
    private List<Activite> activiteList;

    public Probleme() {
    }

    public Probleme(Integer idprobleme) {
        this.idprobleme = idprobleme;
    }

    public Integer getIdprobleme() {
        return idprobleme;
    }

    public void setIdprobleme(Integer idprobleme) {
        this.idprobleme = idprobleme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Boolean getFaible() {
        return faible;
    }

    public void setFaible(Boolean faible) {
        this.faible = faible;
    }

    public Double getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(Double totalpoint) {
        this.totalpoint = totalpoint;
    }

    @XmlTransient
    public List<Notationprobleme> getNotationproblemeList() {
        return notationproblemeList;
    }

    public void setNotationproblemeList(List<Notationprobleme> notationproblemeList) {
        this.notationproblemeList = notationproblemeList;
    }

    @XmlTransient
    public List<Cible> getCibleList() {
        return cibleList;
    }

    public void setCibleList(List<Cible> cibleList) {
        this.cibleList = cibleList;
    }

    public IndicateurDistrict getIdindicateurDistrict() {
        return idindicateurDistrict;
    }

    public void setIdindicateurDistrict(IndicateurDistrict idindicateurDistrict) {
        this.idindicateurDistrict = idindicateurDistrict;
    }

    @XmlTransient
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprobleme != null ? idprobleme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Probleme)) {
            return false;
        }
        Probleme other = (Probleme) object;
        if ((this.idprobleme == null && other.idprobleme != null) || (this.idprobleme != null && !this.idprobleme.equals(other.idprobleme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Probleme[ idprobleme=" + idprobleme + " ]";
    }
    
}
