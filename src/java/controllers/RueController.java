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
import entities.Quartier;
import entities.Region;
import entities.Rue;
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
import sessions.QuartierFacadeLocal;
import sessions.RegionFacadeLocal;
import sessions.RueFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RueController {

    /**
     * Creates a new instance of RueController
     */
    @EJB
    private RueFacadeLocal rueFacadeLocal;
    private List<Rue> rues = new ArrayList<>();
    private Rue rue;
    private Rue selected;

    @EJB
    private QuartierFacadeLocal quartierFacadeLocal;
    private List<Quartier> quartiers = new ArrayList<>();
    private Quartier quartier;

     @EJB
    private ArrondissementFacadeLocal arrondissementFacadeLocal;
    private List<Arrondissement> arrondissements = new ArrayList<>();
    private Arrondissement arrondissement;

    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private List<Region> regions = new ArrayList<>();
    private Region region;

    @EJB
    private DepartementFacadeLocal departementFacadeLocal;
    private List<Departement> departements = new ArrayList<>();
    private Departement departement;

    
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public RueController() {

    }

    @PostConstruct
    private void init() {
        selected = new Rue();
        rue = new Rue();
        mouchard = new Mouchard();
        quartier= new Quartier();
        quartiers=quartierFacadeLocal.findAll();
        arrondissement= new Arrondissement();
        arrondissements = arrondissementFacadeLocal.findAll();
        departement= new Departement();
        departements =departementFacadeLocal.findAll();
        region=new  Region();
        regions = regionFacadeLocal.findAll();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rue = new Rue();
        selected = new Rue();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void save() {
        try {

            if (mode == "Create") {
                rue.setIdrue(rueFacadeLocal.nextId());
                rueFacadeLocal.create(rue);
                utilitaire.Utilitaires.saveOperation("Enregistrement de la rue -> " + rue.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                Rue r = rueFacadeLocal.find(rue.getIdrue());
                rueFacadeLocal.edit(rue);
                utilitaire.Utilitaires.saveOperation("Modification de la rue -> " + r.getNom() + " Par -> " + rue.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Région mise à jour !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void edit() {
        try {
            if (selected.getIdrue() != null) {
                Rue r = rueFacadeLocal.find(selected.getIdrue());
                rueFacadeLocal.edit(selected);
                mouchard.setAction("Modification de la rue ->  " + r.getNom() + " -> " + selected.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);

                JsfUtil.addSuccessMessage("La rue a été mise à jour");

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une rue");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.getRues();
        }
    }

    public void delete() {
        try {
            if (rue != null) {
               
                    rueFacadeLocal.remove(rue);
                    Utilitaires.saveOperation("Suppression de la rue -> " + rue.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun Rue selectionné !");
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
       
    public void filterQuartierByArondissement() {
        try {
           quartiers.clear();
            if (region != null) {
                quartiers = arrondissement.getQuartierList();               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
    
    public void filterRueByQuartier() {
        try {
           rues.clear();
            if (quartier != null) {
                rues = quartier.getRueList();               
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

    

    public Rue getRue() {
        return rue;
    }

    public void setRue(Rue rue) {
        this.rue = rue;
    }

    public Rue getSelected() {
        return selected;
    }

    public void setSelected(Rue selected) {
        this.selected = selected;
    }

    public List<Rue> getRues() {
        rues=rueFacadeLocal.findAll();
        return rues;
    }

    public void setRues(List<Rue> rues) {
        this.rues = rues;
    }

    public Quartier getQuartier() {
         
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public List<Quartier> getQuartiers() {
     quartiers = quartierFacadeLocal.findAll();
        return quartiers;
    }

    public void setQuartiers(List<Quartier> quartiers) {
        this.quartiers = quartiers;
    }

    public List<Arrondissement> getArrondissements() {
        return arrondissements;
    }

    public void setArrondissements(List<Arrondissement> arrondissements) {
        this.arrondissements = arrondissements;
    }

    public Arrondissement getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(Arrondissement arrondissement) {
        this.arrondissement = arrondissement;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    
  
}
