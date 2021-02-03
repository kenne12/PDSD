/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquepopulation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquepopulationFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriquepopulationController {

    /**
     * Creates a new instance of RubriquepopulationController
     */
    @EJB
    private RubriquepopulationFacadeLocal rubriquepopulationFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriquepopulation rubriquepopulation;
    private List<Rubriquepopulation> rubriquepopulations = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriquepopulationController() {

    }

    @PostConstruct
    private void init() {
        rubriquepopulation = new Rubriquepopulation();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriquepopulation = new Rubriquepopulation();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriquepopulation() {
        try {

            if (mode == "Create") {
                rubriquepopulation.setIdrubriquepopulation(rubriquepopulationFacadeLocal.nextId());
                List<Rubriquepopulation> test = rubriquepopulationFacadeLocal.findByNom(rubriquepopulation.getNomFr());

                if (test.isEmpty()) {
                    rubriquepopulationFacadeLocal.create(rubriquepopulation);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' rubriquepopulation -> " + rubriquepopulation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubriquepopulation portant de nom existe dejà");
                }
            } else {
                Rubriquepopulation test = rubriquepopulationFacadeLocal.find(rubriquepopulation.getIdrubriquepopulation());
                utilitaire.Utilitaires.saveOperation("Modification du rubriquepopulation -> " + test.getNomFr() + " par -> " + rubriquepopulation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriquepopulationFacadeLocal.edit(rubriquepopulation);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriquepopulation() {
        try {
            if (rubriquepopulation != null) {

                rubriquepopulationFacadeLocal.remove(rubriquepopulation);
                Utilitaires.saveOperation("Suppression du rubriquepopulation -> " + rubriquepopulation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun rubriquepopulation selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rubriquepopulation getRubriquepopulation() {
        return rubriquepopulation;
    }

    public void setRubriquepopulation(Rubriquepopulation rubriquepopulation) {
        this.rubriquepopulation = rubriquepopulation;
    }

    public List<Rubriquepopulation> getRubriquepopulations() {
        rubriquepopulations = rubriquepopulationFacadeLocal.findAll();
        return rubriquepopulations;
    }

    public void setRubriquepopulations(List<Rubriquepopulation> rubriquepopulations) {
        this.rubriquepopulations = rubriquepopulations;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
