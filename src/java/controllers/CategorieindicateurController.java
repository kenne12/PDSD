/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Categorieindicateur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CategorieindicateurFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class CategorieindicateurController {

    @EJB
    private CategorieindicateurFacadeLocal categorieindicateurFacadeLocal;
    private Categorieindicateur categorieindicateur = new Categorieindicateur();
    private Categorieindicateur categorieindicateur1 = new Categorieindicateur();
    private List<Categorieindicateur> categorieindicateurs = new ArrayList<>();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of CategorieindicateurController
     */
    public CategorieindicateurController() {
    }

    @PostConstruct
    private void init() {
        categorieindicateur = new Categorieindicateur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
        try {
            categorieindicateur1 = new Categorieindicateur();
            categorieindicateur = new Categorieindicateur();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void create() {
        try {
            if ("Create".equals(mode)) {
                if (categorieindicateurFacadeLocal.find(categorieindicateur.getNomFr()).isEmpty()) {
                    categorieindicateur.setIdcategorie(categorieindicateurFacadeLocal.nextId());
                    categorieindicateurFacadeLocal.create(categorieindicateur);
                    categorieindicateur = new Categorieindicateur();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Une categorie de ce nom existe déja");
                }
            } else {
                if (categorieindicateur != null) {
                    categorieindicateurFacadeLocal.edit(categorieindicateur);
                    JsfUtil.addSuccessMessage("Categorieindicateur stratégique mis à jour avec succès");
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
            if (categorieindicateur.getIndicateurList().isEmpty()) {
                categorieindicateurFacadeLocal.remove(categorieindicateur);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Categorieindicateur getCategorieindicateur() {
        return categorieindicateur;
    }

    public void setCategorieindicateur(Categorieindicateur categorieindicateur) {
        this.categorieindicateur = categorieindicateur;
    }

    public Categorieindicateur getCategorieindicateur1() {
        return categorieindicateur1;
    }

    public void setCategorieindicateur1(Categorieindicateur categorieindicateur1) {
        this.categorieindicateur1 = categorieindicateur1;
    }

    public List<Categorieindicateur> getCategorieindicateurs() {
        categorieindicateurs = categorieindicateurFacadeLocal.findAll();
        return categorieindicateurs;
    }

    public void setCategorieindicateurs(List<Categorieindicateur> categorieindicateurs) {
        this.categorieindicateurs = categorieindicateurs;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
