/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouchard.findAll", query = "SELECT m FROM Mouchard m"),
    @NamedQuery(name = "Mouchard.findByIdoperation", query = "SELECT m FROM Mouchard m WHERE m.idoperation = :idoperation"),
    @NamedQuery(name = "Mouchard.findByAction", query = "SELECT m FROM Mouchard m WHERE m.action = :action"),
    @NamedQuery(name = "Mouchard.findByDateaction", query = "SELECT m FROM Mouchard m WHERE m.dateaction = :dateaction"),
    @NamedQuery(name = "Mouchard.findByHeure", query = "SELECT m FROM Mouchard m WHERE m.heure = :heure")})
public class Mouchard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idoperation;
    @Size(max = 254)
    private String action;
    @Temporal(TemporalType.DATE)
    private Date dateaction;
    @Temporal(TemporalType.TIME)
    private Date heure;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur idutilisateur;

    public Mouchard() {
    }

    public Mouchard(Long idoperation) {
        this.idoperation = idoperation;
    }

    public Long getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(Long idoperation) {
        this.idoperation = idoperation;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDateaction() {
        return dateaction;
    }

    public void setDateaction(Date dateaction) {
        this.dateaction = dateaction;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public Utilisateur getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperation != null ? idoperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mouchard)) {
            return false;
        }
        Mouchard other = (Mouchard) object;
        if ((this.idoperation == null && other.idoperation != null) || (this.idoperation != null && !this.idoperation.equals(other.idoperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mouchard[ idoperation=" + idoperation + " ]";
    }
    
}
