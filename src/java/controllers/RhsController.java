/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Commentairetab;
import entities.Profilpersonnel;
import entities.Rhs;
import entities.Structure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.MouchardFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.ProfilpersonnelFacadeLocal;
import sessions.RhsFacadeLocal;
import sessions.StructureFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class RhsController implements Serializable {

    /**
     * Creates a new instance of AnneeController
     */
    @EJB
    private RhsFacadeLocal rhsFacadeLocal;
    private List<Rhs> rhses = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private ProfilpersonnelFacadeLocal profilpersonnelFacadeLocal;
    private List<Profilpersonnel> profilpersonnels = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Boolean detail = true;

    public RhsController() {

    }

    @PostConstruct
    private void init() {
        annee = new Annee();
        this.updateCommentaire();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void uptadeTable() {
        try {
            rhses.clear();
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
                if (structure != null) {
                    if (!this.getProfilpersonnels().isEmpty()) {

                        if (rhsFacadeLocal.find(structure, annee).isEmpty()) {
                            for (Profilpersonnel p : this.getProfilpersonnels()) {
                                Rhs rhs = new Rhs();
                                rhs.setIdannee(annee);
                                rhs.setIdprofilpersonnel(p);
                                rhs.setIdstructure(structure);
                                rhs.setValeur(0);
                                rhses.add(rhs);
                            }
                        } else {
                            for (Profilpersonnel p : this.getProfilpersonnels()) {
                                List<Rhs> temp = rhsFacadeLocal.find(structure, p, annee);
                                if (temp.isEmpty()) {
                                    Rhs rhs = new Rhs();
                                    rhs.setIdannee(annee);
                                    rhs.setIdprofilpersonnel(p);
                                    rhs.setIdstructure(structure);
                                    rhs.setValeur(0);
                                    rhses.add(rhs);
                                } else {
                                    rhses.add(temp.get(0));
                                }
                            }
                        }
                    } else {
                        System.err.println("aucun profil");
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez selectionnner une ligne");
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 8);
                if (!commentairetabs.isEmpty()) {
                    commentairetab = commentairetabs.get(0);
                    return;
                }
                commentairetab = new Commentairetab();

            } else {
                JsfUtil.addErrorMessage("Veillez selectionner une année");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 8);
            if (!commentairetabs.isEmpty()) {
                commentairetab = commentairetabs.get(0);
                return;
            }
            commentairetab = new Commentairetab();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (!rhses.isEmpty()) {
                for (Rhs r : rhses) {
                    if (r.getIdrhs() == null) {
                        r.setIdrhs(rhsFacadeLocal.nextId());
                        rhsFacadeLocal.create(r);
                    } else {
                        rhsFacadeLocal.edit(r);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 8);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(8);
                    commentairetabFacadeLocal.create(commentairetab);
                } else {
                    commentairetabFacadeLocal.edit(commentairetab);
                }

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findRhs(Structure structure, Profilpersonnel profilpersonnel) {
        String resultat = "";
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());

                if (structure != null) {
                    if (profilpersonnel != null) {

                        List<Rhs> rhses = rhsFacadeLocal.find(structure, profilpersonnel, annee);
                        if (!rhses.isEmpty()) {
                            resultat = "" + rhses.get(0).getValeur();
                        } else {
                            resultat = "";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        return mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findAllRange();
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        try {
            if (SessionMBean.getDistrict() != null) {
                structures = structureFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public List<Profilpersonnel> getProfilpersonnels() {
        profilpersonnels = profilpersonnelFacadeLocal.findAllRange();
        return profilpersonnels;
    }

    public void setProfilpersonnels(List<Profilpersonnel> profilpersonnels) {
        this.profilpersonnels = profilpersonnels;
    }

    public List<Rhs> getRhses() {
        return rhses;
    }

    public void setRhses(List<Rhs> rhses) {
        this.rhses = rhses;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
