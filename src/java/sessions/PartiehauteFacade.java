/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Partiehaute;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
@Stateless
public class PartiehauteFacade extends AbstractFacade<Partiehaute> implements PartiehauteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartiehauteFacade() {
        super(Partiehaute.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idpartiehaute) FROM Partiehaute e");
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
    public List<Partiehaute> findByDistrict(int district) {
        List<Partiehaute> partiehautes = null;
        try {
            Query query = em.createQuery("SELECT s FROM Partiehaute s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            partiehautes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partiehautes;
    }

    @Override
    public List<Partiehaute> findByDistrict(District district) throws Exception {
        List<Partiehaute> partiehautes = null;
        Query query = em.createQuery("SELECT p FROM Partiehaute p WHERE p.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        partiehautes = query.getResultList();
        return partiehautes;
    }

    @Override
    public List<Partiehaute> findByIntroduction(String introduction) {
        List<Partiehaute> partiehautes = null;
        try {
            Query query = em.createNamedQuery("Partiehaute.findByIntroductionFr");
            query.setParameter("introduction", introduction);
            partiehautes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partiehautes;
    }
}
