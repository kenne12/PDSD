/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Sourcefinancement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.SourcefinancementFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class SourcefinancementController {

    /**
     * Creates a new instance of SourcefinancementController
     */
    @EJB
    private SourcefinancementFacadeLocal sourcefinancementFacadeLocal;

    private Sourcefinancement sourcefinancement;
    private Sourcefinancement selected;
    private List<Sourcefinancement> sourcefinancements = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public SourcefinancementController() {

    }

    @PostConstruct
    private void init() {
        sourcefinancement = new Sourcefinancement();
        selected = new Sourcefinancement();
        mouchard = new Mouchard();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
        sourcefinancement = new Sourcefinancement();
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Sourcefinancement> test = sourcefinancementFacadeLocal.findByNom(sourcefinancement.getNomFr());
                if (test.isEmpty()) {
                    sourcefinancement.setIdsourcefi(sourcefinancementFacadeLocal.nextId());
                    sourcefinancementFacadeLocal.create(sourcefinancement);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de la source de financement -> " + sourcefinancement.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    this.getSourcefinancements();
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une source de financement portant ce nom existe dejà");
                    this.getSourcefinancements();
                }
            } else {
                if (sourcefinancement != null) {
                    Sourcefinancement s = sourcefinancementFacadeLocal.find(sourcefinancement.getIdsourcefi());
                    sourcefinancementFacadeLocal.edit(sourcefinancement);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Modification de la source de financement ->  " + s.getNomFr()+ " -> " + sourcefinancement.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        try {
            if (sourcefinancement != null) {

                if (sourcefinancement.getActiviteList().isEmpty()) {
                    if (sourcefinancement.getRecetteList().isEmpty()) {
                        sourcefinancementFacadeLocal.remove(sourcefinancement);
                        mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                        mouchard.setAction("Suppression de la source de financement -> " + sourcefinancement.getNomFr());
                        mouchard.setIdutilisateur(SessionMBean.getUser());
                        mouchard.setDateaction(new Date());
                        mouchardFacadeLocal.create(mouchard);
                        JsfUtil.addSuccessMessage("Suppression effectuée avec succès");
                    }
                }

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une source de financement !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Sourcefinancement getSourcefinancement() {
        return sourcefinancement;
    }

    public void setSourcefinancement(Sourcefinancement sourcefinancement) {
        this.sourcefinancement = sourcefinancement;
    }

    public Sourcefinancement getSelected() {
        return selected;
    }

    public void setSelected(Sourcefinancement selected) {
        this.selected = selected;
    }

    public List<Sourcefinancement> getSourcefinancements() {
        sourcefinancements = sourcefinancementFacadeLocal.findAll();
        return sourcefinancements;
    }

    public void setSourcefinancements(List<Sourcefinancement> sourcefinancements) {
        this.sourcefinancements = sourcefinancements;
    }

}
