/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Thematique;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Simo
 */
@Stateless
public class ThematiqueFacade extends AbstractFacade<Thematique> implements ThematiqueFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThematiqueFacade() {
        super(Thematique.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(t.idthematique) FROM Thematique t");
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
    public List<Thematique> findbynom(String nom) {
        List<Thematique> listThemat = null;
        try {
            Query query = em.createNamedQuery("Thematique.findByNomFr");
            query.setParameter("nomFr", nom);
            listThemat = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThemat;
    }

    @Override
    public List<Thematique> findByEtat(Boolean etat) {
        List<Thematique> thematiques = null;
        try {
            Query query = em.createNamedQuery("Thematique.findByEtat");
            query.setParameter("etat", etat);
            thematiques = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thematiques;
    }
}
