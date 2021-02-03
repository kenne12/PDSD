/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
    @NamedQuery(name = "Resultatattendu.findAll", query = "SELECT r FROM Resultatattendu r"),
    @NamedQuery(name = "Resultatattendu.findByIdresultatattendu", query = "SELECT r FROM Resultatattendu r WHERE r.idresultatattendu = :idresultatattendu"),
    @NamedQuery(name = "Resultatattendu.findByResultatFr", query = "SELECT r FROM Resultatattendu r WHERE r.resultatFr = :resultatFr"),
    @NamedQuery(name = "Resultatattendu.findByResultatEn", query = "SELECT r FROM Resultatattendu r WHERE r.resultatEn = :resultatEn")})
public class Resultatattendu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idresultatattendu;
    @Size(max = 1000)
    @Column(name = "resultat_fr")
    private String resultatFr;
    @Size(max = 1000)
    @Column(name = "resultat_en")
    private String resultatEn;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;

    public Resultatattendu() {
    }

    public Resultatattendu(Integer idresultatattendu) {
        this.idresultatattendu = idresultatattendu;
    }

    public Integer getIdresultatattendu() {
        return idresultatattendu;
    }

    public void setIdresultatattendu(Integer idresultatattendu) {
        this.idresultatattendu = idresultatattendu;
    }

    public String getResultatFr() {
        return resultatFr;
    }

    public void setResultatFr(String resultatFr) {
        this.resultatFr = resultatFr;
    }

    public String getResultatEn() {
        return resultatEn;
    }

    public void setResultatEn(String resultatEn) {
        this.resultatEn = resultatEn;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresultatattendu != null ? idresultatattendu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultatattendu)) {
            return false;
        }
        Resultatattendu other = (Resultatattendu) object;
        if ((this.idresultatattendu == null && other.idresultatattendu != null) || (this.idresultatattendu != null && !this.idresultatattendu.equals(other.idresultatattendu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Resultatattendu[ idresultatattendu=" + idresultatattendu + " ]";
    }
    
}
