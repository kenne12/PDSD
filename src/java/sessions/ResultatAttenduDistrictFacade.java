/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Indicateur;
import entities.ResultatAttenduDistrict;
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
public class ResultatAttenduDistrictFacade extends AbstractFacade<ResultatAttenduDistrict> implements ResultatAttenduDistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultatAttenduDistrictFacade() {
        super(ResultatAttenduDistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(r.idresultatAttenduDistrict) FROM ResultatAttenduDistrict r");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ResultatAttenduDistrict> findByDistrict(District district) throws Exception {
        List<ResultatAttenduDistrict> resultatAttenduDistricts = null;
        Query query = em.createQuery("SELECT r FROM ResultatAttenduDistrict r WHERE r.iddistrict.iddistrict=:district ORDER BY r.idindicateur.idinterventionpnds.code , r.idindicateur.nomFr");
        query.setParameter("district", district.getIddistrict());
        resultatAttenduDistricts = query.getResultList();
        return resultatAttenduDistricts;
    }

    @Override
    public List<ResultatAttenduDistrict> findByIndicateur(Indicateur indicateteur) throws Exception {
        List<ResultatAttenduDistrict> resultatAttenduDistricts = null;
        Query query = em.createQuery("SELECT r FROM ResultatAttenduDistrict r WHERE r.idindicateur.idindicateur=:indicateur  ORDER BY r.idindicateur.idinterventionpnds.code , r.idindicateur.nomFr");
        query.setParameter("indicateur", indicateteur.getIdindicateur());
        resultatAttenduDistricts = query.getResultList();
        return resultatAttenduDistricts;
    }

    @Override
    public List<ResultatAttenduDistrict> findByIndicateur(Indicateur indicateteur, District district) throws Exception {
        List<ResultatAttenduDistrict> resultatAttenduDistricts = null;
        Query query = em.createQuery("SELECT r FROM ResultatAttenduDistrict r WHERE r.idindicateur.idindicateur=:indicateur AND r.iddistrict.iddistrict=:district  ORDER BY r.idindicateur.idinterventionpnds.code , r.idindicateur.nomFr");
        query.setParameter("indicateur", indicateteur.getIdindicateur()).setParameter("district", district.getIddistrict());
        resultatAttenduDistricts = query.getResultList();
        return resultatAttenduDistricts;
    }

}
