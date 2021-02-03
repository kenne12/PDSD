/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Pathologie;
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
public class PathologieFacade extends AbstractFacade<Pathologie> implements PathologieFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PathologieFacade() {
        super(Pathologie.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idpathologie) FROM Pathologie e");
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
    public List<Pathologie> findByNom(String nom) {
        List<Pathologie> pathologies = null;
        try {
            Query query = em.createQuery("SELECT p FROM Pathologie p WHERE p.nomFr=:nom");
            query.setParameter("nom", nom);
            pathologies = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathologies;
    }
}
