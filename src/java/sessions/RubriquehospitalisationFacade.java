/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquehospitalisation;
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
public class RubriquehospitalisationFacade extends AbstractFacade<Rubriquehospitalisation> implements RubriquehospitalisationFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquehospitalisationFacade() {
        super(Rubriquehospitalisation.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idrubriquehospitalisation) FROM Rubriquehospitalisation n");
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
    public List<Rubriquehospitalisation> findByNom(String nom) {
        List<Rubriquehospitalisation> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Rubriquehospitalisation.findByNomFr");
            query.setParameter("nomFr", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Rubriquehospitalisation> findAllRange() {
        List<Rubriquehospitalisation> rubriquehospitalisations = null;
        Query query = em.createQuery("SELECT m FROM Rubriquehospitalisation m ORDER BY m.idrubriquehospitalisation");
        rubriquehospitalisations = query.getResultList();
        return rubriquehospitalisations;
    }

}
