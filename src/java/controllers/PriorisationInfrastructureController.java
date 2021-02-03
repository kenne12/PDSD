/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Airesante;
import entities.Etatinfrastructure;
import entities.Infrastructure;
import entities.Paramcoutinfrastructure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AiresanteFacadeLocal;
import sessions.EtatinfrastructureFacadeLocal;
import sessions.InfrastructureFacadeLocal;
import sessions.ParamcoutinfrastructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PriorisationInfrastructureController implements Serializable {

    @EJB
    private AiresanteFacadeLocal airesanteFacadeLocal;
    private Airesante airesante = new Airesante();
    private List<Airesante> airesantes = new ArrayList<>();

    @EJB
    private InfrastructureFacadeLocal infrastructureFacadeLocal;
    private List<Infrastructure> infrastructures = new ArrayList<>();
    private Infrastructure infrastructure = new Infrastructure();

    @EJB
    private EtatinfrastructureFacadeLocal etatinfrastructureFacadeLocal;
    private List<Etatinfrastructure> etatinfrastructures = new ArrayList<>();

    @EJB
    private ParamcoutinfrastructureFacadeLocal paramcoutinfrastructureFacadeLocal;

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public PriorisationInfrastructureController() {
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
            if (!infrastructures.isEmpty()) {
                for (Infrastructure i : infrastructures) {
                    infrastructureFacadeLocal.edit(i);
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
                infrastructures = infrastructureFacadeLocal.find(airesante, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findByInfrastructure(Infrastructure infrastructure) {
        String result = "";
        try {
            List<Paramcoutinfrastructure> paramcoutinfrastructures = paramcoutinfrastructureFacadeLocal.findByTypeStructureTypeInfra(infrastructure.getIdstructure().getIdtypestructure(), infrastructure.getIdtypeinfraTypestruc().getIdtypeinfrastructure(), infrastructure.getIdetatinfrastructure());
            if (!paramcoutinfrastructures.isEmpty()) {
                result = infrastructure.getIdstructure().getNomFr() + " : " + paramcoutinfrastructures.get(0).getDefaultoperationFr();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public Double findByInfrastructure1(Infrastructure infrastructure) {
        Double result = 0d;
        try {
            List<Paramcoutinfrastructure> paramcoutinfrastructures = paramcoutinfrastructureFacadeLocal.findByTypeStructureTypeInfra(infrastructure.getIdstructure().getIdtypestructure(), infrastructure.getIdtypeinfraTypestruc().getIdtypeinfrastructure(), infrastructure.getIdetatinfrastructure());
            if (!paramcoutinfrastructures.isEmpty()) {
                result = paramcoutinfrastructures.get(0).getCoutunitaire();
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

    public List<Etatinfrastructure> getEtatinfrastructures() {
        etatinfrastructures = etatinfrastructureFacadeLocal.findAll();
        return etatinfrastructures;
    }

    public void setEtatinfrastructures(List<Etatinfrastructure> etatinfrastructures) {
        this.etatinfrastructures = etatinfrastructures;
    }

    public List<Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public void setInfrastructures(List<Infrastructure> infrastructures) {
        this.infrastructures = infrastructures;
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

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

}
