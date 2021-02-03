/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Groupefacteur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.GroupefacteurFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class GroupefacteurController {

    @EJB
    private GroupefacteurFacadeLocal groupefacteurFacadeLocal;
    private Groupefacteur groupefacteur = new Groupefacteur();
    private List<Groupefacteur> groupefacteurs = new ArrayList<>();

    private String mode = "";
    private boolean detail = true;

    /**
     * Creates a new instance of GroupefacteurController
     */
    public GroupefacteurController() {
    }

    @PostConstruct
    private void init() {
        groupefacteur = new Groupefacteur();
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
            groupefacteur = new Groupefacteur();
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
                groupefacteur.setIdgroupefacteur(groupefacteurFacadeLocal.nextId());
                groupefacteurFacadeLocal.create(groupefacteur);
                groupefacteur = new Groupefacteur();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (groupefacteur != null) {

                    groupefacteurFacadeLocal.edit(groupefacteur);

                    JsfUtil.addSuccessMessage("Groupe facteur mis à jour avec succès");
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
            if (groupefacteur != null) {
                groupefacteurFacadeLocal.remove(groupefacteur);
                groupefacteur = new Groupefacteur();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Groupefacteur getGroupefacteur() {
        return groupefacteur;
    }

    public void setGroupefacteur(Groupefacteur groupefacteur) {
        this.groupefacteur = groupefacteur;
    }

    public List<Groupefacteur> getGroupefacteurs() {
        groupefacteurs = groupefacteurFacadeLocal.findAll();
        return groupefacteurs;
    }

    public void setGroupefacteurs(List<Groupefacteur> groupefacteurs) {
        this.groupefacteurs = groupefacteurs;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
