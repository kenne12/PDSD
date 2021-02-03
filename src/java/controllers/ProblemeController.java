/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Axe;
import entities.District;
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
import sessions.AxeFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.NotationproblemeFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ProblemeController {
    
    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();
    
    @EJB
    private ProblemeFacadeLocal problemeFacadeLocal;
    private Probleme probleme = new Probleme();
    private List<Probleme> problemes = new ArrayList<>();
    
    @EJB
    private NotationproblemeFacadeLocal notationproblemeFacadeLocal;
    
    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();
    
    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();
    
    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;
    private IndicateurDistrict indicateurDistrict = new IndicateurDistrict();
    
    private District district = new District();
    
    private Double pourcentageAxe = 0d;
    private Double pourcentageSousAxe = 0d;
    
    private boolean detail = true;
    
    private boolean showIndicateur = false;
    
    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public ProblemeController() {
    }
    
    @PostConstruct
    private void init() {
        axes = axeFacadeLocal.findAllRangeByCode();
        district = SessionMBean.getDistrict();
        probleme = new Probleme();
        
        try {
            district = SessionMBean.getDistrict();
            if (!axes.isEmpty()) {
                
                axe = axes.get(0);
                
                List<IndicateurDistrict> ids = indicateurDistrictFacadeLocal.findByDistrictAxeObservation(district, axe, 2);
                if (ids.isEmpty()) {
                    pourcentageAxe = 0d;
                } else {
                    Integer conteur = 0;
                    for (IndicateurDistrict i : ids) {
                        List<Probleme> pbs = problemeFacadeLocal.find(i);
                        if (!pbs.isEmpty()) {
                            conteur += 1;
                        }
                    }
                    if (conteur != 0) {
                        pourcentageAxe = (conteur.doubleValue() / ids.size()) * 100;
                    } else {
                        pourcentageAxe = 0d;
                    }
                }
                
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                
                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                    
                    List<IndicateurDistrict> ids1 = indicateurDistrictFacadeLocal.findByDistrictSousaxeObservation(district, sousaxe, 2);
                    
                    if (!ids1.isEmpty()) {
                        if (!problemes.isEmpty()) {
                            Integer compteur1 = problemes.size();
                            pourcentageSousAxe = (compteur1.doubleValue() / ids1.size()) * 100;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateValues() {
        try {
            if (indicateur.getIdindicateur() != null) {
                indicateur = indicateurFacadeLocal.find(indicateur.getIdindicateur());
                List<IndicateurDistrict> temp = indicateurDistrictFacadeLocal.findByIndicateur(indicateur, district);
                if (temp.isEmpty()) {
                    JsfUtil.addErrorMessage("La valeur de l'indicateur pour ce district n'est pas encore renseignée");
                    indicateurDistrict = new IndicateurDistrict();
                } else {
                    indicateurDistrict = temp.get(0);
                }
            } else {
                indicateur = new Indicateur();
                indicateurDistrict = new IndicateurDistrict();
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
        mode = "Create";
        showIndicateur = false;
        
        indicateur = new Indicateur();
        indicateurDistrict = new IndicateurDistrict();
        probleme = new Probleme();
        probleme.setCause("-");
        indicateurs.clear();
        try {
            indicateurs = indicateurFacadeLocal.findBySousAxeNiveauCollecte(sousaxe, 2);
            if (!indicateurs.isEmpty()) {
                List<Indicateur> temp = new ArrayList<>();
                for (Indicateur i : indicateurs) {
                    List<IndicateurDistrict> indicateurDistricts = indicateurDistrictFacadeLocal.findByIndicateur(i, district);
                    if (!indicateurDistricts.isEmpty()) {
                        if (indicateurDistricts.get(0).getIdobservation().getIdobservation().equals(2)) {
                            temp.add(indicateurDistricts.get(0).getIdindicateur());
                        }
                    }
                }
                indicateurs = temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        mode = "Edit";
        showIndicateur = true;
        try {
            indicateurs = indicateurFacadeLocal.findBySousAxeNiveauCollecte(sousaxe, 2);
            if (probleme != null) {
                indicateur = probleme.getIdindicateurDistrict().getIdindicateur();
                indicateurDistrict = probleme.getIdindicateurDistrict();
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
                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
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
                pourcentageSousAxe = 0d;
                if (sousaxe != null) {
                    
                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                    
                    List<IndicateurDistrict> ids1 = indicateurDistrictFacadeLocal.findByDistrictSousaxeObservation(district, sousaxe, 2);
                    
                    if (!ids1.isEmpty()) {
                        
                        Integer conteur = 0;
                        for (IndicateurDistrict id : ids1) {
                            List<Probleme> pbs = problemeFacadeLocal.find(id);
                            if (!pbs.isEmpty()) {
                                conteur += 1;
                            }
                        }
                        if (conteur != 0) {
                            pourcentageSousAxe = (conteur.doubleValue() / ids1.size()) * 100;
                        } else {
                            pourcentageSousAxe = 0d;
                        }
                    }
                }
            } else {
                sousaxe = new Sousaxe();
                problemes.clear();
                pourcentageSousAxe = 0d;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create() {
        try {
            if ("Edit".equals(mode)) {

                //on est en mode ajout
                if (probleme != null) {
                    problemeFacadeLocal.edit(probleme);
                    
                    List<Probleme> temps = problemeFacadeLocal.find(indicateurDistrict);
                    String chaine = "";
                    String chaine1 = "";
                    int i = 0;
                    for (Probleme p : temps) {
                        if (i == 0) {
                            chaine += " - " + p.getNom();
                            chaine1 += " - " + p.getCause();
                        } else {
                            chaine += "\n - " + p.getNom();
                            chaine1 += "\n - " + p.getCause();
                        }
                        i++;
                    }
                    indicateurDistrict.setProbleme(chaine);
                    indicateurDistrict.setCause(chaine1);
                    indicateurDistrictFacadeLocal.edit(indicateurDistrict);
                    
                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
                
            } else {
                //on est en mode ajout
                if (indicateurDistrict != null) {
                    probleme.setIdprobleme(problemeFacadeLocal.nextId());
                    probleme.setIdindicateurDistrict(indicateurDistrict);
                    probleme.setTotalpoint(0d);
                    
                    if (indicateurDistrict.getIdobservation().getIdobservation().equals(1)) {
                        probleme.setFaible(false);
                        problemeFacadeLocal.create(probleme);
                    } else {
                        probleme.setFaible(true);
                        problemeFacadeLocal.create(probleme);
                    }
                    
                    List<Probleme> temps = problemeFacadeLocal.find(indicateurDistrict);
                    String chaine = "";
                    String chaine1 = "";
                    int i = 0;
                    for (Probleme p : temps) {
                        if (i == 0) {
                            chaine += " - " + p.getNom();
                            chaine1 += " - " + p.getCause();
                        } else {
                            chaine += "\n - " + p.getNom();
                            chaine1 += "\n - " + p.getCause();
                        }
                        i++;
                    }
                    indicateurDistrict.setCause(chaine1);
                    indicateurDistrict.setProbleme(chaine);
                    indicateurDistrictFacadeLocal.edit(indicateurDistrict);
                }
                problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        try {
            if (probleme != null) {
                if (notationproblemeFacadeLocal.find(probleme).isEmpty()) {
                    problemeFacadeLocal.remove(probleme);
                    
                    indicateurDistrict = probleme.getIdindicateurDistrict();
                    List<Probleme> temps = problemeFacadeLocal.find(indicateurDistrict);
                    
                    String chaine = "";
                    String chaine1 = "";
                    int i = 0;
                    for (Probleme p : temps) {
                        if (i == 0) {
                            chaine += " - " + p.getNom();
                            chaine1 += " - " + p.getCause();
                        } else {
                            chaine += "\n - " + p.getNom();
                            chaine1 += "\n - " + p.getCause();
                        }
                        i++;
                    }
                    indicateurDistrict.setCause(chaine1);
                    indicateurDistrict.setProbleme(chaine);
                    indicateurDistrictFacadeLocal.edit(indicateurDistrict);
                    
                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Ce probleme contient les données de priroisation et ne peut etre supprimé");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune problème selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nextAxe() {
        try {
            pourcentageAxe = 0d;
            pourcentageSousAxe = 0d;
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
                                
                                List<IndicateurDistrict> ids = indicateurDistrictFacadeLocal.findByDistrictAxeObservation(district, axe, 2);
                                if (ids.isEmpty()) {
                                    pourcentageAxe = 0d;
                                } else {
                                    Integer conteur = 0;
                                    for (IndicateurDistrict id : ids) {
                                        List<Probleme> pbs = problemeFacadeLocal.find(id);
                                        if (!pbs.isEmpty()) {
                                            conteur += 1;
                                        }
                                    }
                                    if (conteur != 0) {
                                        pourcentageAxe = (conteur.doubleValue() / ids.size()) * 100;
                                    } else {
                                        pourcentageAxe = 0d;
                                    }
                                }
                                
                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                                
                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                                    
                                    List<IndicateurDistrict> ids1 = indicateurDistrictFacadeLocal.findByDistrictSousaxeObservation(district, sousaxe, 2);
                                    
                                    if (!ids1.isEmpty()) {
                                        
                                        Integer conteur = 0;
                                        for (IndicateurDistrict id : ids1) {
                                            List<Probleme> pbs = problemeFacadeLocal.find(id);
                                            if (!pbs.isEmpty()) {
                                                conteur += 1;
                                            }
                                        }
                                        if (conteur != 0) {
                                            pourcentageSousAxe = (conteur.doubleValue() / ids1.size()) * 100;
                                        } else {
                                            pourcentageSousAxe = 0d;
                                        }
                                    }
                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    problemes.clear();
                                    
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
            pourcentageAxe = 0d;
            pourcentageSousAxe = 0d;
            if (!axes.isEmpty()) {
                if (axes.size() > 1) {
                    int i = 0;
                    for (Axe a : axes) {
                        if (a.equals(axe)) {
                            if (i == 0) {
                                break;
                            } else {
                                axe = axes.get(i - 1);
                                
                                List<IndicateurDistrict> ids = indicateurDistrictFacadeLocal.findByDistrictAxeObservation(district, axe, 2);
                                if (ids.isEmpty()) {
                                    pourcentageAxe = 0d;
                                } else {
                                    Integer conteur = 0;
                                    for (IndicateurDistrict id : ids) {
                                        List<Probleme> pbs = problemeFacadeLocal.find(id);
                                        if (!pbs.isEmpty()) {
                                            conteur += 1;
                                        }
                                    }
                                    if (conteur != 0) {
                                        pourcentageAxe = (conteur.doubleValue() / ids.size()) * 100;
                                    } else {
                                        pourcentageAxe = 0d;
                                    }
                                }
                                
                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    problemes = problemeFacadeLocal.find(district, sousaxe, 2);
                                    
                                    List<IndicateurDistrict> ids1 = indicateurDistrictFacadeLocal.findByDistrictSousaxeObservation(district, sousaxe, 2);
                                    
                                    if (!ids1.isEmpty()) {
                                        
                                        Integer conteur = 0;
                                        for (IndicateurDistrict id : ids1) {
                                            List<Probleme> pbs = problemeFacadeLocal.find(id);
                                            if (!pbs.isEmpty()) {
                                                conteur += 1;
                                            }
                                        }
                                        if (conteur != 0) {
                                            pourcentageSousAxe = (conteur.doubleValue() / ids1.size()) * 100;
                                        } else {
                                            pourcentageSousAxe = 0d;
                                        }
                                    }
                                    
                                    break;
                                } else {
                                    problemes.clear();
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
    
    public String findBackColor(Probleme probleme) {
        String color = "";
        try {
            if (probleme.getIdindicateurDistrict().getIdindicateur().getInverse()) {
                if (probleme.getIdindicateurDistrict().getValeur() <= probleme.getIdindicateurDistrict().getIdindicateur().getCiblenationale()) {
                    color = "blue";
                } else {
                    color = "red";
                }
            } else {
                if (probleme.getIdindicateurDistrict().getValeur() <= probleme.getIdindicateurDistrict().getIdindicateur().getCiblenationale()) {
                    color = "red";
                } else {
                    color = "blue";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }
    
    public String findColor(Probleme probleme) {
        String color = "";
        try {
            if (probleme.getIdindicateurDistrict().getIdindicateur().getInverse()) {
                if (probleme.getIdindicateurDistrict().getValeur() <= probleme.getIdindicateurDistrict().getIdindicateur().getCiblenationale()) {
                    color = "white";
                } else {
                    color = "yellow";
                }
            } else {
                if (probleme.getIdindicateurDistrict().getValeur() <= probleme.getIdindicateurDistrict().getIdindicateur().getCiblenationale()) {
                    color = "white";
                } else {
                    color = "white";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
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
    
    public District getDistrict() {
        return district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
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
    
    public IndicateurDistrict getIndicateurDistrict() {
        return indicateurDistrict;
    }
    
    public void setIndicateurDistrict(IndicateurDistrict indicateurDistrict) {
        this.indicateurDistrict = indicateurDistrict;
    }
    
    public Double getPourcentageAxe() {
        return pourcentageAxe;
    }
    
    public void setPourcentageAxe(Double pourcentageAxe) {
        this.pourcentageAxe = pourcentageAxe;
    }
    
    public Double getPourcentageSousAxe() {
        return pourcentageSousAxe;
    }
    
    public void setPourcentageSousAxe(Double pourcentageSousAxe) {
        this.pourcentageSousAxe = pourcentageSousAxe;
    }
    
}
