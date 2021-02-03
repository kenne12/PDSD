/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Groupeacteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface GroupeacteurFacadeLocal {

    void create(Groupeacteur groupeacteur);

    void edit(Groupeacteur groupeacteur);

    void remove(Groupeacteur groupeacteur);

    Groupeacteur find(Object id);

    List<Groupeacteur> findAll();

    List<Groupeacteur> findRange(int[] range);

    int count();

    int nextId();

}
