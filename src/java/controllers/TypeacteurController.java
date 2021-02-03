/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Typeacteur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.TypeacteurFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class TypeacteurController {

    @EJB
    private TypeacteurFacadeLocal typeacteurFacadeLocal;
    private Typeacteur typeacteur = new Typeacteur();
    private List<Typeacteur> typeacteurs = new ArrayList<>();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of TypeacteurController
     */
    public TypeacteurController() {
    }

    @PostConstruct
    private void init() {
        typeacteur = new Typeacteur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
        typeacteur = new Typeacteur();
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void create() {
        try {
            if ("Create".equals(mode)) {
                typeacteur.setIdtypeacteur(typeacteurFacadeLocal.nextId());
                typeacteurFacadeLocal.create(typeacteur);
                typeacteur = new Typeacteur();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (typeacteur != null) {
                    typeacteurFacadeLocal.edit(typeacteur);

                    JsfUtil.addSuccessMessage("Typeacteur stratégique mis à jour avec succès");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (typeacteur.getActeurList().isEmpty()) {
                typeacteurFacadeLocal.remove(typeacteur);
                JsfUtil.addSuccessMessage("Opération réussie");
            }else{
                JsfUtil.addErrorMessage("Cet type est lié à plusieurs acteurs");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typeacteur getTypeacteur() {
        return typeacteur;
    }

    public void setTypeacteur(Typeacteur typeacteur) {
        this.typeacteur = typeacteur;
    }

    public Typeacteur getTypeacteur1() {
        return typeacteur;
    }

    public void setTypeacteur1(Typeacteur typeacteur) {
        this.typeacteur = typeacteur;
    }

    public List<Typeacteur> getTypeacteurs() {
        typeacteurs = typeacteurFacadeLocal.findAll();
        return typeacteurs;
    }

    public void setTypeacteurs(List<Typeacteur> typeacteurs) {
        this.typeacteurs = typeacteurs;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
