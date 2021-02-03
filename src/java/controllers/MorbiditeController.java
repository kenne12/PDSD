/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Morbidite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.MorbiditeFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class MorbiditeController {

    /**
     * Creates a new instance of MorbiditeController
     */
    @EJB
    private MorbiditeFacadeLocal morbiditeFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Morbidite morbidite;
    private List<Morbidite> morbidites = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public MorbiditeController() {

    }

    @PostConstruct
    private void init() {
        morbidite = new Morbidite();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        morbidite = new Morbidite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveMorbidite() {
        try {

            if (mode == "Create") {
                morbidite.setIdmorbidite(morbiditeFacadeLocal.nextId());
                List<Morbidite> test = morbiditeFacadeLocal.findByNom(morbidite.getNomFr());

                if (test.isEmpty()) {
                    morbiditeFacadeLocal.create(morbidite);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de la morbidite -> " + morbidite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Une morbidite portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Morbidite test = morbiditeFacadeLocal.find(morbidite.getIdmorbidite());
                utilitaire.Utilitaires.saveOperation("Modification de la morbidite -> " + test.getNomFr() + " par -> " + morbidite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                morbiditeFacadeLocal.edit(morbidite);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMorbidite() {
        try {
            if (morbidite != null) {

                morbiditeFacadeLocal.remove(morbidite);
                Utilitaires.saveOperation("Suppression du morbidite -> " + morbidite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun morbidite selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Morbidite getMorbidite() {
        return morbidite;
    }

    public void setMorbidite(Morbidite morbidite) {
        this.morbidite = morbidite;
    }

    public List<Morbidite> getMorbidites() {
        morbidites = morbiditeFacadeLocal.findAll();
        return morbidites;
    }

    public void setMorbidites(List<Morbidite> morbidites) {
        this.morbidites = morbidites;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
