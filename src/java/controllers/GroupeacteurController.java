/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Groupeacteur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.GroupeacteurFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class GroupeacteurController {

    @EJB
    private GroupeacteurFacadeLocal groupeacteurFacadeLocal;
    private Groupeacteur groupeacteur = new Groupeacteur();
    private List<Groupeacteur> groupeacteurs = new ArrayList<>();

    private String mode = "";
    private boolean detail = true;

    /**
     * Creates a new instance of GroupeacteurController
     */
    public GroupeacteurController() {
    }

    @PostConstruct
    private void init() {
        groupeacteur = new Groupeacteur();
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
            groupeacteur = new Groupeacteur();
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
                groupeacteur.setIdgroupeacteur(groupeacteurFacadeLocal.nextId());
                groupeacteurFacadeLocal.create(groupeacteur);
                groupeacteur = new Groupeacteur();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (groupeacteur != null) {

                    groupeacteurFacadeLocal.edit(groupeacteur);

                    JsfUtil.addSuccessMessage("Opération réussie");
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
            if (groupeacteur != null) {
                groupeacteurFacadeLocal.remove(groupeacteur);
                groupeacteur = new Groupeacteur();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Groupeacteur getGroupeacteur() {
        return groupeacteur;
    }

    public void setGroupeacteur(Groupeacteur groupeacteur) {
        this.groupeacteur = groupeacteur;
    }

    public List<Groupeacteur> getGroupeacteurs() {
        groupeacteurs = groupeacteurFacadeLocal.findAll();
        return groupeacteurs;
    }

    public void setGroupeacteurs(List<Groupeacteur> groupeacteurs) {
        this.groupeacteurs = groupeacteurs;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

}
