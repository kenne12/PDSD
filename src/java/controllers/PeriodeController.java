/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Periode;
import entities.Periodederattachement;

import entities.Periodicite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;

import sessions.PeriodiciteFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class PeriodeController {

    /**
     * Creates a new instance of PeriodeController
     */
    @EJB
    private PeriodeFacadeLocal periodeFacadeLocal;
    private Periode periode;
    private Periode selected;
    private List<Periode> periodes = new ArrayList<>();

    @EJB
    private PeriodiciteFacadeLocal PeriodiciteFacadeLocal;
    private Periodicite periodicite;
    private List<Periodicite> periodicites = new ArrayList<>();

    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private Periodederattachement periodederattachement;
    private List<Periodederattachement> periodederattachements = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "Create";

    public PeriodeController() {

    }

    @PostConstruct
    private void init() {
        periode = new Periode();
        selected = new Periode();
        periodicite = new Periodicite();
        periodederattachement = new Periodederattachement();
        mouchard = new Mouchard();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (periode != null) {
                if (periode.getIdperiodicite() != null) {
                    periodicite = periode.getIdperiodicite();
                }

                if (periode.getIdperiodederattachement() != null) {
                    periodederattachement = periode.getIdperiodederattachement();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Periode> test = periodeFacadeLocal.findByNom(periode.getNom());
                if (test.isEmpty()) {

                    periode.setIdperiode(periodeFacadeLocal.nextId());

                    if (periodicite.getIdperiodicite() != null) {
                        periodicite = PeriodiciteFacadeLocal.find(periodicite.getIdperiodicite());
                        periode.setIdperiodicite(periodicite);
                    }

                    if (periodederattachement.getIdperiodederattachement() != null) {
                        periodederattachement = periodederattachementFacadeLocal.find(periodederattachement.getIdperiodederattachement());
                        periode.setIdperiodederattachement(periodederattachement);
                    }

                    periodeFacadeLocal.create(periode);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de la période -> " + periode.getNom());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une période portant ce nom existe dejà");
                    this.getPeriodes();
                }
            } else {
                if (periode != null) {

                    if (periodicite.getIdperiodicite() != null) {
                        periodicite = PeriodiciteFacadeLocal.find(periodicite.getIdperiodicite());
                        periode.setIdperiodicite(periodicite);
                    }

                    if (periodederattachement.getIdperiodederattachement() != null) {
                        periodederattachement = periodederattachementFacadeLocal.find(periodederattachement.getIdperiodederattachement());
                        periode.setIdperiodederattachement(periodederattachement);
                    }

                    periodeFacadeLocal.edit(periode);
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (periode != null) {

                periodeFacadeLocal.remove(periode);
                mouchard.setAction("Suppression de la période -> " + periode.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                detail = true;
                JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

            } else {
                JsfUtil.addErrorMessage("Aucune période selectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getPeriodicites();
        }
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public Periode getSelected() {
        return selected;
    }

    public void setSelected(Periode selected) {
        this.selected = selected;
    }

    public List<Periode> getPeriodes() {
        periodes = periodeFacadeLocal.findAll();
        return periodes;
    }

    public void setPeriodes(List<Periode> periodes) {
        this.periodes = periodes;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public List<Periodicite> getPeriodicites() {
        periodicites = PeriodiciteFacadeLocal.findAll();
        return periodicites;
    }

    public void setPeriodicites(List<Periodicite> periodicites) {
        this.periodicites = periodicites;
    }

    public Periodederattachement getPeriodederattachement() {
        return periodederattachement;
    }

    public void setPeriodederattachement(Periodederattachement periodederattachement) {
        this.periodederattachement = periodederattachement;
    }

    public List<Periodederattachement> getPeriodederattachements() {
        periodederattachements = periodederattachementFacadeLocal.findAll();
        return periodederattachements;
    }

    public void setPeriodederattachements(List<Periodederattachement> periodederattachements) {
        this.periodederattachements = periodederattachements;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

}
