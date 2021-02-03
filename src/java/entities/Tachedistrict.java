/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Tachedistrict.findAll", query = "SELECT t FROM Tachedistrict t"),
    @NamedQuery(name = "Tachedistrict.findByIdtachedistrict", query = "SELECT t FROM Tachedistrict t WHERE t.idtachedistrict = :idtachedistrict"),
    @NamedQuery(name = "Tachedistrict.findByLibelle", query = "SELECT t FROM Tachedistrict t WHERE t.libelle = :libelle"),
    @NamedQuery(name = "Tachedistrict.findByResponsable", query = "SELECT t FROM Tachedistrict t WHERE t.responsable = :responsable"),
    @NamedQuery(name = "Tachedistrict.findByCout", query = "SELECT t FROM Tachedistrict t WHERE t.cout = :cout"),
    @NamedQuery(name = "Tachedistrict.findByM1", query = "SELECT t FROM Tachedistrict t WHERE t.m1 = :m1"),
    @NamedQuery(name = "Tachedistrict.findByM2", query = "SELECT t FROM Tachedistrict t WHERE t.m2 = :m2"),
    @NamedQuery(name = "Tachedistrict.findByM3", query = "SELECT t FROM Tachedistrict t WHERE t.m3 = :m3"),
    @NamedQuery(name = "Tachedistrict.findByM4", query = "SELECT t FROM Tachedistrict t WHERE t.m4 = :m4"),
    @NamedQuery(name = "Tachedistrict.findByM5", query = "SELECT t FROM Tachedistrict t WHERE t.m5 = :m5"),
    @NamedQuery(name = "Tachedistrict.findByM6", query = "SELECT t FROM Tachedistrict t WHERE t.m6 = :m6"),
    @NamedQuery(name = "Tachedistrict.findByM7", query = "SELECT t FROM Tachedistrict t WHERE t.m7 = :m7"),
    @NamedQuery(name = "Tachedistrict.findByM8", query = "SELECT t FROM Tachedistrict t WHERE t.m8 = :m8"),
    @NamedQuery(name = "Tachedistrict.findByM9", query = "SELECT t FROM Tachedistrict t WHERE t.m9 = :m9"),
    @NamedQuery(name = "Tachedistrict.findByM10", query = "SELECT t FROM Tachedistrict t WHERE t.m10 = :m10"),
    @NamedQuery(name = "Tachedistrict.findByM11", query = "SELECT t FROM Tachedistrict t WHERE t.m11 = :m11"),
    @NamedQuery(name = "Tachedistrict.findByM12", query = "SELECT t FROM Tachedistrict t WHERE t.m12 = :m12")})
public class Tachedistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idtachedistrict;
    @Size(max = 2147483647)
    private String libelle;
    @Size(max = 2147483647)
    private String responsable;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double cout;
    private Boolean m1;
    private Boolean m2;
    private Boolean m3;
    private Boolean m4;
    private Boolean m5;
    private Boolean m6;
    private Boolean m7;
    private Boolean m8;
    private Boolean m9;
    private Boolean m10;
    private Boolean m11;
    private Boolean m12;
    @JoinColumn(name = "idactivitestructure", referencedColumnName = "idactivite_structure")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActiviteStructure idactivitestructure;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;

    public Tachedistrict() {
    }

    public Tachedistrict(Long idtachedistrict) {
        this.idtachedistrict = idtachedistrict;
    }

    public Long getIdtachedistrict() {
        return idtachedistrict;
    }

    public void setIdtachedistrict(Long idtachedistrict) {
        this.idtachedistrict = idtachedistrict;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public Boolean getM1() {
        return m1;
    }

    public void setM1(Boolean m1) {
        this.m1 = m1;
    }

    public Boolean getM2() {
        return m2;
    }

    public void setM2(Boolean m2) {
        this.m2 = m2;
    }

    public Boolean getM3() {
        return m3;
    }

    public void setM3(Boolean m3) {
        this.m3 = m3;
    }

    public Boolean getM4() {
        return m4;
    }

    public void setM4(Boolean m4) {
        this.m4 = m4;
    }

    public Boolean getM5() {
        return m5;
    }

    public void setM5(Boolean m5) {
        this.m5 = m5;
    }

    public Boolean getM6() {
        return m6;
    }

    public void setM6(Boolean m6) {
        this.m6 = m6;
    }

    public Boolean getM7() {
        return m7;
    }

    public void setM7(Boolean m7) {
        this.m7 = m7;
    }

    public Boolean getM8() {
        return m8;
    }

    public void setM8(Boolean m8) {
        this.m8 = m8;
    }

    public Boolean getM9() {
        return m9;
    }

    public void setM9(Boolean m9) {
        this.m9 = m9;
    }

    public Boolean getM10() {
        return m10;
    }

    public void setM10(Boolean m10) {
        this.m10 = m10;
    }

    public Boolean getM11() {
        return m11;
    }

    public void setM11(Boolean m11) {
        this.m11 = m11;
    }

    public Boolean getM12() {
        return m12;
    }

    public void setM12(Boolean m12) {
        this.m12 = m12;
    }

    public ActiviteStructure getIdactivitestructure() {
        return idactivitestructure;
    }

    public void setIdactivitestructure(ActiviteStructure idactivitestructure) {
        this.idactivitestructure = idactivitestructure;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtachedistrict != null ? idtachedistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tachedistrict)) {
            return false;
        }
        Tachedistrict other = (Tachedistrict) object;
        if ((this.idtachedistrict == null && other.idtachedistrict != null) || (this.idtachedistrict != null && !this.idtachedistrict.equals(other.idtachedistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tachedistrict[ idtachedistrict=" + idtachedistrict + " ]";
    }
    
}
