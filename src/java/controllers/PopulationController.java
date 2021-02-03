/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Commentairetab;
import entities.District;
import entities.Population;
import entities.Rubriquepopulation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.PopulationFacadeLocal;
import sessions.RubriquepopulationFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PopulationController {

    @EJB
    private PopulationFacadeLocal populationFacadeLocal;
    private List<Population> populations = new ArrayList<>();

    @EJB
    private RubriquepopulationFacadeLocal rubriquepopulationFacadeLocal;
    private List<Rubriquepopulation> rubriquepopulations = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    private District district = new District();

    Double effectif = 0.0;

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public PopulationController() {
    }

    @PostConstruct
    private void init() {
        annee = new Annee();
        try {
            district = SessionMBean.getDistrict();
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 2);
            if (!commentairetabs.isEmpty()) {
                commentairetab = commentairetabs.get(0);
                return;
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

    public void initAnnee() {
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTable() {
        try {
            if (effectif != null && effectif != 0.0) {
                if (!populations.isEmpty()) {
                    int i = 0;
                    for (Population p : populations) {
                        Double resultat = (effectif * p.getIdrubriquepopulation().getPourcentage()) / 100;
                        populations.get(i).setValeurpopulationrubrique(Utilitaires.arrondiNDecimales(resultat, 1));
                        i++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable() {
        try {

            effectif = 0D;

            populations.clear();
            District district = SessionMBean.getDistrict();
            if (annee != null) {

                if (district != null) {
                    if (!this.getRubriquepopulations().isEmpty()) {

                        if (populationFacadeLocal.find(district, annee).isEmpty()) {
                            for (Rubriquepopulation r : this.getRubriquepopulations()) {
                                Population population = new Population();
                                population.setIdannee(annee);
                                population.setIddistrict(district);
                                population.setIdrubriquepopulation(r);

                                populations.add(population);
                            }
                        } else {
                            for (Rubriquepopulation r : this.getRubriquepopulations()) {
                                List<Population> temp = populationFacadeLocal.find(district, r, annee);
                                effectif = temp.get(0).getValeurpopulationdistrict();
                                if (temp.isEmpty()) {
                                    Population population = new Population();
                                    population.setIdannee(annee);
                                    population.setIddistrict(district);
                                    population.setIdrubriquepopulation(r);

                                    populations.add(population);
                                } else {
                                    populations.add(temp.get(0));
                                }
                            }
                        }
                    } else {
                        System.err.println("Aucune rubrique de la poulation");
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez selectionnner un district");
                }
            } else {
                JsfUtil.addErrorMessage("Veillez selectionner une année");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 2);
            if (!commentairetabs.isEmpty()) {
                commentairetab = commentairetabs.get(0);
                return;
            }
            commentairetab = new Commentairetab();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!populations.isEmpty()) {
                for (Population p : populations) {
                    if (p.getIdpopulation() == null) {
                        p.setIdpopulation(populationFacadeLocal.nextId());
                        p.setValeurpopulationdistrict(effectif);
                        populationFacadeLocal.create(p);
                    } else {
                        p.setValeurpopulationdistrict(effectif);
                        populationFacadeLocal.edit(p);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 2);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(2);
                    commentairetabFacadeLocal.create(commentairetab);
                } else {
                    commentairetabFacadeLocal.edit(commentairetab);
                }
                this.updateCommentaire();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Population findPopulation(Rubriquepopulation rubriquepopulation, Annee annee) {
        Population population = null;
        try {
            if (annee.getIdannee() != null) {
                if (SessionMBean.getDistrict() != null) {
                    if (rubriquepopulation != null) {

                        List<Population> temps = populationFacadeLocal.find(SessionMBean.getDistrict(), rubriquepopulation, annee);
                        if (!temps.isEmpty()) {
                            population = temps.get(0);
                        } else {
                            population = new Population();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return population;
    }

    public Population findTotalPopulation(Annee annee) {
        Population population = null;
        try {
            if (annee.getIdannee() != null) {
                if (SessionMBean.getDistrict() != null) {

                    List<Population> temps = populationFacadeLocal.find(SessionMBean.getDistrict(), annee);
                    if (!temps.isEmpty()) {
                        population = temps.get(0);
                    } else {
                        population = new Population();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            population = new Population();
        }
        return population;
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
        annees.remove(0);
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

    public List<Population> getPopulations() {
        return populations;
    }

    public void setPopulations(List<Population> populations) {
        this.populations = populations;
    }

    public List<Rubriquepopulation> getRubriquepopulations() {
        rubriquepopulations = rubriquepopulationFacadeLocal.findAllRange();
        return rubriquepopulations;
    }

    public void setRubriquepopulations(List<Rubriquepopulation> rubriquepopulations) {
        this.rubriquepopulations = rubriquepopulations;
    }

    public Double getEffectif() {
        return effectif;
    }

    public void setEffectif(Double effectif) {
        this.effectif = effectif;
    }

    public District getDistrict() {
        return district;
    }
}
