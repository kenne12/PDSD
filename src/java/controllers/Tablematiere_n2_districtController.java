/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.TablematiereN2;
import entities.Tablematieren1District;
import entities.Tablematieren2District;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.TablematiereN2FacadeLocal;
import sessions.Tablematieren1DistrictFacadeLocal;
import sessions.Tablematieren2DistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class Tablematiere_n2_districtController {

    @EJB
    private Tablematieren2DistrictFacadeLocal tablematieren2DistrictFacadeLocal;
    private Tablematieren2District tablematieren2District = new Tablematieren2District();
    private List<Tablematieren2District> tablematieren2Districts = new ArrayList<>();

    @EJB
    private Tablematieren1DistrictFacadeLocal tablematieren1DistrictFacadeLocal;
    private Tablematieren1District tablematieren1District = new Tablematieren1District();
    private List<Tablematieren1District> tablematieren1Districts = new ArrayList<>();

    @EJB
    private TablematiereN2FacadeLocal tablematiereN2FacadeLocal;
    private TablematiereN2 tablematiereN2 = new TablematiereN2();
    private List<TablematiereN2> tablematiereN2s = new ArrayList<>();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public Tablematiere_n2_districtController() {
    }

    @PostConstruct
    private void init() {
        tablematiereN2 = new TablematiereN2();
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
            tablematiereN2 = new TablematiereN2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void create() {
        try {
            if (!tablematieren2Districts.isEmpty()) {
                for (Tablematieren2District t : tablematieren2Districts) {
                    if (t.getIdtablematieren2District() != null) {
                        tablematieren2DistrictFacadeLocal.edit(t);
                    } else {
                        t.setIdtablematieren2District(tablematieren2DistrictFacadeLocal.nextId());
                        tablematieren2DistrictFacadeLocal.create(t);
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
            /*if (tablematiereN3 != null) {
             if (tablematiereN1.getTablematiereN2List().isEmpty()) {
             tablematiereN1FacadeLocal.remove(tablematiereN1);
             }
             JsfUtil.addSuccessMessage("Opération réussie");
             } else {
             JsfUtil.addErrorMessage("Aucune ligne selectionnée");
             }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            tablematieren2Districts.clear();
            if (SessionMBean.getDistrict() != null) {
                List<TablematiereN2> temp = getTablematiereN2s();
                if (!temp.isEmpty()) {
                    for (TablematiereN2 t : temp) {
                        List<Tablematieren2District> result = tablematieren2DistrictFacadeLocal.findByDistrictTableniveau2(t, SessionMBean.getDistrict());
                        if (!result.isEmpty()) {
                            tablematieren2Districts.add(result.get(0));
                        } else {
                            Tablematieren2District value = new Tablematieren2District();
                            value.setIddistrict(SessionMBean.getDistrict());
                            if (t.getDefaultnumpage() != null) {
                                value.setNumeropage(t.getDefaultnumpage());
                            }
                            value.setIdtablematiereN2(t);
                            tablematieren2Districts.add(value);

                        }
                    }
                    if (tablematieren2Districts.isEmpty()) {
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

    public TablematiereN2 getTablematiereN2() {
        return tablematiereN2;
    }

    public void setTablematiereN2(TablematiereN2 tablematiereN2) {
        this.tablematiereN2 = tablematiereN2;
    }

    public List<TablematiereN2> getTablematiereN2s() {
        try {
            tablematiereN2s = tablematiereN2FacadeLocal.findByAllRange(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tablematiereN2s;
    }

    public void setTablematiereN2s(List<TablematiereN2> tablematiereN2s) {
        this.tablematiereN2s = tablematiereN2s;
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

    public Tablematieren2District getTablematieren2District() {
        return tablematieren2District;
    }

    public void setTablematieren2District(Tablematieren2District tablematieren2District) {
        this.tablematieren2District = tablematieren2District;
    }

    public List<Tablematieren2District> getTablematieren2Districts() {
        return tablematieren2Districts;
    }

    public void setTablematieren2Districts(List<Tablematieren2District> tablematieren2Districts) {
        this.tablematieren2Districts = tablematieren2Districts;
    }

}
