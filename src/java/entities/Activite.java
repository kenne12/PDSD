/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Activite.findAll", query = "SELECT a FROM Activite a"),
    @NamedQuery(name = "Activite.findByIdactivite", query = "SELECT a FROM Activite a WHERE a.idactivite = :idactivite"),
    @NamedQuery(name = "Activite.findByNom", query = "SELECT a FROM Activite a WHERE a.nom = :nom"),
    @NamedQuery(name = "Activite.findByResponsable", query = "SELECT a FROM Activite a WHERE a.responsable = :responsable"),
    @NamedQuery(name = "Activite.findByCoutglobal", query = "SELECT a FROM Activite a WHERE a.coutglobal = :coutglobal"),
    @NamedQuery(name = "Activite.findByCoutunitaire", query = "SELECT a FROM Activite a WHERE a.coutunitaire = :coutunitaire")})
public class Activite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idactivite;
    @Size(max = 2147483647)
    private String nom;
    @Size(max = 254)
    private String responsable;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutglobal;
    private Double coutunitaire;
    @OneToMany(mappedBy = "idactivite", fetch = FetchType.LAZY)
    private List<ThematiqueActivite> thematiqueActiviteList;
    @OneToMany(mappedBy = "idactivite", fetch = FetchType.LAZY)
    private List<ActiviteElementCout> activiteElementCoutList;
    @OneToMany(mappedBy = "idactivite", fetch = FetchType.LAZY)
    private List<Chronogramme> chronogrammeList;
    @OneToMany(mappedBy = "idactivitePdsd", fetch = FetchType.LAZY)
    private List<ActiviteTraceurPdsd> activiteTraceurPdsdList;
    @JoinColumn(name = "ididobjectif_opp", referencedColumnName = "idobjectif_opp_district")
    @ManyToOne(fetch = FetchType.LAZY)
    private ObjectifOppDistrict ididobjectifOpp;
    @JoinColumn(name = "idprobleme", referencedColumnName = "idprobleme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Probleme idprobleme;
    @JoinColumn(name = "idresultatattendu", referencedColumnName = "idresultat_attendu_district")
    @ManyToOne(fetch = FetchType.LAZY)
    private ResultatAttenduDistrict idresultatattendu;
    @JoinColumn(name = "idsourcefi", referencedColumnName = "idsourcefi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefi;
    @JoinColumn(name = "idtypeactivite", referencedColumnName = "idtypeactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeactivite idtypeactivite;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;
    @OneToMany(mappedBy = "idactivite", fetch = FetchType.LAZY)
    private List<ActiviteStructure> activiteStructureList;

    public Activite() {
    }

    public Activite(Long idactivite) {
        this.idactivite = idactivite;
    }

    public Long getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Long idactivite) {
        this.idactivite = idactivite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Double getCoutglobal() {
        return coutglobal;
    }

    public void setCoutglobal(Double coutglobal) {
        this.coutglobal = coutglobal;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    @XmlTransient
    public List<ThematiqueActivite> getThematiqueActiviteList() {
        return thematiqueActiviteList;
    }

    public void setThematiqueActiviteList(List<ThematiqueActivite> thematiqueActiviteList) {
        this.thematiqueActiviteList = thematiqueActiviteList;
    }

    @XmlTransient
    public List<ActiviteElementCout> getActiviteElementCoutList() {
        return activiteElementCoutList;
    }

    public void setActiviteElementCoutList(List<ActiviteElementCout> activiteElementCoutList) {
        this.activiteElementCoutList = activiteElementCoutList;
    }

    @XmlTransient
    public List<Chronogramme> getChronogrammeList() {
        return chronogrammeList;
    }

    public void setChronogrammeList(List<Chronogramme> chronogrammeList) {
        this.chronogrammeList = chronogrammeList;
    }

    @XmlTransient
    public List<ActiviteTraceurPdsd> getActiviteTraceurPdsdList() {
        return activiteTraceurPdsdList;
    }

    public void setActiviteTraceurPdsdList(List<ActiviteTraceurPdsd> activiteTraceurPdsdList) {
        this.activiteTraceurPdsdList = activiteTraceurPdsdList;
    }

    public ObjectifOppDistrict getIdidobjectifOpp() {
        return ididobjectifOpp;
    }

    public void setIdidobjectifOpp(ObjectifOppDistrict ididobjectifOpp) {
        this.ididobjectifOpp = ididobjectifOpp;
    }

    public Probleme getIdprobleme() {
        return idprobleme;
    }

    public void setIdprobleme(Probleme idprobleme) {
        this.idprobleme = idprobleme;
    }

    public ResultatAttenduDistrict getIdresultatattendu() {
        return idresultatattendu;
    }

    public void setIdresultatattendu(ResultatAttenduDistrict idresultatattendu) {
        this.idresultatattendu = idresultatattendu;
    }

    public Sourcefinancement getIdsourcefi() {
        return idsourcefi;
    }

    public void setIdsourcefi(Sourcefinancement idsourcefi) {
        this.idsourcefi = idsourcefi;
    }

    public Typeactivite getIdtypeactivite() {
        return idtypeactivite;
    }

    public void setIdtypeactivite(Typeactivite idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @XmlTransient
    public List<ActiviteStructure> getActiviteStructureList() {
        return activiteStructureList;
    }

    public void setActiviteStructureList(List<ActiviteStructure> activiteStructureList) {
        this.activiteStructureList = activiteStructureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactivite != null ? idactivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.idactivite == null && other.idactivite != null) || (this.idactivite != null && !this.idactivite.equals(other.idactivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Activite[ idactivite=" + idactivite + " ]";
    }
    
}
