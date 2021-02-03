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
    @NamedQuery(name = "Informationsanitairedistrict.findAll", query = "SELECT i FROM Informationsanitairedistrict i"),
    @NamedQuery(name = "Informationsanitairedistrict.findByIdinformationsanitairedistrict", query = "SELECT i FROM Informationsanitairedistrict i WHERE i.idinformationsanitairedistrict = :idinformationsanitairedistrict"),
    @NamedQuery(name = "Informationsanitairedistrict.findByValeur", query = "SELECT i FROM Informationsanitairedistrict i WHERE i.valeur = :valeur"),
    @NamedQuery(name = "Informationsanitairedistrict.findByPrcds", query = "SELECT i FROM Informationsanitairedistrict i WHERE i.prcds = :prcds")})
public class Informationsanitairedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idinformationsanitairedistrict;
    @Size(max = 2147483647)
    private String valeur;
    private Boolean prcds;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idrubriqueinfosanitaire", referencedColumnName = "idrubriqueinfosanitaire")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriqueinfosanitaire idrubriqueinfosanitaire;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Informationsanitairedistrict() {
    }

    public Informationsanitairedistrict(Integer idinformationsanitairedistrict) {
        this.idinformationsanitairedistrict = idinformationsanitairedistrict;
    }

    public Integer getIdinformationsanitairedistrict() {
        return idinformationsanitairedistrict;
    }

    public void setIdinformationsanitairedistrict(Integer idinformationsanitairedistrict) {
        this.idinformationsanitairedistrict = idinformationsanitairedistrict;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Boolean getPrcds() {
        return prcds;
    }

    public void setPrcds(Boolean prcds) {
        this.prcds = prcds;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Rubriqueinfosanitaire getIdrubriqueinfosanitaire() {
        return idrubriqueinfosanitaire;
    }

    public void setIdrubriqueinfosanitaire(Rubriqueinfosanitaire idrubriqueinfosanitaire) {
        this.idrubriqueinfosanitaire = idrubriqueinfosanitaire;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinformationsanitairedistrict != null ? idinformationsanitairedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informationsanitairedistrict)) {
            return false;
        }
        Informationsanitairedistrict other = (Informationsanitairedistrict) object;
        if ((this.idinformationsanitairedistrict == null && other.idinformationsanitairedistrict != null) || (this.idinformationsanitairedistrict != null && !this.idinformationsanitairedistrict.equals(other.idinformationsanitairedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Informationsanitairedistrict[ idinformationsanitairedistrict=" + idinformationsanitairedistrict + " ]";
    }
    
}
