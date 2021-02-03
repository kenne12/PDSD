/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Structurecommunautaire;
import entities.Commentairetab;
import entities.Etatfonctstructcom;
import entities.Typestructurecommunautaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.StructurecommunautaireFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.EtatfonctstructcomFacadeLocal;

import sessions.TypestructurecommunautaireFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class StructurecommunautaireController implements Serializable {

    @EJB
    private StructurecommunautaireFacadeLocal structurecommunautaireFacadeLocal;
    private Structurecommunautaire structurecommunautaire = new Structurecommunautaire();
    private List<Structurecommunautaire> structurecommunautaires = new ArrayList<>();

    @EJB
    private TypestructurecommunautaireFacadeLocal typestructurecommunautaireFacadeLocal;
    private Typestructurecommunautaire typestructurecommunautaire = new Typestructurecommunautaire();
    private List<Typestructurecommunautaire> typestructurecommunautaires = new ArrayList<>();

    @EJB
    private EtatfonctstructcomFacadeLocal etatfonctstructcomFacadeLocal;
    private Etatfonctstructcom etatfonctstructcom = new Etatfonctstructcom();
    private List<Etatfonctstructcom> etatfonctstructcoms = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of StructurecommunautaireController
     */
    public StructurecommunautaireController() {

    }

    @PostConstruct
    private void init() {
        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 9);
            if (!commentairetabs.isEmpty()) {
                commentairetab = commentairetabs.get(0);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            structurecommunautaire = new Structurecommunautaire();
            typestructurecommunautaire = new Typestructurecommunautaire();
            etatfonctstructcom = new Etatfonctstructcom();
            structurecommunautaire.setEffectif(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (structurecommunautaire != null) {
                if (structurecommunautaire.getIdtypestructurecommunautaire() != null) {
                    typestructurecommunautaire = structurecommunautaire.getIdtypestructurecommunautaire();
                }

                if (structurecommunautaire.getIdetatfonctstructcom() != null) {
                    etatfonctstructcom = structurecommunautaire.getIdetatfonctstructcom();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {
                structurecommunautaire.setIdstructurecommunautaire(structurecommunautaireFacadeLocal.nextId());

                if (typestructurecommunautaire.getIdtypestructurecommunautaire() != null) {
                    structurecommunautaire.setIdtypestructurecommunautaire(typestructurecommunautaireFacadeLocal.find(typestructurecommunautaire.getIdtypestructurecommunautaire()));
                }

                if (etatfonctstructcom.getIdetatfonctstructcom() != null) {
                    structurecommunautaire.setIdetatfonctstructcom(etatfonctstructcomFacadeLocal.find(etatfonctstructcom.getIdetatfonctstructcom()));
                }
                structurecommunautaire.setIddistrict(SessionMBean.getDistrict());
                structurecommunautaireFacadeLocal.create(structurecommunautaire);
                structurecommunautaire = new Structurecommunautaire();

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 9);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(9);
                    commentairetabFacadeLocal.create(commentairetab);
                } else {
                    commentairetabFacadeLocal.edit(commentairetab);
                }

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (structurecommunautaire != null) {

                    if (typestructurecommunautaire.getIdtypestructurecommunautaire() != null) {
                        structurecommunautaire.setIdtypestructurecommunautaire(typestructurecommunautaireFacadeLocal.find(typestructurecommunautaire.getIdtypestructurecommunautaire()));
                    }

                    if (etatfonctstructcom.getIdetatfonctstructcom() != null) {
                        structurecommunautaire.setIdetatfonctstructcom(etatfonctstructcomFacadeLocal.find(etatfonctstructcom.getIdetatfonctstructcom()));
                    }

                    structurecommunautaireFacadeLocal.edit(structurecommunautaire);

                    List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 9);
                    if (commentairetabs.isEmpty()) {
                        commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                        commentairetab.setIddistrict(SessionMBean.getDistrict());
                        commentairetab.setNumerotab(9);
                        commentairetabFacadeLocal.create(commentairetab);
                    } else {
                        commentairetabFacadeLocal.edit(commentairetab);
                    }

                    structurecommunautaire = new Structurecommunautaire();

                    JsfUtil.addSuccessMessage("Structurecommunautaire mis à jour avec succès");
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
            if (structurecommunautaire != null) {
                structurecommunautaireFacadeLocal.remove(structurecommunautaire);
                structurecommunautaire = new Structurecommunautaire();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {
            if (commentairetab.getIdcommentairetab() != null) {
                if (!structurecommunautaires.isEmpty()) {
                    commentairetabFacadeLocal.edit(commentairetab);
                    JsfUtil.addSuccessMessage("Opération réussie");
                    return;
                }
                JsfUtil.addErrorMessage("Aucune données à commenter");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Structurecommunautaire getStructurecommunautaire() {
        return structurecommunautaire;
    }

    public void setStructurecommunautaire(Structurecommunautaire structurecommunautaire) {
        this.structurecommunautaire = structurecommunautaire;
    }

    public List<Structurecommunautaire> getStructurecommunautaires() {
        try {
            structurecommunautaires = structurecommunautaireFacadeLocal.findByDistrict(SessionMBean.getDistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return structurecommunautaires;
    }

    public void setStructurecommunautaires(List<Structurecommunautaire> structurecommunautaires) {
        this.structurecommunautaires = structurecommunautaires;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Typestructurecommunautaire getTypestructurecommunautaire() {
        return typestructurecommunautaire;
    }

    public void setTypestructurecommunautaire(Typestructurecommunautaire typestructurecommunautaire) {
        this.typestructurecommunautaire = typestructurecommunautaire;
    }

    public List<Typestructurecommunautaire> getTypestructurecommunautaires() {
        typestructurecommunautaires = typestructurecommunautaireFacadeLocal.findAllRange();
        return typestructurecommunautaires;
    }

    public void setTypestructurecommunautaires(List<Typestructurecommunautaire> typestructurecommunautaires) {
        this.typestructurecommunautaires = typestructurecommunautaires;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

    public Etatfonctstructcom getEtatfonctstructcom() {
        return etatfonctstructcom;
    }

    public void setEtatfonctstructcom(Etatfonctstructcom etatfonctstructcom) {
        this.etatfonctstructcom = etatfonctstructcom;
    }

    public List<Etatfonctstructcom> getEtatfonctstructcoms() {
        etatfonctstructcoms = etatfonctstructcomFacadeLocal.findAllRange();
        return etatfonctstructcoms;
    }

    public void setEtatfonctstructcoms(List<Etatfonctstructcom> etatfonctstructcoms) {
        this.etatfonctstructcoms = etatfonctstructcoms;
    }

}
