/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "medicamenttraceur_structure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamenttraceurStructure.findAll", query = "SELECT m FROM MedicamenttraceurStructure m"),
    @NamedQuery(name = "MedicamenttraceurStructure.findByIdmedicamenttraceurStructure", query = "SELECT m FROM MedicamenttraceurStructure m WHERE m.idmedicamenttraceurStructure = :idmedicamenttraceurStructure"),
    @NamedQuery(name = "MedicamenttraceurStructure.findByNbrejrrupturestock", query = "SELECT m FROM MedicamenttraceurStructure m WHERE m.nbrejrrupturestock = :nbrejrrupturestock"),
    @NamedQuery(name = "MedicamenttraceurStructure.findByPrcds", query = "SELECT m FROM MedicamenttraceurStructure m WHERE m.prcds = :prcds")})
public class MedicamenttraceurStructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmedicamenttraceur_structure")
    private Integer idmedicamenttraceurStructure;
    private Integer nbrejrrupturestock;
    private Boolean prcds;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idmedicamenttraceur", referencedColumnName = "idmedicamenttraceur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicamenttraceur idmedicamenttraceur;
    @JoinColumn(name = "idsourceapprovisionnement", referencedColumnName = "idsourceapprovisionnement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourceapprovisionnement idsourceapprovisionnement;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public MedicamenttraceurStructure() {
    }

    public MedicamenttraceurStructure(Integer idmedicamenttraceurStructure) {
        this.idmedicamenttraceurStructure = idmedicamenttraceurStructure;
    }

    public Integer getIdmedicamenttraceurStructure() {
        return idmedicamenttraceurStructure;
    }

    public void setIdmedicamenttraceurStructure(Integer idmedicamenttraceurStructure) {
        this.idmedicamenttraceurStructure = idmedicamenttraceurStructure;
    }

    public Integer getNbrejrrupturestock() {
        return nbrejrrupturestock;
    }

    public void setNbrejrrupturestock(Integer nbrejrrupturestock) {
        this.nbrejrrupturestock = nbrejrrupturestock;
    }

    public Boolean getPrcds() {
        return prcds;
    }

    public void setPrcds(Boolean prcds) {
        this.prcds = prcds;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Medicamenttraceur getIdmedicamenttraceur() {
        return idmedicamenttraceur;
    }

    public void setIdmedicamenttraceur(Medicamenttraceur idmedicamenttraceur) {
        this.idmedicamenttraceur = idmedicamenttraceur;
    }

    public Sourceapprovisionnement getIdsourceapprovisionnement() {
        return idsourceapprovisionnement;
    }

    public void setIdsourceapprovisionnement(Sourceapprovisionnement idsourceapprovisionnement) {
        this.idsourceapprovisionnement = idsourceapprovisionnement;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicamenttraceurStructure != null ? idmedicamenttraceurStructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamenttraceurStructure)) {
            return false;
        }
        MedicamenttraceurStructure other = (MedicamenttraceurStructure) object;
        if ((this.idmedicamenttraceurStructure == null && other.idmedicamenttraceurStructure != null) || (this.idmedicamenttraceurStructure != null && !this.idmedicamenttraceurStructure.equals(other.idmedicamenttraceurStructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MedicamenttraceurStructure[ idmedicamenttraceurStructure=" + idmedicamenttraceurStructure + " ]";
    }
    
}
