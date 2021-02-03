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
    @NamedQuery(name = "Chronogramme.findAll", query = "SELECT c FROM Chronogramme c"),
    @NamedQuery(name = "Chronogramme.findByIdchronogramme", query = "SELECT c FROM Chronogramme c WHERE c.idchronogramme = :idchronogramme"),
    @NamedQuery(name = "Chronogramme.findByEtat", query = "SELECT c FROM Chronogramme c WHERE c.etat = :etat")})
public class Chronogramme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idchronogramme;
    private Boolean etat;
    @JoinColumn(name = "idactivite", referencedColumnName = "idactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activite idactivite;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;

    public Chronogramme() {
    }

    public Chronogramme(Long idchronogramme) {
        this.idchronogramme = idchronogramme;
    }

    public Long getIdchronogramme() {
        return idchronogramme;
    }

    public void setIdchronogramme(Long idchronogramme) {
        this.idchronogramme = idchronogramme;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Activite getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Activite idactivite) {
        this.idactivite = idactivite;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchronogramme != null ? idchronogramme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chronogramme)) {
            return false;
        }
        Chronogramme other = (Chronogramme) object;
        if ((this.idchronogramme == null && other.idchronogramme != null) || (this.idchronogramme != null && !this.idchronogramme.equals(other.idchronogramme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Chronogramme[ idchronogramme=" + idchronogramme + " ]";
    }
    
}
