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
    @NamedQuery(name = "Compteutilisateur.findAll", query = "SELECT c FROM Compteutilisateur c"),
    @NamedQuery(name = "Compteutilisateur.findByIdcompte", query = "SELECT c FROM Compteutilisateur c WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "Compteutilisateur.findByLogin", query = "SELECT c FROM Compteutilisateur c WHERE c.login = :login"),
    @NamedQuery(name = "Compteutilisateur.findByPassword", query = "SELECT c FROM Compteutilisateur c WHERE c.password = :password"),
    @NamedQuery(name = "Compteutilisateur.findByDatecreation", query = "SELECT c FROM Compteutilisateur c WHERE c.datecreation = :datecreation"),
    @NamedQuery(name = "Compteutilisateur.findByEtat", query = "SELECT c FROM Compteutilisateur c WHERE c.etat = :etat")})
public class Compteutilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcompte;
    @Size(max = 254)
    private String login;
    @Size(max = 254)
    private String password;
    @Temporal(TemporalType.DATE)
    private Date datecreation;
    private Boolean etat;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur idutilisateur;

    public Compteutilisateur() {
    }

    public Compteutilisateur(Integer idcompte) {
        this.idcompte = idcompte;
    }

    public Integer getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Integer idcompte) {
        this.idcompte = idcompte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
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
        hash += (idcompte != null ? idcompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compteutilisateur)) {
            return false;
        }
        Compteutilisateur other = (Compteutilisateur) object;
        if ((this.idcompte == null && other.idcompte != null) || (this.idcompte != null && !this.idcompte.equals(other.idcompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compteutilisateur[ idcompte=" + idcompte + " ]";
    }
    
}
