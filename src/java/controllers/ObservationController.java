/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Observation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.ObservationFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ObservationController {

    /**
     * Creates a new instance of ObservationController
     */
    @EJB
    private ObservationFacadeLocal observationFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Observation observation;
    private List<Observation> observations = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public ObservationController() {

    }

    @PostConstruct
    private void init() {
        observation = new Observation();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        observation = new Observation();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveObservation() {
        try {

            if (mode == "Create") {
                observation.setIdobservation(observationFacadeLocal.nextId());
                List<Observation> test = observationFacadeLocal.findByNom(observation.getValeurFr());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    observationFacadeLocal.create(observation);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' observation -> " + observation.getValeurFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un observation portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Observation test = observationFacadeLocal.find(observation.getIdobservation());
                utilitaire.Utilitaires.saveOperation("Modification du observation -> " + test.getValeurFr()+ " par -> " + observation.getValeurFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                observationFacadeLocal.edit(observation);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteObservation() {
        try {
            if (observation != null) {
              
                    observationFacadeLocal.remove(observation);
                    Utilitaires.saveOperation("Suppression du observation -> " + observation.getValeurFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun observation selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public List<Observation> getObservations() {
        observations = observationFacadeLocal.findAll();
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
