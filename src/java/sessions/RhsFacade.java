/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Profilpersonnel;
import entities.Rhs;
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
public class RhsFacade extends AbstractFacade<Rhs> implements RhsFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhsFacade() {
        super(Rhs.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(r.idrhs) FROM Rhs r");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Rhs> find(Structure structure, Profilpersonnel profilpersonnel, Annee annee) throws Exception {
        List<Rhs> rhses = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Rhs r WHERE r.idstructure.idstructure=:structure AND r.idprofilpersonnel.idprofilpersonnel=:profil AND r.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("profil", profilpersonnel.getIdprofilpersonnel()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            rhses = query.getResultList();
        }
        return rhses;
    }

    @Override
    public List<Rhs> find(Structure structure, Annee annee) throws Exception {
        List<Rhs> rhses = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Rhs r WHERE r.idstructure.idstructure=:structure AND r.idannee.idannee=:annee");
        query.setParameter("structure", structure.getIdstructure()).setParameter("annee", annee.getIdannee());
        if (!query.getResultList().isEmpty()) {
            rhses = query.getResultList();
        }
        return rhses;
    }

    @Override
    public List<Rhs> find(Structure structure) throws Exception {
        List<Rhs> rhses = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Rhs r WHERE r.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        if (!query.getResultList().isEmpty()) {
            rhses = query.getResultList();
        }
        return rhses;
    }

}
