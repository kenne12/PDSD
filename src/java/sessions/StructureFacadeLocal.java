/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Structure;
import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface StructureFacadeLocal {

    void create(Structure structure);

    void edit(Structure structure);

    void remove(Structure structure);

    Structure find(Object id);

    List<Structure> findAll();

    List<Structure> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Structure> findByNom(District district, String nom);

    List<Structure> findByDistrict(int district);

    List<Structure> find(int district, Typestructure typestructure) throws Exception;

    List<Structure> find(Typestructure typestructure) throws Exception;

    List<Structure> findByAire(int aire) throws Exception;

}
