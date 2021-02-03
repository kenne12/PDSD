/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Facteurdistrict;
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
public class FacteurdistrictFacade extends AbstractFacade<Facteurdistrict> implements FacteurdistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacteurdistrictFacade() {
        super(Facteurdistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(f.idfacteurdistrict) FROM Facteurdistrict f");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Facteurdistrict> findByDistrict(District district) throws Exception {
        List<Facteurdistrict> facteurdistricts = null;
        Query query = em.createQuery("SELECT f FROM Facteurdistrict f WHERE f.iddistrict.iddistrict=:district ORDER BY f.idfacteur.idgroupefacteur.code,f.idfacteur.idtypefacteur.nomFr,f.idfacteur.nomEn");
        query.setParameter("district", district.getIddistrict());
        facteurdistricts = query.getResultList();
        return facteurdistricts;
    }

    @Override
    public List<Facteurdistrict> findByDistrict(int district) {
        List<Facteurdistrict> facteurs = null;
        try {
            Query query = em.createQuery("SELECT s FROM Facteurdistrict s WHERE s.iddistrict.iddistrict=:district ORDER BY s.idfacteur.idgroupefacteur.code,s.idfacteur.idtypefacteur.nomFr,s.idfacteur.nomFr");
            query.setParameter("district", district);
            facteurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facteurs;
    }

}
