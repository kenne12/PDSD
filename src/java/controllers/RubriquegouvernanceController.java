/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquegouvernance;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquegouvernanceFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriquegouvernanceController {

    /**
     * Creates a new instance of RubriquegouvernanceController
     */
    @EJB
    private RubriquegouvernanceFacadeLocal rubriquegouvernanceFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriquegouvernance rubriquegouvernance;
    private List<Rubriquegouvernance> rubriquegouvernances = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriquegouvernanceController() {

    }

    @PostConstruct
    private void init() {
        rubriquegouvernance = new Rubriquegouvernance();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriquegouvernance = new Rubriquegouvernance();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriquegouvernance() {
        try {

            if (mode == "Create") {
                rubriquegouvernance.setIdrubriquegouvernance(rubriquegouvernanceFacadeLocal.nextId());
                List<Rubriquegouvernance> test = rubriquegouvernanceFacadeLocal.findByNom(rubriquegouvernance.getNomFr());

                if (test.isEmpty()) {
                    rubriquegouvernanceFacadeLocal.create(rubriquegouvernance);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de la rubrique de gouvernance -> " + rubriquegouvernance.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubrique de gouvernance portant de nom existe dejà");
                }
            } else {
                Rubriquegouvernance test = rubriquegouvernanceFacadeLocal.find(rubriquegouvernance.getIdrubriquegouvernance());
                utilitaire.Utilitaires.saveOperation("Modification du rubrique de gouvernance -> " + test.getNomFr() + " par -> " + rubriquegouvernance.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriquegouvernanceFacadeLocal.edit(rubriquegouvernance);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriquegouvernance() {
        try {
            if (rubriquegouvernance != null) {

                rubriquegouvernanceFacadeLocal.remove(rubriquegouvernance);
                Utilitaires.saveOperation("Suppression du rubriquegouvernance -> " + rubriquegouvernance.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun rubriquegouvernance selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rubriquegouvernance getRubriquegouvernance() {
        return rubriquegouvernance;
    }

    public void setRubriquegouvernance(Rubriquegouvernance rubriquegouvernance) {
        this.rubriquegouvernance = rubriquegouvernance;
    }

    public List<Rubriquegouvernance> getRubriquegouvernances() {
        rubriquegouvernances = rubriquegouvernanceFacadeLocal.findAll();
        return rubriquegouvernances;
    }

    public void setRubriquegouvernances(List<Rubriquegouvernance> rubriquegouvernances) {
        this.rubriquegouvernances = rubriquegouvernances;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
