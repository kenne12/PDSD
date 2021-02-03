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
public class IndicateurDistrictFacade extends AbstractFacade<IndicateurDistrict> implements IndicateurDistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicateurDistrictFacade() {
        super(IndicateurDistrict.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(d.idindicateurDistrict) FROM IndicateurDistrict d");
        Long result = (Long) query.getSingleResult();
        if (result != null) {
            result += 1;
        } else {
            result = 1L;
        }
        return result;
    }

    @Override
    public List<IndicateurDistrict> findByIndicateur(Indicateur indicateur) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.idindicateur.idindicateur=:indicateur");
        query.setParameter("indicateur", indicateur.getIdindicateur());
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

    @Override
    public List<IndicateurDistrict> findByIndicateur(Indicateur indicateur, District district) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.idindicateur.idindicateur=:indicateur AND i.iddistrict.iddistrict=:district");
        query.setParameter("indicateur", indicateur.getIdindicateur()).setParameter("district", district.getIddistrict());
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

    @Override
    public List<IndicateurDistrict> findByDistrictSousaxe(District district, Sousaxe sousaxe) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.iddistrict.iddistrict=:district AND i.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe");
        query.setParameter("district", district.getIddistrict()).setParameter("sousaxe", sousaxe.getIdsousaxe());
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

    @Override
    public List<IndicateurDistrict> findByDistrictAxeObservation(District district, Axe axe, int idobservation) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.iddistrict.iddistrict=:district AND i.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idaxe.idaxe=:axe AND i.idobservation.idobservation=:observation AND i.idindicateur.idinterventionpnds.district=true");
        query.setParameter("district", district.getIddistrict()).setParameter("axe", axe.getIdaxe()).setParameter("observation", idobservation);
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

    @Override
    public List<IndicateurDistrict> findByDistrict(District district) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

    @Override
    public List<IndicateurDistrict> findByDistrictSousaxeObservation(District district, Sousaxe sousaxe, int idobservation) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.iddistrict.iddistrict=:district AND i.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND i.idobservation.idobservation=:observation");
        query.setParameter("district", district.getIddistrict()).setParameter("sousaxe", sousaxe.getIdsousaxe()).setParameter("observation", idobservation);
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

    @Override
    public List<IndicateurDistrict> findByDistrictIndicateurObservation(District district, Indicateur indicateur, int idobservation) throws Exception {
        List<IndicateurDistrict> indicateurDistricts = null;
        Query query = em.createQuery("SELECT i FROM IndicateurDistrict i WHERE i.iddistrict.iddistrict=:district AND i.idindicateur.idindicateur=:indicateur AND i.idobservation.idobservation=:observation");
        query.setParameter("district", district.getIddistrict()).setParameter("indicateur", indicateur.getIdindicateur()).setParameter("observation", idobservation);
        indicateurDistricts = query.getResultList();
        return indicateurDistricts;
    }

}
