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
    @NamedQuery(name = "Typeactivite.findAll", query = "SELECT t FROM Typeactivite t"),
    @NamedQuery(name = "Typeactivite.findByIdtypeactivite", query = "SELECT t FROM Typeactivite t WHERE t.idtypeactivite = :idtypeactivite"),
    @NamedQuery(name = "Typeactivite.findByNomFr", query = "SELECT t FROM Typeactivite t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typeactivite.findByNomEn", query = "SELECT t FROM Typeactivite t WHERE t.nomEn = :nomEn")})
public class Typeactivite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypeactivite;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idtypeactivite", fetch = FetchType.LAZY)
    private List<Activite> activiteList;

    public Typeactivite() {
    }

    public Typeactivite(Integer idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
    }

    public Integer getIdtypeactivite() {
        return idtypeactivite;
    }

    public void setIdtypeactivite(Integer idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
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
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeactivite != null ? idtypeactivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeactivite)) {
            return false;
        }
        Typeactivite other = (Typeactivite) object;
        if ((this.idtypeactivite == null && other.idtypeactivite != null) || (this.idtypeactivite != null && !this.idtypeactivite.equals(other.idtypeactivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typeactivite[ idtypeactivite=" + idtypeactivite + " ]";
    }
    
}
