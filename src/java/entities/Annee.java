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
    @NamedQuery(name = "Annee.findAll", query = "SELECT a FROM Annee a"),
    @NamedQuery(name = "Annee.findByIdannee", query = "SELECT a FROM Annee a WHERE a.idannee = :idannee"),
    @NamedQuery(name = "Annee.findByNom", query = "SELECT a FROM Annee a WHERE a.nom = :nom"),
    @NamedQuery(name = "Annee.findByChoix", query = "SELECT a FROM Annee a WHERE a.choix = :choix"),
    @NamedQuery(name = "Annee.findByEtat", query = "SELECT a FROM Annee a WHERE a.etat = :etat"),
    @NamedQuery(name = "Annee.findByDateEnregistre", query = "SELECT a FROM Annee a WHERE a.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Annee.findByDerniereModif", query = "SELECT a FROM Annee a WHERE a.derniereModif = :derniereModif"),
    @NamedQuery(name = "Annee.findByCibles", query = "SELECT a FROM Annee a WHERE a.cibles = :cibles"),
    @NamedQuery(name = "Annee.findByDepense", query = "SELECT a FROM Annee a WHERE a.depense = :depense"),
    @NamedQuery(name = "Annee.findByRecette", query = "SELECT a FROM Annee a WHERE a.recette = :recette"),
    @NamedQuery(name = "Annee.findByPopfosa", query = "SELECT a FROM Annee a WHERE a.popfosa = :popfosa"),
    @NamedQuery(name = "Annee.findByMape", query = "SELECT a FROM Annee a WHERE a.mape = :mape"),
    @NamedQuery(name = "Annee.findByCiblehorizon", query = "SELECT a FROM Annee a WHERE a.ciblehorizon = :ciblehorizon"),
    @NamedQuery(name = "Annee.findByPlannifie", query = "SELECT a FROM Annee a WHERE a.plannifie = :plannifie"),
    @NamedQuery(name = "Annee.findByChronogramme", query = "SELECT a FROM Annee a WHERE a.chronogramme = :chronogramme"),
    @NamedQuery(name = "Annee.findByProjectionrecette", query = "SELECT a FROM Annee a WHERE a.projectionrecette = :projectionrecette"),
    @NamedQuery(name = "Annee.findByHistoriques", query = "SELECT a FROM Annee a WHERE a.historiques = :historiques")})
public class Annee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idannee;
    @Size(max = 254)
    private String nom;
    private Boolean choix;
    @Size(max = 50)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    private Boolean cibles;
    private Boolean depense;
    private Boolean recette;
    private Boolean popfosa;
    private Boolean mape;
    private Boolean ciblehorizon;
    private Boolean plannifie;
    private Boolean chronogramme;
    private Boolean projectionrecette;
    private Boolean historiques;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Population> populationList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<MedicamenttraceurStructure> medicamenttraceurStructureList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Chronogramme> chronogrammeList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<IndicateurDistrict> indicateurDistrictList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Recette> recetteList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Morbiditedistrict> morbiditedistrictList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Tachedistrict> tachedistrictList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Cible> cibleList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Projectionrecette> projectionrecetteList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<ActiviteStructure> activiteStructureList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Mapedistrict> mapedistrictList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Populationfosa> populationfosaList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Depense> depenseList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Rhs> rhsList;

    public Annee() {
    }

    public Annee(Integer idannee) {
        this.idannee = idannee;
    }

    public Integer getIdannee() {
        return idannee;
    }

    public void setIdannee(Integer idannee) {
        this.idannee = idannee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getChoix() {
        return choix;
    }

    public void setChoix(Boolean choix) {
        this.choix = choix;
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

    public Boolean getCibles() {
        return cibles;
    }

    public void setCibles(Boolean cibles) {
        this.cibles = cibles;
    }

    public Boolean getDepense() {
        return depense;
    }

    public void setDepense(Boolean depense) {
        this.depense = depense;
    }

    public Boolean getRecette() {
        return recette;
    }

    public void setRecette(Boolean recette) {
        this.recette = recette;
    }

    public Boolean getPopfosa() {
        return popfosa;
    }

    public void setPopfosa(Boolean popfosa) {
        this.popfosa = popfosa;
    }

    public Boolean getMape() {
        return mape;
    }

    public void setMape(Boolean mape) {
        this.mape = mape;
    }

    public Boolean getCiblehorizon() {
        return ciblehorizon;
    }

    public void setCiblehorizon(Boolean ciblehorizon) {
        this.ciblehorizon = ciblehorizon;
    }

    public Boolean getPlannifie() {
        return plannifie;
    }

    public void setPlannifie(Boolean plannifie) {
        this.plannifie = plannifie;
    }

    public Boolean getChronogramme() {
        return chronogramme;
    }

    public void setChronogramme(Boolean chronogramme) {
        this.chronogramme = chronogramme;
    }

    public Boolean getProjectionrecette() {
        return projectionrecette;
    }

    public void setProjectionrecette(Boolean projectionrecette) {
        this.projectionrecette = projectionrecette;
    }

    public Boolean getHistoriques() {
        return historiques;
    }

    public void setHistoriques(Boolean historiques) {
        this.historiques = historiques;
    }

    @XmlTransient
    public List<Population> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(List<Population> populationList) {
        this.populationList = populationList;
    }

    @XmlTransient
    public List<MedicamenttraceurStructure> getMedicamenttraceurStructureList() {
        return medicamenttraceurStructureList;
    }

    public void setMedicamenttraceurStructureList(List<MedicamenttraceurStructure> medicamenttraceurStructureList) {
        this.medicamenttraceurStructureList = medicamenttraceurStructureList;
    }

    @XmlTransient
    public List<Chronogramme> getChronogrammeList() {
        return chronogrammeList;
    }

    public void setChronogrammeList(List<Chronogramme> chronogrammeList) {
        this.chronogrammeList = chronogrammeList;
    }

    @XmlTransient
    public List<IndicateurDistrict> getIndicateurDistrictList() {
        return indicateurDistrictList;
    }

    public void setIndicateurDistrictList(List<IndicateurDistrict> indicateurDistrictList) {
        this.indicateurDistrictList = indicateurDistrictList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @XmlTransient
    public List<Morbiditedistrict> getMorbiditedistrictList() {
        return morbiditedistrictList;
    }

    public void setMorbiditedistrictList(List<Morbiditedistrict> morbiditedistrictList) {
        this.morbiditedistrictList = morbiditedistrictList;
    }

    @XmlTransient
    public List<Tachedistrict> getTachedistrictList() {
        return tachedistrictList;
    }

    public void setTachedistrictList(List<Tachedistrict> tachedistrictList) {
        this.tachedistrictList = tachedistrictList;
    }

    @XmlTransient
    public List<Cible> getCibleList() {
        return cibleList;
    }

    public void setCibleList(List<Cible> cibleList) {
        this.cibleList = cibleList;
    }

    @XmlTransient
    public List<Projectionrecette> getProjectionrecetteList() {
        return projectionrecetteList;
    }

    public void setProjectionrecetteList(List<Projectionrecette> projectionrecetteList) {
        this.projectionrecetteList = projectionrecetteList;
    }

    @XmlTransient
    public List<ActiviteStructure> getActiviteStructureList() {
        return activiteStructureList;
    }

    public void setActiviteStructureList(List<ActiviteStructure> activiteStructureList) {
        this.activiteStructureList = activiteStructureList;
    }

    @XmlTransient
    public List<Mapedistrict> getMapedistrictList() {
        return mapedistrictList;
    }

    public void setMapedistrictList(List<Mapedistrict> mapedistrictList) {
        this.mapedistrictList = mapedistrictList;
    }

    @XmlTransient
    public List<Populationfosa> getPopulationfosaList() {
        return populationfosaList;
    }

    public void setPopulationfosaList(List<Populationfosa> populationfosaList) {
        this.populationfosaList = populationfosaList;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @XmlTransient
    public List<Rhs> getRhsList() {
        return rhsList;
    }

    public void setRhsList(List<Rhs> rhsList) {
        this.rhsList = rhsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idannee != null ? idannee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annee)) {
            return false;
        }
        Annee other = (Annee) object;
        if ((this.idannee == null && other.idannee != null) || (this.idannee != null && !this.idannee.equals(other.idannee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Annee[ idannee=" + idannee + " ]";
    }
    
}
