/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Hospitalisation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.HospitalisationFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class HospitalisationController {

    /**
     * Creates a new instance of HospitalisationController
     */
    @EJB
    private HospitalisationFacadeLocal hospitalisationFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Hospitalisation hospitalisation;
    private List<Hospitalisation> hospitalisations = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public HospitalisationController() {

    }

    @PostConstruct
    private void init() {
        hospitalisation = new Hospitalisation();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        hospitalisation = new Hospitalisation();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveHospitalisation() {
        try {

            if (mode == "Create") {
                hospitalisation.setIdhospitalisation(hospitalisationFacadeLocal.nextId());
                List<Hospitalisation> test = hospitalisationFacadeLocal.findByNom(hospitalisation.getNomFr());

                if (test.isEmpty()) {
                    hospitalisationFacadeLocal.create(hospitalisation);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' hospitalisation -> " + hospitalisation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un hospitalisation portant de nom existe dejà");
                }
            } else {
                Hospitalisation test = hospitalisationFacadeLocal.find(hospitalisation.getIdhospitalisation());
                utilitaire.Utilitaires.saveOperation("Modification du hospitalisation -> " + test.getNomFr() + " par -> " + hospitalisation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                hospitalisationFacadeLocal.edit(hospitalisation);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteHospitalisation() {
        try {
            if (hospitalisation != null) {

                hospitalisationFacadeLocal.remove(hospitalisation);
                Utilitaires.saveOperation("Suppression du hospitalisation -> " + hospitalisation.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun hospitalisation selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Hospitalisation getHospitalisation() {
        return hospitalisation;
    }

    public void setHospitalisation(Hospitalisation hospitalisation) {
        this.hospitalisation = hospitalisation;
    }

    public List<Hospitalisation> getHospitalisations() {
        hospitalisations = hospitalisationFacadeLocal.findAll();
        return hospitalisations;
    }

    public void setHospitalisations(List<Hospitalisation> hospitalisations) {
        this.hospitalisations = hospitalisations;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
