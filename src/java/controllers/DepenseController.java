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
import entities.Depense;
import entities.Naturedepense;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.DepenseFacadeLocal;
import sessions.NaturedepenseFacadeLocal;
import sessions.StructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class DepenseController {

    @EJB
    private DepenseFacadeLocal depenseFacadeLocal;
    private List<Depense> depenses = new ArrayList<>();

    @EJB
    private NaturedepenseFacadeLocal naturedepenseFacadeLocal;
    private Naturedepense naturedepense;
    private List<Naturedepense> naturedepenses = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public DepenseController() {
    }

    @PostConstruct
    private void init() {
        structure = new Structure();
        annee = new Annee();
        naturedepense = new Naturedepense();
        this.updateCommentaire();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void initStructure() {
        try {
            if (structure.getIdstructure() != null) {
                structure = structureFacadeLocal.find(structure.getIdstructure());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAnnee() {
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable() {
        try {
            depenses.clear();
            if (structure != null) {
                if (!this.getNaturedepenses().isEmpty()) {
                    for (Naturedepense n : this.getNaturedepenses()) {
                        Depense depense = new Depense();
                        depense.setIdnaturedepense(n);
                        depense.setIdstructure(structure);
                        depenses.add(depense);
                    }
                } else {
                    System.err.println("Aucune nature de dépenses");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionnner une structure");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uptadeTable() {
        try {
            depenses.clear();
            if (annee.getIdannee() != null) {

                if (structure != null) {
                    if (!this.getNaturedepenses().isEmpty()) {

                        if (depenseFacadeLocal.find(structure, annee).isEmpty()) {
                            for (Naturedepense n : this.getNaturedepenses()) {
                                Depense depense = new Depense();
                                depense.setIdannee(annee);
                                depense.setIdnaturedepense(n);
                                depense.setIdstructure(structure);
                                depenses.add(depense);
                            }
                        } else {
                            for (Naturedepense n : this.getNaturedepenses()) {
                                List<Depense> temp = depenseFacadeLocal.find(structure, n, annee);
                                if (temp.isEmpty()) {
                                    Depense depense = new Depense();
                                    depense.setIdannee(annee);
                                    depense.setIdnaturedepense(n);
                                    depense.setIdstructure(structure);
                                    depenses.add(depense);
                                } else {
                                    depenses.add(temp.get(0));
                                }
                            }
                        }
                    } else {
                        System.err.println("aucun profil");
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez selectionnner une structure");
                }
            } else {
                JsfUtil.addErrorMessage("Veillez selectionner une année");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 13);
            if (!commentairetabs.isEmpty()) {
                commentairetab = commentairetabs.get(0);
                return;
            }
            commentairetab = new Commentairetab();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!depenses.isEmpty()) {
                for (Depense d : depenses) {
                    if (d.getIddepense() == null) {
                        d.setIddepense(depenseFacadeLocal.nextId());
                        depenseFacadeLocal.edit(d);
                    } else {
                        depenseFacadeLocal.edit(d);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 13);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(13);
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

    public Depense findDepense(Naturedepense naturedepense, Annee annee) {
        Depense depense = null;
        try {
            if (annee.getIdannee() != null) {
                if (structure != null) {
                    if (naturedepense != null) {

                        List<Depense> temps = depenseFacadeLocal.find(structure, naturedepense, annee);
                        if (!temps.isEmpty()) {
                            depense = temps.get(0);
                        } else {
                            depense = new Depense();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depense;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
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
            structures = structureFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            annees = anneeFacadeLocal.findByEtatDepense(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public List<Naturedepense> getNaturedepenses() {
        naturedepenses = naturedepenseFacadeLocal.findAll();
        return naturedepenses;
    }

    public void setNaturedepenses(List<Naturedepense> naturedepenses) {
        this.naturedepenses = naturedepenses;
    }

    public Naturedepense getNaturedepense() {
        return naturedepense;
    }

    public void setNaturedepense(Naturedepense naturedepense) {
        this.naturedepense = naturedepense;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
