/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Facteur;
import entities.Groupefacteur;
import entities.Typefacteur;
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
public class FacteurFacade extends AbstractFacade<Facteur> implements FacteurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacteurFacade() {
        super(Facteur.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idfacteur) FROM Facteur n");
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
    public List<Facteur> findByNom(String nom) {
        List<Facteur> naturecontencieus = null;
        try {
            Query query = em.createQuery("SELECT f FROM Facteur f WHERE f.nomFr=:nom");
            query.setParameter("nom", nom);
            naturecontencieus = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naturecontencieus;
    }

    @Override
    public List<Facteur> findByDistrict(int district) {
        List<Facteur> facteurs = null;
        try {
            Query query = em.createQuery("SELECT s FROM Facteur s WHERE s.iddistrict.iddistrict=:district");
            query.setParameter("district", district);
            facteurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facteurs;
    }

    @Override
    public List<Facteur> findByGroupefacteur(int groupefacteur) {
        List<Facteur> facteurs = null;
        Query query = em.createQuery("SELECT r FROM Facteur r WHERE r.idgroupefacteur.idgroupefacteur=:groupefacteur");
        query.setParameter("groupefacteur", groupefacteur);
        if (!query.getResultList().isEmpty()) {
            facteurs = query.getResultList();
        }
        return facteurs;
    }

    @Override
    public List<Facteur> findByGroupefacteurTypefacteur(Facteur facteur, Groupefacteur groupefacteur, Typefacteur typefacteur) throws Exception {
        List<Facteur> facteurs = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Facteur r WHERE r.idfacteur=:facteur AND r.idgroupefacteur.idgroupefacteur=:groupefacteur AND r.idtypefacteur.idtypefacteur=:typefacteur");
        query.setParameter("facteur", facteur.getIdfacteur()).setParameter("groupefacteur", groupefacteur.getIdgroupefacteur()).setParameter("typefacteur", typefacteur.getIdtypefacteur());
        if (!query.getResultList().isEmpty()) {
            facteurs = query.getResultList();
        }
        return facteurs;
    }

    @Override
    public List<Facteur> find(Facteur facteur, Typefacteur typefacteur) throws Exception {
        List<Facteur> facteurs = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Facteur r WHERE r.idfacteur=:facteur AND r.idtypefacteur.idtypefacteur=:typefacteur");
        query.setParameter("facteur", facteur.getIdfacteur()).setParameter("typefacteur", typefacteur.getIdtypefacteur());
        if (!query.getResultList().isEmpty()) {
            facteurs = query.getResultList();
        }
        return facteurs;
    }

    @Override
    public List<Facteur> find(Groupefacteur groupefacteur, Typefacteur typefacteur) throws Exception {
        List<Facteur> facteurs = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Facteur r WHERE r.idgroupefacteur.idgroupefacteur=:groupefacteur AND r.idtypefacteur.idtypefacteur=:typefacteur");
        query.setParameter("groupefacteur", groupefacteur.getIdgroupefacteur()).setParameter("typefacteur", typefacteur.getIdtypefacteur());
        if (!query.getResultList().isEmpty()) {
            facteurs = query.getResultList();
        }
        return facteurs;
    }

    @Override
    public List<Facteur> findfacteur(District distrist) throws Exception {
        List<Facteur> facteurs = new ArrayList<>();
        Query query = em.createQuery("SELECT r FROM Facteur r WHERE r.idfacteur NOT IN(SELECT f FROM Facteurdistrict f WHERE f.idfacteur.idfacteur = r.idfacteur AND f.iddistrict.iddistrict=:district)");
        query.setParameter("district", distrist.getIddistrict());
        if (!query.getResultList().isEmpty()) {
            facteurs = query.getResultList();
        }
        return facteurs;
    }
}
