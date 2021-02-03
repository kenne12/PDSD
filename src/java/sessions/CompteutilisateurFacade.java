/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Compteutilisateur;
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
public class CompteutilisateurFacade extends AbstractFacade<Compteutilisateur> implements CompteutilisateurFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteutilisateurFacade() {
        super(Compteutilisateur.class);
    }
    
    
    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(c.idcompte) FROM Compteutilisateur c");
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
    public Compteutilisateur login(String login, String password) {
        Compteutilisateur compteutilisateur = null;
        try {
            Query query = em.createQuery("SELECT c FROM Compteutilisateur c WHERE c.login=:login AND c.password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            compteutilisateur = (Compteutilisateur) query.getSingleResult();
        } catch (Exception e) {
            e.getCause();
            e.getMessage();
        }
        return compteutilisateur;
    }

    @Override
    public List<Compteutilisateur> findAll(Boolean etat) throws Exception {
        List<Compteutilisateur> compteutilisateurs = null;
        Query query = em.createQuery("SELECT c FROM Compteutilisateur c WHERE c.etat=:etat");
        query.setParameter("etat", etat);
        compteutilisateurs = query.getResultList();
        return compteutilisateurs;
    }
    
}
