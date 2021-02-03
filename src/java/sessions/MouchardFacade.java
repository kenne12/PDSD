/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mouchard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class MouchardFacade extends AbstractFacade<Mouchard> implements MouchardFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouchardFacade() {
        super(Mouchard.class);
    }

    @Override
    public Long nextId() {

        Query query = em.createQuery("SELECT MAX(m.idoperation) FROM Mouchard m");
        Long resultat = (Long) query.getSingleResult();

        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }

    }

}
