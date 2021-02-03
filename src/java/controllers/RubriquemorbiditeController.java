/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Rubriquemorbidite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.RubriquemorbiditeFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class RubriquemorbiditeController {

    /**
     * Creates a new instance of RubriquemorbiditeController
     */
    @EJB
    private RubriquemorbiditeFacadeLocal rubriquemorbiditeFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private Rubriquemorbidite rubriquemorbidite;
    private List<Rubriquemorbidite> rubriquemorbidites = new ArrayList<>();
    private Boolean detail = true;

    private String mode = "";

    public RubriquemorbiditeController() {

    }

    @PostConstruct
    private void init() {
        rubriquemorbidite = new Rubriquemorbidite();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        rubriquemorbidite = new Rubriquemorbidite();
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";
    }

    public void saveRubriquemorbidite() {
        try {

            if (mode == "Create") {
                rubriquemorbidite.setIdrubriquemorbidite(rubriquemorbiditeFacadeLocal.nextId());
                List<Rubriquemorbidite> test = rubriquemorbiditeFacadeLocal.findByNom(rubriquemorbidite.getNomFr());

                if (test.isEmpty()) {
                    rubriquemorbiditeFacadeLocal.create(rubriquemorbidite);
                    utilitaire.Utilitaires.saveOperation("Enregistrement de  rubriquemorbidite -> " + rubriquemorbidite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Un rubriquemorbidite portant de nom existe dejà");
                }
            } else {
                Rubriquemorbidite test = rubriquemorbiditeFacadeLocal.find(rubriquemorbidite.getIdrubriquemorbidite());
                utilitaire.Utilitaires.saveOperation("Modification du rubriquemorbidite -> " + test.getNomFr() + " par -> " + rubriquemorbidite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                rubriquemorbiditeFacadeLocal.edit(rubriquemorbidite);
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRubriquemorbidite() {
        try {
            if (rubriquemorbidite != null) {

                rubriquemorbiditeFacadeLocal.remove(rubriquemorbidite);
                Utilitaires.saveOperation("Suppression du rubriquemorbidite -> " + rubriquemorbidite.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun rubriquemorbidite selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rubriquemorbidite getRubriquemorbidite() {
        return rubriquemorbidite;
    }

    public void setRubriquemorbidite(Rubriquemorbidite rubriquemorbidite) {
        this.rubriquemorbidite = rubriquemorbidite;
    }

    public List<Rubriquemorbidite> getRubriquemorbidites() {
        rubriquemorbidites = rubriquemorbiditeFacadeLocal.findAll();
        return rubriquemorbidites;
    }

    public void setRubriquemorbidites(List<Rubriquemorbidite> rubriquemorbidites) {
        this.rubriquemorbidites = rubriquemorbidites;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

}
