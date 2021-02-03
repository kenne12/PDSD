/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listetableau;
import entities.Listetableau;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Stateless
public class ListetableauFacade extends AbstractFacade<Listetableau> implements ListetableauFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListetableauFacade() {
        super(Listetableau.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idlisttableau) FROM Listetableau n");
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
    public List<Listetableau> findByNom(String nom) {
        List<Listetableau> listetableaus = null;
        try {
            Query query = em.createQuery("SELECT l FROM Listetableau l WHERE l.nomFr=:nom");
            query.setParameter("nom", nom);
            listetableaus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listetableaus;
    }

}
