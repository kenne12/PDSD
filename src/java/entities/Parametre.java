/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Parametre.findAll", query = "SELECT p FROM Parametre p"),
    @NamedQuery(name = "Parametre.findByIdparametre", query = "SELECT p FROM Parametre p WHERE p.idparametre = :idparametre"),
    @NamedQuery(name = "Parametre.findByIdindicateur", query = "SELECT p FROM Parametre p WHERE p.idindicateur = :idindicateur"),
    @NamedQuery(name = "Parametre.findByDiametre", query = "SELECT p FROM Parametre p WHERE p.diametre = :diametre"),
    @NamedQuery(name = "Parametre.findByEcart", query = "SELECT p FROM Parametre p WHERE p.ecart = :ecart"),
    @NamedQuery(name = "Parametre.findByNbretestvr", query = "SELECT p FROM Parametre p WHERE p.nbretestvr = :nbretestvr"),
    @NamedQuery(name = "Parametre.findByNbretestva", query = "SELECT p FROM Parametre p WHERE p.nbretestva = :nbretestva"),
    @NamedQuery(name = "Parametre.findByNbretestgap", query = "SELECT p FROM Parametre p WHERE p.nbretestgap = :nbretestgap"),
    @NamedQuery(name = "Parametre.findByDiametrenum", query = "SELECT p FROM Parametre p WHERE p.diametrenum = :diametrenum"),
    @NamedQuery(name = "Parametre.findByNbretestnum", query = "SELECT p FROM Parametre p WHERE p.nbretestnum = :nbretestnum"),
    @NamedQuery(name = "Parametre.findByIdindicateur2", query = "SELECT p FROM Parametre p WHERE p.idindicateur2 = :idindicateur2"),
    @NamedQuery(name = "Parametre.findByDiametreden", query = "SELECT p FROM Parametre p WHERE p.diametreden = :diametreden"),
    @NamedQuery(name = "Parametre.findByNbretestden", query = "SELECT p FROM Parametre p WHERE p.nbretestden = :nbretestden"),
    @NamedQuery(name = "Parametre.findByCercle", query = "SELECT p FROM Parametre p WHERE p.cercle = :cercle"),
    @NamedQuery(name = "Parametre.findByIdindicateur1", query = "SELECT p FROM Parametre p WHERE p.idindicateur1 = :idindicateur1"),
    @NamedQuery(name = "Parametre.findByIdindicateur3", query = "SELECT p FROM Parametre p WHERE p.idindicateur3 = :idindicateur3"),
    @NamedQuery(name = "Parametre.findByIdindicateur4", query = "SELECT p FROM Parametre p WHERE p.idindicateur4 = :idindicateur4"),
    @NamedQuery(name = "Parametre.findByIdindicateur5", query = "SELECT p FROM Parametre p WHERE p.idindicateur5 = :idindicateur5"),
    @NamedQuery(name = "Parametre.findByDefaut", query = "SELECT p FROM Parametre p WHERE p.defaut = :defaut"),
    @NamedQuery(name = "Parametre.findByPeriode1", query = "SELECT p FROM Parametre p WHERE p.periode1 = :periode1"),
    @NamedQuery(name = "Parametre.findByPeriode2", query = "SELECT p FROM Parametre p WHERE p.periode2 = :periode2"),
    @NamedQuery(name = "Parametre.findByPeriode3", query = "SELECT p FROM Parametre p WHERE p.periode3 = :periode3"),
    @NamedQuery(name = "Parametre.findByPeriode4", query = "SELECT p FROM Parametre p WHERE p.periode4 = :periode4"),
    @NamedQuery(name = "Parametre.findByPeriode5", query = "SELECT p FROM Parametre p WHERE p.periode5 = :periode5"),
    @NamedQuery(name = "Parametre.findByIdindicateur6", query = "SELECT p FROM Parametre p WHERE p.idindicateur6 = :idindicateur6"),
    @NamedQuery(name = "Parametre.findByIdindicateur7", query = "SELECT p FROM Parametre p WHERE p.idindicateur7 = :idindicateur7"),
    @NamedQuery(name = "Parametre.findByIdindicateur8", query = "SELECT p FROM Parametre p WHERE p.idindicateur8 = :idindicateur8"),
    @NamedQuery(name = "Parametre.findByIdindicateur10", query = "SELECT p FROM Parametre p WHERE p.idindicateur10 = :idindicateur10"),
    @NamedQuery(name = "Parametre.findByIdindicateur11", query = "SELECT p FROM Parametre p WHERE p.idindicateur11 = :idindicateur11"),
    @NamedQuery(name = "Parametre.findByIdindicateur12", query = "SELECT p FROM Parametre p WHERE p.idindicateur12 = :idindicateur12"),
    @NamedQuery(name = "Parametre.findByIdindicateur9", query = "SELECT p FROM Parametre p WHERE p.idindicateur9 = :idindicateur9")})
public class Parametre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idparametre;
    private Integer idindicateur;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double diametre;
    private Double ecart;
    private Double nbretestvr;
    private Double nbretestva;
    private Double nbretestgap;
    private Double diametrenum;
    private Double nbretestnum;
    private Integer idindicateur2;
    private Double diametreden;
    private Double nbretestden;
    private Boolean cercle;
    private Integer idindicateur1;
    private Integer idindicateur3;
    private Integer idindicateur4;
    private Integer idindicateur5;
    private Boolean defaut;
    @Size(max = 2147483647)
    private String periode1;
    @Size(max = 2147483647)
    private String periode2;
    @Size(max = 2147483647)
    private String periode3;
    @Size(max = 2147483647)
    private String periode4;
    @Size(max = 2147483647)
    private String periode5;
    private Integer idindicateur6;
    private Integer idindicateur7;
    private Integer idindicateur8;
    private Integer idindicateur10;
    private Integer idindicateur11;
    private Integer idindicateur12;
    private Integer idindicateur9;

    public Parametre() {
    }

    public Parametre(Integer idparametre) {
        this.idparametre = idparametre;
    }

    public Integer getIdparametre() {
        return idparametre;
    }

    public void setIdparametre(Integer idparametre) {
        this.idparametre = idparametre;
    }

    public Integer getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Integer idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Double getDiametre() {
        return diametre;
    }

    public void setDiametre(Double diametre) {
        this.diametre = diametre;
    }

    public Double getEcart() {
        return ecart;
    }

    public void setEcart(Double ecart) {
        this.ecart = ecart;
    }

    public Double getNbretestvr() {
        return nbretestvr;
    }

    public void setNbretestvr(Double nbretestvr) {
        this.nbretestvr = nbretestvr;
    }

    public Double getNbretestva() {
        return nbretestva;
    }

    public void setNbretestva(Double nbretestva) {
        this.nbretestva = nbretestva;
    }

    public Double getNbretestgap() {
        return nbretestgap;
    }

    public void setNbretestgap(Double nbretestgap) {
        this.nbretestgap = nbretestgap;
    }

    public Double getDiametrenum() {
        return diametrenum;
    }

    public void setDiametrenum(Double diametrenum) {
        this.diametrenum = diametrenum;
    }

    public Double getNbretestnum() {
        return nbretestnum;
    }

    public void setNbretestnum(Double nbretestnum) {
        this.nbretestnum = nbretestnum;
    }

    public Integer getIdindicateur2() {
        return idindicateur2;
    }

    public void setIdindicateur2(Integer idindicateur2) {
        this.idindicateur2 = idindicateur2;
    }

    public Double getDiametreden() {
        return diametreden;
    }

    public void setDiametreden(Double diametreden) {
        this.diametreden = diametreden;
    }

    public Double getNbretestden() {
        return nbretestden;
    }

    public void setNbretestden(Double nbretestden) {
        this.nbretestden = nbretestden;
    }

    public Boolean getCercle() {
        return cercle;
    }

    public void setCercle(Boolean cercle) {
        this.cercle = cercle;
    }

    public Integer getIdindicateur1() {
        return idindicateur1;
    }

    public void setIdindicateur1(Integer idindicateur1) {
        this.idindicateur1 = idindicateur1;
    }

    public Integer getIdindicateur3() {
        return idindicateur3;
    }

    public void setIdindicateur3(Integer idindicateur3) {
        this.idindicateur3 = idindicateur3;
    }

    public Integer getIdindicateur4() {
        return idindicateur4;
    }

    public void setIdindicateur4(Integer idindicateur4) {
        this.idindicateur4 = idindicateur4;
    }

    public Integer getIdindicateur5() {
        return idindicateur5;
    }

    public void setIdindicateur5(Integer idindicateur5) {
        this.idindicateur5 = idindicateur5;
    }

    public Boolean getDefaut() {
        return defaut;
    }

    public void setDefaut(Boolean defaut) {
        this.defaut = defaut;
    }

    public String getPeriode1() {
        return periode1;
    }

    public void setPeriode1(String periode1) {
        this.periode1 = periode1;
    }

    public String getPeriode2() {
        return periode2;
    }

    public void setPeriode2(String periode2) {
        this.periode2 = periode2;
    }

    public String getPeriode3() {
        return periode3;
    }

    public void setPeriode3(String periode3) {
        this.periode3 = periode3;
    }

    public String getPeriode4() {
        return periode4;
    }

    public void setPeriode4(String periode4) {
        this.periode4 = periode4;
    }

    public String getPeriode5() {
        return periode5;
    }

    public void setPeriode5(String periode5) {
        this.periode5 = periode5;
    }

    public Integer getIdindicateur6() {
        return idindicateur6;
    }

    public void setIdindicateur6(Integer idindicateur6) {
        this.idindicateur6 = idindicateur6;
    }

    public Integer getIdindicateur7() {
        return idindicateur7;
    }

    public void setIdindicateur7(Integer idindicateur7) {
        this.idindicateur7 = idindicateur7;
    }

    public Integer getIdindicateur8() {
        return idindicateur8;
    }

    public void setIdindicateur8(Integer idindicateur8) {
        this.idindicateur8 = idindicateur8;
    }

    public Integer getIdindicateur10() {
        return idindicateur10;
    }

    public void setIdindicateur10(Integer idindicateur10) {
        this.idindicateur10 = idindicateur10;
    }

    public Integer getIdindicateur11() {
        return idindicateur11;
    }

    public void setIdindicateur11(Integer idindicateur11) {
        this.idindicateur11 = idindicateur11;
    }

    public Integer getIdindicateur12() {
        return idindicateur12;
    }

    public void setIdindicateur12(Integer idindicateur12) {
        this.idindicateur12 = idindicateur12;
    }

    public Integer getIdindicateur9() {
        return idindicateur9;
    }

    public void setIdindicateur9(Integer idindicateur9) {
        this.idindicateur9 = idindicateur9;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparametre != null ? idparametre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametre)) {
            return false;
        }
        Parametre other = (Parametre) object;
        if ((this.idparametre == null && other.idparametre != null) || (this.idparametre != null && !this.idparametre.equals(other.idparametre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Parametre[ idparametre=" + idparametre + " ]";
    }
    
}
