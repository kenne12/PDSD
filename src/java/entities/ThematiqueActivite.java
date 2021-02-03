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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "thematique_activite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ThematiqueActivite.findAll", query = "SELECT t FROM ThematiqueActivite t"),
    @NamedQuery(name = "ThematiqueActivite.findByIdthematiqueActivite", query = "SELECT t FROM ThematiqueActivite t WHERE t.idthematiqueActivite = :idthematiqueActivite"),
    @NamedQuery(name = "ThematiqueActivite.findByIddistrict", query = "SELECT t FROM ThematiqueActivite t WHERE t.iddistrict = :iddistrict"),
    @NamedQuery(name = "ThematiqueActivite.findByEtat", query = "SELECT t FROM ThematiqueActivite t WHERE t.etat = :etat")})
public class ThematiqueActivite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idthematique_activite")
    private Long idthematiqueActivite;
    private Integer iddistrict;
    private Boolean etat;
    @JoinColumn(name = "idactivite", referencedColumnName = "idactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activite idactivite;
    @JoinColumn(name = "idthematique", referencedColumnName = "idthematique")
    @ManyToOne(fetch = FetchType.LAZY)
    private Thematique idthematique;

    public ThematiqueActivite() {
    }

    public ThematiqueActivite(Long idthematiqueActivite) {
        this.idthematiqueActivite = idthematiqueActivite;
    }

    public Long getIdthematiqueActivite() {
        return idthematiqueActivite;
    }

    public void setIdthematiqueActivite(Long idthematiqueActivite) {
        this.idthematiqueActivite = idthematiqueActivite;
    }

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Activite getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Activite idactivite) {
        this.idactivite = idactivite;
    }

    public Thematique getIdthematique() {
        return idthematique;
    }

    public void setIdthematique(Thematique idthematique) {
        this.idthematique = idthematique;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idthematiqueActivite != null ? idthematiqueActivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThematiqueActivite)) {
            return false;
        }
        ThematiqueActivite other = (ThematiqueActivite) object;
        if ((this.idthematiqueActivite == null && other.idthematiqueActivite != null) || (this.idthematiqueActivite != null && !this.idthematiqueActivite.equals(other.idthematiqueActivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ThematiqueActivite[ idthematiqueActivite=" + idthematiqueActivite + " ]";
    }
    
}
