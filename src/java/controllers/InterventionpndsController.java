/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Categorieintervention;
import entities.Interventionpnds;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CategorieinterventionFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.InterventionpndsFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class InterventionpndsController {

    /**
     * Creates a new instance of InterventionpndsController
     */
    @EJB
    private InterventionpndsFacadeLocal interventionpndsFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Interventionpnds interventionpnds;
    private List<Interventionpnds> interventionpndss = new ArrayList<>();

    @EJB
    private CategorieinterventionFacadeLocal categorieinterventionFacadeLocal;
    private Categorieintervention catintervention;
    private List<Categorieintervention> catinterventions = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public InterventionpndsController() {

    }

    @PostConstruct
    private void init() {
        interventionpnds = new Interventionpnds();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        interventionpnds = new Interventionpnds();
        interventionpnds.setRegion(true);
        interventionpnds.setDistrict(true);
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveInterventionpnds() {
        try {

            if (mode == "Create") {
                interventionpnds.setIdinterventionpnds(interventionpndsFacadeLocal.nextId());

                interventionpndsFacadeLocal.create(interventionpnds);
                utilitaire.Utilitaires.saveOperation("Enregistrement de l'intervention -> " + interventionpnds.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");

            } else {
                System.err.println("mode : " + mode);
                Interventionpnds test = interventionpndsFacadeLocal.find(interventionpnds.getIdinterventionpnds());
                utilitaire.Utilitaires.saveOperation("Modification du intervention pnds -> " + test.getNomFr() + " par -> " + interventionpnds.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                interventionpndsFacadeLocal.edit(interventionpnds);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteInterventionpnds() {
        try {
            if (interventionpnds != null) {

                interventionpndsFacadeLocal.remove(interventionpnds);
                Utilitaires.saveOperation("Suppression du interventionpnds -> " + interventionpnds.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun interventionpnds selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Interventionpnds getInterventionpnds() {
        return interventionpnds;
    }

    public void setInterventionpnds(Interventionpnds interventionpnds) {
        this.interventionpnds = interventionpnds;
    }

    public List<Interventionpnds> getInterventionpndss() {
        interventionpndss = interventionpndsFacadeLocal.findAllRange();
        return interventionpndss;
    }

    public void setInterventionpndss(List<Interventionpnds> interventionpndss) {
        this.interventionpndss = interventionpndss;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Categorieintervention getCatintervention() {
        return catintervention;
    }

    public void setCatintervention(Categorieintervention catintervention) {
        this.catintervention = catintervention;
    }

    public List<Categorieintervention> getCatinterventions() {
        catinterventions = categorieinterventionFacadeLocal.findAllRange();
        return catinterventions;
    }

    public void setCatinterventions(List<Categorieintervention> catinterventions) {
        this.catinterventions = catinterventions;
    }

}
