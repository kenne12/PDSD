/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Airesante;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.DistrictFacadeLocal;
import sessions.AiresanteFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class AiresanteController {

    @EJB
    private AiresanteFacadeLocal airesanteFacadeLocal;
    private Airesante airesante = new Airesante();
    private List<Airesante> airesantes = new ArrayList<>();

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district = new District();
    private List<District> districts = new ArrayList<>();



    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of AiresanteController
     */
    public AiresanteController() {
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
        mode = "Create";
        try {
            airesante = new Airesante();
            district = new District();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (airesante != null) {
                if (airesante.getIddistrict() != null) {
                    district = airesante.getIddistrict();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {
                airesante.setIdairesante(airesanteFacadeLocal.nextId());
               
                if (district.getIddistrict() != null) {
                    district = districtFacadeLocal.find(district.getIddistrict());
                    airesante.setIddistrict(district);
                }

                airesanteFacadeLocal.create(airesante);
                airesante = new Airesante();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (airesante != null) {

                    if (district.getIddistrict() != null) {
                        district = districtFacadeLocal.find(district.getIddistrict());
                        airesante.setIddistrict(district);
                    }

                    airesanteFacadeLocal.edit(airesante);

                    JsfUtil.addSuccessMessage("Sous axe stratégique mis à jour avec succès");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (airesante != null) {
                airesanteFacadeLocal.remove(airesante);
                airesante = new Airesante();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public List<District> getDistricts() {
        districts = districtFacadeLocal.findAll();
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
