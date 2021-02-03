/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.ListetableauDistrict;
import entities.Listetableau;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ListetableauDistrictFacadeLocal;
import sessions.ListetableauFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ListetableaudistrictController {

    /**
     * Creates a new instance of ListetableauDistrictController
     */
    @EJB
    private ListetableauDistrictFacadeLocal listetableaudistrictFacadeLocal;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private ListetableauDistrict listetableaudistrict;
    private List<ListetableauDistrict> listetableaudistricts = new ArrayList<>();
    private List<ListetableauDistrict> listetableaudistrictTest = new ArrayList<>();

    private Boolean detail = true;
    @EJB
    private ListetableauFacadeLocal listetableauFacadeLocal;
    private Listetableau listetableau;
    private List<Listetableau> listetableaus = new ArrayList<>();
    
    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    private String mode = "";

    private DualListModel<Listetableau> dualList = new DualListModel<>();

    public ListetableaudistrictController() {

    }

    @PostConstruct
    private void init() {
        listetableaudistrict = new ListetableauDistrict();
        listetableau = new Listetableau();

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        try {
            dualList = new DualListModel<>();
            listetableaus.clear();
            listetableaudistrictTest.clear();
            
            handleDistrictCharge();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            listetableaus = listetableauFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // methode qui raffraichit la pickLKist
    public void handleDistrictCharge() {
        try {

            
            dualList.getTarget().clear();

            List<Listetableau> listetableaus = listetableauFacadeLocal.findAll();
            List<ListetableauDistrict> acteurdistricts = listetableaudistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());

            if (acteurdistricts.isEmpty()) {
                if (!listetableaus.isEmpty()) {
                    dualList.setSource(listetableaus);
                }
            } else {

                dualList.getSource().clear();
                //les acteur que le district possede
                List<Listetableau> acteurs1 = new ArrayList<>();

                for (ListetableauDistrict a : acteurdistricts) {
                    acteurs1.add(a.getIdlisttableau());
                }

                for (Listetableau a : listetableaus) {
                    if (!acteurs1.contains(a)) {
                        dualList.getSource().add(a);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //methode qui transfert les données de la pickList vers le tableau
    public void transfertEtape() {
        try {
            if (!dualList.getTarget().isEmpty()) {
                List<Listetableau> test = dualList.getTarget();
                for (int i = 0; i < test.size(); i++) {
                    ListetableauDistrict test1 = new ListetableauDistrict();
                    test1.setIdlisttableau(test.get(i));
                    test1.setIddistrict(SessionMBean.getDistrict());
                    test1.setNumpage(0);
                    listetableaudistrictTest.add(test1);
                }
            } else {
                JsfUtil.addErrorMessage("Aucune Listetableau  sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (!listetableaudistrictTest.isEmpty()) {
                for (ListetableauDistrict e : listetableaudistrictTest) {
                    e.setIdlistetableauDistrict(listetableaudistrictFacadeLocal.nextId());
                    e.setIddistrict(SessionMBean.getDistrict());
                    listetableaudistrictFacadeLocal.create(e);
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            if (listetableaudistrict != null) {
                listetableaudistrictFacadeLocal.edit(listetableaudistrict);
                Utilitaires.saveOperation("Modification de la valeur listetableau explotation : " + listetableaudistrict.getIdlistetableauDistrict(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage(" mis à jour reussi !");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionée ! ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteListetableauDistrict() {
        try {
            if (listetableaudistrict != null) {

                listetableaudistrictFacadeLocal.remove(listetableaudistrict);
                Utilitaires.saveOperation("Suppression du listetableaudistrict -> " + listetableaudistrict.getNumpage(), SessionMBean.getUser(), mouchardFacadeLocal);

            } else {
                JsfUtil.addErrorMessage("Aucun listetableaudistrict selectionné !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        return mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public ListetableauDistrict getListetableauDistrict() {
        return listetableaudistrict;
    }

    public void setListetableauDistrict(ListetableauDistrict listetableaudistrict) {
        this.listetableaudistrict = listetableaudistrict;
    }

    public List<ListetableauDistrict> getListetableauDistricts() {
        try {
            listetableaudistricts = listetableaudistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listetableaudistricts;
    }

    public void setListetableauDistricts(List<ListetableauDistrict> listetableaudistricts) {
        this.listetableaudistricts = listetableaudistricts;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Listetableau getListetableau() {
        return listetableau;
    }

    public void setListetableau(Listetableau listetableau) {
        this.listetableau = listetableau;
    }

    public List<Listetableau> getListetableaus() {

        listetableaus = listetableauFacadeLocal.findAll();

        return listetableaus;
    }

    public void setListetableaus(List<Listetableau> listetableaus) {
        this.listetableaus = listetableaus;
    }

    public DualListModel<Listetableau> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Listetableau> dualList) {
        this.dualList = dualList;
    }

    public List<ListetableauDistrict> getListetableauDistrictTest() {
        listetableaudistricts = listetableaudistrictFacadeLocal.findAll();
        return listetableaudistrictTest;
    }

    public void setListetableauDistrictTest(List<ListetableauDistrict> listetableaudistrictTest) {
        this.listetableaudistrictTest = listetableaudistrictTest;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
