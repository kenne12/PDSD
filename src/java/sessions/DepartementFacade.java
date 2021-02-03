/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Departement;
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
public class DepartementFacade extends AbstractFacade<Departement> implements DepartementFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartementFacade() {
        super(Departement.class);
    }
    
    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(d.iddepart) FROM Departement d");
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
    public List<Departement> findByNom(String nom) {
        List<Departement> departements = null;
        try {
            Query query = em.createQuery("SELECT d FROM Departement d WHERE UPPER(d.nom) = UPPER(:nom)");
            query.setParameter("nom", nom);
            departements = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departements;
    }
    
}
