/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Naturedepense;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.NaturedepenseFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class NaturedepenseController {

    /**
     * Creates a new instance of NaturedepenseController
     */
    @EJB
    private NaturedepenseFacadeLocal naturedepenseFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Naturedepense naturedepense;
    private List<Naturedepense> naturedepenses = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public NaturedepenseController() {

    }

    @PostConstruct
    private void init() {
        naturedepense = new Naturedepense();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        naturedepense = new Naturedepense();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveNaturedepense() {
        try {

            if (mode == "Create") {
                naturedepense.setIdnaturedepense(naturedepenseFacadeLocal.nextId());
                List<Naturedepense> test = naturedepenseFacadeLocal.findByNom(naturedepense.getNomFr());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    naturedepenseFacadeLocal.create(naturedepense);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de nature depense -> " + naturedepense.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un naturedepense portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Naturedepense test = naturedepenseFacadeLocal.find(naturedepense.getIdnaturedepense());
                utilitaire.Utilitaires.saveOperation("Modification du nature depense -> " + test.getNomFr() + " par -> " + naturedepense.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                naturedepenseFacadeLocal.edit(naturedepense);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNaturedepense() {
        try {
            if (naturedepense != null) {

                naturedepenseFacadeLocal.remove(naturedepense);
                Utilitaires.saveOperation("Suppression du nature depense -> " + naturedepense.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun naturedepense selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Naturedepense getNaturedepense() {
        return naturedepense;
    }

    public void setNaturedepense(Naturedepense naturedepense) {
        this.naturedepense = naturedepense;
    }

    public List<Naturedepense> getNaturedepenses() {
        naturedepenses = naturedepenseFacadeLocal.findAll();
        return naturedepenses;
    }

    public void setNaturedepenses(List<Naturedepense> naturedepenses) {
        this.naturedepenses = naturedepenses;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
