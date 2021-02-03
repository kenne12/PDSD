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
import javax.persistence.Id;
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
    @NamedQuery(name = "Sourceenergie.findAll", query = "SELECT s FROM Sourceenergie s"),
    @NamedQuery(name = "Sourceenergie.findByIdSourceenergie", query = "SELECT s FROM Sourceenergie s WHERE s.idSourceenergie = :idSourceenergie"),
    @NamedQuery(name = "Sourceenergie.findByNomFr", query = "SELECT s FROM Sourceenergie s WHERE s.nomFr = :nomFr"),
    @NamedQuery(name = "Sourceenergie.findByNomEn", query = "SELECT s FROM Sourceenergie s WHERE s.nomEn = :nomEn")})
public class Sourceenergie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sourceenergie")
    private Integer idSourceenergie;
    @Size(max = 255)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;

    public Sourceenergie() {
    }

    public Sourceenergie(Integer idSourceenergie) {
        this.idSourceenergie = idSourceenergie;
    }

    public Integer getIdSourceenergie() {
        return idSourceenergie;
    }

    public void setIdSourceenergie(Integer idSourceenergie) {
        this.idSourceenergie = idSourceenergie;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSourceenergie != null ? idSourceenergie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sourceenergie)) {
            return false;
        }
        Sourceenergie other = (Sourceenergie) object;
        if ((this.idSourceenergie == null && other.idSourceenergie != null) || (this.idSourceenergie != null && !this.idSourceenergie.equals(other.idSourceenergie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sourceenergie[ idSourceenergie=" + idSourceenergie + " ]";
    }
    
}
