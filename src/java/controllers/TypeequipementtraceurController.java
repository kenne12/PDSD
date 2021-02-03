/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typeequipementtraceur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypeequipementtraceurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TypeequipementtraceurController {

    /**
     * Creates a new instance of TypeequipementtraceurController
      */
    @EJB
    private TypeequipementtraceurFacadeLocal typeequipementtraceurFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typeequipementtraceur typeequipementtraceur;
    private List<Typeequipementtraceur> typeequipementtraceurs = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypeequipementtraceurController() {

    }

    @PostConstruct
    private void init() {
        typeequipementtraceur = new Typeequipementtraceur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typeequipementtraceur = new Typeequipementtraceur();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypeequipementtraceur() {
        try {

            if (mode == "Create") {
                typeequipementtraceur.setIdtypeequipementtraceur(typeequipementtraceurFacadeLocal.nextId());
                List<Typeequipementtraceur> test = typeequipementtraceurFacadeLocal.findByNom(typeequipementtraceur.getNomFr());

                if (test.isEmpty()) {
                    typeequipementtraceurFacadeLocal.create(typeequipementtraceur);
                    utilitaire.Utilitaires.saveOperation("Enregistrement du type d'equipement traceur -> " + typeequipementtraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un type d'equipement traceur portant de nom existe dejà");
                }
            } else {
                Typeequipementtraceur test = typeequipementtraceurFacadeLocal.find(typeequipementtraceur.getIdtypeequipementtraceur());
                utilitaire.Utilitaires.saveOperation("Modification du type d'equipement traceur -> " + test.getNomFr()+ " par -> " + typeequipementtraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typeequipementtraceurFacadeLocal.edit(typeequipementtraceur);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeequipementtraceur() {
        try {
            if (typeequipementtraceur != null) {
              
                    typeequipementtraceurFacadeLocal.remove(typeequipementtraceur);
                    Utilitaires.saveOperation("Suppression du typeequipementtraceur -> " + typeequipementtraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun typeequipementtraceur selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typeequipementtraceur getTypeequipementtraceur() {
        return typeequipementtraceur;
    }

    public void setTypeequipementtraceur(Typeequipementtraceur typeequipementtraceur) {
        this.typeequipementtraceur = typeequipementtraceur;
    }

    public List<Typeequipementtraceur> getTypeequipementtraceurs() {
        typeequipementtraceurs = typeequipementtraceurFacadeLocal.findAll();
        return typeequipementtraceurs;
    }

    public void setTypeequipementtraceurs(List<Typeequipementtraceur> typeequipementtraceurs) {
        this.typeequipementtraceurs = typeequipementtraceurs;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
 