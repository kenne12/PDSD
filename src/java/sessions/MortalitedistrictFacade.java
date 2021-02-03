/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Mortalite;
import entities.Mortalitedistrict;
import entities.Rubriquemortalite;
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
public class MortalitedistrictFacade extends AbstractFacade<Mortalitedistrict> implements MortalitedistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MortalitedistrictFacade() {
        super(Mortalitedistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(m.idmortalitedistrict) FROM Mortalitedistrict m");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Mortalitedistrict> find(Mortalite mortalite, Rubriquemortalite rubriquemortalite) throws Exception {
        List<Mortalitedistrict> mortalitedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Mortalitedistrict m WHERE m.idmortalite.idmortalite=:mortalite AND m.idrubriquemortalite.idrubriquemortalite=:rubriquemortalite");
        query.setParameter("mortalite", mortalite.getIdmortalite()).setParameter("rubriquemortalite", rubriquemortalite.getIdrubriquemortalite());
        if (!query.getResultList().isEmpty()) {
            mortalitedistricts = query.getResultList();
        }
        return mortalitedistricts;
    }

    @Override
    public List<Mortalitedistrict> find(Mortalite mortalite) throws Exception {
        List<Mortalitedistrict> mortalitedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Mortalitedistrict m WHERE m.idmortalite.idmortalite=:mortalite");
        query.setParameter("mortalite", mortalite.getIdmortalite());
        if (!query.getResultList().isEmpty()) {
            mortalitedistricts = query.getResultList();
        }
        return mortalitedistricts;
    }

    @Override
    public List<Mortalitedistrict> findByDistrict(int district) {
        List<Mortalitedistrict> mortalitedistricts = null;
        try {
            Query query = em.createQuery("SELECT s FROM Mortalitedistrict s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            mortalitedistricts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mortalitedistricts;
    }

    @Override
    public List<Mortalitedistrict> find(Mortalite mortalite, Rubriquemortalite rubriquemortalite, District district) throws Exception {
        List<Mortalitedistrict> mortalitedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Mortalitedistrict m WHERE m.iddistrict.iddistrict=:district AND m.idmortalite.idmortalite=:mortalite AND m.idrubriquemortalite.idrubriquemortalite=:rubriquemortalite");
        query.setParameter("mortalite", mortalite.getIdmortalite()).setParameter("rubriquemortalite", rubriquemortalite.getIdrubriquemortalite()).setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            mortalitedistricts = query.getResultList();
        }
        return mortalitedistricts;
    }

}
