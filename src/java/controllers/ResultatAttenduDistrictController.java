/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Indicateur;
import entities.Interventionpnds;
import entities.ObjectifOppDistrict;
import entities.ResultatAttenduDistrict;
import entities.Resultatattendu;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.IndicateurFacadeLocal;
import sessions.InterventionpndsFacadeLocal;
import sessions.ObjectifOppDistrictFacadeLocal;
import sessions.ResultatAttenduDistrictFacadeLocal;
import sessions.ResultatattenduFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class ResultatAttenduDistrictController {

    @EJB
    private ResultatAttenduDistrictFacadeLocal resultatAttenduDistrictFacadeLocal;
    private ResultatAttenduDistrict resultatAttenduDistrict = new ResultatAttenduDistrict();
    private List<ResultatAttenduDistrict> resultatAttenduDistricts = new ArrayList<>();

    @EJB
    private InterventionpndsFacadeLocal interventionpndsFacadeLocal;
    private Interventionpnds interventionpnds = new Interventionpnds();
    private List<Interventionpnds> interventionpndses = new ArrayList<>();

    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();

    @EJB
    private ResultatattenduFacadeLocal resultatattenduFacadeLocal;
    private Resultatattendu resultatattendu = new Resultatattendu();
    private List<Resultatattendu> resultatattendus = new ArrayList<>();

    @EJB
    private ObjectifOppDistrictFacadeLocal objectifOppDistrictFacadeLocal;
    private ObjectifOppDistrict objectifOppDistrict = new ObjectifOppDistrict();

    private boolean detail = true;

    private boolean imported = true;

    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public ResultatAttenduDistrictController() {
    }

    @PostConstruct
    private void init() {
        resultatAttenduDistrict = new ResultatAttenduDistrict();
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
            resultatAttenduDistrict = new ResultatAttenduDistrict();
            indicateur = new Indicateur();
            interventionpnds = new Interventionpnds();
            indicateurs.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
        try {
            objectifOppDistrict = new ObjectifOppDistrict();
            if (resultatAttenduDistrict != null) {
                List<ObjectifOppDistrict> objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict(), resultatAttenduDistrict.getIdindicateur().getIdinterventionpnds());
                if (!objectifOppDistricts.isEmpty()) {
                    objectifOppDistrict = objectifOppDistricts.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {

            resultatAttenduDistrict.setIdresultatAttenduDistrict(resultatAttenduDistrictFacadeLocal.nextId());
            resultatAttenduDistrict.setIdindicateur(indicateur);
            resultatAttenduDistrict.setIddistrict(SessionMBean.getDistrict());
            resultatAttenduDistrictFacadeLocal.create(resultatAttenduDistrict);
            resultatAttenduDistrict = new ResultatAttenduDistrict();

            JsfUtil.addSuccessMessage("Opération réussie");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (resultatAttenduDistrict != null) {
                resultatAttenduDistrictFacadeLocal.remove(resultatAttenduDistrict);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importer() {
        try {
            List<Resultatattendu> resultatattendus = resultatattenduFacadeLocal.findAllRange();
            resultatAttenduDistricts.clear();
            for (Resultatattendu r : resultatattendus) {
                ResultatAttenduDistrict r1 = new ResultatAttenduDistrict();
                r1.setIdresultatAttenduDistrict(resultatAttenduDistrictFacadeLocal.nextId());
                r1.setIdindicateur(r.getIdindicateur());
                r1.setIddistrict(SessionMBean.getDistrict());
                if ("fr".equals(SessionMBean.getLangue())) {
                    r1.setResultat(r.getResultatFr());
                } else {
                    r1.setResultat(r.getResultatEn());
                }
                resultatAttenduDistrictFacadeLocal.create(r1);
            }
            JsfUtil.addSuccessMessage("Données importées avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjectifOppDistrict findObjectif(ResultatAttenduDistrict rad) {
        ObjectifOppDistrict objectifOppDistrict = new ObjectifOppDistrict();
        try {
            List<ObjectifOppDistrict> objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict(), rad.getIdindicateur().getIdinterventionpnds());
            if (!objectifOppDistricts.isEmpty()) {
                objectifOppDistrict = objectifOppDistricts.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectifOppDistrict;
    }

    public void updateIndicateur() {
        try {
            indicateurs.clear();
            if (interventionpnds.getIdinterventionpnds() != null) {
                objectifOppDistrict = new ObjectifOppDistrict();
                interventionpnds = interventionpndsFacadeLocal.find(interventionpnds.getIdinterventionpnds());
                List<Indicateur> indi = indicateurFacadeLocal.findByIntervention(interventionpnds);
                List<Indicateur> indi1 = new ArrayList<>();
                List<Indicateur> indi2 = new ArrayList<>();

                if (!indi.isEmpty()) {

                    List<Resultatattendu> resultats = resultatattenduFacadeLocal.findAll();
                    if (!resultats.isEmpty()) {
                        for (Resultatattendu r : resultats) {
                            if (r.getIdindicateur().getIdinterventionpnds().equals(interventionpnds)) {
                                indi1.add(r.getIdindicateur());
                            }
                        }
                    }

                    List<ResultatAttenduDistrict> rats = resultatAttenduDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                    if (!rats.isEmpty()) {
                        for (ResultatAttenduDistrict r1 : rats) {
                            indi2.add(r1.getIdindicateur());
                        }
                    }

                    for (Indicateur i : indi1) {
                        if (!indi2.contains(i)) {
                            if (!indicateurs.contains(i)) {
                                indicateurs.add(i);
                            }
                        }
                    }

                    List<ObjectifOppDistrict> objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict(), interventionpnds);
                    if (!objectifOppDistricts.isEmpty()) {
                        objectifOppDistrict = objectifOppDistricts.get(0);
                    }

                } else {
                    JsfUtil.addErrorMessage("Cette intervention n a aucun indicateur");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateResultat() {
        try {
            if (indicateur.getIdindicateur() != null) {
                indicateur = indicateurFacadeLocal.find(indicateur.getIdindicateur());
                List<Resultatattendu> rs = resultatattenduFacadeLocal.findByIndicateur(indicateur);
                if (!rs.isEmpty()) {
                    if ("en".equals(SessionMBean.getLangue())) {
                        resultatAttenduDistrict.setResultat(rs.get(0).getResultatEn());
                    } else {
                        resultatAttenduDistrict.setResultat(rs.get(0).getResultatFr());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (resultatAttenduDistrict != null) {
                resultatAttenduDistrictFacadeLocal.edit(resultatAttenduDistrict);
                JsfUtil.addErrorMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnnée");
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

    public Resultatattendu getResultatattendu() {
        return resultatattendu;
    }

    public void setResultatattendu(Resultatattendu resultatattendu) {
        this.resultatattendu = resultatattendu;
    }

    public List<Resultatattendu> getResultatattendus() {
        return resultatattendus;
    }

    public void setResultatattendus(List<Resultatattendu> resultatattendus) {
        this.resultatattendus = resultatattendus;
    }

    public ResultatAttenduDistrict getResultatAttenduDistrict() {
        return resultatAttenduDistrict;
    }

    public void setResultatAttenduDistrict(ResultatAttenduDistrict resultatAttenduDistrict) {
        this.resultatAttenduDistrict = resultatAttenduDistrict;
    }

    public List<ResultatAttenduDistrict> getResultatAttenduDistricts() {
        try {
            resultatAttenduDistricts = resultatAttenduDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
            if (resultatAttenduDistricts.isEmpty()) {
                imported = false;
            } else {
                imported = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultatAttenduDistricts;
    }

    public void setResultatAttenduDistricts(List<ResultatAttenduDistrict> resultatAttenduDistricts) {
        this.resultatAttenduDistricts = resultatAttenduDistricts;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public List<Indicateur> getIndicateurs() {
        return indicateurs;
    }

    public void setIndicateurs(List<Indicateur> indicateurs) {
        this.indicateurs = indicateurs;
    }

    public Interventionpnds getInterventionpnds() {
        return interventionpnds;
    }

    public void setInterventionpnds(Interventionpnds interventionpnds) {
        this.interventionpnds = interventionpnds;
    }

    public List<Interventionpnds> getInterventionpndses() {
        try {
            interventionpndses = interventionpndsFacadeLocal.findAllRangeCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interventionpndses;
    }

    public void setInterventionpndses(List<Interventionpnds> interventionpndses) {
        this.interventionpndses = interventionpndses;
    }

    public ObjectifOppDistrict getObjectifOppDistrict() {
        return objectifOppDistrict;
    }

    public void setObjectifOppDistrict(ObjectifOppDistrict objectifOppDistrict) {
        this.objectifOppDistrict = objectifOppDistrict;
    }

}
