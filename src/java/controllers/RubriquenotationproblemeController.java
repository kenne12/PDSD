/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquenotationprobleme;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquenotationproblemeFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriquenotationproblemeController {

    /**
     * Creates a new instance of RubriquenotationproblemeController
     */
    @EJB
    private RubriquenotationproblemeFacadeLocal rubriquenotationproblemeFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriquenotationprobleme rubriquenotationprobleme;
    private List<Rubriquenotationprobleme> rubriquenotationproblemes = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriquenotationproblemeController() {

    }

    @PostConstruct
    private void init() {
        rubriquenotationprobleme = new Rubriquenotationprobleme();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriquenotationprobleme = new Rubriquenotationprobleme();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriquenotationprobleme() {
        try {

            if (mode == "Create") {
                rubriquenotationprobleme.setIdrubriquenotationprobleme(rubriquenotationproblemeFacadeLocal.nextId());
                List<Rubriquenotationprobleme> test = rubriquenotationproblemeFacadeLocal.findByNom(rubriquenotationprobleme.getNomFr());

                if (test.isEmpty()) {
                    rubriquenotationproblemeFacadeLocal.create(rubriquenotationprobleme);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' rubriquenotationprobleme -> " + rubriquenotationprobleme.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubriquenotationprobleme portant de nom existe dejà");
                }
            } else {
                Rubriquenotationprobleme test = rubriquenotationproblemeFacadeLocal.find(rubriquenotationprobleme.getIdrubriquenotationprobleme());
                utilitaire.Utilitaires.saveOperation("Modification du rubriquenotationprobleme -> " + test.getNomFr() + " par -> " + rubriquenotationprobleme.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriquenotationproblemeFacadeLocal.edit(rubriquenotationprobleme);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriquenotationprobleme() {
        try {
            if (rubriquenotationprobleme != null) {

                rubriquenotationproblemeFacadeLocal.remove(rubriquenotationprobleme);
                Utilitaires.saveOperation("Suppression du rubriquenotationprobleme -> " + rubriquenotationprobleme.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun rubriquenotationprobleme selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
