/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByIdutilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idutilisateur = :idutilisateur"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateur.findBySexe", query = "SELECT u FROM Utilisateur u WHERE u.sexe = :sexe")})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idutilisateur;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String prenom;
    @Size(max = 30)
    private String sexe;
    @OneToMany(mappedBy = "idutilisateur", fetch = FetchType.LAZY)
    private List<Compteutilisateur> compteutilisateurList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idutilisateur", fetch = FetchType.LAZY)
    private List<Utilisateurdistrict> utilisateurdistrictList;
    @OneToMany(mappedBy = "idutilisateur", fetch = FetchType.LAZY)
    private List<Mouchard> mouchardList;
    @JoinColumn(name = "idgroupeutilisateur", referencedColumnName = "idgroupeutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Groupeutilisateur idgroupeutilisateur;

    public Utilisateur() {
    }

    public Utilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @XmlTransient
    public List<Compteutilisateur> getCompteutilisateurList() {
        return compteutilisateurList;
    }

    public void setCompteutilisateurList(List<Compteutilisateur> compteutilisateurList) {
        this.compteutilisateurList = compteutilisateurList;
    }

    @XmlTransient
    public List<Utilisateurdistrict> getUtilisateurdistrictList() {
        return utilisateurdistrictList;
    }

    public void setUtilisateurdistrictList(List<Utilisateurdistrict> utilisateurdistrictList) {
        this.utilisateurdistrictList = utilisateurdistrictList;
    }

    @XmlTransient
    public List<Mouchard> getMouchardList() {
        return mouchardList;
    }

    public void setMouchardList(List<Mouchard> mouchardList) {
        this.mouchardList = mouchardList;
    }

    public Groupeutilisateur getIdgroupeutilisateur() {
        return idgroupeutilisateur;
    }

    public void setIdgroupeutilisateur(Groupeutilisateur idgroupeutilisateur) {
        this.idgroupeutilisateur = idgroupeutilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idutilisateur != null ? idutilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idutilisateur == null && other.idutilisateur != null) || (this.idutilisateur != null && !this.idutilisateur.equals(other.idutilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Utilisateur[ idutilisateur=" + idutilisateur + " ]";
    }
    
}
