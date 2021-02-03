/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Acteur;
import entities.Commentairetab;
import entities.Groupeacteur;
import entities.Typeacteur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ActeurFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.GroupeacteurFacadeLocal;
import sessions.TypeacteurFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ActeurcommunautaireController implements Serializable {

    @EJB
    private ActeurFacadeLocal acteurFacadeLocal;
    private Acteur acteur = new Acteur();
    private List<Acteur> acteurs = new ArrayList<>();

    @EJB
    private GroupeacteurFacadeLocal groupeacteurFacadeLocal;
    private Groupeacteur groupeacteur = new Groupeacteur();
    private List<Groupeacteur> groupeacteurs = new ArrayList<>();

    @EJB
    private TypeacteurFacadeLocal typeacteurFacadeLocal;
    private Typeacteur typeacteur = new Typeacteur();
    private List<Typeacteur> typeacteurs = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of ActeurController
     */
    public ActeurcommunautaireController() {

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
            acteur = new Acteur();
            typeacteur = new Typeacteur();
            groupeacteur = new Groupeacteur();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            if (acteur != null) {
                if (acteur.getIdtypeacteur() != null) {
                    typeacteur = acteur.getIdtypeacteur();
                }

                if (acteur.getIdgroupeacteur() != null) {
                    groupeacteur = acteur.getIdgroupeacteur();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            if ("Create".equals(mode)) {
                acteur.setIdacteur(acteurFacadeLocal.nextId());

                if (typeacteur.getIdtypeacteur() != null) {
                    acteur.setIdtypeacteur(typeacteurFacadeLocal.find(typeacteur.getIdtypeacteur()));
                }

                if (groupeacteur.getIdgroupeacteur() != null) {
                    acteur.setIdgroupeacteur(groupeacteurFacadeLocal.find(groupeacteur.getIdgroupeacteur()));
                }
                acteurFacadeLocal.create(acteur);
                acteur = new Acteur();

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (acteur != null) {

                    if (typeacteur.getIdtypeacteur() != null) {
                        acteur.setIdtypeacteur(typeacteurFacadeLocal.find(typeacteur.getIdtypeacteur()));
                    }

                    if (groupeacteur.getIdgroupeacteur() != null) {
                        acteur.setIdgroupeacteur(groupeacteurFacadeLocal.find(groupeacteur.getIdgroupeacteur()));
                    }

                    acteurFacadeLocal.edit(acteur);
                    acteur = new Acteur();

                    JsfUtil.addSuccessMessage("Acteur mis à jour avec succès");
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
            if (acteur != null) {
                acteurFacadeLocal.remove(acteur);
                acteur = new Acteur();
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
                if (!acteurs.isEmpty()) {
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

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public List<Acteur> getActeurs() {
        try {
            acteurs = acteurFacadeLocal.find(SessionMBean.getDistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
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

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
