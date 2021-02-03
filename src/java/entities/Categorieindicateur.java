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
    @NamedQuery(name = "Categorieindicateur.findAll", query = "SELECT c FROM Categorieindicateur c"),
    @NamedQuery(name = "Categorieindicateur.findByIdcategorie", query = "SELECT c FROM Categorieindicateur c WHERE c.idcategorie = :idcategorie"),
    @NamedQuery(name = "Categorieindicateur.findByNomFr", query = "SELECT c FROM Categorieindicateur c WHERE c.nomFr = :nomFr"),
    @NamedQuery(name = "Categorieindicateur.findByNomEn", query = "SELECT c FROM Categorieindicateur c WHERE c.nomEn = :nomEn")})
public class Categorieindicateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcategorie;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idcategorie", fetch = FetchType.LAZY)
    private List<Indicateur> indicateurList;

    public Categorieindicateur() {
    }

    public Categorieindicateur(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Integer getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
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
    public List<Indicateur> getIndicateurList() {
        return indicateurList;
    }

    public void setIndicateurList(List<Indicateur> indicateurList) {
        this.indicateurList = indicateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategorie != null ? idcategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorieindicateur)) {
            return false;
        }
        Categorieindicateur other = (Categorieindicateur) object;
        if ((this.idcategorie == null && other.idcategorie != null) || (this.idcategorie != null && !this.idcategorie.equals(other.idcategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categorieindicateur[ idcategorie=" + idcategorie + " ]";
    }
    
}
