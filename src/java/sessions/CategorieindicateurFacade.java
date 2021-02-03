/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categorieindicateur;
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
public class CategorieindicateurFacade extends AbstractFacade<Categorieindicateur> implements CategorieindicateurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieindicateurFacade() {
        super(Categorieindicateur.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idcategorie) FROM Categorieindicateur c");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Categorieindicateur> find(String nom) throws Exception {
        List<Categorieindicateur> categorieindicateurs = null;
        Query query = em.createQuery("SELECT c FROM Categorieindicateur c WHERE UPPER(c.nomFr)=:nom");
        query.setParameter("nom", nom.toUpperCase());
        categorieindicateurs = query.getResultList();
        return categorieindicateurs;
    }
    
    
    

}
