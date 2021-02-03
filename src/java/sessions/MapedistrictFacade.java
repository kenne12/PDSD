/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.District;
import entities.Mape;
import entities.Mapedistrict;
import java.util.ArrayList;
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
public class MapedistrictFacade extends AbstractFacade<Mapedistrict> implements MapedistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapedistrictFacade() {
        super(Mapedistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(m.idmapedistrict) FROM Mapedistrict m");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Mapedistrict> find(Mape mape, Annee annee) throws Exception {
        List<Mapedistrict> mapedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Mapedistrict m WHERE m.idmape.idmape=:mape AND m.idannee.idannee=:annee");
        query.setParameter("mape", mape.getIdmape()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            mapedistricts = query.getResultList();
        }
        return mapedistricts;
    }

    @Override
    public List<Mapedistrict> find(Mape mape) throws Exception {
        List<Mapedistrict> mapedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Mapedistrict m WHERE m.idmape.idmape=:mape");
        query.setParameter("mape", mape.getIdmape());
        if (!query.getResultList().isEmpty()) {
            mapedistricts = query.getResultList();
        }
        return mapedistricts;
    }

    @Override
    public List<Mapedistrict> findByDistrict(int district) {
        List<Mapedistrict> mapedistricts = null;
        try {
            Query query = em.createQuery("SELECT s FROM Mapedistrict s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            mapedistricts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapedistricts;
    }
    
    @Override
    public List<Mapedistrict> find(Mape mape, Annee annee, District district) throws Exception {
        List<Mapedistrict> mapedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Mapedistrict m WHERE m.iddistrict.iddistrict=:district AND m.idmape.idmape=:mape AND m.idannee.idannee=:annee");
        query.setParameter("mape", mape.getIdmape()).setParameter("annee", annee.getIdannee()).setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            mapedistricts = query.getResultList();
        }
        return mapedistricts;
    }
}
