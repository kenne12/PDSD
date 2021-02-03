/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
    @NamedQuery(name = "Structure.findAll", query = "SELECT s FROM Structure s"),
    @NamedQuery(name = "Structure.findByIdstructure", query = "SELECT s FROM Structure s WHERE s.idstructure = :idstructure"),
    @NamedQuery(name = "Structure.findByNomFr", query = "SELECT s FROM Structure s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Structure.findByCode", query = "SELECT s FROM Structure s WHERE s.code = :code"),
    @NamedQuery(name = "Structure.findByGpsnord", query = "SELECT s FROM Structure s WHERE s.gpsnord = :gpsnord"),
    @NamedQuery(name = "Structure.findByGpssud", query = "SELECT s FROM Structure s WHERE s.gpssud = :gpssud"),
    @NamedQuery(name = "Structure.findByActive", query = "SELECT s FROM Structure s WHERE s.active = :active"),
    @NamedQuery(name = "Structure.findByEtat", query = "SELECT s FROM Structure s WHERE s.etat = :etat"),
    @NamedQuery(name = "Structure.findByDateEnregistre", query = "SELECT s FROM Structure s WHERE s.dateEnregistre = :dateEnregistre"),
    @NamedQuery(name = "Structure.findByDerniereModif", query = "SELECT s FROM Structure s WHERE s.derniereModif = :derniereModif"),
    @NamedQuery(name = "Structure.findByDistance", query = "SELECT s FROM Structure s WHERE s.distance = :distance"),
    @NamedQuery(name = "Structure.findByLeader", query = "SELECT s FROM Structure s WHERE s.leader = :leader"),
    @NamedQuery(name = "Structure.findByNomEn", query = "SELECT s FROM Structure s WHERE s.nomEn = :nomEn"),
    @NamedQuery(name = "Structure.findByConsolide", query = "SELECT s FROM Structure s WHERE s.consolide = :consolide")})
public class Structure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idstructure;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String code;
    private BigInteger gpsnord;
    private BigInteger gpssud;
    private Boolean active;
    @Size(max = 50)
    private String etat;
    @Column(name = "date_enregistre")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistre;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double distance;
    private Boolean leader;
    @Size(max = 255)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean consolide;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Equipementtraceur> equipementtraceurList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<MedicamenttraceurStructure> medicamenttraceurStructureList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Recette> recetteList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Projectionrecette> projectionrecetteList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Gouvernancedistrict> gouvernancedistrictList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<ActiviteStructure> activiteStructureList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Populationfosa> populationfosaList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Informationsanitairedistrict> informationsanitairedistrictList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Depense> depenseList;
    @JoinColumn(name = "idadresse", referencedColumnName = "id_adresse")
    @ManyToOne(fetch = FetchType.LAZY)
    private Adresse idadresse;
    @JoinColumn(name = "idairesante", referencedColumnName = "idairesante")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airesante idairesante;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idinstitution", referencedColumnName = "idinstitution")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution idinstitution;
    @JoinColumn(name = "idniveaupyramide", referencedColumnName = "idniveaupyramide")
    @ManyToOne(fetch = FetchType.LAZY)
    private Niveaupyramide idniveaupyramide;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idstatutstructure", referencedColumnName = "idstatutstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Statutstructure idstatutstructure;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Infrastructure> infrastructureList;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private List<Rhs> rhsList;

    public Structure() {
    }

    public Structure(Integer idstructure) {
        this.idstructure = idstructure;
    }

    public Integer getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Integer idstructure) {
        this.idstructure = idstructure;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigInteger getGpsnord() {
        return gpsnord;
    }

    public void setGpsnord(BigInteger gpsnord) {
        this.gpsnord = gpsnord;
    }

    public BigInteger getGpssud() {
        return gpssud;
    }

    public void setGpssud(BigInteger gpssud) {
        this.gpssud = gpssud;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public Boolean getConsolide() {
        return consolide;
    }

    public void setConsolide(Boolean consolide) {
        this.consolide = consolide;
    }

    @XmlTransient
    public List<Equipementtraceur> getEquipementtraceurList() {
        return equipementtraceurList;
    }

    public void setEquipementtraceurList(List<Equipementtraceur> equipementtraceurList) {
        this.equipementtraceurList = equipementtraceurList;
    }

    @XmlTransient
    public List<MedicamenttraceurStructure> getMedicamenttraceurStructureList() {
        return medicamenttraceurStructureList;
    }

    public void setMedicamenttraceurStructureList(List<MedicamenttraceurStructure> medicamenttraceurStructureList) {
        this.medicamenttraceurStructureList = medicamenttraceurStructureList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @XmlTransient
    public List<Projectionrecette> getProjectionrecetteList() {
        return projectionrecetteList;
    }

    public void setProjectionrecetteList(List<Projectionrecette> projectionrecetteList) {
        this.projectionrecetteList = projectionrecetteList;
    }

    @XmlTransient
    public List<Gouvernancedistrict> getGouvernancedistrictList() {
        return gouvernancedistrictList;
    }

    public void setGouvernancedistrictList(List<Gouvernancedistrict> gouvernancedistrictList) {
        this.gouvernancedistrictList = gouvernancedistrictList;
    }

    @XmlTransient
    public List<ActiviteStructure> getActiviteStructureList() {
        return activiteStructureList;
    }

    public void setActiviteStructureList(List<ActiviteStructure> activiteStructureList) {
        this.activiteStructureList = activiteStructureList;
    }

    @XmlTransient
    public List<Populationfosa> getPopulationfosaList() {
        return populationfosaList;
    }

    public void setPopulationfosaList(List<Populationfosa> populationfosaList) {
        this.populationfosaList = populationfosaList;
    }

    @XmlTransient
    public List<Informationsanitairedistrict> getInformationsanitairedistrictList() {
        return informationsanitairedistrictList;
    }

    public void setInformationsanitairedistrictList(List<Informationsanitairedistrict> informationsanitairedistrictList) {
        this.informationsanitairedistrictList = informationsanitairedistrictList;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    public Adresse getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(Adresse idadresse) {
        this.idadresse = idadresse;
    }

    public Airesante getIdairesante() {
        return idairesante;
    }

    public void setIdairesante(Airesante idairesante) {
        this.idairesante = idairesante;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Institution getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Institution idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Niveaupyramide getIdniveaupyramide() {
        return idniveaupyramide;
    }

    public void setIdniveaupyramide(Niveaupyramide idniveaupyramide) {
        this.idniveaupyramide = idniveaupyramide;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Statutstructure getIdstatutstructure() {
        return idstatutstructure;
    }

    public void setIdstatutstructure(Statutstructure idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @XmlTransient
    public List<Infrastructure> getInfrastructureList() {
        return infrastructureList;
    }

    public void setInfrastructureList(List<Infrastructure> infrastructureList) {
        this.infrastructureList = infrastructureList;
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
        hash += (idstructure != null ? idstructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Structure)) {
            return false;
        }
        Structure other = (Structure) object;
        if ((this.idstructure == null && other.idstructure != null) || (this.idstructure != null && !this.idstructure.equals(other.idstructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Structure[ idstructure=" + idstructure + " ]";
    }
    
}
