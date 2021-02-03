/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquenotationprobleme;
import entities.Sousrubriquenotationprobleme;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquenotationproblemeFacadeLocal;
import sessions.SousrubriquenotationproblemeFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class SousrubriquenotationproblemeController {

    /**
     * Creates a new instance of SousrubriquenotationproblemeController
     */
    @EJB
    private SousrubriquenotationproblemeFacadeLocal sousrubriquenotationproblemeFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Sousrubriquenotationprobleme sousrubriquenotationprobleme;
    private List<Sousrubriquenotationprobleme> sousrubriquenotationproblemes = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    @EJB
    private RubriquenotationproblemeFacadeLocal rubriquenotationproblemeFacadeLocal;
    private Rubriquenotationprobleme rubriquenotationprobleme;
    private List<Rubriquenotationprobleme> rubriquenotationproblemes = new ArrayList<>();

    public SousrubriquenotationproblemeController() {

    }

    @PostConstruct
    private void init() {
        sousrubriquenotationprobleme = new Sousrubriquenotationprobleme();
        rubriquenotationprobleme = new Rubriquenotationprobleme();
        rubriquenotationproblemes = rubriquenotationproblemeFacadeLocal.findAll();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        sousrubriquenotationprobleme = new Sousrubriquenotationprobleme();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveSousrubriquenotationprobleme() {
        try {

            if (mode == "Create") {
                sousrubriquenotationprobleme.setIdsousrubriquenotationprobleme(sousrubriquenotationproblemeFacadeLocal.nextId());

                sousrubriquenotationproblemeFacadeLocal.create(sousrubriquenotationprobleme);
                utilitaire.Utilitaires.saveOperation("Enregistrement de la sous rubrique de notation des probleme -> " + sousrubriquenotationprobleme.getScoremax(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");

            } else {
                Sousrubriquenotationprobleme test = sousrubriquenotationproblemeFacadeLocal.find(sousrubriquenotationprobleme.getIdsousrubriquenotationprobleme());
                utilitaire.Utilitaires.saveOperation("Modification du sousrubriquenotationprobleme -> " + test.getScoremax() + " par -> " + sousrubriquenotationprobleme.getScoremax(), SessionMBean.getUser(), mouchardFacadeLocal);
                sousrubriquenotationproblemeFacadeLocal.edit(sousrubriquenotationprobleme);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSousrubriquenotationprobleme() {
        try {
            if (sousrubriquenotationprobleme != null) {

                sousrubriquenotationproblemeFacadeLocal.remove(sousrubriquenotationprobleme);
                Utilitaires.saveOperation("Suppression du sousrubriquenotationprobleme -> " + sousrubriquenotationprobleme.getScoremax(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun sousrubriquenotationprobleme selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sousrubriquenotationprobleme getSousrubriquenotationprobleme() {
        return sousrubriquenotationprobleme;
    }

    public void setSousrubriquenotationprobleme(Sousrubriquenotationprobleme sousrubriquenotationprobleme) {
        this.sousrubriquenotationprobleme = sousrubriquenotationprobleme;
    }

    public List<Sousrubriquenotationprobleme> getSousrubriquenotationproblemes() {
        sousrubriquenotationproblemes = sousrubriquenotationproblemeFacadeLocal.findAll();
        return sousrubriquenotationproblemes;
    }

    public void setSousrubriquenotationproblemes(List<Sousrubriquenotationprobleme> sousrubriquenotationproblemes) {
        this.sousrubriquenotationproblemes = sousrubriquenotationproblemes;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Rubriquenotationprobleme getRubriquenotationprobleme() {
        return rubriquenotationprobleme;
    }

    public void setRubriquenotationprobleme(Rubriquenotationprobleme rubriquenotationprobleme) {
        this.rubriquenotationprobleme = rubriquenotationprobleme;
    }

    public List<Rubriquenotationprobleme> getRubriquenotationproblemes() {
        rubriquenotationproblemes = rubriquenotationproblemeFacadeLocal.findAll();
        return rubriquenotationproblemes;
    }

    public void setRubriquenotationproblemes(List<Rubriquenotationprobleme> rubriquenotationproblemes) {
        this.rubriquenotationproblemes = rubriquenotationproblemes;
    }

}
