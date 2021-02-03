/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Facteurdistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface FacteurdistrictFacadeLocal {

    void create(Facteurdistrict facteurdistrict);

    void edit(Facteurdistrict facteurdistrict);

    void remove(Facteurdistrict facteurdistrict);

    Facteurdistrict find(Object id);

    List<Facteurdistrict> findAll();

    List<Facteurdistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Facteurdistrict> findByDistrict(District district) throws Exception;

    List<Facteurdistrict> findByDistrict(int district);

}
