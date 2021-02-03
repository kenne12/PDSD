/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.TablematiereN1;
import entities.Tablematieren1District;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.TablematiereN1FacadeLocal;
import sessions.Tablematieren1DistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class Tablematiere_n1_districtController {
    
    @EJB
    private Tablematieren1DistrictFacadeLocal tablematieren1DistrictFacadeLocal;
    private Tablematieren1District tablematieren1District = new Tablematieren1District();
    private List<Tablematieren1District> tablematieren1Districts = new ArrayList<>();
    
    @EJB
    private TablematiereN1FacadeLocal tablematiereN1FacadeLocal;
    private TablematiereN1 tablematiereN1 = new TablematiereN1();
    private List<TablematiereN1> tablematiereN1s = new ArrayList<>();
    
    private boolean detail = true;
    
    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public Tablematiere_n1_districtController() {
    }
    
    @PostConstruct
    private void init() {
        tablematiereN1 = new TablematiereN1();
        this.loadData();
    }
    
    public void prepare() {
        
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        mode = "Edit";
    }
    
    public void create() {
        try {
            if (!tablematieren1Districts.isEmpty()) {
                for (Tablematieren1District t : tablematieren1Districts) {
                    if (t.getIdtablematieren1District() != null) {
                        tablematieren1DistrictFacadeLocal.edit(t);
                    } else {
                        t.setIdtablematieren1District(tablematieren1DistrictFacadeLocal.nextId());
                        tablematieren1DistrictFacadeLocal.create(t);
                    }
                }
                this.loadData();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        try {
            if (tablematiereN1 != null) {
                if (tablematiereN1.getTablematiereN2List().isEmpty()) {
                    tablematiereN1FacadeLocal.remove(tablematiereN1);
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadData() {
        try {
            tablematieren1Districts.clear();
            if (SessionMBean.getDistrict() != null) {
                List<TablematiereN1> temp = getTablematiereN1s();
                if (!this.getTablematiereN1s().isEmpty()) {
                    for (TablematiereN1 t : temp) {
                        List<Tablematieren1District> result = tablematieren1DistrictFacadeLocal.findByDistrictTableniveau1(t, SessionMBean.getDistrict());
                        if (!result.isEmpty()) {
                            tablematieren1Districts.add(result.get(0));
                        } else {
                            Tablematieren1District value = new Tablematieren1District();
                            value.setIddistrict(SessionMBean.getDistrict());
                            if (t.getDefaultnumpage() != null) {
                                value.setNumeropage(t.getDefaultnumpage());
                            }
                            value.setIdtablematiereN1(t);
                            tablematieren1Districts.add(value);       
                        }
                    }
                    if (tablematieren1Districts.isEmpty()) {
                        detail = true;
                    } else {
                        detail = false;
                    }
                }
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
            tablematiereN1s = tablematiereN1FacadeLocal.findAllRange(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tablematiereN1s;
    }
    
    public void setTablematiereN1s(List<TablematiereN1> tablematiereN1s) {
        this.tablematiereN1s = tablematiereN1s;
    }
    
    public Tablematieren1District getTablematieren1District() {
        return tablematieren1District;
    }
    
    public void setTablematieren1District(Tablematieren1District tablematieren1District) {
        this.tablematieren1District = tablematieren1District;
    }
    
    public List<Tablematieren1District> getTablematieren1Districts() {
        return tablematieren1Districts;
    }
    
    public void setTablematieren1Districts(List<Tablematieren1District> tablematieren1Districts) {
        this.tablematieren1Districts = tablematieren1Districts;
    }
    
}
