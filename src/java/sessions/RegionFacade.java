/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Region;
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
public class RegionFacade extends AbstractFacade<Region> implements RegionFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(r.idregion) FROM Region r");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public List<Region> findByNom(String nom) {
        List<Region> regions = null;
        try {
            Query query = em.createQuery("SELECT r FROM Region r WHERE r.nomFr=:nom");
            query.setParameter("nom", nom);
            regions = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }

    @Override
    public List<Region> find(Boolean etat) throws Exception {
        List<Region> regions = null;
        Query query = em.createQuery("SELECT r FROM Region r");
        //query.setParameter("active", etat);
        regions = query.getResultList();
        return regions;
    }

    @Override
    public List<Region> findAllRange() {
        List<Region> regions = null;
        Query query = em.createQuery("SELECT r FROM Region r ORDER BY r.nomFr");
        regions = query.getResultList();
        return regions;
    }

}
