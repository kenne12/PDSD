/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteur;
import entities.District;
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
public class ActeurFacade extends AbstractFacade<Acteur> implements ActeurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActeurFacade() {
        super(Acteur.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idacteur) FROM Acteur a");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Acteur> find(District district) throws Exception {
        List<Acteur> acteurs = null;
        Query query = em.createQuery("SELECT a FROM Acteur a ORDER BY a.idacteur");
        //query.setParameter("district", district.getIddistrict());
        acteurs = query.getResultList();
        return acteurs;
    }

}
