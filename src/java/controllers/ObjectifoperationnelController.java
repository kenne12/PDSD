/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Interventionpnds;
import entities.ObjectifOppDistrict;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.InterventionpndsFacadeLocal;
import sessions.ObjectifOppDistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class ObjectifoperationnelController {

    @EJB
    private ObjectifOppDistrictFacadeLocal objectifOppDistrictFacadeLocal;
    private ObjectifOppDistrict objectifOppDistrict = new ObjectifOppDistrict();
    private List<ObjectifOppDistrict> objectifOppDistricts = new ArrayList<>();

    @EJB
    private InterventionpndsFacadeLocal interventionpndsFacadeLocal;
    private Interventionpnds interventionpnds = new Interventionpnds();
    private List<Interventionpnds> interventionpndses = new ArrayList<>();

    private boolean detail = true;

    private boolean imported = true;

    private String mode = "";

    /**
     * Creates a new instance of AxeController
     */
    public ObjectifoperationnelController() {
    }

    @PostConstruct
    private void init() {

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
            interventionpnds = new Interventionpnds();
            objectifOppDistrict = new ObjectifOppDistrict();

            interventionpndses.clear();

            List<Interventionpnds> ints = interventionpndsFacadeLocal.findAllRangeCode();
            List<Interventionpnds> ints1 = new ArrayList<>();

            List<ObjectifOppDistrict> objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());

            for (ObjectifOppDistrict o : objectifOppDistricts) {
                ints1.add(o.getIdintervention());
            }

            for (Interventionpnds i : ints) {
                if (!ints1.contains(i)) {
                    interventionpndses.add(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void create() {
        try {
            objectifOppDistrict.setIdobjectifOppDistrict(objectifOppDistrictFacadeLocal.nextId());
            objectifOppDistrict.setIddistrict(SessionMBean.getDistrict());
            objectifOppDistrict.setIdintervention(interventionpnds);
            objectifOppDistrictFacadeLocal.create(objectifOppDistrict);
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (objectifOppDistrict != null) {
                objectifOppDistrictFacadeLocal.remove(objectifOppDistrict);
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
            interventionpndses = interventionpndsFacadeLocal.findAllRange();
            for (Interventionpnds i : interventionpndses) {
                ObjectifOppDistrict o1 = new ObjectifOppDistrict();
                o1.setIdobjectifOppDistrict(objectifOppDistrictFacadeLocal.nextId());
                o1.setIddistrict(SessionMBean.getDistrict());
                o1.setIdintervention(i);

                if ("fr".equals(SessionMBean.getLangue())) {
                    o1.setObjectif(i.getObjectifOpFr());
                } else {
                    o1.setObjectif(i.getObjectifOpEn());
                }
                objectifOppDistrictFacadeLocal.create(o1);
            }
            JsfUtil.addSuccessMessage("Données importées avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateObjectif() {
        try {
            if (interventionpnds.getIdinterventionpnds() != null) {
                interventionpnds = interventionpndsFacadeLocal.find(interventionpnds.getIdinterventionpnds());
                if ("en".equals(SessionMBean.getLangue())) {
                    objectifOppDistrict.setObjectif(interventionpnds.getObjectifOpEn());
                } else {
                    objectifOppDistrict.setObjectif(interventionpnds.getObjectifOpFr());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (objectifOppDistrict != null) {
                objectifOppDistrictFacadeLocal.edit(objectifOppDistrict);
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

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public Interventionpnds getInterventionpnds() {
        return interventionpnds;
    }

    public void setInterventionpnds(Interventionpnds interventionpnds) {
        this.interventionpnds = interventionpnds;
    }

    public List<Interventionpnds> getInterventionpndses() {
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

    public List<ObjectifOppDistrict> getObjectifOppDistricts() {
        try {
            objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
            if (objectifOppDistricts.isEmpty()) {
                imported = false;
            } else {
                imported = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectifOppDistricts;
    }

    public void setObjectifOppDistricts(List<ObjectifOppDistrict> objectifOppDistricts) {
        this.objectifOppDistricts = objectifOppDistricts;
    }

}
