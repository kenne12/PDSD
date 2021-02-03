/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typefacteur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypefacteurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TypefacteurController {

    /**
     * Creates a new instance of TypefacteurController
     */
    @EJB
    private TypefacteurFacadeLocal typefacteurFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typefacteur typefacteur;
    private List<Typefacteur> typefacteurs = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypefacteurController() {

    }

    @PostConstruct
    private void init() {
        typefacteur = new Typefacteur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typefacteur = new Typefacteur();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypefacteur() {
        try {

            if (mode == "Create") {
                typefacteur.setIdtypefacteur(typefacteurFacadeLocal.nextId());
                List<Typefacteur> test = typefacteurFacadeLocal.findByNom(typefacteur.getNomFr());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    typefacteurFacadeLocal.create(typefacteur);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' typefacteur -> " + typefacteur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typefacteur portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Typefacteur test = typefacteurFacadeLocal.find(typefacteur.getIdtypefacteur());
                utilitaire.Utilitaires.saveOperation("Modification du typefacteur -> " + test.getNomFr() + " par -> " + typefacteur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typefacteurFacadeLocal.edit(typefacteur);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypefacteur() {
        try {
            if (typefacteur != null) {

                typefacteurFacadeLocal.remove(typefacteur);
                Utilitaires.saveOperation("Suppression du typefacteur -> " + typefacteur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun typefacteur selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typefacteur getTypefacteur() {
        return typefacteur;
    }

    public void setTypefacteur(Typefacteur typefacteur) {
        this.typefacteur = typefacteur;
    }

    public List<Typefacteur> getTypefacteurs() {
        typefacteurs = typefacteurFacadeLocal.findAll();
        return typefacteurs;
    }

    public void setTypefacteurs(List<Typefacteur> typefacteurs) {
        this.typefacteurs = typefacteurs;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
