/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Mouchard;
import entities.Region;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.RegionFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class DistrictController {

    /**
     * Creates a new instance of DistrictController
     */
    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    private District selected;
    
    @EJB
    private RegionFacadeLocal regionFacadeLocal;
    private Region region;
    private List<Region> regions = new ArrayList<>();
    
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;
    
    private boolean detail = true;
    
    public DistrictController() {
        
    }
    
    @PostConstruct
    private void init() {
        district = new District();
        selected = new District();
        mouchard = new Mouchard();
        region = new Region();
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
            List<District> test = districtFacadeLocal.findByNom(district.getNomFr());
            if (test.isEmpty()) {
                district.setIddistrict(districtFacadeLocal.nextId());
                districtFacadeLocal.create(district);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement du district : " + district.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                this.getDistricts();
                JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
            } else {
                JsfUtil.addErrorMessage("Un district portant ce nom existe dejà");
                this.getDistricts();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void edit() {
        try {
            if (selected.getIddistrict() != null) {
                District r = districtFacadeLocal.find(selected.getIddistrict());
                districtFacadeLocal.edit(selected);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Modification du district ->  " + r.getNomFr() + " -> " + selected.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                
                JsfUtil.addSuccessMessage("Le district a été mis à jour");
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner un district");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getDistricts();
        }
    }
    
    public void delete() {
        try {
            if (selected.getIddistrict() != null) {
                
                districtFacadeLocal.remove(selected);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Suppression du district -> " + selected.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                JsfUtil.addSuccessMessage("Suppression effectuée avec succès");
                
            } else {
                
                JsfUtil.addErrorMessage("Aucun district sélectionné !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getDistricts();
        }
    }
    
    public boolean isDetail() {
        return detail;
    }
    
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    public District getDistrict() {
        return district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }
    
    public District getSelected() {
        return selected;
    }
    
    public void setSelected(District selected) {
        this.selected = selected;
    }
    
    public List<District> getDistricts() {
        districts = districtFacadeLocal.findAllRange();
        return districts;
    }
    
    public void setDistricts(List<District> districts) {
        this.districts = districts;
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
}
