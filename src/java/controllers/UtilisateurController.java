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
import entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sessions.GroupeutilisateurFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.UtilisateurFacadeLocal;

@ManagedBean
@ViewScoped
public class UtilisateurController implements Serializable {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private Utilisateur utilisateur;
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private Utilisateur selectedUser;
    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @EJB
    private GroupeutilisateurFacadeLocal GroupeutilisateurFacadeLocal;
    private Groupeutilisateur groupeutilisateur;
    private List<Groupeutilisateur> groupeutilisateurs = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard = new Mouchard();

    private boolean detail = true;

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    private String msg;

    public UtilisateurController() {

    }

    @PostConstruct
    private void init() {
        mouchard = new Mouchard();
        utilisateur = new Utilisateur();
        selectedUser = new Utilisateur();
        groupeutilisateur = new Groupeutilisateur();
    }

    public void prepareCreate() {
        groupeutilisateurs = GroupeutilisateurFacadeLocal.findByEtat(true);
        groupeutilisateur = new Groupeutilisateur();
        utilisateur = new Utilisateur();
    }

    public void prepareEdit() {

    }

    public void prepareDelete() {

    }

    public void saveUtilisateur() {
        try {
            Utilisateur usr = utilisateurFacade.findByNomPrenom(utilisateur.getNom(), utilisateur.getPrenom());
            if (usr != null) {
                JsfUtil.addErrorMessage("Un utilisateur avec ces parametres existent deja");
            } else {
                utilisateur.setIdutilisateur(utilisateurFacade.nextId());
                utilisateur.setIdgroupeutilisateur(groupeutilisateur);
                utilisateurFacade.create(utilisateur);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement de l'utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom());
                mouchard.setDateaction(new Date());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchardFacadeLocal.create(mouchard);
                utilisateur = new Utilisateur();
                JsfUtil.addSuccessMessage("Utilsateur enregistré avec succès");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editUtilisateur() {
        try {
            if (selectedUser.getIdutilisateur() != null) {

                Utilisateur usr = utilisateurFacade.find(selectedUser.getIdutilisateur());
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Modification de l'utilisateur:  " + usr.getNom() + " " + usr.getPrenom() + " -> " + selectedUser.getNom() + " " + selectedUser.getPrenom());
                mouchard.setDateaction(new Date());
                mouchard.setIdutilisateur(SessionMBean.getUser());

                utilisateurFacade.edit(selectedUser);
                mouchardFacadeLocal.create(mouchard);
                selectedUser = new Utilisateur();
                JsfUtil.addSuccessMessage("Utilisateur mis à jour");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUtilisateur() {
        if (selectedUser != null) {
            if (selectedUser.getCompteutilisateurList().isEmpty()) {
                if (selectedUser.getMouchardList().isEmpty()) {
                    utilisateurFacade.remove(selectedUser);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("suppression de l'utilisateur " + selectedUser.getNom() + " " + selectedUser.getPrenom());
                    mouchard.setDateaction(new Date());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchardFacadeLocal.create(mouchard);
                    selectedUser = new Utilisateur();
                    JsfUtil.addSuccessMessage("operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Plusieurs opérations ont eté effectuées par cet utilisateur et il  ne peut etre supprimé");
                }
            } else {
                JsfUtil.addErrorMessage("cet utilisateur porte un compte utilsateur et ne peut etre supprimé");
            }
        } else {
            JsfUtil.addErrorMessage("veuillez selectionner un utilisateur");
        }
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Groupeutilisateur getGroupeutilisateur() {
        return groupeutilisateur;
    }

    public void setGroupeutilisateur(Groupeutilisateur groupeutilisateur) {
        this.groupeutilisateur = groupeutilisateur;
    }

    public List<Groupeutilisateur> getGroupeutilisateurs() {
        groupeutilisateurs = GroupeutilisateurFacadeLocal.findByEtat(true);
        return groupeutilisateurs;
    }

    public void setGroupeutilisateurs(List<Groupeutilisateur> groupeutilisateurs) {
        this.groupeutilisateurs = groupeutilisateurs;
    }

    public List<Utilisateur> getUtilisateurs() {
        utilisateurs = utilisateurFacade.findAll();
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Utilisateur getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Utilisateur selectedUser) {
        this.selectedUser = selectedUser;
    }

}
