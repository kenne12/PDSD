/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Hospitalisation;
import entities.Hospitalisationdistrict;
import entities.Rubriquehospitalisation;
import java.util.ArrayList;
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
public class HospitalisationdistrictFacade extends AbstractFacade<Hospitalisationdistrict> implements HospitalisationdistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospitalisationdistrictFacade() {
        super(Hospitalisationdistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(m.idhospitalisationdistrict) FROM Hospitalisationdistrict m");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Hospitalisationdistrict> find(Hospitalisation hospitalisation, Rubriquehospitalisation rubriquehospitalisation) throws Exception {
        List<Hospitalisationdistrict> hospitalisationdistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Hospitalisationdistrict m WHERE m.idhospitalisation.idhospitalisation=:hospitalisation AND m.idrubriquehospitalisation.idrubriquehospitalisation=:rubriquehospitalisation");
        query.setParameter("hospitalisation", hospitalisation.getIdhospitalisation()).setParameter("rubriquehospitalisation", rubriquehospitalisation.getIdrubriquehospitalisation());
        if (!query.getResultList().isEmpty()) {
            hospitalisationdistricts = query.getResultList();
        }
        return hospitalisationdistricts;
    }

    @Override
    public List<Hospitalisationdistrict> find(Hospitalisation hospitalisation) throws Exception {
        List<Hospitalisationdistrict> hospitalisationdistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Hospitalisationdistrict m WHERE m.idhospitalisation.idhospitalisation=:hospitalisation");
        query.setParameter("hospitalisation", hospitalisation.getIdhospitalisation());
        if (!query.getResultList().isEmpty()) {
            hospitalisationdistricts = query.getResultList();
        }
        return hospitalisationdistricts;
    }

    @Override
    public List<Hospitalisationdistrict> findByDistrict(int district) {
        List<Hospitalisationdistrict> hospitalisationdistricts = null;
        try {
            Query query = em.createQuery("SELECT s FROM Hospitalisationdistrict s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            hospitalisationdistricts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospitalisationdistricts;
    }

    @Override
    public List<Hospitalisationdistrict> find(Hospitalisation hospitalisation, Rubriquehospitalisation rubriquehospitalisation, District district) throws Exception {
        List<Hospitalisationdistrict> hospitalisationdistricts = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM Hospitalisationdistrict m WHERE m.iddistrict.iddistrict=:district AND m.idhospitalisation.idhospitalisation=:hospitalisation AND m.idrubriquehospitalisation.idrubriquehospitalisation=:rubriquehospitalisation");
        query.setParameter("hospitalisation", hospitalisation.getIdhospitalisation()).setParameter("rubriquehospitalisation", rubriquehospitalisation.getIdrubriquehospitalisation()).setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            hospitalisationdistricts = query.getResultList();
        }
        return hospitalisationdistricts;
    }

}
