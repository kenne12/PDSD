/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Privilege;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PrivilegeFacadeLocal {

    void create(Privilege privilege);

    void edit(Privilege privilege);

    void remove(Privilege privilege);

    Privilege find(Object id);

    List<Privilege> findAll();

    List<Privilege> findRange(int[] range);

    int count();
    
    List<Privilege> findByGroupeUser(int groupeUser, Boolean etatMenu, Boolean etat);

    List<Privilege> findByGroupeUser(int groupeUser, Boolean etatMenu);

    Privilege findByGroupMenu(int groupeUser, int menu)throws Exception;

    Integer nextId() throws Exception;
    
}
