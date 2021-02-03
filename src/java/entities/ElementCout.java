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
@Table(name = "element_cout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementCout.findAll", query = "SELECT e FROM ElementCout e"),
    @NamedQuery(name = "ElementCout.findByIdelementCout", query = "SELECT e FROM ElementCout e WHERE e.idelementCout = :idelementCout"),
    @NamedQuery(name = "ElementCout.findByNomFr", query = "SELECT e FROM ElementCout e WHERE e.nomFr = :nomFr"),
    @NamedQuery(name = "ElementCout.findByNomEn", query = "SELECT e FROM ElementCout e WHERE e.nomEn = :nomEn"),
    @NamedQuery(name = "ElementCout.findByDefaultCu", query = "SELECT e FROM ElementCout e WHERE e.defaultCu = :defaultCu"),
    @NamedQuery(name = "ElementCout.findByDefaultQte", query = "SELECT e FROM ElementCout e WHERE e.defaultQte = :defaultQte"),
    @NamedQuery(name = "ElementCout.findByDefaultNbreJr", query = "SELECT e FROM ElementCout e WHERE e.defaultNbreJr = :defaultNbreJr")})
public class ElementCout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idelement_cout")
    private Integer idelementCout;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "default_cu")
    private Double defaultCu;
    @Column(name = "default_qte")
    private Double defaultQte;
    @Column(name = "default_nbre_jr")
    private Double defaultNbreJr;
    @OneToMany(mappedBy = "idelementcout", fetch = FetchType.LAZY)
    private List<ActiviteElementCout> activiteElementCoutList;
    @OneToMany(mappedBy = "idelementcout", fetch = FetchType.LAZY)
    private List<CoastingDefault> coastingDefaultList;

    public ElementCout() {
    }

    public ElementCout(Integer idelementCout) {
        this.idelementCout = idelementCout;
    }

    public Integer getIdelementCout() {
        return idelementCout;
    }

    public void setIdelementCout(Integer idelementCout) {
        this.idelementCout = idelementCout;
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

    public Double getDefaultCu() {
        return defaultCu;
    }

    public void setDefaultCu(Double defaultCu) {
        this.defaultCu = defaultCu;
    }

    public Double getDefaultQte() {
        return defaultQte;
    }

    public void setDefaultQte(Double defaultQte) {
        this.defaultQte = defaultQte;
    }

    public Double getDefaultNbreJr() {
        return defaultNbreJr;
    }

    public void setDefaultNbreJr(Double defaultNbreJr) {
        this.defaultNbreJr = defaultNbreJr;
    }

    @XmlTransient
    public List<ActiviteElementCout> getActiviteElementCoutList() {
        return activiteElementCoutList;
    }

    public void setActiviteElementCoutList(List<ActiviteElementCout> activiteElementCoutList) {
        this.activiteElementCoutList = activiteElementCoutList;
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
        hash += (idelementCout != null ? idelementCout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementCout)) {
            return false;
        }
        ElementCout other = (ElementCout) object;
        if ((this.idelementCout == null && other.idelementCout != null) || (this.idelementCout != null && !this.idelementCout.equals(other.idelementCout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ElementCout[ idelementCout=" + idelementCout + " ]";
    }
    
}
