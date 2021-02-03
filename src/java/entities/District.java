/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "District.findAll", query = "SELECT d FROM District d"),
    @NamedQuery(name = "District.findByIddistrict", query = "SELECT d FROM District d WHERE d.iddistrict = :iddistrict"),
    @NamedQuery(name = "District.findByNomFr", query = "SELECT d FROM District d WHERE d.nomFr = :nomFr"),
    @NamedQuery(name = "District.findByCoordonnegps", query = "SELECT d FROM District d WHERE d.coordonnegps = :coordonnegps"),
    @NamedQuery(name = "District.findByCodedistrict", query = "SELECT d FROM District d WHERE d.codedistrict = :codedistrict"),
    @NamedQuery(name = "District.findByCarte", query = "SELECT d FROM District d WHERE d.carte = :carte"),
    @NamedQuery(name = "District.findByNomEn", query = "SELECT d FROM District d WHERE d.nomEn = :nomEn")})
public class District implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer iddistrict;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String coordonnegps;
    @Size(max = 254)
    private String codedistrict;
    @Size(max = 254)
    private String carte;
    @Size(max = 255)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Analysefinanciere> analysefinanciereList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Partiehaute> partiehauteList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<ObjectifOppDistrict> objectifOppDistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Structurecommunautaire> structurecommunautaireList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Population> populationList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<ResultatAttenduDistrict> resultatAttenduDistrictList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Utilisateurdistrict> utilisateurdistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<IndicateurDistrict> indicateurDistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Facteurdistrict> facteurdistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Airesante> airesanteList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Tablematieren3District> tablematieren3DistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Morbiditedistrict> morbiditedistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Hospitalisationdistrict> hospitalisationdistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Cible> cibleList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Patologiedistrict> patologiedistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Mortalitedistrict> mortalitedistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Gouvernancedistrict> gouvernancedistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Tablematieren1District> tablematieren1DistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Ffom> ffomList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Mapedistrict> mapedistrictList;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Tablematieren2District> tablematieren2DistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Actualite> actualiteList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Acteurdistrict> acteurdistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Informationsanitairedistrict> informationsanitairedistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<ListetableauDistrict> listetableauDistrictList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Structure> structureList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<Commentairetab> commentairetabList;
    @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
    private List<SituationSocioCulturel> situationSocioCulturelList;

    public District() {
    }

    public District(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getCoordonnegps() {
        return coordonnegps;
    }

    public void setCoordonnegps(String coordonnegps) {
        this.coordonnegps = coordonnegps;
    }

    public String getCodedistrict() {
        return codedistrict;
    }

    public void setCodedistrict(String codedistrict) {
        this.codedistrict = codedistrict;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String carte) {
        this.carte = carte;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Analysefinanciere> getAnalysefinanciereList() {
        return analysefinanciereList;
    }

    public void setAnalysefinanciereList(List<Analysefinanciere> analysefinanciereList) {
        this.analysefinanciereList = analysefinanciereList;
    }

    @XmlTransient
    public List<Partiehaute> getPartiehauteList() {
        return partiehauteList;
    }

    public void setPartiehauteList(List<Partiehaute> partiehauteList) {
        this.partiehauteList = partiehauteList;
    }

    @XmlTransient
    public List<ObjectifOppDistrict> getObjectifOppDistrictList() {
        return objectifOppDistrictList;
    }

    public void setObjectifOppDistrictList(List<ObjectifOppDistrict> objectifOppDistrictList) {
        this.objectifOppDistrictList = objectifOppDistrictList;
    }

    @XmlTransient
    public List<Structurecommunautaire> getStructurecommunautaireList() {
        return structurecommunautaireList;
    }

    public void setStructurecommunautaireList(List<Structurecommunautaire> structurecommunautaireList) {
        this.structurecommunautaireList = structurecommunautaireList;
    }

    @XmlTransient
    public List<Population> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(List<Population> populationList) {
        this.populationList = populationList;
    }

    @XmlTransient
    public List<ResultatAttenduDistrict> getResultatAttenduDistrictList() {
        return resultatAttenduDistrictList;
    }

    public void setResultatAttenduDistrictList(List<ResultatAttenduDistrict> resultatAttenduDistrictList) {
        this.resultatAttenduDistrictList = resultatAttenduDistrictList;
    }

    @XmlTransient
    public List<Utilisateurdistrict> getUtilisateurdistrictList() {
        return utilisateurdistrictList;
    }

    public void setUtilisateurdistrictList(List<Utilisateurdistrict> utilisateurdistrictList) {
        this.utilisateurdistrictList = utilisateurdistrictList;
    }

    @XmlTransient
    public List<IndicateurDistrict> getIndicateurDistrictList() {
        return indicateurDistrictList;
    }

    public void setIndicateurDistrictList(List<IndicateurDistrict> indicateurDistrictList) {
        this.indicateurDistrictList = indicateurDistrictList;
    }

    @XmlTransient
    public List<Facteurdistrict> getFacteurdistrictList() {
        return facteurdistrictList;
    }

    public void setFacteurdistrictList(List<Facteurdistrict> facteurdistrictList) {
        this.facteurdistrictList = facteurdistrictList;
    }

    @XmlTransient
    public List<Airesante> getAiresanteList() {
        return airesanteList;
    }

    public void setAiresanteList(List<Airesante> airesanteList) {
        this.airesanteList = airesanteList;
    }

    @XmlTransient
    public List<Tablematieren3District> getTablematieren3DistrictList() {
        return tablematieren3DistrictList;
    }

    public void setTablematieren3DistrictList(List<Tablematieren3District> tablematieren3DistrictList) {
        this.tablematieren3DistrictList = tablematieren3DistrictList;
    }

    @XmlTransient
    public List<Morbiditedistrict> getMorbiditedistrictList() {
        return morbiditedistrictList;
    }

    public void setMorbiditedistrictList(List<Morbiditedistrict> morbiditedistrictList) {
        this.morbiditedistrictList = morbiditedistrictList;
    }

    @XmlTransient
    public List<Hospitalisationdistrict> getHospitalisationdistrictList() {
        return hospitalisationdistrictList;
    }

    public void setHospitalisationdistrictList(List<Hospitalisationdistrict> hospitalisationdistrictList) {
        this.hospitalisationdistrictList = hospitalisationdistrictList;
    }

    @XmlTransient
    public List<Cible> getCibleList() {
        return cibleList;
    }

    public void setCibleList(List<Cible> cibleList) {
        this.cibleList = cibleList;
    }

    @XmlTransient
    public List<Patologiedistrict> getPatologiedistrictList() {
        return patologiedistrictList;
    }

    public void setPatologiedistrictList(List<Patologiedistrict> patologiedistrictList) {
        this.patologiedistrictList = patologiedistrictList;
    }

    @XmlTransient
    public List<Mortalitedistrict> getMortalitedistrictList() {
        return mortalitedistrictList;
    }

    public void setMortalitedistrictList(List<Mortalitedistrict> mortalitedistrictList) {
        this.mortalitedistrictList = mortalitedistrictList;
    }

    @XmlTransient
    public List<Gouvernancedistrict> getGouvernancedistrictList() {
        return gouvernancedistrictList;
    }

    public void setGouvernancedistrictList(List<Gouvernancedistrict> gouvernancedistrictList) {
        this.gouvernancedistrictList = gouvernancedistrictList;
    }

    @XmlTransient
    public List<Tablematieren1District> getTablematieren1DistrictList() {
        return tablematieren1DistrictList;
    }

    public void setTablematieren1DistrictList(List<Tablematieren1District> tablematieren1DistrictList) {
        this.tablematieren1DistrictList = tablematieren1DistrictList;
    }

    @XmlTransient
    public List<Ffom> getFfomList() {
        return ffomList;
    }

    public void setFfomList(List<Ffom> ffomList) {
        this.ffomList = ffomList;
    }

    @XmlTransient
    public List<Mapedistrict> getMapedistrictList() {
        return mapedistrictList;
    }

    public void setMapedistrictList(List<Mapedistrict> mapedistrictList) {
        this.mapedistrictList = mapedistrictList;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @XmlTransient
    public List<Tablematieren2District> getTablematieren2DistrictList() {
        return tablematieren2DistrictList;
    }

    public void setTablematieren2DistrictList(List<Tablematieren2District> tablematieren2DistrictList) {
        this.tablematieren2DistrictList = tablematieren2DistrictList;
    }

    @XmlTransient
    public List<Actualite> getActualiteList() {
        return actualiteList;
    }

    public void setActualiteList(List<Actualite> actualiteList) {
        this.actualiteList = actualiteList;
    }

    @XmlTransient
    public List<Acteurdistrict> getActeurdistrictList() {
        return acteurdistrictList;
    }

    public void setActeurdistrictList(List<Acteurdistrict> acteurdistrictList) {
        this.acteurdistrictList = acteurdistrictList;
    }

    @XmlTransient
    public List<Informationsanitairedistrict> getInformationsanitairedistrictList() {
        return informationsanitairedistrictList;
    }

    public void setInformationsanitairedistrictList(List<Informationsanitairedistrict> informationsanitairedistrictList) {
        this.informationsanitairedistrictList = informationsanitairedistrictList;
    }

    @XmlTransient
    public List<ListetableauDistrict> getListetableauDistrictList() {
        return listetableauDistrictList;
    }

    public void setListetableauDistrictList(List<ListetableauDistrict> listetableauDistrictList) {
        this.listetableauDistrictList = listetableauDistrictList;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @XmlTransient
    public List<Commentairetab> getCommentairetabList() {
        return commentairetabList;
    }

    public void setCommentairetabList(List<Commentairetab> commentairetabList) {
        this.commentairetabList = commentairetabList;
    }

    @XmlTransient
    public List<SituationSocioCulturel> getSituationSocioCulturelList() {
        return situationSocioCulturelList;
    }

    public void setSituationSocioCulturelList(List<SituationSocioCulturel> situationSocioCulturelList) {
        this.situationSocioCulturelList = situationSocioCulturelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistrict != null ? iddistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        if ((this.iddistrict == null && other.iddistrict != null) || (this.iddistrict != null && !this.iddistrict.equals(other.iddistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.District[ iddistrict=" + iddistrict + " ]";
    }
    
}
