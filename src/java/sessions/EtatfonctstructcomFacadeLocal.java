/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatfonctstructcom;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface EtatfonctstructcomFacadeLocal {

    void create(Etatfonctstructcom etatfonctstructcom);

    void edit(Etatfonctstructcom etatfonctstructcom);

    void remove(Etatfonctstructcom etatfonctstructcom);

    Etatfonctstructcom find(Object id);

    List<Etatfonctstructcom> findAll();

    List<Etatfonctstructcom> findRange(int[] range);

    int count();
    
    Integer nextId() throws Exception;
    
    List<Etatfonctstructcom> findAllRange();
    
}
