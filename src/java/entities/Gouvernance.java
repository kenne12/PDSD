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
import javax.persistence.Id;
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
    @NamedQuery(name = "Gouvernance.findAll", query = "SELECT g FROM Gouvernance g"),
    @NamedQuery(name = "Gouvernance.findByIdgouvernance", query = "SELECT g FROM Gouvernance g WHERE g.idgouvernance = :idgouvernance"),
    @NamedQuery(name = "Gouvernance.findByNomFr", query = "SELECT g FROM Gouvernance g WHERE g.nomFr = :nomFr"),
    @NamedQuery(name = "Gouvernance.findByNomEn", query = "SELECT g FROM Gouvernance g WHERE g.nomEn = :nomEn")})
public class Gouvernance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idgouvernance;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;

    public Gouvernance() {
    }

    public Gouvernance(Integer idgouvernance) {
        this.idgouvernance = idgouvernance;
    }

    public Integer getIdgouvernance() {
        return idgouvernance;
    }

    public void setIdgouvernance(Integer idgouvernance) {
        this.idgouvernance = idgouvernance;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgouvernance != null ? idgouvernance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gouvernance)) {
            return false;
        }
        Gouvernance other = (Gouvernance) object;
        if ((this.idgouvernance == null && other.idgouvernance != null) || (this.idgouvernance != null && !this.idgouvernance.equals(other.idgouvernance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Gouvernance[ idgouvernance=" + idgouvernance + " ]";
    }
    
}
