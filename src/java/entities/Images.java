/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Images.findAll", query = "SELECT i FROM Images i"),
    @NamedQuery(name = "Images.findByIdimages", query = "SELECT i FROM Images i WHERE i.idimages = :idimages"),
    @NamedQuery(name = "Images.findByLibelleFr", query = "SELECT i FROM Images i WHERE i.libelleFr = :libelleFr"),
    @NamedQuery(name = "Images.findByCommentaireFr", query = "SELECT i FROM Images i WHERE i.commentaireFr = :commentaireFr"),
    @NamedQuery(name = "Images.findByImage", query = "SELECT i FROM Images i WHERE i.image = :image"),
    @NamedQuery(name = "Images.findByIdregion", query = "SELECT i FROM Images i WHERE i.idregion = :idregion"),
    @NamedQuery(name = "Images.findByIdperiode", query = "SELECT i FROM Images i WHERE i.idperiode = :idperiode"),
    @NamedQuery(name = "Images.findByAffiche", query = "SELECT i FROM Images i WHERE i.affiche = :affiche"),
    @NamedQuery(name = "Images.findByDateimage", query = "SELECT i FROM Images i WHERE i.dateimage = :dateimage"),
    @NamedQuery(name = "Images.findByLibelleEn", query = "SELECT i FROM Images i WHERE i.libelleEn = :libelleEn"),
    @NamedQuery(name = "Images.findByCommentaireEn", query = "SELECT i FROM Images i WHERE i.commentaireEn = :commentaireEn")})
public class Images implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idimages;
    @Size(max = 2147483647)
    @Column(name = "libelle_fr")
    private String libelleFr;
    @Size(max = 500)
    @Column(name = "commentaire_fr")
    private String commentaireFr;
    @Size(max = 2147483647)
    private String image;
    private Integer idregion;
    private Integer idperiode;
    private Integer affiche;
    @Temporal(TemporalType.DATE)
    private Date dateimage;
    @Size(max = 255)
    @Column(name = "libelle_en")
    private String libelleEn;
    @Size(max = 500)
    @Column(name = "commentaire_en")
    private String commentaireEn;

    public Images() {
    }

    public Images(Integer idimages) {
        this.idimages = idimages;
    }

    public Integer getIdimages() {
        return idimages;
    }

    public void setIdimages(Integer idimages) {
        this.idimages = idimages;
    }

    public String getLibelleFr() {
        return libelleFr;
    }

    public void setLibelleFr(String libelleFr) {
        this.libelleFr = libelleFr;
    }

    public String getCommentaireFr() {
        return commentaireFr;
    }

    public void setCommentaireFr(String commentaireFr) {
        this.commentaireFr = commentaireFr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIdregion() {
        return idregion;
    }

    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    public Integer getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Integer idperiode) {
        this.idperiode = idperiode;
    }

    public Integer getAffiche() {
        return affiche;
    }

    public void setAffiche(Integer affiche) {
        this.affiche = affiche;
    }

    public Date getDateimage() {
        return dateimage;
    }

    public void setDateimage(Date dateimage) {
        this.dateimage = dateimage;
    }

    public String getLibelleEn() {
        return libelleEn;
    }

    public void setLibelleEn(String libelleEn) {
        this.libelleEn = libelleEn;
    }

    public String getCommentaireEn() {
        return commentaireEn;
    }

    public void setCommentaireEn(String commentaireEn) {
        this.commentaireEn = commentaireEn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimages != null ? idimages.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Images)) {
            return false;
        }
        Images other = (Images) object;
        if ((this.idimages == null && other.idimages != null) || (this.idimages != null && !this.idimages.equals(other.idimages))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Images[ idimages=" + idimages + " ]";
    }
    
}
