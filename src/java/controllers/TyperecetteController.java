/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typerecette;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TyperecetteFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TyperecetteController {

    /**
     * Creates a new instance of TyperecetteController
     */
    @EJB
    private TyperecetteFacadeLocal typerecetteFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typerecette typerecette;
    private List<Typerecette> typerecettes = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TyperecetteController() {

    }

    @PostConstruct
    private void init() {
        typerecette = new Typerecette();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typerecette = new Typerecette();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTyperecette() {
        try {

            if (mode == "Create") {
                typerecette.setIdtyperecette(typerecetteFacadeLocal.nextId());
                List<Typerecette> test = typerecetteFacadeLocal.findByNom(typerecette.getNomFr());

                if (test.isEmpty()) {
                    typerecetteFacadeLocal.create(typerecette);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' typerecette -> " + typerecette.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typerecette portant de nom existe dejà");
                }
            } else {
                Typerecette test = typerecetteFacadeLocal.find(typerecette.getIdtyperecette());
                utilitaire.Utilitaires.saveOperation("Modification du typerecette -> " + test.getNomFr() + " par -> " + typerecette.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typerecetteFacadeLocal.edit(typerecette);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTyperecette() {
        try {
            if (typerecette != null) {

                typerecetteFacadeLocal.remove(typerecette);
                Utilitaires.saveOperation("Suppression du typerecette -> " + typerecette.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun typerecette selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typerecette getTyperecette() {
        return typerecette;
    }

    public void setTyperecette(Typerecette typerecette) {
        this.typerecette = typerecette;
    }

    public List<Typerecette> getTyperecettes() {
        typerecettes = typerecetteFacadeLocal.findAll();
        return typerecettes;
    }

    public void setTyperecettes(List<Typerecette> typerecettes) {
        this.typerecettes = typerecettes;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
