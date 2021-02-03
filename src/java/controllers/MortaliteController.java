/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mortalite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.MortaliteFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class MortaliteController {

    /**
     * Creates a new instance of MortaliteController
     */
    @EJB
    private MortaliteFacadeLocal mortaliteFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Mortalite mortalite;
    private List<Mortalite> mortalites = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public MortaliteController() {

    }

    @PostConstruct
    private void init() {
        mortalite = new Mortalite();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mortalite = new Mortalite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveMortalite() {
        try {

            if (mode == "Create") {
                mortalite.setIdmortalite(mortaliteFacadeLocal.nextId());
                List<Mortalite> test = mortaliteFacadeLocal.findByNom(mortalite.getNomFr());

                if (test.isEmpty()) {
                    mortaliteFacadeLocal.create(mortalite);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' mortalite -> " + mortalite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un mortalite portant de nom existe dejà");
                }
            } else {
                Mortalite test = mortaliteFacadeLocal.find(mortalite.getIdmortalite());
                utilitaire.Utilitaires.saveOperation("Modification du mortalite -> " + test.getNomFr() + " par -> " + mortalite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                mortaliteFacadeLocal.edit(mortalite);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMortalite() {
        try {
            if (mortalite != null) {

                mortaliteFacadeLocal.remove(mortalite);
                Utilitaires.saveOperation("Suppression du mortalite -> " + mortalite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun mortalite selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mortalite getMortalite() {
        return mortalite;
    }

    public void setMortalite(Mortalite mortalite) {
        this.mortalite = mortalite;
    }

    public List<Mortalite> getMortalites() {
        mortalites = mortaliteFacadeLocal.findAll();
        return mortalites;
    }

    public void setMortalites(List<Mortalite> mortalites) {
        this.mortalites = mortalites;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
