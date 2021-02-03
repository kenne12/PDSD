/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TablematiereN1;
import entities.TablematiereN2;
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
public class TablematiereN2Facade extends AbstractFacade<TablematiereN2> implements TablematiereN2FacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablematiereN2Facade() {
        super(TablematiereN2.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtablematiereN2) FROM TablematiereN2 t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<TablematiereN2> findByAllRange() throws Exception {
        List<TablematiereN2> tablematiereN2s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN2 t ORDER BY t.idtablematiereN1.idtablematiereN1 , t.idtablematiereN2,t.defaultnumpage");
        tablematiereN2s = query.getResultList();
        return tablematiereN2s;
    }

    @Override
    public List<TablematiereN2> findByAllRange(Boolean district) throws Exception {
        List<TablematiereN2> tablematiereN2s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN2 t WHERE t.idtablematiereN1.district = true ORDER BY t.idtablematiereN1.idtablematiereN1 , t.idtablematiereN2,t.defaultnumpage");
        tablematiereN2s = query.getResultList();
        return tablematiereN2s;
    }

    @Override
    public List<TablematiereN2> findByTablematieren1(TablematiereN1 tablematiereN1) throws Exception {
        List<TablematiereN2> tablematiereN2s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN2 t WHERE t.idtablematiereN1.idtablematiereN1=:tablen1 ORDER BY t.idtablematiereN2");
        query.setParameter("tablen1", tablematiereN1.getIdtablematiereN1());
        tablematiereN2s = query.getResultList();
        return tablematiereN2s;
    }

}
