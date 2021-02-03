/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Structurecommunautaire;
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
public class StructurecommunautaireFacade extends AbstractFacade<Structurecommunautaire> implements StructurecommunautaireFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StructurecommunautaireFacade() {
        super(Structurecommunautaire.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(s.idstructurecommunautaire) FROM Structurecommunautaire s");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Structurecommunautaire> findByDistrict(District district) throws Exception {
        List<Structurecommunautaire> structurecommunautaires = null;
        Query query = em.createQuery("SELECT s FROM Structurecommunautaire s WHERE s.iddistrict.iddistrict=:district ORDER BY s.nomFr");
        query.setParameter("district", district.getIddistrict());
        structurecommunautaires = query.getResultList();
        return structurecommunautaires;
    }

}
