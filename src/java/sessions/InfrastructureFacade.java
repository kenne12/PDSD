/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Airesante;
import entities.Infrastructure;
import entities.Structure;
import entities.TypeinfraTypestruc;
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
public class InfrastructureFacade extends AbstractFacade<Infrastructure> implements InfrastructureFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfrastructureFacade() {
        super(Infrastructure.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(i.idinfrastructure) FROM Infrastructure i");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Infrastructure> find(Structure structure) throws Exception {
        List<Infrastructure> infrastructures = null;
        Query query = em.createQuery("SELECT i FROM Infrastructure i WHERE i.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        infrastructures = query.getResultList();
        return infrastructures;
    }

    @Override
    public List<Infrastructure> find(Structure structure, TypeinfraTypestruc typeinfraTypestruc) throws Exception {
        List<Infrastructure> infrastructures = null;
        Query query = em.createQuery("SELECT i FROM Infrastructure i WHERE i.idstructure.idstructure=:structure AND i.idtypeinfraTypestruc.idtypeinfraTypestruc=:infrastructure");
        query.setParameter("structure", structure.getIdstructure()).setParameter("infrastructure", typeinfraTypestruc.getIdtypeinfraTypestruc());
        infrastructures = query.getResultList();
        return infrastructures;
    }

    @Override
    public List<Infrastructure> find(Airesante airesante, Boolean priorisation) throws Exception {
        List<Infrastructure> infrastructures = null;
        Query query = em.createQuery("SELECT i FROM Infrastructure i WHERE i.idstructure.idairesante.idairesante=:airesante AND i.idetatinfrastructure.priorite=:priorite ORDER BY i.numero");
        query.setParameter("airesante", airesante.getIdairesante()).setParameter("priorite", priorisation);
        infrastructures = query.getResultList();
        return infrastructures;
    }

}
