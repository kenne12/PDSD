/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categorieintervention;
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
public class CategorieinterventionFacade extends AbstractFacade<Categorieintervention> implements CategorieinterventionFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieinterventionFacade() {
        super(Categorieintervention.class);
    }

    @Override
    public List<Categorieintervention> findAllRange() {
        List<Categorieintervention> categorieinterventions = null;
        Query query = em.createQuery("SELECT c FROM Categorieintervention c ORDER BY c.idsousaxe.idaxe.code,c.idsousaxe.code,c.code");
        categorieinterventions = query.getResultList();
        return categorieinterventions;
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idcategorieintervention) FROM Categorieintervention c");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Categorieintervention> find(Sousaxe sousaxe) throws Exception {
        List<Categorieintervention> categorieinterventions = null;
        Query query = em.createQuery("SELECT c FROM Categorieintervention c WHERE c.idsousaxe.idsousaxe=:sousaxe ORDER BY c.code");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe());
        categorieinterventions = query.getResultList();
        return categorieinterventions;
    }

}
