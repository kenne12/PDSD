/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Axe;
import entities.Sousaxe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AxeFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class SousaxeController {

    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private Sousaxe selected = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();

    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public SousaxeController() {
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
            axe = new Axe();
            sousaxe = new Sousaxe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (sousaxe != null) {
                axe = sousaxe.getIdaxe();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {
                sousaxe.setIdsousaxe(sousaxeFacadeLocal.nextId());
                if (axe.getIdaxe() != null) {
                    axe = axeFacadeLocal.find(axe.getIdaxe());
                    sousaxe.setIdaxe(axe);
                }

                sousaxeFacadeLocal.create(sousaxe);
                sousaxe = new Sousaxe();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (sousaxe != null) {

                    if (axe.getIdaxe() != null) {
                        axe = axeFacadeLocal.find(axe.getIdaxe());
                        sousaxe.setIdaxe(axe);
                    }

                    sousaxeFacadeLocal.edit(sousaxe);

                    JsfUtil.addSuccessMessage("Opération réussie");
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
            if (sousaxe != null) {
                sousaxeFacadeLocal.remove(sousaxe);
                sousaxe = new Sousaxe();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sousaxe getSousaxe() {
        return sousaxe;
    }

    public void setSousaxe(Sousaxe sousaxe) {
        this.sousaxe = sousaxe;
    }

    public List<Sousaxe> getSousaxes() {
        sousaxes = sousaxeFacadeLocal.findAllRangeByCode();
        return sousaxes;
    }

    public void setSousaxes(List<Sousaxe> sousaxes) {
        this.sousaxes = sousaxes;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public List<Axe> getAxes() {
        axes = axeFacadeLocal.findAllRangeByCode();
        return axes;
    }

    public void setAxes(List<Axe> axes) {
        this.axes = axes;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Sousaxe getSelected() {
        return selected;
    }

    public void setSelected(Sousaxe selected) {
        this.selected = selected;
    }

}
