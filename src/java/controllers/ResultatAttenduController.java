/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import entities.Indicateur;
import entities.Interventionpnds;
import entities.Resultatattendu;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.IndicateurFacadeLocal;
import sessions.InterventionpndsFacadeLocal;
import sessions.ResultatattenduFacadeLocal;

/**
 *
 * @author Abdel Bein-Info
 */
@ManagedBean
@ViewScoped
public class ResultatAttenduController {

    @EJB
    private ResultatattenduFacadeLocal resultatattenduFacadeLocal;
    private Resultatattendu resultatattendu = new Resultatattendu();
    private List<Resultatattendu> resultatattendus = new ArrayList<>();
    List<Resultatattendu> resultatattendTest = new ArrayList<>();

    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();

    @EJB
    private InterventionpndsFacadeLocal interventionpndsFacadeLocal;
    private Interventionpnds interventionpnds = new Interventionpnds();
    private List<Interventionpnds> interventionpndses = new ArrayList<>();

    private boolean detail = true;

    private boolean importer = true;

    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public ResultatAttenduController() {
    }

    @PostConstruct
    private void init() {
        resultatattendu = new Resultatattendu();
        indicateur = new Indicateur();
    }

    public void prepare() {

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
            resultatattendu = new Resultatattendu();
            indicateur = new Indicateur();
            interventionpnds = new Interventionpnds();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void loadData() {
        try {
            List<Indicateur> indis = indicateurFacadeLocal.findByNiveauCollecte(2);

            for (Indicateur indi : indis) {
                Resultatattendu indi1 = new Resultatattendu();
                indi1.setIdresultatattendu(resultatattenduFacadeLocal.nextId());
                indi1.setIdindicateur(indi);
                resultatattenduFacadeLocal.create(indi1);
            }
            JsfUtil.addSuccessMessage("Importation Réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            resultatattendu.setIdresultatattendu(resultatattenduFacadeLocal.nextId());
            resultatattendu.setIdindicateur(indicateur);
            resultatattenduFacadeLocal.create(resultatattendu);
            resultatattendu = new Resultatattendu();

            JsfUtil.addSuccessMessage("Opération réussie");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (resultatattendu != null) {
                resultatattenduFacadeLocal.edit(resultatattendu);
                JsfUtil.addErrorMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (resultatattendu != null) {
                resultatattenduFacadeLocal.remove(resultatattendu);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterIndicateur() {
        try {
            indicateurs.clear();
            if (interventionpnds.getIdinterventionpnds() != null) {
                interventionpnds = interventionpndsFacadeLocal.find(interventionpnds.getIdinterventionpnds());
                indicateurs = indicateurFacadeLocal.findByIntervention(interventionpnds);
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

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public List<Indicateur> getIndicateurs() {

        return indicateurs;

    }

    public void setIndicateurs(List<Indicateur> indicateurs) {
        this.indicateurs = indicateurs;
    }

    public Resultatattendu getResultatattendu() {
        return resultatattendu;
    }

    public void setResultatattendu(Resultatattendu resultatattendu) {
        this.resultatattendu = resultatattendu;
    }

    public List<Resultatattendu> getResultatattendus() {
        resultatattendus = resultatattenduFacadeLocal.findAllRange();
        if (resultatattendus.isEmpty()) {
            importer = false;
        } else {
            importer = true;
        }
        return resultatattendus;
    }

    public void setResultatattendus(List<Resultatattendu> resultatattendus) {
        this.resultatattendus = resultatattendus;
    }

    public boolean isImporter() {
        return importer;
    }

    public void setImporter(boolean importer) {
        this.importer = importer;
    }

    public Interventionpnds getInterventionpnds() {
        return interventionpnds;
    }

    public void setInterventionpnds(Interventionpnds interventionpnds) {
        this.interventionpnds = interventionpnds;
    }

    public List<Interventionpnds> getInterventionpndses() {
        try {
            interventionpndses = interventionpndsFacadeLocal.findAllRangeCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interventionpndses;
    }

    public void setInterventionpndses(List<Interventionpnds> interventionpndses) {
        this.interventionpndses = interventionpndses;
    }

    public List<Resultatattendu> getResultatattendTest() {
        return resultatattendTest;
    }

    public void setResultatattendTest(List<Resultatattendu> resultatattendTest) {
        this.resultatattendTest = resultatattendTest;
    }

}
