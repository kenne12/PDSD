/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Airesante;
import entities.Equipementtraceur;
import entities.Structure;
import entities.TypestrucTypeequipement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface EquipementtraceurFacadeLocal {

    void create(Equipementtraceur equipementtraceur);

    void edit(Equipementtraceur equipementtraceur);

    void remove(Equipementtraceur equipementtraceur);

    Equipementtraceur find(Object id);

    List<Equipementtraceur> findAll();

    List<Equipementtraceur> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Equipementtraceur> find(Structure structure) throws Exception;

    List<Equipementtraceur> find(Structure structure, TypestrucTypeequipement typestrucTypeequipement) throws Exception;

    List<Equipementtraceur> find(Airesante airesante, Boolean priorisation) throws Exception;

}
