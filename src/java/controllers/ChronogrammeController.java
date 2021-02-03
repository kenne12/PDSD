/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Activite;
import entities.Annee;
import entities.Axe;
import entities.Chronogramme;
import entities.Cible;
import entities.District;
import entities.Probleme;
import entities.Sousaxe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ActiviteFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.ChronogrammeFacadeLocal;
import sessions.CibleFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ChronogrammeController {

    @EJB
    private ChronogrammeFacadeLocal chronogrammeFacadeLocal;
    private Chronogramme chronogramme = new Chronogramme();
    private List<Chronogramme> chronogrammes = new ArrayList<>();

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;
    private Activite activite = new Activite();
    private List<Activite> activites = new ArrayList<>();

    @EJB
    private CibleFacadeLocal cibleFacadeLocal;
    private Cible cible = new Cible();
    private List<Cible> cibles = new ArrayList<>();

    @EJB
    private ProblemeFacadeLocal problemeFacadeLocal;
    private Probleme probleme = new Probleme();
    private List<Probleme> problemes = new ArrayList<>();

    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();

    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    private District district = new District();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public ChronogrammeController() {
    }

    @PostConstruct
    private void init() {
        annee = new Annee();
        axes = axeFacadeLocal.findAllRangeByCode();

        try {
            district = SessionMBean.getDistrict();
            if (!axes.isEmpty()) {

                axe = axes.get(0);

                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);

                    activites = activiteFacadeLocal.findBySousAxe(sousaxe, district);
                }
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

    public void updateAll() {
        try {
            if (axe != null) {
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    activites = activiteFacadeLocal.findBySousAxe(sousaxe, SessionMBean.getDistrict());
                } else {
                    sousaxe = new Sousaxe();
                    activites.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                activites.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {

                if (sousaxe != null) {
                    activites = activiteFacadeLocal.findBySousAxe(sousaxe, SessionMBean.getDistrict());
                }
            } else {
                sousaxe = new Sousaxe();
                activites.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Chronogramme findResult(Activite activite, Annee annee) {
        Chronogramme chronogramme = new Chronogramme();
        try {
            List<Chronogramme> results = chronogrammeFacadeLocal.findByActivite(activite, annee);
            if (!results.isEmpty()) {
                chronogramme = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chronogramme;
    }

    public void createChronogramme(Chronogramme chronogramme) {
        try {
            if (chronogramme.getIdchronogramme() != null) {
                if (!chronogramme.getEtat()) {
                    chronogrammeFacadeLocal.remove(chronogramme);
                    uptadeTable();
                } else {

                }
            } else {
                if (chronogramme.getEtat()) {
                    chronogramme.setIdchronogramme(chronogrammeFacadeLocal.nextId());
                    chronogramme.setEtat(true);
                    chronogrammeFacadeLocal.create(chronogramme);
                    uptadeTable();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!chronogrammes.isEmpty()) {
                for (Chronogramme c : chronogrammes) {
                    if (!c.getEtat()) {
                        if (c.getIdchronogramme() != null) {
                            chronogrammeFacadeLocal.remove(c);
                        }
                    } else {
                        if (c.getIdchronogramme() == null) {
                            c.setIdchronogramme(chronogrammeFacadeLocal.nextId());
                            c.setEtat(true);
                            chronogrammeFacadeLocal.create(c);
                        }
                    }
                }
                JsfUtil.addSuccessMessage("Operation réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextAxe() {
        try {
            if (!axes.isEmpty()) {
                if (axes.size() > 1) {
                    int i = 0;
                    for (Axe a : axes) {
                        if (a.equals(axe)) {
                            if (i <= axes.size()) {

                                if (i + 1 == axes.size()) {
                                    break;
                                }

                                axe = axes.get(i + 1);
                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    activites = activiteFacadeLocal.findBySousAxe(sousaxe, SessionMBean.getDistrict());
                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    activites.clear();
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void precAxe() {
        try {
            if (!axes.isEmpty()) {
                if (axes.size() > 1) {
                    int i = 0;
                    for (Axe a : axes) {
                        if (a.equals(axe)) {
                            if (i == 0) {
                                break;
                            } else {
                                axe = axes.get(i - 1);

                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    activites = activiteFacadeLocal.findBySousAxe(sousaxe, SessionMBean.getDistrict());
                                    break;
                                } else {
                                    activites.clear();
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {
                if (sousaxes.size() > 1) {
                    int i = 0;
                    for (Sousaxe s : sousaxes) {
                        if (s.equals(sousaxe)) {
                            if (i <= axes.size()) {

                                if (i + 1 == sousaxes.size()) {
                                    return;
                                }
                                sousaxe = sousaxes.get(i + 1);
                                this.updateSousaxe();
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void precSousAxe() {

        try {
            if (!sousaxes.isEmpty()) {
                if (sousaxes.size() > 1) {
                    int i = 0;
                    for (Sousaxe s : sousaxes) {
                        if (s.equals(sousaxe)) {
                            if (i == 0) {
                                break;
                            } else {
                                sousaxe = sousaxes.get(i - 1);
                                this.updateSousaxe();
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uptadeTable() {
        try {
            chronogrammes.clear();

            if (activite != null) {
                if (!this.getAnnees().isEmpty()) {

                    for (Annee a : getAnnees()) {
                        List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(activite, a);
                        if (chronogrammes.isEmpty()) {
                            Chronogramme chronogramme = new Chronogramme();
                            chronogramme.setEtat(false);
                            chronogramme.setIdactivite(activite);
                            chronogramme.setIdannee(a);
                            this.chronogrammes.add(chronogramme);
                        } else {
                            chronogrammes.get(0).setEtat(true);
                            this.chronogrammes.add(chronogrammes.get(0));
                        }
                    }
                } else {
                    System.err.println("Aucune annee trouvée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (activite != null) {
                List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(activite);
                if (!chronogrammes.isEmpty()) {
                    for (Chronogramme c : chronogrammes) {
                        chronogrammeFacadeLocal.remove(c);
                    }
                }
                activites = activiteFacadeLocal.findBySousAxe(sousaxe, SessionMBean.getDistrict());
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune activité selectionnée");
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

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            annees = anneeFacadeLocal.findByEtatChronogramme(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public List<Axe> getAxes() {
        return axes;
    }

    public void setAxes(List<Axe> axes) {
        this.axes = axes;
    }

    public Sousaxe getSousaxe() {
        return sousaxe;
    }

    public void setSousaxe(Sousaxe sousaxe) {
        this.sousaxe = sousaxe;
    }

    public List<Sousaxe> getSousaxes() {
        return sousaxes;
    }

    public void setSousaxes(List<Sousaxe> sousaxes) {
        this.sousaxes = sousaxes;
    }

    public Probleme getProbleme() {
        return probleme;
    }

    public void setProbleme(Probleme probleme) {
        this.probleme = probleme;
    }

    public List<Probleme> getProblemes() {
        return problemes;
    }

    public void setProblemes(List<Probleme> problemes) {
        this.problemes = problemes;
    }

    public Cible getCible() {
        return cible;
    }

    public void setCible(Cible cible) {
        this.cible = cible;
    }

    public List<Cible> getCibles() {
        return cibles;
    }

    public void setCibles(List<Cible> cibles) {
        this.cibles = cibles;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Chronogramme getChronogramme() {
        return chronogramme;
    }

    public void setChronogramme(Chronogramme chronogramme) {
        this.chronogramme = chronogramme;
    }

    public List<Chronogramme> getChronogrammes() {
        return chronogrammes;
    }

    public void setChronogrammes(List<Chronogramme> chronogrammes) {
        this.chronogrammes = chronogrammes;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

}
