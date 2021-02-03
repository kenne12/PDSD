/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axe;
import entities.District;
import entities.Indicateur;
import entities.IndicateurDistrict;
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
public class ProblemeFacade extends AbstractFacade<Probleme> implements ProblemeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProblemeFacade() {
        super(Probleme.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idprobleme) FROM Probleme p");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Probleme> find(Indicateur indicateur, District district, Sousaxe sousaxe) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.iddistrict.iddistrict=:district AND p.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND p.idindicateurDistrict.iddistrict.iddistrict=:district");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("district", district.getIddistrict());
        problemes = query.getResultList();
        return problemes;
    }

    @Override
    public List<Probleme> find(District district, Sousaxe sousaxe, int observation) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.iddistrict.iddistrict=:district AND p.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND p.idindicateurDistrict.idobservation.idobservation=:observation ORDER BY p.totalpoint DESC");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("district", district.getIddistrict()).setParameter("observation", observation);
        problemes = query.getResultList();
        return problemes;
    }

    @Override
    public List<Probleme> find(District district, Sousaxe sousaxe) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.iddistrict.iddistrict=:district AND p.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe ORDER BY p.totalpoint DESC");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("district", district.getIddistrict());
        problemes = query.getResultList();
        return problemes;
    }

    @Override
    public List<Probleme> find(IndicateurDistrict indicateurDistrict) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.idindicateurDistrict=:indicateur");
        query.setParameter("indicateur", indicateurDistrict.getIdindicateurDistrict());
        problemes = query.getResultList();
        return problemes;
    }

    @Override
    public List<Probleme> find(Indicateur indicateur, District district, int observation) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.iddistrict.iddistrict=:district AND p.idindicateurDistrict.idindicateur.idindicateur=:indicateur AND p.idindicateurDistrict.idobservation.idobservation=:observation ORDER BY p.totalpoint DESC");
        query.setParameter("indicateur", indicateur.getIdindicateur()).setParameter("district", district.getIddistrict()).setParameter("observation", observation);
        problemes = query.getResultList();
        return problemes;
    }

    @Override
    public List<Probleme> findByAxeDistrict(Axe axe, District district, int observation) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idaxe.idaxe=:axe AND p.idindicateurDistrict.iddistrict.iddistrict=:district AND p.idindicateurDistrict.idobservation.idobservation=:observation");
        query.setParameter("axe", axe.getIdaxe()).setParameter("district", district.getIddistrict()).setParameter("observation", observation);
        problemes = query.getResultList();
        return problemes;
    }

    @Override
    public List<Probleme> findBySousAxeDistrict(Sousaxe sousaxe, District district, int observation) throws Exception {
        List<Probleme> problemes = null;
        Query query = em.createQuery("SELECT p FROM Probleme p WHERE p.idindicateurDistrict.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND p.idindicateurDistrict.iddistrict.iddistrict=:district AND p.idindicateurDistrict.idobservation.idobservation=:observation");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("district", district.getIddistrict()).setParameter("observation", observation);
        problemes = query.getResultList();
        return problemes;
    }
}
