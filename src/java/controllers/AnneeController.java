/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.UserTransaction;
import sessions.MouchardFacadeLocal;
import sessions.AnneeFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class AnneeController {

    /**
     * Creates a new instance of AnneeController
     */
    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();
    private Boolean detail = true;
    
    private String mode = "";
    
    @Resource
    UserTransaction ut;
    
    public AnneeController() {
        
    }
    
    @PostConstruct
    private void init() {
        annee = new Annee();
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void prepareCreate() {
        annee = new Annee();
        annee.setDepense(true);
        annee.setRecette(true);
        annee.setCibles(true);
        annee.setMape(true);
        annee.setProjectionrecette(true);
        annee.setChronogramme(true);
        annee.setPopfosa(true);
        annee.setPlannifie(true);
        mode = "Create";
    }
    
    public void prepareEdit() {
        mode = "Edit";
    }
    
    public void saveAnnee() {
        try {
            
            if (mode == "Create") {
                annee.setIdannee(anneeFacadeLocal.nextId());
                List<Annee> test = anneeFacadeLocal.findByNom(annee.getNom());
                
                if (test.isEmpty()) {
                    ut.begin();
                    anneeFacadeLocal.create(annee);
                    annee = new Annee();
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' annee -> " + annee.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                    ut.commit();
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un annee portant de nom existe dejà");
                }
            } else {
                Annee test = anneeFacadeLocal.find(annee.getIdannee());
                utilitaire.Utilitaires.saveOperation("Modification du annee -> " + test.getNom() + " par -> " + annee.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                anneeFacadeLocal.edit(annee);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteAnnee() {
        try {
            if (annee != null) {
                
                anneeFacadeLocal.remove(annee);
                Utilitaires.saveOperation("Suppression du annee -> " + annee.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                
            } else {
                JsfUtil.addErrorMessage("Aucun annee selectionné !");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MouchardFacadeLocal getMouchardFacadeLocal() {
        return mouchardFacadeLocal;
    }
    
    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        this.mouchardFacadeLocal = mouchardFacadeLocal;
    }
    
    public Annee getAnnee() {
        return annee;
    }
    
    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
    
    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findAllRange();
        return annees;
    }
    
    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }
    
    public Boolean getDetail() {
        return detail;
    }
    
    public void setDetail(Boolean detail) {
        this.detail = detail;
    }
    
}
