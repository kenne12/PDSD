/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Airesante;
import entities.Commentairetab;
import entities.Annee;
import entities.Populationfosa;
import entities.Statutstructure;
import entities.Structure;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AiresanteFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.PopulationfosaFacadeLocal;
import sessions.StatutstructureFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PopulationfosaController {
    
    @EJB
    private PopulationfosaFacadeLocal populationfosaFacadeLocal;
    private List<Populationfosa> populationfosas = new ArrayList<>();
    
    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();
    
    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();
    
    @EJB
    private AiresanteFacadeLocal airesanteFacadeLocal;
    private Airesante airesante;
    private List<Airesante> airesantes = new ArrayList<>();
    
    @EJB
    
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();
    
    @EJB
    private StatutstructureFacadeLocal statutstructurFacadeLocal;
    private Statutstructure statutstructure;
    private List<Statutstructure> statutstructures = new ArrayList<>();
    
    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();
    
    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public PopulationfosaController() {
    }
    
    @PostConstruct
    private void init() {
        structure = new Structure();
        annee = new Annee();
        this.updateCommentaire();
        
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void uptadeTable() {
        try {
            populationfosas.clear();
            if (structure != null) {
                if (!this.getAnnees().isEmpty()) {
                    for (Annee m : this.getAnnees()) {
                        List<Populationfosa> temp = populationfosaFacadeLocal.find(structure, m);
                        if (temp.isEmpty()) {
                            Populationfosa populationfosa = new Populationfosa();
                            populationfosa.setIdannee(m);
                            populationfosa.setIdstructure(structure);
                            populationfosas.add(populationfosa);
                        } else {
                            populationfosas.add(temp.get(0));
                        }
                    }
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
            
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 1);
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
            if (!populationfosas.isEmpty()) {
                for (Populationfosa m : populationfosas) {
                    if (m.getIdpopulationfosa() == null) {
                        m.setIdpopulationfosa(populationfosaFacadeLocal.nextId());
                        m.setPrcds(false);
                        populationfosaFacadeLocal.create(m);
                    } else {
                        populationfosaFacadeLocal.edit(m);
                    }
                }
                
                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 1);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(1);
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
    
    public String findAnnee(Structure structure, Annee annee) {
        String resultat = "";
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
                
                if (structure != null) {
                    if (annee != null) {
                        
                        List<Populationfosa> temps = populationfosaFacadeLocal.find(structure, annee);
                        if (!temps.isEmpty()) {
                            resultat = "" + temps.get(0).getPopulationcouverte();
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
            annees = anneeFacadeLocal.findByEtatPopulationfosa(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }
    
    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }
    
    public List<Populationfosa> getPopulationfosas() {
        return populationfosas;
    }
    
    public void setPopulationfosas(List<Populationfosa> populationfosas) {
        this.populationfosas = populationfosas;
    }
    
    public Commentairetab getCommentairetab() {
        return commentairetab;
    }
    
    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }
    
    public Airesante getAiresante() {
        return airesante;
    }
    
    public void setAiresante(Airesante airesante) {
        this.airesante = airesante;
    }
    
    public List<Airesante> getAiresantes() {
        airesantes = airesanteFacadeLocal.findAllRange();
        return airesantes;
    }
    
    public void setAiresantes(List<Airesante> airesantes) {
        this.airesantes = airesantes;
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
    
    public Statutstructure getStatutstructure() {
        return statutstructure;
    }
    
    public void setStatutstructure(Statutstructure statutstructure) {
        this.statutstructure = statutstructure;
    }
    
    public List<Statutstructure> getStatutstructures() {
        statutstructures = statutstructurFacadeLocal.findAll();
        return statutstructures;
    }
    
    public void setStatutstructures(List<Statutstructure> statutstructures) {
        this.statutstructures = statutstructures;
    }
    
}
