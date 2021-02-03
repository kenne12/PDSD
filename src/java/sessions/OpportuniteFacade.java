/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteursfacteurs;
import entities.District;
import entities.Ffom;
import entities.Opportunite;
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
public class OpportuniteFacade extends AbstractFacade<Opportunite> implements OpportuniteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpportuniteFacade() {
        super(Opportunite.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(o.idopportunite) FROM Opportunite o");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Opportunite> findByNom(String nom) {
        List<Opportunite> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Opportunite.findByNom");
            query.setParameter("nom", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Opportunite> find(Opportunite opportunite, Acteursfacteurs acteursfacteurs, District district) throws Exception {
        List<Opportunite> opportunites = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Opportunite r WHERE r.idopportunite=:opportunite AND r.idacteursfacteurs.idacteursfacteurs=:acteursfacteurs AND r.iddistrict.iddistrict=:district");
        query.setParameter("opportunite", opportunite.getIdopportunite()).setParameter("acteursfacteurs", acteursfacteurs.getIdacteursfacteurs()).setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            opportunites = query.getResultList();
        }
        return opportunites;
    }

    @Override
    public List<Opportunite> findByDistrict(int district) {
        List<Opportunite> opportunites = null;
        try {
            Query query = em.createQuery("SELECT s FROM Opportunite s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            opportunites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opportunites;
    }

    @Override
    public List<Opportunite> findByFfom(Ffom ffom) throws Exception {
        List<Opportunite> opportunites = null;
        Query query = em.createQuery("SELECT o FROM Opportunite o WHERE o.idffom.idffom=:ffom");
        query.setParameter("ffom", ffom.getIdffom());
        opportunites = query.getResultList();
        return opportunites;
    }

}
