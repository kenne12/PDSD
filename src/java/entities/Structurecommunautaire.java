/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Structurecommunautaire.findAll", query = "SELECT s FROM Structurecommunautaire s"),
    @NamedQuery(name = "Structurecommunautaire.findByIdstructurecommunautaire", query = "SELECT s FROM Structurecommunautaire s WHERE s.idstructurecommunautaire = :idstructurecommunautaire"),
    @NamedQuery(name = "Structurecommunautaire.findByEffectif", query = "SELECT s FROM Structurecommunautaire s WHERE s.effectif = :effectif"),
    @NamedQuery(name = "Structurecommunautaire.findByNomFr", query = "SELECT s FROM Structurecommunautaire s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Structurecommunautaire.findByNomEn", query = "SELECT s FROM Structurecommunautaire s WHERE s.nomEn = :nomEn")})
public class Structurecommunautaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idstructurecommunautaire;
    private Integer effectif;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 255)
    @Column(name = "nom_en")
    private String nomEn;
    @JoinColumn(name = "idcategoriestructurecom", referencedColumnName = "idcategoriestructurecom")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoriestructurecom idcategoriestructurecom;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idetatfonctstructcom", referencedColumnName = "idetatfonctstructcom")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etatfonctstructcom idetatfonctstructcom;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idtypestructurecommunautaire", referencedColumnName = "idtypestructurecommunautaire")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructurecommunautaire idtypestructurecommunautaire;

    public Structurecommunautaire() {
    }

    public Structurecommunautaire(Integer idstructurecommunautaire) {
        this.idstructurecommunautaire = idstructurecommunautaire;
    }

    public Integer getIdstructurecommunautaire() {
        return idstructurecommunautaire;
    }

    public void setIdstructurecommunautaire(Integer idstructurecommunautaire) {
        this.idstructurecommunautaire = idstructurecommunautaire;
    }

    public Integer getEffectif() {
        return effectif;
    }

    public void setEffectif(Integer effectif) {
        this.effectif = effectif;
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

    public Categoriestructurecom getIdcategoriestructurecom() {
        return idcategoriestructurecom;
    }

    public void setIdcategoriestructurecom(Categoriestructurecom idcategoriestructurecom) {
        this.idcategoriestructurecom = idcategoriestructurecom;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Etatfonctstructcom getIdetatfonctstructcom() {
        return idetatfonctstructcom;
    }

    public void setIdetatfonctstructcom(Etatfonctstructcom idetatfonctstructcom) {
        this.idetatfonctstructcom = idetatfonctstructcom;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Typestructurecommunautaire getIdtypestructurecommunautaire() {
        return idtypestructurecommunautaire;
    }

    public void setIdtypestructurecommunautaire(Typestructurecommunautaire idtypestructurecommunautaire) {
        this.idtypestructurecommunautaire = idtypestructurecommunautaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstructurecommunautaire != null ? idstructurecommunautaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Structurecommunautaire)) {
            return false;
        }
        Structurecommunautaire other = (Structurecommunautaire) object;
        if ((this.idstructurecommunautaire == null && other.idstructurecommunautaire != null) || (this.idstructurecommunautaire != null && !this.idstructurecommunautaire.equals(other.idstructurecommunautaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Structurecommunautaire[ idstructurecommunautaire=" + idstructurecommunautaire + " ]";
    }
    
}
