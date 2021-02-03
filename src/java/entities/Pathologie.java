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
    @NamedQuery(name = "Pathologie.findAll", query = "SELECT p FROM Pathologie p"),
    @NamedQuery(name = "Pathologie.findByIdpathologie", query = "SELECT p FROM Pathologie p WHERE p.idpathologie = :idpathologie"),
    @NamedQuery(name = "Pathologie.findByNomFr", query = "SELECT p FROM Pathologie p WHERE p.nomFr = :nomFr"),
    @NamedQuery(name = "Pathologie.findByNomEn", query = "SELECT p FROM Pathologie p WHERE p.nomEn = :nomEn"),
    @NamedQuery(name = "Pathologie.findByPrevalence", query = "SELECT p FROM Pathologie p WHERE p.prevalence = :prevalence")})
public class Pathologie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idpathologie;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean prevalence;
    @OneToMany(mappedBy = "idpathologie", fetch = FetchType.LAZY)
    private List<PrevalenceMaladie> prevalenceMaladieList;
    @OneToMany(mappedBy = "idpathologie", fetch = FetchType.LAZY)
    private List<Morbiditedistrict> morbiditedistrictList;
    @OneToMany(mappedBy = "idpathologie", fetch = FetchType.LAZY)
    private List<Patologiedistrict> patologiedistrictList;
    @JoinColumn(name = "idtypepathologie", referencedColumnName = "idtypepathologie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typepathologie idtypepathologie;

    public Pathologie() {
    }

    public Pathologie(Integer idpathologie) {
        this.idpathologie = idpathologie;
    }

    public Integer getIdpathologie() {
        return idpathologie;
    }

    public void setIdpathologie(Integer idpathologie) {
        this.idpathologie = idpathologie;
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

    public Boolean getPrevalence() {
        return prevalence;
    }

    public void setPrevalence(Boolean prevalence) {
        this.prevalence = prevalence;
    }

    @XmlTransient
    public List<PrevalenceMaladie> getPrevalenceMaladieList() {
        return prevalenceMaladieList;
    }

    public void setPrevalenceMaladieList(List<PrevalenceMaladie> prevalenceMaladieList) {
        this.prevalenceMaladieList = prevalenceMaladieList;
    }

    @XmlTransient
    public List<Morbiditedistrict> getMorbiditedistrictList() {
        return morbiditedistrictList;
    }

    public void setMorbiditedistrictList(List<Morbiditedistrict> morbiditedistrictList) {
        this.morbiditedistrictList = morbiditedistrictList;
    }

    @XmlTransient
    public List<Patologiedistrict> getPatologiedistrictList() {
        return patologiedistrictList;
    }

    public void setPatologiedistrictList(List<Patologiedistrict> patologiedistrictList) {
        this.patologiedistrictList = patologiedistrictList;
    }

    public Typepathologie getIdtypepathologie() {
        return idtypepathologie;
    }

    public void setIdtypepathologie(Typepathologie idtypepathologie) {
        this.idtypepathologie = idtypepathologie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpathologie != null ? idpathologie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pathologie)) {
            return false;
        }
        Pathologie other = (Pathologie) object;
        if ((this.idpathologie == null && other.idpathologie != null) || (this.idpathologie != null && !this.idpathologie.equals(other.idpathologie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pathologie[ idpathologie=" + idpathologie + " ]";
    }
    
}
