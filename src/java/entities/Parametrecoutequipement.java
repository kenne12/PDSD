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
    @NamedQuery(name = "Parametrecoutequipement.findAll", query = "SELECT p FROM Parametrecoutequipement p"),
    @NamedQuery(name = "Parametrecoutequipement.findByIdparametrecoutequipement", query = "SELECT p FROM Parametrecoutequipement p WHERE p.idparametrecoutequipement = :idparametrecoutequipement"),
    @NamedQuery(name = "Parametrecoutequipement.findByCoutunitaire", query = "SELECT p FROM Parametrecoutequipement p WHERE p.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "Parametrecoutequipement.findByDefaultoperationFr", query = "SELECT p FROM Parametrecoutequipement p WHERE p.defaultoperationFr = :defaultoperationFr"),
    @NamedQuery(name = "Parametrecoutequipement.findByDefaultoperationEn", query = "SELECT p FROM Parametrecoutequipement p WHERE p.defaultoperationEn = :defaultoperationEn"),
    @NamedQuery(name = "Parametrecoutequipement.findByIdtypeequipementtraceur", query = "SELECT p FROM Parametrecoutequipement p WHERE p.idtypeequipementtraceur = :idtypeequipementtraceur")})
public class Parametrecoutequipement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idparametrecoutequipement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    @Size(max = 2147483647)
    @Column(name = "defaultoperation_fr")
    private String defaultoperationFr;
    @Size(max = 255)
    @Column(name = "defaultoperation_en")
    private String defaultoperationEn;
    private Integer idtypeequipementtraceur;
    @JoinColumn(name = "idetatequipement", referencedColumnName = "idetatequipement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etatequipement idetatequipement;
    @JoinColumn(name = "idtypeequipement", referencedColumnName = "idtypeequipementtraceur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeequipementtraceur idtypeequipement;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;

    public Parametrecoutequipement() {
    }

    public Parametrecoutequipement(Integer idparametrecoutequipement) {
        this.idparametrecoutequipement = idparametrecoutequipement;
    }

    public Integer getIdparametrecoutequipement() {
        return idparametrecoutequipement;
    }

    public void setIdparametrecoutequipement(Integer idparametrecoutequipement) {
        this.idparametrecoutequipement = idparametrecoutequipement;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public String getDefaultoperationFr() {
        return defaultoperationFr;
    }

    public void setDefaultoperationFr(String defaultoperationFr) {
        this.defaultoperationFr = defaultoperationFr;
    }

    public String getDefaultoperationEn() {
        return defaultoperationEn;
    }

    public void setDefaultoperationEn(String defaultoperationEn) {
        this.defaultoperationEn = defaultoperationEn;
    }

    public Integer getIdtypeequipementtraceur() {
        return idtypeequipementtraceur;
    }

    public void setIdtypeequipementtraceur(Integer idtypeequipementtraceur) {
        this.idtypeequipementtraceur = idtypeequipementtraceur;
    }

    public Etatequipement getIdetatequipement() {
        return idetatequipement;
    }

    public void setIdetatequipement(Etatequipement idetatequipement) {
        this.idetatequipement = idetatequipement;
    }

    public Typeequipementtraceur getIdtypeequipement() {
        return idtypeequipement;
    }

    public void setIdtypeequipement(Typeequipementtraceur idtypeequipement) {
        this.idtypeequipement = idtypeequipement;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparametrecoutequipement != null ? idparametrecoutequipement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrecoutequipement)) {
            return false;
        }
        Parametrecoutequipement other = (Parametrecoutequipement) object;
        if ((this.idparametrecoutequipement == null && other.idparametrecoutequipement != null) || (this.idparametrecoutequipement != null && !this.idparametrecoutequipement.equals(other.idparametrecoutequipement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Parametrecoutequipement[ idparametrecoutequipement=" + idparametrecoutequipement + " ]";
    }
    
}
