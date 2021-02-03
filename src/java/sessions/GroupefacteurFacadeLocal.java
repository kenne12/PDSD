/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Groupefacteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface GroupefacteurFacadeLocal {

    void create(Groupefacteur groupefacteur);

    void edit(Groupefacteur groupefacteur);

    void remove(Groupefacteur groupefacteur);

    Groupefacteur find(Object id);

    List<Groupefacteur> findAll();

    List<Groupefacteur> findRange(int[] range);

    int count();

    int nextId();

}
