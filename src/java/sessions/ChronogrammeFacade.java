/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.Annee;
import entities.Chronogramme;
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
public class ChronogrammeFacade extends AbstractFacade<Chronogramme> implements ChronogrammeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChronogrammeFacade() {
        super(Chronogramme.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idchronogramme) FROM Chronogramme c");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Chronogramme> findByActivite(Activite activite) throws Exception {
        List<Chronogramme> chronogrammes = null;
        Query query = em.createQuery("SELECT c FROM Chronogramme c WHERE c.idactivite.idactivite=:activite");
        query.setParameter("activite", activite.getIdactivite());
        chronogrammes = query.getResultList();
        return chronogrammes;
    }

    @Override
    public List<Chronogramme> findByActivite(Activite activite, Annee annee) throws Exception {
        List<Chronogramme> chronogrammes = null;
        Query query = em.createQuery("SELECT c FROM Chronogramme c WHERE c.idactivite.idactivite=:activite AND c.idannee.idannee=:annee");
        query.setParameter("activite", activite.getIdactivite()).setParameter("annee", annee.getIdannee());
        chronogrammes = query.getResultList();
        return chronogrammes;
    }

}
