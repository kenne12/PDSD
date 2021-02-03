/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categorieintervention;
import entities.Indicateur;
import entities.Interventionpnds;
import entities.Niveaucollecte;
import entities.Periodicite;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class IndicateurFacade extends AbstractFacade<Indicateur> implements IndicateurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicateurFacade() {
        super(Indicateur.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(i.idindicateur) FROM Indicateur i");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<Indicateur> findByNom(String nom) {
        List<Indicateur> unites = null;
        try {
            Query query = em.createQuery("SELECT i FROM Indicateur i WHERE UPPER(i.nomFr)=UPPER(:nom)");
            query.setParameter("nom", nom);
            unites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unites;
    }

    @Override
    public List<Indicateur> findByCategorie(int categorie) throws Exception {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT c FROM Indicateur c WHERE c.idcategorie.idcategorie=:categorie");
        query.setParameter("categorie", categorie);
        indicateurs = query.getResultList();
        return indicateurs;
    }

    @Override
    public List<Indicateur> find(Periodicite periodicite) throws Exception {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT c FROM Indicateur c WHERE c.idperiodicite.idperiodicite=:periodicite");
        query.setParameter("periodicite", periodicite.getIdperiodicite());
        indicateurs = query.getResultList();
        return indicateurs;
    }

    @Override
    public List<Indicateur> findByCategorieIntervention(Categorieintervention categorieintervention, Niveaucollecte niveaucollecte) throws Exception {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT c FROM Indicateur c WHERE c.idinterventionpnds.idcategorieintervention.idcategorieintervention=:categorieintervention AND c.idinterventionpnds.district = true AND  c.idniveaucollecte.idniveaucollecte=:niveaucollecte ORDER BY c.nomFr");
        query.setParameter("categorieintervention", categorieintervention.getIdcategorieintervention()).setParameter("niveaucollecte", niveaucollecte.getIdniveaucollecte());
        indicateurs = query.getResultList();
        return indicateurs;
    }

    @Override
    public List<Indicateur> findAllRange() {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT i FROM Indicateur i ORDER BY i.idinterventionpnds.code,i.nomFr");
        indicateurs = query.getResultList();
        return indicateurs;
    }

    @Override
    public List<Indicateur> findBySousAxeNiveauCollecte(Sousaxe sousaxe, Integer niveaucollecte) throws Exception {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT i FROM Indicateur i WHERE i.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND i.idniveaucollecte.idniveaucollecte=:niveaucollecte AND i.idinterventionpnds.district=true");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("niveaucollecte", niveaucollecte);
        indicateurs = query.getResultList();
        return indicateurs;
    }

    @Override
    public List<Indicateur> findByNiveauCollecte(Integer niveaucollecte) throws Exception {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT i FROM Indicateur i WHERE i.idniveaucollecte.idniveaucollecte=:niveaucollecte AND i.idinterventionpnds.district=true ORDER BY i.nomFr");
        query.setParameter("niveaucollecte", niveaucollecte);
        indicateurs = query.getResultList();
        return indicateurs;
    }

    @Override
    public List<Indicateur> findByIntervention(Interventionpnds interventionpnds) throws Exception {
        List<Indicateur> indicateurs = null;
        Query query = em.createQuery("SELECT i FROM Indicateur i WHERE i.idinterventionpnds.idinterventionpnds=:intervention");
        query.setParameter("intervention", interventionpnds.getIdinterventionpnds());
        indicateurs = query.getResultList();
        return indicateurs;
    }

}
