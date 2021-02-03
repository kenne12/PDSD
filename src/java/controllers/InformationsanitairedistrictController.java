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
import entities.Rubriqueinfosanitaire;
import entities.Informationsanitairedistrict;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.RubriqueinfosanitaireFacadeLocal;
import sessions.InformationsanitairedistrictFacadeLocal;
import sessions.StructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class InformationsanitairedistrictController {

    @EJB
    private InformationsanitairedistrictFacadeLocal informationsanitairedistrictFacadeLocal;
    private List<Informationsanitairedistrict> informationsanitairedistricts = new ArrayList<>();

    @EJB
    private RubriqueinfosanitaireFacadeLocal rubriqueinformationsanitaireFacadeLocal;
    private List<Rubriqueinfosanitaire> rubriqueinformationsanitaires = new ArrayList<>();

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
    public InformationsanitairedistrictController() {
    }

    @PostConstruct
    private void init() {
        structure = new Structure();
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
            informationsanitairedistricts.clear();

            if (structure != null) {
                if (!this.getRubriqueinfosanitaires().isEmpty()) {
                    for (Rubriqueinfosanitaire m : this.getRubriqueinfosanitaires()) {
                        List<Informationsanitairedistrict> temp = informationsanitairedistrictFacadeLocal.find(structure, m, SessionMBean.getDistrict());
                        if (temp.isEmpty()) {
                            Informationsanitairedistrict informationsanitairedistrict = new Informationsanitairedistrict();
                            informationsanitairedistrict.setIdrubriqueinfosanitaire(m);
                            informationsanitairedistrict.setIdstructure(structure);
                            informationsanitairedistrict.setIddistrict(SessionMBean.getDistrict());
                            informationsanitairedistricts.add(informationsanitairedistrict);
                        } else {
                            informationsanitairedistricts.add(temp.get(0));
                        }
                    }
                }

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionnner une ligne");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {

            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 15);
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
            if (!informationsanitairedistricts.isEmpty()) {
                for (Informationsanitairedistrict m : informationsanitairedistricts) {
                    if (m.getIdinformationsanitairedistrict() == null) {
                        m.setIdinformationsanitairedistrict(informationsanitairedistrictFacadeLocal.nextId());
                          m.setIddistrict(SessionMBean.getDistrict());
                        informationsanitairedistrictFacadeLocal.create(m);
                    } else {
                        informationsanitairedistrictFacadeLocal.edit(m);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 15);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(15);
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

    public String findRubriqueinformationsanitaire(Structure structure, Rubriqueinfosanitaire rubriqueinformationsanitaire) {
        String resultat = "";
        try {

            if (structure != null) {
                if (rubriqueinformationsanitaire != null) {

                    List<Informationsanitairedistrict> temps = informationsanitairedistrictFacadeLocal.find(structure, rubriqueinformationsanitaire,SessionMBean.getDistrict());
                    if (!temps.isEmpty()) {
                        resultat = "" + temps.get(0).getValeur();
                    } else {
                        resultat = "";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
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
        annees = anneeFacadeLocal.findAllRange();
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public List<Rubriqueinfosanitaire> getRubriqueinfosanitaires() {
        rubriqueinformationsanitaires = rubriqueinformationsanitaireFacadeLocal.findAllRange();
        return rubriqueinformationsanitaires;
    }

    public void setRubriqueinfosanitaires(List<Rubriqueinfosanitaire> rubriqueinformationsanitaires) {
        this.rubriqueinformationsanitaires = rubriqueinformationsanitaires;
    }

    public List<Informationsanitairedistrict> getInformationsanitairedistricts() {
        return informationsanitairedistricts;
    }

    public void setInformationsanitairedistricts(List<Informationsanitairedistrict> informationsanitairedistricts) {
        this.informationsanitairedistricts = informationsanitairedistricts;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
