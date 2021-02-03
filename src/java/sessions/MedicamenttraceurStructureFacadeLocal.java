/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Medicamenttraceur;
import entities.MedicamenttraceurStructure;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface MedicamenttraceurStructureFacadeLocal {

    void create(MedicamenttraceurStructure medicamenttraceurStructure);

    void edit(MedicamenttraceurStructure medicamenttraceurStructure);

    void remove(MedicamenttraceurStructure medicamenttraceurStructure);

    MedicamenttraceurStructure find(Object id);

    List<MedicamenttraceurStructure> findAll();

    List<MedicamenttraceurStructure> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<MedicamenttraceurStructure> find(Structure structure, Medicamenttraceur medicamenttraceur, Annee annee) throws Exception;

    List<MedicamenttraceurStructure> find(Structure structure, Annee annee) throws Exception;
    
    List<MedicamenttraceurStructure> find(Structure structure) throws Exception;

}
