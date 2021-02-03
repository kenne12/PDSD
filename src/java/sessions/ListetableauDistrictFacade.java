/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.ListetableauDistrict;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Stateless
public class ListetableauDistrictFacade extends AbstractFacade<ListetableauDistrict> implements ListetableauDistrictFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListetableauDistrictFacade() {
        super(ListetableauDistrict.class);
    }
    
    
          @Override
    public long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idlistetableauDistrict) FROM ListetableauDistrict n");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((long) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }
                 @Override
    public List<ListetableauDistrict> findByDistrict(District district) throws Exception {
        List<ListetableauDistrict> listetableaudistricts = null;
        Query query = em.createQuery("SELECT f FROM ListetableauDistrict f WHERE f.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        listetableaudistricts = query.getResultList();
        return listetableaudistricts;
    }
    
     @Override
    public List<ListetableauDistrict> findByDistrict(int district) {
        List<ListetableauDistrict> listetableaus = null;
        try {
            Query query = em.createQuery("SELECT s FROM ListetableauDistrict s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            listetableaus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listetableaus;
    }

}
