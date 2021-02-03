/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.SessionMBean;
import entities.Acteurdistrict;
import entities.Airesante;
import entities.Commentairetab;
import entities.Depense;
import entities.Equipementtraceur;
import entities.Facteurdistrict;
import entities.Faiblesse;
import entities.Ffom;
import entities.Force;
import entities.Gouvernancedistrict;
import entities.Hospitalisationdistrict;
import entities.Informationsanitairedistrict;
import entities.Infrastructure;
import entities.Mapedistrict;
import entities.MedicamenttraceurStructure;
import entities.Menace;
import entities.Morbiditedistrict;
import entities.Mortalitedistrict;
import entities.Opportunite;
import entities.Partiehaute;
import entities.Population;
import entities.Populationfosa;
import entities.Recette;
import entities.Rhs;
import entities.SituationSocioCulturel;
import entities.Structure;
import entities.Structurecommunautaire;
import entities.Tablematieren1District;
import entities.Tablematieren2District;
import entities.Tablematieren3District;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.ActeurdistrictFacadeLocal;
import sessions.ActiviteElementCoutFacadeLocal;
import sessions.ActiviteFacadeLocal;
import sessions.ActiviteStructureFacadeLocal;
import sessions.AiresanteFacadeLocal;
import sessions.ChronogrammeFacadeLocal;
import sessions.CibleFacadeLocal;
import sessions.CommentairetabFacadeLocal;
import sessions.DepenseFacadeLocal;
import sessions.EquipementtraceurFacadeLocal;
import sessions.FacteurdistrictFacadeLocal;
import sessions.FaiblesseFacadeLocal;
import sessions.FfomFacadeLocal;
import sessions.ForceFacadeLocal;
import sessions.GouvernancedistrictFacadeLocal;
import sessions.HospitalisationdistrictFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.InformationsanitairedistrictFacadeLocal;
import sessions.InfrastructureFacadeLocal;
import sessions.MapedistrictFacadeLocal;
import sessions.MedicamenttraceurStructureFacadeLocal;
import sessions.MenaceFacadeLocal;
import sessions.MorbiditedistrictFacadeLocal;
import sessions.MortalitedistrictFacadeLocal;
import sessions.NotationproblemeFacadeLocal;
import sessions.ObjectifOppDistrictFacadeLocal;
import sessions.OpportuniteFacadeLocal;
import sessions.PartiehauteFacadeLocal;
import sessions.PopulationFacadeLocal;
import sessions.PopulationfosaFacadeLocal;
import sessions.ProblemeFacadeLocal;
import sessions.RecetteFacadeLocal;
import sessions.ResultatAttenduDistrictFacadeLocal;
import sessions.RhsFacadeLocal;
import sessions.SituationSocioCulturelFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.StructurecommunautaireFacadeLocal;
import sessions.Tablematieren1DistrictFacadeLocal;
import sessions.Tablematieren2DistrictFacadeLocal;
import sessions.Tablematieren3DistrictFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class ConsolidationController implements Serializable {

    @EJB
    private PartiehauteFacadeLocal partiehauteFacadeLocal;

    @EJB
    private SituationSocioCulturelFacadeLocal situationSocioCulturelFacadeLocal;

    @EJB
    private StructurecommunautaireFacadeLocal structurecommunautaireFacadeLocal;

    @EJB
    private GouvernancedistrictFacadeLocal gouvernancedistrictFacadeLocal;

    @EJB
    private PopulationFacadeLocal populationFacadeLocal;

    @EJB
    private PopulationfosaFacadeLocal populationfosaFacadeLocal;

    @EJB
    private ProblemeFacadeLocal problemeFacadeLocal;

    @EJB
    private NotationproblemeFacadeLocal notationproblemeFacadeLocal;

    @EJB
    private SituationSocioCulturelFacadeLocal socioCulturelFacadeLocal;

    @EJB
    private ActeurdistrictFacadeLocal acteurdistrictFacadeLocal;

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;

    @EJB
    private ActiviteStructureFacadeLocal activiteStructureFacadeLocal;

    @EJB
    private ActiviteElementCoutFacadeLocal activiteElementCoutFacadeLocal;

    @EJB
    private ChronogrammeFacadeLocal chronogrammeFacadeLocal;

    @EJB
    private FacteurdistrictFacadeLocal facteurdistrictFacadeLocal;

    @EJB
    private FfomFacadeLocal ffomFacadeLocal;

    @EJB
    private ForceFacadeLocal forceFacadeLocal;

    @EJB
    private FaiblesseFacadeLocal faiblesseFacadeLocal;

    @EJB
    private OpportuniteFacadeLocal opportuniteFacadeLocal;

    @EJB
    private MenaceFacadeLocal menaceFacadeLocal;

    @EJB
    private CommentairetabFacadeLocal commentairetabFacadeLocal;

    @EJB
    private MapedistrictFacadeLocal mapedistrictFacadeLocal;

    @EJB
    private MorbiditedistrictFacadeLocal morbiditedistrictFacadeLocal;

    @EJB
    private MortalitedistrictFacadeLocal mortalitedistrictFacadeLocal;

    @EJB
    private HospitalisationdistrictFacadeLocal hospitalisationdistrictFacadeLocal;

    @EJB
    private Tablematieren1DistrictFacadeLocal tablematieren1DistrictFacadeLocal;

    @EJB
    private Tablematieren2DistrictFacadeLocal tablematieren2DistrictFacadeLocal;

    @EJB
    private Tablematieren3DistrictFacadeLocal tablematieren3DistrictFacadeLocal;

    @EJB
    private AiresanteFacadeLocal airesanteFacadeLocal;

    @EJB
    private StructureFacadeLocal structureFacadeLocal;

    @EJB
    private DepenseFacadeLocal depenseFacadeLocal;

    @EJB
    private RecetteFacadeLocal recetteFacadeLocal;

    @EJB
    private RhsFacadeLocal rhsFacadeLocal;

    @EJB
    private InformationsanitairedistrictFacadeLocal informationsanitairedistrictFacadeLocal;

    @EJB
    private MedicamenttraceurStructureFacadeLocal medicamenttraceurStructureFacadeLocal;

    @EJB
    private InfrastructureFacadeLocal infrastructureFacadeLocal;

    @EJB
    private EquipementtraceurFacadeLocal equipementtraceurFacadeLocal;

    @EJB
    private ResultatAttenduDistrictFacadeLocal resultatAttenduDistrictFacadeLocal;

    @EJB
    private ObjectifOppDistrictFacadeLocal objectifOppDistrictFacadeLocal;

    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;

    @EJB
    private CibleFacadeLocal cibleFacadeLocal;


    /* Connexion à la base de données */
    Connection connexion = null;

    public Connection connexionDB() {
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://192.168.1.1/pdsd_prcds";
            String utilisateur = "postgres";
            String motDePasse = "batrapi";
            connection = DriverManager.getConnection(url, utilisateur, motDePasse);
        } catch (SQLException es) {
            es.printStackTrace();
        }
        return connection;
    }

    public void consilidate() {

        try {

            connexion = connexionDB();
            Statement statement = connexion.createStatement();

            System.err.println("------------------------------------------> Début de supression");

            //on supprime la partie haute
            delete("DELETE FROM partiehaute WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            //on supprime les commentaires
            delete("DELETE FROM commentairetab WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            //On supprimes les donnés de la situatio socio culturel
            delete("DELETE FROM situation_socio_culturel WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            //on supprime les données demographiques
            delete("DELETE FROM population WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            // on fait une recherche dans le ffom
            ResultSet resultatFFOM = statement.executeQuery("SELECT * FROM ffom WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            while (resultatFFOM.next()) {
                // on supprime les force et faiblesse du district
                deleteForce(resultatFFOM.getInt(1));
                deleteFaiblesse(resultatFFOM.getInt(1));
                deleteOpportunite(resultatFFOM.getInt(1));
                deleteMenace(resultatFFOM.getInt(1));
            }

            //on supprime a ce niveau l initialisateur des ffom
            this.deleteFFOM(SessionMBean.getDistrict().getIddistrict());

            // on supprime les structure communautaire
            this.deleteStructurecom(SessionMBean.getDistrict().getIddistrict());

            //on supprime a les hospitalisation
            this.deleteHospitalisation(SessionMBean.getDistrict().getIddistrict());

            // on supprime les acteur du district
            this.deleteActeur(SessionMBean.getDistrict().getIddistrict());

            //on supprime les facteurs du district
            this.deleteFacteur(SessionMBean.getDistrict().getIddistrict());

            // on supprime les morbidite
            this.deleteMorbidite(SessionMBean.getDistrict().getIddistrict());

            // on supprime les infomation de mortalité
            deleteMortalite(SessionMBean.getDistrict().getIddistrict());

            //on supprime les information de mape
            delete("DELETE FROM mapedistrict WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            //on supprime les element de l'hospitalisation
            delete("DELETE FROM hospitalisationdistrict WHERE iddistrict = " + SessionMBean.getDistrict().getIddistrict());

            //on efface les données de la table de matiere de niveau 1            
            deleteTabMatiereN1(SessionMBean.getDistrict().getIddistrict());

            //on efface les données de la table de matiere de niveau 2            
            deleteTabMatiereN2(SessionMBean.getDistrict().getIddistrict());

            //on efface les données de la table de matiere de niveau 3            
            deleteTabMatiereN3(SessionMBean.getDistrict().getIddistrict());

            //On recupere les aire de santé
            ResultSet resultatAire = findAireSante(SessionMBean.getDistrict().getIddistrict());

            while (resultatAire.next()) {
                // on supprime les force et faiblesse du district

                ResultSet resultatStructure = findAireStructure(resultatAire.getInt(1));

                while (resultatStructure.next()) {

                    int adresse = resultatStructure.getInt("idadresse");

                    // on supprime les medicament traceur
                    deleteMedicament(resultatStructure.getInt("idstructure"));

                    //on supprime les equipeent
                    deleteEquipement(resultatStructure.getInt("idstructure"));

                    //on supprime les infrastructure
                    deleteInfrastructure(resultatStructure.getInt("idstructure"));

                    // on supprime les données rh
                    deleteRhs(resultatStructure.getInt("idstructure"));

                    // on supprime les données de depense
                    deleteDepense(resultatStructure.getInt("idstructure"));

                    // on supprime les données de recette
                    deleteRecette(resultatStructure.getInt("idstructure"));

                    //projection recette
                    deleteProjection(resultatStructure.getInt("idstructure"));

                    // on supprime les données dactivité
                    deleteActiviteStr(resultatStructure.getInt("idstructure"));

                    // on supprime les gouvernance
                    deleteGouvernance(resultatStructure.getInt("idstructure"));

                    //on supprime les information de population fosa
                    deletePopulationFosa(resultatStructure.getInt("idstructure"));

                    //on supprime les info de linformationsanitaire
                    deleteInfosanitaire(resultatStructure.getInt("idstructure"));

                    // on supprime les structure
                    deleteStructure1(resultatStructure.getInt("idstructure"));

                    //on supprime les adresse
                    deleteAdresse(adresse);

                }

                delete("DELETE FROM airesante where idairesante = " + resultatAire.getInt("idairesante"));

            }

            //on supprime laire de sante
            deleteAire(SessionMBean.getDistrict().getIddistrict());

            //on recupere les evaluation
            ResultSet resultatIndicateurDistrict = findPerformance(SessionMBean.getDistrict().getIddistrict());

            while (resultatIndicateurDistrict.next()) {

                deleteCible(resultatIndicateurDistrict.getInt("idindicateur"), SessionMBean.getDistrict().getIddistrict());

                ResultSet pbs = findProbleme(resultatIndicateurDistrict.getInt("idindicateur_district"));

                while (pbs.next()) {
                    //on supprime la priorisation
                    deleteNotation(pbs.getInt("idprobleme"));

                    //on recherche les activités
                    ResultSet acts = findActivite(pbs.getInt("idprobleme"));

                    while (acts.next()) {
                        deleteChronogramme(acts.getInt("idactivite"));
                        delete("DELETE FROM activite_structure WHERE idactivite = " + acts.getLong("idactivite"));
                        delete("DELETE FROM activite_element_cout WHERE idactivite = " + acts.getLong("idactivite"));
                    }
                    deleteActivite(pbs.getInt("idprobleme"));
                }
                deleteProbleme(resultatIndicateurDistrict.getInt("idindicateur_district"));
            }
            deletePerformance(SessionMBean.getDistrict().getIddistrict());

            // on supprime les objectif par district
            delete("DELETE FROM objectif_opp_district WHERE iddistrict =" + SessionMBean.getDistrict().getIddistrict());

            //on suprime les rescultat attendus par district
            delete("DELETE FROM resultat_attendu_district WHERE iddistrict =" + SessionMBean.getDistrict().getIddistrict());

            System.err.println("------------------------------------------> Fin de suppression\n");

            // on commence les insertion
            try {

                // on insere la partie haute
                List<Partiehaute> partiehautes = partiehauteFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!partiehautes.isEmpty()) {
                    ResultSet partiehaute = findNextId("partiehaute", "idpartiehaute");
                    Integer id = 0;
                    if (partiehaute != null) {

                        while (partiehaute.next()) {
                            id = partiehaute.getInt(1) + 1;
                        }
                    } else {
                        id = 1;
                    }

                    insert("INSERT INTO partiehaute (idpartiehaute,iddistrict,introduction_fr,presentationgenerale_fr,situationgeographique_fr,situationeconomique_fr,situationsocioculturelle_fr,carte)"
                            + " VALUES(" + id + "," + SessionMBean.getDistrict().getIddistrict() + ",'" + partiehautes.get(0).getIntroductionFr().replaceAll("'", " ") + "','"
                            + partiehautes.get(0).getPresentationgeneraleFr().replaceAll("'", " ") + "','"
                            + partiehautes.get(0).getSituationgeographiqueFr().replaceAll("'", " ") + "','"
                            + partiehautes.get(0).getSituationeconomiqueFr().replaceAll("'", " ") + "','"
                            + partiehautes.get(0).getSituationsocioculturelleFr().replaceAll("'", " ") + "','"
                            + partiehautes.get(0).getCarte() + "')");
                }

                //on insere la situation socio-culturelle              
                List<SituationSocioCulturel> situationSocioCulturels = situationSocioCulturelFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
                if (!situationSocioCulturels.isEmpty()) {
                    ResultSet ssc = findNextId("situation_socio_culturel", "idsituationsociocult");
                    Integer id = 0;
                    if (ssc != null) {

                        while (ssc.next()) {
                            id = ssc.getInt(1) + 1;
                        }
                    } else {
                        id = 1;
                    }

                    insert("INSERT INTO situation_socio_culturel (idsituationsociocult,iddistrict,education,statut,facteurculturel,facteursociocult)"
                            + " VALUES(" + id + ","
                            + SessionMBean.getDistrict().getIddistrict() + ",'"
                            + situationSocioCulturels.get(0).getEducation().replaceAll("'", " ") + "','"
                            + situationSocioCulturels.get(0).getStatut().replaceAll("'", " ") + "','"
                            + situationSocioCulturels.get(0).getFacteurculturel().replaceAll("'", " ") + "','"
                            + situationSocioCulturels.get(0).getFacteursociocult().replaceAll("'", " ") + "')");
                }

                // on insere les facteur du district
                List<Acteurdistrict> acteurdistricts = acteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!acteurdistricts.isEmpty()) {
                    for (Acteurdistrict a : acteurdistricts) {
                        ResultSet pbs = findNextId("acteurdistrict", "idacteur_district");
                        Long id = 0L;
                        if (pbs != null) {

                            while (pbs.next()) {
                                id = pbs.getLong(1) + 1;
                            }
                        } else {
                            id = 1L;
                        }

                        insert("INSERT INTO acteurdistrict(idacteur_district,idacteur,observation,iddistrict) VALUES(" + id + ","
                                + a.getIdacteur().getIdacteur() + ",'"
                                + a.getObservation().replaceAll("'", " ") + "',"
                                + SessionMBean.getDistrict().getIddistrict() + ")");
                    }
                }

                //on insere les facteur district
                List<Facteurdistrict> facteurdistricts = facteurdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!facteurdistricts.isEmpty()) {
                    for (Facteurdistrict a : facteurdistricts) {
                        ResultSet pbs = findNextId("facteurdistrict", "idfacteurdistrict");
                        Long id = 0L;
                        if (pbs != null) {

                            while (pbs.next()) {
                                id = pbs.getLong(1) + 1;
                            }
                        } else {
                            id = 1L;
                        }
                        insert("INSERT INTO facteurdistrict(idfacteurdistrict,idfacteur,observation,iddistrict) VALUES("
                                + id + ","
                                + a.getIdfacteur().getIdfacteur() + ",'"
                                + a.getObservation().replaceAll("'", "") + "',"
                                + SessionMBean.getDistrict().getIddistrict() + ")");

                    }
                }

                // on insere les commentaire
                List<Commentairetab> commentairetabs = commentairetabFacadeLocal.find(SessionMBean.getDistrict());
                if (!commentairetabs.isEmpty()) {
                    for (Commentairetab c : commentairetabs) {
                        ResultSet nextC = findNextId("commentairetab", "idcommentairetab");
                        Integer idCommentaire = 0;
                        if (nextC != null) {
                            while (nextC.next()) {
                                idCommentaire = nextC.getInt(1) + 1;
                            }
                        } else {
                            idCommentaire = 1;
                        }
                        insert("INSERT INTO commentairetab(idcommentairetab,iddistrict,commentaire,numerotab) VALUES("
                                + idCommentaire + ","
                                + SessionMBean.getDistrict().getIddistrict() + ", '"
                                + c.getCommentaire().replaceAll("'", " ") + "',"
                                + c.getNumerotab() + ")");
                    }
                }

                // on insere les données demographiques
                List<Population> populations = populationFacadeLocal.find(SessionMBean.getDistrict());
                if (!populations.isEmpty()) {
                    for (Population p : populations) {
                        ResultSet nextId = findNextId("population", "idpopulation");
                        Long idPopulation = 0l;
                        if (nextId != null) {
                            while (nextId.next()) {
                                idPopulation = nextId.getLong(1) + 1l;
                            }
                        } else {
                            idPopulation = 1l;
                        }

                        insert("INSERT INTO population(idpopulation,idrubriquepopulation,iddistrict,valeurpopulationdistrict,valeurpopulationrubrique,idannee) VALUES("
                                + idPopulation + ","
                                + p.getIdrubriquepopulation().getIdrubriquepopulation() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + p.getValeurpopulationdistrict() + ","
                                + p.getValeurpopulationrubrique() + ","
                                + p.getIdannee().getIdannee() + ")");
                    }
                }

                //on recherche les ffom
                List<Ffom> ffoms = ffomFacadeLocal.find(SessionMBean.getDistrict());
                int i = 0;
                if (!ffoms.isEmpty()) {
                    for (Ffom f : ffoms) {
                        i += 1;
                        ResultSet pbs = findNextId("ffom", "idffom");
                        int id = 0;
                        if (pbs != null) {
                            while (pbs.next()) {
                                id = pbs.getInt(1) + 1;
                            }
                        } else {
                            id = 1;
                        }
                        // on insere le ffom
                        insert("INSERT INTO ffom(idffom,force,faiblesse,opportunite,menace,iddistrict,idpilier) VALUES("
                                + id + ", '"
                                + f.getForce().replaceAll("'", " ") + "','"
                                + f.getFaiblesse().replaceAll("'", " ") + "','"
                                + f.getOpportunite().replace("'", " ") + "','"
                                + f.getMenace().replaceAll("'", " ") + "',"
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + f.getIdpilier().getIdpilier() + ")");

                        // on recherche les force et on insere
                        List<Force> forces = forceFacadeLocal.findByFfom(f);
                        if (!forces.isEmpty()) {
                            for (Force fr : forces) {

                                ResultSet nextFor = findNextId("force", "idforce");
                                Long idforce = 0L;
                                if (nextFor != null) {
                                    while (nextFor.next()) {
                                        idforce = nextFor.getLong(1) + 1L;
                                    }
                                } else {
                                    idforce = 1L;
                                }
                                // on insere les faiblesse
                                if (fr.getIdfacteur() != null) {
                                    insert("INSERT INTO force(idforce,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idforce + ","
                                            + id + ","
                                            + null + ",'"
                                            + fr.getNom().replaceAll("'", " ") + "',"
                                            + fr.getIdfacteur().getIdfacteur() + ","
                                            + fr.getFacteur() + ")");
                                } else if (fr.getIdacteur() != null) {
                                    insert("INSERT INTO force(idforce,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idforce + ","
                                            + id + ","
                                            + fr.getIdacteur().getIdacteur() + ",'"
                                            + fr.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + fr.getFacteur() + ")");
                                } else {
                                    insert("INSERT INTO force(idforce,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idforce + ","
                                            + id + ","
                                            + null + ",'"
                                            + fr.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + fr.getFacteur() + ")");
                                }
                            }
                        }

                        // on recherche les faiblesse
                        List<Faiblesse> faiblesses = faiblesseFacadeLocal.findByFfom(f);
                        if (!faiblesses.isEmpty()) {
                            for (Faiblesse fai : faiblesses) {

                                ResultSet nextFai = findNextId("faiblesse", "idfaiblesse");
                                Long idfaiblesse = 0L;
                                if (nextFai != null) {
                                    while (nextFai.next()) {
                                        idfaiblesse = nextFai.getLong(1) + 1L;
                                    }
                                } else {
                                    idfaiblesse = 1L;
                                }

                                // on insere les faiblesse
                                if (fai.getIdfacteur() != null) {
                                    insert("INSERT INTO faiblesse(idfaiblesse,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idfaiblesse + ","
                                            + id + ","
                                            + null + ",'"
                                            + fai.getNom().replaceAll("'", " ") + "',"
                                            + fai.getIdfacteur().getIdfacteur() + ","
                                            + fai.getFacteur() + ")");
                                } else if (fai.getIdacteur() != null) {
                                    insert("INSERT INTO faiblesse(idfaiblesse,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idfaiblesse + ","
                                            + id + ","
                                            + fai.getIdacteur().getIdacteur() + ",'"
                                            + fai.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + fai.getFacteur() + ")");
                                } else {
                                    insert("INSERT INTO faiblesse(idfaiblesse,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idfaiblesse + ","
                                            + id + ","
                                            + null + ",'"
                                            + fai.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + fai.getFacteur() + ")");
                                }
                            }
                        }

                        // on recherche les opportunites 
                        List<Opportunite> opportunites = opportuniteFacadeLocal.findByFfom(f);
                        if (!opportunites.isEmpty()) {
                            for (Opportunite opp : opportunites) {

                                ResultSet nextOpp = findNextId("opportunite", "idopportunite");
                                Long idopp = 0L;
                                if (nextOpp != null) {
                                    while (nextOpp.next()) {
                                        idopp = nextOpp.getLong(1) + 1L;
                                    }
                                } else {
                                    idopp = 1L;
                                }
                                // on insere les opportunité
                                if (opp.getIdfacteur() != null) {
                                    insert("INSERT INTO opportunite(idopportunite,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idopp + ","
                                            + id + ","
                                            + null + ",'"
                                            + opp.getNom().replaceAll("'", " ") + "',"
                                            + opp.getIdfacteur().getIdfacteur() + ","
                                            + opp.getFacteur() + ")");
                                } else if (opp.getIdacteur() != null) {
                                    insert("INSERT INTO opportunite(idopportunite,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idopp + ","
                                            + id + ","
                                            + opp.getIdacteur().getIdacteur() + ",'"
                                            + opp.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + opp.getFacteur() + ")");
                                } else {
                                    insert("INSERT INTO opportunite(idopportunite,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idopp + ","
                                            + id + ","
                                            + null + ",'"
                                            + opp.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + opp.getFacteur() + ")");
                                }
                            }
                        }

                        //on recherche les meances
                        List<Menace> menaces = menaceFacadeLocal.findByFfom(f);
                        if (!menaces.isEmpty()) {
                            for (Menace m : menaces) {

                                ResultSet nextM = findNextId("menace", "idmenace");
                                Long idM = 0L;
                                if (nextM != null) {
                                    while (nextM.next()) {
                                        idM = nextM.getLong(1) + 1L;
                                    }
                                } else {
                                    idM = 1L;
                                }
                                // on insere les opportunité
                                if (m.getIdfacteur() != null) {
                                    insert("INSERT INTO menace(idmenace,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idM + ","
                                            + id + ","
                                            + null + ",'"
                                            + m.getNom().replaceAll("'", " ") + "',"
                                            + m.getIdfacteur().getIdfacteur() + ","
                                            + m.getFacteur() + ")");
                                } else if (m.getIdacteur() != null) {
                                    insert("INSERT INTO menace(idmenace,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idM + ","
                                            + id + ","
                                            + m.getIdacteur().getIdacteur() + ",'"
                                            + m.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + m.getFacteur() + ")");
                                } else {
                                    insert("INSERT INTO menace(idmenace,idffom,idacteur,nom,idfacteur,facteur) VALUES("
                                            + idM + ","
                                            + id + ","
                                            + null + ",'"
                                            + m.getNom().replaceAll("'", " ") + "',"
                                            + null + ","
                                            + m.getFacteur() + ")");
                                }
                            }
                        }
                    }
                }

                // on insere les mape
                List<Mapedistrict> mapedistricts = mapedistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
                if (!mapedistricts.isEmpty()) {
                    for (Mapedistrict m : mapedistricts) {
                        ResultSet nextM = findNextId("mapedistrict", "idmapedistrict");
                        Long idMape = 0L;
                        if (nextM != null) {
                            while (nextM.next()) {
                                idMape = nextM.getLong(1) + 1L;
                            }
                        } else {
                            idMape = 1L;
                        }

                        Double valeur = 0d;
                        try {
                            valeur = m.getValeur().doubleValue();
                        } catch (Exception e) {
                            valeur = 0d;
                        }

                        insert("INSERT INTO mapedistrict(idmapedistrict,idannee,idmape,iddistrict,valeur) VALUES( "
                                + idMape + ","
                                + m.getIdannee().getIdannee() + ","
                                + m.getIdmape().getIdmape() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + valeur + ")");
                    }
                }

                // on insere les morbidité district
                List<Morbiditedistrict> morbiditedistricts = morbiditedistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
                if (!morbiditedistricts.isEmpty()) {
                    for (Morbiditedistrict m : morbiditedistricts) {
                        ResultSet nextM = findNextId("morbiditedistrict", "idmorbiditedistrict");
                        Long idMorbidite = 0L;
                        if (nextM != null) {
                            while (nextM.next()) {
                                idMorbidite = nextM.getInt(1) + 1L;
                            }
                        } else {
                            idMorbidite = 1L;
                        }

                        BigInteger valeur = BigInteger.ZERO;
                        try {
                            valeur = BigInteger.valueOf(m.getValeur().longValue());
                        } catch (Exception e) {
                            valeur = BigInteger.ZERO;
                        }
                        insert("INSERT INTO morbiditedistrict(idmorbiditedistrict,idmorbidite,idrubriquemorbidite,iddistrict,valeur) VALUES("
                                + idMorbidite + ","
                                + m.getIdmorbidite().getIdmorbidite() + ","
                                + m.getIdrubriquemorbidite().getIdrubriquemorbidite() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + valeur + ")");
                    }
                }

                //on insere les mortailité
                List<Mortalitedistrict> mortalitedistricts = mortalitedistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
                if (!mortalitedistricts.isEmpty()) {
                    for (Mortalitedistrict m : mortalitedistricts) {
                        ResultSet nextM = findNextId("mortalitedistrict", "idmortalitedistrict");
                        Long idMortalite = 0L;
                        if (nextM != null) {
                            while (nextM.next()) {
                                idMortalite = nextM.getInt(1) + 1L;
                            }
                        } else {
                            idMortalite = 1L;
                        }

                        Double valeur = 0d;
                        try {
                            valeur = m.getValeur().doubleValue();
                        } catch (Exception e) {
                            valeur = 0d;
                        }
                        insert("INSERT INTO mortalitedistrict (idmortalitedistrict,idmortalite,iddistrict,idrubriquemortalite,valeur) VALUES("
                                + idMortalite + ","
                                + m.getIdmortalite().getIdmortalite() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + m.getIdrubriquemortalite().getIdrubriquemortalite() + ","
                                + valeur + ")");
                    }
                }

                //hospitalisation du district
                List<Hospitalisationdistrict> hospitalisationdistricts = hospitalisationdistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict().getIddistrict());
                if (!hospitalisationdistricts.isEmpty()) {
                    for (Hospitalisationdistrict h : hospitalisationdistricts) {
                        ResultSet nextH = findNextId("hospitalisationdistrict", "idhospitalisationdistrict");
                        Long idHospi = 0L;
                        if (nextH != null) {
                            while (nextH.next()) {
                                idHospi = nextH.getInt(1) + 1L;
                            }
                        } else {
                            idHospi = 1L;
                        }

                        BigInteger valeur = BigInteger.ZERO;
                        try {
                            valeur = BigInteger.valueOf(h.getValeur().longValue());
                        } catch (Exception e) {
                            valeur = BigInteger.ZERO;
                        }

                        insert("INSERT INTO hospitalisationdistrict (idhospitalisationdistrict,idrubriquehospitalisation,idhospitalisation,iddistrict,valeur) VALUES("
                                + idHospi + ","
                                + h.getIdrubriquehospitalisation().getIdrubriquehospitalisation() + ","
                                + h.getIdhospitalisation().getIdhospitalisation() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + valeur + ")");
                    }
                }

                // on insere la table de matiere de niveau 1
                List<Tablematieren1District> tablematieren1Districts = tablematieren1DistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!tablematieren1Districts.isEmpty()) {
                    for (Tablematieren1District t : tablematieren1Districts) {
                        ResultSet nextT1 = findNextId("tablematieren1_district", "idtablematieren1_district");
                        Long idT1 = 0L;
                        if (nextT1 != null) {
                            while (nextT1.next()) {
                                idT1 = nextT1.getLong(1) + 1L;
                            }
                        } else {
                            idT1 = 1L;
                        }

                        insert("INSERT INTO tablematieren1_district (idtablematieren1_district,idtablematiere_n1,iddistrict,numeropage) VALUES ("
                                + idT1 + ","
                                + t.getIdtablematiereN1().getIdtablematiereN1() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + t.getNumeropage() + ")");
                    }
                }

                // on insere la table de matiere de niveau 2
                List<Tablematieren2District> tablematieren2Districts = tablematieren2DistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!tablematieren2Districts.isEmpty()) {
                    for (Tablematieren2District t : tablematieren2Districts) {
                        ResultSet nextId = findNextId("tablematieren2_district", "idtablematieren2_district");
                        Long id = 0L;
                        if (nextId != null) {
                            while (nextId.next()) {
                                id = nextId.getLong(1) + 1L;
                            }
                        } else {
                            id = 1L;
                        }

                        insert("INSERT INTO tablematieren2_district (idtablematieren2_district,idtablematiere_n2,iddistrict,numeropage) VALUES ("
                                + id + ","
                                + t.getIdtablematiereN2().getIdtablematiereN2() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + t.getNumeropage() + ")");
                    }
                }

                // on insere la table de matiere de niveau 3
                List<Tablematieren3District> tablematieren3Districts = tablematieren3DistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!tablematieren3Districts.isEmpty()) {
                    for (Tablematieren3District t : tablematieren3Districts) {
                        ResultSet nextId = findNextId("tablematieren3_district", "idtablematieren3_district");
                        Long id = 0L;
                        if (nextId != null) {
                            while (nextId.next()) {
                                id = nextId.getInt(1) + 1L;
                            }
                        } else {
                            id = 1L;
                        }
                        insert("INSERT INTO tablematieren3_district (idtablematieren3_district,idtablematiere_n3,iddistrict,numeropage) VALUES ("
                                + id + ","
                                + t.getIdtablematiereN3().getIdtablematiereN3() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + t.getNumeropage() + ")");
                    }
                }

                HashMap mapStructure = new HashMap();

                //on reinsere les des aires de santé
                List<Airesante> airesante = airesanteFacadeLocal.find(SessionMBean.getDistrict());
                if (!airesante.isEmpty()) {
                    for (Airesante a : airesante) {
                        ResultSet nextAir = findNextId("airesante", "idairesante");
                        int idAir = 0;
                        if (nextAir != null) {
                            while (nextAir.next()) {
                                idAir = nextAir.getInt(1) + 1;
                            }
                        } else {
                            idAir = 1;
                        }

                        // on insere les aire de santé
                        insert("INSERT INTO airesante(idairesante,iddistrict,nom_fr,nom_en) VALUES("
                                + idAir + ","
                                + SessionMBean.getDistrict().getIddistrict() + ",'"
                                + a.getNomFr().replaceAll("'", " ") + "','"
                                + a.getNomEn().replaceAll("'", " ") + "')");

                        List<Structure> structures = structureFacadeLocal.findByAire(a.getIdairesante());
                        if (!structures.isEmpty()) {
                            for (Structure s : structures) {

                                Long idAdd = 0L;
                                if (s.getIdadresse() != null) {
                                    ResultSet nextAdd = findNextId("adresse", "id_adresse");
                                    if (nextAdd != null) {
                                        while (nextAdd.next()) {
                                            idAdd = nextAdd.getLong(1) + 1L;
                                        }
                                    } else {
                                        idAdd = 1L;
                                    }
                                } else {
                                    idAdd = null;
                                }

                                //on insere l'adresse
                                insert("INSERT INTO adresse(id_adresse)VALUES(" + idAdd + ")");

                                ResultSet nextStr = findNextId("structure", "idstructure");
                                int idStr = 0;
                                if (nextStr != null) {
                                    while (nextStr.next()) {
                                        idStr = nextStr.getInt(1) + 1;
                                    }
                                } else {
                                    idStr = 1;
                                }

                                //on insere les structure
                                Double distance = 0.0;

                                try {
                                    if (!s.getDistance().equals(null)) {
                                        distance = s.getDistance();
                                    }
                                } catch (Exception e) {
                                    distance = 0.0;
                                }

                                insert("INSERT INTO structure(idstructure,idtypestructure,idstatutstructure,nom_fr,nom_en,"
                                        + "code,iddistrict,idadresse,idairesante,distance,leader,idinstitution,consolide,idniveaupyramide)"
                                        + "VALUES(" + idStr + ","
                                        + s.getIdtypestructure().getIdtypestructure() + ","
                                        + s.getIdstatutstructure().getIdstatutstructure() + ",'"
                                        + s.getNomFr().replaceAll("'", " ") + "','"
                                        + s.getNomEn().replaceAll("'", " ") + "','"
                                        + s.getCode().replaceAll("'", " ") + "',"
                                        + SessionMBean.getDistrict().getIddistrict() + ","
                                        + idAdd + ","
                                        + idAir + ","
                                        + distance + ","
                                        + s.getLeader() + ","
                                        + s.getIdinstitution().getIdinstitution() + ","
                                        + false + ","
                                        + (3) + ")");

                                mapStructure.put(s.getIdstructure(), idStr);

                                List<Equipementtraceur> equipementtraceurs = equipementtraceurFacadeLocal.find(s);
                                if (!equipementtraceurs.isEmpty()) {
                                    for (Equipementtraceur e : equipementtraceurs) {
                                        ResultSet nextEq = findNextId("equipementtraceur", "idequipementtraceur");
                                        int idEq = 0;
                                        if (nextEq != null) {
                                            while (nextEq.next()) {
                                                idEq = nextEq.getInt(1) + 1;
                                            }
                                        } else {
                                            idEq = 1;
                                        }
                                        // on insere les equipement traceur
                                        insert("INSERT INTO equipementtraceur (idequipementtraceur,idtypestruc_typeequipement,idetatequipement,idstructure,nombre)VALUES("
                                                + idEq + ","
                                                + e.getIdtypestrucTypeequipement().getIdtypestrucTypeequipement() + ","
                                                + e.getIdetatequipement().getIdetatequipement() + ","
                                                + idStr + ","
                                                + e.getNombre() + ")");
                                    }
                                }

                                List<Infrastructure> infrastructures = infrastructureFacadeLocal.find(s);
                                if (!infrastructures.isEmpty()) {
                                    for (Infrastructure in : infrastructures) {
                                        ResultSet nextIn = findNextId("infrastructure", "idinfrastructure");
                                        int idIn = 0;
                                        if (nextIn != null) {
                                            while (nextIn.next()) {
                                                idIn = nextIn.getInt(1) + 1;
                                            }
                                        } else {
                                            idIn = 1;
                                        }

                                        insert("INSERT INTO infrastructure(idinfrastructure,idtypeinfra_typestruc,idetatinfrastructure,idstructure) VALUES("
                                                + idIn + ","
                                                + in.getIdtypeinfraTypestruc().getIdtypeinfraTypestruc() + ","
                                                + in.getIdetatinfrastructure().getIdetatinfrastructure() + ","
                                                + idStr + ")");

                                    }
                                }

                                // on traite les depenses
                                List<Depense> depenses = depenseFacadeLocal.find(s);
                                if (!depenses.isEmpty()) {
                                    for (Depense d : depenses) {
                                        ResultSet nextId = findNextId("depense", "iddepense");
                                        int idDepense = 0;
                                        if (nextId != null) {
                                            while (nextId.next()) {
                                                idDepense = nextId.getInt(1) + 1;
                                            }
                                        } else {
                                            idDepense = 1;
                                        }

                                        Double valeur = 0.0;

                                        try {
                                            if (!d.getValeur().equals(null)) {
                                                valeur = d.getValeur();
                                            }
                                        } catch (Exception e) {
                                            valeur = 0.0;
                                        }

                                        insert("INSERT INTO depense(iddepense,idstructure,idnaturedepense,idannee,valeur,prcds) VALUES("
                                                + idDepense + ","
                                                + idStr + ","
                                                + d.getIdnaturedepense().getIdnaturedepense() + ","
                                                + d.getIdannee().getIdannee() + ","
                                                + valeur + ","
                                                + false + ")");
                                    }
                                }

                                // on traite les recette
                                List<Recette> recettes = recetteFacadeLocal.find(s);
                                if (!recettes.isEmpty()) {
                                    for (Recette r : recettes) {
                                        ResultSet nextRec = findNextId("recette", "idrecette");
                                        int idRec = 0;
                                        if (nextRec != null) {
                                            while (nextRec.next()) {
                                                idRec = nextRec.getInt(1) + 1;
                                            }
                                        } else {
                                            idRec = 1;
                                        }

                                        BigInteger valeur = BigInteger.ZERO;

                                        try {
                                            if (!r.getValeur().equals(null)) {
                                                valeur = r.getValeur();
                                            }
                                        } catch (Exception e) {
                                            valeur = BigInteger.ZERO;
                                        }

                                        insert("INSERT INTO recette (idrecette,idannee,idsourcefi,idstructure,valeur,prcds) VALUES("
                                                + idRec + ","
                                                + r.getIdannee().getIdannee() + ","
                                                + r.getIdsourcefi().getIdsourcefi() + ","
                                                + idStr + ","
                                                + valeur + ","
                                                + false + ")");
                                    }
                                }

                                // on traite les rhs
                                List<Rhs> rhses = rhsFacadeLocal.find(s);
                                if (!rhses.isEmpty()) {
                                    for (Rhs rhs : rhses) {
                                        ResultSet nextRhs = findNextId("rhs", "idrhs");
                                        int idRhs = 0;
                                        if (nextRhs != null) {
                                            while (nextRhs.next()) {
                                                idRhs = nextRhs.getInt(1) + 1;
                                            }
                                        } else {
                                            idRhs = 1;
                                        }

                                        Integer valeur = 0;
                                        try {
                                            valeur = rhs.getValeur();
                                        } catch (Exception e) {
                                            valeur = 0;
                                        }
                                        insert("INSERT INTO rhs (idrhs,idprofilpersonnel,idannee,idstructure,valeur,prcds) VALUES( "
                                                + idRhs + ","
                                                + rhs.getIdprofilpersonnel().getIdprofilpersonnel() + ","
                                                + rhs.getIdannee().getIdannee() + ","
                                                + idStr + ","
                                                + valeur + ","
                                                + false + ")");
                                    }
                                }

                                //on traite les medicaments
                                List<MedicamenttraceurStructure> medicamenttraceurStructures = medicamenttraceurStructureFacadeLocal.find(s);
                                if (!medicamenttraceurStructures.isEmpty()) {
                                    for (MedicamenttraceurStructure m : medicamenttraceurStructures) {
                                        ResultSet nextId = findNextId("medicamenttraceur_structure", "idmedicamenttraceur_structure");
                                        int id = 0;
                                        if (nextId != null) {
                                            while (nextId.next()) {
                                                id = nextId.getInt(1) + 1;
                                            }
                                        } else {
                                            id = 1;
                                        }

                                        Integer idsource = null;

                                        try {
                                            idsource = m.getIdsourceapprovisionnement().getIdsourceapprovisionnement();
                                        } catch (Exception e) {
                                            idsource = null;
                                        }

                                        insert("INSERT INTO medicamenttraceur_structure (idmedicamenttraceur_structure,idmedicamenttraceur,idstructure,idannee,idsourceapprovisionnement,nbrejrrupturestock,prcds) VALUES("
                                                + id + ","
                                                + m.getIdmedicamenttraceur().getIdmedicamenttraceur() + ","
                                                + idStr + ","
                                                + m.getIdannee().getIdannee() + ","
                                                + idsource + ","
                                                + m.getNbrejrrupturestock() + ","
                                                + false + ")");
                                    }
                                }

                                // on traite les information sanitaire                               
                                List<Informationsanitairedistrict> informationsanitairedistricts = informationsanitairedistrictFacadeLocal.find(s);
                                if (!informationsanitairedistricts.isEmpty()) {
                                    for (Informationsanitairedistrict in : informationsanitairedistricts) {

                                        ResultSet nextInf = findNextId("informationsanitairedistrict", "idinformationsanitairedistrict");
                                        int id = 0;
                                        if (nextInf != null) {
                                            while (nextInf.next()) {
                                                id = nextInf.getInt(1) + 1;
                                            }
                                        } else {
                                            id = 1;
                                        }

                                        String valeur = "";
                                        try {
                                            valeur = in.getValeur();
                                        } catch (Exception e) {
                                            valeur = "";
                                        }

                                        insert("INSERT INTO informationsanitairedistrict (idinformationsanitairedistrict,valeur,iddistrict,idrubriqueinfosanitaire,idstructure,prcds) VALUES("
                                                + id + ",'"
                                                + valeur + "',"
                                                + SessionMBean.getDistrict().getIddistrict() + ","
                                                + in.getIdrubriqueinfosanitaire().getIdrubriqueinfosanitaire() + ","
                                                + idStr + ","
                                                + false + ")");
                                    }
                                }

                                // on insere les données de la gouvernance                               
                                List<Gouvernancedistrict> gouvernancedistricts = gouvernancedistrictFacadeLocal.find(s);
                                //gouvernancedistrictFacadeLocal.f

                                if (!gouvernancedistricts.isEmpty()) {
                                    for (Gouvernancedistrict g : gouvernancedistricts) {

                                        ResultSet nextId = findNextId("gouvernancedistrict", "idgouvernancedistrict");
                                        int id = 0;
                                        if (nextId != null) {
                                            while (nextId.next()) {
                                                id = nextId.getInt(1) + 1;
                                            }
                                        } else {
                                            id = 1;
                                        }

                                        String valeur = "";
                                        try {
                                            valeur = g.getValeur();
                                        } catch (Exception e) {
                                            valeur = "";
                                        }

                                        insert("INSERT INTO gouvernancedistrict (idgouvernancedistrict,valeur,iddistrict,idrubriquegouvernance,idstructure) VALUES("
                                                + id + ",'"
                                                + valeur + "',"
                                                + SessionMBean.getDistrict().getIddistrict() + ","
                                                + g.getIdrubriquegouvernance().getIdrubriquegouvernance() + ","
                                                + idStr + ")");

                                    }
                                }

                                //on insere les population fosa                                
                                List<Populationfosa> populationfosas = populationfosaFacadeLocal.findByStructure(s.getIdstructure());
                                if (!populationfosas.isEmpty()) {
                                    for (Populationfosa p : populationfosas) {

                                        ResultSet nextId = findNextId("populationfosa", "idpopulationfosa");
                                        Long id = 0l;
                                        if (nextId != null) {
                                            while (nextId.next()) {
                                                id = nextId.getLong(1) + 1l;
                                            }
                                        } else {
                                            id = 1l;
                                        }

                                        Integer valeur = 0;
                                        try {
                                            valeur = p.getPopulationcouverte().intValue();
                                        } catch (Exception e) {
                                            valeur = 0;
                                        }

                                        insert("INSERT INTO populationfosa (idpopulationfosa,idstructure,idannee,populationcouverte,prcds) VALUES("
                                                + id + ","
                                                + idStr + ","
                                                + p.getIdannee().getIdannee() + ","
                                                + valeur + ","
                                                + false + ")");

                                    }
                                }

                            }
                        }
                    }
                }

                //on insere les structure communautaire
                List<Structurecommunautaire> structurecommunautaires = structurecommunautaireFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!structurecommunautaires.isEmpty()) {
                    for (Structurecommunautaire s : structurecommunautaires) {
                        ResultSet nextId = findNextId("structurecommunautaire", "idstructurecommunautaire");
                        int id = 0;
                        if (nextId != null) {
                            while (nextId.next()) {
                                id = nextId.getInt(1) + 1;
                            }
                        } else {
                            id = 1;
                        }

                        Integer valeur = 0;
                        try {
                            valeur = s.getEffectif();
                        } catch (Exception e) {
                            valeur = 0;
                        }

                        String nom_fr = "";
                        String nom_en = "";
                        try {
                            nom_fr = s.getNomFr();
                        } catch (Exception e) {
                            nom_fr = "";
                        }

                        try {
                            nom_en = s.getNomEn();
                        } catch (Exception e) {
                            nom_en = "";
                        }

                        insert("INSERT INTO structurecommunautaire (idstructurecommunautaire,idtypestructurecommunautaire,idetatfonctstructcom,iddistrict,effectif,nom_fr,nom_en) VALUES("
                                + id + ","
                                + s.getIdtypestructurecommunautaire().getIdtypestructurecommunautaire() + ","
                                + s.getIdetatfonctstructcom().getIdetatfonctstructcom() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + valeur + ",'"
                                + nom_fr.replaceAll("'", " ") + "','"
                                + nom_en.replaceAll("'", " ") + "')");

                    }
                }

                
                /*
                
                HashMap mapObjectif = new HashMap();
                //on insere les objectif operationnels
                List<ObjectifOppDistrict> objectifOppDistricts = objectifOppDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!objectifOppDistricts.isEmpty()) {
                    for (ObjectifOppDistrict o : objectifOppDistricts) {
                        ResultSet nextId = findNextId("objectif_opp_district", "idobjectif_opp_district");
                        Long id = 0L;
                        if (nextId != null) {
                            while (nextId.next()) {
                                id = nextId.getLong(1) + 1L;
                            }
                        } else {
                            id = 1L;
                        }

                        String valeur = "-";
                        try {
                            valeur = o.getObjectif();
                        } catch (Exception e) {
                            valeur = "-";
                        }

                        insert("INSERT INTO objectif_opp_district (idobjectif_opp_district,idintervention,iddistrict,objectif) VALUES("
                                + id + ","
                                + o.getIdintervention().getIdinterventionpnds() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ",'"
                                + valeur.replaceAll("'", " ") + "')");

                        mapObjectif.put(o.getIdobjectifOppDistrict(), id);

                    }
                }

                HashMap mapResult = new HashMap();

                //on insere les resultat attendus
                List<ResultatAttenduDistrict> resultatAttenduDistricts = resultatAttenduDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!resultatAttenduDistricts.isEmpty()) {
                    for (ResultatAttenduDistrict r : resultatAttenduDistricts) {
                        ResultSet nextId = findNextId("resultat_attendu_district", "idresultat_attendu_district");
                        Long id = 0L;
                        if (nextId != null) {
                            while (nextId.next()) {
                                id = nextId.getLong(1) + 1L;
                            }
                        } else {
                            id = 1L;
                        }

                        String valeur = "-";
                        try {
                            valeur = r.getResultat();
                        } catch (Exception e) {
                            valeur = "-";
                        }

                        insert("INSERT INTO resultat_attendu_district(idresultat_attendu_district,idindicateur,iddistrict,resultat) VALUES("
                                + id + ","
                                + r.getIdindicateur().getIdindicateur() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ",'"
                                + valeur.replaceAll("'", " ") + "')");

                        mapResult.put(r.getIdresultatAttenduDistrict(), id);
                    }
                }

                //on insere les cibles
                List<Cible> cibles = cibleFacadeLocal.find(SessionMBean.getDistrict());
                for (Cible c : cibles) {
                    ResultSet nextId = findNextId("cible", "idcible");
                    int id = 0;
                    if (nextId != null) {
                        while (nextId.next()) {
                            id = nextId.getInt(1) + 1;
                        }
                    } else {
                        id = 1;
                    }

                    Double valeur = 0d;
                    try {
                        valeur = c.getValeur();
                    } catch (Exception e) {
                        valeur = 0d;
                    }

                    insert("INSERT INTO cible(idcible,idannee,idindicateur,iddistrict,valeur) VALUES("
                            + id + ","
                            + c.getIdannee().getIdannee() + ","
                            + c.getIdindicateur().getIdindicateur() + ","
                            + SessionMBean.getDistrict().getIddistrict() + ","
                            + valeur + ")");

                }
                // on insere la performance
                List<IndicateurDistrict> indicateurDistricts = indicateurDistrictFacadeLocal.findByDistrict(SessionMBean.getDistrict());
                if (!indicateurDistricts.isEmpty()) {

                    for (IndicateurDistrict ind : indicateurDistricts) {

                        ResultSet nextId = findNextId("indicateur_district", "idindicateur_district");
                        int id = 0;
                        if (nextId != null) {
                            while (nextId.next()) {
                                id = nextId.getInt(1) + 1;
                            }
                        } else {
                            id = 1;
                        }
                        insert("INSERT INTO indicateur_district (idindicateur_district,idindicateur,iddistrict,valeur,probleme,idobservation,idannee,observation,cause) VALUES("
                                + id + ","
                                + ind.getIdindicateur().getIdindicateur() + ","
                                + SessionMBean.getDistrict().getIddistrict() + ","
                                + ind.getValeur() + ",'"
                                + ind.getProbleme().replaceAll("'", " ") + "',"
                                + ind.getIdobservation().getIdobservation() + ","
                                + ind.getIdannee().getIdannee() + ",'"
                                + ind.getObservation().replaceAll("'", " ") + "','"
                                + ind.getCause().replaceAll("'", " ") + "')");

                        //on insere les problemes
                        List<Probleme> problemes = problemeFacadeLocal.find(ind);
                        for (Probleme p : problemes) {

                            ResultSet nextIdPb = findNextId("probleme", "idprobleme");
                            int idPb = 0;
                            if (nextIdPb != null) {
                                while (nextIdPb.next()) {
                                    idPb = nextIdPb.getInt(1) + 1;
                                }
                            } else {
                                idPb = 1;
                            }

                            String objectif = "-";
                            String cause = "-";
                            String nom = "-";

                            try {
                                objectif = p.getObjectif();
                            } catch (Exception e) {
                                objectif = "-";
                            }

                            try {
                                cause = p.getCause();
                            } catch (Exception e) {
                                cause = "-";
                            }

                            try {
                                nom = p.getNom();
                            } catch (Exception e) {
                                nom = "-";
                            }

                            insert("INSERT INTO probleme(idprobleme,idindicateur_district,nom,cause,objectif,faible,totalpoint) VALUES("
                                    + idPb + ","
                                    + id + ",'"
                                    + nom.replaceAll("'", " ") + "','"
                                    + cause.replaceAll("'", " ") + "','"
                                    + objectif.replaceAll("'", " ") + "',"
                                    + p.getFaible() + ","
                                    + p.getTotalpoint() + ")");

                            //on fait la priorisation des probleme
                            List<Notationprobleme> notationproblemes = notationproblemeFacadeLocal.find(p);
                            for (Notationprobleme n : notationproblemes) {
                                ResultSet nextIdNot = findNextId("notationprobleme", "idnotationprobleme");
                                int idNot = 0;
                                if (nextIdNot != null) {
                                    while (nextIdNot.next()) {
                                        idNot = nextIdNot.getInt(1) + 1;
                                    }
                                } else {
                                    idNot = 1;
                                }

                                insert("INSERT INTO notationprobleme(idnotationprobleme,idprobleme,idsousrubriquenotationprobleme,valeur) VALUES("
                                        + idNot + ","
                                        + idPb + ","
                                        + n.getIdsousrubriquenotationprobleme().getIdsousrubriquenotationprobleme() + ","
                                        + n.getValeur() + ")");

                            }

                            //on insere les activités
                            List<Activite> activites = activiteFacadeLocal.findByProbleme(p);
                            for (Activite a : activites) {

                                ResultSet nextIdAct = findNextId("activite", "idactivite");
                                Long idAct = 0l;
                                if (nextIdAct != null) {
                                    while (nextIdAct.next()) {
                                        idAct = nextIdAct.getLong(1) + 1l;
                                    }
                                } else {
                                    idAct = 1l;
                                }

                                Long idresultat = (Long) mapResult.get(a.getIdresultatattendu().getIdresultatAttenduDistrict());
                                Long idobjectif = (Long) mapObjectif.get(a.getIdidobjectifOpp().getIdobjectifOppDistrict());

                                String label = " - ";
                                try {
                                    label = a.getNom();
                                } catch (Exception e) {
                                    label = " - ";
                                }

                                insert("INSERT INTO activite(idactivite,idprobleme,idsourcefi,idtypestructure,nom,responsable,coutglobal,coutunitaire,idresultatattendu,ididobjectif_opp) VALUES("
                                        + idAct + ","
                                        + idPb + ","
                                        + a.getIdsourcefi().getIdsourcefi() + ","
                                        + a.getIdtypestructure().getIdtypestructure() + ",'"
                                        + label.replaceAll("'", " ") + "','"
                                        + a.getResponsable().replaceAll("'", " ") + "',"
                                        + a.getCoutglobal() + ","
                                        + a.getCoutunitaire() + ","
                                        + idresultat + ","
                                        + idobjectif + ")");

                                //on insere le chronogramme
                                List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(a);

                                for (Chronogramme c : chronogrammes) {
                                    ResultSet nextIdCh = findNextId("chronogramme", "idchronogramme");
                                    Long idCh = 0l;
                                    if (nextIdCh != null) {
                                        while (nextIdCh.next()) {
                                            idCh = nextIdCh.getLong(1) + 1l;
                                        }
                                    } else {
                                        idCh = 1l;
                                    }

                                    insert("INSERT INTO chronogramme(idchronogramme,idannee,idactivite,etat) VALUES("
                                            + idCh + ","
                                            + c.getIdannee().getIdannee() + ","
                                            + idAct + ","
                                            + c.getEtat() + ")");

                                }

                                //on enregistre les activité structure
                                List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(a);
                                for (ActiviteStructure as : activiteStructures) {
                                    ResultSet nextIdAs = findNextId("activite_structure", "idactivite_structure");
                                    Long idAs = 0l;
                                    if (nextIdAs != null) {
                                        while (nextIdAs.next()) {
                                            idAs = nextIdAs.getLong(1) + 1l;
                                        }
                                    } else {
                                        idAs = 1l;
                                    }

                                    int idstructure = (Integer) mapStructure.get(as.getIdstructure().getIdstructure());

                                    insert("INSERT INTO activite_structure(idactivite_structure,idsourcefi,idactivite,idstructure,idannee,cout,programe) VALUES("
                                            + idAs + ","
                                            + as.getIdsourcefi().getIdsourcefi() + ","
                                            + idAct + ","
                                            + idstructure + ","
                                            + as.getIdannee().getIdannee() + ","
                                            + as.getCout() + ","
                                            + as.getPrograme() + ")");

                                }

                                //on cost les activités
                                List<ActiviteElementCout> activiteElementCouts = activiteElementCoutFacadeLocal.findByActivite(a);
                                for (ActiviteElementCout aec : activiteElementCouts) {
                                    ResultSet nextIdAec = findNextId("activite_element_cout", "idactivite_element_cout");
                                    Long idAec = 0l;
                                    if (nextIdAec != null) {
                                        while (nextIdAec.next()) {
                                            idAec = nextIdAec.getLong(1) + 1l;
                                        }
                                    } else {
                                        idAec = 1l;
                                    }

                                    insert("INSERT INTO activite_element_cout(idactivite_element_cout,idactivite,idelementcout,coutunitaire,qte,nbre_jr) VALUES("
                                            + idAec + ","
                                            + idAct + ","
                                            + aec.getIdelementcout().getIdelementCout() + ","
                                            + aec.getCoutunitaire() + ","
                                            + aec.getQte() + ","
                                            + aec.getNbreJr() + ")");

                                }

                            }

                        }

                    }
                }*/

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.err.println("opération réussie");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ignore) {

                }
            }
        }
    }

    /**
     * Creates a new instance of ConsolidationController
     */
    public ConsolidationController() {
    }

    public void deleteForce(int idffom) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM force WHERE idffom = " + idffom);
        cnx.close();
    }

    public void deleteFaiblesse(int idffom) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM faiblesse WHERE idffom = " + idffom);
        cnx.close();
    }

    public void deleteOpportunite(int idffom) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM opportunite WHERE idffom = " + idffom);
        cnx.close();
    }

    public void deleteMenace(int idffom) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM menace WHERE idffom = " + idffom);
        cnx.close();
    }

    public void deleteFFOM(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM ffom WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteStructurecom(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM structurecommunautaire WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteHospitalisation(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM hospitalisationdistrict WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteActeur(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM acteurdistrict WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteFacteur(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM facteurdistrict WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteMorbidite(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM morbiditedistrict WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteMortalite(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM mortalitedistrict WHERE iddistrict =" + district);
        cnx.close();
    }

    public void deleteTabMatiereN1(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM tablematieren1_district WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteTabMatiereN2(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM tablematieren2_district WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteTabMatiereN3(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM tablematieren3_district WHERE iddistrict = " + district);
        cnx.close();
    }

    public ResultSet findAireSante(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        ResultSet resultatAire = statement.executeQuery("SELECT * FROM airesante WHERE iddistrict = " + district);
        cnx.close();
        return resultatAire;
    }

    public ResultSet findAireStructure(int aire) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        ResultSet resultatAire = statement.executeQuery("SELECT * FROM structure WHERE idairesante = " + aire);
        cnx.close();
        return resultatAire;
    }

    public void deleteMedicament(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM medicamenttraceur_structure WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteRhs(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM rhs WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteDepense(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM depense WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteRecette(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM recette WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteActiviteStr(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM activite_structure WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteGouvernance(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM gouvernancedistrict WHERE idstructure =" + structure);
        cnx.close();
    }

    public void deletePopulationFosa(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM populationfosa WHERE idstructure =" + structure);
        cnx.close();
    }

    public void deleteAdresse(int adresse) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM adresse WHERE id_adresse = " + adresse);
        cnx.close();
    }

    public void deleteStructure(int airesante) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM structure WHERE idairesante = " + airesante);
        cnx.close();
    }

    public void deleteStructure1(int structure) throws SQLException {
        try {
            Connection cnx = connexionDB();
            Statement statement = cnx.createStatement();
            statement.executeUpdate("DELETE FROM structure WHERE idstructure = " + structure);
            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAire(int district) throws SQLException {
        try {
            Connection cnx = connexionDB();
            Statement statement = cnx.createStatement();
            statement.executeUpdate("DELETE FROM airesante WHERE idairesante = " + district);
            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteInfosanitaire(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM informationsanitairedistrict WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteProjection(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM projectionrecette WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteEquipement(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM equipementtraceur WHERE idstructure = " + structure);
        cnx.close();
    }

    public void deleteInfrastructure(int structure) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM infrastructure WHERE idstructure = " + structure);
        cnx.close();
    }

    public ResultSet findPerformance(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        ResultSet resultatPerformance = statement.executeQuery("SELECT * FROM indicateur_district WHERE iddistrict = " + district);
        cnx.close();
        return resultatPerformance;
    }

    public ResultSet findProbleme(int performance) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        ResultSet resultatPerformance = statement.executeQuery("SELECT * FROM probleme WHERE idindicateur_district = " + performance);
        cnx.close();
        return resultatPerformance;
    }

    public ResultSet findActivite(int probleme) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        ResultSet resultatPerformance = statement.executeQuery("SELECT * FROM activite WHERE idprobleme = " + probleme);
        cnx.close();
        return resultatPerformance;
    }

    public void deleteChronogramme(int activite) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM chronogramme WHERE idactivite = " + activite);
        cnx.close();
    }

    public void deleteActivite(int probleme) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM activite WHERE idprobleme = " + probleme);
        cnx.close();
    }

    public void deleteProbleme(int performance) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM probleme WHERE idindicateur_district = " + performance);
        cnx.close();
    }

    public void deletePerformance(int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM indicateur_district WHERE iddistrict = " + district);
        cnx.close();
    }

    public void deleteNotation(int probleme) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM notationprobleme WHERE idprobleme = " + probleme);
        cnx.close();
    }

    public void deleteCible(int indicateur, int district) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        statement.executeUpdate("DELETE FROM cible WHERE idindicateur = " + indicateur + " AND iddistrict = " + district);
        cnx.close();
    }

    public ResultSet findNextId(String table, String colonne) throws SQLException {
        Connection cnx = connexionDB();
        Statement statement = cnx.createStatement();
        ResultSet resultatPerformance = statement.executeQuery("SELECT MAX(" + colonne + ") FROM " + table);
        cnx.close();
        return resultatPerformance;
    }

    public void insert(String requette) throws SQLException {
        try {
            Connection cnx = connexionDB();
            Statement statement = cnx.createStatement();
            statement.executeUpdate(requette);
            System.err.println(requette);
            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String requette) throws SQLException {
        try {
            Connection cnx = connexionDB();
            Statement statement = cnx.createStatement();
            statement.executeUpdate(requette);
            System.err.println(requette);
            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
