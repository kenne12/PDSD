/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sourcefinancement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface SourcefinancementFacadeLocal {

    void create(Sourcefinancement sourcefinancement);

    void edit(Sourcefinancement sourcefinancement);

    void remove(Sourcefinancement sourcefinancement);

    Sourcefinancement find(Object id);

    List<Sourcefinancement> findAll();

    List<Sourcefinancement> findRange(int[] range);

    int count();

    int nextId();

    List<Sourcefinancement> findByNom(String nom);

    List<Sourcefinancement> findAllRange();

}
