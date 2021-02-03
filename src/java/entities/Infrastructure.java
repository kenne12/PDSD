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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Infrastructure.findAll", query = "SELECT i FROM Infrastructure i"),
    @NamedQuery(name = "Infrastructure.findByIdinfrastructure", query = "SELECT i FROM Infrastructure i WHERE i.idinfrastructure = :idinfrastructure"),
    @NamedQuery(name = "Infrastructure.findByNumero", query = "SELECT i FROM Infrastructure i WHERE i.numero = :numero")})
public class Infrastructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idinfrastructure;
    private Integer numero;
    @JoinColumn(name = "idetatinfrastructure", referencedColumnName = "idetatinfrastructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etatinfrastructure idetatinfrastructure;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @JoinColumn(name = "idtypeinfra_typestruc", referencedColumnName = "idtypeinfra_typestruc")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeinfraTypestruc idtypeinfraTypestruc;

    public Infrastructure() {
    }

    public Infrastructure(Integer idinfrastructure) {
        this.idinfrastructure = idinfrastructure;
    }

    public Integer getIdinfrastructure() {
        return idinfrastructure;
    }

    public void setIdinfrastructure(Integer idinfrastructure) {
        this.idinfrastructure = idinfrastructure;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Etatinfrastructure getIdetatinfrastructure() {
        return idetatinfrastructure;
    }

    public void setIdetatinfrastructure(Etatinfrastructure idetatinfrastructure) {
        this.idetatinfrastructure = idetatinfrastructure;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    public TypeinfraTypestruc getIdtypeinfraTypestruc() {
        return idtypeinfraTypestruc;
    }

    public void setIdtypeinfraTypestruc(TypeinfraTypestruc idtypeinfraTypestruc) {
        this.idtypeinfraTypestruc = idtypeinfraTypestruc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinfrastructure != null ? idinfrastructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infrastructure)) {
            return false;
        }
        Infrastructure other = (Infrastructure) object;
        if ((this.idinfrastructure == null && other.idinfrastructure != null) || (this.idinfrastructure != null && !this.idinfrastructure.equals(other.idinfrastructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Infrastructure[ idinfrastructure=" + idinfrastructure + " ]";
    }
    
}
