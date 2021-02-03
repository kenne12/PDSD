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
    @NamedQuery(name = "Thematique.findAll", query = "SELECT t FROM Thematique t"),
    @NamedQuery(name = "Thematique.findByIdthematique", query = "SELECT t FROM Thematique t WHERE t.idthematique = :idthematique"),
    @NamedQuery(name = "Thematique.findByNomFr", query = "SELECT t FROM Thematique t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Thematique.findByNomEn", query = "SELECT t FROM Thematique t WHERE t.nomEn = :nomEn"),
    @NamedQuery(name = "Thematique.findByEtat", query = "SELECT t FROM Thematique t WHERE t.etat = :etat")})
public class Thematique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idthematique;
    @Size(max = 255)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 255)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean etat;
    @OneToMany(mappedBy = "idthematique", fetch = FetchType.LAZY)
    private List<ThematiqueActivite> thematiqueActiviteList;

    public Thematique() {
    }

    public Thematique(Integer idthematique) {
        this.idthematique = idthematique;
    }

    public Integer getIdthematique() {
        return idthematique;
    }

    public void setIdthematique(Integer idthematique) {
        this.idthematique = idthematique;
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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<ThematiqueActivite> getThematiqueActiviteList() {
        return thematiqueActiviteList;
    }

    public void setThematiqueActiviteList(List<ThematiqueActivite> thematiqueActiviteList) {
        this.thematiqueActiviteList = thematiqueActiviteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idthematique != null ? idthematique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thematique)) {
            return false;
        }
        Thematique other = (Thematique) object;
        if ((this.idthematique == null && other.idthematique != null) || (this.idthematique != null && !this.idthematique.equals(other.idthematique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Thematique[ idthematique=" + idthematique + " ]";
    }
    
}
