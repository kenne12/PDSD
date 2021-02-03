/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeinfrastructure.findAll", query = "SELECT t FROM Typeinfrastructure t"),
    @NamedQuery(name = "Typeinfrastructure.findByIdtypeinfrastructure", query = "SELECT t FROM Typeinfrastructure t WHERE t.idtypeinfrastructure = :idtypeinfrastructure"),
    @NamedQuery(name = "Typeinfrastructure.findByNomFr", query = "SELECT t FROM Typeinfrastructure t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typeinfrastructure.findByNomEn", query = "SELECT t FROM Typeinfrastructure t WHERE t.nomEn = :nomEn"),
    @NamedQuery(name = "Typeinfrastructure.findByRegional", query = "SELECT t FROM Typeinfrastructure t WHERE t.regional = :regional"),
    @NamedQuery(name = "Typeinfrastructure.findByDistrict", query = "SELECT t FROM Typeinfrastructure t WHERE t.district = :district")})
public class Typeinfrastructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypeinfrastructure;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean regional;
    private Boolean district;
    @OneToMany(mappedBy = "idtypeinfrastructure", fetch = FetchType.LAZY)
    private List<Paramcoutinfrastructure> paramcoutinfrastructureList;
    @OneToMany(mappedBy = "idtypeinfrastructure", fetch = FetchType.LAZY)
    private List<TypeinfraTypestruc> typeinfraTypestrucList;

    public Typeinfrastructure() {
    }

    public Typeinfrastructure(Integer idtypeinfrastructure) {
        this.idtypeinfrastructure = idtypeinfrastructure;
    }

    public Integer getIdtypeinfrastructure() {
        return idtypeinfrastructure;
    }

    public void setIdtypeinfrastructure(Integer idtypeinfrastructure) {
        this.idtypeinfrastructure = idtypeinfrastructure;
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

    public Boolean getRegional() {
        return regional;
    }

    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

    public Boolean getDistrict() {
        return district;
    }

    public void setDistrict(Boolean district) {
        this.district = district;
    }

    @XmlTransient
    public List<Paramcoutinfrastructure> getParamcoutinfrastructureList() {
        return paramcoutinfrastructureList;
    }

    public void setParamcoutinfrastructureList(List<Paramcoutinfrastructure> paramcoutinfrastructureList) {
        this.paramcoutinfrastructureList = paramcoutinfrastructureList;
    }

    @XmlTransient
    public List<TypeinfraTypestruc> getTypeinfraTypestrucList() {
        return typeinfraTypestrucList;
    }

    public void setTypeinfraTypestrucList(List<TypeinfraTypestruc> typeinfraTypestrucList) {
        this.typeinfraTypestrucList = typeinfraTypestrucList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeinfrastructure != null ? idtypeinfrastructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeinfrastructure)) {
            return false;
        }
        Typeinfrastructure other = (Typeinfrastructure) object;
        if ((this.idtypeinfrastructure == null && other.idtypeinfrastructure != null) || (this.idtypeinfrastructure != null && !this.idtypeinfrastructure.equals(other.idtypeinfrastructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typeinfrastructure[ idtypeinfrastructure=" + idtypeinfrastructure + " ]";
    }
    
}
