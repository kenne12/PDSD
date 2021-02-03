/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.District;
import entities.Partiehaute;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PartiehauteFacadeLocal;
import utilitaire.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class PartiehauteController {

    /**
     * Creates a new instance of PartiehauteController
     */
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    
    @EJB
    private PartiehauteFacadeLocal partiehauteFacadeLocal;
    private Partiehaute partiehaute;
    private List<Partiehaute> partiehautes = new ArrayList<>();
    private Boolean detail = true;
    
    @EJB
    private DistrictFacadeLocal districtFacadeLocal;
    private District district;
    private List<District> districts = new ArrayList<>();
    private String carte = "";
    
    private String fichier_carte = null;
    private String msg = "";
    private boolean bouton = true;
    private boolean showPrintForm = true;
    private boolean selectModel = true;
    private Date date;
    UploadedFile file;
    private final String destination = Utilitaires.path + "/report/pdsd/images/";
    //variable qui contient la visibilité d'un bouton
    private boolean printable = true;
    
    private boolean isRegistred = false;
    
    private String repertoire = Utilitaires.path + "/" + Utilitaires.repertoireDefautVehicule;
    private String fichier = Utilitaires.nomFichierParDefautListeVehicule;
    
    public PartiehauteController() {
        
    }
    
    @PostConstruct
    private void init() {
        
        partiehaute = new Partiehaute();
        district = new District();
        try {
            partiehautes = partiehauteFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
            if (!partiehautes.isEmpty()) {
                isRegistred = true;
                partiehaute = partiehautes.get(0);
                
                if ("".equals(partiehautes.get(0).getCarte())) {
                    
                } else {
                    File f = new File(Utilitaires.path + "/report/pdsd/images/" + partiehautes.get(0).getCarte());
                    if (f.exists()) {
                        carte = "" + partiehautes.get(0).getCarte();
                    }
                }
                
            } else {
                isRegistred = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void savePartiehaute() {
        try {
            if (isRegistred) {
                partiehauteFacadeLocal.edit(partiehaute);
            } else {
                partiehaute.setIdpartiehaute(partiehauteFacadeLocal.nextId());
                if ("".equals(carte)) {
                    carte = Utilitaires.carAvatar;
                }
                partiehaute.setCarte(carte);
                partiehaute.setIddistrict(SessionMBean.getDistrict());
                partiehauteFacadeLocal.create(partiehaute);
                isRegistred = true;
                utilitaire.Utilitaires.saveOperation("Enregistrement de la partiehaute -> " + partiehaute.getIntroductionFr(), SessionMBean.getUser(), mouchardFacadeLocal);
                JsfUtil.addSuccessMessage("Operation réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        this.file = event.getFile();
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream(), destination);
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream(), Utilitaires.path + "/report/pdsd_en/images/");
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream(), Utilitaires.path + "/report/pdsd_fr/images/");
            carte = event.getFile().getFileName();
            
            if (isRegistred) {
                partiehaute.setCarte(carte);
                partiehauteFacadeLocal.edit(partiehaute);
            } else {
                partiehaute.setIdpartiehaute(partiehauteFacadeLocal.nextId());
                partiehaute.setIddistrict(SessionMBean.getDistrict());
                partiehaute.setPresentationgeneraleFr("-");
                partiehaute.setIntroductionFr("-");
                partiehaute.setCarte(carte);
                partiehaute.setSituationsocioculturelleFr("-");
                partiehaute.setSituationeconomiqueFr("-");
                partiehauteFacadeLocal.create(partiehaute);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void copyFile(String fileName, InputStream in, String destination) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream output = new FileOutputStream(new File(destination + fileName));
            
            int read = 0;
            byte[] bytes = new byte[128];
            while ((read = in.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            
            in.close();
            output.flush();
            output.close();
            
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Partiehaute getPartiehaute() {
        return partiehaute;
    }
    
    public void setPartiehaute(Partiehaute partiehaute) {
        this.partiehaute = partiehaute;
    }
    
    public List<Partiehaute> getPartiehautes() {
        try {
            partiehautes = partiehauteFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partiehautes;
    }
    
    public void setPartiehautes(List<Partiehaute> partiehautes) {
        this.partiehautes = partiehautes;
    }
    
    public Boolean getDetail() {
        return detail;
    }
    
    public void setDetail(Boolean detail) {
        this.detail = detail;
    }
    
    public String getCarte() {
        return carte;
    }
    
    public void setCarte(String carte) {
        this.carte = carte;
    }
    
    public String getFichier_carte() {
        return fichier_carte;
    }
    
    public void setFichier_carte(String fichier_carte) {
        this.fichier_carte = fichier_carte;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public boolean isShowPrintForm() {
        return showPrintForm;
    }
    
    public void setShowPrintForm(boolean showPrintForm) {
        this.showPrintForm = showPrintForm;
    }
    
    public boolean isSelectModel() {
        return selectModel;
    }
    
    public void setSelectModel(boolean selectModel) {
        this.selectModel = selectModel;
    }
    
    public UploadedFile getFile() {
        return file;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public boolean isPrintable() {
        return printable;
    }
    
    public void setPrintable(boolean printable) {
        this.printable = printable;
    }
    
    public String getRepertoire() {
        return repertoire;
    }
    
    public void setRepertoire(String repertoire) {
        this.repertoire = repertoire;
    }
    
    public String getFichier() {
        return fichier;
    }
    
    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    
    public boolean isBouton() {
        return bouton;
    }
    
    public void setBouton(boolean bouton) {
        this.bouton = bouton;
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
