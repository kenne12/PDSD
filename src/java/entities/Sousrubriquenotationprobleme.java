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
    @NamedQuery(name = "Sousrubriquenotationprobleme.findAll", query = "SELECT s FROM Sousrubriquenotationprobleme s"),
    @NamedQuery(name = "Sousrubriquenotationprobleme.findByIdsousrubriquenotationprobleme", query = "SELECT s FROM Sousrubriquenotationprobleme s WHERE s.idsousrubriquenotationprobleme = :idsousrubriquenotationprobleme"),
    @NamedQuery(name = "Sousrubriquenotationprobleme.findByScoremax", query = "SELECT s FROM Sousrubriquenotationprobleme s WHERE s.scoremax = :scoremax"),
    @NamedQuery(name = "Sousrubriquenotationprobleme.findByNomFr", query = "SELECT s FROM Sousrubriquenotationprobleme s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Sousrubriquenotationprobleme.findByNomEn", query = "SELECT s FROM Sousrubriquenotationprobleme s WHERE s.nomEn = :nomEn")})
public class Sousrubriquenotationprobleme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsousrubriquenotationprobleme;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double scoremax;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idsousrubriquenotationprobleme", fetch = FetchType.LAZY)
    private List<Notationprobleme> notationproblemeList;
    @JoinColumn(name = "idrubriquenotationprobleme", referencedColumnName = "idrubriquenotationprobleme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquenotationprobleme idrubriquenotationprobleme;

    public Sousrubriquenotationprobleme() {
    }

    public Sousrubriquenotationprobleme(Integer idsousrubriquenotationprobleme) {
        this.idsousrubriquenotationprobleme = idsousrubriquenotationprobleme;
    }

    public Integer getIdsousrubriquenotationprobleme() {
        return idsousrubriquenotationprobleme;
    }

    public void setIdsousrubriquenotationprobleme(Integer idsousrubriquenotationprobleme) {
        this.idsousrubriquenotationprobleme = idsousrubriquenotationprobleme;
    }

    public Double getScoremax() {
        return scoremax;
    }

    public void setScoremax(Double scoremax) {
        this.scoremax = scoremax;
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
    public List<Notationprobleme> getNotationproblemeList() {
        return notationproblemeList;
    }

    public void setNotationproblemeList(List<Notationprobleme> notationproblemeList) {
        this.notationproblemeList = notationproblemeList;
    }

    public Rubriquenotationprobleme getIdrubriquenotationprobleme() {
        return idrubriquenotationprobleme;
    }

    public void setIdrubriquenotationprobleme(Rubriquenotationprobleme idrubriquenotationprobleme) {
        this.idrubriquenotationprobleme = idrubriquenotationprobleme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsousrubriquenotationprobleme != null ? idsousrubriquenotationprobleme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sousrubriquenotationprobleme)) {
            return false;
        }
        Sousrubriquenotationprobleme other = (Sousrubriquenotationprobleme) object;
        if ((this.idsousrubriquenotationprobleme == null && other.idsousrubriquenotationprobleme != null) || (this.idsousrubriquenotationprobleme != null && !this.idsousrubriquenotationprobleme.equals(other.idsousrubriquenotationprobleme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sousrubriquenotationprobleme[ idsousrubriquenotationprobleme=" + idsousrubriquenotationprobleme + " ]";
    }
    
}
