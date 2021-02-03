/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquegouvernance;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
@Stateless
public class RubriquegouvernanceFacade extends AbstractFacade<Rubriquegouvernance> implements RubriquegouvernanceFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquegouvernanceFacade() {
        super(Rubriquegouvernance.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idrubriquegouvernance) FROM Rubriquegouvernance n");
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
    public List<Rubriquegouvernance> findByNom(String nom) {
        List<Rubriquegouvernance> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Rubriquegouvernance.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Rubriquegouvernance> findAllRange() {
        List<Rubriquegouvernance> rubriquegouvernances = null;
        Query query = em.createQuery("SELECT m FROM Rubriquegouvernance m ORDER BY m.idrubriquegouvernance");
        rubriquegouvernances = query.getResultList();
        return rubriquegouvernances;
    }

}
