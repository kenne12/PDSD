/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Groupeutilisateur;
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
public class GroupeutilisateurFacade extends AbstractFacade<Groupeutilisateur> implements GroupeutilisateurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupeutilisateurFacade() {
        super(Groupeutilisateur.class);
    }

    @Override
    public Integer nextVal() throws Exception {
        Query query = em.createQuery("SELECT MAX(g.idgroupeutilisateur) FROM Groupeutilisateur g");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public Groupeutilisateur findByNom(String nom) {
        Groupeutilisateur groupeutilisateur = null;
        Query query;
        try {
            query = em.createNamedQuery("Groupeutilisateur.findByNom");
            query.setParameter("nom", nom);
            groupeutilisateur = (Groupeutilisateur) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            e.getCause();
        }
        return groupeutilisateur;
    }

    @Override
    public List<Groupeutilisateur> findByEtat(Boolean etat) {
        List<Groupeutilisateur> groupeutilisateurs = null;
        try {
            Query query = em.createNamedQuery("Groupeutilisateur.findByEtat");
            query.setParameter("etat", etat);
            groupeutilisateurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupeutilisateurs;
    }

}
