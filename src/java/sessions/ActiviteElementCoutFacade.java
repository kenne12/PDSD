/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.ActiviteElementCout;
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
public class ActiviteElementCoutFacade extends AbstractFacade<ActiviteElementCout> implements ActiviteElementCoutFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteElementCoutFacade() {
        super(ActiviteElementCout.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idactiviteElementCout) FROM ActiviteElementCout a");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ActiviteElementCout> findByActivite(Activite activite) throws Exception {
        List<ActiviteElementCout> activiteElementCouts = null;
        Query query = em.createQuery("SELECT a FROM ActiviteElementCout a WHERE a.idactivite.idactivite=:activite");
        query.setParameter("activite", activite.getIdactivite());
        activiteElementCouts = query.getResultList();
        return activiteElementCouts;
    }

}
