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
import entities.Categorieintervention;
import entities.Indicateur;
import entities.IndicateurDistrict;
import entities.Probleme;
import entities.Sousaxe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.CategorieinterventionFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.NiveaucollecteFacadeLocal;
import sessions.ObservationFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PerformancedistrictController {
    
    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();
    
    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();
    
    @EJB
    private CategorieinterventionFacadeLocal categorieinterventionFacadeLocal;
    private Categorieintervention categorieintervention = new Categorieintervention();
    private List<Categorieintervention> categorieinterventions = new ArrayList<>();
    
    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();
    
    @EJB
    private NiveaucollecteFacadeLocal niveaucollecteFacadeLocal;
    
    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;
    private IndicateurDistrict indicateurDistrict = new IndicateurDistrict();
    
    @EJB
    private ObservationFacadeLocal observationFacadeLocal;
    
    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();
    
    @EJB
    private ProblemeFacadeLocal problemeFacadeLocal;
    private Probleme probleme = new Probleme();
    
    private boolean detail = true;
    private boolean drapeau = false;

    /**
     * Creates a new instance of SousaxeController
     */
    public PerformancedistrictController() {
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
                    
                    categorieinterventions = categorieinterventionFacadeLocal.find(sousaxe);
                    
                    if (!categorieinterventions.isEmpty()) {
                        categorieintervention = categorieinterventions.get(0);
                        indicateurs = indicateurFacadeLocal.findByCategorieIntervention(categorieintervention, niveaucollecteFacadeLocal.find(2));
                    }
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
    
    public void prepareCreate() {
        try {
            if (indicateur != null) {
                List<IndicateurDistrict> temp = indicateurDistrictFacadeLocal.findByIndicateur(indicateur, SessionMBean.getDistrict());
                annee = new Annee();
                if (temp.isEmpty()) {
                    indicateurDistrict = new IndicateurDistrict();
                    indicateurDistrict.setCause("-");
                    drapeau = false;
                    indicateurDistrict.setProbleme(indicateur.getModeleprobleme());
                } else {
                    if (temp.get(0).getIdannee() != null) {
                        annee = temp.get(0).getIdannee();
                    }
                    indicateurDistrict = temp.get(0);
                    drapeau = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateAll() {
        try {
            
            if (axe != null) {
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                
                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    
                    categorieinterventions = categorieinterventionFacadeLocal.find(sousaxe);
                    
                    if (!categorieinterventions.isEmpty()) {
                        categorieintervention = categorieinterventions.get(0);
                        indicateurs = indicateurFacadeLocal.findByCategorieIntervention(categorieintervention, niveaucollecteFacadeLocal.find(2));
                    }
                } else {
                    categorieintervention = new Categorieintervention();
                    sousaxe = new Sousaxe();
                    indicateurs.clear();
                }
            } else {
                categorieintervention = new Categorieintervention();
                sousaxe = new Sousaxe();
                indicateurs.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {
                
                if (sousaxe != null) {
                    
                    categorieinterventions = categorieinterventionFacadeLocal.find(sousaxe);
                    
                    if (!categorieinterventions.isEmpty()) {
                        categorieintervention = categorieinterventions.get(0);
                        indicateurs = indicateurFacadeLocal.findByCategorieIntervention(categorieintervention, niveaucollecteFacadeLocal.find(2));
                    } else {
                        indicateurs.clear();
                        categorieinterventions.clear();
                        categorieintervention = new Categorieintervention();
                    }
                }
            } else {
                categorieintervention = new Categorieintervention();
                sousaxe = new Sousaxe();
                indicateurs.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCategorie() {
        try {
            if (!sousaxes.isEmpty()) {
                
                if (sousaxe != null) {
                    
                    categorieinterventions = categorieinterventionFacadeLocal.find(sousaxe);
                    
                    if (!categorieinterventions.isEmpty()) {
                        categorieintervention = categorieinterventions.get(0);
                    } else {
                        indicateurs.clear();
                        categorieinterventions.clear();
                    }
                }
            } else {
                categorieintervention = new Categorieintervention();
                sousaxe = new Sousaxe();
                indicateurs.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateIndicateur() {
        try {
            
            if (categorieintervention != null) {
                indicateurs = indicateurFacadeLocal.findByCategorieIntervention(categorieintervention, niveaucollecteFacadeLocal.find(2));
            } else {
                indicateurs.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public IndicateurDistrict findResult(Indicateur indicateur) {
        IndicateurDistrict indicateurDistrict = new IndicateurDistrict();
        try {
            if (indicateur != null) {
                if (SessionMBean.getDistrict() != null) {
                    List<IndicateurDistrict> results = indicateurDistrictFacadeLocal.findByIndicateur(indicateur, SessionMBean.getDistrict());
                    if (!results.isEmpty()) {
                        indicateurDistrict = results.get(0);
                        indicateurDistrict.getValeur();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indicateurDistrict;
    }
    
    public void create() {
        try {
            if (!drapeau) {
                
                indicateurDistrict.setIdindicateurDistrict(indicateurDistrictFacadeLocal.nextVal());
                indicateurDistrict.setIdindicateur(indicateur);
                indicateurDistrict.setIddistrict(SessionMBean.getDistrict());
                
                if (annee.getIdannee() != null) {
                    indicateurDistrict.setIdannee(annee);
                }
                
                if (indicateur.getInverse()) {
                    if (indicateurDistrict.getValeur() <= indicateur.getCiblenationale()) {
                        
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(1));
                        indicateurDistrictFacadeLocal.create(indicateurDistrict);
                        
                        probleme = new Probleme();
                        probleme.setIdprobleme(problemeFacadeLocal.nextId());
                        probleme.setNom("R A S");
                        probleme.setCause("R A S");
                        probleme.setObjectif("R A S");
                        probleme.setIdindicateurDistrict(indicateurDistrict);
                        probleme.setFaible(false);
                        probleme.setTotalpoint(0d);
                        problemeFacadeLocal.create(probleme);
                        
                    } else {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(2));
                        indicateurDistrictFacadeLocal.create(indicateurDistrict);
                    }
                    
                } else {
                    if (indicateurDistrict.getValeur() < indicateur.getCiblenationale()) {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(2));
                        indicateurDistrictFacadeLocal.create(indicateurDistrict);
                    } else {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(1));
                        indicateurDistrictFacadeLocal.create(indicateurDistrict);
                        probleme = new Probleme();
                        probleme.setIdprobleme(problemeFacadeLocal.nextId());
                        probleme.setNom("R A S");
                        probleme.setCause("R A S");
                        probleme.setObjectif("R A S");
                        probleme.setIdindicateurDistrict(indicateurDistrict);
                        probleme.setFaible(false);
                        probleme.setTotalpoint(0d);
                        problemeFacadeLocal.create(probleme);
                    }
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                
                if (annee.getIdannee() != null) {
                    indicateurDistrict.setIdannee(anneeFacadeLocal.find(annee.getIdannee()));
                }
                
                if (indicateurDistrict.getIdindicateur().getInverse()) {
                    if (indicateurDistrict.getValeur() <= indicateur.getCiblenationale()) {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(1));
                    } else {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(2));
                    }
                } else {
                    if (indicateurDistrict.getValeur() < indicateur.getCiblenationale()) {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(2));
                    } else {
                        indicateurDistrict.setIdobservation(observationFacadeLocal.find(1));
                    }
                }
                indicateurDistrictFacadeLocal.edit(indicateurDistrict);
                JsfUtil.addSuccessMessage("Opération réussie");
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
                                    return;
                                }
                                axe = axes.get(i + 1);
                                this.updateAll();
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
                                this.updateAll();
                                break;
                            }
                            
                        }
                        i++;
                    }
                }
            }
            System.err.println("");
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
    
    public void nextCategorie() {
        try {
            if (!categorieinterventions.isEmpty()) {
                if (categorieinterventions.size() > 1) {
                    int i = 0;
                    for (Categorieintervention c : categorieinterventions) {
                        
                        if (c.equals(categorieintervention)) {
                            if (i <= categorieinterventions.size()) {
                                
                                if (i + 1 == categorieinterventions.size()) {
                                    return;
                                }
                                categorieintervention = categorieinterventions.get(i + 1);
                                this.updateIndicateur();
                                break;
                            }
                        }
                        i++;
                    }
                }
            } else {
                System.err.println(" non vide " + categorieinterventions.size());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void precCategorie() {
        try {
            if (!categorieinterventions.isEmpty()) {
                if (categorieinterventions.size() > 1) {
                    int i = 0;
                    for (Categorieintervention c : categorieinterventions) {
                        if (c.equals(categorieintervention)) {
                            
                            if (i == 0) {
                                categorieintervention = categorieinterventions.get(i);
                                this.updateIndicateur();
                                break;
                            } else {
                                categorieintervention = categorieinterventions.get(i - 1);
                                this.updateIndicateur();
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
    
    public Categorieintervention getCategorieintervention() {
        return categorieintervention;
    }
    
    public void setCategorieintervention(Categorieintervention categorieintervention) {
        this.categorieintervention = categorieintervention;
    }
    
    public List<Categorieintervention> getCategorieinterventions() {
        return categorieinterventions;
    }
    
    public void setCategorieinterventions(List<Categorieintervention> categorieinterventions) {
        this.categorieinterventions = categorieinterventions;
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
    
}
