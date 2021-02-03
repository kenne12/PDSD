/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquemortalite;
import entities.Rubriquemortalite;
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
public class RubriquemortaliteFacade extends AbstractFacade<Rubriquemortalite> implements RubriquemortaliteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquemortaliteFacade() {
        super(Rubriquemortalite.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idrubriquemortalite) FROM Rubriquemortalite n");
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
    public List<Rubriquemortalite> findByNom(String nom) {
        List<Rubriquemortalite> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Rubriquemortalite.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Rubriquemortalite> findAllRange() {
        List<Rubriquemortalite> rubriquemortalites = null;
        Query query = em.createQuery("SELECT m FROM Rubriquemortalite m ORDER BY m.idrubriquemortalite");
        rubriquemortalites = query.getResultList();
        return rubriquemortalites;
    }
}
