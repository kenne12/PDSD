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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "typestruc_typeequipement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypestrucTypeequipement.findAll", query = "SELECT t FROM TypestrucTypeequipement t"),
    @NamedQuery(name = "TypestrucTypeequipement.findByIdtypestrucTypeequipement", query = "SELECT t FROM TypestrucTypeequipement t WHERE t.idtypestrucTypeequipement = :idtypestrucTypeequipement")})
public class TypestrucTypeequipement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtypestruc_typeequipement")
    private Integer idtypestrucTypeequipement;
    @OneToMany(mappedBy = "idtypestrucTypeequipement", fetch = FetchType.LAZY)
    private List<Equipementtraceur> equipementtraceurList;
    @JoinColumn(name = "idtypeequipementtraceur", referencedColumnName = "idtypeequipementtraceur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeequipementtraceur idtypeequipementtraceur;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;

    public TypestrucTypeequipement() {
    }

    public TypestrucTypeequipement(Integer idtypestrucTypeequipement) {
        this.idtypestrucTypeequipement = idtypestrucTypeequipement;
    }

    public Integer getIdtypestrucTypeequipement() {
        return idtypestrucTypeequipement;
    }

    public void setIdtypestrucTypeequipement(Integer idtypestrucTypeequipement) {
        this.idtypestrucTypeequipement = idtypestrucTypeequipement;
    }

    @XmlTransient
    public List<Equipementtraceur> getEquipementtraceurList() {
        return equipementtraceurList;
    }

    public void setEquipementtraceurList(List<Equipementtraceur> equipementtraceurList) {
        this.equipementtraceurList = equipementtraceurList;
    }

    public Typeequipementtraceur getIdtypeequipementtraceur() {
        return idtypeequipementtraceur;
    }

    public void setIdtypeequipementtraceur(Typeequipementtraceur idtypeequipementtraceur) {
        this.idtypeequipementtraceur = idtypeequipementtraceur;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypestrucTypeequipement != null ? idtypestrucTypeequipement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypestrucTypeequipement)) {
            return false;
        }
        TypestrucTypeequipement other = (TypestrucTypeequipement) object;
        if ((this.idtypestrucTypeequipement == null && other.idtypestrucTypeequipement != null) || (this.idtypestrucTypeequipement != null && !this.idtypestrucTypeequipement.equals(other.idtypestrucTypeequipement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypestrucTypeequipement[ idtypestrucTypeequipement=" + idtypestrucTypeequipement + " ]";
    }
    
}
