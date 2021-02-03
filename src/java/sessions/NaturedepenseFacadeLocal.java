/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Naturedepense;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface NaturedepenseFacadeLocal {

    void create(Naturedepense naturedepense);

    void edit(Naturedepense naturedepense);

    void remove(Naturedepense naturedepense);

    Naturedepense find(Object id);

    List<Naturedepense> findAll();

    List<Naturedepense> findRange(int[] range);

    int count();
    
    int nextId();

    List<Naturedepense> findByNom(String nom);
}
