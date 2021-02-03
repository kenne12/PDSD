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
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByIdAdresse", query = "SELECT a FROM Adresse a WHERE a.idAdresse = :idAdresse"),
    @NamedQuery(name = "Adresse.findByContact", query = "SELECT a FROM Adresse a WHERE a.contact = :contact"),
    @NamedQuery(name = "Adresse.findByFax", query = "SELECT a FROM Adresse a WHERE a.fax = :fax"),
    @NamedQuery(name = "Adresse.findByEmail", query = "SELECT a FROM Adresse a WHERE a.email = :email"),
    @NamedQuery(name = "Adresse.findBySiteWeb", query = "SELECT a FROM Adresse a WHERE a.siteWeb = :siteWeb"),
    @NamedQuery(name = "Adresse.findByBp", query = "SELECT a FROM Adresse a WHERE a.bp = :bp")})
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_adresse")
    private Long idAdresse;
    @Size(max = 1024)
    private String contact;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 1024)
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 1024)
    private String email;
    @Size(max = 1024)
    @Column(name = "site_web")
    private String siteWeb;
    @Size(max = 2147483647)
    private String bp;
    @OneToMany(mappedBy = "idadresse", fetch = FetchType.LAZY)
    private List<Structure> structureList;
    @JoinColumn(name = "idquartier", referencedColumnName = "idquartier")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quartier idquartier;
    @JoinColumn(name = "idrue", referencedColumnName = "idrue")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rue idrue;

    public Adresse() {
    }

    public Adresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Long getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    public Quartier getIdquartier() {
        return idquartier;
    }

    public void setIdquartier(Quartier idquartier) {
        this.idquartier = idquartier;
    }

    public Rue getIdrue() {
        return idrue;
    }

    public void setIdrue(Rue idrue) {
        this.idrue = idrue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Adresse[ idAdresse=" + idAdresse + " ]";
    }
    
}
