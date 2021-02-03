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
    @NamedQuery(name = "Facteur.findAll", query = "SELECT f FROM Facteur f"),
    @NamedQuery(name = "Facteur.findByIdfacteur", query = "SELECT f FROM Facteur f WHERE f.idfacteur = :idfacteur"),
    @NamedQuery(name = "Facteur.findByNomFr", query = "SELECT f FROM Facteur f WHERE f.nomFr = :nomFr"),
    @NamedQuery(name = "Facteur.findByNomEn", query = "SELECT f FROM Facteur f WHERE f.nomEn = :nomEn")})
public class Facteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idfacteur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idfacteur", fetch = FetchType.LAZY)
    private List<Menace> menaceList;
    @OneToMany(mappedBy = "idfacteur", fetch = FetchType.LAZY)
    private List<Faiblesse> faiblesseList;
    @OneToMany(mappedBy = "idfacteur", fetch = FetchType.LAZY)
    private List<Force> forceList;
    @JoinColumn(name = "idgroupefacteur", referencedColumnName = "idgroupefacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Groupefacteur idgroupefacteur;
    @JoinColumn(name = "idtypefacteur", referencedColumnName = "idtypefacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typefacteur idtypefacteur;
    @OneToMany(mappedBy = "idfacteur", fetch = FetchType.LAZY)
    private List<Opportunite> opportuniteList;
    @OneToMany(mappedBy = "idfacteur", fetch = FetchType.LAZY)
    private List<Facteurdistrict> facteurdistrictList;

    public Facteur() {
    }

    public Facteur(Integer idfacteur) {
        this.idfacteur = idfacteur;
    }

    public Integer getIdfacteur() {
        return idfacteur;
    }

    public void setIdfacteur(Integer idfacteur) {
        this.idfacteur = idfacteur;
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
    public List<Menace> getMenaceList() {
        return menaceList;
    }

    public void setMenaceList(List<Menace> menaceList) {
        this.menaceList = menaceList;
    }

    @XmlTransient
    public List<Faiblesse> getFaiblesseList() {
        return faiblesseList;
    }

    public void setFaiblesseList(List<Faiblesse> faiblesseList) {
        this.faiblesseList = faiblesseList;
    }

    @XmlTransient
    public List<Force> getForceList() {
        return forceList;
    }

    public void setForceList(List<Force> forceList) {
        this.forceList = forceList;
    }

    public Groupefacteur getIdgroupefacteur() {
        return idgroupefacteur;
    }

    public void setIdgroupefacteur(Groupefacteur idgroupefacteur) {
        this.idgroupefacteur = idgroupefacteur;
    }

    public Typefacteur getIdtypefacteur() {
        return idtypefacteur;
    }

    public void setIdtypefacteur(Typefacteur idtypefacteur) {
        this.idtypefacteur = idtypefacteur;
    }

    @XmlTransient
    public List<Opportunite> getOpportuniteList() {
        return opportuniteList;
    }

    public void setOpportuniteList(List<Opportunite> opportuniteList) {
        this.opportuniteList = opportuniteList;
    }

    @XmlTransient
    public List<Facteurdistrict> getFacteurdistrictList() {
        return facteurdistrictList;
    }

    public void setFacteurdistrictList(List<Facteurdistrict> facteurdistrictList) {
        this.facteurdistrictList = facteurdistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacteur != null ? idfacteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facteur)) {
            return false;
        }
        Facteur other = (Facteur) object;
        if ((this.idfacteur == null && other.idfacteur != null) || (this.idfacteur != null && !this.idfacteur.equals(other.idfacteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Facteur[ idfacteur=" + idfacteur + " ]";
    }
    
}
