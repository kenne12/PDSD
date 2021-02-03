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
    @NamedQuery(name = "Utilisateurdistrict.findAll", query = "SELECT u FROM Utilisateurdistrict u"),
    @NamedQuery(name = "Utilisateurdistrict.findById", query = "SELECT u FROM Utilisateurdistrict u WHERE u.id = :id")})
public class Utilisateurdistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long id;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur idutilisateur;

    public Utilisateurdistrict() {
    }

    public Utilisateurdistrict(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateurdistrict)) {
            return false;
        }
        Utilisateurdistrict other = (Utilisateurdistrict) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Utilisateurdistrict[ id=" + id + " ]";
    }
    
}
