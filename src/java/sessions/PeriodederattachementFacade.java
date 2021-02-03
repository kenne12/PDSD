/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periodederattachement;
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
public class PeriodederattachementFacade extends AbstractFacade<Periodederattachement> implements PeriodederattachementFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodederattachementFacade() {
        super(Periodederattachement.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(p.idperiodederattachement) FROM Periodederattachement p");
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
    public List<Periodederattachement> findByNom(String nom) {
        List<Periodederattachement> periodederattachements = null;
        try {
            Query query = em.createQuery("SELECT p FROM Periodederattachement p WHERE UPPER(p.nom)=UPPER(:nom)");
            query.setParameter("nom", nom);
            periodederattachements = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodederattachements;
    }

    @Override
    public List<Periodederattachement> findByEtat(boolean etat) {
        List<Periodederattachement> periodederattachements = null;
        try {
            Query query = em.createQuery("SELECT p FROM Periodederattachement p WHERE p.etat=:etat");
            query.setParameter("etat", etat);
            periodederattachements = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodederattachements;
    }

}
