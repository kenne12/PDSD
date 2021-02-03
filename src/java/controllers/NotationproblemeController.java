/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Axe;
import entities.Indicateur;
import entities.IndicateurDistrict;
import entities.Notationprobleme;
import entities.Probleme;
import entities.Sousaxe;
import entities.Sousrubriquenotationprobleme;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.NotationproblemeFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.SousrubriquenotationproblemeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class NotationproblemeController implements Serializable {

    @EJB
    private NotationproblemeFacadeLocal notationproblemeFacadeLocal;
    private Notationprobleme notationprobleme = new Notationprobleme();
    private List<Notationprobleme> notationproblemes = new ArrayList<>();

    @EJB
    private SousrubriquenotationproblemeFacadeLocal sousrubriquenotationproblemeFacadeLocal;
    private Sousrubriquenotationprobleme sousrubriquenotationprobleme = new Sousrubriquenotationprobleme();
    private List<Sousrubriquenotationprobleme> sousrubriquenotationproblemes = new ArrayList<>();

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
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();

    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;
    private IndicateurDistrict indicateurDistrict = new IndicateurDistrict();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public NotationproblemeController() {
    }

    @PostConstruct
    private void init() {
        annee = new Annee();
        axes = axeFacadeLocal.findAllRangeByCode();

        try {
            if (!axes.isEmpty()) {

                axe = axes.get(0);
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    problemes = problemeFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
                } else {

                }
            } else {

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
                    problemes = problemeFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
                } else {
                    sousaxe = new Sousaxe();
                    problemes.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                problemes.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {

                if (sousaxe != null) {
                    problemes = problemeFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
                }
            } else {
                sousaxe = new Sousaxe();
                problemes.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Notationprobleme findResult(Probleme probleme, Sousrubriquenotationprobleme sousrubriquenotationprobleme) {
        Notationprobleme notationprobleme = new Notationprobleme();
        try {
            List<Notationprobleme> results = notationproblemeFacadeLocal.find(probleme, sousrubriquenotationprobleme);
            if (!results.isEmpty()) {
                notationprobleme = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notationprobleme;
    }

    public void create() {
        try {
            if (!notationproblemes.isEmpty()) {
                Double totalpoint = 0d;
                Double a = 0d;
                Double b = 0d;
                Double c = 0d;
                Double d = 0d;

                for (Notationprobleme n : notationproblemes) {

                    totalpoint += n.getValeur();
                    if (n.getIdsousrubriquenotationprobleme().getIdsousrubriquenotationprobleme() == 1) {
                        a = n.getValeur();
                    } else if (n.getIdsousrubriquenotationprobleme().getIdsousrubriquenotationprobleme() == 2) {
                        b = n.getValeur();
                    } else if (n.getIdsousrubriquenotationprobleme().getIdsousrubriquenotationprobleme() == 3) {
                        c = n.getValeur();
                    } else {
                        d = n.getValeur();
                    }
                    if (n.getIdnotationprobleme() == null) {
                        n.setIdnotationprobleme(notationproblemeFacadeLocal.nextId());
                        notationproblemeFacadeLocal.create(n);
                    } else {
                        notationproblemeFacadeLocal.edit(n);
                    }
                }
                totalpoint = (a + b) * c * d;
                probleme.setTotalpoint(totalpoint);
                problemeFacadeLocal.edit(probleme);

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
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
                                    problemes = problemeFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    problemes.clear();
                                }
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
                                    problemes = problemeFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
                                } else {
                                    sousaxe = new Sousaxe();
                                    problemes.clear();
                                }
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
            notationproblemes.clear();

            if (probleme != null) {
                if (!this.getSousrubriquenotationproblemes().isEmpty()) {

                    if (notationproblemeFacadeLocal.find(probleme).isEmpty()) {
                        for (Sousrubriquenotationprobleme s : this.getSousrubriquenotationproblemes()) {
                            notationprobleme = new Notationprobleme();
                            notationprobleme.setIdprobleme(probleme);
                            notationprobleme.setIdsousrubriquenotationprobleme(s);
                            notationproblemes.add(notationprobleme);
                        }
                    } else {
                        for (Sousrubriquenotationprobleme s : this.getSousrubriquenotationproblemes()) {
                            List<Notationprobleme> temp = notationproblemeFacadeLocal.find(probleme, s);
                            if (temp.isEmpty()) {
                                notationprobleme = new Notationprobleme();
                                notationprobleme.setIdprobleme(probleme);
                                notationprobleme.setIdsousrubriquenotationprobleme(s);
                                notationproblemes.add(notationprobleme);
                            } else {
                                notationproblemes.add(temp.get(0));
                            }
                        }
                    }
                } else {
                    System.err.println("Aucune rubrique trouvée");
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

    public IndicateurDistrict getIndicateurDistrict() {
        return indicateurDistrict;
    }

    public void setIndicateurDistrict(IndicateurDistrict indicateurDistrict) {
        this.indicateurDistrict = indicateurDistrict;
    }

    public Notationprobleme getNotationprobleme() {
        return notationprobleme;
    }

    public void setNotationprobleme(Notationprobleme notationprobleme) {
        this.notationprobleme = notationprobleme;
    }

    public List<Notationprobleme> getNotationproblemes() {
        return notationproblemes;
    }

    public void setNotationproblemes(List<Notationprobleme> notationproblemes) {
        this.notationproblemes = notationproblemes;
    }

    public Sousrubriquenotationprobleme getSousrubriquenotationprobleme() {
        return sousrubriquenotationprobleme;
    }

    public void setSousrubriquenotationprobleme(Sousrubriquenotationprobleme sousrubriquenotationprobleme) {
        this.sousrubriquenotationprobleme = sousrubriquenotationprobleme;
    }

    public List<Sousrubriquenotationprobleme> getSousrubriquenotationproblemes() {
        sousrubriquenotationproblemes = sousrubriquenotationproblemeFacadeLocal.findAll();
        return sousrubriquenotationproblemes;
    }

    public void setSousrubriquenotationproblemes(List<Sousrubriquenotationprobleme> sousrubriquenotationproblemes) {
        this.sousrubriquenotationproblemes = sousrubriquenotationproblemes;
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

}
