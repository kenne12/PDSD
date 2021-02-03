/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Thematique;
import entities.ThematiqueActivite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Simo
 */
@Stateless
public class ThematiqueActiviteFacade extends AbstractFacade<ThematiqueActivite> implements ThematiqueActiviteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThematiqueActiviteFacade() {
        super(ThematiqueActivite.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(t.idthematiqueActivite) FROM ThematiqueActivite t ");
            Long result = (Long) query.getSingleResult();
            if (result != 0) {
                return result + 1;
            } else {
                return 1L;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1L;
        }
    }

    @Override
    public ThematiqueActivite findbythematiqueActivite(int thematique, long activite) {
        ThematiqueActivite thematiqueActivite = null;
        try {
            Query query = em.createQuery("SELECT t FROM ThematiqueActivite t WHERE t.idactivite.idactivite=:activite AND t.idthematique.idthematique=:thematique ");
            query.setParameter(thematique, thematique).setParameter("", activite);
            thematiqueActivite = (ThematiqueActivite) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thematiqueActivite;
    }

    @Override
    public List<ThematiqueActivite> retour() {
        List<ThematiqueActivite> themact = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT t FROM ThematiqueActivite t");
            themact = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return themact;
    }

    @Override
    public List<ThematiqueActivite> findbythematique(int thematique) {
        List<ThematiqueActivite> thematiqueActivites = null;
        try {
            Query query = em.createQuery("SELECT t FROM ThematiqueActivite t WHERE t.idthematique.idthematique =:thematique");
            query.setParameter("thematique", thematique);
            thematiqueActivites = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thematiqueActivites;
    }

    @Override
    public List<ThematiqueActivite> findAllRange() {
        List<ThematiqueActivite> thematiqueActivites = null;
        Query query = em.createQuery("SELECT a FROM ThematiqueActivite a ORDER BY a.idthematique.nomFr");
        thematiqueActivites = query.getResultList();
        return thematiqueActivites;
    }

    //**********************************************************************************************
    @Override
    public ThematiqueActivite findBythematiqueActv(int thematique, long activite) {
        ThematiqueActivite thematiqueActivite = null;
        try {
            Query query = em.createQuery("SELECT t FROM ThematiqueActivite t WHERE t.idthematique.idthematique=:thematique AND t.idactivite.idactivite=:activite");
            query.setParameter("thematique", thematique);
            query.setParameter("activite", activite);
            if (!query.getResultList().isEmpty()) {
                thematiqueActivite = (ThematiqueActivite) query.getResultList().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thematiqueActivite;
    }

    //**********************************************************************************************************
    @Override
    public List<ThematiqueActivite> findBythematiques(int thematique) throws Exception {
        List<ThematiqueActivite> thematiqueActivites = null;
        Query query = em.createQuery("SELECT t FROM ThematiqueActivite t WHERE t.idthematique.idthematique=:thematique");
        query.setParameter("thematique", thematique);
        thematiqueActivites = query.getResultList();
        return thematiqueActivites;
    }

    @Override
    public List<ThematiqueActivite> findByDistrict(District district) throws Exception {
        List<ThematiqueActivite> thematiqueActivites = null;
        Query query = em.createQuery("SELECT t FROM ThematiqueActivite t WHERE t.idactivite.idprobleme.idindicateurDistrict.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        thematiqueActivites = query.getResultList();
        return thematiqueActivites;
    }

    @Override
    public List<ThematiqueActivite> findByDistrictThematique(District district, Thematique thematique) throws Exception {
        List<ThematiqueActivite> thematiqueActivites = null;
        Query query = em.createQuery("SELECT t FROM ThematiqueActivite t WHERE t.idactivite.idprobleme.idindicateurDistrict.iddistrict.iddistrict=:district AND t.idthematique.idthematique=:thematique");
        query.setParameter("district", district.getIddistrict()).setParameter("thematique", thematique.getIdthematique());
        thematiqueActivites = query.getResultList();
        return thematiqueActivites;
    }
}
