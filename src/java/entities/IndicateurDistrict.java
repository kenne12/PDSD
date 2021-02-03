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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "indicateur_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicateurDistrict.findAll", query = "SELECT i FROM IndicateurDistrict i"),
    @NamedQuery(name = "IndicateurDistrict.findByIdindicateurDistrict", query = "SELECT i FROM IndicateurDistrict i WHERE i.idindicateurDistrict = :idindicateurDistrict"),
    @NamedQuery(name = "IndicateurDistrict.findByValeur", query = "SELECT i FROM IndicateurDistrict i WHERE i.valeur = :valeur"),
    @NamedQuery(name = "IndicateurDistrict.findByAcquis", query = "SELECT i FROM IndicateurDistrict i WHERE i.acquis = :acquis"),
    @NamedQuery(name = "IndicateurDistrict.findByProbleme", query = "SELECT i FROM IndicateurDistrict i WHERE i.probleme = :probleme"),
    @NamedQuery(name = "IndicateurDistrict.findByObservation", query = "SELECT i FROM IndicateurDistrict i WHERE i.observation = :observation"),
    @NamedQuery(name = "IndicateurDistrict.findByIdperiode", query = "SELECT i FROM IndicateurDistrict i WHERE i.idperiode = :idperiode"),
    @NamedQuery(name = "IndicateurDistrict.findByObjectifOperationnel", query = "SELECT i FROM IndicateurDistrict i WHERE i.objectifOperationnel = :objectifOperationnel"),
    @NamedQuery(name = "IndicateurDistrict.findByCause", query = "SELECT i FROM IndicateurDistrict i WHERE i.cause = :cause")})
public class IndicateurDistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idindicateur_district")
    private Long idindicateurDistrict;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    @Size(max = 254)
    private String acquis;
    @Size(max = 2147483647)
    private String probleme;
    @Size(max = 2147483647)
    private String observation;
    private Integer idperiode;
    @Size(max = 2147483647)
    @Column(name = "objectif_operationnel")
    private String objectifOperationnel;
    @Size(max = 2147483647)
    private String cause;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idobservation", referencedColumnName = "idobservation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Observation idobservation;
    @OneToMany(mappedBy = "idindicateurDistrict", fetch = FetchType.LAZY)
    private List<Probleme> problemeList;

    public IndicateurDistrict() {
    }

    public IndicateurDistrict(Long idindicateurDistrict) {
        this.idindicateurDistrict = idindicateurDistrict;
    }

    public Long getIdindicateurDistrict() {
        return idindicateurDistrict;
    }

    public void setIdindicateurDistrict(Long idindicateurDistrict) {
        this.idindicateurDistrict = idindicateurDistrict;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public String getAcquis() {
        return acquis;
    }

    public void setAcquis(String acquis) {
        this.acquis = acquis;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Integer idperiode) {
        this.idperiode = idperiode;
    }

    public String getObjectifOperationnel() {
        return objectifOperationnel;
    }

    public void setObjectifOperationnel(String objectifOperationnel) {
        this.objectifOperationnel = objectifOperationnel;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Observation getIdobservation() {
        return idobservation;
    }

    public void setIdobservation(Observation idobservation) {
        this.idobservation = idobservation;
    }

    @XmlTransient
    public List<Probleme> getProblemeList() {
        return problemeList;
    }

    public void setProblemeList(List<Probleme> problemeList) {
        this.problemeList = problemeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindicateurDistrict != null ? idindicateurDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicateurDistrict)) {
            return false;
        }
        IndicateurDistrict other = (IndicateurDistrict) object;
        if ((this.idindicateurDistrict == null && other.idindicateurDistrict != null) || (this.idindicateurDistrict != null && !this.idindicateurDistrict.equals(other.idindicateurDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IndicateurDistrict[ idindicateurDistrict=" + idindicateurDistrict + " ]";
    }
    
}
