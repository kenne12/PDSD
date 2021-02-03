/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Medicamenttraceur;
import entities.MedicamenttraceurStructure;
import entities.Notationprobleme;
import entities.Probleme;
import entities.Sousrubriquenotationprobleme;
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
public class NotationproblemeFacade extends AbstractFacade<Notationprobleme> implements NotationproblemeFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotationproblemeFacade() {
        super(Notationprobleme.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idnotationprobleme) FROM Notationprobleme p");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Notationprobleme> find(Structure structure, Medicamenttraceur medicamenttraceur, Annee annee) throws Exception {
        List<Notationprobleme> notationproblemes = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM MedicamenttraceurStructure m WHERE m.idstructure.idstructure=:structure AND m.idmedicamenttraceur.idmedicamenttraceur=:medicament AND m.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("medicament", medicamenttraceur.getIdmedicamenttraceur()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            notationproblemes = query.getResultList();
        }
        return notationproblemes;
    }

    @Override
    public List<Notationprobleme> find(Structure structure, Annee annee) throws Exception {
        List<Notationprobleme> notationproblemes = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM MedicamenttraceurStructure m WHERE m.idstructure.idstructure=:structure AND m.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            notationproblemes = query.getResultList();
        }
        return notationproblemes;
    }

    @Override
    public List<Notationprobleme> find(Probleme probleme) throws Exception {
        List<Notationprobleme> notationproblemes = null;
        Query query = em.createQuery("SELECT n FROM Notationprobleme n WHERE n.idprobleme.idprobleme=:probleme");
        query.setParameter("probleme", probleme.getIdprobleme());
        notationproblemes = query.getResultList();
        return notationproblemes;
    }

    @Override
    public List<Notationprobleme> find(Probleme probleme, Sousrubriquenotationprobleme sousrubriquenotationprobleme) throws Exception {
        List<Notationprobleme> notationproblemes = null;
        Query query = em.createQuery("SELECT n FROM Notationprobleme n WHERE n.idprobleme.idprobleme=:probleme AND n.idsousrubriquenotationprobleme.idsousrubriquenotationprobleme=:sousrubrique");
        query.setParameter("probleme", probleme.getIdprobleme()).setParameter("sousrubrique", sousrubriquenotationprobleme.getIdsousrubriquenotationprobleme());
        notationproblemes = query.getResultList();
        return notationproblemes;
    }

}
