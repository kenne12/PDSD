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
import entities.Cible;
import entities.District;
import entities.IndicateurDistrict;
import entities.Probleme;
import entities.Region;
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
import sessions.CibleFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class CibleController {

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
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;
    private Activite activite = new Activite();

    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;
    private IndicateurDistrict indicateurDistrict;
    private List<IndicateurDistrict> indicateurDistricts = new ArrayList<>();

    private District district = new District();
    
    private Region region = new Region();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public CibleController() {
    }

    @PostConstruct
    private void init() {
        axes = axeFacadeLocal.findAllRangeByCode();
        district = SessionMBean.getDistrict();

        try {
            district = SessionMBean.getDistrict();            
            if (!axes.isEmpty()) {

                axe = axes.get(0);
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    indicateurDistricts = indicateurDistrictFacadeLocal.findByDistrictSousaxe(district, sousaxe);                    
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
                    indicateurDistricts = indicateurDistrictFacadeLocal.findByDistrictSousaxe(district, sousaxe);
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
                    indicateurDistricts = indicateurDistrictFacadeLocal.findByDistrictSousaxe(district, sousaxe);
                }
            } else {
                sousaxe = new Sousaxe();
                indicateurDistricts.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cible findResult(IndicateurDistrict indicateurDistrict, Annee annee) {
        Cible cible = new Cible();
        try {
            List<Cible> results = cibleFacadeLocal.find(indicateurDistrict, annee);
            if (!results.isEmpty()) {
                cible = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cible;
    }

    public void create() {
        try {
            if (!cibles.isEmpty()) {
                for (Cible c : cibles) {
                    if (c.getIdcible() == null) {
                        c.setIdcible(cibleFacadeLocal.nextId());
                        cibleFacadeLocal.create(c);
                    } else {
                        cibleFacadeLocal.edit(c);
                    }
                }
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
                                    indicateurDistricts = indicateurDistrictFacadeLocal.findByDistrictSousaxe(district, sousaxe);                                    
                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    indicateurDistricts.clear();
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
                                    indicateurDistricts = indicateurDistrictFacadeLocal.findByDistrictSousaxe(district, sousaxe);                                    
                                    break;
                                } else {
                                    indicateurDistricts.clear();
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
            cibles.clear();
            activite = new Activite();

            if (indicateurDistrict != null) {
                if (!this.getAnnees().isEmpty()) {

                    if (cibleFacadeLocal.find(indicateurDistrict).isEmpty()) {

                        for (Annee a : this.getAnnees()) {
                            Cible c = new Cible();
                            c.setIdannee(a);
                            c.setIddistrict(district);
                            c.setIdindicateur(indicateurDistrict.getIdindicateur());
                            cibles.add(c);
                        }
                    } else {

                        for (Annee a : this.getAnnees()) {
                            List<Cible> temp = cibleFacadeLocal.find(indicateurDistrict, a);
                            if (temp.isEmpty()) {
                                Cible c = new Cible();
                                c.setIdannee(a);
                                c.setIddistrict(district);
                                c.setIdindicateur(indicateurDistrict.getIdindicateur());
                                cibles.add(c);

                            } else {
                                cibles.add(temp.get(0));
                            }
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

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findByEtatCibles(true);
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

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public IndicateurDistrict getIndicateurDistrict() {
        return indicateurDistrict;
    }

    public void setIndicateurDistrict(IndicateurDistrict indicateurDistrict) {
        this.indicateurDistrict = indicateurDistrict;
    }

    public List<IndicateurDistrict> getIndicateurDistricts() {
        return indicateurDistricts;
    }

    public void setIndicateurDistricts(List<IndicateurDistrict> indicateurDistricts) {
        this.indicateurDistricts = indicateurDistricts;
    }

}
