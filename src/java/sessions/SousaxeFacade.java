/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axe;
import entities.Sousaxe;
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
public class SousaxeFacade extends AbstractFacade<Sousaxe> implements SousaxeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SousaxeFacade() {
        super(Sousaxe.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(s.idsousaxe) FROM Sousaxe s");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Sousaxe> findAllRange() {
        List<Sousaxe> sousaxes = null;
        Query query = em.createQuery("SELECT s FROM Sousaxe s ORDER BY s.nomFr");
        sousaxes = query.getResultList();
        return sousaxes;
    }

    @Override
    public List<Sousaxe> findAllRangeByCode() {
        List<Sousaxe> sousaxes = null;
        Query query = em.createQuery("SELECT s FROM Sousaxe s ORDER BY s.idaxe.code,s.code,s.nomFr");
        sousaxes = query.getResultList();
        return sousaxes;
    }

    @Override
    public List<Sousaxe> findByAxe(Axe axe) throws Exception {
        List<Sousaxe> sousaxes = null;
        Query query = em.createQuery("SELECT s FROM Sousaxe s WHERE s.idaxe.idaxe=:axe ORDER BY s.code");
        query.setParameter("axe", axe.getIdaxe());
        sousaxes = query.getResultList();
        return sousaxes;
    }

}
