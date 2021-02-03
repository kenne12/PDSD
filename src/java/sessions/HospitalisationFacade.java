/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Hospitalisation;
import entities.Hospitalisation;
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
public class HospitalisationFacade extends AbstractFacade<Hospitalisation> implements HospitalisationFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospitalisationFacade() {
        super(Hospitalisation.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idhospitalisation) FROM Hospitalisation e");
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
    public List<Hospitalisation> findByNom(String nom) {
        List<Hospitalisation> hospitalisations = null;
        try {
            Query query = em.createNamedQuery("Hospitalisation.findByNomFr");
            query.setParameter("nomFr", nom);
            hospitalisations = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospitalisations;
    }

    @Override
    public List<Hospitalisation> findAllRange() {
        List<Hospitalisation> hospitalisations = null;
        Query query = em.createQuery("SELECT a FROM Hospitalisation a ORDER BY a.nomFr");
        hospitalisations = query.getResultList();
        return hospitalisations;
    }

    @Override
    public List<Hospitalisation> findAllCode() {
        List<Hospitalisation> hospitalisations = null;
        Query query = em.createQuery("SELECT a FROM Hospitalisation a ORDER BY a.code ASC");
        hospitalisations = query.getResultList();
        return hospitalisations;
    }

    @Override
    public List<Hospitalisation> findAllRangeId() {
        List<Hospitalisation> hospitalisations = null;
        Query query = em.createQuery("SELECT a FROM Hospitalisation a ORDER BY a.idhospitalisation");
        hospitalisations = query.getResultList();
        return hospitalisations;
    }
}
