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
@Table(name = "tablematieren2_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablematieren2District.findAll", query = "SELECT t FROM Tablematieren2District t"),
    @NamedQuery(name = "Tablematieren2District.findByIdtablematieren2District", query = "SELECT t FROM Tablematieren2District t WHERE t.idtablematieren2District = :idtablematieren2District"),
    @NamedQuery(name = "Tablematieren2District.findByNumeropage", query = "SELECT t FROM Tablematieren2District t WHERE t.numeropage = :numeropage")})
public class Tablematieren2District implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematieren2_district")
    private Long idtablematieren2District;
    private Integer numeropage;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idtablematiere_n2", referencedColumnName = "idtablematiere_n2")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN2 idtablematiereN2;

    public Tablematieren2District() {
    }

    public Tablematieren2District(Long idtablematieren2District) {
        this.idtablematieren2District = idtablematieren2District;
    }

    public Long getIdtablematieren2District() {
        return idtablematieren2District;
    }

    public void setIdtablematieren2District(Long idtablematieren2District) {
        this.idtablematieren2District = idtablematieren2District;
    }

    public Integer getNumeropage() {
        return numeropage;
    }

    public void setNumeropage(Integer numeropage) {
        this.numeropage = numeropage;
    }

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        this.iddistrict = iddistrict;
    }

    public TablematiereN2 getIdtablematiereN2() {
        return idtablematiereN2;
    }

    public void setIdtablematiereN2(TablematiereN2 idtablematiereN2) {
        this.idtablematiereN2 = idtablematiereN2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematieren2District != null ? idtablematieren2District.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablematieren2District)) {
            return false;
        }
        Tablematieren2District other = (Tablematieren2District) object;
        if ((this.idtablematieren2District == null && other.idtablematieren2District != null) || (this.idtablematieren2District != null && !this.idtablematieren2District.equals(other.idtablematieren2District))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablematieren2District[ idtablematieren2District=" + idtablematieren2District + " ]";
    }
    
}
