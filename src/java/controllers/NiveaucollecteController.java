/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Niveaucollecte;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.NiveaucollecteFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class NiveaucollecteController {
    
    @EJB
    private NiveaucollecteFacadeLocal niveaucollecteFacadeLocal;
    private Niveaucollecte niveaucollecte = new Niveaucollecte();
    private List<Niveaucollecte> niveaucollectes = new ArrayList<>();
    
    private boolean detail = true;
    
    private String mode = "";

    /**
     * Creates a new instance of NiveaucollecteController
     */
    public NiveaucollecteController() {
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
        niveaucollecte = new Niveaucollecte();
    }
    
    public void prepareEdit() {
        mode = "Edit";
    }
    
    public void create() {
        try {
            if ("Create".equals(mode)) {
                niveaucollecte.setIdniveaucollecte(niveaucollecteFacadeLocal.nextId());
                niveaucollecteFacadeLocal.create(niveaucollecte);
                niveaucollecte = new Niveaucollecte();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (niveaucollecte != null) {
                    
                    niveaucollecteFacadeLocal.edit(niveaucollecte);
                    
                    JsfUtil.addSuccessMessage("Niveau de collecte mis à jour avec succès");
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
            if (niveaucollecte != null) {
                if (niveaucollecte.getIndicateurList().isEmpty()) {
                    niveaucollecteFacadeLocal.remove(niveaucollecte);
                    niveaucollecte = new Niveaucollecte();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Ce niveau de collecte est lié a plusieurs indicateurs");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Niveaucollecte getNiveaucollecte() {
        return niveaucollecte;
    }
    
    public void setNiveaucollecte(Niveaucollecte niveaucollecte) {
        this.niveaucollecte = niveaucollecte;
    }
    
    public List<Niveaucollecte> getNiveaucollectes() {
        niveaucollectes = niveaucollecteFacadeLocal.findAll();
        return niveaucollectes;
    }
    
    public void setNiveaucollectes(List<Niveaucollecte> niveaucollectes) {
        this.niveaucollectes = niveaucollectes;
    }
    
    public boolean isDetail() {
        return detail;
    }
    
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
}
