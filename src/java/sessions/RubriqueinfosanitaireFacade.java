/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriqueinfosanitaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
@Stateless
public class RubriqueinfosanitaireFacade extends AbstractFacade<Rubriqueinfosanitaire> implements RubriqueinfosanitaireFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriqueinfosanitaireFacade() {
        super(Rubriqueinfosanitaire.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idrubriqueinfosanitaire) FROM Rubriqueinfosanitaire n");
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
    public List<Rubriqueinfosanitaire> findByNom(String nom) {
        List<Rubriqueinfosanitaire> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Rubriqueinfosanitaire.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Rubriqueinfosanitaire> findAllRange() {
        List<Rubriqueinfosanitaire> rubriqueinfosanitaires = null;
        Query query = em.createQuery("SELECT m FROM Rubriqueinfosanitaire m ORDER BY m.idrubriqueinfosanitaire");
        rubriqueinfosanitaires = query.getResultList();
        return rubriqueinfosanitaires;
    }
}
