/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ElementCout;
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
public class ElementCoutFacade extends AbstractFacade<ElementCout> implements ElementCoutFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementCoutFacade() {
        super(ElementCout.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idelementCout) FROM ElementCout e");
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
    public List<ElementCout> findbyElementCout(String nomFr) {
        List<ElementCout> elementcouts = null;
        try {
            Query query = em.createNamedQuery("ElementCout.findByNomFr");
            query.setParameter("nomFr", nomFr);
            elementcouts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementcouts;
    }

}
