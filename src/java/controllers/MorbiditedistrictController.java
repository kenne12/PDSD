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
import entities.Rubriquemorbidite;
import entities.Morbiditedistrict;
import entities.Morbidite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.RubriquemorbiditeFacadeLocal;
import sessions.MorbiditedistrictFacadeLocal;
import sessions.MorbiditeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class MorbiditedistrictController {
    
    @EJB
    private MorbiditedistrictFacadeLocal morbiditedistrictFacadeLocal;
    private List<Morbiditedistrict> morbiditedistricts = new ArrayList<>();
    
    @EJB
    private RubriquemorbiditeFacadeLocal rubriquemorbiditeFacadeLocal;
    private List<Rubriquemorbidite> rubriquemorbidites = new ArrayList<>();
    
    @EJB
    private MorbiditeFacadeLocal morbiditeFacadeLocal;
    private Morbidite morbidite;
    private List<Morbidite> morbidites = new ArrayList<>();
    
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
    public MorbiditedistrictController() {
    }
    
    @PostConstruct
    private void init() {
        morbidite = new Morbidite();
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
            morbiditedistricts.clear();
            if (morbidite != null) {
                if (!this.getRubriquemorbidites().isEmpty()) {                    
                    for (Rubriquemorbidite m : this.getRubriquemorbidites()) {
                        List<Morbiditedistrict> temp = morbiditedistrictFacadeLocal.find(morbidite, m, SessionMBean.getDistrict());
                        if (temp.isEmpty()) {
                            Morbiditedistrict morbiditedistrict = new Morbiditedistrict();
                            morbiditedistrict.setIdrubriquemorbidite(m);
                            morbiditedistrict.setIdmorbidite(morbidite);
                            morbiditedistrict.setIddistrict(SessionMBean.getDistrict());
                            morbiditedistricts.add(morbiditedistrict);
                        } else {
                            morbiditedistricts.add(temp.get(0));
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
            
            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 4);
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
            if (!morbiditedistricts.isEmpty()) {
                for (Morbiditedistrict m : morbiditedistricts) {
                    if (m.getIdmorbiditedistrict() == null) {
                        m.setIdmorbiditedistrict(morbiditedistrictFacadeLocal.nextId());
                        m.setIddistrict(SessionMBean.getDistrict());
                        morbiditedistrictFacadeLocal.create(m);
                    } else {
                        morbiditedistrictFacadeLocal.edit(m);
                    }
                }
                
                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 4);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(4);
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
    
    public String findRubriquemorbidite(Morbidite morbidite, Rubriquemorbidite rubriquemorbidite) {
        String resultat = "";
        try {
            
            if (morbidite != null) {
                if (rubriquemorbidite != null) {
                    
                    List<Morbiditedistrict> temps = morbiditedistrictFacadeLocal.find(morbidite, rubriquemorbidite, SessionMBean.getDistrict());
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
    
    public Morbidite getMorbidite() {
        return morbidite;
    }
    
    public void setMorbidite(Morbidite morbidite) {
        this.morbidite = morbidite;
    }
    
    public List<Morbidite> getMorbidites() {       
        morbidites = morbiditeFacadeLocal.findAllRangeCode();
        return morbidites;
    }
    
    public void setMorbidites(List<Morbidite> morbidites) {
        this.morbidites = morbidites;
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
    
    public List<Rubriquemorbidite> getRubriquemorbidites() {
        rubriquemorbidites = rubriquemorbiditeFacadeLocal.findAllRange();
        return rubriquemorbidites;
    }
    
    public List<Morbiditedistrict> getMorbiditedistricts() {
        return morbiditedistricts;
    }
    
    public void setMorbiditedistricts(List<Morbiditedistrict> morbiditedistricts) {
        this.morbiditedistricts = morbiditedistricts;
    }
    
    public Commentairetab getCommentairetab() {
        return commentairetab;
    }
    
    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }
    
}
