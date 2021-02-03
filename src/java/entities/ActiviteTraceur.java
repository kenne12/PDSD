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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "activite_traceur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteTraceur.findAll", query = "SELECT a FROM ActiviteTraceur a"),
    @NamedQuery(name = "ActiviteTraceur.findByIdactiviteTraceur", query = "SELECT a FROM ActiviteTraceur a WHERE a.idactiviteTraceur = :idactiviteTraceur"),
    @NamedQuery(name = "ActiviteTraceur.findByNom", query = "SELECT a FROM ActiviteTraceur a WHERE a.nom = :nom")})
public class ActiviteTraceur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_traceur")
    private Long idactiviteTraceur;
    @Size(max = 2147483647)
    private String nom;
    @JoinColumn(name = "idinterventionpnds", referencedColumnName = "idinterventionpnds")
    @ManyToOne(fetch = FetchType.LAZY)
    private Interventionpnds idinterventionpnds;
    @OneToMany(mappedBy = "idactiviteTraceur", fetch = FetchType.LAZY)
    private List<ActiviteTraceurPdsd> activiteTraceurPdsdList;

    public ActiviteTraceur() {
    }

    public ActiviteTraceur(Long idactiviteTraceur) {
        this.idactiviteTraceur = idactiviteTraceur;
    }

    public Long getIdactiviteTraceur() {
        return idactiviteTraceur;
    }

    public void setIdactiviteTraceur(Long idactiviteTraceur) {
        this.idactiviteTraceur = idactiviteTraceur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Interventionpnds getIdinterventionpnds() {
        return idinterventionpnds;
    }

    public void setIdinterventionpnds(Interventionpnds idinterventionpnds) {
        this.idinterventionpnds = idinterventionpnds;
    }

    @XmlTransient
    public List<ActiviteTraceurPdsd> getActiviteTraceurPdsdList() {
        return activiteTraceurPdsdList;
    }

    public void setActiviteTraceurPdsdList(List<ActiviteTraceurPdsd> activiteTraceurPdsdList) {
        this.activiteTraceurPdsdList = activiteTraceurPdsdList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactiviteTraceur != null ? idactiviteTraceur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteTraceur)) {
            return false;
        }
        ActiviteTraceur other = (ActiviteTraceur) object;
        if ((this.idactiviteTraceur == null && other.idactiviteTraceur != null) || (this.idactiviteTraceur != null && !this.idactiviteTraceur.equals(other.idactiviteTraceur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteTraceur[ idactiviteTraceur=" + idactiviteTraceur + " ]";
    }
    
}
