/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Groupeutilisateur;
import entities.Mouchard;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.GroupeutilisateurFacadeLocal;
import sessions.MouchardFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class GroupeUtilisateurController {

    /**
     * Creates a new instance of GroupeUtilisateurController
     */
    @EJB
    private GroupeutilisateurFacadeLocal groupeutilisateurFacadeLocal;
    private Groupeutilisateur groupeutilisateur;
    private List<Groupeutilisateur> groupeutilisateurs = new ArrayList<>();
    private Groupeutilisateur SelectedGroupeutilisateur;

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    public GroupeUtilisateurController() {

    }

    @PostConstruct
    private void init() {
        mouchard = new Mouchard();
        SelectedGroupeutilisateur = new Groupeutilisateur();
        groupeutilisateur = new Groupeutilisateur();
    }

    public void prepareCreate() {
        groupeutilisateur = new Groupeutilisateur();
        SelectedGroupeutilisateur = new Groupeutilisateur();
    }

    public void prepareEdit() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void saveGroupeUtilisateur() {
        try {
            Groupeutilisateur groupe = groupeutilisateurFacadeLocal.findByNom(groupeutilisateur.getNom());
            if (groupe == null) {
                groupeutilisateur.setIdgroupeutilisateur(groupeutilisateurFacadeLocal.nextVal());
                groupeutilisateur.setEtat(true);
                groupeutilisateur.setDatecreation(new Date());
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement du groupe utilisateur : " + groupeutilisateur.getNom());
                mouchard.setDateaction(new Date());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                groupeutilisateurFacadeLocal.create(groupeutilisateur);
                mouchardFacadeLocal.create(mouchard);
                groupeutilisateur = new Groupeutilisateur();
                JsfUtil.addSuccessMessage("Groupe utilisateur enregistré");
            } else {
                JsfUtil.addErrorMessage("Ce nom de groupe d'utilsateur existe deja");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editGoupeUtilisateur() {
        if (SelectedGroupeutilisateur.getIdgroupeutilisateur() != null) {
            Groupeutilisateur gr = groupeutilisateurFacadeLocal.find(SelectedGroupeutilisateur.getIdgroupeutilisateur());
            groupeutilisateurFacadeLocal.edit(SelectedGroupeutilisateur);
            mouchard.setIdoperation(mouchardFacadeLocal.nextId());
            mouchard.setAction("Modification du groupe utilisateur : " + gr.getNom() + "->" + " " + SelectedGroupeutilisateur.getNom());
            mouchard.setDateaction(new Date());
            mouchard.setIdutilisateur(SessionMBean.getUser());
            mouchardFacadeLocal.create(mouchard);
            SelectedGroupeutilisateur = new Groupeutilisateur();
            JsfUtil.addSuccessMessage("Groupe utilisateur mis à jour");
        } else {
            JsfUtil.addErrorMessage("veuillez selectionnez un groupe");
        }
    }

    public void deleteGroupeUtilsateur() {
        if (SelectedGroupeutilisateur.getIdgroupeutilisateur() != null) {
            if (SelectedGroupeutilisateur.getPrivilegeList().isEmpty()) {
                if (SelectedGroupeutilisateur.getUtilisateurList().isEmpty()) {
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Suppression du groupe utilisateur " + SelectedGroupeutilisateur.getNom());
                    mouchard.setDateaction(new Date());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    groupeutilisateurFacadeLocal.remove(SelectedGroupeutilisateur);
                    mouchardFacadeLocal.create(mouchard);
                    SelectedGroupeutilisateur = new Groupeutilisateur();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("des utilisateur portent ce groupe");
                }
            } else {
                JsfUtil.addErrorMessage("ce groupe utilisateur est utilisé dans les privelege");
            }

        } else {
            JsfUtil.addErrorMessage("veuillez selectionner un groupe");
        }

    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Groupeutilisateur getGroupeutilisateur() {
        return groupeutilisateur;
    }

    public void setGroupeutilisateur(Groupeutilisateur groupeutilisateur) {
        this.groupeutilisateur = groupeutilisateur;
    }

    public List<Groupeutilisateur> getGroupeutilisateurs() {
        groupeutilisateurs = groupeutilisateurFacadeLocal.findAll();
        return groupeutilisateurs;
    }

    public void setGroupeutilisateurs(List<Groupeutilisateur> groupeutilisateurs) {
        this.groupeutilisateurs = groupeutilisateurs;
    }

    public Groupeutilisateur getSelectedGroupeutilisateur() {
        return SelectedGroupeutilisateur;
    }

    public void setSelectedGroupeutilisateur(Groupeutilisateur SelectedGroupeutilisateur) {
        this.SelectedGroupeutilisateur = SelectedGroupeutilisateur;
    }

}
