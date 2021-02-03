/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rue;
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
public class RueFacade extends AbstractFacade<Rue> implements RueFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RueFacade() {
        super(Rue.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(r.idRue) FROM Rue r");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<Rue> findByNom(String nom) {
        List<Rue> rues = null;
        try {
            Query query = em.createNamedQuery("Rue.findByLibelle");
            query.setParameter("nom", nom);
            rues = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rues;
    }

}
