/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import java.util.ArrayList;
import java.util.List;
import entities.Typestructure;
import entities.TypeinfraTypestruc;
import entities.Typeinfrastructure;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import utilitaire.Utilitaires;
import sessions.TypeinfraTypestrucFacadeLocal;
import sessions.TypestructureFacadeLocal;
import sessions.TypeinfrastructureFacadeLocal;
/**
 *
 * @author hp
 */

@ManagedBean
@ViewScoped
public class TypeinfratypesstructureController {

    @EJB
    private TypeinfraTypestrucFacadeLocal typeinfrastructuretypestructureFacadeLocal;
    private TypeinfraTypestruc typeinfrastructuretypestructure;
    private List<TypeinfraTypestruc> typeinfrastructuretypestructures = new ArrayList<>();

    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    private TypeinfrastructureFacadeLocal typeinfrastructureFacadeLocal;
    private List<Typeinfrastructure> typeinfrastructure = new ArrayList<>();
    private List<Typeinfrastructure> selectedTypeinfrastructures = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;

    private boolean detail = true;

    private String mode = "";

       /**
     * Creates a new instance of Typeinfratypesstructure
     */
    public TypeinfratypesstructureController() {
    }
   
  
    @PostConstruct
    private void init() {
        typeinfrastructuretypestructure = new TypeinfraTypestruc();
        typestructure = new Typestructure();
    }

    public void prepareCreate() {
        mode = "Create";
    }

    public void prepareEdit() {
        mode = "Edit";

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void filterTypeinfrastructure() {
        typeinfrastructure.clear();
        try {
            if (typestructure.getIdtypestructure() != null) {
                typestructure = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                List<Typeinfrastructure> typeinfrastructureFinal = typeinfrastructureFacadeLocal.findAll();
                List<TypeinfraTypestruc> typeinfrastructuretypestructuresTemp = typeinfrastructuretypestructureFacadeLocal.findByTypestructure(typestructure);

                if (typeinfrastructuretypestructuresTemp.isEmpty()) {
                    typeinfrastructure = typeinfrastructureFacadeLocal.findAll();
                } else {
                    List<Typeinfrastructure> typeinfrastructureTemp = new ArrayList<>();
                    for (TypeinfraTypestruc p : typeinfrastructuretypestructuresTemp) {
                        typeinfrastructureTemp.add(p.getIdtypeinfrastructure());
                    }

                    for (Typeinfrastructure i : typeinfrastructureFinal) {
                        if (!typeinfrastructureTemp.contains(i)) {
                            typeinfrastructure.add(i);
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucun typeinfrastructure selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (mode.equals("Create")) {
                if (typestructure.getIdtypestructure() != null) {
                    typestructure = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                    if (!selectedTypeinfrastructures.isEmpty()) {
                        for (Typeinfrastructure p : selectedTypeinfrastructures) {
                            TypeinfraTypestruc typeinfrastructuretypestructure = new TypeinfraTypestruc();
                            typeinfrastructuretypestructure.setIdtypeinfraTypestruc(typeinfrastructuretypestructureFacadeLocal.nextId());
                            typeinfrastructuretypestructure.setIdtypeinfrastructure(p);
                            typeinfrastructuretypestructure.setIdtypestructure(typestructure);
                            typeinfrastructuretypestructureFacadeLocal.create(typeinfrastructuretypestructure);
                            Utilitaires.saveOperation("Association de type infrastructure  -> " + p.getNomFr()+ " ; Au Type de structure  -> " + typestructure.getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                        }
                        JsfUtil.addSuccessMessage("Opération reussie");
                    } else {
                        JsfUtil.addErrorMessage("Aucun type infrastructure sélectionné");
                    }
                } else {
                    JsfUtil.addErrorMessage("Aucune typestructure sélectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (typeinfrastructuretypestructure != null) {
                typeinfrastructuretypestructureFacadeLocal.remove(typeinfrastructuretypestructure);
                Utilitaires.saveOperation("Suppression de l'appartenance de l'typeinfrastructure -> " + typeinfrastructuretypestructure.getIdtypeinfrastructure().getNomFr()+ " A l'typestructure -> " + typeinfrastructuretypestructure.getIdtypestructure().getNomFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                typeinfrastructuretypestructure = new TypeinfraTypestruc();
                JsfUtil.addSuccessMessage("Opération réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Typeinfrastructure> getTypeinfrastructures() {
        return typeinfrastructure;
    }

    public void setTypeinfrastructures(List<Typeinfrastructure> typeinfrastructure) {
        this.typeinfrastructure = typeinfrastructure;
    }

    public List<Typeinfrastructure> getSelectedTypeinfrastructures() {
        return selectedTypeinfrastructures;
    }

    public void setSelectedTypeinfrastructures(List<Typeinfrastructure> selectedTypeinfrastructures) {
        this.selectedTypeinfrastructures = selectedTypeinfrastructures;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public TypeinfraTypestruc getTypeinfraTypestruc() {
        return typeinfrastructuretypestructure;
    }

    public void setTypeinfraTypestruc(TypeinfraTypestruc typeinfrastructuretypestructure) {
        this.typeinfrastructuretypestructure = typeinfrastructuretypestructure;
    }

    public List<TypeinfraTypestruc> getTypeinfraTypestrucs() {
        typeinfrastructuretypestructures = typeinfrastructuretypestructureFacadeLocal.findAll();
        return typeinfrastructuretypestructures;
    }

    public void setTypeinfraTypestrucs(List<TypeinfraTypestruc> typeinfrastructuretypestructures) {
        this.typeinfrastructuretypestructures = typeinfrastructuretypestructures;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public List<Typestructure> getTypestructures() {
        typestructures = typestructureFacadeLocal.findAll();
        return typestructures;
    }

    public void setTypestructures(List<Typestructure> typestructures) {
        this.typestructures = typestructures;
    }

}
