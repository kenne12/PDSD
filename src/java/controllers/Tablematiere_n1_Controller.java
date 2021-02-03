/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.TablematiereN1;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.TablematiereN1FacadeLocal;
import sessions.TablematiereN2FacadeLocal;
import sessions.Tablematieren1DistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class Tablematiere_n1_Controller {
    
    @EJB
    private TablematiereN1FacadeLocal tablematiereN1FacadeLocal;
    private TablematiereN1 tablematiereN1 = new TablematiereN1();
    private List<TablematiereN1> tablematiereN1s = new ArrayList<>();
    
    @EJB
    private TablematiereN2FacadeLocal tablematiereN2FacadeLocal;
    
    @EJB
    private Tablematieren1DistrictFacadeLocal tablematieren1DistrictFacadeLocal;
    
    private boolean detail = true;
    
    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public Tablematiere_n1_Controller() {
    }
    
    @PostConstruct
    private void init() {
        tablematiereN1 = new TablematiereN1();
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
            tablematiereN1.setRegion(true);
            tablematiereN1.setDistrict(true);
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
                tablematiereN1.setIdtablematiereN1(tablematiereN1FacadeLocal.nextId());
                tablematiereN1FacadeLocal.create(tablematiereN1);
                tablematiereN1 = new TablematiereN1();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (tablematiereN1 != null) {
                    tablematiereN1FacadeLocal.edit(tablematiereN1);
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
            if (tablematiereN1 != null) {
                if (tablematiereN2FacadeLocal.findByTablematieren1(tablematiereN1).isEmpty()) {
                    if (tablematieren1DistrictFacadeLocal.findByTableniveau1(tablematiereN1).isEmpty()) {
                        tablematiereN1FacadeLocal.remove(tablematiereN1);
                        JsfUtil.addSuccessMessage("Opération réussie");
                    } else {
                        JsfUtil.addErrorMessage("Cet element est lié a plusieurs elements de numerotation de niveau 1");
                    }
                } else {
                    JsfUtil.addErrorMessage("Cet element est lié a plusieurs elements de la table de matiere de niveau 2");
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
    
}
