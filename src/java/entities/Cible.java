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
    @NamedQuery(name = "Cible.findAll", query = "SELECT c FROM Cible c"),
    @NamedQuery(name = "Cible.findByIdcible", query = "SELECT c FROM Cible c WHERE c.idcible = :idcible"),
    @NamedQuery(name = "Cible.findByValeur", query = "SELECT c FROM Cible c WHERE c.valeur = :valeur"),
    @NamedQuery(name = "Cible.findByValeurrealisee", query = "SELECT c FROM Cible c WHERE c.valeurrealisee = :valeurrealisee"),
    @NamedQuery(name = "Cible.findByCommentaire", query = "SELECT c FROM Cible c WHERE c.commentaire = :commentaire"),
    @NamedQuery(name = "Cible.findByRecommandation", query = "SELECT c FROM Cible c WHERE c.recommandation = :recommandation"),
    @NamedQuery(name = "Cible.findByEtat", query = "SELECT c FROM Cible c WHERE c.etat = :etat")})
public class Cible implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcible;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Double valeurrealisee;
    @Size(max = 2147483647)
    private String commentaire;
    @Size(max = 2147483647)
    private String recommandation;
    private Boolean etat;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idprobleme", referencedColumnName = "idprobleme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Probleme idprobleme;

    public Cible() {
    }

    public Cible(Integer idcible) {
        this.idcible = idcible;
    }

    public Integer getIdcible() {
        return idcible;
    }

    public void setIdcible(Integer idcible) {
        this.idcible = idcible;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Double getValeurrealisee() {
        return valeurrealisee;
    }

    public void setValeurrealisee(Double valeurrealisee) {
        this.valeurrealisee = valeurrealisee;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getRecommandation() {
        return recommandation;
    }

    public void setRecommandation(String recommandation) {
        this.recommandation = recommandation;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
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

    public Probleme getIdprobleme() {
        return idprobleme;
    }

    public void setIdprobleme(Probleme idprobleme) {
        this.idprobleme = idprobleme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcible != null ? idcible.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cible)) {
            return false;
        }
        Cible other = (Cible) object;
        if ((this.idcible == null && other.idcible != null) || (this.idcible != null && !this.idcible.equals(other.idcible))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cible[ idcible=" + idcible + " ]";
    }
    
}
