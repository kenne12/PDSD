/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typeactivite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypeactiviteFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TypeactiviteController {

    /**
     * Creates a new instance of TypeactiviteController
     */
    @EJB
    private TypeactiviteFacadeLocal typeactiviteFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typeactivite typeactivite;
    private List<Typeactivite> typeactivites = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypeactiviteController() {

    }

    @PostConstruct
    private void init() {
        typeactivite = new Typeactivite();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typeactivite = new Typeactivite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypeactivite() {
        try {

            if (mode == "Create") {
                typeactivite.setIdtypeactivite(typeactiviteFacadeLocal.nextId());
                List<Typeactivite> test = typeactiviteFacadeLocal.findByNom(typeactivite.getNomFr());

                if (test.isEmpty()) {
                    typeactiviteFacadeLocal.create(typeactivite);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de type d'activite -> " + typeactivite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typeactivite portant de nom existe dejà");
                }
            } else {
                Typeactivite test = typeactiviteFacadeLocal.find(typeactivite.getIdtypeactivite());
                utilitaire.Utilitaires.saveOperation("Modification du type d'activite -> " + test.getNomFr() + " par -> " + typeactivite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typeactiviteFacadeLocal.edit(typeactivite);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeactivite() {
        try {
            if (typeactivite != null) {
              
                    typeactiviteFacadeLocal.remove(typeactivite);
                    Utilitaires.saveOperation("Suppression du typeactivite -> " + typeactivite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun typeactivite selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typeactivite getTypeactivite() {
        return typeactivite;
    }

    public void setTypeactivite(Typeactivite typeactivite) {
        this.typeactivite = typeactivite;
    }

    public List<Typeactivite> getTypeactivites() {
        typeactivites = typeactiviteFacadeLocal.findAll();
        return typeactivites;
    }

    public void setTypeactivites(List<Typeactivite> typeactivites) {
        this.typeactivites = typeactivites;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
