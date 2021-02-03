/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeequipementtraceur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeequipementtraceurFacadeLocal {

    void create(Typeequipementtraceur typeequipementtraceur);

    void edit(Typeequipementtraceur typeequipementtraceur);

    void remove(Typeequipementtraceur typeequipementtraceur);

    Typeequipementtraceur find(Object id);

    List<Typeequipementtraceur> findAll();

    List<Typeequipementtraceur> findRange(int[] range);

    int count();

    int nextId();

    List<Typeequipementtraceur> findByNom(String nom);

}
