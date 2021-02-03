/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Groupeutilisateur.findAll", query = "SELECT g FROM Groupeutilisateur g"),
    @NamedQuery(name = "Groupeutilisateur.findByIdgroupeutilisateur", query = "SELECT g FROM Groupeutilisateur g WHERE g.idgroupeutilisateur = :idgroupeutilisateur"),
    @NamedQuery(name = "Groupeutilisateur.findByNom", query = "SELECT g FROM Groupeutilisateur g WHERE g.nom = :nom"),
    @NamedQuery(name = "Groupeutilisateur.findByDatecreation", query = "SELECT g FROM Groupeutilisateur g WHERE g.datecreation = :datecreation"),
    @NamedQuery(name = "Groupeutilisateur.findByEtat", query = "SELECT g FROM Groupeutilisateur g WHERE g.etat = :etat")})
public class Groupeutilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idgroupeutilisateur;
    @Size(max = 100)
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date datecreation;
    private Boolean etat;
    @OneToMany(mappedBy = "idgroupeutilisateur", fetch = FetchType.LAZY)
    private List<Privilege> privilegeList;
    @OneToMany(mappedBy = "idgroupeutilisateur", fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurList;

    public Groupeutilisateur() {
    }

    public Groupeutilisateur(Integer idgroupeutilisateur) {
        this.idgroupeutilisateur = idgroupeutilisateur;
    }

    public Integer getIdgroupeutilisateur() {
        return idgroupeutilisateur;
    }

    public void setIdgroupeutilisateur(Integer idgroupeutilisateur) {
        this.idgroupeutilisateur = idgroupeutilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    @XmlTransient
    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgroupeutilisateur != null ? idgroupeutilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupeutilisateur)) {
            return false;
        }
        Groupeutilisateur other = (Groupeutilisateur) object;
        if ((this.idgroupeutilisateur == null && other.idgroupeutilisateur != null) || (this.idgroupeutilisateur != null && !this.idgroupeutilisateur.equals(other.idgroupeutilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupeutilisateur[ idgroupeutilisateur=" + idgroupeutilisateur + " ]";
    }
    
}
