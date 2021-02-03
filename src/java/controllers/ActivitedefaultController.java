/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.ActiviteDefault;
import entities.Axe;
import entities.CoastingDefault;
import entities.ElementCout;
import entities.Indicateur;
import entities.Interventionpnds;
import entities.Sourcefinancement;
import entities.Sousaxe;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ActiviteDefaultFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.CoastingDefaultFacadeLocal;
import sessions.ElementCoutFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.InterventionpndsFacadeLocal;
import sessions.SourcefinancementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ActivitedefaultController {
    
    @EJB
    private ActiviteDefaultFacadeLocal activiteDefaultFacadeLocal;
    private ActiviteDefault activiteDefault = new ActiviteDefault();
    private List<ActiviteDefault> activiteDefaults = new ArrayList<>();
    
    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();
    
    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure = new Typestructure();
    private List<Typestructure> typestructures = new ArrayList<>();
    
    @EJB
    private SourcefinancementFacadeLocal sourcefinancementFacadeLocal;
    private Sourcefinancement sourcefinancement = new Sourcefinancement();
    private List<Sourcefinancement> sourcefinancements = new ArrayList<>();
    
    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();
    
    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();
    
    @EJB
    private InterventionpndsFacadeLocal interventionpndsFacadeLocal;
    private Interventionpnds interventionpnds = new Interventionpnds();
    private List<Interventionpnds> interventionpndses = new ArrayList<>();
    
    @EJB
    private CoastingDefaultFacadeLocal coastingDefaultFacadeLocal;
    private CoastingDefault coastingDefault = new CoastingDefault();
    private List<CoastingDefault> coastingDefaults = new ArrayList<>();
    
    @EJB
    private ElementCoutFacadeLocal elementCoutFacadeLocal;
    private ElementCout elementCout = new ElementCout();
    private List<ElementCout> elementCouts = new ArrayList<>();
    
    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    
    private Double total = 0d;
    
    private boolean isCoasted = true;
    
    private boolean detail = true;
    
    private boolean showTypestructure = false;
    
    private boolean showIndicateur = false;
    private boolean showProbleme = false;
    
    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public ActivitedefaultController() {
        this.coastingDefaults = new ArrayList<>();
    }
    
    @PostConstruct
    private void init() {
        axes = axeFacadeLocal.findAllRangeByCode();
        try {
            if (!axes.isEmpty()) {
                
                axe = axes.get(0);
                
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                
                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    activiteDefaults = activiteDefaultFacadeLocal.findBySousAxe(sousaxe);
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
    
    public void updateIndicateur() {
        try {
            if (interventionpnds.getIdinterventionpnds() != null) {
                indicateurs = indicateurFacadeLocal.findByIntervention(interventionpnds);
            } else {
                indicateurs.clear();
                activiteDefaults.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCoasting() {
        try {
            total = 0d;
            for (CoastingDefault c : coastingDefaults) {
                total += (c.getCoutunitaire() * c.getNbreJr() * c.getQte());
            }
            activiteDefault.setCoutUnitaire(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        
    }
    
    public void prepareCreate() {
        mode = "Create";
        showIndicateur = false;
        showProbleme = false;
        showTypestructure = false;
        sourcefinancement = new Sourcefinancement();
        typestructure = new Typestructure();
        indicateur = new Indicateur();
        interventionpnds = new Interventionpnds();
        activiteDefault = new ActiviteDefault();
        activiteDefault.setCoutUnitaire(0d);
        typestructure = new Typestructure();
        sourcefinancement = new Sourcefinancement();
        coastingDefaults.clear();
        isCoasted = false;
        total = 0d;
        try {
            if (sousaxe != null) {
                interventionpndses = interventionpndsFacadeLocal.findBySousaxe(sousaxe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareCoasting() {
        try {
            if ("Create".equals(mode)) {
                if (!isCoasted) {
                    total = 0d;
                    coastingDefaults.clear();
                    elementCouts = elementCoutFacadeLocal.findAll();
                    if (!elementCouts.isEmpty()) {
                        for (ElementCout e : elementCouts) {
                            CoastingDefault d = new CoastingDefault();
                            d.setCoutunitaire(e.getDefaultCu());
                            d.setIdelementcout(e);
                            d.setNbreJr(e.getDefaultNbreJr().intValue());
                            d.setQte(e.getDefaultQte().intValue());
                            coastingDefaults.add(d);
                            total += (e.getDefaultCu() * e.getDefaultNbreJr() * e.getDefaultQte());
                        }
                    }
                }
            } else {
                coastingDefaults = coastingDefaultFacadeLocal.findByActivite(activiteDefault);
                List<ElementCout> elementCouts = elementCoutFacadeLocal.findAll();
                List<ElementCout> elementCouts1 = new ArrayList<>();
                for (CoastingDefault c : coastingDefaults) {
                    elementCouts1.add(c.getIdelementcout());
                }
                for (ElementCout e : elementCouts) {
                    if (!elementCouts1.contains(e)) {
                        CoastingDefault c = new CoastingDefault();
                        c.setCoutunitaire(e.getDefaultCu());
                        c.setIdelementcout(e);
                        c.setIdactiviteDefault(activiteDefault);
                        c.setNbreJr(e.getDefaultNbreJr().intValue());
                        c.setQte(e.getDefaultQte().intValue());
                        coastingDefaults.add(c);
                    }
                }
                total = activiteDefault.getCoutUnitaire();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void coastActivity() {
        try {
            isCoasted = true;
            total = 0d;
            for (CoastingDefault c : coastingDefaults) {
                total += (c.getCoutunitaire() * c.getNbreJr() * c.getQte());
            }
            activiteDefault.setCoutUnitaire(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cancelCosting() {
        coastingDefaults.clear();
        isCoasted = false;
    }
    
    public void prepareEdit() {
        mode = "Edit";
        showIndicateur = true;
        showProbleme = true;
        showTypestructure = true;
        coastingDefaults.clear();
        try {
            
            typestructure = new Typestructure();
            sourcefinancement = new Sourcefinancement();
            
            if (sousaxe != null) {
                interventionpndses = interventionpndsFacadeLocal.findBySousaxe(sousaxe);
            }
            
            if (activiteDefault != null) {
                interventionpnds = activiteDefault.getIdindicateur().getIdinterventionpnds();
                indicateur = activiteDefault.getIdindicateur();
                indicateurs = indicateurFacadeLocal.findByIntervention(interventionpnds);
            }
            
            if (activiteDefault.getIdsourcefi() != null) {
                sourcefinancement = activiteDefault.getIdsourcefi();
            }
            
            if (activiteDefault.getIdtypestructure() != null) {
                typestructure = activiteDefault.getIdtypestructure();
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
                    activiteDefaults = activiteDefaultFacadeLocal.findBySousAxe(sousaxe);
                } else {
                    sousaxe = new Sousaxe();
                    activiteDefaults.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                activiteDefaults.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {
                
                if (sousaxe != null) {
                    activiteDefaults = activiteDefaultFacadeLocal.findBySousAxe(sousaxe);
                }
            } else {
                sousaxe = new Sousaxe();
                activiteDefaults.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create() {
        try {
            if ("Edit".equals(mode)) {
                indicateur = indicateurFacadeLocal.find(indicateur.getIdindicateur());
                activiteDefault.setIdindicateur(indicateur);
                
                if (sourcefinancement.getIdsourcefi() != null) {
                    sourcefinancement = sourcefinancementFacadeLocal.find(sourcefinancement.getIdsourcefi());
                    activiteDefault.setIdsourcefi(sourcefinancement);
                }
                
                if (typestructure.getIdtypestructure() != null) {
                    typestructure = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                    activiteDefault.setIdtypestructure(typestructure);
                }
                
                if (isCoasted) {
                    if (!coastingDefaults.isEmpty()) {
                        for (CoastingDefault c : coastingDefaults) {
                            if (c.getIdcoastingDefault() != null) {
                                coastingDefaultFacadeLocal.edit(c);
                            } else {
                                c.setIdcoastingDefault(coastingDefaultFacadeLocal.nextId());
                                c.setIdactiviteDefault(activiteDefault);
                                coastingDefaultFacadeLocal.create(c);
                            }
                        }
                    }
                }
                isCoasted = false;
                
                activiteDefaultFacadeLocal.edit(activiteDefault);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                //on est en mode ajout
                activiteDefault.setIdactiviteDefault(activiteDefaultFacadeLocal.nextId());
                activiteDefault.setIdindicateur(indicateur);
                
                if (sourcefinancement.getIdsourcefi() != null) {
                    activiteDefault.setIdsourcefi(sourcefinancement);
                }
                
                if (typestructure.getIdtypestructure() != null) {
                    activiteDefault.setIdtypestructure(typestructure);
                }
                
                activiteDefaultFacadeLocal.create(activiteDefault);
                
                if (isCoasted) {
                    if (!coastingDefaults.isEmpty()) {
                        for (CoastingDefault c : coastingDefaults) {
                            c.setIdcoastingDefault(coastingDefaultFacadeLocal.nextId());
                            c.setIdactiviteDefault(activiteDefault);
                            coastingDefaultFacadeLocal.create(c);
                        }
                    }
                }
                isCoasted = false;
                
                JsfUtil.addSuccessMessage("Opération réussie");
            }
            activiteDefaults = activiteDefaultFacadeLocal.findBySousAxe(sousaxe);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        try {
            if (activiteDefault != null) {
                List<CoastingDefault> coastingDefaults = coastingDefaultFacadeLocal.findByActivite(activiteDefault);
                if (!coastingDefaults.isEmpty()) {
                    for (CoastingDefault c : coastingDefaults) {
                        coastingDefaultFacadeLocal.remove(c);
                    }
                }
                activiteDefaultFacadeLocal.remove(activiteDefault);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Veuillez sélectionner une ligne");
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
                                    activiteDefaults = activiteDefaultFacadeLocal.findBySousAxe(sousaxe);
                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    activiteDefaults.clear();
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
                                    
                                    activiteDefaults = activiteDefaultFacadeLocal.findBySousAxe(sousaxe);
                                    
                                    break;
                                } else {
                                    activiteDefaults.clear();
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
            
            indicateurs = indicateurFacadeLocal.findBySousAxeNiveauCollecte(sousaxe, 2);
            
            if ("Edit".equals(mode)) {
                
            } else {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCoutglobal() {
        try {
            if (activiteDefault.getCoutUnitaire() != null) {
                
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
    
    public Typestructure getTypestructure() {
        return typestructure;
    }
    
    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }
    
    public List<Typestructure> getTypestructures() {
        typestructures = typestructureFacadeLocal.findAll();
        return typestructures;
    }
    
    public void setTypestructures(List<Typestructure> typestructures) {
        this.typestructures = typestructures;
    }
    
    public Sourcefinancement getSourcefinancement() {
        return sourcefinancement;
    }
    
    public void setSourcefinancement(Sourcefinancement sourcefinancement) {
        this.sourcefinancement = sourcefinancement;
    }
    
    public List<Sourcefinancement> getSourcefinancements() {
        sourcefinancements = sourcefinancementFacadeLocal.findAll();
        return sourcefinancements;
    }
    
    public void setSourcefinancements(List<Sourcefinancement> sourcefinancements) {
        this.sourcefinancements = sourcefinancements;
    }
    
    public boolean isShowTypestructure() {
        return showTypestructure;
    }
    
    public void setShowTypestructure(boolean showTypestructure) {
        this.showTypestructure = showTypestructure;
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
    
    public boolean isShowIndicateur() {
        return showIndicateur;
    }
    
    public void setShowIndicateur(boolean showIndicateur) {
        this.showIndicateur = showIndicateur;
    }
    
    public boolean isShowProbleme() {
        return showProbleme;
    }
    
    public void setShowProbleme(boolean showProbleme) {
        this.showProbleme = showProbleme;
    }
    
    public ActiviteDefault getActiviteDefault() {
        return activiteDefault;
    }
    
    public void setActiviteDefault(ActiviteDefault activiteDefault) {
        this.activiteDefault = activiteDefault;
    }
    
    public List<ActiviteDefault> getActiviteDefaults() {
        return activiteDefaults;
    }
    
    public void setActiviteDefaults(List<ActiviteDefault> activiteDefaults) {
        this.activiteDefaults = activiteDefaults;
    }
    
    public Interventionpnds getInterventionpnds() {
        return interventionpnds;
    }
    
    public void setInterventionpnds(Interventionpnds interventionpnds) {
        this.interventionpnds = interventionpnds;
    }
    
    public List<Interventionpnds> getInterventionpndses() {
        return interventionpndses;
    }
    
    public void setInterventionpndses(List<Interventionpnds> interventionpndses) {
        this.interventionpndses = interventionpndses;
    }
    
    public List<CoastingDefault> getCoastingDefaults() {
        return coastingDefaults;
    }
    
    public void setCoastingDefaults(List<CoastingDefault> coastingDefaults) {
        this.coastingDefaults = coastingDefaults;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
}
