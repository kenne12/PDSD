/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Observation;
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
public class ObservationFacade extends AbstractFacade<Observation> implements ObservationFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservationFacade() {
        super(Observation.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idobservation) FROM Observation n");
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
    public List<Observation> findByNom(String nom) {
        List<Observation> naturecontencieus = null;
        try {
            Query query = em.createQuery("SELECT o FROM Observation o WHERE o.valeurFr=:valeur");
            query.setParameter("valeur", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

}
