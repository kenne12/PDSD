/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Adresse;
import entities.Airesante;
import entities.District;
import entities.Institution;
import entities.Structure;
import entities.Mouchard;
import entities.Quartier;
import entities.Rue;
import entities.Statutstructure;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AdresseFacadeLocal;
import sessions.AiresanteFacadeLocal;
import sessions.DepenseFacadeLocal;
import sessions.DistrictFacadeLocal;
import sessions.EquipementtraceurFacadeLocal;
import sessions.InfrastructureFacadeLocal;
import sessions.InstitutionFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.QuartierFacadeLocal;
import sessions.RecetteFacadeLocal;
import sessions.RhsFacadeLocal;
import sessions.RueFacadeLocal;
import sessions.StatutstructureFacadeLocal;
import sessions.TypestructureFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class StructureController {

    /**
     * Creates a new instance of StructureController
     */
    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure;
    private Structure selected;
    private List<Structure> structures = new ArrayList<>();
    
    @EJB
    private AdresseFacadeLocal adresseFacadeLocal;
    private Adresse adresse;
    
    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    
    @EJB
    private TypestructureFacadeLocal typestructureFacadeLocal;
    private Typestructure typestructure;
    private List<Typestructure> typestructures = new ArrayList<>();
    
    @EJB
    private AiresanteFacadeLocal airesanteFacadeLocal;
    private Airesante airesante;
    private List<Airesante> airesantes = new ArrayList<>();
    
    @EJB
    private StatutstructureFacadeLocal statutstructureFacadeLocal;
    private Statutstructure statutstructure;
    private List<Statutstructure> statutstructures = new ArrayList<>();
    
    @EJB
    private QuartierFacadeLocal quartierFacadeLocal;
    private Quartier quartier;
    private List<Quartier> quartiers = new ArrayList<>();
    
    @EJB
    private InstitutionFacadeLocal institutionFacadeLocal;
    private Institution institution = new Institution();
    private List<Institution> institutions = new ArrayList<>();
    
    @EJB
    private RueFacadeLocal rueFacadeLocal;
    private List<Rue> rues = new ArrayList<>();
    private Rue rue;
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;
    
    private boolean detail = true;
    private String mode = "";
    
    @EJB
    private RhsFacadeLocal rhsFacadeLocal;
    @EJB
    private DepenseFacadeLocal depenseFacadeLocal;
    @EJB
    private RecetteFacadeLocal recetteFacadeLocal;
    
    @EJB
    private InfrastructureFacadeLocal infrastructureFacadeLocal;
    
    @EJB
    private EquipementtraceurFacadeLocal equipementtraceurFacadeLocal;
    
    public StructureController() {
        
    }
    
    @PostConstruct
    private void init() {
        structure = new Structure();
        selected = new Structure();
        district = new District();
        typestructure = new Typestructure();
        adresse = new Adresse();
        mouchard = new Mouchard();
        airesante = new Airesante();
        districts = districtFacadeLocal.findAll();
        airesantes = airesanteFacadeLocal.findAll();
        statutstructure = new Statutstructure();
        
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void resetParents() {
        
    }
    
    public void prepareCreate() {
        structure = new Structure();
        structure.setDistance(0d);
        adresse = new Adresse();
        adresse.setBp("-");
        adresse.setEmail("-");
        adresse.setContact("-");
        adresse.setSiteWeb("-");
        structure.setLeader(false);
        institution = new Institution();
        mode = "Create";
        district = SessionMBean.getDistrict();
        try {
            airesantes = airesanteFacadeLocal.find(district);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        mode = "Edit";
        try {
            if (structure != null) {
                
                if (structure.getIdstatutstructure() != null) {
                    statutstructure = structure.getIdstatutstructure();
                }
                
                if (structure.getIdtypestructure() != null) {
                    typestructure = structure.getIdtypestructure();
                }
                
                if (structure.getIdadresse() != null) {
                    adresse = structure.getIdadresse();
                }
                
                institution = new Institution();
                if (structure.getIdinstitution() != null) {
                    institution = structure.getIdinstitution();
                }
                
                if (structure.getIdairesante() != null) {
                    airesante = structure.getIdairesante();
                    district = airesante.getIddistrict();
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void save() {
        try {
            if ("Create".equals(mode)) {
                List<Structure> test = structureFacadeLocal.findByNom(district, structure.getNomFr());
                if (test.isEmpty()) {
                    
                    adresse.setIdAdresse(adresseFacadeLocal.nextId());
                    
                    if (statutstructure.getIdstatutstructure() != null) {
                        structure.setIdstatutstructure(statutstructure);
                    }
                    
                    if (typestructure.getIdtypestructure() != null) {
                        structure.setIdstatutstructure(statutstructure);
                    }
                    
                    if (airesante.getIdairesante() != null) {
                        structure.setIdairesante(airesante);
                    }
                    
                    if (institution.getIdinstitution() != null) {
                        structure.setIdinstitution(institution);
                    }
                    
                    adresseFacadeLocal.create(adresse);
                    structure.setIdadresse(adresse);
                    structure.setIdstructure(structureFacadeLocal.nextId());
                    structure.setConsolide(false);
                    structureFacadeLocal.create(structure);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Enregistrement de la structure  -> " + structure.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    
                    structure = new Structure();
                    
                    this.getStructures();
                    JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
                } else {
                    JsfUtil.addErrorMessage("Une formation portant ce nom existe déjà !");
                }
            } else {
                if (structure != null) {
                    Structure u = structureFacadeLocal.find(structure.getIdstructure());
                    adresseFacadeLocal.edit(adresse);
                    
                    if (statutstructure.getIdstatutstructure() != null) {
                        statutstructure = statutstructureFacadeLocal.find(statutstructure.getIdstatutstructure());
                        structure.setIdstatutstructure(statutstructure);
                    }
                    
                    if (typestructure.getIdtypestructure() != null) {
                        typestructure = typestructureFacadeLocal.find(typestructure.getIdtypestructure());
                        structure.setIdstatutstructure(statutstructure);
                    }
                    
                    if (airesante.getIdairesante() != null) {
                        airesante = airesanteFacadeLocal.find(airesante.getIdairesante());
                        structure.setIdairesante(airesante);
                    }
                    
                    if (institution.getIdinstitution() != null) {
                        institution = institutionFacadeLocal.find(institution.getIdinstitution());
                        structure.setIdinstitution(institution);
                    }
                    
                    structureFacadeLocal.edit(structure);
                    mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                    mouchard.setAction("Modification de la formation sanitaire ->  " + u.getNomFr() + " -> " + structure.getNomFr());
                    mouchard.setIdutilisateur(SessionMBean.getUser());
                    mouchard.setDateaction(new Date());
                    mouchardFacadeLocal.create(mouchard);
                    detail = true;
                    JsfUtil.addSuccessMessage("La formation sanitaire a été mise à jour");
                } else {
                    JsfUtil.addErrorMessage("Aucune formation sanitaire sélectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void edit() {
        try {
            if (structure != null) {
                Structure u = structureFacadeLocal.find(structure.getIdstructure());
                
                adresseFacadeLocal.edit(adresse);
                structureFacadeLocal.edit(selected);
                
                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Modification de la formation sanitaire ->  " + u.getNomFr() + " -> " + structure.getNomFr());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                
                detail = true;
                JsfUtil.addSuccessMessage("La formation sanitaire a été mise à jour");
            } else {
                JsfUtil.addErrorMessage("Aucune formation sanitaire sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getStructures();
        }
    }
    
    public void delete() {
        try {
            if (structure != null) {
                if (structure.getActiviteStructureList().isEmpty()) {
                    if (depenseFacadeLocal.find(structure).isEmpty()) {
                        if (equipementtraceurFacadeLocal.find(structure).isEmpty()) {
                            if (infrastructureFacadeLocal.find(structure).isEmpty()) {
                                if (recetteFacadeLocal.find(structure).isEmpty()) {
                                    if (structure.getRhsList().isEmpty()) {
                                        if (structure.getGouvernancedistrictList().isEmpty()) {
                                            if (structure.getInformationsanitairedistrictList().isEmpty()) {
                                                structureFacadeLocal.remove(structure);
                                                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                                                mouchard.setAction("Suppression de la formation sanitaire -> " + structure.getNomFr());
                                                mouchard.setIdutilisateur(SessionMBean.getUser());
                                                mouchard.setDateaction(new Date());
                                                mouchardFacadeLocal.create(mouchard);
                                                detail = true;
                                                JsfUtil.addSuccessMessage("Suppression effectuée avec succès");
                                            } else {
                                                JsfUtil.addErrorMessage("Cette formation sanitaire contient plusieurs données sur l'information sanitaire");
                                            }
                                        } else {
                                            JsfUtil.addErrorMessage("Cette formation sanitaire contient plusieurs informations sur la gouvernance");
                                        }
                                        
                                    } else {
                                        JsfUtil.addErrorMessage("Cette formation sanitaire est liée à plusieurs recette");
                                    }
                                } else {
                                    JsfUtil.addErrorMessage("Cette formation sanitaire est liée à plusieurs recette");
                                }
                            } else {
                                JsfUtil.addErrorMessage("Cette formation sanitaire est liée à plusieurs infrastructures");
                            }
                        } else {
                            JsfUtil.addErrorMessage("Cette formation sanitaire est liée à plusieurs equipements");
                        }
                        
                    } else {
                        JsfUtil.addErrorMessage("Cette formation sanitaire est liée à plusieurs dépenses");
                    }
                } else {
                    JsfUtil.addErrorMessage("Cette formation sanitaire est liée à plusieurs activités");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getStructures();
        }
    }
    
    public void filterAire() {
        try {
            if (district.getIddistrict() != null) {
                district = districtFacadeLocal.find(district.getIddistrict());
                airesantes = airesanteFacadeLocal.find(district);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void filterRueByQuartier() {
        try {
            rues.clear();
            if (quartier != null) {
                rues = quartier.getRueList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public Structure getStructure() {
        return structure;
    }
    
    public void setStructure(Structure structure) {
        this.structure = structure;
    }
    
    public Structure getSelected() {
        return selected;
    }
    
    public void setSelected(Structure selected) {
        this.selected = selected;
    }
    
    public List<Structure> getStructures() {
        structures = structureFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        return structures;
    }
    
    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }
    
    public District getDistrict() {
        return district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }
    
    public List<District> getDistricts() {
        districts = districtFacadeLocal.findAllRange();
        return districts;
    }
    
    public void setDistricts(List<District> districts) {
        this.districts = districts;
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
    
    public Airesante getAiresante() {
        return airesante;
    }
    
    public void setAiresante(Airesante airesante) {
        this.airesante = airesante;
    }
    
    public List<Airesante> getAiresantes() {
        return airesantes;
    }
    
    public void setAiresantes(List<Airesante> airesantes) {
        this.airesantes = airesantes;
    }
    
    public Adresse getAdresse() {
        return adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public boolean isDetail() {
        return detail;
    }
    
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    public Statutstructure getStatutstructure() {
        return statutstructure;
    }
    
    public void setStatutstructure(Statutstructure statutstructure) {
        this.statutstructure = statutstructure;
    }
    
    public List<Statutstructure> getStatutstructures() {
        statutstructures = statutstructureFacadeLocal.findAllRange();
        return statutstructures;
    }
    
    public void setStatutstructures(List<Statutstructure> statutstructures) {
        this.statutstructures = statutstructures;
    }
    
    public Quartier getQuartier() {
        return quartier;
    }
    
    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }
    
    public List<Quartier> getQuartiers() {
        quartiers = quartierFacadeLocal.findAll();
        return quartiers;
    }
    
    public void setQuartiers(List<Quartier> quartiers) {
        this.quartiers = quartiers;
    }
    
    public List<Rue> getRues() {
        rues = rueFacadeLocal.findAll();
        return rues;
    }
    
    public void setRues(List<Rue> rues) {
        this.rues = rues;
    }
    
    public Rue getRue() {
        return rue;
    }
    
    public void setRue(Rue rue) {
        this.rue = rue;
    }
    
    public Institution getInstitution() {
        return institution;
    }
    
    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
    
    public List<Institution> getInstitutions() {
        institutions = institutionFacadeLocal.findAll();
        return institutions;
    }
    
    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }
    
}
