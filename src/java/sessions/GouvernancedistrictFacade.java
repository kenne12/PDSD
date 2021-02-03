/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Gouvernancedistrict;
import entities.Rubriquegouvernance;
import entities.Structure;
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
public class GouvernancedistrictFacade extends AbstractFacade<Gouvernancedistrict> implements GouvernancedistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GouvernancedistrictFacade() {
        super(Gouvernancedistrict.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(m.idgouvernancedistrict) FROM Gouvernancedistrict m");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Gouvernancedistrict> find(Structure structure, Rubriquegouvernance rubriquegouvernance, District district) throws Exception {
        List<Gouvernancedistrict> gouvernancedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Gouvernancedistrict m WHERE  m.iddistrict.iddistrict=:district AND m.idstructure.idstructure=:structure AND m.idrubriquegouvernance.idrubriquegouvernance=:rubriquegouvernance");
        query.setParameter("district", district.getIddistrict()).setParameter("structure", structure.getIdstructure()).setParameter("rubriquegouvernance", rubriquegouvernance.getIdrubriquegouvernance());
        gouvernancedistricts = query.getResultList();
        return gouvernancedistricts;
    }

    @Override
    public List<Gouvernancedistrict> find(District district) throws Exception {
        List<Gouvernancedistrict> gouvernancedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Gouvernancedistrict m WHERE  m.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        gouvernancedistricts = query.getResultList();
        return gouvernancedistricts;
    }

    @Override
    public List<Gouvernancedistrict> find(Structure structure) throws Exception {
        List<Gouvernancedistrict> gouvernancedistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Gouvernancedistrict m WHERE  m.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        gouvernancedistricts = query.getResultList();
        return gouvernancedistricts;
    }

}
