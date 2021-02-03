/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Interventionpnds;
import entities.Sousaxe;
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
public class InterventionpndsFacade extends AbstractFacade<Interventionpnds> implements InterventionpndsFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterventionpndsFacade() {
        super(Interventionpnds.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idinterventionpnds) FROM Interventionpnds e");
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
    public List<Interventionpnds> findByNom(String nom) {
        List<Interventionpnds> interventionpndss = null;
        try {
            Query query = em.createQuery("SELECT i FROM Interventionpnds i WHERE i.nomFr=:nom");
            query.setParameter("nom", nom);
            interventionpndss = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interventionpndss;
    }

    @Override
    public List<Interventionpnds> findAllRange() {
        List<Interventionpnds> interventionpndses = null;
        Query query = em.createQuery("SELECT i FROM Interventionpnds i WHERE i.district = true ORDER BY i.idcategorieintervention.idsousaxe.idaxe.code,i.idcategorieintervention.idsousaxe.code,i.idcategorieintervention.code,i.code");
        interventionpndses = query.getResultList();
        return interventionpndses;
    }
    
    @Override
    public List<Interventionpnds> findAllRangeAll() {
        List<Interventionpnds> interventionpndses = null;
        Query query = em.createQuery("SELECT i FROM Interventionpnds i ORDER BY i.idcategorieintervention.idsousaxe.idaxe.code,i.idcategorieintervention.idsousaxe.code,i.idcategorieintervention.code,i.code");
        interventionpndses = query.getResultList();
        return interventionpndses;
    }

    @Override
    public List<Interventionpnds> findAllRangeCode() {
        List<Interventionpnds> interventionpndses = null;
        Query query = em.createQuery("SELECT i FROM Interventionpnds i WHERE i.district=true ORDER BY i.code");
        interventionpndses = query.getResultList();
        return interventionpndses;
    }

    @Override
    public List<Interventionpnds> findBySousaxe(Sousaxe sousaxe) throws Exception {
        List<Interventionpnds> interventionpndses = null;
        Query query = em.createQuery("SELECT i FROM Interventionpnds i WHERE i.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND i.district = true ORDER BY i.code");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe());
        interventionpndses = query.getResultList();
        return interventionpndses;
    }

}
