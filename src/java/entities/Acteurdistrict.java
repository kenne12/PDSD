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
    @NamedQuery(name = "Acteurdistrict.findAll", query = "SELECT a FROM Acteurdistrict a"),
    @NamedQuery(name = "Acteurdistrict.findByIdacteurDistrict", query = "SELECT a FROM Acteurdistrict a WHERE a.idacteurDistrict = :idacteurDistrict"),
    @NamedQuery(name = "Acteurdistrict.findByObservation", query = "SELECT a FROM Acteurdistrict a WHERE a.observation = :observation")})
public class Acteurdistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idacteur_district")
    private Long idacteurDistrict;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "idacteur", referencedColumnName = "idacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Acteur idacteur;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;

    public Acteurdistrict() {
    }

    public Acteurdistrict(Long idacteurDistrict) {
        this.idacteurDistrict = idacteurDistrict;
    }

    public Long getIdacteurDistrict() {
        return idacteurDistrict;
    }

    public void setIdacteurDistrict(Long idacteurDistrict) {
        this.idacteurDistrict = idacteurDistrict;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Acteur getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(Acteur idacteur) {
        this.idacteur = idacteur;
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
        hash += (idacteurDistrict != null ? idacteurDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acteurdistrict)) {
            return false;
        }
        Acteurdistrict other = (Acteurdistrict) object;
        if ((this.idacteurDistrict == null && other.idacteurDistrict != null) || (this.idacteurDistrict != null && !this.idacteurDistrict.equals(other.idacteurDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Acteurdistrict[ idacteurDistrict=" + idacteurDistrict + " ]";
    }
    
}
