/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.TablematiereN1;
import entities.TablematiereN2;
import entities.TablematiereN3;
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
import sessions.Tablematieren3DistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class Tablematiere_n3_Controller implements Serializable {
    
    @EJB
    private TablematiereN3FacadeLocal tablematiereN3FacadeLocal;
    private TablematiereN3 tablematiereN3 = new TablematiereN3();
    private List<TablematiereN3> tablematiereN3s = new ArrayList<>();
    
    @EJB
    private TablematiereN2FacadeLocal tablematiereN2FacadeLocal;
    private TablematiereN2 tablematiereN2 = new TablematiereN2();
    private List<TablematiereN2> tablematiereN2s = new ArrayList<>();
    
    @EJB
    private TablematiereN1FacadeLocal tablematiereN1FacadeLocal;
    private TablematiereN1 tablematiereN1 = new TablematiereN1();
    private List<TablematiereN1> tablematiereN1s = new ArrayList<>();
    
    @EJB
    private Tablematieren3DistrictFacadeLocal tablematieren3DistrictFacadeLocal;
    
    private boolean detail = true;
    
    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public Tablematiere_n3_Controller() {
    }
    
    @PostConstruct
    private void init() {
        tablematiereN1 = new TablematiereN1();
        tablematiereN2 = new TablematiereN2();
        tablematiereN3 = new TablematiereN3();
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
            tablematiereN3 = new TablematiereN3();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        mode = "Edit";
        try {
            if (tablematiereN3 != null) {
                if (tablematiereN3.getIdtablematiereN2() != null) {
                    tablematiereN2 = tablematiereN3.getIdtablematiereN2();
                    if (tablematiereN2 != null) {
                        tablematiereN1 = tablematiereN2.getIdtablematiereN1();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleFilterTablen2() {
        try {
            if (tablematiereN1.getIdtablematiereN1() != null) {
                tablematiereN2s = tablematiereN2FacadeLocal.findByTablematieren1(tablematiereN1);
            } else {
                tablematiereN2s.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create() {
        try {
            if ("Create".equals(mode)) {
                tablematiereN3.setIdtablematiereN3(tablematiereN3FacadeLocal.nextId());
                tablematiereN3.setIdtablematiereN2(tablematiereN2);
                tablematiereN3FacadeLocal.create(tablematiereN3);
                tablematiereN2 = new TablematiereN2();
                tablematiereN1 = new TablematiereN1();
                tablematiereN3 = new TablematiereN3();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (tablematiereN3 != null) {
                    tablematiereN3.setIdtablematiereN2(tablematiereN2FacadeLocal.find(tablematiereN2.getIdtablematiereN2()));
                    tablematiereN3FacadeLocal.edit(tablematiereN3);
                    tablematiereN2 = new TablematiereN2();
                    tablematiereN1 = new TablematiereN1();
                    tablematiereN3 = new TablematiereN3();
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
            if (tablematiereN3 != null) {
                if (tablematieren3DistrictFacadeLocal.findByTableniveau3(tablematiereN3).isEmpty()) {
                    tablematiereN3FacadeLocal.remove(tablematiereN3);
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Cet element est lié a plusieurs d'autre element de numerotation de niveau 3");
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
    
    public TablematiereN3 getTablematiereN3() {
        return tablematiereN3;
    }
    
    public void setTablematiereN3(TablematiereN3 tablematiereN3) {
        this.tablematiereN3 = tablematiereN3;
    }
    
    public List<TablematiereN3> getTablematiereN3s() {
        try {
            tablematiereN3s = tablematiereN3FacadeLocal.findByAllRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tablematiereN3s;
    }
    
    public void setTablematiereN3s(List<TablematiereN3> tablematiereN3s) {
        this.tablematiereN3s = tablematiereN3s;
    }
    
}
