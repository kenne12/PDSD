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
    @NamedQuery(name = "Commentairetab.findAll", query = "SELECT c FROM Commentairetab c"),
    @NamedQuery(name = "Commentairetab.findByIdcommentairetab", query = "SELECT c FROM Commentairetab c WHERE c.idcommentairetab = :idcommentairetab"),
    @NamedQuery(name = "Commentairetab.findByCommentaire", query = "SELECT c FROM Commentairetab c WHERE c.commentaire = :commentaire"),
    @NamedQuery(name = "Commentairetab.findByIdannee", query = "SELECT c FROM Commentairetab c WHERE c.idannee = :idannee"),
    @NamedQuery(name = "Commentairetab.findByNumerotab", query = "SELECT c FROM Commentairetab c WHERE c.numerotab = :numerotab")})
public class Commentairetab implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcommentairetab;
    @Size(max = 2147483647)
    private String commentaire;
    private Integer idannee;
    private Integer numerotab;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;

    public Commentairetab() {
    }

    public Commentairetab(Integer idcommentairetab) {
        this.idcommentairetab = idcommentairetab;
    }

    public Integer getIdcommentairetab() {
        return idcommentairetab;
    }

    public void setIdcommentairetab(Integer idcommentairetab) {
        this.idcommentairetab = idcommentairetab;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getIdannee() {
        return idannee;
    }

    public void setIdannee(Integer idannee) {
        this.idannee = idannee;
    }

    public Integer getNumerotab() {
        return numerotab;
    }

    public void setNumerotab(Integer numerotab) {
        this.numerotab = numerotab;
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
        hash += (idcommentairetab != null ? idcommentairetab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentairetab)) {
            return false;
        }
        Commentairetab other = (Commentairetab) object;
        if ((this.idcommentairetab == null && other.idcommentairetab != null) || (this.idcommentairetab != null && !this.idcommentairetab.equals(other.idcommentairetab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commentairetab[ idcommentairetab=" + idcommentairetab + " ]";
    }
    
}
