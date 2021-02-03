/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Pathologie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.PathologieFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class PathologieController {

    /**
     * Creates a new instance of PathologieController
     */
    @EJB
    private PathologieFacadeLocal pathologieFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Pathologie pathologie;
    private List<Pathologie> pathologies = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public PathologieController() {

    }

    @PostConstruct
    private void init() {
        pathologie = new Pathologie();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        pathologie = new Pathologie();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void savePathologie() {
        try {

            if (mode == "Create") {
                pathologie.setIdpathologie(pathologieFacadeLocal.nextId());
                List<Pathologie> test = pathologieFacadeLocal.findByNom(pathologie.getNomFr());

                if (test.isEmpty()) {
                    pathologieFacadeLocal.create(pathologie);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' pathologie -> " + pathologie.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Une pathologie portant de nom existe dejà");
                }
            } else {
                Pathologie test = pathologieFacadeLocal.find(pathologie.getIdpathologie());
                utilitaire.Utilitaires.saveOperation("Modification du pathologie -> " + test.getNomFr() + " par -> " + pathologie.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                pathologieFacadeLocal.edit(pathologie);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePathologie() {
        try {
            if (pathologie != null) {

                pathologieFacadeLocal.remove(pathologie);
                Utilitaires.saveOperation("Suppression du pathologie -> " + pathologie.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun pathologie selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pathologie getPathologie() {
        return pathologie;
    }

    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    public List<Pathologie> getPathologies() {
        pathologies = pathologieFacadeLocal.findAll();
        return pathologies;
    }

    public void setPathologies(List<Pathologie> pathologies) {
        this.pathologies = pathologies;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
