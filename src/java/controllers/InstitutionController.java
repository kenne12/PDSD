/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Institution;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.InstitutionFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class InstitutionController {

    /**
     * Creates a new instance of InstitutionController
     */
    @EJB
    private InstitutionFacadeLocal institutionFacadeLocal;

    private Institution institution;
    private Institution selected;
    private List<Institution> institutions = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public InstitutionController() {

    }

    @PostConstruct
    private void init() {
        institution = new Institution();
        selected = new Institution();
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
        institution = new Institution();
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Institution> test = institutionFacadeLocal.findByNom(institution.getNomFr());
                if (test.isEmpty()) {
                    institution.setIdinstitution(institutionFacadeLocal.nextId());
                    institutionFacadeLocal.create(institution);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de la liste -> " + institution.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    this.getInstitutions();
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une liste  portant ce nom existe dejà");
                    this.getInstitutions();
                }
            } else {
                if (institution != null) {
                    Institution s = institutionFacadeLocal.find(institution.getIdinstitution());
                    institutionFacadeLocal.edit(institution);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Modification de la source de financement ->  " + s.getNomFr() + " -> " + institution.getNomFr());
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
            if (institution != null) {

                institutionFacadeLocal.remove(institution);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Suppression de la liste -> " + institution.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                JsfUtil.addSuccessMessage("Suppression effectuée avec succès");
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Institution getSelected() {
        return selected;
    }

    public void setSelected(Institution selected) {
        this.selected = selected;
    }

    public List<Institution> getInstitutions() {
        institutions = institutionFacadeLocal.findAll();
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }

}
