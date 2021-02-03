/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatinfrastructure;
import entities.Paramcoutinfrastructure;
import entities.Typeinfrastructure;
import entities.Typestructure;
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
public class ParamcoutinfrastructureFacade extends AbstractFacade<Paramcoutinfrastructure> implements ParamcoutinfrastructureFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParamcoutinfrastructureFacade() {
        super(Paramcoutinfrastructure.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idparamcoutinfrastructure) FROM Paramcoutinfrastructure p");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Paramcoutinfrastructure> findAllRange() {
        List<Paramcoutinfrastructure> paramcoutinfrastructures = null;
        Query query = em.createQuery("SELECT P FROM Paramcoutinfrastructure p ORDER BY p.idtypestructure.nomFr , p.idtypeinfrastructure.nomFr");
        paramcoutinfrastructures = query.getResultList();
        return paramcoutinfrastructures;
    }

    @Override
    public List<Paramcoutinfrastructure> findByTypeStructureTypeInfra(Typestructure typestructure, Typeinfrastructure typeinfrastructure, Etatinfrastructure etatinfrastructure) throws Exception {
        List<Paramcoutinfrastructure> paramcoutinfrastructures = null;
        Query query = em.createQuery("SELECT p FROM Paramcoutinfrastructure p WHERE p.idtypeinfrastructure.idtypeinfrastructure=:typeinfra AND p.idtypestructure.idtypestructure=:typestruct AND p.idetatinfrastructure.idetatinfrastructure=:etatinfra");
        query.setParameter("typeinfra", typeinfrastructure.getIdtypeinfrastructure()).setParameter("typestruct", typestructure.getIdtypestructure()).setParameter("etatinfra", etatinfrastructure.getIdetatinfrastructure());
        paramcoutinfrastructures = query.getResultList();
        return paramcoutinfrastructures;
    }

}
