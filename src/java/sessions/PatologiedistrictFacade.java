/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Patologiedistrict;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kenne
 */
@Stateless
public class PatologiedistrictFacade extends AbstractFacade<Patologiedistrict> implements PatologiedistrictFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatologiedistrictFacade() {
        super(Patologiedistrict.class);
    }
    
}
