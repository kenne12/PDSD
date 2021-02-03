/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Arrondissement;
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
public class ArrondissementFacade extends AbstractFacade<Arrondissement> implements ArrondissementFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArrondissementFacade() {
        super(Arrondissement.class);
    }
    
    
    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(d.idarrondi) FROM Arrondissement d");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

    }


    @Override
    public List<Arrondissement> findByNom(String nom) {
        List<Arrondissement>arrondissements = null;
        try {
            Query query = em.createNamedQuery("Arrondissement.findByNom");
            query.setParameter("nom", nom);
            arrondissements = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrondissements;
    }
    
}
