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
    @NamedQuery(name = "Indicateur.findAll", query = "SELECT i FROM Indicateur i"),
    @NamedQuery(name = "Indicateur.findByIdindicateur", query = "SELECT i FROM Indicateur i WHERE i.idindicateur = :idindicateur"),
    @NamedQuery(name = "Indicateur.findByCodeindicateur", query = "SELECT i FROM Indicateur i WHERE i.codeindicateur = :codeindicateur"),
    @NamedQuery(name = "Indicateur.findByNomFr", query = "SELECT i FROM Indicateur i WHERE i.nomFr = :nomFr"),
    @NamedQuery(name = "Indicateur.findByObjectifFr", query = "SELECT i FROM Indicateur i WHERE i.objectifFr = :objectifFr"),
    @NamedQuery(name = "Indicateur.findByDescription", query = "SELECT i FROM Indicateur i WHERE i.description = :description"),
    @NamedQuery(name = "Indicateur.findByNumerateur", query = "SELECT i FROM Indicateur i WHERE i.numerateur = :numerateur"),
    @NamedQuery(name = "Indicateur.findByDenominateur", query = "SELECT i FROM Indicateur i WHERE i.denominateur = :denominateur"),
    @NamedQuery(name = "Indicateur.findByAnneebaseline", query = "SELECT i FROM Indicateur i WHERE i.anneebaseline = :anneebaseline"),
    @NamedQuery(name = "Indicateur.findByCoef", query = "SELECT i FROM Indicateur i WHERE i.coef = :coef"),
    @NamedQuery(name = "Indicateur.findByPrioritenationale", query = "SELECT i FROM Indicateur i WHERE i.prioritenationale = :prioritenationale"),
    @NamedQuery(name = "Indicateur.findByDiamdeno", query = "SELECT i FROM Indicateur i WHERE i.diamdeno = :diamdeno"),
    @NamedQuery(name = "Indicateur.findByDiamnum", query = "SELECT i FROM Indicateur i WHERE i.diamnum = :diamnum"),
    @NamedQuery(name = "Indicateur.findByDiamvalatt", query = "SELECT i FROM Indicateur i WHERE i.diamvalatt = :diamvalatt"),
    @NamedQuery(name = "Indicateur.findByDiamvalrel", query = "SELECT i FROM Indicateur i WHERE i.diamvalrel = :diamvalrel"),
    @NamedQuery(name = "Indicateur.findByCercle", query = "SELECT i FROM Indicateur i WHERE i.cercle = :cercle"),
    @NamedQuery(name = "Indicateur.findByCiblenationale", query = "SELECT i FROM Indicateur i WHERE i.ciblenationale = :ciblenationale"),
    @NamedQuery(name = "Indicateur.findByDistrict", query = "SELECT i FROM Indicateur i WHERE i.district = :district"),
    @NamedQuery(name = "Indicateur.findByRegion", query = "SELECT i FROM Indicateur i WHERE i.region = :region"),
    @NamedQuery(name = "Indicateur.findByModeleprobleme", query = "SELECT i FROM Indicateur i WHERE i.modeleprobleme = :modeleprobleme"),
    @NamedQuery(name = "Indicateur.findByModeleacquis", query = "SELECT i FROM Indicateur i WHERE i.modeleacquis = :modeleacquis"),
    @NamedQuery(name = "Indicateur.findByConditionreussiteFr", query = "SELECT i FROM Indicateur i WHERE i.conditionreussiteFr = :conditionreussiteFr"),
    @NamedQuery(name = "Indicateur.findByProblemepardefaut", query = "SELECT i FROM Indicateur i WHERE i.problemepardefaut = :problemepardefaut"),
    @NamedQuery(name = "Indicateur.findByBaseline", query = "SELECT i FROM Indicateur i WHERE i.baseline = :baseline"),
    @NamedQuery(name = "Indicateur.findByInverse", query = "SELECT i FROM Indicateur i WHERE i.inverse = :inverse"),
    @NamedQuery(name = "Indicateur.findByNomEn", query = "SELECT i FROM Indicateur i WHERE i.nomEn = :nomEn"),
    @NamedQuery(name = "Indicateur.findByObjectifEn", query = "SELECT i FROM Indicateur i WHERE i.objectifEn = :objectifEn"),
    @NamedQuery(name = "Indicateur.findByConditionreussiteEn", query = "SELECT i FROM Indicateur i WHERE i.conditionreussiteEn = :conditionreussiteEn")})
public class Indicateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idindicateur;
    @Size(max = 254)
    private String codeindicateur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    @Column(name = "objectif_fr")
    private String objectifFr;
    @Size(max = 2147483647)
    private String description;
    @Size(max = 254)
    private String numerateur;
    @Size(max = 254)
    private String denominateur;
    private Integer anneebaseline;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coef;
    private Boolean prioritenationale;
    private Double diamdeno;
    private Double diamnum;
    private Double diamvalatt;
    private Double diamvalrel;
    private Boolean cercle;
    private Double ciblenationale;
    private Boolean district;
    private Boolean region;
    @Size(max = 254)
    private String modeleprobleme;
    @Size(max = 254)
    private String modeleacquis;
    @Size(max = 254)
    @Column(name = "conditionreussite_fr")
    private String conditionreussiteFr;
    @Size(max = 255)
    private String problemepardefaut;
    private Double baseline;
    private Boolean inverse;
    @Size(max = 255)
    @Column(name = "nom_en")
    private String nomEn;
    @Size(max = 255)
    @Column(name = "objectif_en")
    private String objectifEn;
    @Size(max = 2147483647)
    @Column(name = "conditionreussite_en")
    private String conditionreussiteEn;
    @OneToMany(mappedBy = "idindicateur", fetch = FetchType.LAZY)
    private List<ActiviteDefault> activiteDefaultList;
    @OneToMany(mappedBy = "idindicateur", fetch = FetchType.LAZY)
    private List<Document> documentList;
    @OneToMany(mappedBy = "idindicateur", fetch = FetchType.LAZY)
    private List<ResultatAttenduDistrict> resultatAttenduDistrictList;
    @OneToMany(mappedBy = "idindicateur", fetch = FetchType.LAZY)
    private List<IndicateurDistrict> indicateurDistrictList;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorieindicateur idcategorie;
    @JoinColumn(name = "idinterventionpnds", referencedColumnName = "idinterventionpnds")
    @ManyToOne(fetch = FetchType.LAZY)
    private Interventionpnds idinterventionpnds;
    @JoinColumn(name = "idniveaucollecte", referencedColumnName = "idniveaucollecte")
    @ManyToOne(fetch = FetchType.LAZY)
    private Niveaucollecte idniveaucollecte;
    @JoinColumn(name = "idperiodicite", referencedColumnName = "idperiodicite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodicite idperiodicite;
    @JoinColumn(name = "idunite", referencedColumnName = "idunite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unite idunite;
    @OneToMany(mappedBy = "idindicateur", fetch = FetchType.LAZY)
    private List<Cible> cibleList;
    @OneToMany(mappedBy = "idindicateur", fetch = FetchType.LAZY)
    private List<Resultatattendu> resultatattenduList;

    public Indicateur() {
    }

    public Indicateur(Integer idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Integer getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Integer idindicateur) {
        this.idindicateur = idindicateur;
    }

    public String getCodeindicateur() {
        return codeindicateur;
    }

    public void setCodeindicateur(String codeindicateur) {
        this.codeindicateur = codeindicateur;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getObjectifFr() {
        return objectifFr;
    }

    public void setObjectifFr(String objectifFr) {
        this.objectifFr = objectifFr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumerateur() {
        return numerateur;
    }

    public void setNumerateur(String numerateur) {
        this.numerateur = numerateur;
    }

    public String getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(String denominateur) {
        this.denominateur = denominateur;
    }

    public Integer getAnneebaseline() {
        return anneebaseline;
    }

    public void setAnneebaseline(Integer anneebaseline) {
        this.anneebaseline = anneebaseline;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public Boolean getPrioritenationale() {
        return prioritenationale;
    }

    public void setPrioritenationale(Boolean prioritenationale) {
        this.prioritenationale = prioritenationale;
    }

    public Double getDiamdeno() {
        return diamdeno;
    }

    public void setDiamdeno(Double diamdeno) {
        this.diamdeno = diamdeno;
    }

    public Double getDiamnum() {
        return diamnum;
    }

    public void setDiamnum(Double diamnum) {
        this.diamnum = diamnum;
    }

    public Double getDiamvalatt() {
        return diamvalatt;
    }

    public void setDiamvalatt(Double diamvalatt) {
        this.diamvalatt = diamvalatt;
    }

    public Double getDiamvalrel() {
        return diamvalrel;
    }

    public void setDiamvalrel(Double diamvalrel) {
        this.diamvalrel = diamvalrel;
    }

    public Boolean getCercle() {
        return cercle;
    }

    public void setCercle(Boolean cercle) {
        this.cercle = cercle;
    }

    public Double getCiblenationale() {
        return ciblenationale;
    }

    public void setCiblenationale(Double ciblenationale) {
        this.ciblenationale = ciblenationale;
    }

    public Boolean getDistrict() {
        return district;
    }

    public void setDistrict(Boolean district) {
        this.district = district;
    }

    public Boolean getRegion() {
        return region;
    }

    public void setRegion(Boolean region) {
        this.region = region;
    }

    public String getModeleprobleme() {
        return modeleprobleme;
    }

    public void setModeleprobleme(String modeleprobleme) {
        this.modeleprobleme = modeleprobleme;
    }

    public String getModeleacquis() {
        return modeleacquis;
    }

    public void setModeleacquis(String modeleacquis) {
        this.modeleacquis = modeleacquis;
    }

    public String getConditionreussiteFr() {
        return conditionreussiteFr;
    }

    public void setConditionreussiteFr(String conditionreussiteFr) {
        this.conditionreussiteFr = conditionreussiteFr;
    }

    public String getProblemepardefaut() {
        return problemepardefaut;
    }

    public void setProblemepardefaut(String problemepardefaut) {
        this.problemepardefaut = problemepardefaut;
    }

    public Double getBaseline() {
        return baseline;
    }

    public void setBaseline(Double baseline) {
        this.baseline = baseline;
    }

    public Boolean getInverse() {
        return inverse;
    }

    public void setInverse(Boolean inverse) {
        this.inverse = inverse;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public String getObjectifEn() {
        return objectifEn;
    }

    public void setObjectifEn(String objectifEn) {
        this.objectifEn = objectifEn;
    }

    public String getConditionreussiteEn() {
        return conditionreussiteEn;
    }

    public void setConditionreussiteEn(String conditionreussiteEn) {
        this.conditionreussiteEn = conditionreussiteEn;
    }

    @XmlTransient
    public List<ActiviteDefault> getActiviteDefaultList() {
        return activiteDefaultList;
    }

    public void setActiviteDefaultList(List<ActiviteDefault> activiteDefaultList) {
        this.activiteDefaultList = activiteDefaultList;
    }

    @XmlTransient
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    @XmlTransient
    public List<ResultatAttenduDistrict> getResultatAttenduDistrictList() {
        return resultatAttenduDistrictList;
    }

    public void setResultatAttenduDistrictList(List<ResultatAttenduDistrict> resultatAttenduDistrictList) {
        this.resultatAttenduDistrictList = resultatAttenduDistrictList;
    }

    @XmlTransient
    public List<IndicateurDistrict> getIndicateurDistrictList() {
        return indicateurDistrictList;
    }

    public void setIndicateurDistrictList(List<IndicateurDistrict> indicateurDistrictList) {
        this.indicateurDistrictList = indicateurDistrictList;
    }

    public Categorieindicateur getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorieindicateur idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Interventionpnds getIdinterventionpnds() {
        return idinterventionpnds;
    }

    public void setIdinterventionpnds(Interventionpnds idinterventionpnds) {
        this.idinterventionpnds = idinterventionpnds;
    }

    public Niveaucollecte getIdniveaucollecte() {
        return idniveaucollecte;
    }

    public void setIdniveaucollecte(Niveaucollecte idniveaucollecte) {
        this.idniveaucollecte = idniveaucollecte;
    }

    public Periodicite getIdperiodicite() {
        return idperiodicite;
    }

    public void setIdperiodicite(Periodicite idperiodicite) {
        this.idperiodicite = idperiodicite;
    }

    public Unite getIdunite() {
        return idunite;
    }

    public void setIdunite(Unite idunite) {
        this.idunite = idunite;
    }

    @XmlTransient
    public List<Cible> getCibleList() {
        return cibleList;
    }

    public void setCibleList(List<Cible> cibleList) {
        this.cibleList = cibleList;
    }

    @XmlTransient
    public List<Resultatattendu> getResultatattenduList() {
        return resultatattenduList;
    }

    public void setResultatattenduList(List<Resultatattendu> resultatattenduList) {
        this.resultatattenduList = resultatattenduList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindicateur != null ? idindicateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicateur)) {
            return false;
        }
        Indicateur other = (Indicateur) object;
        if ((this.idindicateur == null && other.idindicateur != null) || (this.idindicateur != null && !this.idindicateur.equals(other.idindicateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Indicateur[ idindicateur=" + idindicateur + " ]";
    }
    
}
