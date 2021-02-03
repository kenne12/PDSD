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
    @NamedQuery(name = "Axe.findAll", query = "SELECT a FROM Axe a"),
    @NamedQuery(name = "Axe.findByIdaxe", query = "SELECT a FROM Axe a WHERE a.idaxe = :idaxe"),
    @NamedQuery(name = "Axe.findByCode", query = "SELECT a FROM Axe a WHERE a.code = :code"),
    @NamedQuery(name = "Axe.findByNomFr", query = "SELECT a FROM Axe a WHERE a.nomFr = :nomFr"),
    @NamedQuery(name = "Axe.findByObjectifstrategueFr", query = "SELECT a FROM Axe a WHERE a.objectifstrategueFr = :objectifstrategueFr"),
    @NamedQuery(name = "Axe.findByNomEn", query = "SELECT a FROM Axe a WHERE a.nomEn = :nomEn"),
    @NamedQuery(name = "Axe.findByObjectifstrategueEn", query = "SELECT a FROM Axe a WHERE a.objectifstrategueEn = :objectifstrategueEn")})
public class Axe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idaxe;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    @Column(name = "objectifstrategue_fr")
    private String objectifstrategueFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @Size(max = 255)
    @Column(name = "objectifstrategue_en")
    private String objectifstrategueEn;
    @OneToMany(mappedBy = "idaxe", fetch = FetchType.LAZY)
    private List<PrevalenceMaladie> prevalenceMaladieList;
    @OneToMany(mappedBy = "idaxe", fetch = FetchType.LAZY)
    private List<Sousaxe> sousaxeList;

    public Axe() {
    }

    public Axe(Integer idaxe) {
        this.idaxe = idaxe;
    }

    public Integer getIdaxe() {
        return idaxe;
    }

    public void setIdaxe(Integer idaxe) {
        this.idaxe = idaxe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getObjectifstrategueFr() {
        return objectifstrategueFr;
    }

    public void setObjectifstrategueFr(String objectifstrategueFr) {
        this.objectifstrategueFr = objectifstrategueFr;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public String getObjectifstrategueEn() {
        return objectifstrategueEn;
    }

    public void setObjectifstrategueEn(String objectifstrategueEn) {
        this.objectifstrategueEn = objectifstrategueEn;
    }

    @XmlTransient
    public List<PrevalenceMaladie> getPrevalenceMaladieList() {
        return prevalenceMaladieList;
    }

    public void setPrevalenceMaladieList(List<PrevalenceMaladie> prevalenceMaladieList) {
        this.prevalenceMaladieList = prevalenceMaladieList;
    }

    @XmlTransient
    public List<Sousaxe> getSousaxeList() {
        return sousaxeList;
    }

    public void setSousaxeList(List<Sousaxe> sousaxeList) {
        this.sousaxeList = sousaxeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaxe != null ? idaxe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Axe)) {
            return false;
        }
        Axe other = (Axe) object;
        if ((this.idaxe == null && other.idaxe != null) || (this.idaxe != null && !this.idaxe.equals(other.idaxe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Axe[ idaxe=" + idaxe + " ]";
    }
    
}
