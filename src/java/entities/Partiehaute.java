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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partiehaute.findAll", query = "SELECT p FROM Partiehaute p"),
    @NamedQuery(name = "Partiehaute.findByIdpartiehaute", query = "SELECT p FROM Partiehaute p WHERE p.idpartiehaute = :idpartiehaute"),
    @NamedQuery(name = "Partiehaute.findByIntroductionFr", query = "SELECT p FROM Partiehaute p WHERE p.introductionFr = :introductionFr"),
    @NamedQuery(name = "Partiehaute.findByPresentationgeneraleFr", query = "SELECT p FROM Partiehaute p WHERE p.presentationgeneraleFr = :presentationgeneraleFr"),
    @NamedQuery(name = "Partiehaute.findBySituationgeographiqueFr", query = "SELECT p FROM Partiehaute p WHERE p.situationgeographiqueFr = :situationgeographiqueFr"),
    @NamedQuery(name = "Partiehaute.findBySituationeconomiqueFr", query = "SELECT p FROM Partiehaute p WHERE p.situationeconomiqueFr = :situationeconomiqueFr"),
    @NamedQuery(name = "Partiehaute.findBySituationsocioculturelleFr", query = "SELECT p FROM Partiehaute p WHERE p.situationsocioculturelleFr = :situationsocioculturelleFr"),
    @NamedQuery(name = "Partiehaute.findByCarte", query = "SELECT p FROM Partiehaute p WHERE p.carte = :carte")})
public class Partiehaute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idpartiehaute;
    @Size(max = 2147483647)
    @Column(name = "introduction_fr")
    private String introductionFr;
    @Size(max = 2147483647)
    @Column(name = "presentationgenerale_fr")
    private String presentationgeneraleFr;
    @Size(max = 2147483647)
    @Column(name = "situationgeographique_fr")
    private String situationgeographiqueFr;
    @Size(max = 2147483647)
    @Column(name = "situationeconomique_fr")
    private String situationeconomiqueFr;
    @Size(max = 2147483647)
    @Column(name = "situationsocioculturelle_fr")
    private String situationsocioculturelleFr;
    @Size(max = 2147483647)
    private String carte;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;

    public Partiehaute() {
    }

    public Partiehaute(Integer idpartiehaute) {
        this.idpartiehaute = idpartiehaute;
    }

    public Integer getIdpartiehaute() {
        return idpartiehaute;
    }

    public void setIdpartiehaute(Integer idpartiehaute) {
        this.idpartiehaute = idpartiehaute;
    }

    public String getIntroductionFr() {
        return introductionFr;
    }

    public void setIntroductionFr(String introductionFr) {
        this.introductionFr = introductionFr;
    }

    public String getPresentationgeneraleFr() {
        return presentationgeneraleFr;
    }

    public void setPresentationgeneraleFr(String presentationgeneraleFr) {
        this.presentationgeneraleFr = presentationgeneraleFr;
    }

    public String getSituationgeographiqueFr() {
        return situationgeographiqueFr;
    }

    public void setSituationgeographiqueFr(String situationgeographiqueFr) {
        this.situationgeographiqueFr = situationgeographiqueFr;
    }

    public String getSituationeconomiqueFr() {
        return situationeconomiqueFr;
    }

    public void setSituationeconomiqueFr(String situationeconomiqueFr) {
        this.situationeconomiqueFr = situationeconomiqueFr;
    }

    public String getSituationsocioculturelleFr() {
        return situationsocioculturelleFr;
    }

    public void setSituationsocioculturelleFr(String situationsocioculturelleFr) {
        this.situationsocioculturelleFr = situationsocioculturelleFr;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String carte) {
        this.carte = carte;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpartiehaute != null ? idpartiehaute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partiehaute)) {
            return false;
        }
        Partiehaute other = (Partiehaute) object;
        if ((this.idpartiehaute == null && other.idpartiehaute != null) || (this.idpartiehaute != null && !this.idpartiehaute.equals(other.idpartiehaute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Partiehaute[ idpartiehaute=" + idpartiehaute + " ]";
    }
    
}
