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
    @NamedQuery(name = "Appreciation.findAll", query = "SELECT a FROM Appreciation a"),
    @NamedQuery(name = "Appreciation.findByAppreciation", query = "SELECT a FROM Appreciation a WHERE a.appreciation = :appreciation"),
    @NamedQuery(name = "Appreciation.findByNomFr", query = "SELECT a FROM Appreciation a WHERE a.nomFr = :nomFr"),
    @NamedQuery(name = "Appreciation.findByNomEn", query = "SELECT a FROM Appreciation a WHERE a.nomEn = :nomEn")})
public class Appreciation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer appreciation;
    @Size(max = 2147483647)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;

    public Appreciation() {
    }

    public Appreciation(Integer appreciation) {
        this.appreciation = appreciation;
    }

    public Integer getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Integer appreciation) {
        this.appreciation = appreciation;
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
        hash += (appreciation != null ? appreciation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appreciation)) {
            return false;
        }
        Appreciation other = (Appreciation) object;
        if ((this.appreciation == null && other.appreciation != null) || (this.appreciation != null && !this.appreciation.equals(other.appreciation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Appreciation[ appreciation=" + appreciation + " ]";
    }
    
}
