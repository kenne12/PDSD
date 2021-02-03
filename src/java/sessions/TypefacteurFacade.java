/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typefacteur;
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
public class TypefacteurFacade extends AbstractFacade<Typefacteur> implements TypefacteurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypefacteurFacade() {
        super(Typefacteur.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idtypefacteur) FROM Typefacteur n");
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
    public List<Typefacteur> findByNom(String nom) {
        List<Typefacteur> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Typefacteur.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }
}
