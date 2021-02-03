/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquemorbidite;
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
public class RubriquemorbiditeFacade extends AbstractFacade<Rubriquemorbidite> implements RubriquemorbiditeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquemorbiditeFacade() {
        super(Rubriquemorbidite.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idrubriquemorbidite) FROM Rubriquemorbidite n");
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
    public List<Rubriquemorbidite> findByNom(String nom) {
        List<Rubriquemorbidite> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Rubriquemorbidite.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Rubriquemorbidite> findAllRange() {
        List<Rubriquemorbidite> rubriquemorbidites = null;
        Query query = em.createQuery("SELECT m FROM Rubriquemorbidite m ORDER BY m.idrubriquemorbidite");
        rubriquemorbidites = query.getResultList();
        return rubriquemorbidites;
    }

}
