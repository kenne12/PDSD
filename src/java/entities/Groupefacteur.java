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
    @NamedQuery(name = "Groupefacteur.findAll", query = "SELECT g FROM Groupefacteur g"),
    @NamedQuery(name = "Groupefacteur.findByIdgroupefacteur", query = "SELECT g FROM Groupefacteur g WHERE g.idgroupefacteur = :idgroupefacteur"),
    @NamedQuery(name = "Groupefacteur.findByNomFr", query = "SELECT g FROM Groupefacteur g WHERE g.nomFr = :nomFr"),
    @NamedQuery(name = "Groupefacteur.findByCode", query = "SELECT g FROM Groupefacteur g WHERE g.code = :code"),
    @NamedQuery(name = "Groupefacteur.findByNomEn", query = "SELECT g FROM Groupefacteur g WHERE g.nomEn = :nomEn")})
public class Groupefacteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idgroupefacteur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idgroupefacteur", fetch = FetchType.LAZY)
    private List<Facteur> facteurList;

    public Groupefacteur() {
    }

    public Groupefacteur(Integer idgroupefacteur) {
        this.idgroupefacteur = idgroupefacteur;
    }

    public Integer getIdgroupefacteur() {
        return idgroupefacteur;
    }

    public void setIdgroupefacteur(Integer idgroupefacteur) {
        this.idgroupefacteur = idgroupefacteur;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        hash += (idgroupefacteur != null ? idgroupefacteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupefacteur)) {
            return false;
        }
        Groupefacteur other = (Groupefacteur) object;
        if ((this.idgroupefacteur == null && other.idgroupefacteur != null) || (this.idgroupefacteur != null && !this.idgroupefacteur.equals(other.idgroupefacteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupefacteur[ idgroupefacteur=" + idgroupefacteur + " ]";
    }
    
}
