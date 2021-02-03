/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateur;
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
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(u.idutilisateur) FROM Utilisateur u");
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
    public Utilisateur findByNomPrenom(String nom, String prenom) {
        Utilisateur utilisateur = null;
        try {
            Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE UPPER(u.nom) = UPPER(:nom) AND UPPER(u.prenom)=UPPER(:prenom)");
            query.setParameter("nom", nom);
            query.setParameter("prenom", prenom);
            utilisateur = (Utilisateur) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

}
