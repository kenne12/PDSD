/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typestructure;
import entities.TypestrucTypeequipement;
import entities.Typeequipementtraceur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.TypestructureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.TypestrucTypeequipementFacadeLocal;
import sessions.TypeequipementtraceurFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class TypestructuretypeequipementController {

    @EJB
    private TypestrucTypeequipementFacadeLocal typestrucTypeequipementFacadeLocal;
    private TypestrucTypeequipement typestrucTypeequipement;
    private List<TypestrucTypeequipement> typestrucTypeequipements = new ArrayList<>();

    @EJB
    private TypeequipementtraceurFacadeLocal typeequipementtraceurFacadeLocal;
    private Typeequipementtraceur typeequipementtraceur;
    private List<Typeequipementtraceur> typeequipementtraceurs = new ArrayList<>();

    @EJB
    private TypestructureFacadeLocal partenaireFacadeLocal;
    private List<Typestructure> typestructure = new ArrayList<>();
    private List<Typestructure> selectedTypestructures = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of ProjetController
     */
    public TypestructuretypeequipementController() {

    }

    @PostConstruct
    private void init() {
        typestrucTypeequipement = new TypestrucTypeequipement();
        typeequipementtraceur = new Typeequipementtraceur();
    }

    public void prepareCreate() {
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void filterTypestructure() {
        typestructure.clear();
        try {
            if (typeequipementtraceur.getIdtypeequipementtraceur() != null) {
                typeequipementtraceur = typeequipementtraceurFacadeLocal.find(typeequipementtraceur.getIdtypeequipementtraceur());
                List<Typestructure> typestructureFinal = partenaireFacadeLocal.findAll();
                List<TypestrucTypeequipement> typestrucTypeequipementsTemp = typestrucTypeequipementFacadeLocal.findByTypeequipementtraceur(typeequipementtraceur);

                if (typestrucTypeequipementsTemp.isEmpty()) {
                    typestructure = partenaireFacadeLocal.findAll();
                } else {
                    List<Typestructure> typestructureTemp = new ArrayList<>();
                    for (TypestrucTypeequipement p : typestrucTypeequipementsTemp) {
                        typestructureTemp.add(p.getIdtypestructure());
                    }

                    for (Typestructure i : typestructureFinal) {
                        if (!typestructureTemp.contains(i)) {
                            typestructure.add(i);
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucun partenaire selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (mode.equals("Create")) {
                if (typeequipementtraceur.getIdtypeequipementtraceur() != null) {
                    typeequipementtraceur = typeequipementtraceurFacadeLocal.find(typeequipementtraceur.getIdtypeequipementtraceur());
                    if (!selectedTypestructures.isEmpty()) {
                        for (Typestructure p : selectedTypestructures) {
                            TypestrucTypeequipement typestrucTypeequipement = new TypestrucTypeequipement();
                            typestrucTypeequipement.setIdtypestrucTypeequipement(typestrucTypeequipementFacadeLocal.nextId());
                            typestrucTypeequipement.setIdtypestructure(p);
                            typestrucTypeequipement.setIdtypeequipementtraceur(typeequipementtraceur);
                            typestrucTypeequipementFacadeLocal.create(typestrucTypeequipement);
                            Utilitaires.saveOperation("Association de l'partenaire  -> " + p.getNomFr() + " ; A Type equipement traceur  -> " + typeequipementtraceur.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                        }
                        JsfUtil.addSuccessMessage("Opération reussie");
                    } else {
                        JsfUtil.addErrorMessage("Aucun partenaire sélectionné");
                    }
                } else {
                    JsfUtil.addErrorMessage("Aucune typeequipementtraceur sélectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (typestrucTypeequipement != null) {
                typestrucTypeequipementFacadeLocal.remove(typestrucTypeequipement);
                Utilitaires.saveOperation("Suppression de l'appartenance de l'partenaire -> " + typestrucTypeequipement.getIdtypestructure().getNomFr() + " A l'typeequipementtraceur -> " + typestrucTypeequipement.getIdtypeequipementtraceur().getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typestrucTypeequipement = new TypestrucTypeequipement();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Typestructure> getTypestructures() {
        return typestructure;
    }

    public void setTypestructures(List<Typestructure> typestructure) {
        this.typestructure = typestructure;
    }

    public List<Typestructure> getSelectedTypestructures() {
        return selectedTypestructures;
    }

    public void setSelectedTypestructures(List<Typestructure> selectedTypestructures) {
        this.selectedTypestructures = selectedTypestructures;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public TypestrucTypeequipement getTypestrucTypeequipement() {
        return typestrucTypeequipement;
    }

    public void setTypestrucTypeequipement(TypestrucTypeequipement typestrucTypeequipement) {
        this.typestrucTypeequipement = typestrucTypeequipement;
    }

    public List<TypestrucTypeequipement> getTypestrucTypeequipements() {
        typestrucTypeequipements = typestrucTypeequipementFacadeLocal.findAll();
        return typestrucTypeequipements;
    }

    public void setTypestrucTypeequipements(List<TypestrucTypeequipement> typestrucTypeequipements) {
        this.typestrucTypeequipements = typestrucTypeequipements;
    }

    public Typeequipementtraceur getTypeequipementtraceur() {
        return typeequipementtraceur;
    }

    public void setTypeequipementtraceur(Typeequipementtraceur typeequipementtraceur) {
        this.typeequipementtraceur = typeequipementtraceur;
    }

    public List<Typeequipementtraceur> getTypeequipementtraceurs() {
        typeequipementtraceurs = typeequipementtraceurFacadeLocal.findAll();
        return typeequipementtraceurs;
    }

    public void setTypeequipementtraceurs(List<Typeequipementtraceur> typeequipementtraceurs) {
        this.typeequipementtraceurs = typeequipementtraceurs;
    }

}
