/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Ffom;
import entities.Force;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ForceFacadeLocal {

    void create(Force force);

    void edit(Force force);

    void remove(Force force);

    Force find(Object id);

    List<Force> findAll();

    List<Force> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Force> findByFfom(Ffom ffom) throws Exception;

}
