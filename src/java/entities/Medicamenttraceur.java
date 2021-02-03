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
    @NamedQuery(name = "Medicamenttraceur.findAll", query = "SELECT m FROM Medicamenttraceur m"),
    @NamedQuery(name = "Medicamenttraceur.findByIdmedicamenttraceur", query = "SELECT m FROM Medicamenttraceur m WHERE m.idmedicamenttraceur = :idmedicamenttraceur"),
    @NamedQuery(name = "Medicamenttraceur.findByNomFr", query = "SELECT m FROM Medicamenttraceur m WHERE m.nomFr = :nomFr"),
    @NamedQuery(name = "Medicamenttraceur.findByCode", query = "SELECT m FROM Medicamenttraceur m WHERE m.code = :code"),
    @NamedQuery(name = "Medicamenttraceur.findByNomEn", query = "SELECT m FROM Medicamenttraceur m WHERE m.nomEn = :nomEn"),
    @NamedQuery(name = "Medicamenttraceur.findByPdsd", query = "SELECT m FROM Medicamenttraceur m WHERE m.pdsd = :pdsd"),
    @NamedQuery(name = "Medicamenttraceur.findByPrcds", query = "SELECT m FROM Medicamenttraceur m WHERE m.prcds = :prcds")})
public class Medicamenttraceur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmedicamenttraceur;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    private Boolean pdsd;
    private Boolean prcds;
    @OneToMany(mappedBy = "idmedicamenttraceur", fetch = FetchType.LAZY)
    private List<MedicamenttraceurStructure> medicamenttraceurStructureList;

    public Medicamenttraceur() {
    }

    public Medicamenttraceur(Integer idmedicamenttraceur) {
        this.idmedicamenttraceur = idmedicamenttraceur;
    }

    public Integer getIdmedicamenttraceur() {
        return idmedicamenttraceur;
    }

    public void setIdmedicamenttraceur(Integer idmedicamenttraceur) {
        this.idmedicamenttraceur = idmedicamenttraceur;
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

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public Boolean getPdsd() {
        return pdsd;
    }

    public void setPdsd(Boolean pdsd) {
        this.pdsd = pdsd;
    }

    public Boolean getPrcds() {
        return prcds;
    }

    public void setPrcds(Boolean prcds) {
        this.prcds = prcds;
    }

    @XmlTransient
    public List<MedicamenttraceurStructure> getMedicamenttraceurStructureList() {
        return medicamenttraceurStructureList;
    }

    public void setMedicamenttraceurStructureList(List<MedicamenttraceurStructure> medicamenttraceurStructureList) {
        this.medicamenttraceurStructureList = medicamenttraceurStructureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicamenttraceur != null ? idmedicamenttraceur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamenttraceur)) {
            return false;
        }
        Medicamenttraceur other = (Medicamenttraceur) object;
        if ((this.idmedicamenttraceur == null && other.idmedicamenttraceur != null) || (this.idmedicamenttraceur != null && !this.idmedicamenttraceur.equals(other.idmedicamenttraceur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Medicamenttraceur[ idmedicamenttraceur=" + idmedicamenttraceur + " ]";
    }
    
}
