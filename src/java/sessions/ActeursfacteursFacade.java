/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteursfacteurs;
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
public class ActeursfacteursFacade extends AbstractFacade<Acteursfacteurs> implements ActeursfacteursFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActeursfacteursFacade() {
        super(Acteursfacteurs.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idacteursfacteurs) FROM Acteursfacteurs e");
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
    public List<Acteursfacteurs> findAllRange() {
        List<Acteursfacteurs> acteursfacteurses = null;
        Query query = em.createQuery("SELECT a FROM Acteursfacteurs a ORDER BY a.nom");
        acteursfacteurses = query.getResultList();
        return acteursfacteurses;
    }

    @Override
    public List<Acteursfacteurs> findByNom(String nom) {
        List<Acteursfacteurs> acteursfacteurss = null;
        try {
            Query query = em.createNamedQuery("Acteursfacteurs.findByNom");
            query.setParameter("nom", nom);
            acteursfacteurss = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteursfacteurss;
    }

}
