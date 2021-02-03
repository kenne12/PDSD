/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Acteursfacteurs.findAll", query = "SELECT a FROM Acteursfacteurs a"),
    @NamedQuery(name = "Acteursfacteurs.findByIdacteursfacteurs", query = "SELECT a FROM Acteursfacteurs a WHERE a.idacteursfacteurs = :idacteursfacteurs"),
    @NamedQuery(name = "Acteursfacteurs.findByNom", query = "SELECT a FROM Acteursfacteurs a WHERE a.nom = :nom")})
public class Acteursfacteurs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idacteursfacteurs;
    @Size(max = 254)
    private String nom;

    public Acteursfacteurs() {
    }

    public Acteursfacteurs(Integer idacteursfacteurs) {
        this.idacteursfacteurs = idacteursfacteurs;
    }

    public Integer getIdacteursfacteurs() {
        return idacteursfacteurs;
    }

    public void setIdacteursfacteurs(Integer idacteursfacteurs) {
        this.idacteursfacteurs = idacteursfacteurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacteursfacteurs != null ? idacteursfacteurs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acteursfacteurs)) {
            return false;
        }
        Acteursfacteurs other = (Acteursfacteurs) object;
        if ((this.idacteursfacteurs == null && other.idacteursfacteurs != null) || (this.idacteursfacteurs != null && !this.idacteursfacteurs.equals(other.idacteursfacteurs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Acteursfacteurs[ idacteursfacteurs=" + idacteursfacteurs + " ]";
    }
    
}
