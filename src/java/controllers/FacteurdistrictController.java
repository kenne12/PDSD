/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Facteurdistrict;
import entities.Facteur;
import entities.Groupefacteur;
import entities.Mouchard;
import entities.Typefacteur;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.FacteurdistrictFacadeLocal;
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
public class FacteurdistrictController {

    /**
     * Creates a new instance of FacteurdistrictController
     */
    @EJB
    private FacteurdistrictFacadeLocal facteurdistrictFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private Facteurdistrict facteurdistrict;
    private List<Facteurdistrict> facteurdistricts = new ArrayList<>();
    private List<Facteurdistrict> facteurdistrictTest = new ArrayList<>();

    private Boolean detail = true;
    @EJB
    private FacteurFacadeLocal facteurFacadeLocal;
    private Facteur facteur;
    private List<Facteur> facteurs = new ArrayList<>();

    @EJB
    private TypefacteurFacadeLocal typefacteurFacadeLocal;
    private Typefacteur typefacteur;
    private List<Typefacteur> typefacteurs = new ArrayList<>();

    @EJB
    private GroupefacteurFacadeLocal groupefacteurFacadeLocal;
    private Groupefacteur groupefacteur = new Groupefacteur();
    private List<Groupefacteur> groupefacteurs = new ArrayList<>();

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    private String mode = "";

    
    private DualListModel<Facteur> dualList = new DualListModel<>();

    public FacteurdistrictController() {

    }

    @PostConstruct
    private void init() {
        facteurdistrict = new Facteurdistrict();
        facteur = new Facteur();
        groupefacteur =  new Groupefacteur();
        typefacteur =  new  Typefacteur();
        typefacteurs = typefacteurFacadeLocal.findAll();
        groupefacteurs =  groupefacteurFacadeLocal.findAll();

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        try {
            dualList = new DualListModel<>();
            facteurs.clear();
            facteurdistrictTest.clear();
            
            handleDistrictCharge();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
       mode = "Edit";
        try {
            if (facteurdistrict != null) {
                if (facteurdistrict.getIdfacteur() != null) {
                    facteur = facteurdistrict.getIdfacteur();
                }
                if (facteurdistrict.getIdfacteur().getIdgroupefacteur() != null) {
                    groupefacteur = facteurdistrict.getIdfacteur().getIdgroupefacteur();
                }

                 if (facteurdistrict.getIdfacteur().getIdtypefacteur() != null) {
                    typefacteur = facteurdistrict.getIdfacteur().getIdtypefacteur();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

          
    }

    // methode qui raffraichit la pickLKist
    public void handleDistrictCharge() {
        try {

            
            dualList.getTarget().clear();

            List<Facteur> facteurs = facteurFacadeLocal.findAll();
            List<Facteurdistrict> acteurdistricts = facteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());

            if (acteurdistricts.isEmpty()) {
                if (!facteurs.isEmpty()) {
                    dualList.setSource(facteurs);
                }
            } else {

                dualList.getSource().clear();
                //les acteur que le district possede
                List<Facteur> acteurs1 = new ArrayList<>();

                for (Facteurdistrict a : acteurdistricts) {
                    acteurs1.add(a.getIdfacteur());
                }

                for (Facteur a : facteurs) {
                    if (!acteurs1.contains(a)) {
                        dualList.getSource().add(a);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //methode qui transfert les données de la pickList vers le tableau
    public void transfertEtape() {
        try {
            if (!dualList.getTarget().isEmpty()) {
                List<Facteur> test = dualList.getTarget();
                for (int i = 0; i < test.size(); i++) {
                    Facteurdistrict test1 = new Facteurdistrict();
                    test1.setIdfacteur(test.get(i));
                    test1.setIddistrict(SessionMBean.getDistrict());
                    test1.setObservation("");
                    facteurdistrictTest.add(test1);
                }
            } else {
                JsfUtil.addErrorMessage("Aucune Facteur  sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (!facteurdistrictTest.isEmpty()) {
                for (Facteurdistrict e : facteurdistrictTest) {
                    e.setIdfacteurdistrict(facteurdistrictFacadeLocal.nextId());
                    e.setIddistrict(SessionMBean.getDistrict());
                    facteurdistrictFacadeLocal.create(e);
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      public void edit() {
        try {
            if (facteurdistrict.getIdfacteurdistrict() != null) {
                Facteurdistrict r = facteurdistrictFacadeLocal.find(facteurdistrict.getIdfacteurdistrict());
                facteurdistrictFacadeLocal.edit(facteurdistrict);
                mouchard.setAction("Modification du district ->  " + r.getObservation()+ " -> " + facteurdistrict.getObservation());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);

                JsfUtil.addSuccessMessage("Le district a été mis à jour");
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une ligne");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getDistricts();
        }
    }
   /* public void edit() {
        try {
            if (facteurdistrict != null) {
                facteurdistrictFacadeLocal.edit(facteurdistrict);
                Utilitaires.saveOperation("Modification de la valeur facteur explotation : " + facteurdistrict.getIdfacteurdistrict(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage(" mis à jour reussi !");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionée ! ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void deleteFacteurdistrict() {
        try {
            if (facteurdistrict != null) {

                facteurdistrictFacadeLocal.remove(facteurdistrict);
                Utilitaires.saveOperation("Suppression du facteurdistrict -> " + facteurdistrict.getObservation(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun facteurdistrict selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        return mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public Facteurdistrict getFacteurdistrict() {
        return facteurdistrict;
    }

    public void setFacteurdistrict(Facteurdistrict facteurdistrict) {
        this.facteurdistrict = facteurdistrict;
    }

    public List<Facteurdistrict> getFacteurdistricts() {
        try {
            facteurdistricts = facteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facteurdistricts;
    }

    public void setFacteurdistricts(List<Facteurdistrict> facteurdistricts) {
        this.facteurdistricts = facteurdistricts;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
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

    public DualListModel<Facteur> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Facteur> dualList) {
        this.dualList = dualList;
    }

    public List<Facteurdistrict> getFacteurdistrictTest() {
        facteurdistricts = facteurdistrictFacadeLocal.findAll();
        return facteurdistrictTest;
    }

    public void setFacteurdistrictTest(List<Facteurdistrict> facteurdistrictTest) {
        this.facteurdistrictTest = facteurdistrictTest;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
