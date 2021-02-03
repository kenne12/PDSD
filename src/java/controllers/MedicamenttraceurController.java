/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Medicamenttraceur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.MedicamenttraceurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class MedicamenttraceurController {

    /**
     * Creates a new instance of MedicamenttraceurController
     */
    @EJB
    private MedicamenttraceurFacadeLocal medicamenttraceurFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Medicamenttraceur medicamenttraceur;
    private List<Medicamenttraceur> medicamenttraceurs = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public MedicamenttraceurController() {

    }

    @PostConstruct
    private void init() {
        medicamenttraceur = new Medicamenttraceur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        medicamenttraceur = new Medicamenttraceur();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveMedicamenttraceur() {
        try {

            if (mode == "Create") {
                medicamenttraceur.setIdmedicamenttraceur(medicamenttraceurFacadeLocal.nextId());
                List<Medicamenttraceur> test = medicamenttraceurFacadeLocal.findByNom(medicamenttraceur.getNomFr());

                if (test.isEmpty()) {
                    medicamenttraceurFacadeLocal.create(medicamenttraceur);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de du medicament traceur -> " + medicamenttraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un medicament traceur portant de nom existe dejà");
                }
            } else {
                Medicamenttraceur test = medicamenttraceurFacadeLocal.find(medicamenttraceur.getIdmedicamenttraceur());
                utilitaire.Utilitaires.saveOperation("Modification du medicament traceur -> " + test.getNomFr() + " par -> " + medicamenttraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                medicamenttraceurFacadeLocal.edit(medicamenttraceur);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMedicamenttraceur() {
        try {
            if (medicamenttraceur != null) {

                medicamenttraceurFacadeLocal.remove(medicamenttraceur);
                Utilitaires.saveOperation("Suppression du medicament traceur -> " + medicamenttraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun medicamenttraceur selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Medicamenttraceur getMedicamenttraceur() {
        return medicamenttraceur;
    }

    public void setMedicamenttraceur(Medicamenttraceur medicamenttraceur) {
        this.medicamenttraceur = medicamenttraceur;
    }

    public List<Medicamenttraceur> getMedicamenttraceurs() {
        medicamenttraceurs = medicamenttraceurFacadeLocal.findAll();
        return medicamenttraceurs;
    }

    public void setMedicamenttraceurs(List<Medicamenttraceur> medicamenttraceurs) {
        this.medicamenttraceurs = medicamenttraceurs;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
