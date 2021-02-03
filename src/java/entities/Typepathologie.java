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
    @NamedQuery(name = "Typepathologie.findAll", query = "SELECT t FROM Typepathologie t"),
    @NamedQuery(name = "Typepathologie.findByIdtypepathologie", query = "SELECT t FROM Typepathologie t WHERE t.idtypepathologie = :idtypepathologie"),
    @NamedQuery(name = "Typepathologie.findByNomFr", query = "SELECT t FROM Typepathologie t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typepathologie.findByNomEn", query = "SELECT t FROM Typepathologie t WHERE t.nomEn = :nomEn")})
public class Typepathologie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypepathologie;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idtypepathologie", fetch = FetchType.LAZY)
    private List<Pathologie> pathologieList;
    @OneToMany(mappedBy = "idtypepathologie", fetch = FetchType.LAZY)
    private List<Morbidite> morbiditeList;

    public Typepathologie() {
    }

    public Typepathologie(Integer idtypepathologie) {
        this.idtypepathologie = idtypepathologie;
    }

    public Integer getIdtypepathologie() {
        return idtypepathologie;
    }

    public void setIdtypepathologie(Integer idtypepathologie) {
        this.idtypepathologie = idtypepathologie;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Pathologie> getPathologieList() {
        return pathologieList;
    }

    public void setPathologieList(List<Pathologie> pathologieList) {
        this.pathologieList = pathologieList;
    }

    @XmlTransient
    public List<Morbidite> getMorbiditeList() {
        return morbiditeList;
    }

    public void setMorbiditeList(List<Morbidite> morbiditeList) {
        this.morbiditeList = morbiditeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypepathologie != null ? idtypepathologie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typepathologie)) {
            return false;
        }
        Typepathologie other = (Typepathologie) object;
        if ((this.idtypepathologie == null && other.idtypepathologie != null) || (this.idtypepathologie != null && !this.idtypepathologie.equals(other.idtypepathologie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typepathologie[ idtypepathologie=" + idtypepathologie + " ]";
    }
    
}
