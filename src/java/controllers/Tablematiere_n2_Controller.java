/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.TablematiereN1;
import entities.TablematiereN2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.TablematiereN1FacadeLocal;
import sessions.TablematiereN2FacadeLocal;
import sessions.TablematiereN3FacadeLocal;
import sessions.Tablematieren2DistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class Tablematiere_n2_Controller implements Serializable {

    @EJB
    private TablematiereN2FacadeLocal tablematiereN2FacadeLocal;
    private TablematiereN2 tablematiereN2 = new TablematiereN2();
    private List<TablematiereN2> tablematiereN2s = new ArrayList<>();

    @EJB
    private TablematiereN1FacadeLocal tablematiereN1FacadeLocal;
    private TablematiereN1 tablematiereN1 = new TablematiereN1();
    private List<TablematiereN1> tablematiereN1s = new ArrayList<>();

    @EJB
    private TablematiereN3FacadeLocal tablematiereN3FacadeLocal;

    @EJB
    private Tablematieren2DistrictFacadeLocal tablematieren2DistrictFacadeLocal;

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public Tablematiere_n2_Controller() {
    }

    @PostConstruct
    private void init() {
        tablematiereN1 = new TablematiereN1();
        tablematiereN2 = new TablematiereN2();
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
            tablematiereN1 = new TablematiereN1();
            tablematiereN2 = new TablematiereN2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (tablematiereN2 != null) {
                if (tablematiereN2.getIdtablematiereN1() != null) {
                    tablematiereN1 = tablematiereN2.getIdtablematiereN1();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if ("Create".equals(mode)) {
                tablematiereN2.setIdtablematiereN2(tablematiereN2FacadeLocal.nextId());
                tablematiereN2.setIdtablematiereN1(tablematiereN1);
                tablematiereN2FacadeLocal.create(tablematiereN2);
                tablematiereN2 = new TablematiereN2();
                tablematiereN1 = new TablematiereN1();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (tablematiereN2 != null) {
                    tablematiereN2.setIdtablematiereN1(tablematiereN1FacadeLocal.find(tablematiereN1.getIdtablematiereN1()));
                    tablematiereN2FacadeLocal.edit(tablematiereN2);
                    tablematiereN2 = new TablematiereN2();
                    tablematiereN1 = new TablematiereN1();
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
            if (tablematiereN2 != null) {
                if (tablematiereN3FacadeLocal.findByTablematiereN3(tablematiereN2).isEmpty()) {
                    if (tablematieren2DistrictFacadeLocal.findByTableniveau2(tablematiereN2).isEmpty()) {
                        tablematiereN2FacadeLocal.remove(tablematiereN2);
                        JsfUtil.addSuccessMessage("Opération réussie");
                    } else {
                        JsfUtil.addErrorMessage("Cet element est lié à d'autre elements de numerotation de niveau 2");
                    }
                } else {
                    JsfUtil.addErrorMessage("Cet element est lié à d'autre elements de la table de matiere de niveau 3");
                }     
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
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

    public TablematiereN1 getTablematiereN1() {
        return tablematiereN1;
    }

    public void setTablematiereN1(TablematiereN1 tablematiereN1) {
        this.tablematiereN1 = tablematiereN1;
    }

    public List<TablematiereN1> getTablematiereN1s() {
        try {
            tablematiereN1s = tablematiereN1FacadeLocal.findAllRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tablematiereN1s;
    }

    public void setTablematiereN1s(List<TablematiereN1> tablematiereN1s) {
        this.tablematiereN1s = tablematiereN1s;
    }

    public TablematiereN2 getTablematiereN2() {
        return tablematiereN2;
    }

    public void setTablematiereN2(TablematiereN2 tablematiereN2) {
        this.tablematiereN2 = tablematiereN2;
    }

    public List<TablematiereN2> getTablematiereN2s() {
        try {
            tablematiereN2s = tablematiereN2FacadeLocal.findByAllRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tablematiereN2s;
    }

    public void setTablematiereN2s(List<TablematiereN2> tablematiereN2s) {
        this.tablematiereN2s = tablematiereN2s;
    }

}
