/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sourcefinancement;
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
public class SourcefinancementFacade extends AbstractFacade<Sourcefinancement> implements SourcefinancementFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SourcefinancementFacade() {
        super(Sourcefinancement.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(s.idsourcefi) FROM Sourcefinancement s");
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
    public List<Sourcefinancement> findByNom(String nom) {
        List<Sourcefinancement> sources = null;
        try {
            Query query = em.createQuery("SELECT s FROM Sourcefinancement s WHERE  UPPER(s.nomFr)=UPPER(:nom)");
            query.setParameter("nom", nom);
            sources = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sources;
    }

    @Override
    public List<Sourcefinancement> findAllRange() {
        List<Sourcefinancement> sourcefinancements = null;
        Query query = em.createQuery("SELECT s FROM Sourcefinancement s ORDER BY s.nomFr");
        sourcefinancements = query.getResultList();
        return sourcefinancements;
    }

}
