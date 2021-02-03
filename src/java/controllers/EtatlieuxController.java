/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Etatinfrastructure;
import entities.Infrastructure;
import entities.Profilpersonnel;
import entities.Rhs;
import entities.Structure;
import entities.TypeinfraTypestruc;
import entities.Typestructure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AnneeFacadeLocal;
import sessions.EtatinfrastructureFacadeLocal;
import sessions.InfrastructureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProfilpersonnelFacadeLocal;
import sessions.RhsFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypeinfraTypestrucFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class EtatlieuxController implements Serializable{

    @EJB
    private RhsFacadeLocal rhsFacadeLocal;
    private List<Rhs> rhses = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee;
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private ProfilpersonnelFacadeLocal profilpersonnelFacadeLocal;
    private List<Profilpersonnel> profilpersonnels = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Boolean detail = true;

    @PostConstruct
    private void init() {
        annee = new Annee();

        /**
         * ************** partie infra *********************
         */
        typestructure = new Typestructure();
        structure = new Structure();
        typeinfraTypestruc = new TypeinfraTypestruc();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        annee = new Annee();
    }

    public void prepareEdit() {

    }

    public void uptadeTable() {
        try {
            rhses.clear();
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());
                if (structure != null) {
                    if (!this.getProfilpersonnels().isEmpty()) {

                        if (rhsFacadeLocal.find(structure, annee).isEmpty()) {
                            for (Profilpersonnel p : this.getProfilpersonnels()) {
                                Rhs rhs = new Rhs();
                                rhs.setIdannee(annee);
                                rhs.setIdprofilpersonnel(p);
                                rhs.setIdstructure(structure);
                                rhs.setValeur(0);
                                rhses.add(rhs);
                            }
                        } else {
                            for (Profilpersonnel p : this.getProfilpersonnels()) {
                                List<Rhs> temp = rhsFacadeLocal.find(structure, p, annee);
                                if (temp.isEmpty()) {
                                    Rhs rhs = new Rhs();
                                    rhs.setIdannee(annee);
                                    rhs.setIdprofilpersonnel(p);
                                    rhs.setIdstructure(structure);
                                    rhs.setValeur(0);
                                    rhses.add(rhs);
                                } else {
                                    rhses.add(temp.get(0));
                                }
                            }
                        }
                    } else {
                        System.err.println("aucun profil");
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez selectionnner une ligne");
                }
            } else {
                JsfUtil.addErrorMessage("Veillez selectionner une année");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (!rhses.isEmpty()) {
                for (Rhs r : rhses) {
                    if (r.getIdrhs() == null) {
                        r.setIdrhs(rhsFacadeLocal.nextId());
                        rhsFacadeLocal.create(r);
                    } else {
                        rhsFacadeLocal.edit(r);
                    }
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findRhs(Structure structure, Profilpersonnel profilpersonnel) {
        String resultat = "";
        try {
            if (annee.getIdannee() != null) {
                annee = anneeFacadeLocal.find(annee.getIdannee());

                if (structure != null) {
                    if (profilpersonnel != null) {

                        List<Rhs> rhses = rhsFacadeLocal.find(structure, profilpersonnel, annee);
                        if (!rhses.isEmpty()) {
                            resultat = "" + rhses.get(0).getValeur();
                        } else {
                            resultat = "";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        return mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findAll();
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
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
            if (SessionMBean.getDistrict() != null) {
                structures = structureFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public List<Profilpersonnel> getProfilpersonnels() {
        profilpersonnels = profilpersonnelFacadeLocal.findAll();
        return profilpersonnels;
    }

    public void setProfilpersonnels(List<Profilpersonnel> profilpersonnels) {
        this.profilpersonnels = profilpersonnels;
    }

    public List<Rhs> getRhses() {
        return rhses;
    }

    public void setRhses(List<Rhs> rhses) {
        this.rhses = rhses;
    }

    /**
     * ***************************************************** partie
     * infrastructure ****************************************
     */
    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();

    private Structure structure1;
    private List<Structure> structures1 = new ArrayList<>();

    @EJB
    private TypeinfraTypestrucFacadeLocal typeinfraTypestrucFacadeLocal;
    private TypeinfraTypestruc typeinfraTypestruc;
    private List<TypeinfraTypestruc> typeinfraTypestrucs = new ArrayList<>();

    @EJB
    private InfrastructureFacadeLocal infrastructureFacadeLocal;
    private List<Infrastructure> infrastructures = new ArrayList<>();

    @EJB
    private EtatinfrastructureFacadeLocal etatinfrastructureFacadeLocal;
    private List<Etatinfrastructure> etatinfrastructures = new ArrayList<>();

    private String mode = "";

    public void uptadeTable1() {
        try {
            infrastructures.clear();

            if (structure != null) {
                if (!this.getTypeinfraTypestrucs().isEmpty()) {

                    if (infrastructureFacadeLocal.find(structure).isEmpty()) {
                        for (TypeinfraTypestruc t : this.typeinfraTypestrucs) {
                            Infrastructure infrastructure = new Infrastructure();
                            infrastructure.setIdstructure(structure);
                            infrastructure.setIdtypeinfraTypestruc(t);
                            infrastructures.add(infrastructure);
                        }
                    } else {
                        for (TypeinfraTypestruc t : this.typeinfraTypestrucs) {
                            List<Infrastructure> temp = infrastructureFacadeLocal.find(structure, t);
                            if (temp.isEmpty()) {
                                Infrastructure infrastructure = new Infrastructure();
                                infrastructure.setIdstructure(structure);
                                infrastructure.setIdtypeinfraTypestruc(t);
                                infrastructures.add(infrastructure);
                            } else {
                                infrastructures.add(temp.get(0));
                            }
                        }
                    }
                } else {
                    System.err.println("aucun profil");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionnner une ligne");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!infrastructures.isEmpty()) {
                for (Infrastructure i : infrastructures) {
                    if (i.getIdinfrastructure() == null) {
                        i.setIdinfrastructure(infrastructureFacadeLocal.nextId());
                        infrastructureFacadeLocal.create(i);
                    } else {
                        infrastructureFacadeLocal.edit(i);
                    }
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            if (typestructure.getIdtypestructure() != null) {
                typestructure = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                structures = structureFacadeLocal.find(SessionMBean.getDistrict().getIddistrict(), typestructure);
                typeinfraTypestrucs = typeinfraTypestrucFacadeLocal.findByTypestructure(typestructure);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Infrastructure findInfrastructure(Structure structure, TypeinfraTypestruc typeinfraTypestruc) {
        Infrastructure infrastructure = null;
        try {
            if (typestructure.getIdtypestructure() != null) {
                if (structure != null) {
                    if (typeinfraTypestruc != null) {

                        List<Infrastructure> temp = infrastructureFacadeLocal.find(structure, typeinfraTypestruc);
                        if (!temp.isEmpty()) {
                            infrastructure = temp.get(0);
                        } else {
                            infrastructure = new Infrastructure();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infrastructure;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Structure getStructure1() {
        return structure1;
    }

    public void setStructure1(Structure structure1) {
        this.structure1 = structure1;
    }

    public List<Structure> getStructures1() {
        return structures1;
    }

    public void setStructures1(List<Structure> structures1) {
        this.structures1 = structures1;
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

    public TypeinfraTypestruc getTypeinfraTypestruc() {
        return typeinfraTypestruc;
    }

    public void setTypeinfraTypestruc(TypeinfraTypestruc typeinfraTypestruc) {
        this.typeinfraTypestruc = typeinfraTypestruc;
    }

    public List<TypeinfraTypestruc> getTypeinfraTypestrucs() {
        return typeinfraTypestrucs;
    }

    public void setTypeinfraTypestrucs(List<TypeinfraTypestruc> typeinfraTypestrucs) {
        this.typeinfraTypestrucs = typeinfraTypestrucs;
    }

    public List<Etatinfrastructure> getEtatinfrastructures() {
        etatinfrastructures = etatinfrastructureFacadeLocal.findAll();
        return etatinfrastructures;
    }

    public void setEtatinfrastructures(List<Etatinfrastructure> etatinfrastructures) {
        this.etatinfrastructures = etatinfrastructures;
    }

    public List<Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public void setInfrastructures(List<Infrastructure> infrastructures) {
        this.infrastructures = infrastructures;
    }

}
