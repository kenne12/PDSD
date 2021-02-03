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
    @NamedQuery(name = "Etatequipement.findAll", query = "SELECT e FROM Etatequipement e"),
    @NamedQuery(name = "Etatequipement.findByIdetatequipement", query = "SELECT e FROM Etatequipement e WHERE e.idetatequipement = :idetatequipement"),
    @NamedQuery(name = "Etatequipement.findByNomFr", query = "SELECT e FROM Etatequipement e WHERE e.nomFr = :nomFr"),
    @NamedQuery(name = "Etatequipement.findByCode", query = "SELECT e FROM Etatequipement e WHERE e.code = :code"),
    @NamedQuery(name = "Etatequipement.findByNomEn", query = "SELECT e FROM Etatequipement e WHERE e.nomEn = :nomEn"),
    @NamedQuery(name = "Etatequipement.findByPriorite", query = "SELECT e FROM Etatequipement e WHERE e.priorite = :priorite")})
public class Etatequipement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idetatequipement;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean priorite;
    @OneToMany(mappedBy = "idetatequipement", fetch = FetchType.LAZY)
    private List<Equipementtraceur> equipementtraceurList;
    @OneToMany(mappedBy = "idetatequipement", fetch = FetchType.LAZY)
    private List<Parametrecoutequipement> parametrecoutequipementList;

    public Etatequipement() {
    }

    public Etatequipement(Integer idetatequipement) {
        this.idetatequipement = idetatequipement;
    }

    public Integer getIdetatequipement() {
        return idetatequipement;
    }

    public void setIdetatequipement(Integer idetatequipement) {
        this.idetatequipement = idetatequipement;
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

    public Boolean getPriorite() {
        return priorite;
    }

    public void setPriorite(Boolean priorite) {
        this.priorite = priorite;
    }

    @XmlTransient
    public List<Equipementtraceur> getEquipementtraceurList() {
        return equipementtraceurList;
    }

    public void setEquipementtraceurList(List<Equipementtraceur> equipementtraceurList) {
        this.equipementtraceurList = equipementtraceurList;
    }

    @XmlTransient
    public List<Parametrecoutequipement> getParametrecoutequipementList() {
        return parametrecoutequipementList;
    }

    public void setParametrecoutequipementList(List<Parametrecoutequipement> parametrecoutequipementList) {
        this.parametrecoutequipementList = parametrecoutequipementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetatequipement != null ? idetatequipement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etatequipement)) {
            return false;
        }
        Etatequipement other = (Etatequipement) object;
        if ((this.idetatequipement == null && other.idetatequipement != null) || (this.idetatequipement != null && !this.idetatequipement.equals(other.idetatequipement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etatequipement[ idetatequipement=" + idetatequipement + " ]";
    }
    
}
