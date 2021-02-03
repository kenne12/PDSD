/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periodicite;
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
public class PeriodiciteFacade extends AbstractFacade<Periodicite> implements PeriodiciteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodiciteFacade() {
        super(Periodicite.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(p.idperiodicite) FROM Periodicite p");
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
    public List<Periodicite> findByNom(String nom) {
        List<Periodicite> periodicites = null;
        try {
            Query query = em.createQuery("SELECT p FROM Periodicite p WHERE UPPER(p.nomFr)=UPPER(:nom)");
            query.setParameter("nom", nom);
            periodicites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodicites;
    }

    @Override
    public List<Periodicite> findAllRange() {
        List<Periodicite> periodicites = null;
        Query query = em.createQuery("SELECT p FROM Periodicite p ORDER BY p.nomFr");
        periodicites = query.getResultList();
        return periodicites;
    }
}
