/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteDefault;
import entities.CoastingDefault;
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
public class CoastingDefaultFacade extends AbstractFacade<CoastingDefault> implements CoastingDefaultFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoastingDefaultFacade() {
        super(CoastingDefault.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idcoastingDefault) FROM CoastingDefault c");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<CoastingDefault> findByActivite(ActiviteDefault activiteDefault) throws Exception {
        List<CoastingDefault> coastingDefaults = null;
        Query query = em.createQuery("SELECT d FROM CoastingDefault d WHERE d.idactiviteDefault.idactiviteDefault=:activite");
        query.setParameter("activite", activiteDefault.getIdactiviteDefault());
        coastingDefaults = query.getResultList();
        return coastingDefaults;
    }

}
