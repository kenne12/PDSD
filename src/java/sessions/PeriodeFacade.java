/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periode;
import entities.Periodicite;
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
public class PeriodeFacade extends AbstractFacade<Periode> implements PeriodeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodeFacade() {
        super(Periode.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(p.idperiode) FROM Periode p");
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
    public List<Periode> findByNom(String nom) {
        List<Periode> periodes = null;
        try {
            Query query = em.createQuery("SELECT p FROM Periode p WHERE UPPER(p.nom)=UPPER(:nom)");
            query.setParameter("nom", nom);
            periodes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodes;
    }

    @Override
    public List<Periode> findByPeriodicite(int periodicite, Boolean etat) {
        List<Periode> periodes = null;
        try {
            Query query = em.createQuery("SELECT p FROM Periode p WHERE p.periodicite.idperiodicite=:periodicite AND p.idperioderattachement.etat=:etat");
            query.setParameter("periodicite", periodicite).setParameter("etat", etat);
            periodes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodes;
    }

    @Override
    public List<Periode> findByPeriodicite(Periodicite periodicite) throws Exception {
        List<Periode> periodes = null;
        Query query = em.createQuery("SELECT p FROM Periode p WHERE p.periodicite.idperiodicite =:periodicite");
        query.setParameter("periodicite", periodicite.getIdperiodicite());
        periodes = query.getResultList();
        return periodes;
    }

}
