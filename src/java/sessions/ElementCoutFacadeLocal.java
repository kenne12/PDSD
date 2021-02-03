/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ElementCout;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ElementCoutFacadeLocal {

    void create(ElementCout elementCout);

    void edit(ElementCout elementCout);

    void remove(ElementCout elementCout);

    ElementCout find(Object id);

    List<ElementCout> findAll();

    List<ElementCout> findRange(int[] range);

    int count();

    Integer nextId();

    List<ElementCout> findbyElementCout(String nomFr);

}
