/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axe;
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
public class AxeFacade extends AbstractFacade<Axe> implements AxeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AxeFacade() {
        super(Axe.class);
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idaxe) FROM Axe a");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Axe> findAllRange() {
        List<Axe> axes = null;
        Query query = em.createQuery("SELECT a FROM Axe a ORDER BY a.nomFr");
        axes = query.getResultList();
        return axes;
    }

    @Override
    public List<Axe> findAllRangeByCode() {
        List<Axe> axes = null;
        Query query = em.createQuery("SELECT a FROM Axe a ORDER BY a.code");
        axes = query.getResultList();
        return axes;
    }
}
