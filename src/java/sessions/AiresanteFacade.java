/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Airesante;
import entities.District;
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
public class AiresanteFacade extends AbstractFacade<Airesante> implements AiresanteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AiresanteFacade() {
        super(Airesante.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idairesante) FROM Airesante a");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Airesante> find(District district) throws Exception {
        List<Airesante> airesantes = null;
        Query query = em.createQuery("SELECT a FROM Airesante a WHERE a.iddistrict.iddistrict=:district ORDER BY a.nomFr");
        query.setParameter("district", district.getIddistrict());
        airesantes = query.getResultList();
        return airesantes;
    }

    @Override
    public List<Airesante> findAllRange() {
        List<Airesante> airesantes = null;
        Query query = em.createQuery("SELECT m FROM Airesante m ORDER BY m.nomFr");
        airesantes = query.getResultList();
        return airesantes;
    }

}
