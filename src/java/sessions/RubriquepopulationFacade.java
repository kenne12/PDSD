/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquepopulation;
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
public class RubriquepopulationFacade extends AbstractFacade<Rubriquepopulation> implements RubriquepopulationFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquepopulationFacade() {
        super(Rubriquepopulation.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idrubriquepopulation) FROM Rubriquepopulation n");
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
    public List<Rubriquepopulation> findByNom(String nom) {
        List<Rubriquepopulation> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Rubriquepopulation.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Rubriquepopulation> findAllRange() {
        List<Rubriquepopulation> rubriquepopulations = null;
        Query query = em.createQuery("SELECT r FROM Rubriquepopulation r ORDER BY r.idrubriquepopulation");
        rubriquepopulations = query.getResultList();
        return rubriquepopulations;
    }

}
