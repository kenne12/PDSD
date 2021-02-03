/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Commentairetab;
import entities.Recette;
import entities.Sourcefinancement;
import entities.Structure;
import entities.Typerecette;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.RecetteFacadeLocal;
import sessions.SourcefinancementFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TyperecetteFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class RecetteController {

    @EJB
    private RecetteFacadeLocal recetteFacadeLocal;
    private List<Recette> recettes = new ArrayList<>();

    @EJB
    private SourcefinancementFacadeLocal sourcefinancementFacadeLocal;
    private Sourcefinancement sourcefinancement;
    private List<Sourcefinancement> sourcefinancements = new ArrayList<>();

    @EJB
    private TyperecetteFacadeLocal typerecetteFacadeLocal;
    private List<Typerecette> typerecettes = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    private Double totalRecette = 0d;

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public RecetteController() {
    }

    @PostConstruct
    private void init() {
        structure = new Structure();
        annee = new Annee();
        sourcefinancement = new Sourcefinancement();

        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 12);
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

    public void initStructure() {
        try {
            if (structure.getIdstructure() != null) {
                structure = structureFacadeLocal.find(structure.getIdstructure());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAnnee() {
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable() {
        try {
            annee = new Annee();
            recettes.clear();
            if (structure != null) {
                if (!this.getSourcefinancements().isEmpty()) {
                    for (Sourcefinancement s : this.getSourcefinancements()) {
                        Recette recette = new Recette();
                        recette.setIdsourcefi(s);
                        recette.setIdstructure(structure);
                        recettes.add(recette);
                    }
                } else {
                    System.err.println("Aucune source de financement");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionnner une structure");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uptadeTable() {
        try {
            recettes.clear();
            if (annee.getIdannee() != null) {

                if (structure != null) {
                    if (!this.getSourcefinancements().isEmpty()) {

                        if (recetteFacadeLocal.find(structure, annee).isEmpty()) {
                            for (Sourcefinancement s : this.getSourcefinancements()) {
                                Recette recette = new Recette();
                                recette.setIdannee(annee);
                                recette.setIdsourcefi(s);
                                recette.setIdstructure(structure);
                                recettes.add(recette);
                            }
                        } else {
                            for (Sourcefinancement s : this.getSourcefinancements()) {
                                List<Recette> temp = recetteFacadeLocal.find(structure, s, annee);
                                if (temp.isEmpty()) {
                                    Recette recette = new Recette();
                                    recette.setIdannee(annee);
                                    recette.setIdsourcefi(s);
                                    recette.setIdstructure(structure);
                                    recettes.add(recette);
                                } else {
                                    recettes.add(temp.get(0));
                                }
                            }
                        }
                    } else {
                        System.err.println("Aucune source de financement");
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez selectionnner une structure");
                }
            } else {
                JsfUtil.addErrorMessage("Veillez selectionner une année");
            }
            calculTotal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire() {
        try {
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 12);
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
            if (!recettes.isEmpty()) {
                for (Recette r : recettes) {
                    if (r.getIdrecette() == null) {
                        r.setIdrecette(recetteFacadeLocal.nextId());
                        recetteFacadeLocal.create(r);
                    } else {
                        recetteFacadeLocal.edit(r);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 12);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(12);
                    commentairetabFacadeLocal.create(commentairetab);
                } else {
                    commentairetabFacadeLocal.edit(commentairetab);
                }
                this.updateCommentaire();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Recette findRecette(Sourcefinancement sourcefinancement, Annee annee) {
        Recette recette = null;
        try {
            if (annee.getIdannee() != null) {
                if (structure != null) {
                    if (sourcefinancement != null) {

                        List<Recette> temps = recetteFacadeLocal.find(structure, sourcefinancement, annee);
                        if (!temps.isEmpty()) {
                            recette = temps.get(0);
                        } else {
                            recette = new Recette();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recette;
    }

    public void calculTotal() {
        totalRecette = 0d;
        try {
            if (!recettes.isEmpty()) {
                for (Recette r : recettes) {
                    totalRecette += r.getValeur().doubleValue();
                }
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        try {
            structures = structureFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            annees = anneeFacadeLocal.findByEtatRecette(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

    public List<Sourcefinancement> getSourcefinancements() {
        sourcefinancements = sourcefinancementFacadeLocal.findAllRange();
        return sourcefinancements;
    }

    public void setSourcefinancements(List<Sourcefinancement> sourcefinancements) {
        this.sourcefinancements = sourcefinancements;
    }

    public List<Typerecette> getTyperecettes() {
        typerecettes = typerecetteFacadeLocal.findAllRange();
        return typerecettes;
    }

    public void setTyperecettes(List<Typerecette> typerecettes) {
        this.typerecettes = typerecettes;
    }

    public Sourcefinancement getSourcefinancement() {
        return sourcefinancement;
    }

    public void setSourcefinancement(Sourcefinancement sourcefinancement) {
        this.sourcefinancement = sourcefinancement;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

    public Double getTotalRecette() {
        return totalRecette;
    }

    public void setTotalRecette(Double totalRecette) {
        this.totalRecette = totalRecette;
    }

}
