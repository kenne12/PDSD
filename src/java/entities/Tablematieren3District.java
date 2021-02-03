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
@Table(name = "tablematieren3_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablematieren3District.findAll", query = "SELECT t FROM Tablematieren3District t"),
    @NamedQuery(name = "Tablematieren3District.findByIdtablematieren3District", query = "SELECT t FROM Tablematieren3District t WHERE t.idtablematieren3District = :idtablematieren3District"),
    @NamedQuery(name = "Tablematieren3District.findByNumeropage", query = "SELECT t FROM Tablematieren3District t WHERE t.numeropage = :numeropage")})
public class Tablematieren3District implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematieren3_district")
    private Long idtablematieren3District;
    private Integer numeropage;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;
    @JoinColumn(name = "idtablematiere_n3", referencedColumnName = "idtablematiere_n3")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN3 idtablematiereN3;

    public Tablematieren3District() {
    }

    public Tablematieren3District(Long idtablematieren3District) {
        this.idtablematieren3District = idtablematieren3District;
    }

    public Long getIdtablematieren3District() {
        return idtablematieren3District;
    }

    public void setIdtablematieren3District(Long idtablematieren3District) {
        this.idtablematieren3District = idtablematieren3District;
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

    public TablematiereN3 getIdtablematiereN3() {
        return idtablematiereN3;
    }

    public void setIdtablematiereN3(TablematiereN3 idtablematiereN3) {
        this.idtablematiereN3 = idtablematiereN3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematieren3District != null ? idtablematieren3District.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablematieren3District)) {
            return false;
        }
        Tablematieren3District other = (Tablematieren3District) object;
        if ((this.idtablematieren3District == null && other.idtablematieren3District != null) || (this.idtablematieren3District != null && !this.idtablematieren3District.equals(other.idtablematieren3District))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablematieren3District[ idtablematieren3District=" + idtablematieren3District + " ]";
    }
    
}
