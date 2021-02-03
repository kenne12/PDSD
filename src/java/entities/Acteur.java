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
    @NamedQuery(name = "Acteur.findAll", query = "SELECT a FROM Acteur a"),
    @NamedQuery(name = "Acteur.findByIdacteur", query = "SELECT a FROM Acteur a WHERE a.idacteur = :idacteur"),
    @NamedQuery(name = "Acteur.findByNomFr", query = "SELECT a FROM Acteur a WHERE a.nomFr = :nomFr"),
    @NamedQuery(name = "Acteur.findByNomEn", query = "SELECT a FROM Acteur a WHERE a.nomEn = :nomEn")})
public class Acteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idacteur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idacteur", fetch = FetchType.LAZY)
    private List<Menace> menaceList;
    @OneToMany(mappedBy = "idacteur", fetch = FetchType.LAZY)
    private List<Faiblesse> faiblesseList;
    @OneToMany(mappedBy = "idacteur", fetch = FetchType.LAZY)
    private List<Force> forceList;
    @OneToMany(mappedBy = "idacteur", fetch = FetchType.LAZY)
    private List<Opportunite> opportuniteList;
    @OneToMany(mappedBy = "idacteur", fetch = FetchType.LAZY)
    private List<Acteurdistrict> acteurdistrictList;
    @JoinColumn(name = "idgroupeacteur", referencedColumnName = "idgroupeacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Groupeacteur idgroupeacteur;
    @JoinColumn(name = "idtypeacteur", referencedColumnName = "idtypeacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeacteur idtypeacteur;

    public Acteur() {
    }

    public Acteur(Integer idacteur) {
        this.idacteur = idacteur;
    }

    public Integer getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(Integer idacteur) {
        this.idacteur = idacteur;
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

    @XmlTransient
    public List<Opportunite> getOpportuniteList() {
        return opportuniteList;
    }

    public void setOpportuniteList(List<Opportunite> opportuniteList) {
        this.opportuniteList = opportuniteList;
    }

    @XmlTransient
    public List<Acteurdistrict> getActeurdistrictList() {
        return acteurdistrictList;
    }

    public void setActeurdistrictList(List<Acteurdistrict> acteurdistrictList) {
        this.acteurdistrictList = acteurdistrictList;
    }

    public Groupeacteur getIdgroupeacteur() {
        return idgroupeacteur;
    }

    public void setIdgroupeacteur(Groupeacteur idgroupeacteur) {
        this.idgroupeacteur = idgroupeacteur;
    }

    public Typeacteur getIdtypeacteur() {
        return idtypeacteur;
    }

    public void setIdtypeacteur(Typeacteur idtypeacteur) {
        this.idtypeacteur = idtypeacteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacteur != null ? idacteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acteur)) {
            return false;
        }
        Acteur other = (Acteur) object;
        if ((this.idacteur == null && other.idacteur != null) || (this.idacteur != null && !this.idacteur.equals(other.idacteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Acteur[ idacteur=" + idacteur + " ]";
    }
    
}
