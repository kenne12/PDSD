/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.District;
import entities.Probleme;
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
public class ActiviteFacade extends AbstractFacade<Activite> implements ActiviteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteFacade() {
        super(Activite.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idactivite) FROM Activite a");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Activite> findAllRange() {
        List<Activite> activites = null;
        Query query = em.createQuery("SELECT a FROM Activite a ORDER BY a.nom ,a.coutglobal");
        activites = query.getResultList();
        return activites;
    }

    @Override
    public List<Activite> findByProbleme(Probleme probleme) throws Exception {
        List<Activite> activites = null;
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idprobleme.idprobleme=:probleme");
        query.setParameter("probleme", probleme.getIdprobleme());
        activites = query.getResultList();
        return activites;
    }

    @Override
    public List<Activite> findBySousAxe(Sousaxe sousaxe, District district) throws Exception {
        List<Activite> activites = null;
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idprobleme.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND a.idprobleme.idindicateurDistrict.iddistrict.iddistrict=:district ORDER BY a.idprobleme.idindicateurDistrict.idindicateur.idinterventionpnds.code");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("district", district.getIddistrict());
        activites = query.getResultList();
        return activites;
    }

    @Override
    public List<Activite> find(District district, Sousaxe sousaxe, int observation) throws Exception {
        List<Activite> activites = null;
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idprobleme.idindicateurDistrict.iddistrict.iddistrict=:district AND a.idprobleme.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND a.idprobleme.idindicateurDistrict.idobservation.idobservation=:observation ORDER BY a.idprobleme.totalpoint ASC");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("district", district.getIddistrict()).setParameter("observation", observation);
        activites = query.getResultList();
        return activites;
    }

    @Override
    public List<Activite> findByDistrict(District district) throws Exception {
        List<Activite> activites = null;
        Query query = em.createQuery("SELECT a FROM Activite a WHERE a.idprobleme.idindicateurDistrict.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        activites = query.getResultList();
        return activites;
    }

}
