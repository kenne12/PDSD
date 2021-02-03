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
    @NamedQuery(name = "Typestructurecommunautaire.findAll", query = "SELECT t FROM Typestructurecommunautaire t"),
    @NamedQuery(name = "Typestructurecommunautaire.findByIdtypestructurecommunautaire", query = "SELECT t FROM Typestructurecommunautaire t WHERE t.idtypestructurecommunautaire = :idtypestructurecommunautaire"),
    @NamedQuery(name = "Typestructurecommunautaire.findByNomFr", query = "SELECT t FROM Typestructurecommunautaire t WHERE t.nomFr = :nomFr"),
    @NamedQuery(name = "Typestructurecommunautaire.findByNomEn", query = "SELECT t FROM Typestructurecommunautaire t WHERE t.nomEn = :nomEn")})
public class Typestructurecommunautaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idtypestructurecommunautaire;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idtypestructurecommunautaire", fetch = FetchType.LAZY)
    private List<Structurecommunautaire> structurecommunautaireList;

    public Typestructurecommunautaire() {
    }

    public Typestructurecommunautaire(Integer idtypestructurecommunautaire) {
        this.idtypestructurecommunautaire = idtypestructurecommunautaire;
    }

    public Integer getIdtypestructurecommunautaire() {
        return idtypestructurecommunautaire;
    }

    public void setIdtypestructurecommunautaire(Integer idtypestructurecommunautaire) {
        this.idtypestructurecommunautaire = idtypestructurecommunautaire;
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

    @XmlTransient
    public List<Structurecommunautaire> getStructurecommunautaireList() {
        return structurecommunautaireList;
    }

    public void setStructurecommunautaireList(List<Structurecommunautaire> structurecommunautaireList) {
        this.structurecommunautaireList = structurecommunautaireList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypestructurecommunautaire != null ? idtypestructurecommunautaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typestructurecommunautaire)) {
            return false;
        }
        Typestructurecommunautaire other = (Typestructurecommunautaire) object;
        if ((this.idtypestructurecommunautaire == null && other.idtypestructurecommunautaire != null) || (this.idtypestructurecommunautaire != null && !this.idtypestructurecommunautaire.equals(other.idtypestructurecommunautaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typestructurecommunautaire[ idtypestructurecommunautaire=" + idtypestructurecommunautaire + " ]";
    }
    
}
