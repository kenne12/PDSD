/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Compteutilisateur;
import entities.District;

import entities.Menu;
import entities.Mouchard;
import entities.Partiehaute;
import entities.Privilege;
import entities.Utilisateur;
import entities.Utilisateurdistrict;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessions.CompteutilisateurFacadeLocal;

import sessions.MenuFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PartiehauteFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import sessions.UtilisateurdistrictFacadeLocal;
import utilitaire.Utilitaires;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private District district = new District();

    @EJB
    private PartiehauteFacadeLocal partiehauteFacadeLocal;

    private String cartedistrict = "MINSANTE.jpg";

    @EJB
    private CompteutilisateurFacadeLocal compteutilisateurFacadeLocal;
    private Compteutilisateur compteutilisateur = new Compteutilisateur();

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private Utilisateur utilisateur = new Utilisateur();
    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard = new Mouchard();

    @EJB
    private PrivilegeFacadeLocal privilegeFacadeLocal;
    Privilege privilege = new Privilege();
    public static List<String> privilegeUser = new ArrayList<>();
    public static List<String> privilegeTotal = new ArrayList<>();
    private static List<Privilege> privileges = new ArrayList<>();

    @EJB
    private MenuFacadeLocal menuFacadeLocal;
    private static MenuFacadeLocal menuFacadeLocal1;
    private Menu menu;
    public static Menu menu1 = new Menu();

    private String language = "fr";

    @EJB
    private UtilisateurdistrictFacadeLocal utilisateurdistrictFacadeLocal;
    private Utilisateurdistrict utilisateurdistrict = new Utilisateurdistrict();
    private List<Utilisateurdistrict> utilisateurdistricts = new ArrayList<>();

    private boolean viewSession = true;

    public LoginController() {

    }

    @PostConstruct
    private void init() {
        menu1 = new Menu();
    }

    public void validateUsernamePassword() {
        try {

            String password = "";

            password = org.apache.commons.codec.digest.DigestUtils.md5Hex(compteutilisateur.getPassword());

            Compteutilisateur usr = compteutilisateurFacadeLocal.login(compteutilisateur.getLogin(), password);
            if (usr != null) {

                System.err.println("user trouvée");

                if (usr.getEtat()) {
                    utilisateur = usr.getIdutilisateur();
                    HttpSession session = SessionMBean.getSession();

                    session.setAttribute("login", utilisateur.getNom());
                    session.setAttribute("user", utilisateur);
                    session.setAttribute("langue", language);

                    Utilitaires.saveOperation("connexion ", utilisateur, mouchardFacadeLocal);
                    utilisateurdistricts = utilisateurdistrictFacadeLocal.findByUtilisateur(utilisateur.getIdutilisateur());
                    setPrivilegeAll();
                    setPrivilegeUser();

                    FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces/index.xhtml");
                } else {
                    Utilitaires.saveOperation("tentative de connection avec un compte bloqué", usr.getIdutilisateur(), mouchardFacadeLocal);
                    JsfUtil.addErrorMessage("Votre compte est bloqué");
                }

            } else {
                System.err.println("echec d'authentification");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login et mot de passe incorrets", "Please enter correct username and Password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrivilegeAll() {
        List<Menu> menus = menuFacadeLocal.findAll();
        for (Menu m : menus) {
            privilegeTotal.add(m.getRessource());
        }
    }

    public void setPrivilegeUser() {
        if (SessionMBean.getUser() != null) {
            privileges = privilegeFacadeLocal.findByGroupeUser(SessionMBean.getUser().getIdgroupeutilisateur().getIdgroupeutilisateur(), true, true);
            if (privileges.isEmpty()) {
                privilegeUser = new ArrayList<>();
            } else {
                privilegeUser.clear();
                for (Privilege p : privileges) {
                    privilegeUser.add(p.getIdmenu().getRessource());
                }
            }
        } else {
            privilegeUser = new ArrayList<>();
        }
    }

    //logout event, invalidate session
    public void logout() {
        HttpSession session = SessionMBean.getSession();
        Utilisateur usr = SessionMBean.getUser();
        session.invalidate();

        String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        try {
            Utilitaires.saveOperation("Déconnexion", usr, mouchardFacadeLocal);
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void watcher() {
        try {
            if (SessionMBean.getUser() == null) {
                String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces/login.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findMenu(String ressource) {
        try {
            menu1 = menuFacadeLocal1.findByRessource(ressource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSession() {
        try {
            if (utilisateurdistrict.getId() != null) {
                utilisateurdistrict = utilisateurdistrictFacadeLocal.find(utilisateurdistrict.getId());
                HttpSession session = SessionMBean.getSession();
                session.setAttribute("district", utilisateurdistrict.getIddistrict());
                List<Partiehaute> partiehaute = partiehauteFacadeLocal.findByDistrict(utilisateurdistrict.getIddistrict().getIddistrict());
                if (partiehaute.isEmpty()) {
                    cartedistrict = "MINSANTE.jpg";
                } else {
                    if (partiehaute.get(0).getCarte() != null) {

                        File f = new File(Utilitaires.path + "/report/pdsd/images/" + partiehaute.get(0).getCarte());
                        if (f.exists()) {
                            cartedistrict = "" + partiehaute.get(0).getCarte();
                        } else {
                            cartedistrict = "MINSANTE.jpg";
                        }
                    } else {
                        cartedistrict = "MINSANTE.jpg";
                    }
                }
                viewSession = false;
            } else {
                JsfUtil.addErrorMessage("Aucun district selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String switchFr() {
        language = "fr";
        return null;
    }

    public String switchEn() {
        language = "en";
        return null;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Compteutilisateur getCompteutilisateur() {
        return compteutilisateur;
    }

    public void setCompteutilisateur(Compteutilisateur compteutilisateur) {
        this.compteutilisateur = compteutilisateur;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Utilisateurdistrict> getUtilisateurdistricts() {
        return utilisateurdistricts;
    }

    public void setUtilisateurdistricts(List<Utilisateurdistrict> utilisateurdistricts) {
        this.utilisateurdistricts = utilisateurdistricts;
    }

    public boolean isViewSession() {
        return viewSession;
    }

    public void setViewSession(boolean viewSession) {
        this.viewSession = viewSession;
    }

    public Utilisateurdistrict getUtilisateurdistrict() {
        return utilisateurdistrict;
    }

    public void setUtilisateurdistrict(Utilisateurdistrict utilisateurdistrict) {
        this.utilisateurdistrict = utilisateurdistrict;
    }

    public String getCartedistrict() {
        return cartedistrict;
    }

    public void setCartedistrict(String cartedistrict) {
        this.cartedistrict = cartedistrict;
    }

}
