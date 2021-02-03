/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Commentairetab;
import entities.Equipementtraceur;
import entities.Etatequipement;
import entities.Structure;
import entities.TypestrucTypeequipement;
import entities.Typestructure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.CommentairetabFacadeLocal;
import sessions.EquipementtraceurFacadeLocal;
import sessions.EtatequipementFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypestrucTypeequipementFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class EquipementtraceurController implements Serializable {

    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private TypestrucTypeequipementFacadeLocal typestrucTypeequipementFacadeLocal;
    private TypestrucTypeequipement typestrucTypeequipement;
    private List<TypestrucTypeequipement> typestrucTypeequipements = new ArrayList<>();

    @EJB
    private EquipementtraceurFacadeLocal equipementtraceurFacadeLocal;
    private List<Equipementtraceur> equipementtraceurs = new ArrayList<>();

    @EJB
    private EtatequipementFacadeLocal etatequipementFacadeLocal;
    private List<Etatequipement> etatequipements = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public EquipementtraceurController() {
    }

    @PostConstruct
    private void init() {
        typestructure = new Typestructure();
        structure = new Structure();
        typestrucTypeequipement = new TypestrucTypeequipement();
        try {
            this.updateCommentaire();
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

    public void uptadeTable() {
        try {
            equipementtraceurs.clear();

            if (structure != null) {
                if (!this.getTypestrucTypeequipements().isEmpty()) {

                    if (equipementtraceurFacadeLocal.find(structure).isEmpty()) {
                        for (TypestrucTypeequipement t : this.typestrucTypeequipements) {
                            Equipementtraceur equipementtraceur = new Equipementtraceur();
                            equipementtraceur.setIdstructure(structure);
                            equipementtraceur.setIdtypestrucTypeequipement(t);
                            equipementtraceurs.add(equipementtraceur);
                        }
                    } else {
                        for (TypestrucTypeequipement t : this.typestrucTypeequipements) {
                            List<Equipementtraceur> temp = equipementtraceurFacadeLocal.find(structure, t);
                            if (temp.isEmpty()) {
                                Equipementtraceur equipementtraceur = new Equipementtraceur();
                                equipementtraceur.setIdstructure(structure);
                                equipementtraceur.setIdtypestrucTypeequipement(t);
                                equipementtraceurs.add(equipementtraceur);
                            } else {
                                equipementtraceurs.add(temp.get(0));
                            }
                        }
                    }
                } else {
                    System.err.println("aucun profil");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionnner une ligne");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 11);
            if (!commentairetabs.isEmpty()) {
                commentairetab = commentairetabs.get(0);
                return;
            }
            commentairetab = new Commentairetab();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!equipementtraceurs.isEmpty()) {
                for (Equipementtraceur e : equipementtraceurs) {
                    if (e.getIdequipementtraceur() == null) {
                        e.setIdequipementtraceur(equipementtraceurFacadeLocal.nextId());
                        equipementtraceurFacadeLocal.create(e);
                    } else {
                        equipementtraceurFacadeLocal.edit(e);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 11);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(11);
                    commentairetabFacadeLocal.create(commentairetab);
                } else {
                    commentairetabFacadeLocal.edit(commentairetab);
                }

                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            if (typestructure.getIdtypestructure() != null) {
                typestructure = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                structures = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure);
                typestrucTypeequipements = typestrucTypeequipementFacadeLocal.findByTypestructure(typestructure);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findEquipement(Structure structure, TypestrucTypeequipement typestrucTypeequipement) {
        String resultat = "";
        try {
            if (typestructure.getIdtypestructure() != null) {
                if (structure != null) {
                    if (typestrucTypeequipement != null) {

                        List<Equipementtraceur> temp = equipementtraceurFacadeLocal.find(structure, typestrucTypeequipement);
                        if (!temp.isEmpty()) {
                            if ("fr".equals(SessionMBean.getLangue())) {
                                resultat = "[ " + temp.get(0).getNombre() + " ] - " + temp.get(0).getIdetatequipement().getNomFr();
                            } else {
                                resultat = "[ " + temp.get(0).getNombre() + " ] - " + temp.get(0).getIdetatequipement().getNomEn();
                            }
                        } else {
                            resultat = "";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
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

    public List<TypestrucTypeequipement> getTypestrucTypeequipements() {
        return typestrucTypeequipements;
    }

    public void setTypestrucTypeequipements(List<TypestrucTypeequipement> typestrucTypeequipements) {
        this.typestrucTypeequipements = typestrucTypeequipements;
    }

    public List<Equipementtraceur> getEquipementtraceurs() {
        return equipementtraceurs;
    }

    public void setEquipementtraceurs(List<Equipementtraceur> equipementtraceurs) {
        this.equipementtraceurs = equipementtraceurs;
    }

    public List<Etatequipement> getEtatequipements() {
        etatequipements = etatequipementFacadeLocal.findAll();
        return etatequipements;
    }

    public void setEtatequipements(List<Etatequipement> etatequipements) {
        this.etatequipements = etatequipements;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
