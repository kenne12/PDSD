/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Statutstructure;
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
public class StatutstructureFacade extends AbstractFacade<Statutstructure> implements StatutstructureFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatutstructureFacade() {
        super(Statutstructure.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idstatutstructure) FROM Statutstructure n");
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
    public List<Statutstructure> findByNom(String nom) {
        List<Statutstructure> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Statutstructure.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Statutstructure> findAllRange() {
        List<Statutstructure> statutstructures = null;
        Query query = em.createQuery("SELECT s FROM Statutstructure s ORDER BY s.nomEn");
        statutstructures = query.getResultList();
        return statutstructures;
    }
}
