/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TablematiereN2;
import entities.TablematiereN3;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TablematiereN3FacadeLocal {

    void create(TablematiereN3 tablematiereN3);

    void edit(TablematiereN3 tablematiereN3);

    void remove(TablematiereN3 tablematiereN3);

    TablematiereN3 find(Object id);

    List<TablematiereN3> findAll();

    List<TablematiereN3> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<TablematiereN3> findByAllRange() throws Exception;

    List<TablematiereN3> findByAllRange(Boolean district) throws Exception;

    List<TablematiereN3> findByTablematiereN3(TablematiereN2 tablematiereN2) throws Exception;

}
