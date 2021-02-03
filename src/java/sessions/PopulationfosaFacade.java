/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Populationfosa;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Stateless
public class PopulationfosaFacade extends AbstractFacade<Populationfosa> implements PopulationfosaFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PopulationfosaFacade() {
        super(Populationfosa.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idpopulationfosa) FROM Populationfosa p");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Populationfosa> findByAnnee(int annee) {
        List<Populationfosa> populationfosas = null;
        try {
            Query query = em.createQuery("SELECT r FROM Populationfosa r WHERE r.idannee.idannee=:annee");
            query.setParameter("annee", annee);
            populationfosas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return populationfosas;
    }

    @Override
    public Populationfosa findByStructure(Structure structure) throws Exception {
        Populationfosa populationfosa = new Populationfosa();
        Query query = em.createQuery("SELECT a FROM Populationfosa a WHERE a.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        List<Populationfosa> populationfosas = query.getResultList();
        if (!populationfosas.isEmpty()) {
            populationfosa = populationfosas.get(0);
        }
        return populationfosa;
    }

    @Override
    public List<Populationfosa> findByStructure(int structure) throws Exception {
        List<Populationfosa> populationfosas = null;
        Query query = em.createQuery("SELECT a FROM Populationfosa a WHERE a.idstructure.idstructure=:structure");
        query.setParameter("structure", structure);
        populationfosas = query.getResultList();
        return populationfosas;
    }

    @Override
    public List<Populationfosa> find(Structure structure, Annee annee) throws Exception {
        List<Populationfosa> populationfosas = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Populationfosa m WHERE m.idstructure.idstructure=:structure AND m.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            populationfosas = query.getResultList();
        }
        return populationfosas;
    }
}
