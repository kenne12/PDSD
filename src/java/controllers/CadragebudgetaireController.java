/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Activite;
import entities.ActiviteStructure;
import entities.Annee;
import entities.Commentairetab;
import entities.Etatinfrastructure;
import entities.Infrastructure;
import entities.Recette;
import entities.Structure;
import entities.TypeinfraTypestruc;
import entities.Typestructure;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ActiviteFacadeLocal;
import sessions.ActiviteStructureFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.EtatinfrastructureFacadeLocal;
import sessions.InfrastructureFacadeLocal;
import sessions.RecetteFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypeinfraTypestrucFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class CadragebudgetaireController implements Serializable {

    @EJB
    private ActiviteStructureFacadeLocal activiteStructureFacadeLocal;
    private ActiviteStructure activiteStructure = new ActiviteStructure();
    private List<ActiviteStructure> activiteStructures = new ArrayList<>();

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;

    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private TypeinfraTypestrucFacadeLocal typeinfraTypestrucFacadeLocal;
    private TypeinfraTypestruc typeinfraTypestruc;
    private List<TypeinfraTypestruc> typeinfraTypestrucs = new ArrayList<>();

    @EJB
    private InfrastructureFacadeLocal infrastructureFacadeLocal;
    private List<Infrastructure> infrastructures = new ArrayList<>();

    @EJB
    private EtatinfrastructureFacadeLocal etatinfrastructureFacadeLocal;
    private List<Etatinfrastructure> etatinfrastructures = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee = new Annee();
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();

    @EJB
    private RecetteFacadeLocal recetteFacadeLocal;

    private Double totalRecette = 0d;

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public CadragebudgetaireController() {
    }

    @PostConstruct
    private void init() {
        typestructure = new Typestructure();
        structure = new Structure();
        typeinfraTypestruc = new TypeinfraTypestruc();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
    }

    public void uptadeTable() {
        try {
            if (structure.getIdstructure() != null) {
                structure = structureFacadeLocal.find(structure.getIdstructure());
                if (annee.getIdannee() != null) {

                    annee = anneeFacadeLocal.find(annee.getIdannee());
                    if (!activiteStructures.isEmpty()) {
                        int i = 0;
                        for (ActiviteStructure a : activiteStructures) {

                            if (!a.getPrograme()) {
                                activiteStructures.get(i).setCout(a.getIdactivite().getCoutunitaire());
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCadrage(ActiviteStructure activiteStructure) {
        try {
            activiteStructureFacadeLocal.edit(activiteStructure);
            uptadeTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!activiteStructures.isEmpty()) {
                for (ActiviteStructure a : activiteStructures) {
                    activiteStructureFacadeLocal.edit(a);
                }
                Activite activite = activiteStructures.get(0).getIdactivite();
                List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(activite);
                Double somme = 0d;
                for (ActiviteStructure a : activiteStructures) {
                    somme += a.getCout();
                }
                activite.setCoutglobal(somme);
                activiteFacadeLocal.edit(activite);
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
            this.findTotalRecette();
            activiteStructures.clear();
            if (structure.getIdstructure() != null) {
                if (annee.getIdannee() != null) {
                    activiteStructures = activiteStructureFacadeLocal.find(structure, annee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findTotalRecette() {
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
                if (structure.getIdstructure() != null) {
                    List<Recette> recettes = recetteFacadeLocal.find(structure, annee);
                    if (!recettes.isEmpty()) {
                        for (Recette r : recettes) {
                            totalRecette += r.getValeur().doubleValue();
                        }
                    } else {
                        totalRecette = 0d;
                    }
                } else {
                    totalRecette = 0d;
                }
            } else {
                totalRecette = 0d;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double findTotal() {
        Double total = 0d;
        try {
            if (!activiteStructures.isEmpty()) {
                for (ActiviteStructure a : activiteStructures) {
                    if (a.getCout() != null) {
                        total += a.getCout();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
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

    public TypeinfraTypestruc getTypeinfraTypestruc() {
        return typeinfraTypestruc;
    }

    public void setTypeinfraTypestruc(TypeinfraTypestruc typeinfraTypestruc) {
        this.typeinfraTypestruc = typeinfraTypestruc;
    }

    public List<TypeinfraTypestruc> getTypeinfraTypestrucs() {
        return typeinfraTypestrucs;
    }

    public void setTypeinfraTypestrucs(List<TypeinfraTypestruc> typeinfraTypestrucs) {
        this.typeinfraTypestrucs = typeinfraTypestrucs;
    }

    public List<Etatinfrastructure> getEtatinfrastructures() {
        etatinfrastructures = etatinfrastructureFacadeLocal.findAll();
        return etatinfrastructures;
    }

    public void setEtatinfrastructures(List<Etatinfrastructure> etatinfrastructures) {
        this.etatinfrastructures = etatinfrastructures;
    }

    public List<Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public void setInfrastructures(List<Infrastructure> infrastructures) {
        this.infrastructures = infrastructures;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findAllRange();
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public ActiviteStructure getActiviteStructure() {
        return activiteStructure;
    }

    public void setActiviteStructure(ActiviteStructure activiteStructure) {
        this.activiteStructure = activiteStructure;
    }

    public List<ActiviteStructure> getActiviteStructures() {
        return activiteStructures;
    }

    public void setActiviteStructures(List<ActiviteStructure> activiteStructures) {
        this.activiteStructures = activiteStructures;
    }

    public Double getTotalRecette() {
        return totalRecette;
    }

    public void setTotalRecette(Double totalRecette) {
        this.totalRecette = totalRecette;
    }

}
