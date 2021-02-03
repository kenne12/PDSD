/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mortalite;
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
public class MortaliteFacade extends AbstractFacade<Mortalite> implements MortaliteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MortaliteFacade() {
        super(Mortalite.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idmortalite) FROM Mortalite e");
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
    public List<Mortalite> findByNom(String nom) {
        List<Mortalite> mortalites = null;
        try {
            Query query = em.createQuery("SELECT m FROM Mortalite m WHERE m.nomFr=:nom");
            query.setParameter("nom", nom);
            mortalites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mortalites;
    }

    @Override
    public List<Mortalite> findByDistrict(int district) {
        List<Mortalite> mortalites = null;
        try {
            Query query = em.createQuery("SELECT s FROM Mortalite s WHERE s.mortalitedistrictList=:district");
            query.setParameter("district", district);
            mortalites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mortalites;
    }

    @Override
    public List<Mortalite> findAllRange() {
        List<Mortalite> mortalites = null;
        Query query = em.createQuery("SELECT a FROM Mortalite a ORDER BY a.nomFr");
        mortalites = query.getResultList();
        return mortalites;
    }

    @Override
    public List<Mortalite> findAllRangeCode() {
        List<Mortalite> mortalites = null;
        Query query = em.createQuery("SELECT a FROM Mortalite a ORDER BY a.code");
        mortalites = query.getResultList();
        return mortalites;
    }

    @Override
    public List<Mortalite> findAllRangeId() {
        List<Mortalite> mortalites = null;
        Query query = em.createQuery("SELECT a FROM Mortalite a ORDER BY a.idmortalite");
        mortalites = query.getResultList();
        return mortalites;
    }
}
