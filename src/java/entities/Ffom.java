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
    @NamedQuery(name = "Ffom.findAll", query = "SELECT f FROM Ffom f"),
    @NamedQuery(name = "Ffom.findByIdffom", query = "SELECT f FROM Ffom f WHERE f.idffom = :idffom"),
    @NamedQuery(name = "Ffom.findByForce", query = "SELECT f FROM Ffom f WHERE f.force = :force"),
    @NamedQuery(name = "Ffom.findByFaiblesse", query = "SELECT f FROM Ffom f WHERE f.faiblesse = :faiblesse"),
    @NamedQuery(name = "Ffom.findByOpportunite", query = "SELECT f FROM Ffom f WHERE f.opportunite = :opportunite"),
    @NamedQuery(name = "Ffom.findByMenace", query = "SELECT f FROM Ffom f WHERE f.menace = :menace")})
public class Ffom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idffom;
    @Size(max = 2147483647)
    private String force;
    @Size(max = 2147483647)
    private String faiblesse;
    @Size(max = 2147483647)
    private String opportunite;
    @Size(max = 2147483647)
    private String menace;
    @OneToMany(mappedBy = "idffom", fetch = FetchType.LAZY)
    private List<Menace> menaceList;
    @OneToMany(mappedBy = "idffom", fetch = FetchType.LAZY)
    private List<Faiblesse> faiblesseList;
    @OneToMany(mappedBy = "idffom", fetch = FetchType.LAZY)
    private List<Force> forceList;
    @OneToMany(mappedBy = "idffom", fetch = FetchType.LAZY)
    private List<Opportunite> opportuniteList;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idpilier", referencedColumnName = "idpilier")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pilier idpilier;

    public Ffom() {
    }

    public Ffom(Integer idffom) {
        this.idffom = idffom;
    }

    public Integer getIdffom() {
        return idffom;
    }

    public void setIdffom(Integer idffom) {
        this.idffom = idffom;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getFaiblesse() {
        return faiblesse;
    }

    public void setFaiblesse(String faiblesse) {
        this.faiblesse = faiblesse;
    }

    public String getOpportunite() {
        return opportunite;
    }

    public void setOpportunite(String opportunite) {
        this.opportunite = opportunite;
    }

    public String getMenace() {
        return menace;
    }

    public void setMenace(String menace) {
        this.menace = menace;
    }

    @XmlTransient
    public List<Menace> getMenaceList() {
        return menaceList;
    }

    public void setMenaceList(List<Menace> menaceList) {
        this.menaceList = menaceList;
    }

    @XmlTransient
    public List<Faiblesse> getFaiblesseList() {
        return faiblesseList;
    }

    public void setFaiblesseList(List<Faiblesse> faiblesseList) {
        this.faiblesseList = faiblesseList;
    }

    @XmlTransient
    public List<Force> getForceList() {
        return forceList;
    }

    public void setForceList(List<Force> forceList) {
        this.forceList = forceList;
    }

    @XmlTransient
    public List<Opportunite> getOpportuniteList() {
        return opportuniteList;
    }

    public void setOpportuniteList(List<Opportunite> opportuniteList) {
        this.opportuniteList = opportuniteList;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Pilier getIdpilier() {
        return idpilier;
    }

    public void setIdpilier(Pilier idpilier) {
        this.idpilier = idpilier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idffom != null ? idffom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ffom)) {
            return false;
        }
        Ffom other = (Ffom) object;
        if ((this.idffom == null && other.idffom != null) || (this.idffom != null && !this.idffom.equals(other.idffom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ffom[ idffom=" + idffom + " ]";
    }
    
}
