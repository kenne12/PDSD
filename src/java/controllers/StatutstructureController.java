/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Statutstructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.StatutstructureFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class StatutstructureController {

    /**
     * Creates a new instance of StatutstructureController
     */
    @EJB
    private StatutstructureFacadeLocal statutstructureFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Statutstructure statutstructure;
    private List<Statutstructure> statutstructures = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public StatutstructureController() {

    }

    @PostConstruct
    private void init() {
        statutstructure = new Statutstructure();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        statutstructure = new Statutstructure();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveStatutstructure() {
        try {

            if (mode == "Create") {
                statutstructure.setIdstatutstructure(statutstructureFacadeLocal.nextId());
                List<Statutstructure> test = statutstructureFacadeLocal.findByNom(statutstructure.getNomFr());

                if (test.isEmpty()) {
                    statutstructureFacadeLocal.create(statutstructure);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' statutstructure -> " + statutstructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un statutstructure portant de nom existe dejà");
                }
            } else {
                Statutstructure test = statutstructureFacadeLocal.find(statutstructure.getIdstatutstructure());
                utilitaire.Utilitaires.saveOperation("Modification du statutstructure -> " + test.getNomFr() + " par -> " + statutstructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                statutstructureFacadeLocal.edit(statutstructure);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStatutstructure() {
        try {
            if (statutstructure != null) {

                statutstructureFacadeLocal.remove(statutstructure);
                Utilitaires.saveOperation("Suppression du statutstructure -> " + statutstructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun statutstructure selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statutstructure getStatutstructure() {
        return statutstructure;
    }

    public void setStatutstructure(Statutstructure statutstructure) {
        this.statutstructure = statutstructure;
    }

    public List<Statutstructure> getStatutstructures() {
        statutstructures = statutstructureFacadeLocal.findAllRange();
        return statutstructures;
    }

    public void setStatutstructures(List<Statutstructure> statutstructures) {
        this.statutstructures = statutstructures;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
