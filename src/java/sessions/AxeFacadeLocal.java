/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface AxeFacadeLocal {

    void create(Axe axe);

    void edit(Axe axe);

    void remove(Axe axe);

    Axe find(Object id);

    List<Axe> findAll();

    List<Axe> findRange(int[] range);

    int count();

    Integer nextId()throws Exception;
    
    List<Axe> findAllRange();
    
    List<Axe> findAllRangeByCode();

}
