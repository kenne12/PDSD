/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Typestructure.findAll", query = "SELECT t FROM Typestructure t"),
    @NamedQuery(name = "Typestructure.findByIdtypestructure", query = "SELECT t FROM Typestructure t WHERE t.idtypestructure = :idtypestructure"),
    @NamedQuery(name = "Typestructure.findByNomFr", query = "SELECT t FROM Typestructure t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typestructure.findByDesignation", query = "SELECT t FROM Typestructure t WHERE t.designation = :designation"),
    @NamedQuery(name = "Typestructure.findByEtat", query = "SELECT t FROM Typestructure t WHERE t.etat = :etat"),
    @NamedQuery(name = "Typestructure.findByDateEnregistre", query = "SELECT t FROM Typestructure t WHERE t.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Typestructure.findByDerniereModif", query = "SELECT t FROM Typestructure t WHERE t.derniereModif = :derniereModif"),
    @NamedQuery(name = "Typestructure.findByInfrastructure", query = "SELECT t FROM Typestructure t WHERE t.infrastructure = :infrastructure"),
    @NamedQuery(name = "Typestructure.findByEquipement", query = "SELECT t FROM Typestructure t WHERE t.equipement = :equipement"),
    @NamedQuery(name = "Typestructure.findByNomEn", query = "SELECT t FROM Typestructure t WHERE t.nomEn = :nomEn"),
    @NamedQuery(name = "Typestructure.findByCentral", query = "SELECT t FROM Typestructure t WHERE t.central = :central"),
    @NamedQuery(name = "Typestructure.findByRegional", query = "SELECT t FROM Typestructure t WHERE t.regional = :regional"),
    @NamedQuery(name = "Typestructure.findByDistrict", query = "SELECT t FROM Typestructure t WHERE t.district = :district")})
public class Typestructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypestructure;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String designation;
    @Size(max = 50)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    private Boolean infrastructure;
    private Boolean equipement;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean central;
    private Boolean regional;
    private Boolean district;
    @ManyToMany(mappedBy = "typestructureList", fetch = FetchType.LAZY)
    private List<Typeequipementtraceur> typeequipementtraceurList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<ActiviteDefault> activiteDefaultList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<Paramcoutinfrastructure> paramcoutinfrastructureList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<Parametrecoutequipement> parametrecoutequipementList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<Activite> activiteList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<TypeinfraTypestruc> typeinfraTypestrucList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<TypestrucTypeequipement> typestrucTypeequipementList;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private List<Structure> structureList;

    public Typestructure() {
    }

    public Typestructure(Integer idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    public Integer getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Integer idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateEnregistre() {
        return dateEnregistre;
    }

    public void setDateEnregistre(Date dateEnregistre) {
        this.dateEnregistre = dateEnregistre;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public Boolean getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Boolean infrastructure) {
        this.infrastructure = infrastructure;
    }

    public Boolean getEquipement() {
        return equipement;
    }

    public void setEquipement(Boolean equipement) {
        this.equipement = equipement;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public Boolean getCentral() {
        return central;
    }

    public void setCentral(Boolean central) {
        this.central = central;
    }

    public Boolean getRegional() {
        return regional;
    }

    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

    public Boolean getDistrict() {
        return district;
    }

    public void setDistrict(Boolean district) {
        this.district = district;
    }

    @XmlTransient
    public List<Typeequipementtraceur> getTypeequipementtraceurList() {
        return typeequipementtraceurList;
    }

    public void setTypeequipementtraceurList(List<Typeequipementtraceur> typeequipementtraceurList) {
        this.typeequipementtraceurList = typeequipementtraceurList;
    }

    @XmlTransient
    public List<ActiviteDefault> getActiviteDefaultList() {
        return activiteDefaultList;
    }

    public void setActiviteDefaultList(List<ActiviteDefault> activiteDefaultList) {
        this.activiteDefaultList = activiteDefaultList;
    }

    @XmlTransient
    public List<Paramcoutinfrastructure> getParamcoutinfrastructureList() {
        return paramcoutinfrastructureList;
    }

    public void setParamcoutinfrastructureList(List<Paramcoutinfrastructure> paramcoutinfrastructureList) {
        this.paramcoutinfrastructureList = paramcoutinfrastructureList;
    }

    @XmlTransient
    public List<Parametrecoutequipement> getParametrecoutequipementList() {
        return parametrecoutequipementList;
    }

    public void setParametrecoutequipementList(List<Parametrecoutequipement> parametrecoutequipementList) {
        this.parametrecoutequipementList = parametrecoutequipementList;
    }

    @XmlTransient
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    @XmlTransient
    public List<TypeinfraTypestruc> getTypeinfraTypestrucList() {
        return typeinfraTypestrucList;
    }

    public void setTypeinfraTypestrucList(List<TypeinfraTypestruc> typeinfraTypestrucList) {
        this.typeinfraTypestrucList = typeinfraTypestrucList;
    }

    @XmlTransient
    public List<TypestrucTypeequipement> getTypestrucTypeequipementList() {
        return typestrucTypeequipementList;
    }

    public void setTypestrucTypeequipementList(List<TypestrucTypeequipement> typestrucTypeequipementList) {
        this.typestrucTypeequipementList = typestrucTypeequipementList;
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
        hash += (idtypestructure != null ? idtypestructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typestructure)) {
            return false;
        }
        Typestructure other = (Typestructure) object;
        if ((this.idtypestructure == null && other.idtypestructure != null) || (this.idtypestructure != null && !this.idtypestructure.equals(other.idtypestructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typestructure[ idtypestructure=" + idtypestructure + " ]";
    }
    
}
