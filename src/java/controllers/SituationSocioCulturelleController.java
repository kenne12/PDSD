/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.SituationSocioCulturel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.SituationSocioCulturelFacadeLocal;


/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class SituationSocioCulturelleController {

    /**
     * Creates a new instance of SituationSocioCulturelController
     */
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    private SituationSocioCulturelFacadeLocal situationSocioCulturelFacadeLocal;
    private SituationSocioCulturel situationSocioCulturel;
    private List<SituationSocioCulturel> situationSocioCulturels = new ArrayList<>();
    private Boolean detail = true;

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    private String msg = "";
    private boolean bouton = true;
    private boolean showPrintForm = true;
    private boolean selectModel = true;
    private Date date;
    
    private boolean isRegistred = false;

    public SituationSocioCulturelleController() {

    }

    @PostConstruct
    private void init() {

        situationSocioCulturel = new SituationSocioCulturel();
        district = new District();
        try {
            situationSocioCulturels = situationSocioCulturelFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
            if (!situationSocioCulturels.isEmpty()) {
                isRegistred = true;
                situationSocioCulturel = situationSocioCulturels.get(0);
            } else {
                isRegistred = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSituationSocioCulturel() {
        try {
            if (isRegistred) {
                situationSocioCulturelFacadeLocal.edit(situationSocioCulturel);

            } else {
                situationSocioCulturel.setIdsituationsociocult(situationSocioCulturelFacadeLocal.nextId());
                situationSocioCulturel.setIddistrict(SessionMBean.getDistrict());
                situationSocioCulturelFacadeLocal.create(situationSocioCulturel);
                isRegistred = true;
                utilitaire.Utilitaires.saveOperation("Enregistrement de la situationSocioCulturel -> " + situationSocioCulturel.getEducation(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SituationSocioCulturel getSituationSocioCulturel() {
        return situationSocioCulturel;
    }

    public void setSituationSocioCulturel(SituationSocioCulturel situationSocioCulturel) {
        this.situationSocioCulturel = situationSocioCulturel;
    }

    public List<SituationSocioCulturel> getSituationSocioCulturels() {
        try {
            situationSocioCulturels = situationSocioCulturelFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return situationSocioCulturels;
    }

    public void setSituationSocioCulturels(List<SituationSocioCulturel> situationSocioCulturels) {
        this.situationSocioCulturels = situationSocioCulturels;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isShowPrintForm() {
        return showPrintForm;
    }

    public void setShowPrintForm(boolean showPrintForm) {
        this.showPrintForm = showPrintForm;
    }

    public boolean isSelectModel() {
        return selectModel;
    }

    public void setSelectModel(boolean selectModel) {
        this.selectModel = selectModel;
    }

    public boolean isBouton() {
        return bouton;
    }

    public void setBouton(boolean bouton) {
        this.bouton = bouton;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
