/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Structurecommunautaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface StructurecommunautaireFacadeLocal {

    void create(Structurecommunautaire structurecommunautaire);

    void edit(Structurecommunautaire structurecommunautaire);

    void remove(Structurecommunautaire structurecommunautaire);

    Structurecommunautaire find(Object id);

    List<Structurecommunautaire> findAll();

    List<Structurecommunautaire> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;
    
    List<Structurecommunautaire> findByDistrict(District district)throws Exception;

}
