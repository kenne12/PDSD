/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Pays;
import entities.Region;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.PaysFacadeLocal;
import sessions.RegionFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RegionController {

    /**
     * Creates a new instance of RegionController
     */
    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private List<Region> listRegion = new ArrayList<>();
    private Region region;
    private Region selected;
    
    @EJB
    private PaysFacadeLocal paysFacadeLocal;
    private List<Pays> listpays = new ArrayList<>();
    private Pays pays = new Pays();
    
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;
    
    private boolean detail = true;
    
    public RegionController() {
        
    }
    
    @PostConstruct
    private void init() {
        selected = new Region();
        region = new Region();
        mouchard = new Mouchard();
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void prepareCreate() {
        region = new Region();
        selected = new Region();
    }
    
    public void prepareEdit() {
        try {
            if (region.getIdpays() != null) {
                pays = paysFacadeLocal.find(region.getIdpays());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            List<Region> test = regionFacadeLocal.findByNom(region.getNomFr());
            if (test.isEmpty()) {
                region.setIdregion(regionFacadeLocal.nextId());
                region.setIdpays(pays.getIdPays());
                regionFacadeLocal.create(region);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement de la region : " + region.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                this.getListRegion();
                JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
            } else {
                JsfUtil.addSuccessMessage("Une région portant ce nom existe dejà");
                this.getListRegion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void edit() {
        try {
            if (selected.getIdregion() != null) {
                Region r = regionFacadeLocal.find(selected.getIdregion());
                regionFacadeLocal.edit(selected);
                mouchard.setAction("Modification de la region ->  " + r.getNomFr() + " -> " + selected.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
               // mouchardFacadeLocal.create(mouchard);
                
                JsfUtil.addSuccessMessage("La region a été mise à jour");
                
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une region");
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            this.getListRegion();
        }
    }
    
    public void delete() {
        try {
            if (selected.getIdregion() != null) {
                if (selected.getDepartementList().isEmpty()) {
                    if (selected.getDistrictList().isEmpty()) {
                        
                        regionFacadeLocal.remove(selected);
                        mouchard.setAction("Suppression de la region : " + selected.getNomFr());
                        mouchard.setIdutilisateur(SessionMBean.getUser());
                        mouchard.setDateaction(new Date());
                        //mouchardFacadeLocal.create(mouchard);
                        JsfUtil.addSuccessMessage("Suppression effectuée avec succès");
                        
                    } else {
                        JsfUtil.addErrorMessage("Plusieurs departement utilisent cette région");
                    }
                } else {
                    JsfUtil.addErrorMessage("Plusieurs districts utilisent cette région");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une region !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getListRegion();
        }
    }
    
    public boolean isDetail() {
        return detail;
    }
    
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    public List<Region> getListRegion() {
        listRegion = regionFacadeLocal.findAllRange();
        return listRegion;
    }
    
    public void setListRegion(List<Region> listRegion) {
        this.listRegion = listRegion;
    }
    
    public Region getRegion() {
        return region;
    }
    
    public void setRegion(Region region) {
        this.region = region;
    }
    
    public Region getSelected() {
        return selected;
    }
    
    public void setSelected(Region selected) {
        this.selected = selected;
    }
    
    public List<Pays> getListpays() {
        listpays = paysFacadeLocal.findAll();
        return listpays;
    }
    
    public void setListpays(List<Pays> listpays) {
        this.listpays = listpays;
    }
    
    public Pays getPays() {
        return pays;
    }
    
    public void setPays(Pays pays) {
        this.pays = pays;
    }
    
}
