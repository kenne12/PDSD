/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Morbidite;
import entities.Morbiditedistrict;
import entities.Rubriquemorbidite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
@Stateless
public class MorbiditedistrictFacade extends AbstractFacade<Morbiditedistrict> implements MorbiditedistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MorbiditedistrictFacade() {
        super(Morbiditedistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(m.idmorbiditedistrict) FROM Morbiditedistrict m");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Morbiditedistrict> find(Morbidite morbidite, Rubriquemorbidite rubriquemorbidite) throws Exception {
        List<Morbiditedistrict> morbiditedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Morbiditedistrict m WHERE m.idmorbidite.idmorbidite=:morbidite AND m.idrubriquemorbidite.idrubriquemorbidite=:rubriquemorbidite");
        query.setParameter("morbidite", morbidite.getIdmorbidite()).setParameter("rubriquemorbidite", rubriquemorbidite.getIdrubriquemorbidite());
        if (!query.getResultList().isEmpty()) {
            morbiditedistricts = query.getResultList();
        }
        return morbiditedistricts;
    }

    @Override
    public List<Morbiditedistrict> find(Morbidite morbidite) throws Exception {
        List<Morbiditedistrict> morbiditedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Morbiditedistrict m WHERE m.idmorbidite.idmorbidite=:morbidite");
        query.setParameter("morbidite", morbidite.getIdmorbidite());
        if (!query.getResultList().isEmpty()) {
            morbiditedistricts = query.getResultList();
        }
        return morbiditedistricts;
    }

    @Override
    public List<Morbiditedistrict> findByDistrict(int district) {
        List<Morbiditedistrict> morbiditedistricts = null;
        try {
            Query query = em.createQuery("SELECT s FROM Morbiditedistrict s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            morbiditedistricts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return morbiditedistricts;
    }

    @Override
    public List<Morbiditedistrict> find(Morbidite morbidite, Rubriquemorbidite rubriquemorbidite, District district) throws Exception {
        List<Morbiditedistrict> morbiditedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Morbiditedistrict m WHERE m.iddistrict.iddistrict=:district AND m.idmorbidite.idmorbidite=:morbidite AND m.idrubriquemorbidite.idrubriquemorbidite=:rubriquemorbidite");
        query.setParameter("morbidite", morbidite.getIdmorbidite()).setParameter("rubriquemorbidite", rubriquemorbidite.getIdrubriquemorbidite()).setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            morbiditedistricts = query.getResultList();
        }
        return morbiditedistricts;
    }

}
