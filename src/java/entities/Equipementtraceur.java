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
    @NamedQuery(name = "Equipementtraceur.findAll", query = "SELECT e FROM Equipementtraceur e"),
    @NamedQuery(name = "Equipementtraceur.findByIdequipementtraceur", query = "SELECT e FROM Equipementtraceur e WHERE e.idequipementtraceur = :idequipementtraceur"),
    @NamedQuery(name = "Equipementtraceur.findByNombre", query = "SELECT e FROM Equipementtraceur e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipementtraceur.findByNumero", query = "SELECT e FROM Equipementtraceur e WHERE e.numero = :numero")})
public class Equipementtraceur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idequipementtraceur;
    private Integer nombre;
    private Integer numero;
    @JoinColumn(name = "idetatequipement", referencedColumnName = "idetatequipement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etatequipement idetatequipement;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @JoinColumn(name = "idtypestruc_typeequipement", referencedColumnName = "idtypestruc_typeequipement")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypestrucTypeequipement idtypestrucTypeequipement;

    public Equipementtraceur() {
    }

    public Equipementtraceur(Integer idequipementtraceur) {
        this.idequipementtraceur = idequipementtraceur;
    }

    public Integer getIdequipementtraceur() {
        return idequipementtraceur;
    }

    public void setIdequipementtraceur(Integer idequipementtraceur) {
        this.idequipementtraceur = idequipementtraceur;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Etatequipement getIdetatequipement() {
        return idetatequipement;
    }

    public void setIdetatequipement(Etatequipement idetatequipement) {
        this.idetatequipement = idetatequipement;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    public TypestrucTypeequipement getIdtypestrucTypeequipement() {
        return idtypestrucTypeequipement;
    }

    public void setIdtypestrucTypeequipement(TypestrucTypeequipement idtypestrucTypeequipement) {
        this.idtypestrucTypeequipement = idtypestrucTypeequipement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipementtraceur != null ? idequipementtraceur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipementtraceur)) {
            return false;
        }
        Equipementtraceur other = (Equipementtraceur) object;
        if ((this.idequipementtraceur == null && other.idequipementtraceur != null) || (this.idequipementtraceur != null && !this.idequipementtraceur.equals(other.idequipementtraceur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipementtraceur[ idequipementtraceur=" + idequipementtraceur + " ]";
    }
    
}
