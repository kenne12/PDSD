/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Quartier;
import java.util.ArrayList;
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
public class QuartierFacade extends AbstractFacade<Quartier> implements QuartierFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuartierFacade() {
        super(Quartier.class);
    }
    
    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(q.idquartier) FROM Quartier q");
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
    public List<Quartier> findByLibelle(String libelle) {
        List<Quartier> quartiers = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Quartier.findByLibelle");
            query.setParameter("libelle", libelle);
            quartiers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quartiers;
    }
    
}
