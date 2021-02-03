/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteursfacteurs;
import entities.District;
import entities.Ffom;
import entities.Menace;
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
public class MenaceFacade extends AbstractFacade<Menace> implements MenaceFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenaceFacade() {
        super(Menace.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(m.idmenace) FROM Menace m");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Menace> findByNom(String nom) {
        List<Menace> naturecontencieus = null;
        try {
            Query query = em.createNamedQuery("Menace.findByNom");
            query.setParameter("nom", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Menace> find(Menace menace, Acteursfacteurs acteursfacteurs, District district) throws Exception {
        List<Menace> menaces = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Menace r WHERE r.idmenace=:menace AND r.idacteursfacteurs.idacteursfacteurs=:acteursfacteurs AND r.iddistrict.iddistrict=:district");
        query.setParameter("menace", menace.getIdmenace()).setParameter("acteursfacteurs", acteursfacteurs.getIdacteursfacteurs()).setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            menaces = query.getResultList();
        }
        return menaces;
    }

    @Override
    public List<Menace> findByDistrict(int district) {
        List<Menace> menaces = null;
        try {
            Query query = em.createQuery("SELECT s FROM Menace s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            menaces = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menaces;
    }

    @Override
    public List<Menace> findByFfom(Ffom ffom) throws Exception {
        List<Menace> menaces = null;
        Query query = em.createQuery("SELECT m FROM Menace m WHERE m.idffom.idffom=:ffom");
        query.setParameter("ffom", ffom.getIdffom());
        menaces = query.getResultList();
        return menaces;
    }

}
