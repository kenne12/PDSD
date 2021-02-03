/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Facteur;
import entities.Groupefacteur;
import entities.Typefacteur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.FacteurFacadeLocal;
import sessions.GroupefacteurFacadeLocal;
import sessions.TypefacteurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class FacteurController {

    /**
     * Creates a new instance of FacteurController
     */
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    @EJB
    private FacteurFacadeLocal facteurFacadeLocal;
    private Facteur facteur;
    private List<Facteur> facteurs = new ArrayList<>();
    private Boolean detail = true;

    @EJB
    private TypefacteurFacadeLocal typefacteurFacadeLocal;
    private Typefacteur typefacteur;
    private List<Typefacteur> typefacteurs = new ArrayList<>();

    @EJB
    private GroupefacteurFacadeLocal groupefacteurFacadeLocal;
    private Groupefacteur groupefacteur = new Groupefacteur();
    private List<Groupefacteur> groupefacteurs = new ArrayList<>();

    private String mode = "";

    private DualListModel<Facteur> dualList = new DualListModel<>();

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();

    public FacteurController() {

    }

    @PostConstruct
    private void init() {
        facteur = new Facteur();


    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        facteur = new Facteur();
        dualList = new DualListModel<>();
        facteurs.clear();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void uptadeTable() {
        try {
            facteurs.clear();
            if (groupefacteur != null) {
                if (!this.getTypefacteurs().isEmpty()) {

                    if (facteurFacadeLocal.find(groupefacteur, typefacteur).isEmpty()) {
                        for (Typefacteur p : this.getTypefacteurs()) {
                            List<Facteur> temp = facteurFacadeLocal.find(facteur, p);
                            if (temp.isEmpty()) {
                                Facteur facteur = new Facteur();
                                facteur.setIdtypefacteur(p);
                                facteur.setIdgroupefacteur(groupefacteur);
                                //facteur.setNom(mode);
                                facteurs.add(facteur);
                            } else {
                                facteurs.add(temp.get(0));
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFacteur() {
        try {

            if (mode == "Create") {
                facteur.setIdfacteur(facteurFacadeLocal.nextId());
                List<Facteur> test = facteurFacadeLocal.findByNom(facteur.getNomFr());

                if (test.isEmpty()) {
                    facteurFacadeLocal.create(facteur);
                    utilitaire.Utilitaires.saveOperation("Enregistrement du facteur -> " + facteur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un facteur portant de nom existe dejà");
                }
            } else {
                Facteur test = facteurFacadeLocal.find(facteur.getIdfacteur());
                utilitaire.Utilitaires.saveOperation("Modification du facteur -> " + test.getNomFr() + " par -> " + facteur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                facteurFacadeLocal.edit(facteur);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFacteur() {
        try {
            if (facteur != null) {

                facteurFacadeLocal.remove(facteur);
                Utilitaires.saveOperation("Suppression du facteur -> " + facteur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findFacteur(Groupefacteur groupefacteur, Typefacteur typefacteur) {
        String resultat = "";
        try {

            if (groupefacteur != null) {
                if (typefacteur != null) {

                    List<Facteur> facteurs = facteurFacadeLocal.find(groupefacteur, typefacteur);
                    if (!facteurs.isEmpty()) {
                        resultat = "" + facteurs.get(0).getNomFr();
                    } else {
                        resultat = "";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public Facteur getFacteur() {
        return facteur;
    }

    public void setFacteur(Facteur facteur) {
        this.facteur = facteur;
    }

    public List<Facteur> getFacteurs() {
      facteurs =  facteurFacadeLocal.findAll();
        return facteurs;
    }

    public void setFacteurs(List<Facteur> facteurs) {
        this.facteurs = facteurs;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Typefacteur getTypefacteur() {
        return typefacteur;
    }

    public void setTypefacteur(Typefacteur typefacteur) {
        this.typefacteur = typefacteur;
    }

    public List<Typefacteur> getTypefacteurs() {
        typefacteurs = typefacteurFacadeLocal.findAll();
        return typefacteurs;
    }

    public void setTypefacteurs(List<Typefacteur> typefacteurs) {
        this.typefacteurs = typefacteurs;
    }

    public Groupefacteur getGroupefacteur() {
        return groupefacteur;
    }

    public void setGroupefacteur(Groupefacteur groupefacteur) {
        this.groupefacteur = groupefacteur;
    }

    public List<Groupefacteur> getGroupefacteurs() {
        groupefacteurs = groupefacteurFacadeLocal.findAll();
        return groupefacteurs;
    }

    public void setGroupefacteurs(List<Groupefacteur> groupefacteurs) {
        this.groupefacteurs = groupefacteurs;
    }

    public DualListModel<Facteur> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Facteur> dualList) {
        this.dualList = dualList;
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

}
