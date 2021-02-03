/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Thematique;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ThematiqueFacadeLocal;

/**
 *
 * @author Simo
 */
@ManagedBean
@ViewScoped
public class ThematiqueController {

    @EJB
    private ThematiqueFacadeLocal thematiquefacadelocal;
    private Thematique thematique = new Thematique();
    private Thematique selectedthematique = new Thematique();
    private List<Thematique> ListThematique = new ArrayList<>();

    /**
     * Creates a new instance of ThematiqueController
     */
    private boolean detail = true;

    public ThematiqueController() {
    }

    @PostConstruct
    private void init() {
        thematique = new Thematique();
        selectedthematique = new Thematique();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        selectedthematique = new Thematique();
        thematique = new Thematique();
    }

    public void prepareEdit() {
    }

    public void create() {
        try {
            ListThematique = thematiquefacadelocal.findbynom(thematique.getNomFr());
            if (ListThematique.isEmpty()) {
                thematique.setIdthematique(thematiquefacadelocal.nextId());
                thematique.setEtat(true);
                thematiquefacadelocal.create(thematique);
                thematique = new Thematique();
                JsfUtil.addSuccessMessage("La Thématique a été crée!");
            } else {
                JsfUtil.addErrorMessage("La Thématique existe déja....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editThematique() {
        try {
            if (selectedthematique.getIdthematique() != null) {
                thematiquefacadelocal.edit(selectedthematique);
                JsfUtil.addSuccessMessage("Opération Réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune Ligne Sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        try {
            if (selectedthematique.getIdthematique() != null) {
                if (selectedthematique.getThematiqueActiviteList().isEmpty()) {
                    thematiquefacadelocal.remove(selectedthematique);
                    JsfUtil.addSuccessMessage("La Thématique a été supprimée! ");
                } else {
                    JsfUtil.addErrorMessage("Cette Thematique est liée a plusieurs activités!");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune Ligne Selectionnée");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ThematiqueFacadeLocal getThematiquefacadelocal() {
        return thematiquefacadelocal;
    }

    public void setThematiquefacadelocal(ThematiqueFacadeLocal thematiquefacadelocal) {
        this.thematiquefacadelocal = thematiquefacadelocal;
    }

    public Thematique getThematique() {
        return thematique;
    }

    public void setThematique(Thematique thematique) {
        this.thematique = thematique;
    }

    public List<Thematique> getListThematique() {
        ListThematique = thematiquefacadelocal.findAll();
        return ListThematique;
    }

    public void setListThematique(List<Thematique> ListThematique) {
        this.ListThematique = ListThematique;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Thematique getSelectedthematique() {
        return selectedthematique;
    }

    public void setSelectedthematique(Thematique selectedthematique) {
        this.selectedthematique = selectedthematique;
    }

}
