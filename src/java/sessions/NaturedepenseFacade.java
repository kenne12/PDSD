/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Naturedepense;
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
public class NaturedepenseFacade extends AbstractFacade<Naturedepense> implements NaturedepenseFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public NaturedepenseFacade() {
        super(Naturedepense.class);
    }
    
    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idnaturedepense) FROM Naturedepense n");
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
    public List<Naturedepense> findByNom(String nom) {
        List<Naturedepense> naturecontencieus = null;
        try {
            Query query = em.createQuery("SELECT n FROM Naturedepense n WHERE n.nomFr=:nom");
            query.setParameter("nom", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }
    
}
