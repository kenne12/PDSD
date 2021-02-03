/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Categoriestructurecom;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CategoriestructurecomFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class CategorieStructureComController {

    @EJB
    private CategoriestructurecomFacadeLocal categoriestructurecomFacadeLocal;
    private Categoriestructurecom categoriestructurecom;
    private List<Categoriestructurecom> categoriestructurecoms = new ArrayList<>();

    private boolean detail = true;
    private String mode = "";

    /**
     * Creates a new instance of CategorieStructure
     */
    public CategorieStructureComController() {
    }

    @PostConstruct
    private void init() {

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
            categoriestructurecom = new Categoriestructurecom();
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
                categoriestructurecom.setIdcategoriestructurecom(categoriestructurecomFacadeLocal.nextId());
                categoriestructurecomFacadeLocal.create(categoriestructurecom);
                categoriestructurecom = new Categoriestructurecom();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (categoriestructurecom != null) {

                    categoriestructurecomFacadeLocal.edit(categoriestructurecom);

                    JsfUtil.addSuccessMessage("Sous axe stratégique mis à jour avec succès");
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
            if (categoriestructurecom != null) {
                categoriestructurecomFacadeLocal.remove(categoriestructurecom);
                categoriestructurecom = new Categoriestructurecom();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Categoriestructurecom getCategoriestructurecom() {
        return categoriestructurecom;
    }

    public void setCategoriestructurecom(Categoriestructurecom categoriestructurecom) {
        this.categoriestructurecom = categoriestructurecom;
    }

    public List<Categoriestructurecom> getCategoriestructurecoms() {
        categoriestructurecoms = categoriestructurecomFacadeLocal.findAll();
        return categoriestructurecoms;
    }

    public void setCategoriestructurecoms(List<Categoriestructurecom> categoriestructurecoms) {
        this.categoriestructurecoms = categoriestructurecoms;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
