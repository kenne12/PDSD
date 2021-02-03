/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteDefault;
import entities.CoastingDefault;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CoastingDefaultFacadeLocal {

    void create(CoastingDefault coastingDefault);

    void edit(CoastingDefault coastingDefault);

    void remove(CoastingDefault coastingDefault);

    CoastingDefault find(Object id);

    List<CoastingDefault> findAll();

    List<CoastingDefault> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<CoastingDefault> findByActivite(ActiviteDefault activiteDefault) throws Exception;

}
