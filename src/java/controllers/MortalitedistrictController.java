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
import entities.Rubriquemortalite;
import entities.Mortalitedistrict;
import entities.Mortalite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.RubriquemortaliteFacadeLocal;
import sessions.MortalitedistrictFacadeLocal;
import sessions.MortaliteFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class MortalitedistrictController {
    
    @EJB
    private MortalitedistrictFacadeLocal mortalitedistrictFacadeLocal;
    private List<Mortalitedistrict> mortalitedistricts = new ArrayList<>();
    
    @EJB
    private RubriquemortaliteFacadeLocal rubriquemortaliteFacadeLocal;
    private List<Rubriquemortalite> rubriquemortalites = new ArrayList<>();
    
    @EJB
    private MortaliteFacadeLocal mortaliteFacadeLocal;
    private Mortalite mortalite;
    private List<Mortalite> mortalites = new ArrayList<>();
    
    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();
    
    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;
    private Commentairetab commentairetab = new Commentairetab();
    
    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public MortalitedistrictController() {
    }
    
    @PostConstruct
    private void init() {
        mortalite = new Mortalite();
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
            mortalitedistricts.clear();
            if (mortalite != null) {
                if (!this.getRubriquemortalites().isEmpty()) {                    
                    for (Rubriquemortalite m : this.getRubriquemortalites()) {
                        List<Mortalitedistrict> temp = mortalitedistrictFacadeLocal.find(mortalite, m, SessionMBean.getDistrict());
                        if (temp.isEmpty()) {
                            Mortalitedistrict mortalitedistrict = new Mortalitedistrict();
                            mortalitedistrict.setIdrubriquemortalite(m);
                            mortalitedistrict.setIdmortalite(mortalite);
                            mortalitedistrict.setIddistrict(SessionMBean.getDistrict());
                            mortalitedistricts.add(mortalitedistrict);
                        } else {
                            mortalitedistricts.add(temp.get(0));
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
            
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 7);
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
            if (!mortalitedistricts.isEmpty()) {
                for (Mortalitedistrict m : mortalitedistricts) {
                    if (m.getIdmortalitedistrict() == null) {
                        m.setIdmortalitedistrict(mortalitedistrictFacadeLocal.nextId());
                        m.setIddistrict(SessionMBean.getDistrict());
                        mortalitedistrictFacadeLocal.create(m);
                    } else {
                        mortalitedistrictFacadeLocal.edit(m);
                    }
                }
                
                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 7);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(7);
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
    
    public String findRubriquemortalite(Mortalite mortalite, Rubriquemortalite rubriquemortalite) {
        String resultat = "";
        try {
            
            if (mortalite != null) {
                if (rubriquemortalite != null) {
                    
                    List<Mortalitedistrict> temps = mortalitedistrictFacadeLocal.find(mortalite, rubriquemortalite, SessionMBean.getDistrict());
                    if (!temps.isEmpty()) {
                        resultat = "" + temps.get(0).getValeur();
                        
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
    
    public Mortalite getMortalite() {
        return mortalite;
    }
    
    public void setMortalite(Mortalite mortalite) {
        this.mortalite = mortalite;
    }
    
    public List<Mortalite> getMortalites() {
        
        mortalites = mortaliteFacadeLocal.findAllRangeCode();        
        return mortalites;
    }
    
    public void setMortalites(List<Mortalite> mortalites) {
        this.mortalites = mortalites;
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
    
    public List<Rubriquemortalite> getRubriquemortalites() {
        rubriquemortalites = rubriquemortaliteFacadeLocal.findAllRange();
        return rubriquemortalites;
    }
    
    public List<Mortalitedistrict> getMortalitedistricts() {
        return mortalitedistricts;
    }
    
    public void setMortalitedistricts(List<Mortalitedistrict> mortalitedistricts) {
        this.mortalitedistricts = mortalitedistricts;
    }
    
    public Commentairetab getCommentairetab() {
        return commentairetab;
    }
    
    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }
    
}
