/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categoriestructurecom;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class CategoriestructurecomFacade extends AbstractFacade<Categoriestructurecom> implements CategoriestructurecomFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriestructurecomFacade() {
        super(Categoriestructurecom.class);
    }
    
    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idcategoriestructurecom) FROM Categoriestructurecom c");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }
    
}
