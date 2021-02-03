/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Indicateur;
import entities.Resultatattendu;
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
public class ResultatattenduFacade extends AbstractFacade<Resultatattendu> implements ResultatattenduFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultatattenduFacade() {
        super(Resultatattendu.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(s.idresultatattendu) FROM Resultatattendu s");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Resultatattendu> findByIndicateur(Indicateur indicateur) throws Exception {
        List<Resultatattendu> resultatattendus = null;
        Query query = em.createQuery("SELECT r FROM Resultatattendu r WHERE r.idindicateur.idindicateur=:indicateur");
        query.setParameter("indicateur", indicateur.getIdindicateur());
        resultatattendus = query.getResultList();
        return resultatattendus;
    }

    @Override
    public List<Resultatattendu> findAllRange() {
        List<Resultatattendu> resultatattendus = null;
        Query query = em.createQuery("SELECT r FROM Resultatattendu r WHERE r.idindicateur.idinterventionpnds.district=true ORDER BY r.idindicateur.idinterventionpnds.code , r.resultatFr");
        resultatattendus = query.getResultList();
        return resultatattendus;
    }

}
