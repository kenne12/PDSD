/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TablematiereN2;
import entities.TablematiereN3;
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
public class TablematiereN3Facade extends AbstractFacade<TablematiereN3> implements TablematiereN3FacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablematiereN3Facade() {
        super(TablematiereN3.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtablematiereN3) FROM TablematiereN3 t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<TablematiereN3> findByAllRange() throws Exception {
        List<TablematiereN3> tablematiereN3s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN3 t ORDER BY t.idtablematiereN2.idtablematiereN2 , t.idtablematiereN3,t.defaultnumpage");
        tablematiereN3s = query.getResultList();
        return tablematiereN3s;
    }

    @Override
    public List<TablematiereN3> findByAllRange(Boolean district) throws Exception {
        List<TablematiereN3> tablematiereN3s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN3 t WHERE t.idtablematiereN2.idtablematiereN1.district=true ORDER BY t.idtablematiereN2.idtablematiereN2 , t.idtablematiereN3,t.defaultnumpage");
        tablematiereN3s = query.getResultList();
        return tablematiereN3s;
    }

    @Override
    public List<TablematiereN3> findByTablematiereN3(TablematiereN2 tablematiereN2) throws Exception {
        List<TablematiereN3> tablematiereN3s = null;
        Query query = em.createQuery("SELECT t FROM TablematiereN3 t WHERE t.idtablematiereN2.idtablematiereN2=:table");
        query.setParameter("table", tablematiereN2.getIdtablematiereN2());
        tablematiereN3s = query.getResultList();
        return tablematiereN3s;
    }

}
