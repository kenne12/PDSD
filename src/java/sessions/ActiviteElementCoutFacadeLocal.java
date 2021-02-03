/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.ActiviteElementCout;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActiviteElementCoutFacadeLocal {

    void create(ActiviteElementCout activiteElementCout);

    void edit(ActiviteElementCout activiteElementCout);

    void remove(ActiviteElementCout activiteElementCout);

    ActiviteElementCout find(Object id);

    List<ActiviteElementCout> findAll();

    List<ActiviteElementCout> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ActiviteElementCout> findByActivite(Activite activite) throws Exception;

}
