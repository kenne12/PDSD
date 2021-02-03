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
    @NamedQuery(name = "Groupeacteur.findAll", query = "SELECT g FROM Groupeacteur g"),
    @NamedQuery(name = "Groupeacteur.findByIdgroupeacteur", query = "SELECT g FROM Groupeacteur g WHERE g.idgroupeacteur = :idgroupeacteur"),
    @NamedQuery(name = "Groupeacteur.findByNomFr", query = "SELECT g FROM Groupeacteur g WHERE g.nomFr = :nomFr"),
    @NamedQuery(name = "Groupeacteur.findByCode", query = "SELECT g FROM Groupeacteur g WHERE g.code = :code"),
    @NamedQuery(name = "Groupeacteur.findByNomEn", query = "SELECT g FROM Groupeacteur g WHERE g.nomEn = :nomEn")})
public class Groupeacteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idgroupeacteur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idgroupeacteur", fetch = FetchType.LAZY)
    private List<Acteur> acteurList;

    public Groupeacteur() {
    }

    public Groupeacteur(Integer idgroupeacteur) {
        this.idgroupeacteur = idgroupeacteur;
    }

    public Integer getIdgroupeacteur() {
        return idgroupeacteur;
    }

    public void setIdgroupeacteur(Integer idgroupeacteur) {
        this.idgroupeacteur = idgroupeacteur;
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

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Acteur> getActeurList() {
        return acteurList;
    }

    public void setActeurList(List<Acteur> acteurList) {
        this.acteurList = acteurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgroupeacteur != null ? idgroupeacteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupeacteur)) {
            return false;
        }
        Groupeacteur other = (Groupeacteur) object;
        if ((this.idgroupeacteur == null && other.idgroupeacteur != null) || (this.idgroupeacteur != null && !this.idgroupeacteur.equals(other.idgroupeacteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupeacteur[ idgroupeacteur=" + idgroupeacteur + " ]";
    }
    
}
