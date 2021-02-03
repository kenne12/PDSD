/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typerecette;
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
public class TyperecetteFacade extends AbstractFacade<Typerecette> implements TyperecetteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TyperecetteFacade() {
        super(Typerecette.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idtyperecette) FROM Typerecette n");
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
    public List<Typerecette> findByNom(String nom) {
        List<Typerecette> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Typerecette.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Typerecette> findAllRange() {
        List<Typerecette> typerecettes = null;
        Query query = em.createQuery("SELECT t FROM Typerecette t ORDER BY t.nomFr");
        typerecettes = query.getResultList();
        return typerecettes;
    }
}
