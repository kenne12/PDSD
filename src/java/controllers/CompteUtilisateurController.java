/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Compteutilisateur;

import entities.Mouchard;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CompteutilisateurFacadeLocal;
import sessions.MouchardFacadeLocal;

import sessions.UtilisateurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class CompteUtilisateurController {

    /**
     * Creates a new instance of CompteUtilisateurController
     */
    @EJB
    private CompteutilisateurFacadeLocal compteutilisateurFacadeLocal;
    private Compteutilisateur compteutilisateur;
    private List<Compteutilisateur> compteutilisateurs = new ArrayList<>();
    private Compteutilisateur selectedCompteutilisateur;
    private String repeatPassword;

    private List<Compteutilisateur> compteutilisateurInactifs = new ArrayList<>();
    private List<Compteutilisateur> compteutilisateurActifs = new ArrayList<>();

    @EJB
    private UtilisateurFacadeLocal utilisateurFacadeLocal;
    private Utilisateur utilisateur;
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private List<Utilisateur> users = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    public CompteUtilisateurController() {

    }

    @PostConstruct
    private void init() {
        utilisateur = new Utilisateur();
        selectedCompteutilisateur = new Compteutilisateur();
        compteutilisateur = new Compteutilisateur();
        mouchard = new Mouchard();
    }

    public void prepareCreate() {
        filterUtilisateurWithout();
        compteutilisateur = new Compteutilisateur();
        utilisateur = new Utilisateur();
    }

    public void prepareEdit() {
        utilisateurs = utilisateurFacadeLocal.findAll();
        utilisateur = selectedCompteutilisateur.getIdutilisateur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void filterUtilisateurWithout() {
        utilisateurs.clear();
        users = utilisateurFacadeLocal.findAll();
        if (!users.isEmpty()) {
            for (Utilisateur u : users) {
                if (u.getCompteutilisateurList().isEmpty()) {
                    utilisateurs.add(u);
                }
            }
        }
    }

    public void saveCompte() {
        try {

            String password = org.apache.commons.codec.digest.DigestUtils.md5Hex(compteutilisateur.getPassword());

            Compteutilisateur compte = compteutilisateurFacadeLocal.login(compteutilisateur.getLogin(), password);

            if (compte == null) {
                utilisateur = utilisateurFacadeLocal.find(utilisateur.getIdutilisateur());
                compteutilisateur.setIdutilisateur(utilisateur);
                compteutilisateur.setDatecreation(new Date());
                compteutilisateur.setEtat(true);
                compteutilisateur.setPassword(password);
                if (compteutilisateur.getPassword().equals(org.apache.commons.codec.digest.DigestUtils.md5Hex(repeatPassword))) {
                    compteutilisateur.setIdcompte(compteutilisateurFacadeLocal.nextId());
                    compteutilisateurFacadeLocal.create(compteutilisateur);
                    compteutilisateur = new Compteutilisateur();
                    Utilitaires.saveOperation("Creation du compte utilisateur " + compteutilisateur.getLogin() + " Pour l'utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Compte crée avec succès");
                } else {
                    JsfUtil.addErrorMessage("Les deux mot de passe sont differents !");
                }
            } else {
                JsfUtil.addErrorMessage("un utilisateur ayant ces parametres existent deja");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editCompte() {
        try {
            if (selectedCompteutilisateur.getIdcompte() != null) {

                selectedCompteutilisateur.setIdutilisateur(utilisateur);

                selectedCompteutilisateur.setPassword(org.apache.commons.codec.digest.DigestUtils.md5Hex(selectedCompteutilisateur.getPassword()) );
                compteutilisateurFacadeLocal.edit(selectedCompteutilisateur);
                selectedCompteutilisateur = new Compteutilisateur();
                Utilitaires.saveOperation("modification du compte utilisateur N° " + selectedCompteutilisateur.getIdcompte(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Compte utilisateur mis à jour ");
            } else {
                JsfUtil.addErrorMessage("veuillez selectionner un compte");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCompte() {
        try {
            if (selectedCompteutilisateur.getIdcompte() != null) {
                compteutilisateurFacadeLocal.remove(selectedCompteutilisateur);
                Utilitaires.saveOperation("suppression du compte utilisateur " + selectedCompteutilisateur.getLogin() + " " + selectedCompteutilisateur.getPassword(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("veuillez selectionner un compte");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(Compteutilisateur compteutilisateur, String action) {
        if (("desactiver").equals(action)) {
            //desactiver
            compteutilisateur.setEtat(false);
            compteutilisateurFacadeLocal.edit(compteutilisateur);
            Utilitaires.saveOperation("Désactivation du compte utilisateur" + compteutilisateur.getIdutilisateur().getNom()+ " " + compteutilisateur.getIdutilisateur().getPrenom(), SessionMBean.getUser(), mouchardFacadeLocal);
        } else {
            //activer
            compteutilisateur.setEtat(true);
            compteutilisateurFacadeLocal.edit(compteutilisateur);
            Utilitaires.saveOperation("Activation du compte utilisateur :" + compteutilisateur.getIdutilisateur().getNom() + " " + compteutilisateur.getIdutilisateur().getPrenom(), SessionMBean.getUser(), mouchardFacadeLocal);
        }
        System.err.println("apelé");
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Compteutilisateur getCompteutilisateur() {
        return compteutilisateur;
    }

    public void setCompteutilisateur(Compteutilisateur compteutilisateur) {
        this.compteutilisateur = compteutilisateur;
    }

    public List<Compteutilisateur> getCompteutilisateurs() {
        compteutilisateurs = compteutilisateurFacadeLocal.findAll();
        return compteutilisateurs;
    }

    public void setCompteutilisateurs(List<Compteutilisateur> compteutilisateurs) {
        this.compteutilisateurs = compteutilisateurs;
    }

    public Compteutilisateur getSelectedCompteutilisateur() {
        return selectedCompteutilisateur;
    }

    public void setSelectedCompteutilisateur(Compteutilisateur selectedCompteutilisateur) {
        this.selectedCompteutilisateur = selectedCompteutilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public List<Compteutilisateur> getCompteutilisateurInactifs() {
        try {
            compteutilisateurInactifs = compteutilisateurFacadeLocal.findAll(!true);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return compteutilisateurInactifs;
    }

    public List<Compteutilisateur> getCompteutilisateurActifs() {
        try {
            compteutilisateurActifs = compteutilisateurFacadeLocal.findAll(true);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return compteutilisateurActifs;
    }

}
