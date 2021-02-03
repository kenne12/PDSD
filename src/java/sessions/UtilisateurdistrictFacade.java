/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateurdistrict;
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
public class UtilisateurdistrictFacade extends AbstractFacade<Utilisateurdistrict> implements UtilisateurdistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurdistrictFacade() {
        super(Utilisateurdistrict.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(u.id) FROM Utilisateurdistrict u");
        Long resultat = (Long) query.getSingleResult();
        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public Utilisateurdistrict findByUtilisateurDistrict(int utilisateur, int district) {
        Utilisateurdistrict utilisateurdistrict = null;
        try {
            Query query = em.createQuery("SELECT u FROM Utilisateurdistrict u WHERE u.idutilisateur.idutilisateur=:utilisateur AND u.iddistrict.iddistrict=:district");
            query.setParameter(utilisateur, utilisateur).setParameter("", district);
            utilisateurdistrict = (Utilisateurdistrict) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurdistrict;
    }

    @Override
    public List<Utilisateurdistrict> get() {
        List<Utilisateurdistrict> utilisateurdistricts = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT u FROM Utilisateurdistrict u");
            utilisateurdistricts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurdistricts;
    }

    @Override
    public List<Utilisateurdistrict> findByUtilisateur(int utilisateur) {
        List<Utilisateurdistrict> utilisateurdistricts = null;
        try {
            Query query = em.createQuery("SELECT u FROM Utilisateurdistrict u WHERE u.idutilisateur.idutilisateur =:utilisateur");
            query.setParameter("utilisateur", utilisateur);
            utilisateurdistricts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurdistricts;
    }

      @Override
    public List<Utilisateurdistrict> findAllRange() {
        List<Utilisateurdistrict> utilisateurdistricts = null;
        Query query = em.createQuery("SELECT a FROM Utilisateurdistrict a ORDER BY a.iddistrict.nom");
        utilisateurdistricts = query.getResultList();
        return utilisateurdistricts;
    }
}
