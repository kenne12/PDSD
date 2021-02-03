/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Categorieindicateur;
import entities.Indicateur;
import entities.Interventionpnds;
import entities.Mouchard;
import entities.Niveaucollecte;
import entities.Periodicite;
import entities.Unite;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CategorieindicateurFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.InterventionpndsFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.NiveaucollecteFacadeLocal;
import sessions.PeriodiciteFacadeLocal;
import sessions.UniteFacadeLocal;
import utilitaire.Printer;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class IndicateurController {

    /**
     * Creates a new instance of IndicateurController
     */
    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();
    private Indicateur selected = new Indicateur();

    @EJB
    private PeriodiciteFacadeLocal periodiciteFacadeLocal;
    private Periodicite periodicite;
    private List<Periodicite> periodicites = new ArrayList<>();

    @EJB
    private CategorieindicateurFacadeLocal categorieindicateurFacadeLocal;
    private Categorieindicateur categorieindicateur;
    private List<Categorieindicateur> categorieindicateurs = new ArrayList<>();

    @EJB
    private UniteFacadeLocal uniteFacadeLocal;
    private Unite unite;
    private List<Unite> unites = new ArrayList<>();

    @EJB
    private NiveaucollecteFacadeLocal niveaucollecteFacadeLocal;
    private Niveaucollecte niveaucollecte;
    private List<Niveaucollecte> niveaucollectes = new ArrayList<>();

    @EJB
    private InterventionpndsFacadeLocal interventionpndsFacadeLocal;
    private Interventionpnds interventionpnds = new Interventionpnds();
    private List<Interventionpnds> interventionpndss = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private Boolean viewCreateButton = false;

    private String mode = "";

    public IndicateurController() {

    }

    @PostConstruct
    private void init() {
        indicateur = new Indicateur();
        selected = new Indicateur();
        mouchard = new Mouchard();
        indicateurs = indicateurFacadeLocal.findAllRange();
        periodicites = periodiciteFacadeLocal.findAll();
        categorieindicateurs = categorieindicateurFacadeLocal.findAll();
        unites = uniteFacadeLocal.findAll();
        categorieindicateur = new Categorieindicateur();
        unite = new Unite();
        periodicite = new Periodicite();
        niveaucollecte = new Niveaucollecte();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void resetParents() {

    }

    public void prepareCreate() {
        mode = "Create";
        viewCreateButton = false;
        this.init();
        indicateur.setCoef(100d);
        indicateur.setBaseline(0d);
        indicateur.setModeleacquis("-");
        indicateur.setModeleprobleme("-");
    }

    public void prepareView() {
        viewCreateButton = true;
        prepareEdit();
        viewCreateButton = true;
    }

    public void prepareEdit() {
        mode = "Edit";
        viewCreateButton = false;
        try {
            if (indicateur != null) {
                if (indicateur.getIdcategorie() != null) {
                    categorieindicateur = indicateur.getIdcategorie();
                }

                if (indicateur.getIdperiodicite() != null) {
                    periodicite = indicateur.getIdperiodicite();
                }

                if (indicateur.getIdniveaucollecte() != null) {
                    niveaucollecte = indicateur.getIdniveaucollecte();
                }

                if (indicateur.getIdinterventionpnds() != null) {
                    interventionpnds = indicateur.getIdinterventionpnds();
                }

                if (indicateur.getIdunite() != null) {
                    unite = indicateur.getIdunite();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save() {
        try {
            if (categorieindicateur.getIdcategorie() != null) {
                categorieindicateur = categorieindicateurFacadeLocal.find(categorieindicateur.getIdcategorie());
                indicateur.setIdcategorie(categorieindicateur);
            }

            if (unite.getIdunite() != null) {
                unite = uniteFacadeLocal.find(unite.getIdunite());
                indicateur.setIdunite(unite);
            }

            if (niveaucollecte.getIdniveaucollecte() != null) {
                niveaucollecte = niveaucollecteFacadeLocal.find(niveaucollecte.getIdniveaucollecte());
                indicateur.setIdniveaucollecte(niveaucollecte);
            }

            if (periodicite.getIdperiodicite() != null) {
                periodicite = periodiciteFacadeLocal.find(periodicite.getIdperiodicite());
                indicateur.setIdperiodicite(periodicite);
            }

            if (interventionpnds.getIdinterventionpnds() != null) {
                interventionpnds = interventionpndsFacadeLocal.find(interventionpnds.getIdinterventionpnds());
                indicateur.setIdinterventionpnds(interventionpnds);
            }

            if ("Create".equals(mode)) {
                indicateur.setIdindicateur(indicateurFacadeLocal.nextId());
                indicateurFacadeLocal.create(indicateur);
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement de l'indicateur  -> " + indicateur.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
            } else {
                if (indicateur != null) {
                    Indicateur f = indicateurFacadeLocal.find(indicateur.getIdindicateur());
                    indicateurFacadeLocal.edit(indicateur);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Modification de l'indicateur ->  " + f.getNomFr() + " -> " + indicateur.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    detail = true;
                    JsfUtil.addSuccessMessage("L'indicateur a été mis à jour");
                } else {
                    JsfUtil.addErrorMessage("Aucune selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        try {
            Map parameter = new HashMap();
            Printer p = new Printer();
            if (SessionMBean.getLangue() != "en") {
                p.print("/report/listeindicateur_en/liste_des_indicateurs.jasper", parameter);
            } else {
                p.print("/report/listeindicateur_fr/liste_des_indicateurs.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (indicateur.getIdindicateur() != null) {
                if (indicateur.getIndicateurDistrictList().isEmpty()) {
                    if (indicateur.getDocumentList().isEmpty()) {

                        indicateurFacadeLocal.remove(indicateur);
                        mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                        mouchard.setAction("Suppression de l'indicateur -> " + indicateur.getNomFr());
                        mouchard.setIdutilisateur(SessionMBean.getUser());
                        mouchard.setDateaction(new Date());
                        mouchardFacadeLocal.create(mouchard);
                        detail = true;
                        JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

                    } else {
                        JsfUtil.addErrorMessage("Cet est indicateur apparait dans plusieurs document");
                    }
                } else {
                    JsfUtil.addErrorMessage("Cet est indicateur est deja evalué et ne peut etre supprimé");
                }
            } else {
                JsfUtil.addSuccessMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getIndicateurs();
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

    public Indicateur getSelected() {
        return selected;
    }

    public void setSelected(Indicateur selected) {
        this.selected = selected;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public List<Indicateur> getIndicateurs() {
        return indicateurs;
    }

    public void setIndicateurs(List<Indicateur> indicateurs) {
        this.indicateurs = indicateurs;
    }

    public List<Periodicite> getPeriodicites() {
        periodicites = periodiciteFacadeLocal.findAllRange();
        return periodicites;
    }

    public void setPeriodicites(List<Periodicite> periodicites) {
        this.periodicites = periodicites;
    }

    public List<Categorieindicateur> getCategorieindicateurs() {
        categorieindicateurs = categorieindicateurFacadeLocal.findAll();
        return categorieindicateurs;
    }

    public void setCategorieindicateurs(List<Categorieindicateur> categorieindicateurs) {
        this.categorieindicateurs = categorieindicateurs;
    }

    public List<Unite> getUnites() {
        unites = uniteFacadeLocal.findAll();
        return unites;
    }

    public void setUnites(List<Unite> unites) {
        this.unites = unites;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public Categorieindicateur getCategorieindicateur() {
        return categorieindicateur;
    }

    public void setCategorieindicateur(Categorieindicateur categorieindicateur) {
        this.categorieindicateur = categorieindicateur;
    }

    public Niveaucollecte getNiveaucollecte() {
        return niveaucollecte;
    }

    public void setNiveaucollecte(Niveaucollecte niveaucollecte) {
        this.niveaucollecte = niveaucollecte;
    }

    public List<Niveaucollecte> getNiveaucollectes() {
        niveaucollectes = niveaucollecteFacadeLocal.findAll();
        return niveaucollectes;
    }

    public void setNiveaucollectes(List<Niveaucollecte> niveaucollectes) {
        this.niveaucollectes = niveaucollectes;
    }

    public Interventionpnds getInterventionpnds() {
        return interventionpnds;
    }

    public void setInterventionpnds(Interventionpnds interventionpnds) {
        this.interventionpnds = interventionpnds;
    }

    public List<Interventionpnds> getInterventionpndss() {
        interventionpndss = interventionpndsFacadeLocal.findAllRange();
        return interventionpndss;
    }

    public void setInterventionpndss(List<Interventionpnds> interventionpndss) {
        this.interventionpndss = interventionpndss;
    }

    public Boolean getViewCreateButton() {
        return viewCreateButton;
    }

    public void setViewCreateButton(Boolean viewCreateButton) {
        this.viewCreateButton = viewCreateButton;
    }

}
