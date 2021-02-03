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
    @NamedQuery(name = "Typerecette.findAll", query = "SELECT t FROM Typerecette t"),
    @NamedQuery(name = "Typerecette.findByIdtyperecette", query = "SELECT t FROM Typerecette t WHERE t.idtyperecette = :idtyperecette"),
    @NamedQuery(name = "Typerecette.findByNomFr", query = "SELECT t FROM Typerecette t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typerecette.findByNomEn", query = "SELECT t FROM Typerecette t WHERE t.nomEn = :nomEn")})
public class Typerecette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtyperecette;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idtyperecette", fetch = FetchType.LAZY)
    private List<Recette> recetteList;

    public Typerecette() {
    }

    public Typerecette(Integer idtyperecette) {
        this.idtyperecette = idtyperecette;
    }

    public Integer getIdtyperecette() {
        return idtyperecette;
    }

    public void setIdtyperecette(Integer idtyperecette) {
        this.idtyperecette = idtyperecette;
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

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtyperecette != null ? idtyperecette.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typerecette)) {
            return false;
        }
        Typerecette other = (Typerecette) object;
        if ((this.idtyperecette == null && other.idtyperecette != null) || (this.idtyperecette != null && !this.idtyperecette.equals(other.idtyperecette))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typerecette[ idtyperecette=" + idtyperecette + " ]";
    }
    
}
