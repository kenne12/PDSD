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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Periode.findAll", query = "SELECT p FROM Periode p"),
    @NamedQuery(name = "Periode.findByIdperiode", query = "SELECT p FROM Periode p WHERE p.idperiode = :idperiode"),
    @NamedQuery(name = "Periode.findByNom", query = "SELECT p FROM Periode p WHERE p.nom = :nom"),
    @NamedQuery(name = "Periode.findByCode", query = "SELECT p FROM Periode p WHERE p.code = :code"),
    @NamedQuery(name = "Periode.findByDefaut", query = "SELECT p FROM Periode p WHERE p.defaut = :defaut")})
public class Periode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idperiode;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    private Boolean defaut;
    @OneToMany(mappedBy = "idperiode", fetch = FetchType.LAZY)
    private List<Document> documentList;
    @JoinColumn(name = "idperiodederattachement", referencedColumnName = "idperiodederattachement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodederattachement idperiodederattachement;
    @JoinColumn(name = "idperiodicite", referencedColumnName = "idperiodicite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodicite idperiodicite;
    @OneToMany(mappedBy = "idperiode", fetch = FetchType.LAZY)
    private List<Actualite> actualiteList;

    public Periode() {
    }

    public Periode(Integer idperiode) {
        this.idperiode = idperiode;
    }

    public Integer getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Integer idperiode) {
        this.idperiode = idperiode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDefaut() {
        return defaut;
    }

    public void setDefaut(Boolean defaut) {
        this.defaut = defaut;
    }

    @XmlTransient
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public Periodederattachement getIdperiodederattachement() {
        return idperiodederattachement;
    }

    public void setIdperiodederattachement(Periodederattachement idperiodederattachement) {
        this.idperiodederattachement = idperiodederattachement;
    }

    public Periodicite getIdperiodicite() {
        return idperiodicite;
    }

    public void setIdperiodicite(Periodicite idperiodicite) {
        this.idperiodicite = idperiodicite;
    }

    @XmlTransient
    public List<Actualite> getActualiteList() {
        return actualiteList;
    }

    public void setActualiteList(List<Actualite> actualiteList) {
        this.actualiteList = actualiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiode != null ? idperiode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periode)) {
            return false;
        }
        Periode other = (Periode) object;
        if ((this.idperiode == null && other.idperiode != null) || (this.idperiode != null && !this.idperiode.equals(other.idperiode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Periode[ idperiode=" + idperiode + " ]";
    }
    
}
