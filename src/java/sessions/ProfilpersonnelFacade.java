/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Profilpersonnel;
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
public class ProfilpersonnelFacade extends AbstractFacade<Profilpersonnel> implements ProfilpersonnelFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfilpersonnelFacade() {
        super(Profilpersonnel.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idprofilpersonnel) FROM Profilpersonnel n");
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
    public List<Profilpersonnel> findByNom(String nom) {
        List<Profilpersonnel> profilpersonnels = null;
        try {
            Query query = em.createQuery("SELECT p FROM Profilpersonnel p WHERE p.nomFr=:nom");
            query.setParameter("nom", nom);
            profilpersonnels = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profilpersonnels;
    }

    @Override
    public List<Profilpersonnel> findAllRange() {
        List<Profilpersonnel> profilpersonnels = null;
        Query query = em.createQuery("SELECT p FROM Profilpersonnel p ORDER BY p.nomFr");
        profilpersonnels = query.getResultList();
        return profilpersonnels;
    }

}
