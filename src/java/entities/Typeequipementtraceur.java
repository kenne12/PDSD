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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Typeequipementtraceur.findAll", query = "SELECT t FROM Typeequipementtraceur t"),
    @NamedQuery(name = "Typeequipementtraceur.findByIdtypeequipementtraceur", query = "SELECT t FROM Typeequipementtraceur t WHERE t.idtypeequipementtraceur = :idtypeequipementtraceur"),
    @NamedQuery(name = "Typeequipementtraceur.findByNomFr", query = "SELECT t FROM Typeequipementtraceur t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typeequipementtraceur.findByNomEn", query = "SELECT t FROM Typeequipementtraceur t WHERE t.nomEn = :nomEn"),
    @NamedQuery(name = "Typeequipementtraceur.findByRegional", query = "SELECT t FROM Typeequipementtraceur t WHERE t.regional = :regional"),
    @NamedQuery(name = "Typeequipementtraceur.findByDistrict", query = "SELECT t FROM Typeequipementtraceur t WHERE t.district = :district")})
public class Typeequipementtraceur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypeequipementtraceur;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean regional;
    private Boolean district;
    @JoinTable(name = "typestruct_typeequiptraceur", joinColumns = {
        @JoinColumn(name = "idtypeequipementtraceur", referencedColumnName = "idtypeequipementtraceur")}, inverseJoinColumns = {
        @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Typestructure> typestructureList;
    @OneToMany(mappedBy = "idtypeequipement", fetch = FetchType.LAZY)
    private List<Parametrecoutequipement> parametrecoutequipementList;
    @OneToMany(mappedBy = "idtypeequipementtraceur", fetch = FetchType.LAZY)
    private List<TypestrucTypeequipement> typestrucTypeequipementList;

    public Typeequipementtraceur() {
    }

    public Typeequipementtraceur(Integer idtypeequipementtraceur) {
        this.idtypeequipementtraceur = idtypeequipementtraceur;
    }

    public Integer getIdtypeequipementtraceur() {
        return idtypeequipementtraceur;
    }

    public void setIdtypeequipementtraceur(Integer idtypeequipementtraceur) {
        this.idtypeequipementtraceur = idtypeequipementtraceur;
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

    public Boolean getRegional() {
        return regional;
    }

    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

    public Boolean getDistrict() {
        return district;
    }

    public void setDistrict(Boolean district) {
        this.district = district;
    }

    @XmlTransient
    public List<Typestructure> getTypestructureList() {
        return typestructureList;
    }

    public void setTypestructureList(List<Typestructure> typestructureList) {
        this.typestructureList = typestructureList;
    }

    @XmlTransient
    public List<Parametrecoutequipement> getParametrecoutequipementList() {
        return parametrecoutequipementList;
    }

    public void setParametrecoutequipementList(List<Parametrecoutequipement> parametrecoutequipementList) {
        this.parametrecoutequipementList = parametrecoutequipementList;
    }

    @XmlTransient
    public List<TypestrucTypeequipement> getTypestrucTypeequipementList() {
        return typestrucTypeequipementList;
    }

    public void setTypestrucTypeequipementList(List<TypestrucTypeequipement> typestrucTypeequipementList) {
        this.typestrucTypeequipementList = typestrucTypeequipementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeequipementtraceur != null ? idtypeequipementtraceur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeequipementtraceur)) {
            return false;
        }
        Typeequipementtraceur other = (Typeequipementtraceur) object;
        if ((this.idtypeequipementtraceur == null && other.idtypeequipementtraceur != null) || (this.idtypeequipementtraceur != null && !this.idtypeequipementtraceur.equals(other.idtypeequipementtraceur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typeequipementtraceur[ idtypeequipementtraceur=" + idtypeequipementtraceur + " ]";
    }
    
}
