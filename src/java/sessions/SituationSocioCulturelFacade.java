/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.SituationSocioCulturel;
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
public class SituationSocioCulturelFacade extends AbstractFacade<SituationSocioCulturel> implements SituationSocioCulturelFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SituationSocioCulturelFacade() {
        super(SituationSocioCulturel.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idsituationsociocult) FROM SituationSocioCulturel e");
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
    public List<SituationSocioCulturel> findByDistrict(int district) {
        List<SituationSocioCulturel> situationSocioCulturels = null;
        try {
            Query query = em.createQuery("SELECT s FROM SituationSocioCulturel s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            situationSocioCulturels = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return situationSocioCulturels;
    }

    @Override
    public List<SituationSocioCulturel> findByEducation(String introduction) {
        List<SituationSocioCulturel> situationSocioCulturels = null;
        try {
            Query query = em.createNamedQuery("SituationSocioCulturel.findByEducation");
            query.setParameter("introduction", introduction);
            situationSocioCulturels = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return situationSocioCulturels;
    }

}
