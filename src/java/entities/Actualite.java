/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
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
    @NamedQuery(name = "Actualite.findAll", query = "SELECT a FROM Actualite a"),
    @NamedQuery(name = "Actualite.findByIdactualite", query = "SELECT a FROM Actualite a WHERE a.idactualite = :idactualite"),
    @NamedQuery(name = "Actualite.findByTitreFr", query = "SELECT a FROM Actualite a WHERE a.titreFr = :titreFr"),
    @NamedQuery(name = "Actualite.findByLibelleFr", query = "SELECT a FROM Actualite a WHERE a.libelleFr = :libelleFr"),
    @NamedQuery(name = "Actualite.findByImage", query = "SELECT a FROM Actualite a WHERE a.image = :image"),
    @NamedQuery(name = "Actualite.findByDescription", query = "SELECT a FROM Actualite a WHERE a.description = :description"),
    @NamedQuery(name = "Actualite.findByDateactualite", query = "SELECT a FROM Actualite a WHERE a.dateactualite = :dateactualite"),
    @NamedQuery(name = "Actualite.findByAffiche", query = "SELECT a FROM Actualite a WHERE a.affiche = :affiche"),
    @NamedQuery(name = "Actualite.findByTitreEn", query = "SELECT a FROM Actualite a WHERE a.titreEn = :titreEn"),
    @NamedQuery(name = "Actualite.findByLibelleEn", query = "SELECT a FROM Actualite a WHERE a.libelleEn = :libelleEn")})
public class Actualite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idactualite;
    @Size(max = 2147483647)
    @Column(name = "titre_fr")
    private String titreFr;
    @Size(max = 2147483647)
    @Column(name = "libelle_fr")
    private String libelleFr;
    @Size(max = 2147483647)
    private String image;
    @Size(max = 2147483647)
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateactualite;
    @Size(max = 2147483647)
    private String affiche;
    @Size(max = 2147483647)
    @Column(name = "titre_en")
    private String titreEn;
    @Size(max = 2147483647)
    @Column(name = "libelle_en")
    private String libelleEn;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public Actualite() {
    }

    public Actualite(Integer idactualite) {
        this.idactualite = idactualite;
    }

    public Integer getIdactualite() {
        return idactualite;
    }

    public void setIdactualite(Integer idactualite) {
        this.idactualite = idactualite;
    }

    public String getTitreFr() {
        return titreFr;
    }

    public void setTitreFr(String titreFr) {
        this.titreFr = titreFr;
    }

    public String getLibelleFr() {
        return libelleFr;
    }

    public void setLibelleFr(String libelleFr) {
        this.libelleFr = libelleFr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateactualite() {
        return dateactualite;
    }

    public void setDateactualite(Date dateactualite) {
        this.dateactualite = dateactualite;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getTitreEn() {
        return titreEn;
    }

    public void setTitreEn(String titreEn) {
        this.titreEn = titreEn;
    }

    public String getLibelleEn() {
        return libelleEn;
    }

    public void setLibelleEn(String libelleEn) {
        this.libelleEn = libelleEn;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactualite != null ? idactualite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actualite)) {
            return false;
        }
        Actualite other = (Actualite) object;
        if ((this.idactualite == null && other.idactualite != null) || (this.idactualite != null && !this.idactualite.equals(other.idactualite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Actualite[ idactualite=" + idactualite + " ]";
    }
    
}
