/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
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
public class DistrictFacade extends AbstractFacade<District> implements DistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistrictFacade() {
        super(District.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(d.iddistrict) FROM District d");
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
    public List<District> findByNom(String nom) {
        List<District> districts = null;
        try {
            Query query = em.createQuery("SELECT d FROM District d WHERE d.nomFr=:nom");
            query.setParameter("nom", nom);
            districts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districts;
    }

    @Override
    public List<District> findByRegion(int region) {
        List<District> districts = null;
        try {
            Query query = em.createQuery("SELECT d FROM District d WHERE d.idregion.idregion=:region ORDER BY d.nomFr");
            query.setParameter("region", region);
            districts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districts;
    }

    @Override
    public List<District> findAllRange() {
        List<District> districts = null;
        Query query = em.createQuery("SELECT d FROM District d ORDER BY d.idregion.nomFr,d.nomFr");
        districts = query.getResultList();
        return districts;
    }

}
