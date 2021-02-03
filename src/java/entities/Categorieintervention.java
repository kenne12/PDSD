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
    @NamedQuery(name = "Categorieintervention.findAll", query = "SELECT c FROM Categorieintervention c"),
    @NamedQuery(name = "Categorieintervention.findByIdcategorieintervention", query = "SELECT c FROM Categorieintervention c WHERE c.idcategorieintervention = :idcategorieintervention"),
    @NamedQuery(name = "Categorieintervention.findByNomFr", query = "SELECT c FROM Categorieintervention c WHERE c.nomFr = :nomFr"),
    @NamedQuery(name = "Categorieintervention.findByCode", query = "SELECT c FROM Categorieintervention c WHERE c.code = :code"),
    @NamedQuery(name = "Categorieintervention.findByNomEn", query = "SELECT c FROM Categorieintervention c WHERE c.nomEn = :nomEn")})
public class Categorieintervention implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcategorieintervention;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @JoinColumn(name = "idsousaxe", referencedColumnName = "idsousaxe")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousaxe idsousaxe;
    @OneToMany(mappedBy = "idcategorieintervention", fetch = FetchType.LAZY)
    private List<Interventionpnds> interventionpndsList;

    public Categorieintervention() {
    }

    public Categorieintervention(Integer idcategorieintervention) {
        this.idcategorieintervention = idcategorieintervention;
    }

    public Integer getIdcategorieintervention() {
        return idcategorieintervention;
    }

    public void setIdcategorieintervention(Integer idcategorieintervention) {
        this.idcategorieintervention = idcategorieintervention;
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

    public Sousaxe getIdsousaxe() {
        return idsousaxe;
    }

    public void setIdsousaxe(Sousaxe idsousaxe) {
        this.idsousaxe = idsousaxe;
    }

    @XmlTransient
    public List<Interventionpnds> getInterventionpndsList() {
        return interventionpndsList;
    }

    public void setInterventionpndsList(List<Interventionpnds> interventionpndsList) {
        this.interventionpndsList = interventionpndsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategorieintervention != null ? idcategorieintervention.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorieintervention)) {
            return false;
        }
        Categorieintervention other = (Categorieintervention) object;
        if ((this.idcategorieintervention == null && other.idcategorieintervention != null) || (this.idcategorieintervention != null && !this.idcategorieintervention.equals(other.idcategorieintervention))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categorieintervention[ idcategorieintervention=" + idcategorieintervention + " ]";
    }
    
}
