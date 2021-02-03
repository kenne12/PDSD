/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Etatequipement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.EtatequipementFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class EtatequipementController {

    @EJB
    private EtatequipementFacadeLocal etatequipementFacadeLocal;
    private Etatequipement etatequipement = new Etatequipement();
    private List<Etatequipement> etatequipements = new ArrayList<>();

    private String mode = "";
    private boolean detail = true;

    /**
     * Creates a new instance of EtatequipementController
     */
    public EtatequipementController() {
    }

    @PostConstruct
    private void init() {
        etatequipement = new Etatequipement();
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
            etatequipement = new Etatequipement();
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
                etatequipement.setIdetatequipement(etatequipementFacadeLocal.nextId());
                etatequipementFacadeLocal.create(etatequipement);
                etatequipement = new Etatequipement();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (etatequipement != null) {

                    etatequipementFacadeLocal.edit(etatequipement);

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
            if (etatequipement != null) {
                etatequipementFacadeLocal.remove(etatequipement);
                etatequipement = new Etatequipement();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Etatequipement getEtatequipement() {
        return etatequipement;
    }

    public void setEtatequipement(Etatequipement etatequipement) {
        this.etatequipement = etatequipement;
    }

    public List<Etatequipement> getEtatequipements() {
        etatequipements = etatequipementFacadeLocal.findAll();
        return etatequipements;
    }

    public void setEtatequipements(List<Etatequipement> etatequipements) {
        this.etatequipements = etatequipements;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
