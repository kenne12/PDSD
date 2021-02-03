/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteurdistrict;
import entities.District;
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
public class ActeurdistrictFacade extends AbstractFacade<Acteurdistrict> implements ActeurdistrictFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActeurdistrictFacade() {
        super(Acteurdistrict.class);
    }
    
    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idacteurDistrict) FROM Acteurdistrict a");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }
    
    
    
    @Override
    public List<Acteurdistrict> findByDistrict(District district) throws Exception {
        List<Acteurdistrict> acteurdistricts = null;
        Query query = em.createQuery("SELECT a FROM Acteurdistrict a WHERE a.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        acteurdistricts = query.getResultList();
        return acteurdistricts;
    }
	
	@Override
    public List<Acteurdistrict> findByDistrict(int district) {
        List<Acteurdistrict> acteurs = null;
        try {
            Query query = em.createQuery("SELECT s FROM Acteurdistrict s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            acteurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurs;
    }
    
    
}
