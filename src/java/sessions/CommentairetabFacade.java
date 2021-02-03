/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Commentairetab;
import entities.District;
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
public class CommentairetabFacade extends AbstractFacade<Commentairetab> implements CommentairetabFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentairetabFacade() {
        super(Commentairetab.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idcommentairetab) FROM Commentairetab t");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Commentairetab> find(District district, Integer numero) throws Exception {
        List<Commentairetab> commentairetabs = null;
        Query query = em.createQuery("SELECT c FROM Commentairetab c WHERE c.iddistrict.iddistrict=:district AND c.numerotab=:numero");
        query.setParameter("district", district.getIddistrict()).setParameter("numero", numero);
        commentairetabs = query.getResultList();
        return commentairetabs;
    }

    @Override
    public List<Commentairetab> find(District district, Annee annee, Integer numero) throws Exception {
        List<Commentairetab> commentairetabs = null;
        Query query = em.createQuery("SELECT c FROM Commentairetab c WHERE c.iddistrict.iddistrict=:district AND c.idannee.idannee=:annee AND c.numerotab=:numero");
        query.setParameter("district", district.getIddistrict()).setParameter("annee", annee.getIdannee()).setParameter("numero", numero);
        commentairetabs = query.getResultList();
        return commentairetabs;
    }

    @Override
    public List<Commentairetab> find(District district) throws Exception {
        List<Commentairetab> commentairetabs = null;
        Query query = em.createQuery("SELECT c FROM Commentairetab c WHERE c.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        commentairetabs = query.getResultList();
        return commentairetabs;
    }

}
