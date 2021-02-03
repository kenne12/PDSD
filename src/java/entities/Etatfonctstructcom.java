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
    @NamedQuery(name = "Etatfonctstructcom.findAll", query = "SELECT e FROM Etatfonctstructcom e"),
    @NamedQuery(name = "Etatfonctstructcom.findByIdetatfonctstructcom", query = "SELECT e FROM Etatfonctstructcom e WHERE e.idetatfonctstructcom = :idetatfonctstructcom"),
    @NamedQuery(name = "Etatfonctstructcom.findByNomFr", query = "SELECT e FROM Etatfonctstructcom e WHERE e.nomFr = :nomFr"),
    @NamedQuery(name = "Etatfonctstructcom.findByNomEn", query = "SELECT e FROM Etatfonctstructcom e WHERE e.nomEn = :nomEn")})
public class Etatfonctstructcom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idetatfonctstructcom;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idetatfonctstructcom", fetch = FetchType.LAZY)
    private List<Structurecommunautaire> structurecommunautaireList;

    public Etatfonctstructcom() {
    }

    public Etatfonctstructcom(Integer idetatfonctstructcom) {
        this.idetatfonctstructcom = idetatfonctstructcom;
    }

    public Integer getIdetatfonctstructcom() {
        return idetatfonctstructcom;
    }

    public void setIdetatfonctstructcom(Integer idetatfonctstructcom) {
        this.idetatfonctstructcom = idetatfonctstructcom;
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
    public List<Structurecommunautaire> getStructurecommunautaireList() {
        return structurecommunautaireList;
    }

    public void setStructurecommunautaireList(List<Structurecommunautaire> structurecommunautaireList) {
        this.structurecommunautaireList = structurecommunautaireList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetatfonctstructcom != null ? idetatfonctstructcom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etatfonctstructcom)) {
            return false;
        }
        Etatfonctstructcom other = (Etatfonctstructcom) object;
        if ((this.idetatfonctstructcom == null && other.idetatfonctstructcom != null) || (this.idetatfonctstructcom != null && !this.idetatfonctstructcom.equals(other.idetatfonctstructcom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etatfonctstructcom[ idetatfonctstructcom=" + idetatfonctstructcom + " ]";
    }
    
}
