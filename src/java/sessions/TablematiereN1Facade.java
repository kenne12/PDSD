/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN1;
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
public class TablematiereN1Facade extends AbstractFacade<TablematiereN1> implements TablematiereN1FacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablematiereN1Facade() {
        super(TablematiereN1.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtablematiereN1) FROM TablematiereN1 t");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<TablematiereN1> findAllRange() throws Exception {
        List<TablematiereN1> tablematiereN1s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN1 t ORDER BY t.idtablematiereN1,t.defaultnumpage");
        tablematiereN1s = query.getResultList();
        return tablematiereN1s;
    }
    
    @Override
    public List<TablematiereN1> findAllRange(Boolean district) throws Exception{
        List<TablematiereN1> tablematiereN1s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN1 t WHERE t.district=true ORDER BY t.idtablematiereN1,t.defaultnumpage");
        tablematiereN1s = query.getResultList();
        return tablematiereN1s;
    }

}
