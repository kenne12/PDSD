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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "activite_traceur_pdsd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteTraceurPdsd.findAll", query = "SELECT a FROM ActiviteTraceurPdsd a"),
    @NamedQuery(name = "ActiviteTraceurPdsd.findByIdactiviteTraceurPdsd", query = "SELECT a FROM ActiviteTraceurPdsd a WHERE a.idactiviteTraceurPdsd = :idactiviteTraceurPdsd")})
public class ActiviteTraceurPdsd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_traceur_pdsd")
    private Long idactiviteTraceurPdsd;
    @JoinColumn(name = "idactivite_pdsd", referencedColumnName = "idactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activite idactivitePdsd;
    @JoinColumn(name = "idactivite_traceur", referencedColumnName = "idactivite_traceur")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActiviteTraceur idactiviteTraceur;

    public ActiviteTraceurPdsd() {
    }

    public ActiviteTraceurPdsd(Long idactiviteTraceurPdsd) {
        this.idactiviteTraceurPdsd = idactiviteTraceurPdsd;
    }

    public Long getIdactiviteTraceurPdsd() {
        return idactiviteTraceurPdsd;
    }

    public void setIdactiviteTraceurPdsd(Long idactiviteTraceurPdsd) {
        this.idactiviteTraceurPdsd = idactiviteTraceurPdsd;
    }

    public Activite getIdactivitePdsd() {
        return idactivitePdsd;
    }

    public void setIdactivitePdsd(Activite idactivitePdsd) {
        this.idactivitePdsd = idactivitePdsd;
    }

    public ActiviteTraceur getIdactiviteTraceur() {
        return idactiviteTraceur;
    }

    public void setIdactiviteTraceur(ActiviteTraceur idactiviteTraceur) {
        this.idactiviteTraceur = idactiviteTraceur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactiviteTraceurPdsd != null ? idactiviteTraceurPdsd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteTraceurPdsd)) {
            return false;
        }
        ActiviteTraceurPdsd other = (ActiviteTraceurPdsd) object;
        if ((this.idactiviteTraceurPdsd == null && other.idactiviteTraceurPdsd != null) || (this.idactiviteTraceurPdsd != null && !this.idactiviteTraceurPdsd.equals(other.idactiviteTraceurPdsd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteTraceurPdsd[ idactiviteTraceurPdsd=" + idactiviteTraceurPdsd + " ]";
    }
    
}
