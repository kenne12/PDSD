/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Airesante;
import entities.Equipementtraceur;
import entities.Parametrecoutequipement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AiresanteFacadeLocal;
import sessions.EquipementtraceurFacadeLocal;
import sessions.ParametrecoutequipementFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PriorisationEquipementController implements Serializable {

    @EJB
    private AiresanteFacadeLocal airesanteFacadeLocal;
    private Airesante airesante = new Airesante();
    private List<Airesante> airesantes = new ArrayList<>();

    @EJB
    private EquipementtraceurFacadeLocal equipementtraceurFacadeLocal;
    private List<Equipementtraceur> equipementtraceurs = new ArrayList<>();

    @EJB
    private ParametrecoutequipementFacadeLocal parametrecoutequipementFacadeLocal;

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public PriorisationEquipementController() {
    }

    @PostConstruct
    private void init() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {

    }

    public void uptadeTable() {
        try {
            if (!equipementtraceurs.isEmpty()) {
                for (Equipementtraceur e : equipementtraceurs) {
                    equipementtraceurFacadeLocal.edit(e);
                }

                this.update();

                JsfUtil.addSuccessMessage("Activité priorisées avec succès");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            if (airesante.getIdairesante() != null) {
                equipementtraceurs = equipementtraceurFacadeLocal.find(airesante, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findByEquipement(Equipementtraceur equipementtraceur) {
        String result = "";
        try {
            List<Parametrecoutequipement> parametrecoutequipements = parametrecoutequipementFacadeLocal.findByTypeStructureTypeInfra(equipementtraceur.getIdstructure().getIdtypestructure(), equipementtraceur.getIdtypestrucTypeequipement().getIdtypeequipementtraceur(), equipementtraceur.getIdetatequipement());
            if (!parametrecoutequipements.isEmpty()) {
                result = equipementtraceur.getIdstructure().getNomFr() + " : " + parametrecoutequipements.get(0).getDefaultoperationFr();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public Double findByEquipement1(Equipementtraceur equipementtraceur) {
        Double result = 0d;
        try {
            List<Parametrecoutequipement> parametrecoutequipements = parametrecoutequipementFacadeLocal.findByTypeStructureTypeInfra(equipementtraceur.getIdstructure().getIdtypestructure(), equipementtraceur.getIdtypestrucTypeequipement().getIdtypeequipementtraceur(), equipementtraceur.getIdetatequipement());
            if (!parametrecoutequipements.isEmpty()) {
                result = parametrecoutequipements.get(0).getCoutunitaire();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0d;
        }
        return result;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Airesante getAiresante() {
        return airesante;
    }

    public void setAiresante(Airesante airesante) {
        this.airesante = airesante;
    }

    public List<Airesante> getAiresantes() {
        try {
            airesantes = airesanteFacadeLocal.find(SessionMBean.getDistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airesantes;
    }

    public void setAiresantes(List<Airesante> airesantes) {
        this.airesantes = airesantes;
    }

    public List<Equipementtraceur> getEquipementtraceurs() {
        return equipementtraceurs;
    }

    public void setEquipementtraceurs(List<Equipementtraceur> equipementtraceurs) {
        this.equipementtraceurs = equipementtraceurs;
    }

}
