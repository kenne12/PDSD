/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN2;
import entities.Tablematieren1District;
import entities.Tablematieren2District;
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
public class Tablematieren2DistrictFacade extends AbstractFacade<Tablematieren2District> implements Tablematieren2DistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Tablematieren2DistrictFacade() {
        super(Tablematieren2District.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtablematieren2District) FROM Tablematieren2District t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Tablematieren2District> findByDistrictTableniveau2(TablematiereN2 tablematiereN2, District district) throws Exception {
        List<Tablematieren2District> tablematieren2Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren2District t WHERE t.idtablematiereN2.idtablematiereN2=:table AND t.iddistrict.iddistrict=:district ORDER BY t.idtablematiereN2.idtablematiereN1.idtablematiereN1,t.idtablematiereN2.idtablematiereN2,t.numeropage");
        query.setParameter("table", tablematiereN2.getIdtablematiereN2()).setParameter("district", district.getIddistrict());
        tablematieren2Districts = query.getResultList();
        return tablematieren2Districts;
    }

    @Override
    public List<Tablematieren2District> findByTableniveau2(TablematiereN2 tablematiereN2) throws Exception {
        List<Tablematieren2District> tablematieren2Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren2District t WHERE t.idtablematiereN2.idtablematiereN2=:table");
        query.setParameter("table", tablematiereN2.getIdtablematiereN2());
        tablematieren2Districts = query.getResultList();
        return tablematieren2Districts;
    }

    @Override
    public List<Tablematieren2District> findByDistrict(District district) throws Exception {
        List<Tablematieren2District> tablematieren2Districts = null;
        Query query = em.createQuery("SELECT t FROM Tablematieren2District t WHERE t.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        tablematieren2Districts = query.getResultList();
        return tablematieren2Districts;
    }

}
