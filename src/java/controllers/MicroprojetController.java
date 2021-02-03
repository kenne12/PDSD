/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.SessionMBean;
import entities.ActiviteStructure;
import entities.Structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ActiviteStructureFacadeLocal;
import sessions.StructureFacadeLocal;
import utilitaire.Printer;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class MicroprojetController {

    @EJB
    private ActiviteStructureFacadeLocal activiteStructureFacadeLocal;
    private List<ActiviteStructure> activiteStructures = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public MicroprojetController() {
    }

    @PostConstruct
    private void init() {
        structure = new Structure();
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
                activiteStructures = activiteStructureFacadeLocal.find(structure, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAnnee() {

    }

    public void print() {
        try {
            if (structure.getIdstructure() != null) {
                Printer printer = new Printer();
                Map param = new HashMap();
                param.put("idstructure", structure.getIdstructure());
                printer.print("/report/microplan/couverturemicroplan.jasper", param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uptadeTable() {

    }

    public void updateCommentaire() {

    }

    public void create() {

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

    public List<ActiviteStructure> getActiviteStructures() {
        return activiteStructures;
    }

    public void setActiviteStructures(List<ActiviteStructure> activiteStructures) {
        this.activiteStructures = activiteStructures;
    }

}
