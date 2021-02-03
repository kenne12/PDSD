/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Unite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.UniteFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class UniteController {

    /**
     * Creates a new instance of UniteController
     */
    @EJB
    private UniteFacadeLocal uniteFacadeLocal;
    private Unite unite;
    private List<Unite> unites = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public UniteController() {
    }

    @PostConstruct
    private void init() {
        unite = new Unite();
        mouchard = new Mouchard();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        unite = new Unite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Unite> test = uniteFacadeLocal.findByNom(unite.getNom());
                if (test.isEmpty()) {
                    unite.setIdunite(uniteFacadeLocal.nextId());
                    uniteFacadeLocal.create(unite);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de l'unité -> " + unite.getNom());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une unité portant ce nom existe dejà");
                }
            } else {
                if (unite != null) {
                    uniteFacadeLocal.edit(unite);
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {

        try {
            if (unite != null) {
                Unite u = uniteFacadeLocal.find(unite.getIdunite());
                uniteFacadeLocal.edit(unite);
                mouchard.setAction("Modification de l'unité ->  " + u.getNom() + " -> " + unite.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                JsfUtil.addSuccessMessage("L'unité a été mise à jour");
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une unité");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getUnites();
        }
    }

    public void delete() {

        try {
            if (unite != null) {
                if (unite.getIndicateurList().isEmpty()) {

                    uniteFacadeLocal.remove(unite);
                    mouchard.setAction("Suppression de l'unité -> " + unite.getNom());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

                } else {
                    JsfUtil.addErrorMessage("Cette unité est portée par des indicateurs et ne peut etre supprimé");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune unité selectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getUnites();
        }
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public List<Unite> getUnites() {
        unites = uniteFacadeLocal.findAll();
        return unites;
    }

    public void setUnites(List<Unite> unites) {
        this.unites = unites;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

}
