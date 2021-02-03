/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mape;
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
public class MapeFacade extends AbstractFacade<Mape> implements MapeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapeFacade() {
        super(Mape.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idmape) FROM Mape e");
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
    public List<Mape> findByNom(String nom) {
        List<Mape> mapes = null;
        try {
            Query query = em.createQuery("SELECT m FROM Mape m WHERE m.nomFr=:nom");
            query.setParameter("nom", nom);
            mapes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapes;
    }

    @Override
    public List<Mape> findByDistrict(int district) {
        List<Mape> mapes = null;
        try {
            Query query = em.createQuery("SELECT s FROM Mape s WHERE s.mapedistrictList.iddistrict=:district");
            query.setParameter("district", district);
            mapes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapes;
    }

    @Override
    public List<Mape> findAllRange() {
        List<Mape> mapes = null;
        Query query = em.createQuery("SELECT a FROM Mape a ORDER BY a.nomFr");
        mapes = query.getResultList();
        return mapes;
    }

    @Override
    public List<Mape> findAllCode() {
        List<Mape> mapes = null;
        Query query = em.createQuery("SELECT a FROM Mape a ORDER BY a.code ASC");
        mapes = query.getResultList();
        return mapes;
    }

    @Override
    public List<Mape> findAllRangeId() {
        List<Mape> mapes = null;
        Query query = em.createQuery("SELECT a FROM Mape a ORDER BY a.idmape");
        mapes = query.getResultList();
        return mapes;
    }
}
