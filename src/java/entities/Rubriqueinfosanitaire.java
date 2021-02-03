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
    @NamedQuery(name = "Rubriqueinfosanitaire.findAll", query = "SELECT r FROM Rubriqueinfosanitaire r"),
    @NamedQuery(name = "Rubriqueinfosanitaire.findByIdrubriqueinfosanitaire", query = "SELECT r FROM Rubriqueinfosanitaire r WHERE r.idrubriqueinfosanitaire = :idrubriqueinfosanitaire"),
    @NamedQuery(name = "Rubriqueinfosanitaire.findByNomFr", query = "SELECT r FROM Rubriqueinfosanitaire r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Rubriqueinfosanitaire.findByCode", query = "SELECT r FROM Rubriqueinfosanitaire r WHERE r.code = :code"),
    @NamedQuery(name = "Rubriqueinfosanitaire.findByNomEn", query = "SELECT r FROM Rubriqueinfosanitaire r WHERE r.nomEn = :nomEn")})
public class Rubriqueinfosanitaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriqueinfosanitaire;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idrubriqueinfosanitaire", fetch = FetchType.LAZY)
    private List<Informationsanitairedistrict> informationsanitairedistrictList;

    public Rubriqueinfosanitaire() {
    }

    public Rubriqueinfosanitaire(Integer idrubriqueinfosanitaire) {
        this.idrubriqueinfosanitaire = idrubriqueinfosanitaire;
    }

    public Integer getIdrubriqueinfosanitaire() {
        return idrubriqueinfosanitaire;
    }

    public void setIdrubriqueinfosanitaire(Integer idrubriqueinfosanitaire) {
        this.idrubriqueinfosanitaire = idrubriqueinfosanitaire;
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
    public List<Informationsanitairedistrict> getInformationsanitairedistrictList() {
        return informationsanitairedistrictList;
    }

    public void setInformationsanitairedistrictList(List<Informationsanitairedistrict> informationsanitairedistrictList) {
        this.informationsanitairedistrictList = informationsanitairedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriqueinfosanitaire != null ? idrubriqueinfosanitaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriqueinfosanitaire)) {
            return false;
        }
        Rubriqueinfosanitaire other = (Rubriqueinfosanitaire) object;
        if ((this.idrubriqueinfosanitaire == null && other.idrubriqueinfosanitaire != null) || (this.idrubriqueinfosanitaire != null && !this.idrubriqueinfosanitaire.equals(other.idrubriqueinfosanitaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriqueinfosanitaire[ idrubriqueinfosanitaire=" + idrubriqueinfosanitaire + " ]";
    }
    
}
