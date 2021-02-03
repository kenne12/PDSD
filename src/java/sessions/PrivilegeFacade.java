/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Privilege;
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
public class PrivilegeFacade extends AbstractFacade<Privilege> implements PrivilegeFacadeLocal {
    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivilegeFacade() {
        super(Privilege.class);
    }
    
    //en mode retrait
    @Override
    public List<Privilege> findByGroupeUser(int groupeUser, Boolean etatMenu, Boolean etat) {
        List<Privilege> privileges = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT p FROM Privilege p WHERE p.idgroupeutilisateur.idgroupeutilisateur=:groupeUser AND p.idmenu.etat =:etatMenu AND p.etat=:etat");
            query.setParameter("groupeUser", groupeUser);
            query.setParameter("etatMenu", etatMenu);
            query.setParameter("etat", etat);
            privileges = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privileges;
    }

    //en mode attribution
    @Override
    public List<Privilege> findByGroupeUser(int groupeUser, Boolean etatMenu) {
        List<Privilege> privileges = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT p FROM Privilege p WHERE p.idgroupeutilisateur.idgroupeutilisateur=:groupeUser AND p.idmenu.etat =:etatMenu");
            query.setParameter("groupeUser", groupeUser);
            query.setParameter("etatMenu", etatMenu);
            privileges = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privileges;
    }

    @Override
    public Privilege findByGroupMenu(int groupeUser, int menu) {
        Privilege privilege = null;
        try {
            Query query = em.createQuery("SELECT p FROM Privilege p WHERE p.idgroupeutilisateur.idgroupeutilisateur=:groupeUser AND p.idmenu.idmenu=:menu");
            query.setParameter("groupeUser", groupeUser);
            query.setParameter("menu", menu);
            if (!query.getResultList().isEmpty()) {
                privilege = (Privilege) query.getResultList().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privilege;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(p.idprivilege) FROM Privilege p");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }
    
}
