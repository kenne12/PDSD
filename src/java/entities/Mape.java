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
    @NamedQuery(name = "Mape.findAll", query = "SELECT m FROM Mape m"),
    @NamedQuery(name = "Mape.findByIdmape", query = "SELECT m FROM Mape m WHERE m.idmape = :idmape"),
    @NamedQuery(name = "Mape.findByNomFr", query = "SELECT m FROM Mape m WHERE m.nomFr = :nomFr"),
    @NamedQuery(name = "Mape.findByCode", query = "SELECT m FROM Mape m WHERE m.code = :code"),
    @NamedQuery(name = "Mape.findByNomEn", query = "SELECT m FROM Mape m WHERE m.nomEn = :nomEn")})
public class Mape implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmape;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idmape", fetch = FetchType.LAZY)
    private List<Mapedistrict> mapedistrictList;

    public Mape() {
    }

    public Mape(Integer idmape) {
        this.idmape = idmape;
    }

    public Integer getIdmape() {
        return idmape;
    }

    public void setIdmape(Integer idmape) {
        this.idmape = idmape;
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

    @XmlTransient
    public List<Mapedistrict> getMapedistrictList() {
        return mapedistrictList;
    }

    public void setMapedistrictList(List<Mapedistrict> mapedistrictList) {
        this.mapedistrictList = mapedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmape != null ? idmape.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mape)) {
            return false;
        }
        Mape other = (Mape) object;
        if ((this.idmape == null && other.idmape != null) || (this.idmape != null && !this.idmape.equals(other.idmape))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mape[ idmape=" + idmape + " ]";
    }
    
}
