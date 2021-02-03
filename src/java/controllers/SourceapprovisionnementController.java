/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Sourceapprovisionnement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.SourceapprovisionnementFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class SourceapprovisionnementController {

    /**
     * Creates a new instance of SourceapprovisionnementController
     */
    @EJB
    private SourceapprovisionnementFacadeLocal sourceapprovisionnementFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Sourceapprovisionnement sourceapprovisionnement;
    private List<Sourceapprovisionnement> sourceapprovisionnements = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public SourceapprovisionnementController() {

    }

    @PostConstruct
    private void init() {
        sourceapprovisionnement = new Sourceapprovisionnement();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        sourceapprovisionnement = new Sourceapprovisionnement();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveSourceapprovisionnement() {
        try {

            if (mode == "Create") {
                sourceapprovisionnement.setIdsourceapprovisionnement(sourceapprovisionnementFacadeLocal.nextId());
                List<Sourceapprovisionnement> test = sourceapprovisionnementFacadeLocal.findByNom(sourceapprovisionnement.getNomFr());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    sourceapprovisionnementFacadeLocal.create(sourceapprovisionnement);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' sourceapprovisionnement -> " + sourceapprovisionnement.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un sourceapprovisionnement portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Sourceapprovisionnement test = sourceapprovisionnementFacadeLocal.find(sourceapprovisionnement.getIdsourceapprovisionnement());
                utilitaire.Utilitaires.saveOperation("Modification du sourceapprovisionnement -> " + test.getNomFr() + " par -> " + sourceapprovisionnement.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                sourceapprovisionnementFacadeLocal.edit(sourceapprovisionnement);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSourceapprovisionnement() {
        try {
            if (sourceapprovisionnement != null) {

                sourceapprovisionnementFacadeLocal.remove(sourceapprovisionnement);
                Utilitaires.saveOperation("Suppression du sourceapprovisionnement -> " + sourceapprovisionnement.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun sourceapprovisionnement selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sourceapprovisionnement getSourceapprovisionnement() {
        return sourceapprovisionnement;
    }

    public void setSourceapprovisionnement(Sourceapprovisionnement sourceapprovisionnement) {
        this.sourceapprovisionnement = sourceapprovisionnement;
    }

    public List<Sourceapprovisionnement> getSourceapprovisionnements() {
        sourceapprovisionnements = sourceapprovisionnementFacadeLocal.findAll();
        return sourceapprovisionnements;
    }

    public void setSourceapprovisionnements(List<Sourceapprovisionnement> sourceapprovisionnements) {
        this.sourceapprovisionnements = sourceapprovisionnements;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
