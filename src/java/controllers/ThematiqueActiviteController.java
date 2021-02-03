/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Activite;

import entities.Mouchard;
import entities.Thematique;
import entities.ThematiqueActivite;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import sessions.ActiviteFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ThematiqueActiviteFacadeLocal;
import sessions.ThematiqueFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author Simo
 */
@ManagedBean
@ViewScoped
public class ThematiqueActiviteController {

    @EJB
    private ThematiqueActiviteFacadeLocal thematiqueActviteFacadeLocal;
    private ThematiqueActivite activitethem;
    private ThematiqueActivite selectedActivitethem;
    private List<ThematiqueActivite> activitethems;
    private DualListModel<ThematiqueActivite> activitethemdual = new DualListModel<>();

    @EJB
    private ThematiqueFacadeLocal thematiqueFacadeLocal;
    private Thematique thematique;
    private List<Thematique> testthematique = new ArrayList<>();
    private List<Thematique> thematiques = new ArrayList<>();

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;
    private Activite activite;
    private List<Activite> activiteTest = new ArrayList<>();
    private List<Activite> activiteSource = new ArrayList<Activite>();
    private List<Activite> activiteTarget = new ArrayList<>();
    private DualListModel<Activite> activitedualiste = new DualListModel<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    @PostConstruct
    private void init() {
        activite = new Activite();
        activitethem = new ThematiqueActivite();
        thematique = new Thematique();
        mouchard = new Mouchard();
        selectedActivitethem = new ThematiqueActivite();

    }

    public ThematiqueActiviteController() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        activitedualiste = new DualListModel<>();
        thematique = new Thematique();
        activitethem = new ThematiqueActivite();
        try {
            activitedualiste.setSource(activiteFacadeLocal.findByDistrict(SessionMBean.getDistrict()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.filterGroupeActivite();
    }

    public void prepareRetrait() {
        try {
            activitethemdual.setSource(thematiqueActviteFacadeLocal.findByDistrictThematique(SessionMBean.getDistrict(), thematique));
            thematique = new Thematique();
            activiteSource.clear();
            activitethemdual.getSource().clear();
            activitethemdual = new DualListModel<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.filterthmatiqueActiviteRetrait();
    }

    public void prepareEdit() {
        this.filterGroupeActivite();
    }

    public void filterGroupeActivite() {
        try {
            activiteTest.clear();
            activiteSource.clear();
            activitedualiste.getSource().clear();
            activitedualiste.getTarget().clear();

            if (thematique != null) {

                List<ThematiqueActivite> themactics = thematiqueActviteFacadeLocal.findByDistrictThematique(SessionMBean.getDistrict(), thematique);

                if (themactics.isEmpty()) {
                    activitedualiste.setSource(activiteFacadeLocal.findByDistrict(SessionMBean.getDistrict()));
                } else {

                    for (ThematiqueActivite t : themactics) {
                        activiteTest.add(t.getIdactivite());
                    }

                    List<Activite> acts = activiteFacadeLocal.findByDistrict(SessionMBean.getDistrict());

                    for (Activite a : acts) {
                        if (!activiteTest.contains(a)) {
                            activitedualiste.getSource().add(a);
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterthmatiqueActiviteRetrait() {
        try {
            activitethemdual.getSource().clear();
            if (thematique.getIdthematique() != null) {
                List<ThematiqueActivite> tests = thematiqueActviteFacadeLocal.findByDistrictThematique(SessionMBean.getDistrict(), thematique);
                if (!tests.isEmpty()) {
                    activitethemdual.setSource(tests);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savethematique() {
        try {
            if (thematique != null) {
                if (!activitedualiste.getTarget().isEmpty()) {
                    for (Activite a : activitedualiste.getTarget()) {
                        ThematiqueActivite t = new ThematiqueActivite();
                        t.setIdthematiqueActivite(thematiqueActviteFacadeLocal.nextId());
                        t.setIdthematique(thematique);
                        t.setIdactivite(a);
                        t.setEtat(true);
                        t.setIddistrict(SessionMBean.getDistrict().getIddistrict());
                        thematiqueActviteFacadeLocal.create(t);
                    }
                    JsfUtil.addSuccessMessage("Opération éffectuée avec succès !");
                } else {
                    JsfUtil.addErrorMessage("La liste des Activités est vide !");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune Thématique selectionné !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retraitActivite() {
        try {

            for (ThematiqueActivite t : activitethemdual.getTarget()) {
                thematiqueActviteFacadeLocal.remove(t);
            }
            getActivitethems();
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editActivite() {
        try {

            if (selectedActivitethem != null) {
                thematiqueActviteFacadeLocal.edit(selectedActivitethem);
                JsfUtil.addSuccessMessage("Opération réussie");

            } else {
                JsfUtil.addErrorMessage("veuillez selectionner une Activité");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteActivite() {
        try {

            if (selectedActivitethem.getIdthematiqueActivite() != null) {
                thematiqueActviteFacadeLocal.remove(selectedActivitethem);
                JsfUtil.addSuccessMessage("Opération réussie");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ThematiqueActivite getActivitethem() {
        return activitethem;
    }

    public void setActivitethem(ThematiqueActivite activitethem) {
        this.activitethem = activitethem;
    }

    public List<ThematiqueActivite> getActivitethems() {
        try {
            activitethems = thematiqueActviteFacadeLocal.findByDistrict(SessionMBean.getDistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activitethems;
    }

    public void setActivitethems(List<ThematiqueActivite> activitethems) {
        this.activitethems = activitethems;
    }

    public DualListModel<ThematiqueActivite> getActivitethemdual() {
        return activitethemdual;
    }

    public void setActivitethemdual(DualListModel<ThematiqueActivite> activitethemdual) {
        this.activitethemdual = activitethemdual;
    }

    public Thematique getThematique() {
        return thematique;
    }

    public void setThematique(Thematique thematique) {
        this.thematique = thematique;
    }

    public List<Thematique> getThematiques() {
        thematiques = thematiqueFacadeLocal.findAll();
        return thematiques;
    }

    public void setThematiques(List<Thematique> thematiques) {
        this.thematiques = thematiques;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public DualListModel<Activite> getActivitedualiste() {
        return activitedualiste;
    }

    public void setActivitedualiste(DualListModel<Activite> activitedualiste) {
        this.activitedualiste = activitedualiste;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public ThematiqueActivite getSelectedActivitethem() {
        return selectedActivitethem;
    }

    public void setSelectedActivitethem(ThematiqueActivite selectedActivitethem) {
        this.selectedActivitethem = selectedActivitethem;
    }

    public List<Activite> getActiviteTest() {
        return activiteTest;
    }

    public void setActiviteTest(List<Activite> activiteTest) {
        this.activiteTest = activiteTest;
    }

    public List<Activite> getActiviteSource() {
        return activiteSource;
    }

    public void setActiviteSource(List<Activite> activiteSource) {
        this.activiteSource = activiteSource;
    }

    public List<Activite> getActiviteTarget() {
        return activiteTarget;
    }

    public void setActiviteTarget(List<Activite> activiteTarget) {
        this.activiteTarget = activiteTarget;
    }

}
