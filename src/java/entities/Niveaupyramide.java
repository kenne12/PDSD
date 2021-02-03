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
    @NamedQuery(name = "Niveaupyramide.findAll", query = "SELECT n FROM Niveaupyramide n"),
    @NamedQuery(name = "Niveaupyramide.findByIdniveaupyramide", query = "SELECT n FROM Niveaupyramide n WHERE n.idniveaupyramide = :idniveaupyramide"),
    @NamedQuery(name = "Niveaupyramide.findByNomFr", query = "SELECT n FROM Niveaupyramide n WHERE n.nomFr = :nomFr"),
    @NamedQuery(name = "Niveaupyramide.findByNomEn", query = "SELECT n FROM Niveaupyramide n WHERE n.nomEn = :nomEn"),
    @NamedQuery(name = "Niveaupyramide.findByNumero", query = "SELECT n FROM Niveaupyramide n WHERE n.numero = :numero")})
public class Niveaupyramide implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idniveaupyramide;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    @Column(name = "nom_en")
    private String nomEn;
    private Integer numero;
    @OneToMany(mappedBy = "idniveaupyramide", fetch = FetchType.LAZY)
    private List<Structure> structureList;

    public Niveaupyramide() {
    }

    public Niveaupyramide(Integer idniveaupyramide) {
        this.idniveaupyramide = idniveaupyramide;
    }

    public Integer getIdniveaupyramide() {
        return idniveaupyramide;
    }

    public void setIdniveaupyramide(Integer idniveaupyramide) {
        this.idniveaupyramide = idniveaupyramide;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idniveaupyramide != null ? idniveaupyramide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveaupyramide)) {
            return false;
        }
        Niveaupyramide other = (Niveaupyramide) object;
        if ((this.idniveaupyramide == null && other.idniveaupyramide != null) || (this.idniveaupyramide != null && !this.idniveaupyramide.equals(other.idniveaupyramide))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Niveaupyramide[ idniveaupyramide=" + idniveaupyramide + " ]";
    }
    
}
