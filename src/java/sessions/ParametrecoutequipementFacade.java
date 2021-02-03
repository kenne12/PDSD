/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatequipement;
import entities.Etatinfrastructure;
import entities.Paramcoutinfrastructure;
import entities.Parametrecoutequipement;
import entities.Typeequipementtraceur;
import entities.Typeinfrastructure;
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
public class ParametrecoutequipementFacade extends AbstractFacade<Parametrecoutequipement> implements ParametrecoutequipementFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrecoutequipementFacade() {
        super(Parametrecoutequipement.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idparametrecoutequipement) FROM Parametrecoutequipement p");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Parametrecoutequipement> findAllRange() {
        List<Parametrecoutequipement> parametrecoutequipements = null;
        Query query = em.createQuery("SELECT P FROM Parametrecoutequipement p ORDER BY p.idtypestructure.nomFr , p.idetatequipement.nomFr");
        parametrecoutequipements = query.getResultList();
        return parametrecoutequipements;
    }

    @Override
    public List<Parametrecoutequipement> findByTypeStructureTypeInfra(Typestructure typestructure, Typeequipementtraceur typeequipementtraceur, Etatequipement etatequipement) throws Exception {
        List<Parametrecoutequipement> parametrecoutequipements = null;
        Query query = em.createQuery("SELECT p FROM Parametrecoutequipement p WHERE p.idtypeequipement.idtypeequipementtraceur=:typeequip AND p.idtypestructure.idtypestructure=:typestruct AND p.idetatequipement.idetatequipement=:etatequip");
        query.setParameter("typeequip", typeequipementtraceur.getIdtypeequipementtraceur()).setParameter("typestruct", typestructure.getIdtypestructure()).setParameter("etatequip", etatequipement.getIdetatequipement());
        parametrecoutequipements = query.getResultList();
        return parametrecoutequipements;
    }

}
