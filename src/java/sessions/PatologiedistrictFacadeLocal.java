/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Patologiedistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PatologiedistrictFacadeLocal {

    void create(Patologiedistrict patologiedistrict);

    void edit(Patologiedistrict patologiedistrict);

    void remove(Patologiedistrict patologiedistrict);

    Patologiedistrict find(Object id);

    List<Patologiedistrict> findAll();

    List<Patologiedistrict> findRange(int[] range);

    int count();
    
}
