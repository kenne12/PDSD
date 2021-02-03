/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Listetableau;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.ListetableauFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ListetableauController {

    /**
     * Creates a new instance of ListetableauController
     */
    @EJB
    private ListetableauFacadeLocal listetableauFacadeLocal;

    private Listetableau listetableau;
    private Listetableau selected;
    private List<Listetableau> listetableaus = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public ListetableauController() {

    }

    @PostConstruct
    private void init() {
        listetableau = new Listetableau();
        selected = new Listetableau();
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
        listetableau = new Listetableau();
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Listetableau> test = listetableauFacadeLocal.findByNom(listetableau.getNomFr());
                if (test.isEmpty()) {
                    listetableau.setIdlisttableau(listetableauFacadeLocal.nextId());
                    listetableauFacadeLocal.create(listetableau);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de la liste -> " + listetableau.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    this.getListetableaus();
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une liste  portant ce nom existe dejà");
                    this.getListetableaus();
                }
            } else {
                if (listetableau != null) {
                    Listetableau s = listetableauFacadeLocal.find(listetableau.getIdlisttableau());
                    listetableauFacadeLocal.edit(listetableau);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Modification de la source de financement ->  " + s.getNomFr() + " -> " + listetableau.getNomFr());
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
            if (listetableau != null) {

                listetableauFacadeLocal.remove(listetableau);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Suppression de la liste -> " + listetableau.getNomFr());
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

    public Listetableau getListetableau() {
        return listetableau;
    }

    public void setListetableau(Listetableau listetableau) {
        this.listetableau = listetableau;
    }

    public Listetableau getSelected() {
        return selected;
    }

    public void setSelected(Listetableau selected) {
        this.selected = selected;
    }

    public List<Listetableau> getListetableaus() {
        listetableaus = listetableauFacadeLocal.findAll();
        return listetableaus;
    }

    public void setListetableaus(List<Listetableau> listetableaus) {
        this.listetableaus = listetableaus;
    }

}
