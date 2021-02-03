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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "activite_structure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteStructure.findAll", query = "SELECT a FROM ActiviteStructure a"),
    @NamedQuery(name = "ActiviteStructure.findByIdactiviteStructure", query = "SELECT a FROM ActiviteStructure a WHERE a.idactiviteStructure = :idactiviteStructure"),
    @NamedQuery(name = "ActiviteStructure.findByCout", query = "SELECT a FROM ActiviteStructure a WHERE a.cout = :cout"),
    @NamedQuery(name = "ActiviteStructure.findByPrograme", query = "SELECT a FROM ActiviteStructure a WHERE a.programe = :programe")})
public class ActiviteStructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_structure")
    private Long idactiviteStructure;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double cout;
    private Boolean programe;
    @OneToMany(mappedBy = "idactivitestructure", fetch = FetchType.LAZY)
    private List<Tachedistrict> tachedistrictList;
    @JoinColumn(name = "idactivite", referencedColumnName = "idactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activite idactivite;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idsourcefi", referencedColumnName = "idsourcefi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefi;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public ActiviteStructure() {
    }

    public ActiviteStructure(Long idactiviteStructure) {
        this.idactiviteStructure = idactiviteStructure;
    }

    public Long getIdactiviteStructure() {
        return idactiviteStructure;
    }

    public void setIdactiviteStructure(Long idactiviteStructure) {
        this.idactiviteStructure = idactiviteStructure;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public Boolean getPrograme() {
        return programe;
    }

    public void setPrograme(Boolean programe) {
        this.programe = programe;
    }

    @XmlTransient
    public List<Tachedistrict> getTachedistrictList() {
        return tachedistrictList;
    }

    public void setTachedistrictList(List<Tachedistrict> tachedistrictList) {
        this.tachedistrictList = tachedistrictList;
    }

    public Activite getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Activite idactivite) {
        this.idactivite = idactivite;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Sourcefinancement getIdsourcefi() {
        return idsourcefi;
    }

    public void setIdsourcefi(Sourcefinancement idsourcefi) {
        this.idsourcefi = idsourcefi;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactiviteStructure != null ? idactiviteStructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteStructure)) {
            return false;
        }
        ActiviteStructure other = (ActiviteStructure) object;
        if ((this.idactiviteStructure == null && other.idactiviteStructure != null) || (this.idactiviteStructure != null && !this.idactiviteStructure.equals(other.idactiviteStructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteStructure[ idactiviteStructure=" + idactiviteStructure + " ]";
    }
    
}
