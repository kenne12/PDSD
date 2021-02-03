/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByIddocument", query = "SELECT d FROM Document d WHERE d.iddocument = :iddocument"),
    @NamedQuery(name = "Document.findByTitredocumentFr", query = "SELECT d FROM Document d WHERE d.titredocumentFr = :titredocumentFr"),
    @NamedQuery(name = "Document.findByResumedocumentFr", query = "SELECT d FROM Document d WHERE d.resumedocumentFr = :resumedocumentFr"),
    @NamedQuery(name = "Document.findByDocumentpapier", query = "SELECT d FROM Document d WHERE d.documentpapier = :documentpapier"),
    @NamedQuery(name = "Document.findByDatedocument", query = "SELECT d FROM Document d WHERE d.datedocument = :datedocument"),
    @NamedQuery(name = "Document.findByAffiche", query = "SELECT d FROM Document d WHERE d.affiche = :affiche"),
    @NamedQuery(name = "Document.findByIdregion", query = "SELECT d FROM Document d WHERE d.idregion = :idregion"),
    @NamedQuery(name = "Document.findByIddistrict", query = "SELECT d FROM Document d WHERE d.iddistrict = :iddistrict"),
    @NamedQuery(name = "Document.findByTitredocumentEn", query = "SELECT d FROM Document d WHERE d.titredocumentEn = :titredocumentEn"),
    @NamedQuery(name = "Document.findByResumedocumentEn", query = "SELECT d FROM Document d WHERE d.resumedocumentEn = :resumedocumentEn")})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer iddocument;
    @Size(max = 2147483647)
    @Column(name = "titredocument_fr")
    private String titredocumentFr;
    @Size(max = 2147483647)
    @Column(name = "resumedocument_fr")
    private String resumedocumentFr;
    @Size(max = 255)
    private String documentpapier;
    @Temporal(TemporalType.DATE)
    private Date datedocument;
    @Size(max = 2147483647)
    private String affiche;
    private Integer idregion;
    private Integer iddistrict;
    @Size(max = 255)
    @Column(name = "titredocument_en")
    private String titredocumentEn;
    @Size(max = 2147483647)
    @Column(name = "resumedocument_en")
    private String resumedocumentEn;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;

    public Document() {
    }

    public Document(Integer iddocument) {
        this.iddocument = iddocument;
    }

    public Integer getIddocument() {
        return iddocument;
    }

    public void setIddocument(Integer iddocument) {
        this.iddocument = iddocument;
    }

    public String getTitredocumentFr() {
        return titredocumentFr;
    }

    public void setTitredocumentFr(String titredocumentFr) {
        this.titredocumentFr = titredocumentFr;
    }

    public String getResumedocumentFr() {
        return resumedocumentFr;
    }

    public void setResumedocumentFr(String resumedocumentFr) {
        this.resumedocumentFr = resumedocumentFr;
    }

    public String getDocumentpapier() {
        return documentpapier;
    }

    public void setDocumentpapier(String documentpapier) {
        this.documentpapier = documentpapier;
    }

    public Date getDatedocument() {
        return datedocument;
    }

    public void setDatedocument(Date datedocument) {
        this.datedocument = datedocument;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public Integer getIdregion() {
        return idregion;
    }

    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public String getTitredocumentEn() {
        return titredocumentEn;
    }

    public void setTitredocumentEn(String titredocumentEn) {
        this.titredocumentEn = titredocumentEn;
    }

    public String getResumedocumentEn() {
        return resumedocumentEn;
    }

    public void setResumedocumentEn(String resumedocumentEn) {
        this.resumedocumentEn = resumedocumentEn;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddocument != null ? iddocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.iddocument == null && other.iddocument != null) || (this.iddocument != null && !this.iddocument.equals(other.iddocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Document[ iddocument=" + iddocument + " ]";
    }
    
}
