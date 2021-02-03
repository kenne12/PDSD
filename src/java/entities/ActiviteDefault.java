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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "activite_default")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteDefault.findAll", query = "SELECT a FROM ActiviteDefault a"),
    @NamedQuery(name = "ActiviteDefault.findByIdactiviteDefault", query = "SELECT a FROM ActiviteDefault a WHERE a.idactiviteDefault = :idactiviteDefault"),
    @NamedQuery(name = "ActiviteDefault.findByNomFr", query = "SELECT a FROM ActiviteDefault a WHERE a.nomFr = :nomFr"),
    @NamedQuery(name = "ActiviteDefault.findByNomEn", query = "SELECT a FROM ActiviteDefault a WHERE a.nomEn = :nomEn"),
    @NamedQuery(name = "ActiviteDefault.findByCoutUnitaire", query = "SELECT a FROM ActiviteDefault a WHERE a.coutUnitaire = :coutUnitaire")})
public class ActiviteDefault implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_default")
    private Long idactiviteDefault;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cout_unitaire")
    private Double coutUnitaire;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idsourcefi", referencedColumnName = "idsourcefi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefi;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;
    @OneToMany(mappedBy = "idactiviteDefault", fetch = FetchType.LAZY)
    private List<CoastingDefault> coastingDefaultList;

    public ActiviteDefault() {
    }

    public ActiviteDefault(Long idactiviteDefault) {
        this.idactiviteDefault = idactiviteDefault;
    }

    public Long getIdactiviteDefault() {
        return idactiviteDefault;
    }

    public void setIdactiviteDefault(Long idactiviteDefault) {
        this.idactiviteDefault = idactiviteDefault;
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

    public Double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Sourcefinancement getIdsourcefi() {
        return idsourcefi;
    }

    public void setIdsourcefi(Sourcefinancement idsourcefi) {
        this.idsourcefi = idsourcefi;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @XmlTransient
    public List<CoastingDefault> getCoastingDefaultList() {
        return coastingDefaultList;
    }

    public void setCoastingDefaultList(List<CoastingDefault> coastingDefaultList) {
        this.coastingDefaultList = coastingDefaultList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactiviteDefault != null ? idactiviteDefault.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteDefault)) {
            return false;
        }
        ActiviteDefault other = (ActiviteDefault) object;
        if ((this.idactiviteDefault == null && other.idactiviteDefault != null) || (this.idactiviteDefault != null && !this.idactiviteDefault.equals(other.idactiviteDefault))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteDefault[ idactiviteDefault=" + idactiviteDefault + " ]";
    }
    
}
