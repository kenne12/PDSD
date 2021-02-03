/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mape;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.MapeFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class MapeController {

    /**
     * Creates a new instance of MapeController
     */
    @EJB
    private MapeFacadeLocal mapeFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Mape mape;
    private List<Mape> mapes = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public MapeController() {

    }

    @PostConstruct
    private void init() {
        mape = new Mape();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mape = new Mape();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveMape() {
        try {

            if (mode == "Create") {
                mape.setIdmape(mapeFacadeLocal.nextId());
                List<Mape> test = mapeFacadeLocal.findByNom(mape.getNomFr());

                if (test.isEmpty()) {

                    mapeFacadeLocal.create(mape);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' mape -> " + mape.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un mape portant de nom existe dejà");
                }
            } else {

                Mape test = mapeFacadeLocal.find(mape.getIdmape());
                utilitaire.Utilitaires.saveOperation("Modification du mape -> " + test.getNomFr() + " par -> " + mape.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                mapeFacadeLocal.edit(mape);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMape() {
        try {
            if (mape != null) {

                mapeFacadeLocal.remove(mape);
                Utilitaires.saveOperation("Suppression du mape -> " + mape.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun mape selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mape getMape() {
        return mape;
    }

    public void setMape(Mape mape) {
        this.mape = mape;
    }

    public List<Mape> getMapes() {
        mapes = mapeFacadeLocal.findAll();
        return mapes;
    }

    public void setMapes(List<Mape> mapes) {
        this.mapes = mapes;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
