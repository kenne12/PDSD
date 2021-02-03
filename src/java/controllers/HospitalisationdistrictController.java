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
import entities.Rubriquehospitalisation;
import entities.Hospitalisationdistrict;
import entities.Hospitalisation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.AnneeFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.RubriquehospitalisationFacadeLocal;
import sessions.HospitalisationdistrictFacadeLocal;
import sessions.HospitalisationFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class HospitalisationdistrictController {

    @EJB
    private HospitalisationdistrictFacadeLocal hospitalisationdistrictFacadeLocal;
    private List<Hospitalisationdistrict> hospitalisationdistricts = new ArrayList<>();

    @EJB
    private RubriquehospitalisationFacadeLocal rubriquehospitalisationFacadeLocal;
    private List<Rubriquehospitalisation> rubriquehospitalisations = new ArrayList<>();

    @EJB
    private HospitalisationFacadeLocal hospitalisationFacadeLocal;
    private Hospitalisation hospitalisation;
    private List<Hospitalisation> hospitalisations = new ArrayList<>();

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
    public HospitalisationdistrictController() {
    }

    @PostConstruct
    private void init() {
        hospitalisation = new Hospitalisation();
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
            hospitalisationdistricts.clear();
            if (hospitalisation != null) {
                if (!this.getRubriquehospitalisations().isEmpty()) {
                    for (Rubriquehospitalisation m : this.getRubriquehospitalisations()) {
                        List<Hospitalisationdistrict> temp = hospitalisationdistrictFacadeLocal.find(hospitalisation, m, SessionMBean.getDistrict());
                        if (temp.isEmpty()) {
                            Hospitalisationdistrict hospitalisationdistrict = new Hospitalisationdistrict();
                            hospitalisationdistrict.setIdrubriquehospitalisation(m);
                            hospitalisationdistrict.setIdhospitalisation(hospitalisation);
                            hospitalisationdistrict.setIddistrict(SessionMBean.getDistrict());
                            hospitalisationdistricts.add(hospitalisationdistrict);
                        } else {
                            hospitalisationdistricts.add(temp.get(0));
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

            List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 5);
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
            if (!hospitalisationdistricts.isEmpty()) {
                for (Hospitalisationdistrict m : hospitalisationdistricts) {
                    if (m.getIdhospitalisationdistrict() == null) {
                        m.setIdhospitalisationdistrict(hospitalisationdistrictFacadeLocal.nextId());
                        m.setIddistrict(SessionMBean.getDistrict());
                        hospitalisationdistrictFacadeLocal.create(m);
                    } else {
                        hospitalisationdistrictFacadeLocal.edit(m);
                    }
                }

                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict(), 5);
                if (commentairetabs.isEmpty()) {
                    commentairetab.setIdcommentairetab(commentairetabFacadeLocal.nextId());
                    commentairetab.setIddistrict(SessionMBean.getDistrict());
                    commentairetab.setNumerotab(5);
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

    public String findRubriquehospitalisation(Hospitalisation hospitalisation, Rubriquehospitalisation rubriquehospitalisation) {
        String resultat = "";
        try {

            if (hospitalisation != null) {
                if (rubriquehospitalisation != null) {

                    List<Hospitalisationdistrict> temps = hospitalisationdistrictFacadeLocal.find(hospitalisation, rubriquehospitalisation, SessionMBean.getDistrict());
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

    public Hospitalisation getHospitalisation() {
        return hospitalisation;
    }

    public void setHospitalisation(Hospitalisation hospitalisation) {
        this.hospitalisation = hospitalisation;
    }

    public List<Hospitalisation> getHospitalisations() {

        hospitalisations = hospitalisationFacadeLocal.findAllCode();
        return hospitalisations;
    }

    public void setHospitalisations(List<Hospitalisation> hospitalisations) {
        this.hospitalisations = hospitalisations;
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

    public List<Rubriquehospitalisation> getRubriquehospitalisations() {
        rubriquehospitalisations = rubriquehospitalisationFacadeLocal.findAllRange();
        return rubriquehospitalisations;
    }

    public List<Hospitalisationdistrict> getHospitalisationdistricts() {
        return hospitalisationdistricts;
    }

    public void setHospitalisationdistricts(List<Hospitalisationdistrict> hospitalisationdistricts) {
        this.hospitalisationdistricts = hospitalisationdistricts;
    }

    public Commentairetab getCommentairetab() {
        return commentairetab;
    }

    public void setCommentairetab(Commentairetab commentairetab) {
        this.commentairetab = commentairetab;
    }

}
