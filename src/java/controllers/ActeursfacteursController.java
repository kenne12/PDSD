/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Acteursfacteurs;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.ActeursfacteursFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
  */
@ManagedBean
@ViewScoped
public class ActeursfacteursController {

    /**
     * Creates a new instance of ActeursfacteursController
      */
    @EJB
    private ActeursfacteursFacadeLocal acteursfacteursFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Acteursfacteurs acteursfacteurs;
    private List<Acteursfacteurs> acteursfacteurss = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public ActeursfacteursController() {

    }

    @PostConstruct
    private void init() {
        acteursfacteurs = new Acteursfacteurs();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        acteursfacteurs = new Acteursfacteurs();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveActeursfacteurs() {
        try {

            if (mode == "Create") {
                acteursfacteurs.setIdacteursfacteurs(acteursfacteursFacadeLocal.nextId());
                List<Acteursfacteurs> test = acteursfacteursFacadeLocal.findByNom(acteursfacteurs.getNom());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    acteursfacteursFacadeLocal.create(acteursfacteurs);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' acteursfacteurs -> " + acteursfacteurs.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un acteursfacteurs portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Acteursfacteurs test = acteursfacteursFacadeLocal.find(acteursfacteurs.getIdacteursfacteurs());
                utilitaire.Utilitaires.saveOperation("Modification du acteursfacteurs -> " + test.getNom() + " par -> " + acteursfacteurs.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                acteursfacteursFacadeLocal.edit(acteursfacteurs);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteActeursfacteurs() {
        try {
            if (acteursfacteurs != null) {
              
                    acteursfacteursFacadeLocal.remove(acteursfacteurs);
                    Utilitaires.saveOperation("Suppression du acteursfacteurs -> " + acteursfacteurs.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun acteursfacteurs selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Acteursfacteurs getActeursfacteurs() {
        return acteursfacteurs;
    }

    public void setActeursfacteurs(Acteursfacteurs acteursfacteurs) {
        this.acteursfacteurs = acteursfacteurs;
    }

    public List<Acteursfacteurs> getActeursfacteurss() {
        acteursfacteurss = acteursfacteursFacadeLocal.findAll();
        return acteursfacteurss;
    }

    public void setActeursfacteurss(List<Acteursfacteurs> acteursfacteurss) {
        this.acteursfacteurss = acteursfacteurss;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

} 
 