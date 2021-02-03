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
    @NamedQuery(name = "Periodicite.findAll", query = "SELECT p FROM Periodicite p"),
    @NamedQuery(name = "Periodicite.findByIdperiodicite", query = "SELECT p FROM Periodicite p WHERE p.idperiodicite = :idperiodicite"),
    @NamedQuery(name = "Periodicite.findByNomFr", query = "SELECT p FROM Periodicite p WHERE p.nomFr = :nomFr"),
    @NamedQuery(name = "Periodicite.findByNomEn", query = "SELECT p FROM Periodicite p WHERE p.nomEn = :nomEn")})
public class Periodicite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idperiodicite;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idperiodicite", fetch = FetchType.LAZY)
    private List<Periode> periodeList;
    @OneToMany(mappedBy = "idperiodicite", fetch = FetchType.LAZY)
    private List<Indicateur> indicateurList;

    public Periodicite() {
    }

    public Periodicite(Integer idperiodicite) {
        this.idperiodicite = idperiodicite;
    }

    public Integer getIdperiodicite() {
        return idperiodicite;
    }

    public void setIdperiodicite(Integer idperiodicite) {
        this.idperiodicite = idperiodicite;
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
    public List<Periode> getPeriodeList() {
        return periodeList;
    }

    public void setPeriodeList(List<Periode> periodeList) {
        this.periodeList = periodeList;
    }

    @XmlTransient
    public List<Indicateur> getIndicateurList() {
        return indicateurList;
    }

    public void setIndicateurList(List<Indicateur> indicateurList) {
        this.indicateurList = indicateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiodicite != null ? idperiodicite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodicite)) {
            return false;
        }
        Periodicite other = (Periodicite) object;
        if ((this.idperiodicite == null && other.idperiodicite != null) || (this.idperiodicite != null && !this.idperiodicite.equals(other.idperiodicite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Periodicite[ idperiodicite=" + idperiodicite + " ]";
    }
    
}
