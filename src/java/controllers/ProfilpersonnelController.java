/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Profilpersonnel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.ProfilpersonnelFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ProfilpersonnelController {

    /**
     * Creates a new instance of ProfilpersonnelController
     */
    @EJB
    private ProfilpersonnelFacadeLocal profilpersonnelFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    
    private Profilpersonnel profilpersonnel = new Profilpersonnel();
    private List<Profilpersonnel> profilpersonnels = new ArrayList<>();
    private Boolean detail = true;
    
    private String mode = "";
    
    public ProfilpersonnelController() {
        
    }
    
    @PostConstruct
    private void init() {
        profilpersonnel = new Profilpersonnel();
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void prepareCreate() {
        profilpersonnel = new Profilpersonnel();
        profilpersonnel.setDistrict(true);
        profilpersonnel.setRegion(true);
        mode = "Create";
    }
    
    public void prepareEdit() {
        mode = "Edit";
    }
    
    public void saveProfilpersonnel() {
        try {
            
            if (mode == "Create") {
                profilpersonnel.setIdprofilpersonnel(profilpersonnelFacadeLocal.nextId());
                
                profilpersonnelFacadeLocal.create(profilpersonnel);
                utilitaire.Utilitaires.saveOperation("Enregistrement du profil personnel -> " + profilpersonnel.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");                
            } else {
                Profilpersonnel test = profilpersonnelFacadeLocal.find(profilpersonnel.getIdprofilpersonnel());
                utilitaire.Utilitaires.saveOperation("Modification du profil personnel -> " + test.getNomFr() + " par -> " + profilpersonnel.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                profilpersonnelFacadeLocal.edit(profilpersonnel);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteProfilpersonnel() {
        try {
            if (profilpersonnel != null) {
                
                profilpersonnelFacadeLocal.remove(profilpersonnel);
                Utilitaires.saveOperation("Suppression du profil personnel -> " + profilpersonnel.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                
            } else {
                JsfUtil.addErrorMessage("Aucun profil personnel selectionné !");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Profilpersonnel getProfilpersonnel() {
        return profilpersonnel;
    }
    
    public void setProfilpersonnel(Profilpersonnel profilpersonnel) {
        this.profilpersonnel = profilpersonnel;
    }
    
    public List<Profilpersonnel> getProfilpersonnels() {
        profilpersonnels = profilpersonnelFacadeLocal.findAll();
        return profilpersonnels;
    }
    
    public void setProfilpersonnels(List<Profilpersonnel> profilpersonnels) {
        this.profilpersonnels = profilpersonnels;
    }
    
    public Boolean getDetail() {
        return detail;
    }
    
    public void setDetail(Boolean detail) {
        this.detail = detail;
    }
    
}
