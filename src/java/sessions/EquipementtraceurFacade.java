/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Airesante;
import entities.Equipementtraceur;
import entities.Infrastructure;
import entities.Structure;
import entities.TypestrucTypeequipement;
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
public class EquipementtraceurFacade extends AbstractFacade<Equipementtraceur> implements EquipementtraceurFacadeLocal {

    @PersistenceContext(unitName = "PDSDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipementtraceurFacade() {
        super(Equipementtraceur.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(e.idequipementtraceur) FROM Equipementtraceur e");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Equipementtraceur> find(Structure structure) throws Exception {
        List<Equipementtraceur> equipementtraceurs = null;
        Query query = em.createQuery("SELECT e FROM Equipementtraceur e WHERE e.idstructure.idstructure=:structure");
        query.setParameter("structure", structure.getIdstructure());
        equipementtraceurs = query.getResultList();
        return equipementtraceurs;
    }

    @Override
    public List<Equipementtraceur> find(Structure structure, TypestrucTypeequipement typestrucTypeequipement) throws Exception {
        List<Equipementtraceur> equipementtraceurs = null;
        Query query = em.createQuery("SELECT e FROM Equipementtraceur e WHERE e.idstructure.idstructure=:structure AND e.idtypestrucTypeequipement.idtypestrucTypeequipement=:equipement");
        query.setParameter("structure", structure.getIdstructure()).setParameter("equipement", typestrucTypeequipement.getIdtypestrucTypeequipement());
        equipementtraceurs = query.getResultList();
        return equipementtraceurs;
    }

    @Override
    public List<Equipementtraceur> find(Airesante airesante, Boolean priorisation) throws Exception {
        List<Equipementtraceur> equipementtraceurs = null;
        Query query = em.createQuery("SELECT e FROM Equipementtraceur e WHERE e.idstructure.idairesante.idairesante=:airesante AND e.idetatequipement.priorite=:priorite ORDER BY e.numero");
        query.setParameter("airesante", airesante.getIdairesante()).setParameter("priorite", priorisation);
        equipementtraceurs = query.getResultList();
        return equipementtraceurs;
    }

}
