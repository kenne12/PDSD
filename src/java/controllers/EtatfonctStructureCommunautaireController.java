/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Etatfonctstructcom;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.EtatfonctstructcomFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class EtatfonctStructureCommunautaireController {

    @EJB
    private EtatfonctstructcomFacadeLocal etatfonctstructcomFacadeLocal;
    private Etatfonctstructcom etatfonctstructcom;
    private List<Etatfonctstructcom> etatfonctstructcoms = new ArrayList<>();

    private boolean detail = true;
    private String mode = "";

    /**
     * Creates a new instance of EtatfonctStructureCommunautaireController
     */
    public EtatfonctStructureCommunautaireController() {
    }

    @PostConstruct
    private void init() {

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
            etatfonctstructcom = new Etatfonctstructcom();
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
                etatfonctstructcom.setIdetatfonctstructcom(etatfonctstructcomFacadeLocal.nextId());
                etatfonctstructcomFacadeLocal.create(etatfonctstructcom);
                etatfonctstructcom = new Etatfonctstructcom();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (etatfonctstructcom != null) {

                    etatfonctstructcomFacadeLocal.edit(etatfonctstructcom);

                    JsfUtil.addSuccessMessage("Operation réussie");
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
            if (etatfonctstructcom != null) {
                etatfonctstructcomFacadeLocal.remove(etatfonctstructcom);
                etatfonctstructcom = new Etatfonctstructcom();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Etatfonctstructcom getEtatfonctstructcom() {
        return etatfonctstructcom;
    }

    public void setEtatfonctstructcom(Etatfonctstructcom etatfonctstructcom) {
        this.etatfonctstructcom = etatfonctstructcom;
    }

    public List<Etatfonctstructcom> getEtatfonctstructcoms() {
        etatfonctstructcoms = etatfonctstructcomFacadeLocal.findAll();
        return etatfonctstructcoms;
    }

    public void setEtatfonctstructcoms(List<Etatfonctstructcom> etatfonctstructcoms) {
        this.etatfonctstructcoms = etatfonctstructcoms;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
