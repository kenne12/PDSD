/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
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
import sessions.DepartementFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.RegionFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class DepartementController {

    /**
     * Creates a new instance of DepartementController
     */
    @EJB
    private DepartementFacadeLocal departementFacadeLocal;
    private Departement departement;
    private List<Departement> departements = new ArrayList<>();
    private Departement selected;

    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private Region region;
    private List<Region> regions = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    public DepartementController() {

    }

    @PostConstruct
    private void init() {
        departement = new Departement();
        selected = new Departement();
        mouchard = new Mouchard();
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
            List<Departement> test = departementFacadeLocal.findByNom(departement.getNom());
            if (test.isEmpty()) {
                departement.setIddepart(departementFacadeLocal.nextId());
                departementFacadeLocal.create(departement);
                mouchard.setAction("Enregistrement du département : " + departement.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                this.getDepartements();
                JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
            } else {
                JsfUtil.addSuccessMessage("Un département portant ce nom existe dejà");
                this.getDepartements();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (selected.getIddepart() != null) {
                Departement r = departementFacadeLocal.find(selected.getIddepart());
                departementFacadeLocal.edit(selected);
                mouchard.setAction("Modification du departement ->  " + r.getNom() + " -> " + selected.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);

                JsfUtil.addSuccessMessage("Le département a été mise à jour");

            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une region");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.getDepartements();
        }

    }

    public void delete() {
        try {
            if (selected.getIddepart() != null) {
                if (selected.getArrondissementList().isEmpty()) {

                    departementFacadeLocal.remove(selected);
                    mouchard.setAction("Suppression du departement : " + selected.getNom());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

                } else {
                    JsfUtil.addErrorMessage("Plusieurs districts utilisent cette région");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner un departement !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getDepartements();
        }

    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
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

    public Departement getSelected() {
        return selected;
    }

    public void setSelected(Departement selected) {
        this.selected = selected;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Region> getRegions() {
        regions = regionFacadeLocal.findAllRange();
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

}
