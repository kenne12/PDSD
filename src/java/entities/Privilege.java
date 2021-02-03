/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByIdprivilege", query = "SELECT p FROM Privilege p WHERE p.idprivilege = :idprivilege"),
    @NamedQuery(name = "Privilege.findByDateattribution", query = "SELECT p FROM Privilege p WHERE p.dateattribution = :dateattribution"),
    @NamedQuery(name = "Privilege.findByEtat", query = "SELECT p FROM Privilege p WHERE p.etat = :etat")})
public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idprivilege;
    @Temporal(TemporalType.DATE)
    private Date dateattribution;
    private Boolean etat;
    @JoinColumn(name = "idgroupeutilisateur", referencedColumnName = "idgroupeutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Groupeutilisateur idgroupeutilisateur;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu idmenu;

    public Privilege() {
    }

    public Privilege(Integer idprivilege) {
        this.idprivilege = idprivilege;
    }

    public Integer getIdprivilege() {
        return idprivilege;
    }

    public void setIdprivilege(Integer idprivilege) {
        this.idprivilege = idprivilege;
    }

    public Date getDateattribution() {
        return dateattribution;
    }

    public void setDateattribution(Date dateattribution) {
        this.dateattribution = dateattribution;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Groupeutilisateur getIdgroupeutilisateur() {
        return idgroupeutilisateur;
    }

    public void setIdgroupeutilisateur(Groupeutilisateur idgroupeutilisateur) {
        this.idgroupeutilisateur = idgroupeutilisateur;
    }

    public Menu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Menu idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilege != null ? idprivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.idprivilege == null && other.idprivilege != null) || (this.idprivilege != null && !this.idprivilege.equals(other.idprivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Privilege[ idprivilege=" + idprivilege + " ]";
    }
    
}
