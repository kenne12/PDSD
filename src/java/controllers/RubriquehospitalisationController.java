/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquehospitalisation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquehospitalisationFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriquehospitalisationController {

    /**
     * Creates a new instance of RubriquehospitalisationController
     */
    @EJB
    private RubriquehospitalisationFacadeLocal rubriquehospitalisationFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriquehospitalisation rubriquehospitalisation;
    private List<Rubriquehospitalisation> rubriquehospitalisations = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriquehospitalisationController() {

    }

    @PostConstruct
    private void init() {
        rubriquehospitalisation = new Rubriquehospitalisation();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriquehospitalisation = new Rubriquehospitalisation();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriquehospitalisation() {
        try {

            if (mode == "Create") {
                rubriquehospitalisation.setIdrubriquehospitalisation(rubriquehospitalisationFacadeLocal.nextId());
                List<Rubriquehospitalisation> test = rubriquehospitalisationFacadeLocal.findByNom(rubriquehospitalisation.getNomFr());

                if (test.isEmpty()) {
                    rubriquehospitalisationFacadeLocal.create(rubriquehospitalisation);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de la  rubrique d'hospitalisation -> " + rubriquehospitalisation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubrique d'hospitalisation portant de nom existe dejà");
                }
            } else {
                Rubriquehospitalisation test = rubriquehospitalisationFacadeLocal.find(rubriquehospitalisation.getIdrubriquehospitalisation());
                utilitaire.Utilitaires.saveOperation("Modification du rubrique d'hospitalisation -> " + test.getNomFr() + " par -> " + rubriquehospitalisation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriquehospitalisationFacadeLocal.edit(rubriquehospitalisation);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriquehospitalisation() {
        try {
            if (rubriquehospitalisation != null) {

                rubriquehospitalisationFacadeLocal.remove(rubriquehospitalisation);
                Utilitaires.saveOperation("Suppression du rubriquehospitalisation -> " + rubriquehospitalisation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun rubriquehospitalisation selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rubriquehospitalisation getRubriquehospitalisation() {
        return rubriquehospitalisation;
    }

    public void setRubriquehospitalisation(Rubriquehospitalisation rubriquehospitalisation) {
        this.rubriquehospitalisation = rubriquehospitalisation;
    }

    public List<Rubriquehospitalisation> getRubriquehospitalisations() {
        rubriquehospitalisations = rubriquehospitalisationFacadeLocal.findAll();
        return rubriquehospitalisations;
    }

    public void setRubriquehospitalisations(List<Rubriquehospitalisation> rubriquehospitalisations) {
        this.rubriquehospitalisations = rubriquehospitalisations;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
