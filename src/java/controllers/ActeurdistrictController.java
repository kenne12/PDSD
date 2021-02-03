/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Acteurdistrict;
import entities.Acteur;
import entities.Groupeacteur;
import entities.Mouchard;
import entities.Typeacteur;
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
import sessions.ActeurdistrictFacadeLocal;
import sessions.ActeurFacadeLocal;
import sessions.GroupeacteurFacadeLocal;
import sessions.TypeacteurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ActeurdistrictController {

    /**
     * Creates a new instance of ActeurdistrictController
     */
    @EJB
    private ActeurdistrictFacadeLocal acteurdistrictFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;
    private Acteurdistrict acteurdistrict;
    private List<Acteurdistrict> acteurdistricts = new ArrayList<>();
    private List<Acteurdistrict> acteurdistrictTest = new ArrayList<>();

    private Boolean detail = true;

    @EJB
    private GroupeacteurFacadeLocal groupeacteurFacadeLocal;
    private Groupeacteur groupeacteur = new Groupeacteur();
    private List<Groupeacteur> groupeacteurs = new ArrayList<>();

    @EJB
    private TypeacteurFacadeLocal typeacteurFacadeLocal;
    private Typeacteur typeacteur = new Typeacteur();
    private List<Typeacteur> typeacteurs = new ArrayList<>();

    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();

    @EJB
    private ActeurFacadeLocal acteurFacadeLocal;
    private Acteur acteur;
    private List<Acteur> acteurs = new ArrayList<>();

    private String mode = "";

    private DualListModel<Acteur> dualList = new DualListModel<>();

    public ActeurdistrictController() {

    }

    @PostConstruct
    private void init() {
        acteurdistrict = new Acteurdistrict();
        district = new District();
        acteur = new Acteur();
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
            district = new District();
            acteurs.clear();
            acteurdistrictTest.clear();
            handleDistrictCharge();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            acteurs = acteurFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // methode qui raffraichit la pickLKist
    public void handleDistrictCharge() {
        try {
            acteurdistrictTest.clear();

            dualList.getTarget().clear();
            List<Acteur> acteurs = acteurFacadeLocal.findAll();
            List<Acteurdistrict> acteurdistricts = acteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());

            if (acteurdistricts.isEmpty()) {
                if (!acteurs.isEmpty()) {
                    dualList.setSource(acteurs);
                }
            } else {

                dualList.getSource().clear();
                //les acteur que le district possede
                List<Acteur> acteurs1 = new ArrayList<>();

                for (Acteurdistrict a : acteurdistricts) {
                    acteurs1.add(a.getIdacteur());
                }

                for (Acteur a : acteurs) {
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
                List<Acteur> test = dualList.getTarget();
                for (int i = 0; i < test.size(); i++) {
                    Acteurdistrict test1 = new Acteurdistrict();
                    test1.setIddistrict(SessionMBean.getDistrict());
                    test1.setIdacteur(test.get(i));
                    test1.setObservation("");
                    acteurdistrictTest.add(test1);
                }
            } else {
                JsfUtil.addErrorMessage("Aucune Acteur  sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (!acteurdistrictTest.isEmpty()) {
                for (Acteurdistrict e : acteurdistrictTest) {
                    e.setIdacteurDistrict(acteurdistrictFacadeLocal.nextId());
                    acteurdistrictFacadeLocal.create(e);
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
            if (acteurdistrict.getIdacteurDistrict() != null) {
                Acteurdistrict r = acteurdistrictFacadeLocal.find(acteurdistrict.getIdacteurDistrict());
                acteurdistrictFacadeLocal.edit(acteurdistrict);
                mouchard.setAction("Modification du facteur district ->  " + r.getObservation()+ " -> " + acteurdistrict.getObservation());
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
            if (acteurdistrict != null) {
                acteurdistrictFacadeLocal.edit(acteurdistrict);
                Utilitaires.saveOperation("Modification de la valeur acteur explotation : " + acteurdistrict.getIdacteurDistrict(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage(" mis à jour reussi !");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionée ! ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    public void deleteActeurdistrict() {
        try {
            if (acteurdistrict != null) {

                acteurdistrictFacadeLocal.remove(acteurdistrict);
                Utilitaires.saveOperation("Suppression du acteurdistrict -> " + acteurdistrict.getObservation(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun acteurdistrict selectionné !");
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

    public Acteurdistrict getActeurdistrict() {
        return acteurdistrict;
    }

    public void setActeurdistrict(Acteurdistrict acteurdistrict) {
        this.acteurdistrict = acteurdistrict;
    }

    public List<Acteurdistrict> getActeurdistricts() {
       try {
            acteurdistricts = acteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurdistricts;
    }

    public void setActeurdistricts(List<Acteurdistrict> acteurdistricts) {
        this.acteurdistricts = acteurdistricts;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
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

    public DualListModel<Acteur> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Acteur> dualList) {
        this.dualList = dualList;
    }

    public List<Acteurdistrict> getActeurdistrictTest() {
        acteurdistricts = acteurdistrictFacadeLocal.findAll();
        return acteurdistrictTest;
    }

    public void setActeurdistrictTest(List<Acteurdistrict> acteurdistrictTest) {
        this.acteurdistrictTest = acteurdistrictTest;
    }

    public Groupeacteur getGroupeacteur() {
        return groupeacteur;
    }

    public void setGroupeacteur(Groupeacteur groupeacteur) {
        this.groupeacteur = groupeacteur;
    }

    public List<Groupeacteur> getGroupeacteurs() {
        groupeacteurs = groupeacteurFacadeLocal.findAll();
        return groupeacteurs;
    }

    public void setGroupeacteurs(List<Groupeacteur> groupeacteurs) {
        this.groupeacteurs = groupeacteurs;
    }

    public Typeacteur getTypeacteur() {
        return typeacteur;
    }

    public void setTypeacteur(Typeacteur typeacteur) {
        this.typeacteur = typeacteur;
    }

    public List<Typeacteur> getTypeacteurs() {
        typeacteurs = typeacteurFacadeLocal.findAll();
        return typeacteurs;
    }

    public void setTypeacteurs(List<Typeacteur> typeacteurs) {
        this.typeacteurs = typeacteurs;
    }

}
