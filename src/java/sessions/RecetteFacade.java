/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Recette;
import entities.Sourcefinancement;
import entities.Structure;
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
public class RecetteFacade extends AbstractFacade<Recette> implements RecetteFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetteFacade() {
        super(Recette.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(r.idrecette) FROM Recette r");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Recette> find(Structure structure, Sourcefinancement sourcefinancement, Annee annee) throws Exception {
        List<Recette> recettes = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Recette r WHERE r.idstructure.idstructure=:structure AND r.idsourcefi.idsourcefi=:source AND r.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("source", sourcefinancement.getIdsourcefi()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            recettes = query.getResultList();
        }
        return recettes;
    }

    @Override
    public List<Recette> find(Structure structure, Annee annee) throws Exception {
        List<Recette> recettes = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Recette r WHERE r.idstructure.idstructure=:structure AND r.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            recettes = query.getResultList();
        }
        return recettes;
    }

    @Override
    public List<Recette> find(Structure structure) throws Exception {
        List<Recette> recettes = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Recette r WHERE r.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        if (!query.getResultList().isEmpty()) {
            recettes = query.getResultList();
        }
        return recettes;
    }

}
