/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Depense;
import entities.Naturedepense;
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
public class DepenseFacade extends AbstractFacade<Depense> implements DepenseFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepenseFacade() {
        super(Depense.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(d.iddepense) FROM Depense d");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Depense> find(Structure structure, Naturedepense naturedepense, Annee annee) throws Exception {
        List<Depense> depenses = new ArrayList<>();
        Query query = em.createQuery("SELECT d FROM Depense d WHERE d.idstructure.idstructure=:structure AND d.idnaturedepense.idnaturedepense=:nature AND d.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("nature", naturedepense.getIdnaturedepense()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            depenses = query.getResultList();
        }
        return depenses;
    }

    @Override
    public List<Depense> find(Structure structure, Annee annee) throws Exception {
        List<Depense> depenses = new ArrayList<>();
        Query query = em.createQuery("SELECT d FROM Depense d WHERE d.idstructure.idstructure=:structure AND d.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            depenses = query.getResultList();
        }
        return depenses;
    }

    @Override
    public List<Depense> find(Structure structure) throws Exception {
        List<Depense> depenses = new ArrayList<>();
        Query query = em.createQuery("SELECT d FROM Depense d WHERE d.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        if (!query.getResultList().isEmpty()) {
            depenses = query.getResultList();
        }
        return depenses;
    }

}
