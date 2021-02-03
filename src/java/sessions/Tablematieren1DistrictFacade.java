/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN1;
import entities.Tablematieren1District;
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
public class Tablematieren1DistrictFacade extends AbstractFacade<Tablematieren1District> implements Tablematieren1DistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Tablematieren1DistrictFacade() {
        super(Tablematieren1District.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtablematieren1District) FROM Tablematieren1District t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Tablematieren1District> findByDistrictTableniveau1(TablematiereN1 tablematiereN1, District district) throws Exception {
        List<Tablematieren1District> tablematieren1Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren1District t WHERE t.idtablematiereN1.idtablematiereN1=:table AND t.iddistrict.iddistrict=:district ORDER BY t.idtablematiereN1.idtablematiereN1,t.numeropage");
        query.setParameter("table", tablematiereN1.getIdtablematiereN1()).setParameter("district", district.getIddistrict());
        tablematieren1Districts = query.getResultList();
        return tablematieren1Districts;
    }

    @Override
    public List<Tablematieren1District> findByTableniveau1(TablematiereN1 tablematiereN1) throws Exception {
        List<Tablematieren1District> tablematieren1Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren1District t WHERE t.idtablematiereN1.idtablematiereN1=:table");
        query.setParameter("table", tablematiereN1.getIdtablematiereN1());
        tablematieren1Districts = query.getResultList();
        return tablematieren1Districts;
    }

    @Override
    public List<Tablematieren1District> findByDistrict(District district) throws Exception {
        List<Tablematieren1District> tablematieren1Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren1District t WHERE t.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        tablematieren1Districts = query.getResultList();
        return tablematieren1Districts;
    }

}
