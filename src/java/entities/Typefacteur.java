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
    @NamedQuery(name = "Typefacteur.findAll", query = "SELECT t FROM Typefacteur t"),
    @NamedQuery(name = "Typefacteur.findByIdtypefacteur", query = "SELECT t FROM Typefacteur t WHERE t.idtypefacteur = :idtypefacteur"),
    @NamedQuery(name = "Typefacteur.findByNomFr", query = "SELECT t FROM Typefacteur t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typefacteur.findByNomEn", query = "SELECT t FROM Typefacteur t WHERE t.nomEn = :nomEn")})
public class Typefacteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypefacteur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idtypefacteur", fetch = FetchType.LAZY)
    private List<Facteur> facteurList;

    public Typefacteur() {
    }

    public Typefacteur(Integer idtypefacteur) {
        this.idtypefacteur = idtypefacteur;
    }

    public Integer getIdtypefacteur() {
        return idtypefacteur;
    }

    public void setIdtypefacteur(Integer idtypefacteur) {
        this.idtypefacteur = idtypefacteur;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Facteur> getFacteurList() {
        return facteurList;
    }

    public void setFacteurList(List<Facteur> facteurList) {
        this.facteurList = facteurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypefacteur != null ? idtypefacteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typefacteur)) {
            return false;
        }
        Typefacteur other = (Typefacteur) object;
        if ((this.idtypefacteur == null && other.idtypefacteur != null) || (this.idtypefacteur != null && !this.idtypefacteur.equals(other.idtypefacteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typefacteur[ idtypefacteur=" + idtypefacteur + " ]";
    }
    
}
