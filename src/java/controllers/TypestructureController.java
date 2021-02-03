/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypestructureFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TypestructureController {

    /**
     * Creates a new instance of TypestructureController
     */
    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypestructureController() {

    }

    @PostConstruct
    private void init() {
        typestructure = new Typestructure();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typestructure = new Typestructure();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypestructure() {
        try {

            if (mode == "Create") {
                typestructure.setIdtypestructure(typestructureFacadeLocal.nextId());
                List<Typestructure> test = typestructureFacadeLocal.findByNom(typestructure.getNomFr());

                if (test.isEmpty()) {
                    typestructureFacadeLocal.create(typestructure);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' typestructure -> " + typestructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typestructure portant de nom existe dejà");
                }
            } else {
                Typestructure test = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                utilitaire.Utilitaires.saveOperation("Modification du typestructure -> " + test.getNomFr() + " par -> " + typestructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typestructureFacadeLocal.edit(typestructure);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypestructure() {
        try {
            if (typestructure != null) {
              
                    typestructureFacadeLocal.remove(typestructure);
                    Utilitaires.saveOperation("Suppression du typestructure -> " + typestructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun typestructure selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public List<Typestructure> getTypestructures() {
        typestructures = typestructureFacadeLocal.findAll();
        return typestructures;
    }

    public void setTypestructures(List<Typestructure> typestructures) {
        this.typestructures = typestructures;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
