/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Airesante;
import entities.District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface AiresanteFacadeLocal {

    void create(Airesante airesante);

    void edit(Airesante airesante);

    void remove(Airesante airesante);

    Airesante find(Object id);

    List<Airesante> findAll();

    List<Airesante> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Airesante> find(District district) throws Exception;

    List<Airesante> findAllRange();

}
