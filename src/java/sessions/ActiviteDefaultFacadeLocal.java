/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteDefault;
import entities.Indicateur;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActiviteDefaultFacadeLocal {

    void create(ActiviteDefault activiteDefault);

    void edit(ActiviteDefault activiteDefault);

    void remove(ActiviteDefault activiteDefault);

    ActiviteDefault find(Object id);

    List<ActiviteDefault> findAll();

    List<ActiviteDefault> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ActiviteDefault> findBySousAxe(Sousaxe sousaxe) throws Exception;

    List<ActiviteDefault> findByIndicateur(Indicateur indicateur) throws Exception;

}
