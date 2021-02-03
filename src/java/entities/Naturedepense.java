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
    @NamedQuery(name = "Naturedepense.findAll", query = "SELECT n FROM Naturedepense n"),
    @NamedQuery(name = "Naturedepense.findByIdnaturedepense", query = "SELECT n FROM Naturedepense n WHERE n.idnaturedepense = :idnaturedepense"),
    @NamedQuery(name = "Naturedepense.findByNomFr", query = "SELECT n FROM Naturedepense n WHERE n.nomFr = :nomFr"),
    @NamedQuery(name = "Naturedepense.findByNomEn", query = "SELECT n FROM Naturedepense n WHERE n.nomEn = :nomEn")})
public class Naturedepense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idnaturedepense;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idnaturedepense", fetch = FetchType.LAZY)
    private List<Depense> depenseList;

    public Naturedepense() {
    }

    public Naturedepense(Integer idnaturedepense) {
        this.idnaturedepense = idnaturedepense;
    }

    public Integer getIdnaturedepense() {
        return idnaturedepense;
    }

    public void setIdnaturedepense(Integer idnaturedepense) {
        this.idnaturedepense = idnaturedepense;
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
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnaturedepense != null ? idnaturedepense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Naturedepense)) {
            return false;
        }
        Naturedepense other = (Naturedepense) object;
        if ((this.idnaturedepense == null && other.idnaturedepense != null) || (this.idnaturedepense != null && !this.idnaturedepense.equals(other.idnaturedepense))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Naturedepense[ idnaturedepense=" + idnaturedepense + " ]";
    }
    
}
