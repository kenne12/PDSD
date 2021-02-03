/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Acteur;
import entities.Acteurdistrict;
import entities.Acteursfacteurs;
import entities.Facteur;
import entities.Facteurdistrict;
import entities.Faiblesse;
import entities.Ffom;
import entities.Force;
import entities.Menace;
import entities.Opportunite;
import entities.Pilier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ActeurFacadeLocal;
import sessions.ActeurdistrictFacadeLocal;
import sessions.ActeursfacteursFacadeLocal;
import sessions.FacteurFacadeLocal;
import sessions.FacteurdistrictFacadeLocal;
import sessions.FaiblesseFacadeLocal;
import sessions.FfomFacadeLocal;
import sessions.ForceFacadeLocal;
import sessions.MenaceFacadeLocal;
import sessions.OpportuniteFacadeLocal;
import sessions.PilierFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class FfomRhController implements Serializable {

    @EJB
    private FfomFacadeLocal ffomFacadeLocal;
    private Ffom ffom = new Ffom();

    @EJB
    PilierFacadeLocal pilierFacadeLocal;
    private Pilier pilier = new Pilier();
    private List<Pilier> piliers = new ArrayList<>();

    @EJB
    private ForceFacadeLocal forceFacadeLocal;
    private Force force = new Force();
    private List<Force> forces = new ArrayList<>();

    @EJB
    private FaiblesseFacadeLocal faiblesseFacadeLocal;
    private Faiblesse faiblesse = new Faiblesse();
    private List<Faiblesse> faiblesses = new ArrayList<>();

    @EJB
    private OpportuniteFacadeLocal opportuniteFacadeLocal;
    private Opportunite opportunite = new Opportunite();
    private List<Opportunite> opportunites = new ArrayList<>();

    @EJB
    private MenaceFacadeLocal menaceFacadeLocal;
    private Menace menace = new Menace();
    private List<Menace> menaces = new ArrayList<>();

    @EJB
    private ActeursfacteursFacadeLocal acteursfacteursFacadeLocal;
    private Acteursfacteurs acteursfacteurs = new Acteursfacteurs();
    private List<Acteursfacteurs> acteursfacteurss = new ArrayList<>();

    @EJB
    private ActeurFacadeLocal acteurFacadeLocal;
    private Acteur acteur = new Acteur();
    private List<Acteur> acteurs = new ArrayList<>();

    @EJB
    private FacteurFacadeLocal facteurFacadeLocal;
    private Facteur facteur = new Facteur();
    private List<Facteur> facteurs = new ArrayList<>();

    @EJB
    private ActeurdistrictFacadeLocal acteurdistrictFacadeLocal;

    @EJB
    private FacteurdistrictFacadeLocal facteurdistrictFacadeLocal;

    private boolean detail = true;
    private boolean showLoadButton = true;

    private boolean showActeur = false;
    private boolean showFacteur = false;

    private boolean showForce = true;
    private boolean showFaiblesse = true;
    private boolean showOpportunite = true;
    private boolean showMenace = true;

    private String mode = "";

    /**
     * Creates a new instance of StructurecommunautaireController
     */
    public FfomRhController() {

    }

    @PostConstruct
    private void init() {
        try {
            pilier = pilierFacadeLocal.find(1);
            List<Ffom> ffoms = ffomFacadeLocal.find(SessionMBean.getDistrict(), pilier);
            if (!ffoms.isEmpty()) {
                ffom = ffoms.get(0);
                showLoadButton = true;

                showForce = false;
                showFaiblesse = false;
                showOpportunite = false;
                showMenace = false;
            } else {
                showLoadButton = false;

                showForce = true;
                showFaiblesse = true;
                showOpportunite = true;
                showMenace = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    // partie faiblesse
    public void prepareCreateForce() {
        mode = "Create";
        force = new Force();
        menace = new Menace();
        opportunite = new Opportunite();
        faiblesse = new Faiblesse();
        acteur = new Acteur();
        acteursfacteurs = new Acteursfacteurs();
        facteur = new Facteur();
        showFacteur = false;
        showActeur = false;
        detail = false;
    }

    public void update() {
        try {
            acteur = new Acteur();
            facteur = new Facteur();
            menace = new Menace();
            opportunite = new Opportunite();
            faiblesse = new Faiblesse();
            force = new Force();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEditForce() {
        mode = "Edit";
        try {
            if (force.getIdfacteur() != null) {
                facteur = force.getIdfacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(2);
                showFacteur = true;
                showActeur = false;
            } else if (force.getIdacteur() != null) {
                acteur = force.getIdacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(1);
                showActeur = true;
                showFacteur = false;
            } else {
                acteursfacteurs = acteursfacteursFacadeLocal.find(3);
                showActeur = false;
                showFacteur = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateActeurFacteur() {
        try {

            if (acteursfacteurs.getIdacteursfacteurs() != null) {
                if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                    showActeur = true;
                    showFacteur = false;
                    force.setFacteur(false);
                    faiblesse.setFacteur(false);
                    opportunite.setFacteur(false);
                    menace.setFacteur(false);
                } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                    showActeur = false;
                    showFacteur = true;
                    force.setFacteur(true);
                    faiblesse.setFacteur(true);
                    opportunite.setFacteur(true);
                    menace.setFacteur(true);
                } else {
                    showActeur = false;
                    showFacteur = false;
                    force.setFacteur(false);
                    faiblesse.setFacteur(false);
                    opportunite.setFacteur(false);
                    menace.setFacteur(false);
                }
            } else {
                showActeur = false;
                showFacteur = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createForce() {
        try {
            if ("Create".equals(mode)) {

                if (ffom != null) {

                    force.setIdforce(forceFacadeLocal.nextId());
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        force.setIdacteur(acteur);
                        force.setFacteur(false);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        force.setIdfacteur(facteur);
                        force.setFacteur(true);
                    }
                    force.setIdffom(ffom);
                    forceFacadeLocal.create(force);
                    this.updateForce();
                    this.prepareCreateForce();
                }
            } else {

                if (force != null) {
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        force.setIdacteur(acteurFacadeLocal.find(acteur.getIdacteur()));
                        force.setIdfacteur(null);
                        force.setFacteur(false);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        force.setIdacteur(null);
                        force.setIdfacteur(facteurFacadeLocal.find(facteur.getIdfacteur()));
                        force.setFacteur(true);
                    } else {
                        force.setIdacteur(null);
                        force.setIdfacteur(null);
                        force.setFacteur(false);
                    }
                    forceFacadeLocal.edit(force);
                    this.updateForce();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateForce() throws Exception {

        List<Force> temp = forceFacadeLocal.findByFfom(ffom);
        String chaine = "";
        if (!temp.isEmpty()) {

            int i = 0;
            for (Force f : temp) {

                if (i == 0) {

                    if (f.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "- " + f.getIdfacteur().getNomFr() + "";
                        } else {
                            chaine += "- " + f.getIdfacteur().getNomEn() + "";
                        }
                    } else if (f.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "" + f.getIdacteur().getNomFr() + "";
                        } else {
                            chaine += "" + f.getIdacteur().getNomEn() + "";
                        }
                    } else {
                        chaine += "-";
                    }
                    chaine += "\n     - " + f.getNom();

                } else {

                    if (f.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n- " + f.getIdfacteur().getNomFr() + "";
                        } else {
                            chaine += "\n- " + f.getIdfacteur().getNomEn() + "";
                        }
                    } else if (f.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n- " + f.getIdacteur().getNomFr() + "";
                        } else {
                            chaine += "\n- " + f.getIdacteur().getNomEn() + "";
                        }
                    } else {
                        chaine += "\n - ";
                    }
                    chaine += "\n     - " + f.getNom();
                }

                i++;
            }

        }
        ffom.setForce(chaine);
        ffomFacadeLocal.edit(ffom);
    }

    public void updateFaiblesse() throws Exception {

        List<Faiblesse> temp = faiblesseFacadeLocal.findByFfom(ffom);
        String chaine = "";
        if (!temp.isEmpty()) {

            int i = 0;
            for (Faiblesse f : temp) {

                if (i == 0) {

                    if (f.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += " " + f.getIdfacteur().getNomFr() + "";
                        } else {
                            chaine += " " + f.getIdfacteur().getNomEn() + "";
                        }
                    } else if (f.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += " " + f.getIdacteur().getNomFr() + "";
                        } else {
                            chaine += " " + f.getIdacteur().getNomEn() + "";
                        }
                    } else {
                        chaine += "  ";
                    }
                    chaine += "\n      " + f.getNom();
                } else {

                    if (f.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n " + f.getIdfacteur().getNomFr() + "";
                        } else {
                            chaine += "\n " + f.getIdfacteur().getNomEn() + "";
                        }
                    } else if (f.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n " + f.getIdacteur().getNomFr() + "";
                        } else {
                            chaine += "\n " + f.getIdacteur().getNomEn() + "";
                        }
                    } else {
                        chaine += "\n - ";
                    }
                    chaine += "\n      " + f.getNom();
                }

                i++;
            }

        }
        ffom.setFaiblesse(chaine);
        ffomFacadeLocal.edit(ffom);
    }

    public void updateOpportunite() throws Exception {
        List<Opportunite> temp = opportuniteFacadeLocal.findByFfom(ffom);

        if (!temp.isEmpty()) {
            String chaine = "";
            int i = 0;
            for (Opportunite o : temp) {
                if (i == 0) {

                    if (o.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += " " + o.getIdfacteur().getNomFr() + " ";
                        } else {
                            chaine += " " + o.getIdfacteur().getNomEn() + " ";
                        }
                    } else if (o.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += " " + o.getIdacteur().getNomFr() + " ";
                        } else {
                            chaine += " " + o.getIdacteur().getNomEn() + " ";
                        }
                    } else {
                        chaine += " - ";
                    }
                    chaine += "\n     - " + o.getNom();
                } else {

                    if (o.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n " + o.getIdfacteur().getNomFr() + " ";
                        } else {
                            chaine += "\n " + o.getIdfacteur().getNomEn() + " ";
                        }
                    } else if (o.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n " + o.getIdacteur().getNomFr() + " ";
                        } else {
                            chaine += "\n " + o.getIdacteur().getNomEn() + " ";
                        }
                    } else {
                        chaine += "\n - ";
                    }
                    chaine += "\n     - " + o.getNom();
                }
                i++;
            }
            ffom.setOpportunite(chaine);
            ffomFacadeLocal.edit(ffom);
        }
    }

    public void updateMenace() throws Exception {

        List<Menace> temp = menaceFacadeLocal.findByFfom(ffom);
        String chaine = "";
        if (!temp.isEmpty()) {
            int i = 0;
            for (Menace m : temp) {

                if (i == 0) {

                    if (m.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += " " + m.getIdfacteur().getNomFr() + " ";
                        } else {
                            chaine += " " + m.getIdfacteur().getNomEn() + " ";
                        }
                    } else if (m.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += " " + m.getIdacteur().getNomFr() + " ";
                        } else {
                            chaine += " " + m.getIdacteur().getNomEn() + " ";
                        }
                    } else {
                        chaine += " - ";
                    }
                    chaine += "\n     - " + m.getNom();

                } else {

                    if (m.getIdfacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n " + m.getIdfacteur().getNomFr() + " ";
                        } else {
                            chaine += "\n " + m.getIdfacteur().getNomEn() + " ";
                        }
                    } else if (m.getIdacteur() != null) {
                        if ("fr".equals(SessionMBean.getLangue())) {
                            chaine += "\n " + m.getIdacteur().getNomFr() + " ";
                        } else {
                            chaine += "\n " + m.getIdacteur().getNomEn() + " ";
                        }
                    } else {
                        chaine += "\n - ";
                    }
                    chaine += "\n     - " + m.getNom();
                }
                i++;
            }
        }
        ffom.setMenace(chaine);
        ffomFacadeLocal.edit(ffom);
    }

    public void deleteForce() {
        try {
            if (force != null) {

                forceFacadeLocal.remove(force);
                if (ffom != null) {
                    this.updateForce();
                }
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // patie des faiblesse
    public void prepareCreateFaiblesse() {
        mode = "Create";
        faiblesse = new Faiblesse();
        showFacteur = false;
        showActeur = false;
        detail = false;
        acteur = new Acteur();
        facteur = new Facteur();
        acteursfacteurs = new Acteursfacteurs();
    }

    public void prepareEditFaiblesse() {
        mode = "Edit";
        try {
            if (faiblesse.getIdfacteur() != null) {
                facteur = faiblesse.getIdfacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(2);
                showFacteur = true;
                showActeur = false;
            } else if (faiblesse.getIdacteur() != null) {
                acteur = faiblesse.getIdacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(1);
                showFacteur = false;
                showActeur = true;
            } else {
                acteursfacteurs = acteursfacteursFacadeLocal.find(3);
                showFacteur = false;
                showActeur = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFaiblesse() {
        try {
            if ("Create".equals(mode)) {
                if (ffom != null) {

                    faiblesse.setIdfaiblesse(faiblesseFacadeLocal.nextId());
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        faiblesse.setIdacteur(acteur);
                        faiblesse.setIdfacteur(null);
                        faiblesse.setFacteur(false);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        faiblesse.setIdfacteur(facteur);
                        faiblesse.setFacteur(true);
                    } else {
                        faiblesse.setIdacteur(null);
                        faiblesse.setIdfacteur(null);
                    }

                    faiblesse.setIdffom(ffom);
                    faiblesseFacadeLocal.create(faiblesse);

                    JsfUtil.addSuccessMessage("Opération réussie");
                    this.updateFaiblesse();
                    this.prepareCreateForce();
                }
            } else {
                if (faiblesse != null) {
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        faiblesse.setIdacteur(acteurFacadeLocal.find(acteur.getIdacteur()));
                        faiblesse.setIdfacteur(null);
                        faiblesse.setFacteur(false);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        faiblesse.setIdacteur(null);
                        faiblesse.setIdfacteur(facteurFacadeLocal.find(facteur.getIdfacteur()));
                        faiblesse.setFacteur(true);
                    } else {
                        faiblesse.setIdacteur(null);
                        faiblesse.setFacteur(false);
                        faiblesse.setIdfacteur(null);
                    }
                    faiblesseFacadeLocal.edit(faiblesse);
                    this.updateFaiblesse();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
            acteur = new Acteur();
            facteur = new Facteur();
            acteursfacteurs = new Acteursfacteurs();
            showActeur = false;
            showFacteur = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFaiblesse() {
        try {
            if (faiblesse != null) {
                faiblesseFacadeLocal.remove(faiblesse);
                if (ffom != null) {
                    this.updateFaiblesse();
                }
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // patie des faiblesse
    public void prepareCreateOpportunite() {
        mode = "Create";
        opportunite = new Opportunite();
        acteur = new Acteur();
        facteur = new Facteur();
        acteursfacteurs = new Acteursfacteurs();
        showFacteur = false;
        showActeur = false;
        detail = false;
    }

    public void prepareEditOpportunite() {
        mode = "Edit";
        try {
            if (opportunite.getIdfacteur() != null) {
                facteur = opportunite.getIdfacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(2);

                showFacteur = true;
                showActeur = false;

            } else if (opportunite.getIdacteur() != null) {

                acteur = opportunite.getIdacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(1);

                showFacteur = false;
                showActeur = true;

            } else {
                acteursfacteurs = acteursfacteursFacadeLocal.find(3);
                showFacteur = false;
                showActeur = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createOpportunite() {
        try {
            if ("Create".equals(mode)) {

                if (ffom != null) {

                    opportunite.setIdopportunite(opportuniteFacadeLocal.nextId());
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        opportunite.setIdacteur(acteur);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        opportunite.setIdfacteur(facteur);
                    }
                    opportunite.setIdffom(ffom);
                    opportuniteFacadeLocal.create(opportunite);

                    this.updateOpportunite();
                    JsfUtil.addSuccessMessage("Opération réussie");
                    this.prepareCreateForce();
                }
            } else {
                if (opportunite != null) {

                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        opportunite.setIdacteur(acteurFacadeLocal.find(acteur.getIdacteur()));
                        opportunite.setIdfacteur(null);
                        opportunite.setFacteur(false);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        opportunite.setIdacteur(null);
                        opportunite.setIdfacteur(facteurFacadeLocal.find(facteur.getIdfacteur()));
                        opportunite.setFacteur(true);
                    } else {
                        opportunite.setIdacteur(null);
                        opportunite.setIdfacteur(null);
                        opportunite.setFacteur(false);
                    }
                    opportuniteFacadeLocal.edit(opportunite);
                    this.updateOpportunite();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOpportunite() {
        try {

            if (opportunite != null) {
                opportuniteFacadeLocal.remove(opportunite);
                if (ffom != null) {
                    this.updateOpportunite();
                }
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //partie menace
    public void prepareCreateMenace() {
        mode = "Create";
        menace = new Menace();
        acteur = new Acteur();
        facteur = new Facteur();
        acteursfacteurs = new Acteursfacteurs();
        showFacteur = false;
        showActeur = false;
        detail = false;
    }

    public void prepareEditMenace() {
        mode = "Edit";
        try {
            if (menace.getIdfacteur() != null) {
                facteur = menace.getIdfacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(2);
                showFacteur = true;
                showActeur = false;
            } else if (menace.getIdacteur() != null) {
                acteur = menace.getIdacteur();
                acteursfacteurs = acteursfacteursFacadeLocal.find(1);
                showFacteur = false;
                showActeur = true;
            } else {
                acteursfacteurs = acteursfacteursFacadeLocal.find(3);
                showFacteur = false;
                showActeur = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMenace() {
        try {
            if ("Create".equals(mode)) {
                if (ffom != null) {

                    menace.setIdmenace(menaceFacadeLocal.nextId());
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        menace.setIdacteur(acteur);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        menace.setIdfacteur(facteur);
                    }
                    menace.setIdffom(ffom);
                    menaceFacadeLocal.create(menace);

                    this.updateMenace();
                    this.prepareCreateForce();
                }
            } else {
                if (menace != null) {
                    if (acteursfacteurs.getIdacteursfacteurs() == 1) {
                        menace.setIdacteur(acteurFacadeLocal.find(acteur.getIdacteur()));
                        menace.setIdfacteur(null);
                        menace.setFacteur(false);
                    } else if (acteursfacteurs.getIdacteursfacteurs() == 2) {
                        menace.setIdacteur(null);
                        menace.setIdfacteur(facteurFacadeLocal.find(facteur.getIdfacteur()));
                        menace.setFacteur(true);
                    } else {
                        menace.setIdacteur(null);
                        menace.setIdfacteur(null);
                        menace.setFacteur(false);
                    }
                    menaceFacadeLocal.edit(menace);
                    this.updateMenace();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMenace() {
        try {

            if (menace != null) {
                menaceFacadeLocal.remove(menace);
                if (ffom != null) {
                    this.updateMenace();
                }
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addSuccessMessage("Opération réussie");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFfom() {
        try {

            if (ffom != null) {
                if (pilier != null) {
                    ffom.setIdffom(ffomFacadeLocal.nextId());
                    ffom.setIdpilier(pilier);
                    ffom.setIddistrict(SessionMBean.getDistrict());
                    ffom.setFaiblesse("");
                    ffom.setForce("");
                    ffom.setOpportunite("");
                    ffom.setMenace("");
                    ffomFacadeLocal.create(ffom);

                    showForce = false;
                    showFaiblesse = false;
                    showOpportunite = false;
                    showMenace = false;
                    JsfUtil.addSuccessMessage("FFOM initilisé avec succès");
                }
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

    public Ffom getFfom() {
        return ffom;
    }

    public void setFfom(Ffom ffom) {
        this.ffom = ffom;
    }

    public Pilier getPilier() {
        return pilier;
    }

    public void setPilier(Pilier pilier) {
        this.pilier = pilier;
    }

    public boolean isShowLoadButton() {
        return showLoadButton;
    }

    public void setShowLoadButton(boolean showLoadButton) {
        this.showLoadButton = showLoadButton;
    }

    public Force getForce() {
        return force;
    }

    public void setForce(Force force) {
        this.force = force;
    }

    public List<Pilier> getPiliers() {
        return piliers;
    }

    public void setPiliers(List<Pilier> piliers) {
        this.piliers = piliers;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public List<Acteur> getActeurs() {
        try {
            acteurs.clear();
            List<Acteurdistrict> acteurdistricts = acteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
            if (!acteurdistricts.isEmpty()) {
                for (Acteurdistrict f : acteurdistricts) {
                    acteurs.add(f.getIdacteur());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public Acteursfacteurs getActeursfacteurs() {
        return acteursfacteurs;
    }

    public void setActeursfacteurs(Acteursfacteurs acteursfacteurs) {
        this.acteursfacteurs = acteursfacteurs;
    }

    public List<Acteursfacteurs> getActeursfacteurss() {
        acteursfacteurss = acteursfacteursFacadeLocal.findAllRange();
        return acteursfacteurss;
    }

    public void setActeursfacteurss(List<Acteursfacteurs> acteursfacteurss) {
        this.acteursfacteurss = acteursfacteurss;
    }

    public Facteur getFacteur() {
        return facteur;
    }

    public void setFacteur(Facteur facteur) {
        this.facteur = facteur;
    }

    public List<Facteur> getFacteurs() {
        try {
            facteurs.clear();
            List<Facteurdistrict> facteurdistricts = facteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
            if (!facteurdistricts.isEmpty()) {
                for (Facteurdistrict f : facteurdistricts) {
                    facteurs.add(f.getIdfacteur());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facteurs;
    }

    public void setFacteurs(List<Facteur> facteurs) {
        this.facteurs = facteurs;
    }

    public List<Force> getForces() {
        try {
            if (ffom != null) {
                forces = forceFacadeLocal.findByFfom(ffom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forces;
    }

    public void setForces(List<Force> forces) {
        this.forces = forces;
    }

    public boolean isShowActeur() {
        return showActeur;
    }

    public void setShowActeur(boolean showActeur) {
        this.showActeur = showActeur;
    }

    public boolean isShowFacteur() {
        return showFacteur;
    }

    public void setShowFacteur(boolean showFacteur) {
        this.showFacteur = showFacteur;
    }

    public Faiblesse getFaiblesse() {
        return faiblesse;
    }

    public void setFaiblesse(Faiblesse faiblesse) {
        this.faiblesse = faiblesse;
    }

    public List<Faiblesse> getFaiblesses() {
        try {
            if (ffom != null) {
                faiblesses = faiblesseFacadeLocal.findByFfom(ffom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return faiblesses;
    }

    public void setFaiblesses(List<Faiblesse> faiblesses) {
        this.faiblesses = faiblesses;
    }

    public Opportunite getOpportunite() {
        return opportunite;
    }

    public void setOpportunite(Opportunite opportunite) {
        this.opportunite = opportunite;
    }

    public List<Opportunite> getOpportunites() {
        try {
            if (ffom != null) {
                opportunites = opportuniteFacadeLocal.findByFfom(ffom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opportunites;
    }

    public void setOpportunites(List<Opportunite> opportunites) {
        this.opportunites = opportunites;
    }

    public Menace getMenace() {
        return menace;
    }

    public void setMenace(Menace menace) {
        this.menace = menace;
    }

    public List<Menace> getMenaces() {
        try {
            if (ffom != null) {
                menaces = menaceFacadeLocal.findByFfom(ffom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menaces;
    }

    public void setMenaces(List<Menace> menaces) {
        this.menaces = menaces;
    }

    public boolean isShowForce() {
        return showForce;
    }

    public boolean isShowFaiblesse() {
        return showFaiblesse;
    }

    public boolean isShowOpportunite() {
        return showOpportunite;
    }

    public boolean isShowMenace() {
        return showMenace;
    }

}
