/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sourceapprovisionnement;
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
public class SourceapprovisionnementFacade extends AbstractFacade<Sourceapprovisionnement> implements SourceapprovisionnementFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SourceapprovisionnementFacade() {
        super(Sourceapprovisionnement.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idsourceapprovisionnement) FROM Sourceapprovisionnement n");
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
    public List<Sourceapprovisionnement> findByNom(String nom) {
        List<Sourceapprovisionnement> sourceapprovisionnements = null;
        try {
            Query query = em.createNamedQuery("Sourceapprovisionnement.findByNomFr");
            query.setParameter("nomFr", nom);
            sourceapprovisionnements = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceapprovisionnements;
    }

    @Override
    public List<Sourceapprovisionnement> findAllRange() {
        List<Sourceapprovisionnement> sourceapprovisionnements = null;
        Query query = em.createQuery("SELECT s FROM Sourceapprovisionnement s ORDER BY s.nomFr");
        sourceapprovisionnements = query.getResultList();
        return sourceapprovisionnements;
    }

}
