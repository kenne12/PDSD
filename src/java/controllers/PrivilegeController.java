/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Groupeutilisateur;
import entities.Menu;
import entities.Mouchard;
import entities.Privilege;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import sessions.GroupeutilisateurFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class PrivilegeController {

    /**
     * Creates a new instance of PrivilegeController
     */
    @EJB
    private PrivilegeFacadeLocal privilegeFacadeLocal;
    private Privilege privilege;
    private Privilege selectedPrivilege;
    List<Privilege> privileges = new ArrayList<>();
    private DualListModel<Privilege> privilegeDualList = new DualListModel<>();

    @EJB
    private MenuFacadeLocal menuFacadeLocal;
    private Menu menu;
    private DualListModel<Menu> menuDualList = new DualListModel<>();
    private List<Menu> menuSource = new ArrayList<Menu>();
    private List<Menu> menuTarget = new ArrayList<>();
    private List<Menu> menuTest = new ArrayList<>();

    @EJB
    private GroupeutilisateurFacadeLocal groupeutilisateurFacadeLocal;
    private Groupeutilisateur groupeutilisateur;
    private List<Groupeutilisateur> groupeutilisateurs = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    @PostConstruct
    private void init() {
        menu = new Menu();
        privilege = new Privilege();
        groupeutilisateur = new Groupeutilisateur();
        mouchard = new Mouchard();
        selectedPrivilege = new Privilege();
    }

    public PrivilegeController() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        groupeutilisateurs = groupeutilisateurFacadeLocal.findByEtat(true);
        menuTarget = menuFacadeLocal.findByEtat(true);
        menuDualList = new DualListModel<>();
        groupeutilisateur = new Groupeutilisateur();
    }

    public void prepareRetrait() {
        groupeutilisateurs = groupeutilisateurFacadeLocal.findByEtat(true);
        groupeutilisateur = new Groupeutilisateur();
        menuSource.clear();
        menuDualList.getSource().clear();
        menuDualList = new DualListModel<>();
    }

    public void prepareEdit() {
        groupeutilisateurs = groupeutilisateurFacadeLocal.findByEtat(true);
        menuTarget = menuFacadeLocal.findByEtat(true);
    }

    public void filterGroupeMenu() {
        menuTest.clear();
        menuSource.clear();
        menuDualList.getSource().clear();
        if (groupeutilisateur.getIdgroupeutilisateur() != null) {

            List<Privilege> privilegesInactifs = privilegeFacadeLocal.findByGroupeUser(groupeutilisateur.getIdgroupeutilisateur(), true, false);
            List<Privilege> privilegesActifs = privilegeFacadeLocal.findByGroupeUser(groupeutilisateur.getIdgroupeutilisateur(), true, true);

            if (privilegesActifs.isEmpty()) {
                menuSource = menuFacadeLocal.findByEtat(true);
            } else {
                System.err.println("le groupe a " + privilegesActifs.size() + " privileges actifs");
                menuSource = menuFacadeLocal.findByEtat(true);
                for (Privilege p : privilegesActifs) {
                    menuTest.add(p.getIdmenu());
                }

                for (Menu m : menuTest) {
                    if (menuSource.contains(m)) {
                        menuSource.remove(m);
                    }
                }

                if (!privilegesInactifs.isEmpty()) {
                    System.err.println("le groupe a " + privilegesInactifs.size() + " privileges actifs");
                    for (Privilege p : privilegesInactifs) {
                        if (!menuSource.contains(p.getIdmenu())) {
                            menuSource.add(p.getIdmenu());
                        }
                    }
                }
            }
            menuDualList.setSource(menuSource);
        } else {
            menuDualList.getSource().clear();
        }
    }

    public void filterPrivilegeRetrait() {
        privilegeDualList.getSource().clear();
        if (groupeutilisateur.getIdgroupeutilisateur() != null) {
            List<Privilege> tests = privilegeFacadeLocal.findByGroupeUser(groupeutilisateur.getIdgroupeutilisateur(), true, true);
            if (!tests.isEmpty()) {
                privilegeDualList.setSource(tests);
            }
        }
    }

    public void savePrivilege() {
        try {

            menuTarget = menuDualList.getTarget();
            groupeutilisateur = groupeutilisateurFacadeLocal.find(groupeutilisateur.getIdgroupeutilisateur());

            for (Menu m : menuTarget) {
                Privilege privi = privilegeFacadeLocal.findByGroupMenu(groupeutilisateur.getIdgroupeutilisateur(), m.getIdmenu());

                mouchard.setDateaction(new Date());
                mouchard.setIdutilisateur(SessionMBean.getUser());

                if (privi == null) {
                    privilege.setIdprivilege(privilegeFacadeLocal.nextId());
                    privilege.setIdgroupeutilisateur(groupeutilisateur);
                    privilege.setEtat(true);
                    privilege.setDateattribution(new Date());
                    privilege.setIdmenu(m);
                    privilegeFacadeLocal.create(privilege);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Attribution du privilège -> " + m.getLibelleFr() + " Au Groupe utilisateur -> " + groupeutilisateur.getNom());

                    mouchardFacadeLocal.create(mouchard);
                } else {
                    privi.setEtat(true);
                    privilegeFacadeLocal.edit(privi);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Réactivation du privilège -> " + m.getLibelleFr() + " Au Groupe utilisateur -> " + groupeutilisateur.getNom());
                    mouchardFacadeLocal.create(mouchard);
                }
            }
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retraitPrivilege() {
        try {

            for (Privilege p : privilegeDualList.getTarget()) {
                p.setEtat(false);
                privilegeFacadeLocal.edit(p);
                mouchard.setAction("Retrait du privilège -> " + p.getIdmenu().getLibelleFr() + " Au groupe utilisateur -> " + p.getIdgroupeutilisateur().getNom());
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
            }
            getPrivileges();
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editPrivilege() {
        try {

            if (selectedPrivilege != null) {
                privilegeFacadeLocal.remove(selectedPrivilege);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Modification du privilège -> " + selectedPrivilege.getIdmenu().getLibelleFr() + " Au groupe utilisateur -> " + selectedPrivilege.getIdgroupeutilisateur().getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                JsfUtil.addSuccessMessage("Opération réussie");

            } else {
                JsfUtil.addErrorMessage("veuillez selectionner un privilège");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePrivilege() {
        try {

            if (selectedPrivilege.getIdprivilege() != null) {
                privilegeFacadeLocal.remove(selectedPrivilege);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Suppression du privilège -> " + selectedPrivilege.getIdmenu().getLibelleFr()+ " Au groupe utilisateur -> " + selectedPrivilege.getIdgroupeutilisateur().getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Privilege getSelectedPrivilege() {
        return selectedPrivilege;
    }

    public void setSelectedPrivilege(Privilege selectedPrivilege) {
        this.selectedPrivilege = selectedPrivilege;
    }

    public List<Privilege> getPrivileges() {
        privileges = privilegeFacadeLocal.findAll();
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public DualListModel<Menu> getMenuDualList() {
        return menuDualList;
    }

    public void setMenuDualList(DualListModel<Menu> menuDualList) {
        this.menuDualList = menuDualList;
    }

    public List<Menu> getMenuSource() {
        return menuSource;
    }

    public void setMenuSource(List<Menu> menuSource) {
        this.menuSource = menuSource;
    }

    public List<Menu> getMenuTarget() {
        return menuTarget;
    }

    public void setMenuTarget(List<Menu> menuTarget) {
        this.menuTarget = menuTarget;
    }

    public Groupeutilisateur getGroupeutilisateur() {
        return groupeutilisateur;
    }

    public void setGroupeutilisateur(Groupeutilisateur groupeutilisateur) {
        this.groupeutilisateur = groupeutilisateur;
    }

    public List<Groupeutilisateur> getGroupeutilisateurs() {
        return groupeutilisateurs;
    }

    public void setGroupeutilisateurs(List<Groupeutilisateur> groupeutilisateurs) {
        this.groupeutilisateurs = groupeutilisateurs;
    }

    public DualListModel<Privilege> getPrivilegeDualList() {
        return privilegeDualList;
    }

    public void setPrivilegeDualList(DualListModel<Privilege> privilegeDualList) {
        this.privilegeDualList = privilegeDualList;
    }
}
