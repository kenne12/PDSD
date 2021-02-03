/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteur;
import entities.District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActeurFacadeLocal {

    void create(Acteur acteur);

    void edit(Acteur acteur);

    void remove(Acteur acteur);

    Acteur find(Object id);

    List<Acteur> findAll();

    List<Acteur> findRange(int[] range);

    int count();
    
    Integer nextId() throws Exception;
    
    List<Acteur>find(District district)throws Exception;
    
}
