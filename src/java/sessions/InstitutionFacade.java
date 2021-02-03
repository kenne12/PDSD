/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Institution;
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
public class InstitutionFacade extends AbstractFacade<Institution> implements InstitutionFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitutionFacade() {
        super(Institution.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(i.idinstitution) FROM Institution i");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Institution> findByNom(String nom) {
        List<Institution> institutions = null;
        try {
            Query query = em.createQuery("SELECT l FROM Institution l WHERE l.nomFr=:nom");
            query.setParameter("nom", nom);
            institutions = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return institutions;
    }

}
