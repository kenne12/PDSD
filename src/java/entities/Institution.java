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
    @NamedQuery(name = "Institution.findAll", query = "SELECT i FROM Institution i"),
    @NamedQuery(name = "Institution.findByIdinstitution", query = "SELECT i FROM Institution i WHERE i.idinstitution = :idinstitution"),
    @NamedQuery(name = "Institution.findByNomFr", query = "SELECT i FROM Institution i WHERE i.nomFr = :nomFr"),
    @NamedQuery(name = "Institution.findByNomEn", query = "SELECT i FROM Institution i WHERE i.nomEn = :nomEn")})
public class Institution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idinstitution;
    @Size(max = 225)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 250)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idinstitution", fetch = FetchType.LAZY)
    private List<Structure> structureList;

    public Institution() {
    }

    public Institution(Integer idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Integer getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Integer idinstitution) {
        this.idinstitution = idinstitution;
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
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinstitution != null ? idinstitution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institution)) {
            return false;
        }
        Institution other = (Institution) object;
        if ((this.idinstitution == null && other.idinstitution != null) || (this.idinstitution != null && !this.idinstitution.equals(other.idinstitution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Institution[ idinstitution=" + idinstitution + " ]";
    }
    
}
