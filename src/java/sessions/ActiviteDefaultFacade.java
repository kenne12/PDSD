/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteDefault;
import entities.Indicateur;
import entities.Sousaxe;
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
public class ActiviteDefaultFacade extends AbstractFacade<ActiviteDefault> implements ActiviteDefaultFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteDefaultFacade() {
        super(ActiviteDefault.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idactiviteDefault) FROM ActiviteDefault a");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ActiviteDefault> findBySousAxe(Sousaxe sousaxe) throws Exception {
        List<ActiviteDefault> activiteDefaults = null;
        Query query = em.createQuery("SELECT a FROM ActiviteDefault a WHERE a.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:sousaxe AND a.idindicateur.idinterventionpnds.district=true  ORDER BY a.idindicateur.idinterventionpnds.code");
        query.setParameter("sousaxe", sousaxe.getIdsousaxe());
        activiteDefaults = query.getResultList();
        return activiteDefaults;
    }

    @Override
    public List<ActiviteDefault> findByIndicateur(Indicateur indicateur) throws Exception {
        List<ActiviteDefault> activiteDefaults = null;
        Query query = em.createQuery("SELECT a FROM ActiviteDefault a WHERE a.idindicateur.idindicateur=:indicateur");
        query.setParameter("indicateur", indicateur.getIdindicateur());
        activiteDefaults = query.getResultList();
        return activiteDefaults;
    }

}
