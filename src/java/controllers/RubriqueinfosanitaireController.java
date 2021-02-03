/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriqueinfosanitaire;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriqueinfosanitaireFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriqueinfosanitaireController {

    /**
     * Creates a new instance of RubriqueinfosanitaireController
     */
    @EJB
    private RubriqueinfosanitaireFacadeLocal rubriqueinfosanitaireFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriqueinfosanitaire rubriqueinfosanitaire;
    private List<Rubriqueinfosanitaire> rubriqueinfosanitaires = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriqueinfosanitaireController() {

    }

    @PostConstruct
    private void init() {
        rubriqueinfosanitaire = new Rubriqueinfosanitaire();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriqueinfosanitaire = new Rubriqueinfosanitaire();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriqueinfosanitaire() {
        try {

            if (mode == "Create") {
                rubriqueinfosanitaire.setIdrubriqueinfosanitaire(rubriqueinfosanitaireFacadeLocal.nextId());
                List<Rubriqueinfosanitaire> test = rubriqueinfosanitaireFacadeLocal.findByNom(rubriqueinfosanitaire.getNomFr());

                if (test.isEmpty()) {
                    rubriqueinfosanitaireFacadeLocal.create(rubriqueinfosanitaire);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de la rubrique d'infosanitaire -> " + rubriqueinfosanitaire.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubrique d'infosanitaire portant de nom existe dejà");
                }
            } else {
                Rubriqueinfosanitaire test = rubriqueinfosanitaireFacadeLocal.find(rubriqueinfosanitaire.getIdrubriqueinfosanitaire());
                utilitaire.Utilitaires.saveOperation("Modification du rubriqueinfosanitaire -> " + test.getNomFr() + " par -> " + rubriqueinfosanitaire.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriqueinfosanitaireFacadeLocal.edit(rubriqueinfosanitaire);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriqueinfosanitaire() {
        try {
            if (rubriqueinfosanitaire != null) {

                rubriqueinfosanitaireFacadeLocal.remove(rubriqueinfosanitaire);
                Utilitaires.saveOperation("Suppression du rubriqueinfosanitaire -> " + rubriqueinfosanitaire.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun rubriqueinfosanitaire selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rubriqueinfosanitaire getRubriqueinfosanitaire() {
        return rubriqueinfosanitaire;
    }

    public void setRubriqueinfosanitaire(Rubriqueinfosanitaire rubriqueinfosanitaire) {
        this.rubriqueinfosanitaire = rubriqueinfosanitaire;
    }

    public List<Rubriqueinfosanitaire> getRubriqueinfosanitaires() {
        rubriqueinfosanitaires = rubriqueinfosanitaireFacadeLocal.findAll();
        return rubriqueinfosanitaires;
    }

    public void setRubriqueinfosanitaires(List<Rubriqueinfosanitaire> rubriqueinfosanitaires) {
        this.rubriqueinfosanitaires = rubriqueinfosanitaires;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
