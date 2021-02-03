/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeactivite;
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
public class TypeactiviteFacade extends AbstractFacade<Typeactivite> implements TypeactiviteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeactiviteFacade() {
        super(Typeactivite.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idtypeactivite) FROM Typeactivite n");
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
    public List<Typeactivite> findByNom(String nom) {
        List<Typeactivite> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Typeactivite.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Typeactivite> findAllRange() {
        List<Typeactivite> typeactivites = null;
        Query query = em.createQuery("SELECT t FROM Typeactivite t ORDER BY t.nomFr");
        typeactivites = query.getResultList();
        return typeactivites;
    }
}
