/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatinfrastructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface EtatinfrastructureFacadeLocal {

    void create(Etatinfrastructure etatinfrastructure);

    void edit(Etatinfrastructure etatinfrastructure);

    void remove(Etatinfrastructure etatinfrastructure);

    Etatinfrastructure find(Object id);

    List<Etatinfrastructure> findAll();

    List<Etatinfrastructure> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;
    
    List<Etatinfrastructure> findAllRange();

}
