/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Ffom;
import entities.Pilier;
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
public class FfomFacade extends AbstractFacade<Ffom> implements FfomFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FfomFacade() {
        super(Ffom.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(f.idffom) FROM Ffom f ");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<Ffom> find(District district, Pilier pilier) throws Exception {
        List<Ffom> ffoms = null;
        Query query = em.createQuery("SELECT f FROM Ffom f WHERE f.iddistrict.iddistrict=:district AND f.idpilier.idpilier=:pilier");
        query.setParameter("district", district.getIddistrict()).setParameter("pilier", pilier.getIdpilier());
        ffoms = query.getResultList();
        return ffoms;
    }

    @Override
    public List<Ffom> find(District district) throws Exception {
        List<Ffom> ffoms = null;
        Query query = em.createQuery("SELECT f FROM Ffom f WHERE f.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        ffoms = query.getResultList();
        return ffoms;
    }

}
