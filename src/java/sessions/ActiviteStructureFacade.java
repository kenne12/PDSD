/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.ActiviteStructure;
import entities.Annee;
import entities.Probleme;
import entities.Structure;
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
public class ActiviteStructureFacade extends AbstractFacade<ActiviteStructure> implements ActiviteStructureFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteStructureFacade() {
        super(ActiviteStructure.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idactiviteStructure) FROM ActiviteStructure a");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ActiviteStructure> find(Probleme probleme) throws Exception {
        List<ActiviteStructure> activiteStructures = null;
        Query query = em.createQuery("SELECT a FROM  ActiviteStructure a WHERE a.idactivite.idprobleme.idprobleme=:probleme");
        query.setParameter("probleme", probleme.getIdprobleme());
        activiteStructures = query.getResultList();
        return activiteStructures;
    }

    @Override
    public List<ActiviteStructure> find(Activite activite) throws Exception {
        List<ActiviteStructure> activiteStructures = null;
        Query query = em.createQuery("SELECT a FROM  ActiviteStructure a WHERE a.idactivite.idactivite=:activite");
        query.setParameter("activite", activite.getIdactivite());
        activiteStructures = query.getResultList();
        return activiteStructures;
    }

    @Override
    public List<ActiviteStructure> find(Structure structure, Annee annee) throws Exception {
        List<ActiviteStructure> activiteStructures = null;
        Query query = em.createQuery("SELECT a FROM  ActiviteStructure a WHERE a.idannee.idannee=:annee AND a.idstructure.idstructure=:structure");
        query.setParameter("annee", annee.getIdannee()).setParameter("structure", structure.getIdstructure());
        activiteStructures = query.getResultList();
        return activiteStructures;
    }

    @Override
    public List<ActiviteStructure> find(Structure structure, Boolean etat) throws Exception {
        List<ActiviteStructure> activiteStructures = null;
        Query query = em.createQuery("SELECT a FROM  ActiviteStructure a WHERE a.idstructure.idstructure=:structure AND a.programe=true ORDER BY a.idactivite.nom");
        query.setParameter("structure", structure.getIdstructure());
        activiteStructures = query.getResultList();
        return activiteStructures;
    }

    @Override
    public List<ActiviteStructure> find(Structure structure, Activite activite) throws Exception {
        List<ActiviteStructure> activiteStructures = null;
        Query query = em.createQuery("SELECT a FROM ActiviteStructure a WHERE a.idactivite.idactivite=:activite AND a.idstructure.idstructure=:structure");
        query.setParameter("activite", activite.getIdactivite()).setParameter("structure", structure.getIdstructure());
        activiteStructures = query.getResultList();
        return activiteStructures;
    }

}
