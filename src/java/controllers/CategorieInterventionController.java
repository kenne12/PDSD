/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Categorieintervention;
import entities.Sousaxe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CategorieinterventionFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class CategorieInterventionController {
    
    @EJB
    private CategorieinterventionFacadeLocal categorieinterventionFacadeLocal;
    private Categorieintervention categorieintervention;
    private List<Categorieintervention> categorieinterventions = new ArrayList<>();
    
    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();
    
    private boolean detail = true;
    
    private String mode = "";

    /**
     * Creates a new instance of CategorieInterventionController
     */
    public CategorieInterventionController() {
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
            categorieintervention = new Categorieintervention();
            sousaxe = new Sousaxe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        mode = "Edit";
        try {
            if (categorieintervention != null) {
                if (categorieintervention.getIdsousaxe() != null) {
                    sousaxe = categorieintervention.getIdsousaxe();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create() {
        try {
            
            if ("Create".equals(mode)) {
                categorieintervention.setIdcategorieintervention(categorieinterventionFacadeLocal.nextId());
                
                if (sousaxe.getIdsousaxe() != null) {
                    categorieintervention.setIdsousaxe(sousaxe);
                }
                
                categorieinterventionFacadeLocal.create(categorieintervention);
                categorieintervention = new Categorieintervention();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (categorieintervention != null) {
                    
                    if (sousaxe.getIdsousaxe() != null) {
                        categorieintervention.setIdsousaxe(sousaxeFacadeLocal.find(sousaxe.getIdsousaxe()));
                    }
                    
                    categorieinterventionFacadeLocal.edit(categorieintervention);
                    
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
            if (categorieintervention != null) {
                if (categorieintervention.getInterventionpndsList().isEmpty()) {
                    categorieinterventionFacadeLocal.remove(categorieintervention);
                    categorieintervention = new Categorieintervention();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Cette categorie est liée a plusieurs interventions");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Categorieintervention getCategorieintervention() {
        return categorieintervention;
    }
    
    public void setCategorieintervention(Categorieintervention categorieintervention) {
        this.categorieintervention = categorieintervention;
    }
    
    public List<Categorieintervention> getCategorieinterventions() {
        categorieinterventions = categorieinterventionFacadeLocal.findAllRange();
        return categorieinterventions;
    }
    
    public void setCategorieinterventions(List<Categorieintervention> categorieinterventions) {
        this.categorieinterventions = categorieinterventions;
    }
    
    public boolean isDetail() {
        return detail;
    }
    
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    public Sousaxe getSousaxe() {
        return sousaxe;
    }
    
    public void setSousaxe(Sousaxe sousaxe) {
        this.sousaxe = sousaxe;
    }
    
    public List<Sousaxe> getSousaxes() {
        sousaxes = sousaxeFacadeLocal.findAllRangeByCode();
        return sousaxes;
    }
    
    public void setSousaxes(List<Sousaxe> sousaxes) {
        this.sousaxes = sousaxes;
    }
    
}
