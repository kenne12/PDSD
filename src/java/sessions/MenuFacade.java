/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Menu;
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
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    @Override
    public List<Menu> findByEtat(Boolean etat) {
        List<Menu> menus = null;
        try {
            Query query = em.createNamedQuery("Menu.findByEtat");
            query.setParameter("etat", etat);
            menus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public Menu findByRessource(String ressource) {
        Menu menu = null;
        try {
            Query query = em.createNamedQuery("Menu.findByRessource");
            query.setParameter("ressource", ressource);
            menu = (Menu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

}
