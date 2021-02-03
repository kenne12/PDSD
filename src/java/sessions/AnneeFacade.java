/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
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
public class AnneeFacade extends AbstractFacade<Annee> implements AnneeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnneeFacade() {
        super(Annee.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idannee) FROM Annee e");
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
    public List<Annee> findByNom(String nom) {
        List<Annee> annees = null;
        try {
            Query query = em.createNamedQuery("Annee.findByNom");
            query.setParameter("nom", nom);
            annees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    @Override
    public List<Annee> findByEtatRecette(Boolean etat) throws Exception {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.recette=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatDepense(Boolean etat) throws Exception {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.depense=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatChronogramme(Boolean etat) throws Exception {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.chronogramme=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatPlanification(Boolean etat) throws Exception {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.plannifie=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findAllRange() {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a ORDER BY a.nom");
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatCibles(Boolean etat) {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.cibles=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatMape(Boolean etat) throws Exception {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.mape=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatPopulationfosa(Boolean etat) throws Exception {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.popfosa=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

    @Override
    public List<Annee> findByEtatprojection(Boolean etat) {
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.projectionrecette=:etat ORDER BY a.nom");
        query.setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

}
