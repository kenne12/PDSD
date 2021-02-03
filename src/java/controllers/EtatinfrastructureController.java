/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Etatinfrastructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.EtatinfrastructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class EtatinfrastructureController {

    @EJB
    private EtatinfrastructureFacadeLocal etatinfrastructureFacadeLocal;
    private Etatinfrastructure etatinfrastructure = new Etatinfrastructure();
    private List<Etatinfrastructure> etatinfrastructures = new ArrayList<>();

    private String mode = "";
    private boolean detail = true;

    /**
     * Creates a new instance of EtatinfrastructureController
     */
    public EtatinfrastructureController() {
    }

    @PostConstruct
    private void init() {
        etatinfrastructure = new Etatinfrastructure();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
        try {
            etatinfrastructure = new Etatinfrastructure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {
                etatinfrastructure.setIdetatinfrastructure(etatinfrastructureFacadeLocal.nextId());
                etatinfrastructureFacadeLocal.create(etatinfrastructure);
                etatinfrastructure = new Etatinfrastructure();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (etatinfrastructure != null) {

                    etatinfrastructureFacadeLocal.edit(etatinfrastructure);

                    JsfUtil.addSuccessMessage("Sous axe stratégique mis à jour avec succès");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (etatinfrastructure != null) {
                etatinfrastructureFacadeLocal.remove(etatinfrastructure);
                etatinfrastructure = new Etatinfrastructure();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Etatinfrastructure getEtatinfrastructure() {
        return etatinfrastructure;
    }

    public void setEtatinfrastructure(Etatinfrastructure etatinfrastructure) {
        this.etatinfrastructure = etatinfrastructure;
    }

    public List<Etatinfrastructure> getEtatinfrastructures() {
        etatinfrastructures = etatinfrastructureFacadeLocal.findAll();
        return etatinfrastructures;
    }

    public void setEtatinfrastructures(List<Etatinfrastructure> etatinfrastructures) {
        this.etatinfrastructures = etatinfrastructures;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
