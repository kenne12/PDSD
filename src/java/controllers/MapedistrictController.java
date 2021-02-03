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
import entities.Mapedistrict;
import entities.Mape;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.MapedistrictFacadeLocal;
import sessions.MapeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class MapedistrictController {

    @EJB
    private MapedistrictFacadeLocal mapedistrictFacadeLocal;
    private List<Mapedistrict> mapedistricts = new ArrayList<>();

    @EJB
    private MapeFacadeLocal mapeFacadeLocal;
    private Mape mape;
    private List<Mape> mapes = new ArrayList<>();

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
    public MapedistrictController() {
    }

    @PostConstruct
    private void init() {
        mape = new Mape();
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
            mapedistricts.clear();
            if (mape != null) {
                if (!this.getAnnees().isEmpty()) {
                    for (Annee m : this.getAnnees()) {
                        List<Mapedistrict> temp = mapedistrictFacadeLocal.find(mape, m, SessionMBean.getDistrict());
                        if (temp.isEmpty()) {
                            Mapedistrict mapedistrict = new Mapedistrict();
                            mapedistrict.setIdannee(m);
                            mapedistrict.setIdmape(mape);
                            mapedistrict.setIddistrict(SessionMBean.getDistrict());
                            mapedistricts.add(mapedistrict);
                        } else {
                            mapedistricts.add(temp.get(0));
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

            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 6);
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
            if (!mapedistricts.isEmpty()) {
                for (Mapedistrict m : mapedistricts) {
                    if (m.getIdmapedistrict() == null) {
                        m.setIdmapedistrict(mapedistrictFacadeLocal.nextId());
                        m.setIddistrict(SessionMBean.getDistrict());
                        mapedistrictFacadeLocal.create(m);
                    } else {
                        mapedistrictFacadeLocal.edit(m);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 6);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(6);
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

    public String findAnnee(Mape mape, Annee annee) {
        String resultat = "";
        try {

            if (mape != null) {
                if (annee != null) {

                    List<Mapedistrict> temps = mapedistrictFacadeLocal.find(mape, annee, SessionMBean.getDistrict());
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

    public Mape getMape() {
        return mape;
    }

    public void setMape(Mape mape) {
        this.mape = mape;
    }

    public List<Mape> getMapes() {
        mapes = mapeFacadeLocal.findAllCode();
        return mapes;
    }

    public void setMapes(List<Mape> mapes) {
        this.mapes = mapes;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            annees = anneeFacadeLocal.findByEtatMape(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public List<Mapedistrict> getMapedistricts() {
        return mapedistricts;
    }

    public void setMapedistricts(List<Mapedistrict> mapedistricts) {
        this.mapedistricts = mapedistricts;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
