/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatinfrastructure;
import entities.Paramcoutinfrastructure;
import entities.Typeinfrastructure;
import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ParamcoutinfrastructureFacadeLocal {

    void create(Paramcoutinfrastructure paramcoutinfrastructure);

    void edit(Paramcoutinfrastructure paramcoutinfrastructure);

    void remove(Paramcoutinfrastructure paramcoutinfrastructure);

    Paramcoutinfrastructure find(Object id);

    List<Paramcoutinfrastructure> findAll();

    List<Paramcoutinfrastructure> findRange(int[] range);

    int count();

    List<Paramcoutinfrastructure> findAllRange();

    Integer nextId() throws Exception;

    List<Paramcoutinfrastructure> findByTypeStructureTypeInfra(Typestructure typestructure, Typeinfrastructure typeinfrastructure, Etatinfrastructure etatinfrastructure) throws Exception;

}
