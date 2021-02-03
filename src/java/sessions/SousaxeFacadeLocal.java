/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axe;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface SousaxeFacadeLocal {

    void create(Sousaxe sousaxe);

    void edit(Sousaxe sousaxe);

    void remove(Sousaxe sousaxe);

    Sousaxe find(Object id);

    List<Sousaxe> findAll();

    List<Sousaxe> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Sousaxe> findAllRange();
    
    List<Sousaxe> findAllRangeByCode();

    List<Sousaxe> findByAxe(Axe axe) throws Exception;

}
