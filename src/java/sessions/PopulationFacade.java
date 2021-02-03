/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.District;
import entities.Population;
import entities.Recette;
import entities.Rubriquepopulation;
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
public class PopulationFacade extends AbstractFacade<Population> implements PopulationFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PopulationFacade() {
        super(Population.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idpopulation) FROM Population p");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Population> find(District district, Rubriquepopulation rubriquepopulation, Annee annee) throws Exception {
        List<Population> populations = new ArrayList<>();
        Query query = em.createQuery("SELECT p FROM Population p WHERE p.iddistrict.iddistrict=:district AND p.idrubriquepopulation.idrubriquepopulation=:rubrique AND p.idannee.idannee=:annee");
        query.setParameter("district", district.getIddistrict()).setParameter("rubrique", rubriquepopulation.getIdrubriquepopulation()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            populations = query.getResultList();
        }
        return populations;
    }

    @Override
    public List<Population> find(District district) throws Exception {
        List<Population> populations = new ArrayList<>();
        Query query = em.createQuery("SELECT p FROM Population p WHERE p.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            populations = query.getResultList();
        }
        return populations;
    }

    @Override
    public List<Population> find(District district, Annee annee) throws Exception {
        List<Population> populations = new ArrayList<>();
        Query query = em.createQuery("SELECT p FROM Population p WHERE p.iddistrict.iddistrict=:district AND p.idannee.idannee=:annee");
        query.setParameter("district", district.getIddistrict()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            populations = query.getResultList();
        }
        return populations;
    }

}
