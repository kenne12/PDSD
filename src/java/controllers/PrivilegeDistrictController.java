/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Region;
import entities.Utilisateur;
import entities.Utilisateurdistrict;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.RegionFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import sessions.UtilisateurdistrictFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class PrivilegeDistrictController {

    @EJB
    private UtilisateurdistrictFacadeLocal utilisateurdistrictFacadeLocal;
    private Utilisateurdistrict privilige;
    private List<Utilisateurdistrict> privileges = new ArrayList<>();
    private DualListModel<Utilisateurdistrict> privilegeDualList = new DualListModel<>();

    @EJB
    private UtilisateurFacadeLocal utilisateurFacadeLocal;
    private Utilisateur utilisateur;
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private Region region;
    private List<Region> regions = new ArrayList<>();

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    private DualListModel<District> dualList = new DualListModel<>();

    private boolean detail = true;

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    /**
     * Creates a new instance of PrivilegeDistrictController
     */
    public PrivilegeDistrictController() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        try {
            utilisateur = new Utilisateur();
            dualList.getSource().clear();
            dualList.getTarget().clear();
            privilige = new Utilisateurdistrict();
            districts.clear();
            privilegeDualList.getSource().clear();
            privilegeDualList.getTarget().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (utilisateur != null) {
                if (!dualList.getTarget().isEmpty()) {
                    for (District d : dualList.getTarget()) {
                        Utilisateurdistrict utilisateurdistrictTemp = utilisateurdistrictFacadeLocal.findByUtilisateurDistrict(utilisateur.getIdutilisateur(), d.getIddistrict());
                        if (utilisateurdistrictTemp == null) {
                            privilige.setId(utilisateurdistrictFacadeLocal.nextId());
                            privilige.setIddistrict(d);
                            privilige.setIdutilisateur(utilisateur);
                            utilisateurdistrictFacadeLocal.create(privilige);
                            Utilitaires.saveOperation("Permission d'accès au district -> " + d.getNomFr()+ " A l'utilisateur -> " + utilisateur.getNom() + "" + utilisateur.getPrenom(), SessionMBean.getUser(), mouchardFacadeLocal);
                        }
                    }
                    JsfUtil.addSuccessMessage("Opération éffectuée avec succès !");
                } else {
                    JsfUtil.addErrorMessage("La liste des districts est vide !");
                }
            } else {
                JsfUtil.addErrorMessage("Aucun utilisateur selectionné !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (privilige != null) {
                utilisateurdistrictFacadeLocal.remove(privilige);
                JsfUtil.addSuccessMessage("Operation réussie !");
            } else {
                JsfUtil.addSuccessMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterUtilisateurDistrict() {
        try {
            dualList.getSource().clear();
            dualList.getTarget().clear();
            if (utilisateur != null) {

                List<Utilisateurdistrict> privilegeTemps = utilisateurdistrictFacadeLocal.findByUtilisateur(utilisateur.getIdutilisateur());

                if (privilegeTemps.isEmpty()) {
                    System.err.println("La liste est vide");
                    dualList.setSource(districtFacadeLocal.findAll());
                } else {
                    List<District> districtTempAll = districtFacadeLocal.findAll();
                    List<District> userDistricts = new ArrayList<>();

                    for (Utilisateurdistrict u : privilegeTemps) {
                        userDistricts.add(u.getIddistrict());
                    }

                    if (!districtTempAll.isEmpty()) {
                        for (District d : districtTempAll) {
                            if (!userDistricts.contains(d)) {
                                dualList.getSource().add(d);
                            }
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucun utilisateur selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterPrivilegeRetrait() {
        privilegeDualList.getSource().clear();
        privilegeDualList.getTarget().clear();
        if (utilisateur != null) {
            privilegeDualList.setSource(utilisateurdistrictFacadeLocal.findByUtilisateur(utilisateur.getIdutilisateur()));
        }
    }

    public void retraitPrivilege() {
        for (Utilisateurdistrict p : privilegeDualList.getTarget()) {
            if (!privilegeDualList.getTarget().isEmpty()) {
                utilisateurdistrictFacadeLocal.remove(p);
                Utilitaires.saveOperation("Retrait du privilège -> " + p.getIddistrict().getNomFr()+ " A l'utilisateur -> " + p.getIdutilisateur().getNom() + " " + p.getIdutilisateur().getPrenom(), SessionMBean.getUser(), mouchardFacadeLocal);
            }
        }
        getPrivileges();
        JsfUtil.addSuccessMessage("Opération réussie");
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Utilisateurdistrict getPrivilige() {
        return privilige;
    }

    public void setPrivilige(Utilisateurdistrict privilige) {
        this.privilige = privilige;
    }

    public List<Utilisateurdistrict> getPrivileges() {
        privileges = utilisateurdistrictFacadeLocal.findAll();
        return privileges;
    }

    public void setPrivileges(List<Utilisateurdistrict> privileges) {
        this.privileges = privileges;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        utilisateurs = utilisateurFacadeLocal.findAll();
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public DualListModel<District> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<District> dualList) {
        this.dualList = dualList;
    }

    public DualListModel<Utilisateurdistrict> getPrivilegeDualList() {
        return privilegeDualList;
    }

    public void setPrivilegeDualList(DualListModel<Utilisateurdistrict> privilegeDualList) {
        this.privilegeDualList = privilegeDualList;
    }

}
