/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typeinfrastructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypeinfrastructureFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TypeinfrastructureController {

    /**
     * Creates a new instance of TypeinfrastructureController
     */
    @EJB
    private TypeinfrastructureFacadeLocal typeinfrastructureFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typeinfrastructure typeinfrastructure;
    private List<Typeinfrastructure> typeinfrastructures = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypeinfrastructureController() {

    }

    @PostConstruct
    private void init() {
        typeinfrastructure = new Typeinfrastructure();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typeinfrastructure = new Typeinfrastructure();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypeinfrastructure() {
        try {

            if (mode == "Create") {
                typeinfrastructure.setIdtypeinfrastructure(typeinfrastructureFacadeLocal.nextId());
                List<Typeinfrastructure> test = typeinfrastructureFacadeLocal.findByNom(typeinfrastructure.getNomFr());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    typeinfrastructureFacadeLocal.create(typeinfrastructure);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' typeinfrastructure -> " + typeinfrastructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typeinfrastructure portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Typeinfrastructure test = typeinfrastructureFacadeLocal.find(typeinfrastructure.getIdtypeinfrastructure());
                utilitaire.Utilitaires.saveOperation("Modification du typeinfrastructure -> " + test.getNomFr()+ " par -> " + typeinfrastructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typeinfrastructureFacadeLocal.edit(typeinfrastructure);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeinfrastructure() {
        try {
            if (typeinfrastructure != null) {
              
                    typeinfrastructureFacadeLocal.remove(typeinfrastructure);
                    Utilitaires.saveOperation("Suppression du typeinfrastructure -> " + typeinfrastructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun typeinfrastructure selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typeinfrastructure getTypeinfrastructure() {
        return typeinfrastructure;
    }

    public void setTypeinfrastructure(Typeinfrastructure typeinfrastructure) {
        this.typeinfrastructure = typeinfrastructure;
    }

    public List<Typeinfrastructure> getTypeinfrastructures() {
        typeinfrastructures = typeinfrastructureFacadeLocal.findAll();
        return typeinfrastructures;
    }

    public void setTypeinfrastructures(List<Typeinfrastructure> typeinfrastructures) {
        this.typeinfrastructures = typeinfrastructures;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
