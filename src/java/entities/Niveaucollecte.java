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
    @NamedQuery(name = "Niveaucollecte.findAll", query = "SELECT n FROM Niveaucollecte n"),
    @NamedQuery(name = "Niveaucollecte.findByIdniveaucollecte", query = "SELECT n FROM Niveaucollecte n WHERE n.idniveaucollecte = :idniveaucollecte"),
    @NamedQuery(name = "Niveaucollecte.findByNomFr", query = "SELECT n FROM Niveaucollecte n WHERE n.nomFr = :nomFr"),
    @NamedQuery(name = "Niveaucollecte.findByCode", query = "SELECT n FROM Niveaucollecte n WHERE n.code = :code"),
    @NamedQuery(name = "Niveaucollecte.findByDescription", query = "SELECT n FROM Niveaucollecte n WHERE n.description = :description"),
    @NamedQuery(name = "Niveaucollecte.findByNomEn", query = "SELECT n FROM Niveaucollecte n WHERE n.nomEn = :nomEn")})
public class Niveaucollecte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idniveaucollecte;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String description;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idniveaucollecte", fetch = FetchType.LAZY)
    private List<Indicateur> indicateurList;

    public Niveaucollecte() {
    }

    public Niveaucollecte(Integer idniveaucollecte) {
        this.idniveaucollecte = idniveaucollecte;
    }

    public Integer getIdniveaucollecte() {
        return idniveaucollecte;
    }

    public void setIdniveaucollecte(Integer idniveaucollecte) {
        this.idniveaucollecte = idniveaucollecte;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Indicateur> getIndicateurList() {
        return indicateurList;
    }

    public void setIndicateurList(List<Indicateur> indicateurList) {
        this.indicateurList = indicateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idniveaucollecte != null ? idniveaucollecte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveaucollecte)) {
            return false;
        }
        Niveaucollecte other = (Niveaucollecte) object;
        if ((this.idniveaucollecte == null && other.idniveaucollecte != null) || (this.idniveaucollecte != null && !this.idniveaucollecte.equals(other.idniveaucollecte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Niveaucollecte[ idniveaucollecte=" + idniveaucollecte + " ]";
    }
    
}
