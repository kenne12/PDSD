/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Acteur;
import entities.Acteursfacteurs;
import entities.District;
import entities.Facteur;
import entities.Opportunite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ActeurFacadeLocal;
import sessions.ActeursfacteursFacadeLocal;
import sessions.DistrictFacadeLocal;
import sessions.FacteurFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.OpportuniteFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class OpportuniteController {

    /**
     * Creates a new instance of OpportuniteController
     */
    @EJB
    private OpportuniteFacadeLocal opportuniteFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Opportunite opportunite;
    private List<Opportunite> opportunites = new ArrayList<>();
    private Boolean detail = true;

    @EJB
    private ActeursfacteursFacadeLocal acteursfacteursFacadeLocal;
    private Acteursfacteurs acteursfacteurs = new Acteursfacteurs();
    private List<Acteursfacteurs> acteursfacteurss = new ArrayList<>();

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();

    @EJB
    private FacteurFacadeLocal facteurFacadeLocal;
    private Facteur facteur;
    private List<Facteur> facteurs = new ArrayList<>();

    @EJB
    private ActeurFacadeLocal acteurFacadeLocal;
    private Acteur acteur;
    private List<Acteur> acteurs = new ArrayList<>();

    private String mode = "";

    private boolean showActeur = false;
    private boolean showFacteur = false;

    public OpportuniteController() {

    }

    @PostConstruct
    private void init() {
        opportunite = new Opportunite();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {

        try {

        } catch (Exception e) {
        }
        opportunite = new Opportunite();
        mode = "Create";

        try {
            //if (opportunite.getIdacteursfacteurs() != null) {
                //if (opportunite.getIdacteursfacteurs().getIdacteursfacteurs().equals(1)) {
                    //Acteur
                    showActeur = true;
                    showFacteur = false;
                    acteurs = acteurFacadeLocal.findAll();
               // } else if (opportunite.getIdacteursfacteurs().getIdacteursfacteurs().equals(2)) {
                    //Facteur
                    showActeur = false;
                    showFacteur = true;
                    facteurs = facteurFacadeLocal.findAll();

               // } else {
                    showActeur = false;
                    showFacteur = false;
                //}
           // } else {
                showActeur = false;
                showFacteur = false;

           // }
        } catch (Exception e) {
            showActeur = false;
            showFacteur = false;

        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void updateActeurfacteur() {

        try {
           // if (opportunite.getIdacteursfacteurs().getIdacteursfacteurs() != null) {
               // if (opportunite.getIdacteursfacteurs().getIdacteursfacteurs().equals(1)) {
                    //Acteur
                    showActeur = true;
                    showFacteur = false;
                    acteurs = acteurFacadeLocal.findAll();
               // } else if (opportunite.getIdacteursfacteurs().getIdacteursfacteurs().equals(2)) {
                    //Facteur
                    showActeur = false;
                    showFacteur = true;
                    facteurs = facteurFacadeLocal.findAll();

                //} else {
                    showActeur = false;
                    showFacteur = false;
                //}
           // } else {
                showActeur = false;
                showFacteur = false;

            //}
        } catch (Exception e) {
            showActeur = false;
            showFacteur = false;

        }
    }

    public void uptadeTable() {
        try {
            opportunites.clear();
            /*if (opportunite != null) {
                if (!this.getActeursfacteurss().isEmpty()) {

                    if (opportuniteFacadeLocal.find(opportunite, acteursfacteurs, district).isEmpty()) {
                        for (Acteursfacteurs p : this.getActeursfacteurss()) {
                            List<Opportunite> temp = opportuniteFacadeLocal.find(opportunite, p, district);
                            if (temp.isEmpty()) {
                                Opportunite opportunite = new Opportunite();
                                opportunite.setIdacteursfacteurs(p);
                                opportunite.setIdopportunite(Integer.MIN_VALUE);
                                opportunite.setNom(mode);
                                opportunites.add(opportunite);
                            } else {
                                opportunites.add(temp.get(0));
                            }
                        }
                    }
                } else {
                    System.err.println("aucune opportunité");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionnner une ligne");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOpportunite() {
        try {

            /*if (mode == "Create") {
                opportunite.setIdopportunite(opportuniteFacadeLocal.nextId());
                List<Opportunite> test = opportuniteFacadeLocal.findByNom(opportunite.getNom());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    opportunite.setIddistrict(SessionMBean.getDistrict());
                    opportuniteFacadeLocal.create(opportunite);                  
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' opportunite -> " + opportunite.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un opportunite portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Opportunite test = opportuniteFacadeLocal.find(opportunite.getIdopportunite());
                utilitaire.Utilitaires.saveOperation("Modification du opportunite -> " + test.getNom() + " par -> " + opportunite.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                opportuniteFacadeLocal.edit(opportunite);
                JsfUtil.addSuccessMessage("Opération réussie");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOpportunite() {
        try {
            /*if (opportunite != null) {

                opportuniteFacadeLocal.remove(opportunite);
                Utilitaires.saveOperation("Suppression du opportunite -> " + opportunite.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun opportunite selectionné !");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findOpportunite(Opportunite opportunite, Acteursfacteurs acteursfacteurs, District district) {
        String resultat = "";
        try {

            /*if (opportunite != null) {
                if (acteursfacteurs != null) {

                    List<Opportunite> opportunites = opportuniteFacadeLocal.find(opportunite, acteursfacteurs, district);
                    if (!opportunites.isEmpty()) {
                        resultat = "" + opportunites.get(0).getNom();
                    } else {
                        resultat = "0";
                    }
                }
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public Opportunite getOpportunite() {
        return opportunite;
    }

    public void setOpportunite(Opportunite opportunite) {
        this.opportunite = opportunite;
    }

    public List<Opportunite> getOpportunites() {
         try {
            opportunites = opportuniteFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opportunites;
    }

    public void setOpportunites(List<Opportunite> opportunites) {
        this.opportunites = opportunites;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Acteursfacteurs getActeursfacteurs() {
        return acteursfacteurs;
    }

    public void setActeursfacteurs(Acteursfacteurs acteursfacteurs) {
        this.acteursfacteurs = acteursfacteurs;
    }

    public List<Acteursfacteurs> getActeursfacteurss() {
         acteursfacteurss = acteursfacteursFacadeLocal.findAll();
              return acteursfacteurss;
    }

    public void setActeursfacteurss(List<Acteursfacteurs> acteursfacteurss) {
        this.acteursfacteurss = acteursfacteurss;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        districts = districtFacadeLocal.findAll();
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Facteur getFacteur() {
        return facteur;
    }

    public void setFacteur(Facteur facteur) {
        this.facteur = facteur;
    }

    public List<Facteur> getFacteurs() {
        facteurs = facteurFacadeLocal.findAll();
        return facteurs;
    }

    public void setFacteurs(List<Facteur> facteurs) {
        this.facteurs = facteurs;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public List<Acteur> getActeurs() {
        acteurs = acteurFacadeLocal.findAll();
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
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

}
