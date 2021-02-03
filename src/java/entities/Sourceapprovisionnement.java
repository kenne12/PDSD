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
    @NamedQuery(name = "Sourceapprovisionnement.findAll", query = "SELECT s FROM Sourceapprovisionnement s"),
    @NamedQuery(name = "Sourceapprovisionnement.findByIdsourceapprovisionnement", query = "SELECT s FROM Sourceapprovisionnement s WHERE s.idsourceapprovisionnement = :idsourceapprovisionnement"),
    @NamedQuery(name = "Sourceapprovisionnement.findByNomFr", query = "SELECT s FROM Sourceapprovisionnement s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Sourceapprovisionnement.findByNomEn", query = "SELECT s FROM Sourceapprovisionnement s WHERE s.nomEn = :nomEn")})
public class Sourceapprovisionnement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsourceapprovisionnement;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idsourceapprovisionnement", fetch = FetchType.LAZY)
    private List<MedicamenttraceurStructure> medicamenttraceurStructureList;

    public Sourceapprovisionnement() {
    }

    public Sourceapprovisionnement(Integer idsourceapprovisionnement) {
        this.idsourceapprovisionnement = idsourceapprovisionnement;
    }

    public Integer getIdsourceapprovisionnement() {
        return idsourceapprovisionnement;
    }

    public void setIdsourceapprovisionnement(Integer idsourceapprovisionnement) {
        this.idsourceapprovisionnement = idsourceapprovisionnement;
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
    public List<MedicamenttraceurStructure> getMedicamenttraceurStructureList() {
        return medicamenttraceurStructureList;
    }

    public void setMedicamenttraceurStructureList(List<MedicamenttraceurStructure> medicamenttraceurStructureList) {
        this.medicamenttraceurStructureList = medicamenttraceurStructureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsourceapprovisionnement != null ? idsourceapprovisionnement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sourceapprovisionnement)) {
            return false;
        }
        Sourceapprovisionnement other = (Sourceapprovisionnement) object;
        if ((this.idsourceapprovisionnement == null && other.idsourceapprovisionnement != null) || (this.idsourceapprovisionnement != null && !this.idsourceapprovisionnement.equals(other.idsourceapprovisionnement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sourceapprovisionnement[ idsourceapprovisionnement=" + idsourceapprovisionnement + " ]";
    }
    
}
