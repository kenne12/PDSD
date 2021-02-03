/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Paramcoutinfrastructure;
import entities.Commentairetab;
import entities.Etatinfrastructure;
import entities.Typeinfrastructure;

import entities.Typestructure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ParamcoutinfrastructureFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.EtatinfrastructureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.TypeinfrastructureFacadeLocal;
import sessions.TypestructureFacadeLocal;

import utilitaire.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class ParametrecoutInfrastructureController implements Serializable {

    @EJB
    private ParamcoutinfrastructureFacadeLocal paramcoutinfrastructureFacadeLocal;
    private Paramcoutinfrastructure paramcoutinfrastructure = new Paramcoutinfrastructure();
    private List<Paramcoutinfrastructure> paramcoutinfrastructures = new ArrayList<>();

    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure = new Typestructure();
    private List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    private TypeinfrastructureFacadeLocal typeinfrastructureFacadeLocal;
    private Typeinfrastructure typeinfrastructure = new Typeinfrastructure();
    private List<Typeinfrastructure> typeinfrastructures = new ArrayList<>();

    @EJB
    private EtatinfrastructureFacadeLocal etatinfrastructureFacadeLocal;
    private Etatinfrastructure etatinfrastructure = new Etatinfrastructure();
    private List<Etatinfrastructure> etatinfrastructures = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of StructurecommunautaireController
     */
    public ParametrecoutInfrastructureController() {

    }

    @PostConstruct
    private void init() {
        paramcoutinfrastructure = new Paramcoutinfrastructure();
        typestructure = new Typestructure();
        typeinfrastructure = new Typeinfrastructure();
        etatinfrastructure = new Etatinfrastructure();
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
            paramcoutinfrastructure = new Paramcoutinfrastructure();
            typestructure = new Typestructure();
            typeinfrastructure = new Typeinfrastructure();
            etatinfrastructure = new Etatinfrastructure();
            paramcoutinfrastructure.setCoutunitaire(0d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (paramcoutinfrastructure != null) {
                if (paramcoutinfrastructure.getIdetatinfrastructure() != null) {
                    etatinfrastructure = paramcoutinfrastructure.getIdetatinfrastructure();
                }

                if (paramcoutinfrastructure.getIdtypeinfrastructure() != null) {
                    typeinfrastructure = paramcoutinfrastructure.getIdtypeinfrastructure();
                }

                if (paramcoutinfrastructure.getIdtypestructure() != null) {
                    typestructure = paramcoutinfrastructure.getIdtypestructure();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {

                paramcoutinfrastructure.setIdparamcoutinfrastructure(paramcoutinfrastructureFacadeLocal.nextId());

                if (typestructure.getIdtypestructure() != null) {
                    paramcoutinfrastructure.setIdtypestructure(typestructure);
                }

                if (typeinfrastructure.getIdtypeinfrastructure() != null) {
                    paramcoutinfrastructure.setIdtypeinfrastructure(typeinfrastructure);
                }

                if (etatinfrastructure.getIdetatinfrastructure() != null) {
                    paramcoutinfrastructure.setIdetatinfrastructure(etatinfrastructure);
                }

                paramcoutinfrastructureFacadeLocal.create(paramcoutinfrastructure);
                Utilitaires.saveOperation("Parametrage cout des infrastructure  : -> Type de structure : " + paramcoutinfrastructure.getIdtypestructure().getNomFr() + " ; Type d'équipement : " + typeinfrastructure.getNomFr()+ " ; Cout unitaire : " + paramcoutinfrastructure.getCoutunitaire(), SessionMBean.getUser(), mouchardFacadeLocal);
                paramcoutinfrastructure = new Paramcoutinfrastructure();

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (paramcoutinfrastructure != null) {

                    if (typestructure.getIdtypestructure() != null) {
                        paramcoutinfrastructure.setIdtypestructure(typestructure);
                    }

                    if (typeinfrastructure.getIdtypeinfrastructure() != null) {
                        paramcoutinfrastructure.setIdtypeinfrastructure(typeinfrastructure);
                    }

                    if (etatinfrastructure.getIdetatinfrastructure() != null) {
                        paramcoutinfrastructure.setIdetatinfrastructure(etatinfrastructure);
                    }

                    paramcoutinfrastructureFacadeLocal.edit(paramcoutinfrastructure);

                    paramcoutinfrastructure = new Paramcoutinfrastructure();

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
            if (paramcoutinfrastructure != null) {

                paramcoutinfrastructureFacadeLocal.remove(paramcoutinfrastructure);
                //Utilitaires.saveOperation("Suppression de l'activité : " + paramcoutinfrastructure.getNom(), SessionMBean.getUser(), mouchardFacadeLocal);
                paramcoutinfrastructure = new Paramcoutinfrastructure();

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

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

    public Paramcoutinfrastructure getParamcoutinfrastructure() {
        return paramcoutinfrastructure;
    }

    public void setParamcoutinfrastructure(Paramcoutinfrastructure paramcoutinfrastructure) {
        this.paramcoutinfrastructure = paramcoutinfrastructure;
    }

    public List<Paramcoutinfrastructure> getParamcoutinfrastructures() {
        paramcoutinfrastructures = paramcoutinfrastructureFacadeLocal.findAllRange();
        return paramcoutinfrastructures;
    }

    public void setParamcoutinfrastructures(List<Paramcoutinfrastructure> paramcoutinfrastructures) {
        this.paramcoutinfrastructures = paramcoutinfrastructures;
    }

    public Typeinfrastructure getTypeinfrastructure() {
        return typeinfrastructure;
    }

    public void setTypeinfrastructure(Typeinfrastructure typeinfrastructure) {
        this.typeinfrastructure = typeinfrastructure;
    }

    public List<Typeinfrastructure> getTypeinfrastructures() {
        typeinfrastructures = typeinfrastructureFacadeLocal.findAll();
        return typeinfrastructures;
    }

    public void setTypeinfrastructures(List<Typeinfrastructure> typeinfrastructures) {
        this.typeinfrastructures = typeinfrastructures;
    }

    public Etatinfrastructure getEtatinfrastructure() {
        return etatinfrastructure;
    }

    public void setEtatinfrastructure(Etatinfrastructure etatinfrastructure) {
        this.etatinfrastructure = etatinfrastructure;
    }

    public List<Etatinfrastructure> getEtatinfrastructures() {
        etatinfrastructures = etatinfrastructureFacadeLocal.findAll();
        return etatinfrastructures;
    }

    public void setEtatinfrastructures(List<Etatinfrastructure> etatinfrastructures) {
        this.etatinfrastructures = etatinfrastructures;
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
