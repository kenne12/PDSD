/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typestructurecommunautaire;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypestructurecommunautaireFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TypestructurecommunautaireController {

    /**
     * Creates a new instance of TypestructurecommunautaireController
     */
    @EJB
    private TypestructurecommunautaireFacadeLocal typestructurecommunautaireFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typestructurecommunautaire typestructurecommunautaire;
    private List<Typestructurecommunautaire> typestructurecommunautaires = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypestructurecommunautaireController() {

    }

    @PostConstruct
    private void init() {
        typestructurecommunautaire = new Typestructurecommunautaire();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typestructurecommunautaire = new Typestructurecommunautaire();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypestructurecommunautaire() {
        try {

            if (mode == "Create") {
                typestructurecommunautaire.setIdtypestructurecommunautaire(typestructurecommunautaireFacadeLocal.nextId());
                List<Typestructurecommunautaire> test = typestructurecommunautaireFacadeLocal.findByNom(typestructurecommunautaire.getNomFr());

                if (test.isEmpty()) {
                    typestructurecommunautaireFacadeLocal.create(typestructurecommunautaire);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' typestructurecommunautaire -> " + typestructurecommunautaire.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typestructurecommunautaire portant de nom existe dejà");
                }
            } else {
                Typestructurecommunautaire test = typestructurecommunautaireFacadeLocal.find(typestructurecommunautaire.getIdtypestructurecommunautaire());
                utilitaire.Utilitaires.saveOperation("Modification du typestructurecommunautaire -> " + test.getNomFr()+ " par -> " + typestructurecommunautaire.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typestructurecommunautaireFacadeLocal.edit(typestructurecommunautaire);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypestructurecommunautaire() {
        try {
            if (typestructurecommunautaire != null) {
              
                    typestructurecommunautaireFacadeLocal.remove(typestructurecommunautaire);
                    Utilitaires.saveOperation("Suppression du typestructurecommunautaire -> " + typestructurecommunautaire.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun typestructurecommunautaire selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typestructurecommunautaire getTypestructurecommunautaire() {
        return typestructurecommunautaire;
    }

    public void setTypestructurecommunautaire(Typestructurecommunautaire typestructurecommunautaire) {
        this.typestructurecommunautaire = typestructurecommunautaire;
    }

    public List<Typestructurecommunautaire> getTypestructurecommunautaires() {
        typestructurecommunautaires = typestructurecommunautaireFacadeLocal.findAll();
        return typestructurecommunautaires;
    }

    public void setTypestructurecommunautaires(List<Typestructurecommunautaire> typestructurecommunautaires) {
        this.typestructurecommunautaires = typestructurecommunautaires;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
