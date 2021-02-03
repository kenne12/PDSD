/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Axe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class AxeController {

    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private Axe axe1 = new Axe();
    private List<Axe> axes = new ArrayList<>();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public AxeController() {
    }

    @PostConstruct
    private void init() {
        axe = new Axe();
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
            axe1 = new Axe();
            axe = new Axe();
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
                axe1.setIdaxe(axeFacadeLocal.nextId());
                axeFacadeLocal.create(axe1);
                axe1 = new Axe();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (axe1 != null) {
                    axeFacadeLocal.edit(axe1);

                    JsfUtil.addSuccessMessage("Axe stratégique mis à jour avec succès");
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
            if (axe1.getSousaxeList().isEmpty()) {
                axeFacadeLocal.remove(axe1);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public Axe getAxe1() {
        return axe1;
    }

    public void setAxe1(Axe axe1) {
        this.axe1 = axe1;
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

}
