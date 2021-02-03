/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatfonctstructcom;
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
public class EtatfonctstructcomFacade extends AbstractFacade<Etatfonctstructcom> implements EtatfonctstructcomFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtatfonctstructcomFacade() {
        super(Etatfonctstructcom.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(e.idetatfonctstructcom) FROM Etatfonctstructcom e");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Etatfonctstructcom> findAllRange() {
        List<Etatfonctstructcom> etatfonctstructcoms = null;
        Query query = em.createQuery("SELECT e FROM Etatfonctstructcom e ORDER BY e.nomFr");
        etatfonctstructcoms = query.getResultList();
        return etatfonctstructcoms;
    }

}
