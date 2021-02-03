/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Activite;
import entities.ActiviteDefault;
import entities.ActiviteElementCout;
import entities.ActiviteStructure;
import entities.Annee;
import entities.Axe;
import entities.CoastingDefault;
import entities.District;
import entities.ElementCout;
import entities.Indicateur;
import entities.IndicateurDistrict;
import entities.ObjectifOppDistrict;
import entities.Probleme;
import entities.ResultatAttenduDistrict;
import entities.Sourcefinancement;
import entities.Sousaxe;
import entities.Structure;
import entities.Typeactivite;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ActiviteDefaultFacadeLocal;
import sessions.ActiviteElementCoutFacadeLocal;
import sessions.ActiviteFacadeLocal;
import sessions.ActiviteStructureFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.ChronogrammeFacadeLocal;
import sessions.CoastingDefaultFacadeLocal;
import sessions.ElementCoutFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.ObjectifOppDistrictFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.ResultatAttenduDistrictFacadeLocal;
import sessions.SourcefinancementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypeactiviteFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ActivitecorrectriceController {

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;
    private Activite activite = new Activite();
    private List<Activite> activites = new ArrayList<>();

    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();

    @EJB
    private ActiviteStructureFacadeLocal activiteStructureFacadeLocal;

    @EJB
    private StructureFacadeLocal structureFacadeLocal;

    @EJB
    private TypeactiviteFacadeLocal typeactiviteFacadeLocal;
    private Typeactivite typeactivite = new Typeactivite();
    private List<Typeactivite> typeactivites = new ArrayList<>();

    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure = new Typestructure();
    private List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    private SourcefinancementFacadeLocal sourcefinancementFacadeLocal;
    private Sourcefinancement sourcefinancement = new Sourcefinancement();
    private List<Sourcefinancement> sourcefinancements = new ArrayList<>();

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

    @EJB
    private ResultatAttenduDistrictFacadeLocal resultatAttenduDistrictFacadeLocal;
    private ResultatAttenduDistrict resultatAttenduDistrict = new ResultatAttenduDistrict();
    private List<ResultatAttenduDistrict> resultatAttenduDistricts = new ArrayList<>();

    @EJB
    private ChronogrammeFacadeLocal chronogrammeFacadeLocal;

    @EJB
    private ObjectifOppDistrictFacadeLocal objectifOppDistrictFacadeLocal;
    private ObjectifOppDistrict objectifOppDistrict = new ObjectifOppDistrict();
    private List<ObjectifOppDistrict> objectifOppDistricts = new ArrayList<>();

    @EJB
    private ActiviteDefaultFacadeLocal activiteDefaultFacadeLocal;
    private ActiviteDefault activiteDefault = new ActiviteDefault();
    private List<ActiviteDefault> activiteDefaults = new ArrayList<>();

    @EJB
    private ElementCoutFacadeLocal elementCoutFacadeLocal;
    private ElementCout elementCout = new ElementCout();
    private List<ElementCout> elementCouts = new ArrayList<>();

    @EJB
    private ActiviteElementCoutFacadeLocal activiteElementCoutFacadeLocal;
    private ActiviteElementCout activiteElementCout = new ActiviteElementCout();
    private List<ActiviteElementCout> activiteElementCouts = new ArrayList<>();

    @EJB
    private CoastingDefaultFacadeLocal coastingDefaultFacadeLocal;
    private CoastingDefault coastingDefault = new CoastingDefault();
    private List<CoastingDefault> coastingDefaults = new ArrayList<>();

    private Double total = 0d;

    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;

    private District district = new District();

    private boolean detail = true;

    private boolean showTypestructure = false;

    private boolean showIndicateur = false;
    private boolean showProbleme = false;

    private Double pourcentageAxe = 0d;
    private Double pourcentageSousAxe = 0d;

    private boolean showSelectActivite = true;
    private boolean showSelector = true;

    private boolean isCoasted = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public ActivitecorrectriceController() {
    }

    @PostConstruct
    private void init() {
        annee = new Annee();
        axes = axeFacadeLocal.findAllRangeByCode();
        district = SessionMBean.getDistrict();

        try {
            district = SessionMBean.getDistrict();
            if (!axes.isEmpty()) {

                axe = axes.get(0);

                List<Probleme> pbs = problemeFacadeLocal.findByAxeDistrict(axe, SessionMBean.getDistrict(), 2);
                if (pbs.isEmpty()) {
                    pourcentageAxe = 0d;
                } else {
                    Integer conteur = 0;
                    for (Probleme p : pbs) {

                        List<Activite> act = activiteFacadeLocal.findByProbleme(p);
                        if (!act.isEmpty()) {
                            conteur += 1;
                        }
                    }
                    if (conteur != 0) {
                        pourcentageAxe = (conteur.doubleValue() / pbs.size()) * 100;
                    } else {
                        pourcentageAxe = 0d;
                    }
                }

                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);

                    List<Probleme> pbs1 = problemeFacadeLocal.findBySousAxeDistrict(sousaxe, SessionMBean.getDistrict(), 2);
                    if (!pbs1.isEmpty()) {
                        if (!activites.isEmpty()) {
                            Integer compteur1 = activites.size();
                            pourcentageSousAxe = (compteur1.doubleValue() / pbs1.size()) * 100;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProbleme() {
        try {
            problemes.clear();

            if (indicateur.getIdindicateur() != null) {

                indicateur = indicateurFacadeLocal.find(indicateur.getIdindicateur());

                activiteDefaults = activiteDefaultFacadeLocal.findByIndicateur(indicateur);

                probleme = new Probleme();
                if (sousaxe != null) {
                    problemes = problemeFacadeLocal.find(indicateur, district, 2);

                    if (problemes.isEmpty()) {
                        //JsfUtil.addErrorMessage("Cet indicateur n'est pas evalué faible");
                    }
                }

                resultatAttenduDistricts = resultatAttenduDistrictFacadeLocal.findByIndicateur(indicateur, SessionMBean.getDistrict());
                if (resultatAttenduDistricts.isEmpty()) {
                    JsfUtil.addErrorMessage("Cet indicateur ne comporte pas de resultat attendu");
                }

                objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict(), indicateur.getIdinterventionpnds());
                if (objectifOppDistricts.isEmpty()) {
                    JsfUtil.addErrorMessage("Cet indicateur ne comporte pas d'objectif operationnel");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            if (probleme.getIdprobleme() != null) {
                probleme = problemeFacadeLocal.find(probleme.getIdprobleme());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCoasting() {
        try {
            total = 0d;
            for (ActiviteElementCout a : activiteElementCouts) {
                total += (a.getCoutunitaire() * a.getNbreJr() * a.getQte());
            }
            activiteDefault.setCoutUnitaire(total);
            if (typestructure.getIdtypestructure() != null) {
                activiteDefault.setCoutUnitaire(total);
            } else {
                int size = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure).size();
                if (size != 0) {
                    total = (total * size);
                }
            }
            activite.setCoutglobal(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelCosting() {
        activiteElementCouts.clear();
        isCoasted = false;
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
        showProbleme = false;
        showTypestructure = false;
        sourcefinancement = new Sourcefinancement();
        typeactivite = new Typeactivite();
        typestructure = new Typestructure();
        activite = new Activite();
        indicateur = new Indicateur();
        probleme = new Probleme();
        resultatAttenduDistrict = new ResultatAttenduDistrict();
        objectifOppDistrict = new ObjectifOppDistrict();
        resultatAttenduDistricts.clear();
        objectifOppDistricts.clear();
        showSelector = true;
        showSelectActivite = true;
        activiteDefault = new ActiviteDefault();
        activiteDefaults.clear();
        activiteDefaults.clear();
        activiteElementCouts.clear();
        isCoasted = false;
        try {

            indicateurs.clear();

            List<Indicateur> indicateurs = indicateurFacadeLocal.findBySousAxeNiveauCollecte(sousaxe, 2);
            if (!indicateurs.isEmpty()) {
                for (Indicateur i : indicateurs) {
                    List<IndicateurDistrict> ids = indicateurDistrictFacadeLocal.findByDistrictIndicateurObservation(district, i, 2);
                    if (!ids.isEmpty()) {
                        this.indicateurs.add(i);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        showIndicateur = true;
        showProbleme = true;
        showTypestructure = true;
        showSelector = false;
        showSelectActivite = false;
        activiteElementCouts.clear();
        isCoasted = false;
        this.uptadeTable();
    }

    public void updateOther() {
        try {
            if (activiteDefault.getIdactiviteDefault() != null) {

                activiteDefault = activiteDefaultFacadeLocal.find(activiteDefault.getIdactiviteDefault());
                if ("fr".equals(SessionMBean.getLangue())) {
                    activite.setNom(activiteDefault.getNomFr());
                } else {
                    activite.setNom(activiteDefault.getNomEn());
                }
                activite.setCoutunitaire(activiteDefault.getCoutUnitaire());

                if (activiteDefault.getIdsourcefi() != null) {
                    sourcefinancement = activiteDefault.getIdsourcefi();
                }

                if (activiteDefault.getIdtypestructure() != null) {
                    typestructure = activiteDefault.getIdtypestructure();
                }
                this.updateCoutglobal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSelect() {

    }

    public void updateAll() {
        try {
            if (axe != null) {
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
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
                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);

                    List<Probleme> pbs = problemeFacadeLocal.findBySousAxeDistrict(sousaxe, SessionMBean.getDistrict(), 2);

                    if (!pbs.isEmpty()) {

                        Integer conteur = 0;
                        for (Probleme pb : pbs) {
                            List<Activite> acts = activiteFacadeLocal.findByProbleme(pb);
                            if (!acts.isEmpty()) {
                                conteur += 1;
                            }
                        }
                        if (conteur != 0) {
                            pourcentageSousAxe = (conteur.doubleValue() / pbs.size()) * 100;
                        } else {
                            pourcentageSousAxe = 0d;
                        }
                    }
                }
            } else {
                sousaxe = new Sousaxe();
                activites.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Activite findResult(Probleme probleme) {
        Activite activite = new Activite();
        try {
            List<Activite> activites = activiteFacadeLocal.findByProbleme(probleme);
            if (!activites.isEmpty()) {
                activite = activites.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activite;
    }

    public void prepareCoasting() {
        try {
            if ("Create".equals(mode)) {
                if (!isCoasted) {

                    if (activiteDefault != null) {
                        activiteElementCouts.clear();
                        List<CoastingDefault> coastingDefaults = coastingDefaultFacadeLocal.findByActivite(activiteDefault);
                        if (!coastingDefaults.isEmpty()) {
                            for (CoastingDefault c : coastingDefaults) {
                                ActiviteElementCout a = new ActiviteElementCout();
                                a.setCoutunitaire(c.getCoutunitaire());
                                a.setIdelementcout(c.getIdelementcout());
                                a.setNbreJr(c.getNbreJr().doubleValue());
                                a.setQte(c.getQte().doubleValue());
                                activiteElementCouts.add(a);
                            }
                        } else {
                            activiteElementCouts.clear();
                            elementCouts = elementCoutFacadeLocal.findAll();
                            if (!elementCouts.isEmpty()) {
                                for (ElementCout e : elementCouts) {
                                    ActiviteElementCout a = new ActiviteElementCout();
                                    a.setCoutunitaire(e.getDefaultCu());
                                    a.setIdelementcout(e);
                                    a.setNbreJr(e.getDefaultNbreJr());
                                    a.setQte(e.getDefaultQte());
                                    activiteElementCouts.add(a);
                                }
                            }
                        }
                    } else {

                        activiteElementCouts.clear();
                        elementCouts = elementCoutFacadeLocal.findAll();
                        if (!elementCouts.isEmpty()) {
                            for (ElementCout e : elementCouts) {
                                ActiviteElementCout a = new ActiviteElementCout();
                                a.setCoutunitaire(e.getDefaultCu());
                                a.setIdelementcout(e);
                                a.setNbreJr(e.getDefaultNbreJr());
                                a.setQte(e.getDefaultQte());
                                activiteElementCouts.add(a);
                            }
                        }

                    }
                }
            } else {
                if (!isCoasted) {
                    total = activite.getCoutunitaire();
                    activiteElementCouts = activiteElementCoutFacadeLocal.findByActivite(activite);
                    List<ElementCout> elementCouts = elementCoutFacadeLocal.findAll();
                    List<ElementCout> elementCouts1 = new ArrayList<>();
                    for (ActiviteElementCout a : activiteElementCouts) {
                        elementCouts1.add(a.getIdelementcout());
                    }
                    for (ElementCout e : elementCouts) {
                        if (!elementCouts1.contains(e)) {
                            ActiviteElementCout a = new ActiviteElementCout();
                            a.setCoutunitaire(e.getDefaultCu());
                            a.setIdelementcout(e);
                            a.setNbreJr(e.getDefaultNbreJr());
                            a.setIdactivite(activite);
                            a.setQte(e.getDefaultQte());
                            activiteElementCouts.add(a);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void coastActivity() {
        try {
            isCoasted = true;
            total = 0d;
            for (ActiviteElementCout a : activiteElementCouts) {
                total += (a.getCoutunitaire() * a.getNbreJr() * a.getQte());
            }
            activite.setCoutunitaire(total);
            if (typestructure.getIdtypestructure() != null) {
                activite.setCoutunitaire(total);
                int size = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure).size();
                if (size != 0) {
                    activite.setCoutglobal(size * total);
                }
            } else {
                activite.setCoutunitaire(total);
                activite.setCoutglobal(total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if ("Edit".equals(mode)) {

                activite.setIdprobleme(probleme);
                activite.setIdsourcefi(sourcefinancement);
                activite.setIdtypestructure(typestructure);

                resultatAttenduDistrict = resultatAttenduDistrictFacadeLocal.find(resultatAttenduDistrict.getIdresultatAttenduDistrict());

                activite.setIdresultatattendu(resultatAttenduDistrict);

                objectifOppDistrict = objectifOppDistrictFacadeLocal.find(objectifOppDistrict.getIdobjectifOppDistrict());
                activite.setIdidobjectifOpp(objectifOppDistrict);

                Activite activite1 = activiteFacadeLocal.find(activite.getIdactivite());

                if (activite1.getCoutunitaire() != activite.getCoutunitaire()) {
                    List<Structure> structures = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure);

                    if (!structures.isEmpty()) {
                        if (structures.size() > 1) {
                            activite.setCoutglobal(activite.getCoutunitaire() * structures.size());
                        } else {
                            activite.setCoutglobal(activite.getCoutunitaire());
                        }

                        for (ActiviteStructure a : activiteStructureFacadeLocal.find(activite)) {
                            a.setIdsourcefi(sourcefinancement);
                            a.setCout(activite.getCoutunitaire());
                            activiteStructureFacadeLocal.edit(a);
                        }
                    }
                }
                activiteFacadeLocal.edit(activite);

                JsfUtil.addSuccessMessage("Opération réussie");
                activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);

            } else {

                //on est en mode ajout
                List<Structure> structures = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure);

                if (!structures.isEmpty()) {
                    if (structures.size() > 1) {
                        activite.setCoutglobal(activite.getCoutunitaire() * structures.size());
                    } else {
                        activite.setCoutglobal(activite.getCoutunitaire());
                    }
                }

                activite.setIdactivite(activiteFacadeLocal.nextId());
                activite.setIdprobleme(probleme);
                activite.setIdsourcefi(sourcefinancement);
                activite.setIdtypestructure(typestructure);

                activite.setIdresultatattendu(resultatAttenduDistrict);
                activite.setIdidobjectifOpp(objectifOppDistrict);
                activiteFacadeLocal.create(activite);

                if (!structures.isEmpty()) {
                    List<Annee> annees = anneeFacadeLocal.findByEtatprojection(true);
                    for (Structure s : structures) {
                        for (Annee a : annees) {
                            ActiviteStructure activiteStructure = new ActiviteStructure();
                            activiteStructure.setIdactiviteStructure(activiteStructureFacadeLocal.nextId());
                            activiteStructure.setIdsourcefi(sourcefinancement);
                            activiteStructure.setIdstructure(s);
                            activiteStructure.setIdannee(a);
                            activiteStructure.setPrograme(false);
                            activiteStructure.setIdactivite(activite);
                            activiteStructure.setCout(activite.getCoutunitaire());
                            activiteStructureFacadeLocal.create(activiteStructure);
                        }
                    }
                }
                JsfUtil.addSuccessMessage("Opération réussie");
                activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjectifOppDistrict findObjectif(Activite activite) {
        ObjectifOppDistrict objectifOppDistrict = new ObjectifOppDistrict();
        try {
            List<ObjectifOppDistrict> objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict(), activite.getIdprobleme().getIdindicateurDistrict().getIdindicateur().getIdinterventionpnds());
            if (!objectifOppDistricts.isEmpty()) {
                objectifOppDistrict = objectifOppDistricts.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectifOppDistrict;
    }

    public void delete() {
        try {
            if (activite != null) {

                if (chronogrammeFacadeLocal.findByActivite(activite).isEmpty()) {
                    List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(activite);
                    if (!activiteStructures.isEmpty()) {
                        for (ActiviteStructure a : activiteStructures) {
                            activiteStructureFacadeLocal.remove(a);
                        }
                    }
                    activiteFacadeLocal.remove(activite);
                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addSuccessMessage("Cette activité est rattachée au chronogramme et ne peut etre supprimé");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune activite selectionnée");
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

                                List<Probleme> pbs = problemeFacadeLocal.findByAxeDistrict(axe, SessionMBean.getDistrict(), 2);
                                if (pbs.isEmpty()) {
                                    pourcentageAxe = 0d;
                                } else {
                                    Integer conteur = 0;
                                    for (Probleme p : pbs) {
                                        List<Activite> acts = activiteFacadeLocal.findByProbleme(p);
                                        if (!acts.isEmpty()) {
                                            conteur += 1;
                                        }
                                    }
                                    if (conteur != 0) {
                                        pourcentageAxe = (conteur.doubleValue() / pbs.size()) * 100;
                                    } else {
                                        pourcentageAxe = 0d;
                                    }
                                }

                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);

                                    List<Probleme> pbs1 = problemeFacadeLocal.findBySousAxeDistrict(sousaxe, SessionMBean.getDistrict(), 2);

                                    if (!pbs1.isEmpty()) {

                                        Integer conteur = 0;
                                        for (Probleme id : pbs1) {
                                            List<Activite> acts = activiteFacadeLocal.findByProbleme(id);
                                            if (!acts.isEmpty()) {
                                                conteur += 1;
                                            }
                                        }
                                        if (conteur != 0) {
                                            pourcentageSousAxe = (conteur.doubleValue() / pbs1.size()) * 100;
                                        } else {
                                            pourcentageSousAxe = 0d;
                                        }
                                    }

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

                                List<Probleme> pbs = problemeFacadeLocal.findByAxeDistrict(axe, SessionMBean.getDistrict(), 2);
                                if (pbs.isEmpty()) {
                                    pourcentageAxe = 0d;
                                } else {
                                    Integer conteur = 0;
                                    for (Probleme pb : pbs) {
                                        List<Activite> acts = activiteFacadeLocal.findByProbleme(pb);
                                        if (!acts.isEmpty()) {
                                            conteur += 1;
                                        }
                                    }
                                    if (conteur != 0) {
                                        pourcentageAxe = (conteur.doubleValue() / pbs.size()) * 100;
                                    } else {
                                        pourcentageAxe = 0d;
                                    }
                                }

                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe, 2);

                                    List<Probleme> pbs1 = problemeFacadeLocal.findBySousAxeDistrict(sousaxe, SessionMBean.getDistrict(), 2);

                                    if (!pbs1.isEmpty()) {

                                        Integer conteur = 0;
                                        for (Probleme pb : pbs1) {
                                            List<Activite> acts = activiteFacadeLocal.findByProbleme(pb);
                                            if (!acts.isEmpty()) {
                                                conteur += 1;
                                            }
                                        }
                                        if (conteur != 0) {
                                            pourcentageSousAxe = (conteur.doubleValue() / pbs1.size()) * 100;
                                        } else {
                                            pourcentageSousAxe = 0d;
                                        }
                                    }

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

            indicateurs = indicateurFacadeLocal.findBySousAxeNiveauCollecte(sousaxe, 2);

            if ("Edit".equals(mode)) {
                if (activite != null) {

                    if (activite.getIdsourcefi() != null) {
                        sourcefinancement = activite.getIdsourcefi();
                    }

                    if (activite.getIdtypestructure() != null) {
                        typestructure = activite.getIdtypestructure();
                    }

                    if (activite.getIdresultatattendu() != null) {
                        resultatAttenduDistrict = activite.getIdresultatattendu();
                    }

                    if (activite.getIdidobjectifOpp() != null) {
                        objectifOppDistrict = activite.getIdidobjectifOpp();
                    }

                    indicateur = activite.getIdprobleme().getIdindicateurDistrict().getIdindicateur();

                    problemes = problemeFacadeLocal.find(indicateur, district, 2);

                    resultatAttenduDistricts = resultatAttenduDistrictFacadeLocal.findByIndicateur(indicateur);

                    objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict(), indicateur.getIdinterventionpnds());

                    probleme = activite.getIdprobleme();

                } else {
                    showTypestructure = false;
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCoutglobal() {
        try {
            if (typestructure.getIdtypestructure() != null) {
                List<Structure> structures = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure);
                if (structures.isEmpty()) {
                    if (activite.getCoutunitaire() != null) {
                        activite.setCoutglobal(activite.getCoutunitaire());
                    } else {
                        activite.setCoutunitaire(0.0);
                        activite.setCoutglobal(0.0);
                    }
                } else {
                    if (activite.getCoutunitaire() != null) {
                        activite.setCoutglobal(activite.getCoutunitaire() * structures.size());
                    } else {
                        activite.setCoutglobal(0.0);
                        activite.setCoutunitaire(0.0);
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
        annees.remove(0);
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

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
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

    public Typeactivite getTypeactivite() {
        return typeactivite;
    }

    public void setTypeactivite(Typeactivite typeactivite) {
        this.typeactivite = typeactivite;
    }

    public List<Typeactivite> getTypeactivites() {
        typeactivites = typeactiviteFacadeLocal.findAllRange();
        return typeactivites;
    }

    public void setTypeactivites(List<Typeactivite> typeactivites) {
        this.typeactivites = typeactivites;
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

    public ResultatAttenduDistrict getResultatAttenduDistrict() {
        return resultatAttenduDistrict;
    }

    public void setResultatAttenduDistrict(ResultatAttenduDistrict resultatAttenduDistrict) {
        this.resultatAttenduDistrict = resultatAttenduDistrict;
    }

    public List<ResultatAttenduDistrict> getResultatAttenduDistricts() {
        return resultatAttenduDistricts;
    }

    public void setResultatAttenduDistricts(List<ResultatAttenduDistrict> resultatAttenduDistricts) {
        this.resultatAttenduDistricts = resultatAttenduDistricts;
    }

    public ObjectifOppDistrict getObjectifOppDistrict() {
        return objectifOppDistrict;
    }

    public void setObjectifOppDistrict(ObjectifOppDistrict objectifOppDistrict) {
        this.objectifOppDistrict = objectifOppDistrict;
    }

    public List<ObjectifOppDistrict> getObjectifOppDistricts() {
        return objectifOppDistricts;
    }

    public void setObjectifOppDistricts(List<ObjectifOppDistrict> objectifOppDistricts) {
        this.objectifOppDistricts = objectifOppDistricts;
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

    public boolean isShowSelectActivite() {
        return showSelectActivite;
    }

    public void setShowSelectActivite(boolean showSelectActivite) {
        this.showSelectActivite = showSelectActivite;
    }

    public boolean isShowSelector() {
        return showSelector;
    }

    public void setShowSelector(boolean showSelector) {
        this.showSelector = showSelector;
    }

    public List<ElementCout> getElementCouts() {
        return elementCouts;
    }

    public void setElementCouts(List<ElementCout> elementCouts) {
        this.elementCouts = elementCouts;
    }

    public List<ActiviteElementCout> getActiviteElementCouts() {
        return activiteElementCouts;
    }

    public void setActiviteElementCouts(List<ActiviteElementCout> activiteElementCouts) {
        this.activiteElementCouts = activiteElementCouts;
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
