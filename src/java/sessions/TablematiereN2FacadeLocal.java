/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TablematiereN1;
import entities.TablematiereN2;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TablematiereN2FacadeLocal {

    void create(TablematiereN2 tablematiereN2);

    void edit(TablematiereN2 tablematiereN2);

    void remove(TablematiereN2 tablematiereN2);

    TablematiereN2 find(Object id);

    List<TablematiereN2> findAll();

    List<TablematiereN2> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<TablematiereN2> findByAllRange() throws Exception;
    
    List<TablematiereN2> findByAllRange(Boolean district) throws Exception;

    List<TablematiereN2> findByTablematieren1(TablematiereN1 tablematiereN1) throws Exception;

}
