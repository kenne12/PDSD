/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Periodicite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.PeriodiciteFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class PeriodiciteController {

    /**
     * Creates a new instance of PeriodiciteController
     */
    @EJB
    private PeriodiciteFacadeLocal periodiciteFacadeLocal;
    private Periodicite periodicite;
    private Periodicite selected;
    private List<Periodicite> periodicites = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public PeriodiciteController() {

    }

    @PostConstruct
    private void init() {
        periodicite = new Periodicite();
        selected = new Periodicite();
        mouchard = new Mouchard();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        periodicite = new Periodicite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Periodicite> test = periodiciteFacadeLocal.findByNom(periodicite.getNomFr());
                if (test.isEmpty()) {
                    periodicite.setIdperiodicite(periodiciteFacadeLocal.nextId());
                    periodiciteFacadeLocal.create(periodicite);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de la période -> " + periodicite.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    this.getPeriodicites();
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une périodicité portant ce nom existe dejà");
                    this.getPeriodicites();
                }
            } else {
                if (periodicite != null) {
                    periodiciteFacadeLocal.edit(periodicite);
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (selected.getIdperiodicite() != null) {
                Periodicite u = periodiciteFacadeLocal.find(selected.getIdperiodicite());
                periodiciteFacadeLocal.edit(selected);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Modification de la périodicité ->  " + u.getNomFr()+ " -> " + selected.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);

                JsfUtil.addSuccessMessage("La périodicité a été mise à jour");
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une périodicité");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getPeriodicites();
        }
    }

    public void delete() {
        try {
            if (periodicite != null) {
                if (periodicite.getIndicateurList().isEmpty()) {

                    periodiciteFacadeLocal.remove(periodicite);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Suppression de la périodicité -> " + periodicite.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

                } else {
                    JsfUtil.addErrorMessage("Plusieurs inicateurs de sané utilisent cette péridicité");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune périodicité selectionnée !");
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

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public Periodicite getSelected() {
        return selected;
    }

    public void setSelected(Periodicite selected) {
        this.selected = selected;
    }

    public List<Periodicite> getPeriodicites() {
        periodicites = periodiciteFacadeLocal.findAllRange();
        return periodicites;
    }

    public void setPeriodicites(List<Periodicite> periodicites) {
        this.periodicites = periodicites;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

}
