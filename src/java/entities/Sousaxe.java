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
    @NamedQuery(name = "Sousaxe.findAll", query = "SELECT s FROM Sousaxe s"),
    @NamedQuery(name = "Sousaxe.findByIdsousaxe", query = "SELECT s FROM Sousaxe s WHERE s.idsousaxe = :idsousaxe"),
    @NamedQuery(name = "Sousaxe.findByCode", query = "SELECT s FROM Sousaxe s WHERE s.code = :code"),
    @NamedQuery(name = "Sousaxe.findByNomFr", query = "SELECT s FROM Sousaxe s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Sousaxe.findByObjectifFr", query = "SELECT s FROM Sousaxe s WHERE s.objectifFr = :objectifFr"),
    @NamedQuery(name = "Sousaxe.findByNomEn", query = "SELECT s FROM Sousaxe s WHERE s.nomEn = :nomEn"),
    @NamedQuery(name = "Sousaxe.findByObjectifEn", query = "SELECT s FROM Sousaxe s WHERE s.objectifEn = :objectifEn")})
public class Sousaxe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsousaxe;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    @Column(name = "objectif_fr")
    private String objectifFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @Size(max = 255)
    @Column(name = "objectif_en")
    private String objectifEn;
    @OneToMany(mappedBy = "idsousaxe", fetch = FetchType.LAZY)
    private List<Categorieintervention> categorieinterventionList;
    @JoinColumn(name = "idaxe", referencedColumnName = "idaxe")
    @ManyToOne(fetch = FetchType.LAZY)
    private Axe idaxe;

    public Sousaxe() {
    }

    public Sousaxe(Integer idsousaxe) {
        this.idsousaxe = idsousaxe;
    }

    public Integer getIdsousaxe() {
        return idsousaxe;
    }

    public void setIdsousaxe(Integer idsousaxe) {
        this.idsousaxe = idsousaxe;
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

    public String getObjectifFr() {
        return objectifFr;
    }

    public void setObjectifFr(String objectifFr) {
        this.objectifFr = objectifFr;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public String getObjectifEn() {
        return objectifEn;
    }

    public void setObjectifEn(String objectifEn) {
        this.objectifEn = objectifEn;
    }

    @XmlTransient
    public List<Categorieintervention> getCategorieinterventionList() {
        return categorieinterventionList;
    }

    public void setCategorieinterventionList(List<Categorieintervention> categorieinterventionList) {
        this.categorieinterventionList = categorieinterventionList;
    }

    public Axe getIdaxe() {
        return idaxe;
    }

    public void setIdaxe(Axe idaxe) {
        this.idaxe = idaxe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsousaxe != null ? idsousaxe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sousaxe)) {
            return false;
        }
        Sousaxe other = (Sousaxe) object;
        if ((this.idsousaxe == null && other.idsousaxe != null) || (this.idsousaxe != null && !this.idsousaxe.equals(other.idsousaxe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sousaxe[ idsousaxe=" + idsousaxe + " ]";
    }
    
}
