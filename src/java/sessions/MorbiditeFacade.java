/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Morbidite;
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
public class MorbiditeFacade extends AbstractFacade<Morbidite> implements MorbiditeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MorbiditeFacade() {
        super(Morbidite.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idmorbidite) FROM Morbidite e");
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
    public List<Morbidite> findByNom(String nom) {
        List<Morbidite> morbidites = null;
        try {
            Query query = em.createQuery("SELECT m FROM Morbidite m WHERE m.nomFr=:nom");
            query.setParameter("nom", nom);
            morbidites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return morbidites;
    }

    @Override
    public List<Morbidite> findByDistrict(int district) {
        List<Morbidite> morbidites = null;
        try {
            Query query = em.createQuery("SELECT s FROM Morbidite s WHERE s.morbiditedistrictList=:district");
            query.setParameter("district", district);
            morbidites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return morbidites;
    }

    @Override
    public List<Morbidite> findAllRange() {
        List<Morbidite> morbidites = null;
        Query query = em.createQuery("SELECT a FROM Morbidite a ORDER BY a.nomFr");
        morbidites = query.getResultList();
        return morbidites;
    }

    @Override
    public List<Morbidite> findAllRangeCode() {
        List<Morbidite> morbidites = null;
        Query query = em.createQuery("SELECT a FROM Morbidite a ORDER BY a.code");
        morbidites = query.getResultList();
        return morbidites;
    }

    @Override
    public List<Morbidite> findAllRangeId() {
        List<Morbidite> morbidites = null;
        Query query = em.createQuery("SELECT a FROM Morbidite a ORDER BY a.idmorbidite");
        morbidites = query.getResultList();
        return morbidites;
    }
}
