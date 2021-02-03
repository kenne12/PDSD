/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Airesante;
import entities.Infrastructure;
import entities.Structure;
import entities.TypeinfraTypestruc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface InfrastructureFacadeLocal {

    void create(Infrastructure infrastructure);

    void edit(Infrastructure infrastructure);

    void remove(Infrastructure infrastructure);

    Infrastructure find(Object id);

    List<Infrastructure> findAll();

    List<Infrastructure> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Infrastructure> find(Structure structure) throws Exception;

    List<Infrastructure> find(Structure structure, TypeinfraTypestruc typeinfraTypestruc) throws Exception;

    List<Infrastructure> find(Airesante airesante, Boolean priorisation) throws Exception;

}
