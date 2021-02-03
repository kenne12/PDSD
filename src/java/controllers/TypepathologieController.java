/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Typepathologie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.TypepathologieFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
  */
@ManagedBean
@ViewScoped
public class TypepathologieController {

    /**
     * Creates a new instance of TypepathologieController
      */
    @EJB
    private TypepathologieFacadeLocal typepathologieFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Typepathologie typepathologie;
    private List<Typepathologie> typepathologies = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public TypepathologieController() {

    }

    @PostConstruct
    private void init() {
        typepathologie = new Typepathologie();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        typepathologie = new Typepathologie();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveTypepathologie() {
        try {

            if (mode == "Create") {
                typepathologie.setIdtypepathologie(typepathologieFacadeLocal.nextId());
                List<Typepathologie> test = typepathologieFacadeLocal.findByNom(typepathologie.getNomFr());

                if (test.isEmpty()) {
                    typepathologieFacadeLocal.create(typepathologie);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de l' typepathologie -> " + typepathologie.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un typepathologie portant de nom existe dejà");
                }
            } else {
                Typepathologie test = typepathologieFacadeLocal.find(typepathologie.getIdtypepathologie());
                utilitaire.Utilitaires.saveOperation("Modification du typepathologie -> " + test.getNomFr()+ " par -> " + typepathologie.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typepathologieFacadeLocal.edit(typepathologie);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTypepathologie() {
        try {
            if (typepathologie != null) {
              
                    typepathologieFacadeLocal.remove(typepathologie);
                    Utilitaires.saveOperation("Suppression du typepathologie -> " + typepathologie.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
               
            } else {
                JsfUtil.addErrorMessage("Aucun typepathologie selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typepathologie getTypepathologie() {
        return typepathologie;
    }

    public void setTypepathologie(Typepathologie typepathologie) {
        this.typepathologie = typepathologie;
    }

    public List<Typepathologie> getTypepathologies() {
        typepathologies = typepathologieFacadeLocal.findAll();
        return typepathologies;
    }

    public void setTypepathologies(List<Typepathologie> typepathologies) {
        this.typepathologies = typepathologies;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

} 
 