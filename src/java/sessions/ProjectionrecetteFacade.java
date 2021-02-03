/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Projectionrecette;
import entities.Sourcefinancement;
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
public class ProjectionrecetteFacade extends AbstractFacade<Projectionrecette> implements ProjectionrecetteFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectionrecetteFacade() {
        super(Projectionrecette.class);
    }
    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(r.idprojectionrecette) FROM Projectionrecette r");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Projectionrecette> find(Structure structure, Sourcefinancement sourcefinancement, Annee annee) throws Exception {
        List<Projectionrecette> recettes = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Projectionrecette r WHERE r.idstructure.idstructure=:structure AND r.idsourcefinancement.idsourcefi=:sourcefinancement AND r.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("sourcefinancement", sourcefinancement.getIdsourcefi()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            recettes = query.getResultList();
        }
        return recettes;
    }

    @Override
    public List<Projectionrecette> find(Structure structure, Annee annee) throws Exception {
        List<Projectionrecette> recettes = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Projectionrecette r WHERE r.idstructure.idstructure=:structure AND r.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            recettes = query.getResultList();
        }
        return recettes;
    }

    @Override
    public List<Projectionrecette> find(Structure structure) throws Exception {
        List<Projectionrecette> recettes = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Projectionrecette r WHERE r.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        if (!query.getResultList().isEmpty()) {
            recettes = query.getResultList();
        }
        return recettes;
    }

}
