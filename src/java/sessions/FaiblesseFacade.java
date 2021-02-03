/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Faiblesse;
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
public class FaiblesseFacade extends AbstractFacade<Faiblesse> implements FaiblesseFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaiblesseFacade() {
        super(Faiblesse.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(f.idfaiblesse) FROM Faiblesse f");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Faiblesse> findByFfom(Ffom ffom) throws Exception {
        List<Faiblesse> faiblesses = null;
        Query query = em.createQuery("SELECT f FROM Faiblesse f WHERE f.idffom.idffom=:ffom");
        query.setParameter("ffom", ffom.getIdffom());
        faiblesses = query.getResultList();
        return faiblesses;
    }

}
