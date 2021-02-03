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
@Table(name = "tablematieren1_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablematieren1District.findAll", query = "SELECT t FROM Tablematieren1District t"),
    @NamedQuery(name = "Tablematieren1District.findByIdtablematieren1District", query = "SELECT t FROM Tablematieren1District t WHERE t.idtablematieren1District = :idtablematieren1District"),
    @NamedQuery(name = "Tablematieren1District.findByNumeropage", query = "SELECT t FROM Tablematieren1District t WHERE t.numeropage = :numeropage")})
public class Tablematieren1District implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematieren1_district")
    private Long idtablematieren1District;
    private Integer numeropage;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idtablematiere_n1", referencedColumnName = "idtablematiere_n1")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN1 idtablematiereN1;

    public Tablematieren1District() {
    }

    public Tablematieren1District(Long idtablematieren1District) {
        this.idtablematieren1District = idtablematieren1District;
    }

    public Long getIdtablematieren1District() {
        return idtablematieren1District;
    }

    public void setIdtablematieren1District(Long idtablematieren1District) {
        this.idtablematieren1District = idtablematieren1District;
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

    public TablematiereN1 getIdtablematiereN1() {
        return idtablematiereN1;
    }

    public void setIdtablematiereN1(TablematiereN1 idtablematiereN1) {
        this.idtablematiereN1 = idtablematiereN1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematieren1District != null ? idtablematieren1District.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablematieren1District)) {
            return false;
        }
        Tablematieren1District other = (Tablematieren1District) object;
        if ((this.idtablematieren1District == null && other.idtablematieren1District != null) || (this.idtablematieren1District != null && !this.idtablematieren1District.equals(other.idtablematieren1District))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablematieren1District[ idtablematieren1District=" + idtablematieren1District + " ]";
    }
    
}
