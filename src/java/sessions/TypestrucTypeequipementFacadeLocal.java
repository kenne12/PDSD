/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeequipementtraceur;
import entities.TypestrucTypeequipement;
import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypestrucTypeequipementFacadeLocal {

    void create(TypestrucTypeequipement typestrucTypeequipement);

    void edit(TypestrucTypeequipement typestrucTypeequipement);

    void remove(TypestrucTypeequipement typestrucTypeequipement);

    TypestrucTypeequipement find(Object id);

    List<TypestrucTypeequipement> findAll();

    List<TypestrucTypeequipement> findRange(int[] range);

    int count();

    int nextId();

    public List<TypestrucTypeequipement> findByTypeequipementtraceur(Typeequipementtraceur typeequipementtraceur) throws Exception;

    List<TypestrucTypeequipement> findByTypestructure(Typestructure typestructure) throws Exception;
}
