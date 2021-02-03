/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.ElementCout;
import entities.Groupeutilisateur;
import entities.Mouchard;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ElementCoutFacadeLocal;
import sessions.MouchardFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ElementcoutController {

    /**
     * Creates a new instance of GroupeUtilisateurController
     */
    @EJB
    private ElementCoutFacadeLocal elementcoutFacadeLocal;
    private ElementCout elementCout;
    private List<ElementCout> elementCouts = new ArrayList<>();
    private ElementCout SelectedElementCout;

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    public ElementcoutController() {

    }

    @PostConstruct
    private void init() {
        mouchard = new Mouchard();
        SelectedElementCout = new ElementCout();
        elementCout = new ElementCout();
    }

    public void prepareCreate() {
        elementCout = new ElementCout();
        SelectedElementCout = new ElementCout();
    }

    public void prepareEdit() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void saveElement() {
        try {
             List<ElementCout> element = elementcoutFacadeLocal.findbyElementCout(elementCout.getNomFr());
            if (element.isEmpty()) {
                elementCout.setIdelementCout(elementcoutFacadeLocal.nextId());
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement de l'element Cout : " + elementCout.getNomFr());
                mouchard.setDateaction(new Date());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                elementcoutFacadeLocal.create(elementCout);
                mouchardFacadeLocal.create(mouchard);
                elementCout = new ElementCout();
                JsfUtil.addSuccessMessage("Opération éffectuée");
            } else {
                JsfUtil.addErrorMessage("Cet Element existe déja");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editElementcout() {
        if (SelectedElementCout.getIdelementCout() != null) {
              ElementCout gr = elementcoutFacadeLocal.find(SelectedElementCout.getIdelementCout());
            elementcoutFacadeLocal.edit(SelectedElementCout);
            mouchard.setIdoperation(mouchardFacadeLocal.nextId());
            mouchard.setAction("Modification du groupe utilisateur : " + gr.getNomFr() + "->" + " " + SelectedElementCout.getNomFr());
            mouchard.setDateaction(new Date());
            mouchard.setIdutilisateur(SessionMBean.getUser());
            mouchardFacadeLocal.create(mouchard);
            SelectedElementCout = new ElementCout();
            JsfUtil.addSuccessMessage("Elément Cout mis à jour");
        } else {
            JsfUtil.addErrorMessage("veuillez selectionnez une Ligne");
        }
    }

    public void deleteElementCout() {
        if (SelectedElementCout.getIdelementCout()!= null) {
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Suppression d'un Elément  " + SelectedElementCout.getNomFr());
                    mouchard.setDateaction(new Date());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    elementcoutFacadeLocal.remove(SelectedElementCout);
                    mouchardFacadeLocal.create(mouchard);
                    SelectedElementCout = new ElementCout();
                    JsfUtil.addSuccessMessage("Opération réussie");
                
        } else {
            JsfUtil.addErrorMessage("veuillez selectionner une Ligne");
        }

    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public ElementCout getElementCout() {
        return elementCout;
    }

    public void setElementCout(ElementCout elementCout) {
        this.elementCout = elementCout;
    }

    public List<ElementCout> getElementCouts() {
        elementCouts=elementcoutFacadeLocal.findAll();
        return elementCouts;
    }

    public void setElementCouts(List<ElementCout> elementCouts) {
        this.elementCouts = elementCouts;
    }

    public ElementCout getSelectedElementCout() {
        return SelectedElementCout;
    }

    public void setSelectedElementCout(ElementCout SelectedElementCout) {
        this.SelectedElementCout = SelectedElementCout;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }


}
