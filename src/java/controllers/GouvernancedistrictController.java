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
import entities.Rubriquegouvernance;
import entities.Gouvernancedistrict;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.RubriquegouvernanceFacadeLocal;
import sessions.GouvernancedistrictFacadeLocal;
import sessions.StructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class GouvernancedistrictController {

    @EJB
    private GouvernancedistrictFacadeLocal gouvernancedistrictFacadeLocal;
    private List<Gouvernancedistrict> gouvernancedistricts = new ArrayList<>();

    @EJB
    private RubriquegouvernanceFacadeLocal rubriquegouvernanceFacadeLocal;
    private List<Rubriquegouvernance> rubriquegouvernances = new ArrayList<>();

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
    public GouvernancedistrictController() {
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
            gouvernancedistricts.clear();

            if (structure != null) {
                if (!this.getRubriquegouvernances().isEmpty()) {
                    for (Rubriquegouvernance m : this.getRubriquegouvernances()) {
                        List<Gouvernancedistrict> temp = gouvernancedistrictFacadeLocal.find(structure, m, SessionMBean.getDistrict());
                        if (temp.isEmpty()) {
                            Gouvernancedistrict gouvernancedistrict = new Gouvernancedistrict();
                            gouvernancedistrict.setIdrubriquegouvernance(m);
                            gouvernancedistrict.setIdstructure(structure);
                            gouvernancedistrict.setIddistrict(SessionMBean.getDistrict());
                            gouvernancedistricts.add(gouvernancedistrict);
                        } else {
                            gouvernancedistricts.add(temp.get(0));
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

            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 16);
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
            if (!gouvernancedistricts.isEmpty()) {
                for (Gouvernancedistrict m : gouvernancedistricts) {
                    if (m.getIdgouvernancedistrict() == null) {
                        m.setIdgouvernancedistrict(gouvernancedistrictFacadeLocal.nextId());
                        m.setIddistrict(SessionMBean.getDistrict());
                        gouvernancedistrictFacadeLocal.create(m);
                    } else {
                        gouvernancedistrictFacadeLocal.edit(m);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 16);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(16);
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

    public String findRubriquegouvernance(Structure structure, Rubriquegouvernance rubriquegouvernance) {
        String resultat = "";
        try {

            if (structure != null) {
                if (rubriquegouvernance != null) {

                    List<Gouvernancedistrict> temps = gouvernancedistrictFacadeLocal.find(structure, rubriquegouvernance, SessionMBean.getDistrict());
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

    public List<Rubriquegouvernance> getRubriquegouvernances() {
        rubriquegouvernances = rubriquegouvernanceFacadeLocal.findAllRange();
        return rubriquegouvernances;
    }

    public void setRubriquegouvernances(List<Rubriquegouvernance> rubriquegouvernances) {
        this.rubriquegouvernances = rubriquegouvernances;
    }

    public List<Gouvernancedistrict> getGouvernancedistricts() {
        return gouvernancedistricts;
    }

    public void setGouvernancedistricts(List<Gouvernancedistrict> gouvernancedistricts) {
        this.gouvernancedistricts = gouvernancedistricts;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
