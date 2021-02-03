/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeequipementtraceur;
import entities.TypestrucTypeequipement;
import entities.Typestructure;
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
public class TypestrucTypeequipementFacade extends AbstractFacade<TypestrucTypeequipement> implements TypestrucTypeequipementFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypestrucTypeequipementFacade() {
        super(TypestrucTypeequipement.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idtypestrucTypeequipement) FROM TypestrucTypeequipement n");
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
    public List<TypestrucTypeequipement> findByTypeequipementtraceur(Typeequipementtraceur typeequipementtraceur) throws Exception {
        List<TypestrucTypeequipement> typestructypeequipements = null;
        Query query = em.createQuery("SELECT p FROM TypestrucTypeequipement p WHERE p.idtypeequipementtraceur.idtypeequipementtraceur=:typeequipementtraceur");
        query.setParameter("typeequipementtraceur", typeequipementtraceur.getIdtypeequipementtraceur());
        typestructypeequipements = query.getResultList();
        return typestructypeequipements;
    }

    @Override
    public List<TypestrucTypeequipement> findByTypestructure(Typestructure typestructure) throws Exception {
        List<TypestrucTypeequipement> typestrucTypeequipements = null;
        Query query = em.createQuery("SELECT t FROM TypestrucTypeequipement t WHERE t.idtypestructure.idtypestructure =:typestructure");
        query.setParameter("typestructure", typestructure.getIdtypestructure());
        typestrucTypeequipements = query.getResultList();
        return typestrucTypeequipements;
    }

}
