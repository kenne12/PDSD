/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN3;
import entities.Tablematieren2District;
import entities.Tablematieren3District;
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
public class Tablematieren3DistrictFacade extends AbstractFacade<Tablematieren3District> implements Tablematieren3DistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Tablematieren3DistrictFacade() {
        super(Tablematieren3District.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtablematieren3District) FROM Tablematieren3District t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Tablematieren3District> findByDistrictTableniveau3(TablematiereN3 tablematiereN3, District district) throws Exception {
        List<Tablematieren3District> tablematieren3Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren3District t WHERE t.idtablematiereN3.idtablematiereN3=:table AND t.iddistrict.iddistrict=:district ORDER BY t.idtablematiereN3.idtablematiereN2.idtablematiereN1.idtablematiereN1,t.idtablematiereN3.idtablematiereN2.idtablematiereN2,t.idtablematiereN3.idtablematiereN3,t.numeropage");
        query.setParameter("table", tablematiereN3.getIdtablematiereN3()).setParameter("district", district.getIddistrict());
        tablematieren3Districts = query.getResultList();
        return tablematieren3Districts;
    }

    @Override
    public List<Tablematieren3District> findByTableniveau3(TablematiereN3 tablematiereN3) throws Exception {
        List<Tablematieren3District> tablematieren3Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren3District t WHERE t.idtablematiereN3.idtablematiereN3=:table");
        query.setParameter("table", tablematiereN3.getIdtablematiereN3());
        tablematieren3Districts = query.getResultList();
        return tablematieren3Districts;
    }

    @Override
    public List<Tablematieren3District> findByDistrict(District district) throws Exception {
        List<Tablematieren3District> tablematieren3Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren3District t WHERE t.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        tablematieren3Districts = query.getResultList();
        return tablematieren3Districts;
    }

}
