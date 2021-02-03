/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Faiblesse;
import entities.Ffom;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface FaiblesseFacadeLocal {

    void create(Faiblesse faiblesse);

    void edit(Faiblesse faiblesse);

    void remove(Faiblesse faiblesse);

    Faiblesse find(Object id);

    List<Faiblesse> findAll();

    List<Faiblesse> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Faiblesse> findByFfom(Ffom ffom) throws Exception;

}
