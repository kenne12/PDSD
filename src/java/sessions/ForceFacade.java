/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Ffom;
import entities.Force;
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
public class ForceFacade extends AbstractFacade<Force> implements ForceFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ForceFacade() {
        super(Force.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(f.idforce) FROM Force f");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Force> findByFfom(Ffom ffom) throws Exception {
        List<Force> forces = null;
        Query query = em.createQuery("SELECT f FROM Force f WHERE f.idffom.idffom=:ffom");
        query.setParameter("ffom", ffom.getIdffom());
        forces = query.getResultList();
        return forces;
    }

}
