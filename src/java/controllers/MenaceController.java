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
import entities.Menace;
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
import sessions.MenaceFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class MenaceController {

    /**
     * Creates a new instance of MenaceController
     */
    @EJB
    private MenaceFacadeLocal menaceFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Menace menace;
    private List<Menace> menaces = new ArrayList<>();
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

    private boolean showActeur = false;
    private boolean showFacteur = false;

    private String mode = "";

    public MenaceController() {

    }

    @PostConstruct
    private void init() {
        menace = new Menace();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        menace = new Menace();
        mode = "Create";
        
         try {
            //if (menace.getIdacteursfacteurs() != null) {
                //if (menace.getIdacteursfacteurs().getIdacteursfacteurs().equals(1)) {
                    //Acteur
                    showActeur = true;
                    showFacteur = false;   
                    acteurs = acteurFacadeLocal.findAll();
                //} else if (menace.getIdacteursfacteurs().getIdacteursfacteurs().equals(2)) {
                    //Facteur
                    showActeur = false;
                    showFacteur = true;
                    facteurs= facteurFacadeLocal.findAll();
    
          //} else {
                showActeur = false;
                showFacteur = false;
            //}
         //} else {
                 showActeur = false;
                 showFacteur = false;
              
            //}       
        } catch (Exception e) {
             showActeur = false;
             showFacteur = false;
           
        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void updateActeurfacteur(){
    
        try {
            //if (menace.getIdacteursfacteurs().getIdacteursfacteurs()!= null) {
                //if (menace.getIdacteursfacteurs().getIdacteursfacteurs().equals(1)) {
                    //Acteur
                    showActeur = true;
                    showFacteur = false;   
                    acteurs = acteurFacadeLocal.findAll();
               // } else if (menace.getIdacteursfacteurs().getIdacteursfacteurs().equals(2)) {
                    //Facteur
                    showActeur = false;
                    showFacteur = true;
                    facteurs= facteurFacadeLocal.findAll();
    
         // } else {
                showActeur = false;
                showFacteur = false;
            //}
         //} else {
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
            menaces.clear();
            /*if (menace != null) {
                if (!this.getActeursfacteurss().isEmpty()) {

                    if (menaceFacadeLocal.find(menace, acteursfacteurs, district).isEmpty()) {
                        for (Acteursfacteurs p : this.getActeursfacteurss()) {
                            List<Menace> temp = menaceFacadeLocal.find(menace, p, district);
                            if (temp.isEmpty()) {
                                Menace menace = new Menace();
                                menace.setIdacteursfacteurs(p);
                                menace.setIdmenace(Integer.MIN_VALUE);
                                menace.setNom(mode);
                                menaces.add(menace);
                            } else {
                                menaces.add(temp.get(0));
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

    public void saveMenace() {
        try {

            /*if (mode == "Create") {
                menace.setIdmenace(menaceFacadeLocal.nextId());
                List<Menace> test = menaceFacadeLocal.findByNom(menace.getNom());

                if (test.isEmpty()) {
                    System.err.println("mode : " + mode);
                    menace.setIddistrict(SessionMBean.getDistrict());
                    menaceFacadeLocal.create(menace);                  
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' menace -> " + menace.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un menace portant de nom existe dejà");
                }
            } else {
                System.err.println("mode : " + mode);
                Menace test = menaceFacadeLocal.find(menace.getIdmenace());
                utilitaire.Utilitaires.saveOperation("Modification du menace -> " + test.getNom() + " par -> " + menace.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                menaceFacadeLocal.edit(menace);
                JsfUtil.addSuccessMessage("Opération réussie");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMenace() {
        try {
            /*if (menace != null) {

                menaceFacadeLocal.remove(menace);
                Utilitaires.saveOperation("Suppression du menace -> " + menace.get, SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun menace selectionné !");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findMenace(Menace menace, Acteursfacteurs acteursfacteurs, District district) {
        String resultat = "";
        try {

            /*if (menace != null) {
                if (acteursfacteurs != null) {

                    List<Menace> menaces = menaceFacadeLocal.find(menace, acteursfacteurs, district);
                    if (!menaces.isEmpty()) {
                        resultat = "" + menaces.get(0).getNom();
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

    public Menace getMenace() {
        return menace;
    }

    public void setMenace(Menace menace) {
        this.menace = menace;
    }

    public List<Menace> getMenaces() {
       try {
            if (SessionMBean.getDistrict() != null) {
                menaces = menaceFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menaces;
    }

    public void setMenaces(List<Menace> menaces) {
        this.menaces = menaces;
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
  acteursfacteurss=acteursfacteursFacadeLocal.findAll();
       
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
        facteurs =facteurFacadeLocal.findAll();
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
        acteurs=acteurFacadeLocal.findAll();
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
