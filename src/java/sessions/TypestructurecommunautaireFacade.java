/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typestructurecommunautaire;
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
public class TypestructurecommunautaireFacade extends AbstractFacade<Typestructurecommunautaire> implements TypestructurecommunautaireFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypestructurecommunautaireFacade() {
        super(Typestructurecommunautaire.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idtypestructurecommunautaire) FROM Typestructurecommunautaire n");
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
    public List<Typestructurecommunautaire> findByNom(String nom) {
        List<Typestructurecommunautaire> typestructurecommunautaires = null;
        try {
            Query query = em.createNamedQuery("Typestructurecommunautaire.findByNomFr");
            query.setParameter("nomFr", nom);
            typestructurecommunautaires = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typestructurecommunautaires;
    }

    @Override
    public List<Typestructurecommunautaire> findAllRange() {
        List<Typestructurecommunautaire> typestructurecommunautaires = null;
        Query query = em.createQuery("SELECT s FROM Typestructurecommunautaire s ORDER BY s.nomFr");
        typestructurecommunautaires = query.getResultList();
        return typestructurecommunautaires;
    }
}
