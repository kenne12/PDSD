/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquemortalite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquemortaliteFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriquemortaliteController {

    /**
     * Creates a new instance of RubriquemortaliteController
     */
    @EJB
    private RubriquemortaliteFacadeLocal rubriquemortaliteFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriquemortalite rubriquemortalite;
    private List<Rubriquemortalite> rubriquemortalites = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriquemortaliteController() {

    }

    @PostConstruct
    private void init() {
        rubriquemortalite = new Rubriquemortalite();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriquemortalite = new Rubriquemortalite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriquemortalite() {
        try {

            if (mode == "Create") {
                rubriquemortalite.setIdrubriquemortalite(rubriquemortaliteFacadeLocal.nextId());
                List<Rubriquemortalite> test = rubriquemortaliteFacadeLocal.findByNom(rubriquemortalite.getNomFr());

                if (test.isEmpty()) {
                    rubriquemortaliteFacadeLocal.create(rubriquemortalite);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' rubriquemortalite -> " + rubriquemortalite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubriquemortalite portant de nom existe dejà");
                }
            } else {
                Rubriquemortalite test = rubriquemortaliteFacadeLocal.find(rubriquemortalite.getIdrubriquemortalite());
                utilitaire.Utilitaires.saveOperation("Modification du rubriquemortalite -> " + test.getNomFr()+ " par -> " + rubriquemortalite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriquemortaliteFacadeLocal.edit(rubriquemortalite);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriquemortalite() {
        try {
            if (rubriquemortalite != null) {
              
                    rubriquemortaliteFacadeLocal.remove(rubriquemortalite);
                    Utilitaires.saveOperation("Suppression du rubriquemortalite -> " + rubriquemortalite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun rubriquemortalite selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rubriquemortalite getRubriquemortalite() {
        return rubriquemortalite;
    }

    public void setRubriquemortalite(Rubriquemortalite rubriquemortalite) {
        this.rubriquemortalite = rubriquemortalite;
    }

    public List<Rubriquemortalite> getRubriquemortalites() {
        rubriquemortalites = rubriquemortaliteFacadeLocal.findAll();
        return rubriquemortalites;
    }

    public void setRubriquemortalites(List<Rubriquemortalite> rubriquemortalites) {
        this.rubriquemortalites = rubriquemortalites;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
