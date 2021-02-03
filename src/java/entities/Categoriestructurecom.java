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
    @NamedQuery(name = "Categoriestructurecom.findAll", query = "SELECT c FROM Categoriestructurecom c"),
    @NamedQuery(name = "Categoriestructurecom.findByIdcategoriestructurecom", query = "SELECT c FROM Categoriestructurecom c WHERE c.idcategoriestructurecom = :idcategoriestructurecom"),
    @NamedQuery(name = "Categoriestructurecom.findByNomFr", query = "SELECT c FROM Categoriestructurecom c WHERE c.nomFr = :nomFr"),
    @NamedQuery(name = "Categoriestructurecom.findByRang", query = "SELECT c FROM Categoriestructurecom c WHERE c.rang = :rang"),
    @NamedQuery(name = "Categoriestructurecom.findByNomEn", query = "SELECT c FROM Categoriestructurecom c WHERE c.nomEn = :nomEn")})
public class Categoriestructurecom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcategoriestructurecom;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    private Integer rang;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idcategoriestructurecom", fetch = FetchType.LAZY)
    private List<Structurecommunautaire> structurecommunautaireList;

    public Categoriestructurecom() {
    }

    public Categoriestructurecom(Integer idcategoriestructurecom) {
        this.idcategoriestructurecom = idcategoriestructurecom;
    }

    public Integer getIdcategoriestructurecom() {
        return idcategoriestructurecom;
    }

    public void setIdcategoriestructurecom(Integer idcategoriestructurecom) {
        this.idcategoriestructurecom = idcategoriestructurecom;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
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
        hash += (idcategoriestructurecom != null ? idcategoriestructurecom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriestructurecom)) {
            return false;
        }
        Categoriestructurecom other = (Categoriestructurecom) object;
        if ((this.idcategoriestructurecom == null && other.idcategoriestructurecom != null) || (this.idcategoriestructurecom != null && !this.idcategoriestructurecom.equals(other.idcategoriestructurecom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categoriestructurecom[ idcategoriestructurecom=" + idcategoriestructurecom + " ]";
    }
    
}
