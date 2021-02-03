/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Parametrecoutequipement;
import entities.Etatequipement;
import entities.Typeequipementtraceur;

import entities.Typestructure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import sessions.EtatequipementFacadeLocal;
import sessions.ParametrecoutequipementFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.TypeequipementtraceurFacadeLocal;
import sessions.TypestructureFacadeLocal;

import utilitaire.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@javax.faces.bean.ViewScoped
public class ParametrecoutEquipementController implements Serializable {

    @EJB
    private ParametrecoutequipementFacadeLocal parametrecoutequipementFacadeLocal;
    private Parametrecoutequipement parametrecoutequipement = new Parametrecoutequipement();
    private List<Parametrecoutequipement> parametrecoutequipements = new ArrayList<>();

    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure = new Typestructure();
    private List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    private TypeequipementtraceurFacadeLocal typeequipementtraceurFacadeLocal;
    private Typeequipementtraceur typeequipementtraceur = new Typeequipementtraceur();
    private List<Typeequipementtraceur> typeequipementtraceurs = new ArrayList<>();

    @EJB
    private EtatequipementFacadeLocal etatequipementFacadeLocal;
    private Etatequipement etatequipement = new Etatequipement();
    private List<Etatequipement> etatequipements = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of StructurecommunautaireController
     */
    public ParametrecoutEquipementController() {

    }

    @PostConstruct
    private void init() {
        parametrecoutequipement = new Parametrecoutequipement();
        typestructure = new Typestructure();
        etatequipement = new Etatequipement();
        typeequipementtraceur = new Typeequipementtraceur();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
        try {
            parametrecoutequipement = new Parametrecoutequipement();
            typestructure = new Typestructure();
            typeequipementtraceur = new Typeequipementtraceur();
            etatequipement = new Etatequipement();
            parametrecoutequipement.setCoutunitaire(0d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (parametrecoutequipement != null) {
                if (parametrecoutequipement.getIdetatequipement() != null) {
                    etatequipement = parametrecoutequipement.getIdetatequipement();
                }

                if (parametrecoutequipement.getIdtypeequipement() != null) {
                    typeequipementtraceur = parametrecoutequipement.getIdtypeequipement();
                }

                if (parametrecoutequipement.getIdtypestructure() != null) {
                    typestructure = parametrecoutequipement.getIdtypestructure();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {

                parametrecoutequipement.setIdparametrecoutequipement(parametrecoutequipementFacadeLocal.nextId());

                if (typestructure.getIdtypestructure() != null) {
                    parametrecoutequipement.setIdtypestructure(typestructure);
                }

                if (typeequipementtraceur.getIdtypeequipementtraceur() != null) {
                    parametrecoutequipement.setIdtypeequipement(typeequipementtraceur);
                }

                if (etatequipement.getIdetatequipement() != null) {
                    parametrecoutequipement.setIdetatequipement(etatequipement);
                }

                parametrecoutequipementFacadeLocal.create(parametrecoutequipement);
                Utilitaires.saveOperation("Parametrage cout des infrastructure  : -> Type de structure : " + parametrecoutequipement.getIdtypestructure().getNomFr() + " ; Type d'équipement : " + typeequipementtraceur.getNomFr()+ " ; Cout unitaire : " + parametrecoutequipement.getCoutunitaire(), SessionMBean.getUser(), mouchardFacadeLocal);
                parametrecoutequipement = new Parametrecoutequipement();

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (parametrecoutequipement != null) {

                    if (typestructure.getIdtypestructure() != null) {
                        parametrecoutequipement.setIdtypestructure(typestructure);
                    }

                    if (typeequipementtraceur.getIdtypeequipementtraceur() != null) {
                        parametrecoutequipement.setIdtypeequipement(typeequipementtraceur);
                    }

                    if (etatequipement.getIdetatequipement() != null) {
                        parametrecoutequipement.setIdetatequipement(etatequipement);
                    }

                    parametrecoutequipementFacadeLocal.edit(parametrecoutequipement);

                    parametrecoutequipement = new Parametrecoutequipement();

                    JsfUtil.addSuccessMessage("Activité mise à jour avec succès");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (parametrecoutequipement != null) {

                parametrecoutequipementFacadeLocal.remove(parametrecoutequipement);
                parametrecoutequipement = new Parametrecoutequipement();

                JsfUtil.addErrorMessage("Opération réussie");

            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
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

    public Parametrecoutequipement getParametrecoutequipement() {
        return parametrecoutequipement;
    }

    public void setParametrecoutequipement(Parametrecoutequipement parametrecoutequipement) {
        this.parametrecoutequipement = parametrecoutequipement;
    }

    public List<Parametrecoutequipement> getParametrecoutequipements() {
        parametrecoutequipements = parametrecoutequipementFacadeLocal.findAllRange();
        return parametrecoutequipements;
    }

    public void setParametrecoutequipements(List<Parametrecoutequipement> parametrecoutequipements) {
        this.parametrecoutequipements = parametrecoutequipements;
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

    public Etatequipement getEtatequipement() {
        return etatequipement;
    }

    public void setEtatequipement(Etatequipement etatequipement) {
        this.etatequipement = etatequipement;
    }

    public List<Etatequipement> getEtatequipements() {
        etatequipements = etatequipementFacadeLocal.findAll();
        return etatequipements;
    }

    public void setEtatequipements(List<Etatequipement> etatequipements) {
        this.etatequipements = etatequipements;
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

}
