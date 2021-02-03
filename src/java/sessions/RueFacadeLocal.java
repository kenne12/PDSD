/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rue;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RueFacadeLocal {

    void create(Rue rue);

    void edit(Rue rue);

    void remove(Rue rue);

    Rue find(Object id);

    List<Rue> findAll();

    List<Rue> findRange(int[] range);
    
    int nextId();

    List<Rue> findByNom(String nom);

    int count();
    
}
