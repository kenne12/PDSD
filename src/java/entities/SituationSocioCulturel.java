/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "situation_socio_culturel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituationSocioCulturel.findAll", query = "SELECT s FROM SituationSocioCulturel s"),
    @NamedQuery(name = "SituationSocioCulturel.findByIdsituationsociocult", query = "SELECT s FROM SituationSocioCulturel s WHERE s.idsituationsociocult = :idsituationsociocult"),
    @NamedQuery(name = "SituationSocioCulturel.findByEducation", query = "SELECT s FROM SituationSocioCulturel s WHERE s.education = :education"),
    @NamedQuery(name = "SituationSocioCulturel.findByStatut", query = "SELECT s FROM SituationSocioCulturel s WHERE s.statut = :statut"),
    @NamedQuery(name = "SituationSocioCulturel.findByFacteurculturel", query = "SELECT s FROM SituationSocioCulturel s WHERE s.facteurculturel = :facteurculturel"),
    @NamedQuery(name = "SituationSocioCulturel.findByFacteursociocult", query = "SELECT s FROM SituationSocioCulturel s WHERE s.facteursociocult = :facteursociocult")})
public class SituationSocioCulturel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsituationsociocult;
    @Size(max = 2147483647)
    private String education;
    @Size(max = 2147483647)
    private String statut;
    @Size(max = 2147483647)
    private String facteurculturel;
    @Size(max = 2147483647)
    private String facteursociocult;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;

    public SituationSocioCulturel() {
    }

    public SituationSocioCulturel(Integer idsituationsociocult) {
        this.idsituationsociocult = idsituationsociocult;
    }

    public Integer getIdsituationsociocult() {
        return idsituationsociocult;
    }

    public void setIdsituationsociocult(Integer idsituationsociocult) {
        this.idsituationsociocult = idsituationsociocult;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getFacteurculturel() {
        return facteurculturel;
    }

    public void setFacteurculturel(String facteurculturel) {
        this.facteurculturel = facteurculturel;
    }

    public String getFacteursociocult() {
        return facteursociocult;
    }

    public void setFacteursociocult(String facteursociocult) {
        this.facteursociocult = facteursociocult;
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
        hash += (idsituationsociocult != null ? idsituationsociocult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituationSocioCulturel)) {
            return false;
        }
        SituationSocioCulturel other = (SituationSocioCulturel) object;
        if ((this.idsituationsociocult == null && other.idsituationsociocult != null) || (this.idsituationsociocult != null && !this.idsituationsociocult.equals(other.idsituationsociocult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SituationSocioCulturel[ idsituationsociocult=" + idsituationsociocult + " ]";
    }
    
}
