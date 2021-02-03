/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Periodederattachement.findAll", query = "SELECT p FROM Periodederattachement p"),
    @NamedQuery(name = "Periodederattachement.findByIdperiodederattachement", query = "SELECT p FROM Periodederattachement p WHERE p.idperiodederattachement = :idperiodederattachement"),
    @NamedQuery(name = "Periodederattachement.findByNom", query = "SELECT p FROM Periodederattachement p WHERE p.nom = :nom"),
    @NamedQuery(name = "Periodederattachement.findByEtat", query = "SELECT p FROM Periodederattachement p WHERE p.etat = :etat")})
public class Periodederattachement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idperiodederattachement;
    @Size(max = 2147483647)
    private String nom;
    private Boolean etat;
    @OneToMany(mappedBy = "idperiodederattachement", fetch = FetchType.LAZY)
    private List<Periode> periodeList;

    public Periodederattachement() {
    }

    public Periodederattachement(Integer idperiodederattachement) {
        this.idperiodederattachement = idperiodederattachement;
    }

    public Integer getIdperiodederattachement() {
        return idperiodederattachement;
    }

    public void setIdperiodederattachement(Integer idperiodederattachement) {
        this.idperiodederattachement = idperiodederattachement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Periode> getPeriodeList() {
        return periodeList;
    }

    public void setPeriodeList(List<Periode> periodeList) {
        this.periodeList = periodeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiodederattachement != null ? idperiodederattachement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodederattachement)) {
            return false;
        }
        Periodederattachement other = (Periodederattachement) object;
        if ((this.idperiodederattachement == null && other.idperiodederattachement != null) || (this.idperiodederattachement != null && !this.idperiodederattachement.equals(other.idperiodederattachement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Periodederattachement[ idperiodederattachement=" + idperiodederattachement + " ]";
    }
    
}
