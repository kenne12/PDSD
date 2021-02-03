/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Arrondissement;
import entities.Departement;
import entities.Mouchard;
import entities.Region;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ArrondissementFacadeLocal;
import sessions.DepartementFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.RegionFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ArrondissementController {

    /**
     * Creates a new instance of ArrondissementController
     */
    @EJB
    private ArrondissementFacadeLocal arrondissementFacadeLocal;
    private Arrondissement arrondissement;
    private Arrondissement selected;
    private List<Arrondissement> arrondissements = new ArrayList<>();

    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private Region region;
    private List<Region> regions = new ArrayList<>();

    @EJB
    private DepartementFacadeLocal departementFacadeLocal;
    private Departement departement;
    private List<Departement> departements = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    public ArrondissementController() {

    }

    @PostConstruct
    private void init() {
        arrondissement = new Arrondissement();
        selected = new Arrondissement();
        mouchard = new Mouchard();
        departement = new Departement();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {

    }

    public void prepareEdit() {

    }

    public void save() {
        try {
            List<Arrondissement> test = arrondissementFacadeLocal.findByNom(arrondissement.getNom());
            if (test.isEmpty()) {
                arrondissement.setIdarrondi(arrondissementFacadeLocal.nextId());
                arrondissementFacadeLocal.create(arrondissement);
                mouchard.setAction("Enregistrement de l'arrondissement -> " + arrondissement.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                this.getArrondissements();
                JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
            } else {
                JsfUtil.addSuccessMessage("Une région portant ce nom existe dejà");
                this.getArrondissements();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (selected.getIdarrondi() != null) {
                Arrondissement r = arrondissementFacadeLocal.find(selected.getIdarrondi());
                arrondissementFacadeLocal.edit(selected);
                mouchard.setAction("Modification de l'arrondissement ->  " + r.getNom() + " -> " + selected.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);

                JsfUtil.addSuccessMessage("La region a été mise à jour");

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une region");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.getArrondissements();
        }
    }

    public void delete() {
        try {
            if (selected.getIdarrondi() != null) {

                arrondissementFacadeLocal.remove(selected);
                mouchard.setAction("Suppression de la region : " + selected.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une region !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getDepartements();
        }
    }
    
       public void updateDepartement() {
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

    public Arrondissement getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(Arrondissement arrondissement) {
        this.arrondissement = arrondissement;
    }

    public Arrondissement getSelected() {
        return selected;
    }

    public void setSelected(Arrondissement selected) {
        this.selected = selected;
    }

    public List<Arrondissement> getArrondissements() {
        arrondissements = arrondissementFacadeLocal.findAll();
        return arrondissements;
    }

    public void setArrondissements(List<Arrondissement> arrondissements) {
        this.arrondissements = arrondissements;
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
        departements = departementFacadeLocal.findAll();
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
         regions = regionFacadeLocal.findAll();
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

}
