/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeinfraTypestruc;
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
public class TypeinfraTypestrucFacade extends AbstractFacade<TypeinfraTypestruc> implements TypeinfraTypestrucFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeinfraTypestrucFacade() {
        super(TypeinfraTypestruc.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idtypeinfraTypestruc) FROM TypeinfraTypestruc n");
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
    public List<TypeinfraTypestruc> findByTypestructure(Typestructure typestructure) throws Exception {
        List<TypeinfraTypestruc> typeinfraTypestrucs = null;
        Query query = em.createQuery("SELECT p FROM TypeinfraTypestruc p WHERE p.idtypestructure.idtypestructure=:typestructure");
        query.setParameter("typestructure", typestructure.getIdtypestructure());
        typeinfraTypestrucs = query.getResultList();
        return typeinfraTypestrucs;
    }

}
