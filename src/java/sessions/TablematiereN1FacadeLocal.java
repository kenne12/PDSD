/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TablematiereN1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TablematiereN1FacadeLocal {

    void create(TablematiereN1 tablematiereN1);

    void edit(TablematiereN1 tablematiereN1);

    void remove(TablematiereN1 tablematiereN1);

    TablematiereN1 find(Object id);

    List<TablematiereN1> findAll();

    List<TablematiereN1> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<TablematiereN1> findAllRange() throws Exception;

    List<TablematiereN1> findAllRange(Boolean district) throws Exception;

}
