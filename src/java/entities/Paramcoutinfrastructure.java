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
    @NamedQuery(name = "Paramcoutinfrastructure.findAll", query = "SELECT p FROM Paramcoutinfrastructure p"),
    @NamedQuery(name = "Paramcoutinfrastructure.findByIdparamcoutinfrastructure", query = "SELECT p FROM Paramcoutinfrastructure p WHERE p.idparamcoutinfrastructure = :idparamcoutinfrastructure"),
    @NamedQuery(name = "Paramcoutinfrastructure.findByCoutunitaire", query = "SELECT p FROM Paramcoutinfrastructure p WHERE p.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "Paramcoutinfrastructure.findByDefaultoperationFr", query = "SELECT p FROM Paramcoutinfrastructure p WHERE p.defaultoperationFr = :defaultoperationFr"),
    @NamedQuery(name = "Paramcoutinfrastructure.findByDefaultoperationEn", query = "SELECT p FROM Paramcoutinfrastructure p WHERE p.defaultoperationEn = :defaultoperationEn")})
public class Paramcoutinfrastructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idparamcoutinfrastructure;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    @Size(max = 2147483647)
    @Column(name = "defaultoperation_fr")
    private String defaultoperationFr;
    @Size(max = 255)
    @Column(name = "defaultoperation_en")
    private String defaultoperationEn;
    @JoinColumn(name = "idetatinfrastructure", referencedColumnName = "idetatinfrastructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etatinfrastructure idetatinfrastructure;
    @JoinColumn(name = "idtypeinfrastructure", referencedColumnName = "idtypeinfrastructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeinfrastructure idtypeinfrastructure;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;

    public Paramcoutinfrastructure() {
    }

    public Paramcoutinfrastructure(Integer idparamcoutinfrastructure) {
        this.idparamcoutinfrastructure = idparamcoutinfrastructure;
    }

    public Integer getIdparamcoutinfrastructure() {
        return idparamcoutinfrastructure;
    }

    public void setIdparamcoutinfrastructure(Integer idparamcoutinfrastructure) {
        this.idparamcoutinfrastructure = idparamcoutinfrastructure;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public String getDefaultoperationFr() {
        return defaultoperationFr;
    }

    public void setDefaultoperationFr(String defaultoperationFr) {
        this.defaultoperationFr = defaultoperationFr;
    }

    public String getDefaultoperationEn() {
        return defaultoperationEn;
    }

    public void setDefaultoperationEn(String defaultoperationEn) {
        this.defaultoperationEn = defaultoperationEn;
    }

    public Etatinfrastructure getIdetatinfrastructure() {
        return idetatinfrastructure;
    }

    public void setIdetatinfrastructure(Etatinfrastructure idetatinfrastructure) {
        this.idetatinfrastructure = idetatinfrastructure;
    }

    public Typeinfrastructure getIdtypeinfrastructure() {
        return idtypeinfrastructure;
    }

    public void setIdtypeinfrastructure(Typeinfrastructure idtypeinfrastructure) {
        this.idtypeinfrastructure = idtypeinfrastructure;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparamcoutinfrastructure != null ? idparamcoutinfrastructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paramcoutinfrastructure)) {
            return false;
        }
        Paramcoutinfrastructure other = (Paramcoutinfrastructure) object;
        if ((this.idparamcoutinfrastructure == null && other.idparamcoutinfrastructure != null) || (this.idparamcoutinfrastructure != null && !this.idparamcoutinfrastructure.equals(other.idparamcoutinfrastructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paramcoutinfrastructure[ idparamcoutinfrastructure=" + idparamcoutinfrastructure + " ]";
    }
    
}
