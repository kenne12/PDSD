/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Interventionpnds;
import entities.ObjectifOppDistrict;
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
public class ObjectifOppDistrictFacade extends AbstractFacade<ObjectifOppDistrict> implements ObjectifOppDistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjectifOppDistrictFacade() {
        super(ObjectifOppDistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(o.idobjectifOppDistrict) FROM ObjectifOppDistrict o");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ObjectifOppDistrict> findByDistrict(District district) throws Exception {
        List<ObjectifOppDistrict> objectifOppDistricts = null;
        Query query = em.createQuery("SELECT o FROM ObjectifOppDistrict o WHERE o.iddistrict.iddistrict=:district ORDER BY o.idintervention.idcategorieintervention.idsousaxe.idaxe.code,o.idintervention.idcategorieintervention.idsousaxe.code,o.idintervention.code");
        query.setParameter("district", district.getIddistrict());
        objectifOppDistricts = query.getResultList();
        return objectifOppDistricts;
    }

    @Override
    public List<ObjectifOppDistrict> findByDistrict(District district, Interventionpnds interventionpnds) throws Exception {
        List<ObjectifOppDistrict> objectifOppDistricts = null;
        Query query = em.createQuery("SELECT o FROM ObjectifOppDistrict o WHERE o.iddistrict.iddistrict=:district AND o.idintervention.idinterventionpnds=:intervention ");
        query.setParameter("district", district.getIddistrict()).setParameter("intervention", interventionpnds.getIdinterventionpnds());
        objectifOppDistricts = query.getResultList();
        return objectifOppDistricts;
    }

}
