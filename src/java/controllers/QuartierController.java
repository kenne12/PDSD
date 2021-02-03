/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Arrondissement;
import entities.Quartier;
import entities.Departement;
import entities.Mouchard;

import entities.Region;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ArrondissementFacadeLocal;
import sessions.QuartierFacadeLocal;
import sessions.DepartementFacadeLocal;
import sessions.MouchardFacadeLocal;

import sessions.RegionFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class QuartierController {

    /**
     * Creates a new instance of QuartierController
     */
    @EJB
    private QuartierFacadeLocal quartierFacadeLocal;
    private Quartier quartier;
    private Quartier selected;
    private List<Quartier> quartiers = new ArrayList<>();

    @EJB
    private ArrondissementFacadeLocal arrondissementFacadeLocal;
    private Arrondissement arrondissement;
    private List<Arrondissement> arrondissements = new ArrayList<>();

    @EJB
    private DepartementFacadeLocal departementFacadeLocal;
    private Departement departement;
    private List<Departement> departements = new ArrayList<>();

    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private Region region;
    private List<Region> regions = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    public QuartierController() {

    }

    @PostConstruct
    private void init() {
        quartier = new Quartier();
        selected = new Quartier();
        mouchard = new Mouchard();
        departement = new Departement();
        arrondissement = new Arrondissement();
        departements = departementFacadeLocal.findAll();
        regions = regionFacadeLocal.findAll();
        arrondissements = arrondissementFacadeLocal.findAll();

        region = new Region();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        try {
            quartier = new Quartier();
            departement = new Departement();
            region = new Region();
            arrondissement = new Arrondissement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            if (selected != null) {
                region = departement.getIdregion();
                departements = region.getDepartementList();
                arrondissements = departement.getArrondissementList();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {

            quartier.setIdquartier(quartierFacadeLocal.nextId());
            quartierFacadeLocal.create(quartier);
            Utilitaires.saveOperation("Enregistrement de l'quartier -> " + quartier.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
            this.getQuartiers();
            JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (selected.getIdquartier() != null) {
                Quartier r = quartierFacadeLocal.find(selected.getIdquartier());
                quartierFacadeLocal.edit(selected);
                Utilitaires.saveOperation("Modification du quartier ->  " + r.getNom() + " -> " + selected.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("La region a été mise à jour");

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une region");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.getQuartiers();
        }
    }

    public void filterArondissementByDepartement() {
        try {
            arrondissements.clear();
            if (region != null) {
                arrondissements = departement.getArrondissementList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        try {
            if (quartier != null) {

                /*if (quartier.get.isEmpty()) {
                 quartierFacadeLocal.remove(quartier);
                 Utilitaires.saveOperation("Suppression de la rue -> " + quartier.getLibelle(), SessionMBean.getUser(), mouchardFacadeLocal);
                 } else {
                 JsfUtil.addErrorMessage("Plusieurs rues sont rattachées à ce quartier");
                 }*/
            } else {
                JsfUtil.addErrorMessage("Aucun Quartier selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterDepartementByRegion() {
        try {
            departements.clear();
            if (region != null) {
                departements = region.getDepartementList();
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

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Quartier getSelected() {
        return selected;
    }

    public void setSelected(Quartier selected) {
        this.selected = selected;
    }

    public List<Quartier> getQuartiers() {
        quartiers = quartierFacadeLocal.findAll();
        return quartiers;
    }

    public void setQuartiers(List<Quartier> quartiers) {
        this.quartiers = quartiers;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
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

    public Arrondissement getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(Arrondissement arrondissement) {
        this.arrondissement = arrondissement;
    }

    public List<Arrondissement> getArrondissements() {
        arrondissements = arrondissementFacadeLocal.findAll();
        return arrondissements;
    }

    public void setArrondissements(List<Arrondissement> arrondissements) {
        this.arrondissements = arrondissements;
    }

}
