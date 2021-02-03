/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatinfrastructure;
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
public class EtatinfrastructureFacade extends AbstractFacade<Etatinfrastructure> implements EtatinfrastructureFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtatinfrastructureFacade() {
        super(Etatinfrastructure.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(e.idetatinfrastructure) FROM Etatinfrastructure e");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Etatinfrastructure> findAllRange() {
        List<Etatinfrastructure> etatinfrastructures = null;
        Query query = em.createQuery("SELECT e FROM Etatinfrastructure e ORDER BY e.nomFr");
        etatinfrastructures = query.getResultList();
        return etatinfrastructures;
    }

}
